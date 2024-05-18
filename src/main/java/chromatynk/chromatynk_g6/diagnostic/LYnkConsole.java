package chromatynk.chromatynk_g6.diagnostic;

import chromatynk.chromatynk_g6.LYnk.LYnkBaseVisitor;
import chromatynk.chromatynk_g6.LYnk.LYnkParser;
import chromatynk.chromatynk_g6.exceptions.variableExceptions.VariableDoesNotExistException;
import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.utils.BooleanUtil;
import chromatynk.chromatynk_g6.utils.NumberUtil;
import chromatynk.chromatynk_g6.utils.StringUtil;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.PrintStream;
import java.util.BitSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static chromatynk.chromatynk_g6.diagnostic.LYnkValidation.SKIP_ERROR;

public class LYnkConsole extends LYnkBaseVisitor<LYnkValidation> implements ANTLRErrorListener {

    /**
     * A set of issues linked to the given code
     */
    private Set<LYnkIssue> issues;

    private LYnkVariableImpl varContext;

    //When the value is false, the syntaxError method returns without displaying errors.
    private static final boolean REPORT_SYNTAX_ERRORS = true;
    private String errorMsg = "";

    public LYnkConsole(){
        this.issues = new LinkedHashSet<>(0);
        this.varContext = new LYnkVariableImpl();
    }

    private void addIssue(final IssueType issueType, final Token token, String message) {
        final int line = token.getLine();
        final int offset = token.getCharPositionInLine() + 1;
        this.issues.add(new LYnkIssue(issueType, message, line, offset, ""));
    }

    /**
     * Visite the whole program to verify for errors.
     * @param ctx the parse tree
     * @return The validation indicating of an error if they occur
     */
    @Override
    public LYnkValidation visitProgram(final LYnkParser.ProgramContext ctx){
        final LYnkValidation result = super.visitProgram(ctx);
        return result;
    }

    /**
     * Overriding visit function to check for error in the given code with <code>LYnkValidation</code> class to detect those.
     * @param tree A tree representing a rule
     * @return The type of the Object in check or SKIP_ERROR if an error should occur
     */
    @Override
    public LYnkValidation visit(final ParseTree tree) {
        if (tree instanceof ParserRuleContext ruleContext && ruleContext.exception != null) {
            // skip validating on syntax error
            return SKIP_ERROR;
        }

        return super.visit(tree);
    }

    // Arithmetic Expression Visit
    @Override
    public LYnkValidation visitParenthesisExpression(LYnkParser.ParenthesisExpressionContext ctx){
        return visit(ctx.arithmeticExpression());
    }

    @Override
    public LYnkValidation visitMulDivExpression(LYnkParser.MulDivExpressionContext ctx){
        // Either empty value or error already detected deeper in the tree
        final LYnkValidation left = visit(ctx.left);
        if(left.isSkipError() || !left.hasValue()){
            return SKIP_ERROR;
        }
        final LYnkValidation right = visit(ctx.right);
        if(right.isSkipError() || !right.hasValue()){
            return SKIP_ERROR;
        }

        // Left and Right are either a long or a double
        // TODO: add to try catch (IllegalStateException => wrong op)
        if( left.isNumeric() && right.isNumeric() ){
            if( ctx.op.getType() == LYnkParser.DIVISION && (right.value().equals(0))){
                addIssue(IssueType.ERROR, ctx.op, "Division by 0!");
                return SKIP_ERROR;
            }
            final var result = NumberUtil.evalBinaryOperator((Number) left.value(), (Number) right.value(), ctx.op);
            if( left.isDouble() || right.isDouble()){
                return LYnkValidation.doubleVar((Double) result);
            }
            return LYnkValidation.number((Long) result);
        }

        try {

            // Left is a numeral and right is a variable
            if (left.isNumeric() && right.isIdentification()) {
                if (!this.varContext.hasVar(right.asString()) || (this.varContext.hasVar(right.asString()) && this.varContext.getVarType(right.asString()) instanceof Boolean) || (this.varContext.hasVar(right.asString()) && this.varContext.getVarType(right.asString()) instanceof String)) {
                    return SKIP_ERROR;
                }
                return LYnkValidation.doubleVar(varContext.getNumVarValue(right.asString()));
            }

            // Right is a numeral and left is a variable
            if (left.isIdentification() && right.isNumeric()) {
                if (!this.varContext.hasVar(left.asString()) || (this.varContext.hasVar(left.asString()) && this.varContext.getVarType(left.asString()) instanceof Boolean) || (this.varContext.hasVar(left.asString()) && this.varContext.getVarType(left.asString()) instanceof String)) {
                    return SKIP_ERROR;
                }
                return LYnkValidation.doubleVar(varContext.getNumVarValue(left.asString()));
            }

            // Left and right are variables
            if (left.isIdentification() && right.isIdentification()) {
                if (!this.varContext.hasVar(left.asString()) || (this.varContext.hasVar(left.asString()) && this.varContext.getVarType(left.asString()) instanceof Boolean) || (this.varContext.hasVar(left.asString()) && this.varContext.getVarType(left.asString()) instanceof String)) {
                    return SKIP_ERROR;
                }
                if (!this.varContext.hasVar(right.asString()) || (this.varContext.hasVar(right.asString()) && this.varContext.getVarType(right.asString()) instanceof Boolean) || (this.varContext.hasVar(right.asString()) && this.varContext.getVarType(right.asString()) instanceof String)) {
                    return SKIP_ERROR;
                }
                final var result = NumberUtil.evalBinaryOperator((Number) left.value(), (Number) right.value(), ctx.op);

                if (!left.isDouble() && !right.isDouble()) {
                    return LYnkValidation.number((Long) result);
                }
                return LYnkValidation.doubleVar((Double) result);
            }
        }
        catch( VariableDoesNotExistException e){
            addIssue(IssueType.ERROR, ctx.op, "The variable does not exist in the current context!" );
            return SKIP_ERROR;
        }

        return LYnkValidation.VOID;
    }

    @Override
    public LYnkValidation visitPlusMinusExpression(LYnkParser.PlusMinusExpressionContext ctx){
        // Either empty value or error already detected deeper in the tree
        final LYnkValidation left = visit(ctx.left);
        if(left.isSkipError() || !left.hasValue()){
            return SKIP_ERROR;
        }
        final LYnkValidation right = visit(ctx.right);
        if(right.isSkipError() || !right.hasValue()){
            return SKIP_ERROR;
        }

        // Left and Right are either a long or a double
        // TODO: add to try catch (IllegalStateException => wrong op)
        if( left.isNumeric() && right.isNumeric() ){
            if( ctx.op.getType() == LYnkParser.DIVISION && (right.value().equals(0))){
                addIssue(IssueType.ERROR, ctx.op, "Division by 0!");
                return SKIP_ERROR;
            }
            final var result = NumberUtil.evalBinaryOperator((Number) left.value(), (Number) right.value(), ctx.op);
            if( left.isDouble() || right.isDouble()){
                return LYnkValidation.doubleVar((Double) result);
            }
            return LYnkValidation.number((Long) result);
        }

        try {

            // Left is a numeral and right is a variable
            if (left.isNumeric() && right.isIdentification()) {
                if (!this.varContext.hasVar(right.asString()) || (this.varContext.hasVar(right.asString()) && this.varContext.getVarType(right.asString()) instanceof Boolean) || (this.varContext.hasVar(right.asString()) && this.varContext.getVarType(right.asString()) instanceof String)) {
                    return SKIP_ERROR;
                }
                return LYnkValidation.doubleVar(varContext.getNumVarValue(right.asString()));
            }

            // Right is a numeral and left is a variable
            if (left.isIdentification() && right.isNumeric()) {
                if (!this.varContext.hasVar(left.asString()) || (this.varContext.hasVar(left.asString()) && this.varContext.getVarType(left.asString()) instanceof Boolean) || (this.varContext.hasVar(left.asString()) && this.varContext.getVarType(left.asString()) instanceof String)) {
                    return SKIP_ERROR;
                }
                return LYnkValidation.doubleVar(varContext.getNumVarValue(left.asString()));
            }

            // Left and right are variables
            if (left.isIdentification() && right.isIdentification()) {
                if (!this.varContext.hasVar(left.asString()) || (this.varContext.hasVar(left.asString()) && this.varContext.getVarType(left.asString()) instanceof Boolean) || (this.varContext.hasVar(left.asString()) && this.varContext.getVarType(left.asString()) instanceof String)) {
                    return SKIP_ERROR;
                }
                if (!this.varContext.hasVar(right.asString()) || (this.varContext.hasVar(right.asString()) && this.varContext.getVarType(right.asString()) instanceof Boolean) || (this.varContext.hasVar(right.asString()) && this.varContext.getVarType(right.asString()) instanceof String)) {
                    return SKIP_ERROR;
                }
                final var result = NumberUtil.evalBinaryOperator((Number) left.value(), (Number) right.value(), ctx.op);

                if (!left.isDouble() && !right.isDouble()) {
                    return LYnkValidation.number((Long) result);
                }
                return LYnkValidation.doubleVar((Double) result);
            }
        }
        catch( VariableDoesNotExistException e){
            addIssue(IssueType.ERROR, ctx.op, "The variable does not exist in the current context!" );
            return SKIP_ERROR;
        }

        return LYnkValidation.VOID;
    }

    @Override
    public LYnkValidation visitCompExpression(LYnkParser.CompExpressionContext ctx){
        // Either empty value or error already detected deeper in the tree
        final LYnkValidation left = visit(ctx.left);
        if(left.isSkipError() || !left.hasValue()){
            return SKIP_ERROR;
        }
        final LYnkValidation right = visit(ctx.right);
        if(right.isSkipError() || !right.hasValue()){
            return SKIP_ERROR;
        }

        // Left and Right are either a long or a double
        // TODO: add to try catch (IllegalStateException => wrong op)
        if( left.isNumeric() && right.isNumeric() ){
            if( ctx.numOperator().op.getType() == LYnkParser.DIVISION && (right.value().equals(0))){
                addIssue(IssueType.ERROR, ctx.numOperator().op, "Division by 0!");
                return SKIP_ERROR;
            }
            final var result = NumberUtil.evalBinaryOperator((Number) left.value(), (Number) right.value(), ctx.numOperator().op);
            if( left.isDouble() || right.isDouble()){
                return LYnkValidation.doubleVar((Double) result);
            }
            return LYnkValidation.number((Long) result);
        }

        try {

            // Left is a numeral and right is a variable
            if (left.isNumeric() && right.isIdentification()) {
                if (!this.varContext.hasVar(right.asString()) || (this.varContext.hasVar(right.asString()) && this.varContext.getVarType(right.asString()) instanceof Boolean) || (this.varContext.hasVar(right.asString()) && this.varContext.getVarType(right.asString()) instanceof String)) {
                    return SKIP_ERROR;
                }
                return LYnkValidation.doubleVar(varContext.getNumVarValue(right.asString()));
            }

            // Right is a numeral and left is a variable
            if (left.isIdentification() && right.isNumeric()) {
                if (!this.varContext.hasVar(left.asString()) || (this.varContext.hasVar(left.asString()) && this.varContext.getVarType(left.asString()) instanceof Boolean) || (this.varContext.hasVar(left.asString()) && this.varContext.getVarType(left.asString()) instanceof String)) {
                    return SKIP_ERROR;
                }
                return LYnkValidation.doubleVar(varContext.getNumVarValue(left.asString()));
            }

            // Left and right are variables
            if (left.isIdentification() && right.isIdentification()) {
                if (!this.varContext.hasVar(left.asString()) || (this.varContext.hasVar(left.asString()) && this.varContext.getVarType(left.asString()) instanceof Boolean) || (this.varContext.hasVar(left.asString()) && this.varContext.getVarType(left.asString()) instanceof String)) {
                    return SKIP_ERROR;
                }
                if (!this.varContext.hasVar(right.asString()) || (this.varContext.hasVar(right.asString()) && this.varContext.getVarType(right.asString()) instanceof Boolean) || (this.varContext.hasVar(right.asString()) && this.varContext.getVarType(right.asString()) instanceof String)) {
                    return SKIP_ERROR;
                }
                final var result = NumberUtil.evalBinaryOperator((Number) left.value(), (Number) right.value(), ctx.numOperator().op);

                if (!left.isDouble() && !right.isDouble()) {
                    return LYnkValidation.number((Long) result);
                }
                return LYnkValidation.doubleVar((Double) result);
            }
        }
        catch (VariableDoesNotExistException e){
            addIssue(IssueType.ERROR, ctx.numOperator().op, "The variable does not exist in the current context!" );
            return SKIP_ERROR;
        }

        return LYnkValidation.VOID;
    }

    @Override
    public LYnkValidation visitNumberExpression(LYnkParser.NumberExpressionContext ctx) {
        Long value = Long.parseLong(ctx.NUMBER().getText());
        return LYnkValidation.number(value);
    }

    @Override
    public LYnkValidation visitLongExpression(LYnkParser.LongExpressionContext ctx){
        Long value = Long.parseLong(ctx.LONG().getText());
        return LYnkValidation.number(value);
    }

    @Override
    public LYnkValidation visitIdentificationExpression(LYnkParser.IdentificationExpressionContext ctx){
        String value = ctx.IDENTIFICATION().getText();
        return LYnkValidation.identification(value);
    }

    @Override
    public LYnkValidation visitDoubleExpression(LYnkParser.DoubleExpressionContext ctx){
        Double value = Double.parseDouble(ctx.DOUBLE().getText());
        return LYnkValidation.doubleVar(value);
    }
    // Boolean Expression

    /**
     * Boolean Expressions
     */

    @Override
    public LYnkValidation visitParenthesisVar(LYnkParser.ParenthesisVarContext ctx){
        return visit(ctx.booleanExpression());
    }

    @Override
    public LYnkValidation visitNotExpression(LYnkParser.NotExpressionContext ctx){
        return visit(ctx.booleanExpression());
    }

    @Override
    public LYnkValidation visitAndOrExpression(LYnkParser.AndOrExpressionContext ctx){
        // Either empty value or error already detected deeper in the tree
        final LYnkValidation left = visit(ctx.left);
        if(left.isSkipError() || !left.hasValue()){
            return SKIP_ERROR;
        }
        final LYnkValidation right = visit(ctx.right);
        if(right.isSkipError() || !right.hasValue()){
            return SKIP_ERROR;
        }
        final String operator = ctx.op.getText();
        if( !(operator.equals("AND") || operator.equals("OR")) ){
            return SKIP_ERROR;
        }

        // Both boolean
        // TODO: add to try catch (IllegalStateException => wrong op) AND DELETE CONDITIONAL ABOVE COMMENT
        if( left.isBoolean() && right.isBoolean() ){
            final Boolean value = BooleanUtil.evalAndOrOperator((Boolean) left.value(), (Boolean) right.value(), ctx.op);
            return LYnkValidation.bool(value);
        }

        try {

            // left boolean right variable
            if (left.isBoolean() && right.isIdentification()) {
                if (!(this.varContext.getVarType(right.asString()) instanceof Boolean) || !this.varContext.hasVar(right.asString())) {
                    return SKIP_ERROR;
                }
                final Boolean rightValue = varContext.getBoolVarValue(right.asString());
                final Boolean value = BooleanUtil.evalAndOrOperator((Boolean) left.value(), rightValue, ctx.op);
                return LYnkValidation.bool(value);
            }

            // right boolean left variable
            if (right.isBoolean() && left.isIdentification()) {
                if (!(this.varContext.getVarType(left.asString()) instanceof Boolean) || !this.varContext.hasVar(left.asString())) {
                    return SKIP_ERROR;
                }
                final Boolean leftValue = varContext.getBoolVarValue(left.asString());
                final Boolean value = BooleanUtil.evalAndOrOperator(leftValue, (Boolean) right.value(), ctx.op);
                return LYnkValidation.bool(value);
            }
            
            // left variable right variable
            if( left.isIdentification() && right.isIdentification() ){
                if (!this.varContext.hasVar(left.asString()) || (this.varContext.hasVar(left.asString()) && !this.varContext.getVarType(left.asString()) instanceof Boolean)) {
                    return SKIP_ERROR;
                }
                if (!this.varContext.hasVar(right.asString()) || (this.varContext.hasVar(right.asString()) && !this.varContext.getVarType(right.asString()) instanceof Boolean)) {
                    return SKIP_ERROR;
                }
                final Boolean leftValue = this.varContext.getBoolVarValue(left.asString());
                final Boolean rightValue = this.varContext.getBoolVarValue(right.asString());
                final Boolean value = BooleanUtil.evalAndOrOperator(leftValue, rightValue, ctx.op);
                return LYnkValidation.bool(value);
            }
        }
        catch (VariableDoesNotExistException e){
            addIssue(IssueType.ERROR, ctx.op, "The variable does not exist in the current context!" );
            return SKIP_ERROR;
        }
        return LYnkValidation.VOID;
    }

    @Override
    public LYnkValidation visitArithmeticComparison(LYnkParser.ArithmeticComparisonContext ctx) {
        // Either empty value or error already detected deeper in the tree
        final LYnkValidation left = visit(ctx.left);
        if (left.isSkipError() || !left.hasValue()) {
            return SKIP_ERROR;
        }
        final LYnkValidation right = visit(ctx.right);
        if (right.isSkipError() || !right.hasValue()) {
            return SKIP_ERROR;
        }

        try {
            // Both numeric values
            if (left.isNumeric() && right.isNumeric()) {
                final Boolean value = NumberUtil.evalNumberComparisonOperator((Number) left.value(), (Number) right.value(), ctx.arithmeticOperator().op);
                return LYnkValidation.bool(value);
            }
            // right number left variable
            if (right.isNumeric() && left.isIdentification()) {
                if (!(this.varContext.getVarType(left.asString()) instanceof Double) || !this.varContext.hasVar(left.asString()) ) {
                    return SKIP_ERROR;
                }
                final Number leftValue = varContext.getNumVarValue(left.asString());
                final Boolean value = NumberUtil.evalNumberComparisonOperator(leftValue, (Number) right.value(), ctx.arithmeticOperator().op);
                return LYnkValidation.bool(value);
            }
            // left number right variable
            if (left.isNumeric() && right.isIdentification()) {
                if (!(this.varContext.getVarType(right.asString()) instanceof Double) || !this.varContext.hasVar(right.asString()) ){
                    return SKIP_ERROR;
                }
                final Number rightValue = varContext.getNumVarValue(right.asString());
                final Boolean value = NumberUtil.evalNumberComparisonOperator((Number) left.value(), rightValue, ctx.arithmeticOperator().op);
                return LYnkValidation.bool(value);
            }

            // left variable right variable
            if( left.isIdentification() && right.isIdentification() ){
                if (!this.varContext.hasVar(left.asString()) || (this.varContext.hasVar(left.asString()) && !(this.varContext.getVarType(left.asString()) instanceof Double))) {
                    return SKIP_ERROR;
                }
                if (!this.varContext.hasVar(right.asString()) || (this.varContext.hasVar(right.asString()) && !(this.varContext.getVarType(right.asString()) instanceof Double))) {
                    return SKIP_ERROR;
                }
                final Number leftValue = this.varContext.getNumVarValue(left.asString());
                final Number rightValue = this.varContext.getNumVarValue(right.asString());
                final Boolean value = NumberUtil.evalNumberComparisonOperator(leftValue, rightValue, ctx.arithmeticOperator().op);
                return LYnkValidation.bool(value);
            }
        }
        catch(IllegalStateException e){
            addIssue(IssueType.ERROR, ctx.arithmeticOperator().op, e.getMessage());
            return SKIP_ERROR;
        }
        catch(VariableDoesNotExistException e){
            addIssue(IssueType.ERROR, ctx.getStart(), "Une variable n'existe pas dans ce contexte!");
            return SKIP_ERROR;
        }
        return LYnkValidation.bool(null);
    }

    @Override
    public LYnkValidation visitBooleanComparison(LYnkParser.BooleanComparisonContext ctx){
        // Either empty value or error already detected deeper in the tree
        final LYnkValidation left = visit(ctx.left);
        if (left.isSkipError() || !left.hasValue()) {
            return SKIP_ERROR;
        }
        final LYnkValidation right = visit(ctx.right);
        if (right.isSkipError() || !right.hasValue()) {
            return SKIP_ERROR;
        }

        // Both boolean
        // TODO: add to try catch (IllegalStateException => wrong op)
        try {
            if (left.isBoolean() && right.isBoolean()) {
                final Boolean value = BooleanUtil.evalAndOrOperator((Boolean) left.value(), (Boolean) right.value(), ctx.boolOperator().op);
                return LYnkValidation.bool(value);
            }
            // left boolean right variable
            if (left.isBoolean() && right.isIdentification()) {
                if (!(this.varContext.getVarType(right.asString()) instanceof Boolean) || !this.varContext.hasVar(right.asString())) {
                    return SKIP_ERROR;
                }
                final Boolean rightValue = varContext.getBoolVarValue(right.asString());
                final Boolean value = BooleanUtil.evalAndOrOperator((Boolean) left.value(), rightValue, ctx.boolOperator().op);
                return LYnkValidation.bool(value);
            }

            // right boolean left variable
            if (right.isBoolean() && left.isIdentification()) {
                if (!(this.varContext.getVarType(left.asString()) instanceof Boolean) || !this.varContext.hasVar(left.asString())) {
                    return SKIP_ERROR;
                }
                final Boolean leftValue = varContext.getBoolVarValue(left.asString());
                final Boolean value = BooleanUtil.evalAndOrOperator(leftValue, (Boolean) right.value(), ctx.boolOperator().op);
                return LYnkValidation.bool(value);
            }

            // left variable right variable
            if( left.isIdentification() && right.isIdentification() ){
                if (!this.varContext.hasVar(left.asString()) || (this.varContext.hasVar(left.asString()) && !(this.varContext.getVarType(left.asString()) instanceof Boolean))) {
                    return SKIP_ERROR;
                }
                if (!this.varContext.hasVar(right.asString()) || (this.varContext.hasVar(right.asString()) && !(this.varContext.getVarType(right.asString()) instanceof Boolean))) {
                    return SKIP_ERROR;
                }
                final Boolean leftValue = this.varContext.getBoolVarValue(left.asString());
                final Boolean rightValue = this.varContext.getBoolVarValue(right.asString());
                final Boolean value = BooleanUtil.evalAndOrOperator(leftValue, rightValue, ctx.boolOperator().op);
                return LYnkValidation.bool(value);
            }
        }
        catch(IllegalStateException e){
            addIssue(IssueType.ERROR, ctx.boolOperator().op, e.getMessage());
            return SKIP_ERROR;
        }
        catch(VariableDoesNotExistException e){
            addIssue(IssueType.ERROR, ctx.getStart(), "Une variable n'existe pas dans ce contexte!");
            return SKIP_ERROR;
        }
        return LYnkValidation.bool(null);
    }

    @Override
    public LYnkValidation visitLiteralComparison(LYnkParser.LiteralComparisonContext ctx){
        // Either empty value or error already detected deeper in the tree
        final Token left = ctx.left;
        if (left.getType() != LYnkParser.LITERAL || left.getType() != LYnkParser.IDENTIFICATION ) {
            return SKIP_ERROR;
        }
        final Token right = ctx.right;
        if (right.getType() != LYnkParser.LITERAL || right.getType() != LYnkParser.IDENTIFICATION ){
            return SKIP_ERROR;
        }

        try {
            // Both are Literal
            if( left.getType() == LYnkParser.LITERAL && right.getType() == LYnkParser.LITERAL){
                final Boolean value = StringUtil.evalLiteralComparisonOperator(left.getText() , right.getText() , ctx.arithmeticOperator().op);
                return LYnkValidation.bool(value);
            }
            // left literal right identification
            if( left.getType() == LYnkParser.LITERAL && right.getType() == LYnkParser.IDENTIFICATION){
                if( !varContext.hasVar(right.getText()) || (varContext.hasVar(right.getText()) && !(varContext.getVarType(right.getText()) instanceof String))){
                    return SKIP_ERROR;
                }
                final String rightValue = varContext.getStrVarValue(right.getText());
                final Boolean value = StringUtil.evalLiteralComparisonOperator(left.getText() , rightValue , ctx.arithmeticOperator().op);
                return LYnkValidation.bool(value);
            }
            // right literal left identification
            if( right.getType() == LYnkParser.LITERAL && left.getType() == LYnkParser.IDENTIFICATION){
                if( !varContext.hasVar(left.getText()) || (varContext.hasVar(left.getText()) && !(varContext.getVarType(left.getText()) instanceof String))){
                    return SKIP_ERROR;
                }
                final String leftValue = varContext.getStrVarValue(left.getText());
                final Boolean value = StringUtil.evalLiteralComparisonOperator(leftValue , right.getText() , ctx.arithmeticOperator().op);
                return LYnkValidation.bool(value);
            }
            // right identification left identification
            if( right.getType() == LYnkParser.IDENTIFICATION && left.getType() == LYnkParser.IDENTIFICATION){
                if( !varContext.hasVar(left.getText()) || (varContext.hasVar(left.getText()) && !(varContext.getVarType(left.getText()) instanceof String))){
                    return SKIP_ERROR;
                }
                if( !varContext.hasVar(right.getText()) || (varContext.hasVar(right.getText()) && !(varContext.getVarType(right.getText()) instanceof String))){
                    return SKIP_ERROR;
                }
                final String leftValue = varContext.getStrVarValue(left.getText());
                final String rightValue = varContext.getStrVarValue(right.getText());
                final Boolean value = StringUtil.evalLiteralComparisonOperator(leftValue , rightValue , ctx.arithmeticOperator().op);
                return LYnkValidation.bool(value);
            }
        }
        catch(IllegalStateException e){
            addIssue(IssueType.ERROR, ctx.arithmeticOperator().op, e.getMessage());
            return SKIP_ERROR;
        }
        catch(VariableDoesNotExistException e){
            addIssue(IssueType.ERROR, ctx.getStart(), "Une variable n'existe pas dans ce contexte!");
            return SKIP_ERROR;
        }
        return LYnkValidation.bool(null);
    }
    
    @Override
    public LYnkValidation visitIdentificationVar(LYnkParser.IdentificationVarContext ctx){
        try {
            final Object variableValue = varContext.getVarValue(ctx.IDENTIFICATION().getText());
            if( variableValue instanceof Boolean){
                return LYnkValidation.bool((Boolean) variableValue);
            }
            if( variableValue instanceof String){
                return LYnkValidation.string((String) variableValue);
            }
            if( variableValue instanceof Double){
                return LYnkValidation.doubleVar((Double) variableValue);
            }
            if( variableValue instanceof Long){
                return LYnkValidation.number((Long) variableValue);
            }
        }
        catch(VariableDoesNotExistException e){
            addIssue(IssueType.ERROR, ctx.getStart(), ctx.getText() + " n'existe pas dans le contexte actuel!");
            return SKIP_ERROR;
        }
        return LYnkValidation.identification(null);
    }

    @Override
    public LYnkValidation visitTrueVar(LYnkParser.TrueVarContext ctx){
        return LYnkValidation.bool(Boolean.TRUE);
    }

    @Override
    public LYnkValidation visitFalseVar(LYnkParser.FalseVarContext ctx){
        return LYnkValidation.bool(Boolean.FALSE);
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException re){
        if(!REPORT_SYNTAX_ERRORS){
            return;
        }
        this.issues.add(new LYnkIssue(IssueType.ERROR, msg, charPositionInLine, line, ""));

        String sourceName = recognizer.getInputStream().getSourceName();
        if (!sourceName.isEmpty()) {
            sourceName = String.format("%s:%d:%d: ", sourceName, line, charPositionInLine);
        }

        System.err.println(sourceName+"line "+line+":"+charPositionInLine+" "+msg);
        this.errorMsg = this.errorMsg + "\n" + sourceName+"line "+line+":"+charPositionInLine+" "+msg;
    }

    @Override
    public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean b, BitSet bitSet, ATNConfigSet atnConfigSet) {
        //no implementation at the moment
    }

    @Override
    public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitSet, ATNConfigSet atnConfigSet) {
        //no implementation at the moment
    }

    @Override
    public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atnConfigSet) {
        //no implementation at the moment
    }
}
