package chromatynk.chromatynk_g6.diagnostic;

import chromatynk.chromatynk_g6.CursorManager;
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

import java.util.*;

import static chromatynk.chromatynk_g6.diagnostic.LYnkValidation.SKIP_ERROR;
import static chromatynk.chromatynk_g6.diagnostic.LYnkValidation.VOID;

public class LYnkConsole extends LYnkBaseVisitor<LYnkValidation> implements ANTLRErrorListener {

    /**
     * A set of issues linked to the given code
     */
    private Set<LYnkIssue> issues;

    private LYnkVariableImpl varContext;
    private CursorManager cursorContext;

    private List<String> validStatements;

    //When the value is false, the syntaxError method returns without displaying errors.
    private static final boolean REPORT_SYNTAX_ERRORS = true;

    public LYnkConsole(){
        this.issues = new LinkedHashSet<>(0);
        this.varContext = new LYnkVariableImpl();
        this.cursorContext = new CursorManager();
        this.validStatements = new ArrayList<String>();
    }

    private void addIssue(final IssueType issueType, final Token token, String message) {
        final int line = token.getLine();
        final int offset = token.getCharPositionInLine() + 1;
        this.issues.add(new LYnkIssue(issueType, message, line, offset, ""));
    }

    public List<LYnkIssue> getIssues(){
        return issues.stream().toList();
    }

    /**
     * Visite the whole program to verify for errors.
     * @param ctx the parse tree
     * @return The validation indicating of an error if they occur
     */
    @Override
    public LYnkValidation visitProgram(final LYnkParser.ProgramContext ctx){
        if(!ctx.EOF().getText().isEmpty()) {
            LYnkValidation tempValidation = VOID;
            for (LYnkParser.StatementContext statement : ctx.statement()) {
                if (tempValidation.isSkipError() || !tempValidation.hasValue()) {
                    return SKIP_ERROR;
                }
                final LYnkValidation statementValidation = visit(statement);
                if (!statementValidation.isSkipError() && statementValidation.hasValue()) {
                    validStatements.add(statement.getText());
                } else {
                    tempValidation = statementValidation;
                }
            }
            return tempValidation;
        }
        addIssue(IssueType.ERROR, ctx.EOF().getSymbol(), "The input file/program does not contain an end of file (i.e. EOF)");
        return SKIP_ERROR;
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
    /**
     * Arithmetic Expressions
     */
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

        try {
            // Left and Right are either a long or a double
            if (left.isNumeric() && right.isNumeric()) {
                if (ctx.op.getType() == LYnkParser.DIVISION && (right.value().equals(0))) {
                    addIssue(IssueType.ERROR, ctx.op, "Division by 0!");
                    return SKIP_ERROR;
                }
                final Number result = NumberUtil.evalBinaryOperator((Number) left.value(), (Number) right.value(), ctx.op);
                if (left.isDouble() || right.isDouble()) {
                    return LYnkValidation.doubleVar((Double) result);
                }
                return LYnkValidation.number((Long) result);
            }

            // Left is a numeral and right is a variable
            if (left.isNumeric() && right.isIdentification()) {
                if (this.varContext.getVarType(right.asString()) instanceof Boolean || this.varContext.getVarType(right.asString()) instanceof String) {
                    addIssue(IssueType.ERROR, ctx.op, "The right variable does not have a supported type for this operation: " + this.varContext.getVarType(right.asString()));
                    return SKIP_ERROR;
                }
                final Number rightValue = this.varContext.getNumVarValue(right.asString());
                final Number result = NumberUtil.evalBinaryOperator((Number) left.value(), rightValue, ctx.op );
                if (left.isDouble() || rightValue instanceof Double) {
                    return LYnkValidation.doubleVar((Double) result);
                }
                return LYnkValidation.number((Long) result);
            }

            // Right is a numeral and left is a variable
            if (left.isIdentification() && right.isNumeric()) {
                if (this.varContext.getVarType(left.asString()) instanceof Boolean || this.varContext.getVarType(left.asString()) instanceof String) {
                    addIssue(IssueType.ERROR, ctx.op, "The left variable does not have a supported type for this operation: " + this.varContext.getVarType(left.asString()));
                    return SKIP_ERROR;
                }
                final Number leftValue = this.varContext.getNumVarValue(left.asString());
                final Number result = NumberUtil.evalBinaryOperator(leftValue, (Number) right.value(), ctx.op );
                if (right.isDouble() || leftValue  instanceof Double) {
                    return LYnkValidation.doubleVar((Double) result);
                }
                return LYnkValidation.number((Long) result);
            }

            // Left and right are variables
            if (left.isIdentification() && right.isIdentification()) {
                if (this.varContext.getVarType(left.asString()) instanceof Boolean || this.varContext.getVarType(left.asString()) instanceof String) {
                    addIssue(IssueType.ERROR, ctx.op, "The left variable does not have a supported type for this operation: " + this.varContext.getVarType(left.asString()));
                    return SKIP_ERROR;
                }
                if (this.varContext.getVarType(right.asString()) instanceof Boolean || this.varContext.getVarType(right.asString()) instanceof String) {
                    addIssue(IssueType.ERROR, ctx.op, "The right variable does not have a supported type for this operation: " + this.varContext.getVarType(right.asString()));
                    return SKIP_ERROR;
                }
                final Number leftValue = this.varContext.getNumVarValue(left.asString());
                final Number rightValue = this.varContext.getNumVarValue(right.asString());
                final var result = NumberUtil.evalBinaryOperator(leftValue, rightValue, ctx.op);

                if (leftValue instanceof Double || rightValue instanceof Double) {
                    return LYnkValidation.doubleVar((Double) result);
                }
                return LYnkValidation.number((Long) result);
            }
        }
        catch( VariableDoesNotExistException e){
            addIssue(IssueType.ERROR, ctx.op, "The variable does not exist in the current context!" );
            return SKIP_ERROR;
        }
        catch (IllegalStateException e){
            addIssue(IssueType.ERROR, ctx.op, e.getMessage() );
            return SKIP_ERROR;
        }

        return LYnkValidation.number(null);
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

        try {
            // Left and Right are either a long or a double
            if (left.isNumeric() && right.isNumeric()) {
                if (ctx.op.getType() == LYnkParser.DIVISION && (right.value().equals(0))) {
                    addIssue(IssueType.ERROR, ctx.op, "Division by 0!");
                    return SKIP_ERROR;
                }
                final Number result = NumberUtil.evalBinaryOperator((Number) left.value(), (Number) right.value(), ctx.op);
                if (left.isDouble() || right.isDouble()) {
                    return LYnkValidation.doubleVar((Double) result);
                }
                return LYnkValidation.number((Long) result);
            }

            // Left is a numeral and right is a variable
            if (left.isNumeric() && right.isIdentification()) {
                if (this.varContext.getVarType(right.asString()) instanceof Boolean || this.varContext.getVarType(right.asString()) instanceof String) {
                    addIssue(IssueType.ERROR, ctx.op, "The right variable does not have a supported type for this operation: " + this.varContext.getVarType(right.asString()));
                    return SKIP_ERROR;
                }
                final Number rightValue = this.varContext.getNumVarValue(right.asString());
                final Number result = NumberUtil.evalBinaryOperator((Number) left.value(), rightValue, ctx.op );
                if (left.isDouble() || rightValue instanceof Double) {
                    return LYnkValidation.doubleVar((Double) result);
                }
                return LYnkValidation.number((Long) result);
            }

            // Right is a numeral and left is a variable
            if (left.isIdentification() && right.isNumeric()) {
                if ( this.varContext.getVarType(left.asString()) instanceof Boolean || this.varContext.getVarType(left.asString()) instanceof String) {
                    addIssue(IssueType.ERROR, ctx.op, "The left variable does not have a supported type for this operation: " + this.varContext.getVarType(left.asString()));
                    return SKIP_ERROR;
                }
                final Number leftValue = this.varContext.getNumVarValue(left.asString());
                final Number result = NumberUtil.evalBinaryOperator(leftValue, (Number) right.value(), ctx.op );
                if (right.isDouble() || leftValue  instanceof Double) {
                    return LYnkValidation.doubleVar((Double) result);
                }
                return LYnkValidation.number((Long) result);
            }

            // Left and right are variables
            if (left.isIdentification() && right.isIdentification()) {
                if ( this.varContext.getVarType(left.asString()) instanceof Boolean || this.varContext.getVarType(left.asString()) instanceof String) {
                    addIssue(IssueType.ERROR, ctx.op, "The left variable does not have a supported type for this operation: " + this.varContext.getVarType(left.asString()));
                    return SKIP_ERROR;
                }
                if (this.varContext.getVarType(right.asString()) instanceof Boolean || this.varContext.getVarType(right.asString()) instanceof String) {
                    addIssue(IssueType.ERROR, ctx.op, "The right variable does not have a supported type for this operation: " + this.varContext.getVarType(right.asString()));
                    return SKIP_ERROR;
                }
                final Number leftValue = this.varContext.getNumVarValue(left.asString());
                final Number rightValue = this.varContext.getNumVarValue(right.asString());
                final var result = NumberUtil.evalBinaryOperator(leftValue, rightValue, ctx.op);

                if (leftValue instanceof Double || rightValue instanceof Double) {
                    return LYnkValidation.doubleVar((Double) result);
                }
                return LYnkValidation.number((Long) result);
            }
        }
        catch( VariableDoesNotExistException e){
            addIssue(IssueType.ERROR, ctx.op, "The variable does not exist in the current context!" );
            return SKIP_ERROR;
        }
        catch (IllegalStateException e){
            addIssue(IssueType.ERROR, ctx.op, e.getMessage() );
            return SKIP_ERROR;
        }

        return LYnkValidation.number(null);
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

        try {
            // Left and Right are either a long or a double
            if (left.isNumeric() && right.isNumeric()) {
                if (ctx.numOperator().op.getType() == LYnkParser.DIVISION && (right.value().equals(0))) {
                    addIssue(IssueType.ERROR, ctx.numOperator().op, "Division by 0!");
                    return SKIP_ERROR;
                }
                final Number result = NumberUtil.evalBinaryOperator((Number) left.value(), (Number) right.value(), ctx.numOperator().op);
                if (left.isDouble() || right.isDouble()) {
                    return LYnkValidation.doubleVar((Double) result);
                }
                return LYnkValidation.number((Long) result);
            }

            // Left is a numeral and right is a variable
            if (left.isNumeric() && right.isIdentification()) {
                if ( this.varContext.getVarType(right.asString()) instanceof Boolean || this.varContext.getVarType(right.asString()) instanceof String) {
                    addIssue(IssueType.ERROR, ctx.numOperator().op, "The right variable does not have a supported type for this operation: " + this.varContext.getVarType(right.asString()));
                    return SKIP_ERROR;
                }
                final Number rightValue = this.varContext.getNumVarValue(right.asString());
                final Number result = NumberUtil.evalBinaryOperator((Number) left.value(), rightValue, ctx.numOperator().op );
                if (left.isDouble() || rightValue instanceof Double) {
                    return LYnkValidation.doubleVar((Double) result);
                }
                return LYnkValidation.number((Long) result);
            }

            // Right is a numeral and left is a variable
            if (left.isIdentification() && right.isNumeric()) {
                if (this.varContext.getVarType(left.asString()) instanceof Boolean || this.varContext.getVarType(left.asString()) instanceof String) {
                    addIssue(IssueType.ERROR, ctx.numOperator().op, "The left variable does not have a supported type for this operation: " + this.varContext.getVarType(left.asString()));
                    return SKIP_ERROR;
                }
                final Number leftValue = this.varContext.getNumVarValue(left.asString());
                final Number result = NumberUtil.evalBinaryOperator(leftValue, (Number) right.value(), ctx.numOperator().op );
                if (right.isDouble() || leftValue  instanceof Double) {
                    return LYnkValidation.doubleVar((Double) result);
                }
                return LYnkValidation.number((Long) result);
            }

            // Left and right are variables
            if (left.isIdentification() && right.isIdentification()) {
                if (this.varContext.getVarType(left.asString()) instanceof Boolean || this.varContext.getVarType(left.asString()) instanceof String) {
                    addIssue(IssueType.ERROR, ctx.numOperator().op, "The left variable does not have a supported type for this operation: " + this.varContext.getVarType(left.asString()));
                    return SKIP_ERROR;
                }
                if (this.varContext.getVarType(right.asString()) instanceof Boolean || this.varContext.getVarType(right.asString()) instanceof String) {
                    addIssue(IssueType.ERROR, ctx.numOperator().op, "The right variable does not have a supported type for this operation: " + this.varContext.getVarType(right.asString()));
                    return SKIP_ERROR;
                }
                final Number leftValue = this.varContext.getNumVarValue(left.asString());
                final Number rightValue = this.varContext.getNumVarValue(right.asString());
                final var result = NumberUtil.evalBinaryOperator(leftValue, rightValue, ctx.numOperator().op);

                if (leftValue instanceof Double || rightValue instanceof Double) {
                    return LYnkValidation.doubleVar((Double) result);
                }
                return LYnkValidation.number((Long) result);
            }
        }
        catch( VariableDoesNotExistException e){
            addIssue(IssueType.ERROR, ctx.numOperator().op, "The variable does not exist in the current context!" );
            return SKIP_ERROR;
        }
        catch (IllegalStateException e){
            addIssue(IssueType.ERROR, ctx.numOperator().op, e.getMessage() );
            return SKIP_ERROR;
        }

        return LYnkValidation.number(null);
    }

    @Override
    public LYnkValidation visitNumberExpression(LYnkParser.NumberExpressionContext ctx) {
        Double value = Double.parseDouble(ctx.NUMBER().getText());
        return LYnkValidation.doubleVar(value);
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

        try {
            // Both boolean
            if (left.isBoolean() && right.isBoolean()) {
                final Boolean value = BooleanUtil.evalAndOrOperator((Boolean) left.value(), (Boolean) right.value(), ctx.op);
                return LYnkValidation.bool(value);
            }

            // left boolean right variable
            if (left.isBoolean() && right.isIdentification()) {
                if (!(this.varContext.getVarType(right.asString()) instanceof Boolean)) {
                    return SKIP_ERROR;
                }
                final Boolean rightValue = varContext.getBoolVarValue(right.asString());
                final Boolean value = BooleanUtil.evalAndOrOperator((Boolean) left.value(), rightValue, ctx.op);
                return LYnkValidation.bool(value);
            }

            // right boolean left variable
            if (right.isBoolean() && left.isIdentification()) {
                if (!(this.varContext.getVarType(left.asString()) instanceof Boolean)) {
                    return SKIP_ERROR;
                }
                final Boolean leftValue = varContext.getBoolVarValue(left.asString());
                final Boolean value = BooleanUtil.evalAndOrOperator(leftValue, (Boolean) right.value(), ctx.op);
                return LYnkValidation.bool(value);
            }

            // left variable right variable
            if (left.isIdentification() && right.isIdentification()) {
                if (!(this.varContext.getVarType(left.asString()) instanceof Boolean)) {
                    return SKIP_ERROR;
                }
                if (!(this.varContext.getVarType(right.asString()) instanceof Boolean)) {
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
        catch (IllegalStateException e){
            addIssue(IssueType.ERROR, ctx.op, e.getMessage() );
            return SKIP_ERROR;
        }
        return LYnkValidation.VOID;
    }

    @Override
    public LYnkValidation visitArithmeticComparison(LYnkParser.ArithmeticComparisonContext ctx) {
        //TODO: Verifiez si la verif de typage marche bien avec DOuble ou s'il faut modif avec Number
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
                if (!(this.varContext.getVarType(left.asString()) instanceof Double)) {
                    return SKIP_ERROR;
                }
                final Number leftValue = varContext.getNumVarValue(left.asString());
                final Boolean value = NumberUtil.evalNumberComparisonOperator(leftValue, (Number) right.value(), ctx.arithmeticOperator().op);
                return LYnkValidation.bool(value);
            }
            // left number right variable
            if (left.isNumeric() && right.isIdentification()) {
                if (!(this.varContext.getVarType(right.asString()) instanceof Double)){
                    return SKIP_ERROR;
                }
                final Number rightValue = varContext.getNumVarValue(right.asString());
                final Boolean value = NumberUtil.evalNumberComparisonOperator((Number) left.value(), rightValue, ctx.arithmeticOperator().op);
                return LYnkValidation.bool(value);
            }

            // left variable right variable
            if( left.isIdentification() && right.isIdentification() ){
                if (!(this.varContext.getVarType(left.asString()) instanceof Double)) {
                    return SKIP_ERROR;
                }
                if (!(this.varContext.getVarType(right.asString()) instanceof Double)) {
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
        try {
            if (left.isBoolean() && right.isBoolean()) {
                final Boolean value = BooleanUtil.evalAndOrOperator((Boolean) left.value(), (Boolean) right.value(), ctx.boolOperator().op);
                return LYnkValidation.bool(value);
            }
            // left boolean right variable
            if (left.isBoolean() && right.isIdentification()) {
                if (!(this.varContext.getVarType(right.asString()) instanceof Boolean)) {
                    return SKIP_ERROR;
                }
                final Boolean rightValue = varContext.getBoolVarValue(right.asString());
                final Boolean value = BooleanUtil.evalAndOrOperator((Boolean) left.value(), rightValue, ctx.boolOperator().op);
                return LYnkValidation.bool(value);
            }

            // right boolean left variable
            if (right.isBoolean() && left.isIdentification()) {
                if (!(this.varContext.getVarType(left.asString()) instanceof Boolean)) {
                    return SKIP_ERROR;
                }
                final Boolean leftValue = varContext.getBoolVarValue(left.asString());
                final Boolean value = BooleanUtil.evalAndOrOperator(leftValue, (Boolean) right.value(), ctx.boolOperator().op);
                return LYnkValidation.bool(value);
            }

            // left variable right variable
            if( left.isIdentification() && right.isIdentification() ){
                if (!(this.varContext.getVarType(left.asString()) instanceof Boolean)) {
                    return SKIP_ERROR;
                }
                if (!(this.varContext.getVarType(right.asString()) instanceof Boolean)) {
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
                if( this.varContext.hasVar(right.getText()) && !(this.varContext.getVarType(right.getText()) instanceof String)){
                    return SKIP_ERROR;
                }
                final String rightValue = this.varContext.getStrVarValue(right.getText());
                final Boolean value = StringUtil.evalLiteralComparisonOperator(left.getText() , rightValue , ctx.arithmeticOperator().op);
                return LYnkValidation.bool(value);
            }
            // right literal left identification
            if( right.getType() == LYnkParser.LITERAL && left.getType() == LYnkParser.IDENTIFICATION){
                if( this.varContext.hasVar(left.getText()) && !(this.varContext.getVarType(left.getText()) instanceof String)){
                    return SKIP_ERROR;
                }
                final String leftValue = this.varContext.getStrVarValue(left.getText());
                final Boolean value = StringUtil.evalLiteralComparisonOperator(leftValue , right.getText() , ctx.arithmeticOperator().op);
                return LYnkValidation.bool(value);
            }
            // right identification left identification
            if( right.getType() == LYnkParser.IDENTIFICATION && left.getType() == LYnkParser.IDENTIFICATION){
                if( this.varContext.hasVar(left.getText()) && !(this.varContext.getVarType(left.getText()) instanceof String)){
                    return SKIP_ERROR;
                }
                if( this.varContext.hasVar(right.getText()) && !(this.varContext.getVarType(right.getText()) instanceof String)){
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
        // TODO: Revoir la maniere dont on stocke les variables et leurs valeurs
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

    /**
     * Statement Parameter X and Y
     */

    @Override
    public LYnkValidation visitNumStatementParameterX(LYnkParser.NumStatementParameterXContext ctx){
        if(!ctx.arithmeticExpression().isEmpty()){
            final LYnkValidation arithmetic = visit(ctx.arithmeticExpression());
            if( arithmetic.isSkipError() || !arithmetic.hasValue()){
                addIssue(IssueType.ERROR, ctx.getStart(), ctx.arithmeticExpression().getText() + " does not return an accepted value.");
                return SKIP_ERROR;
            }
            return arithmetic;
        }
        if(!ctx.PERCENTAGE().getText().isEmpty()){
            return LYnkValidation.doubleVar(Double.parseDouble(ctx.PERCENTAGE().getText()));
        }
        return LYnkValidation.doubleVar(null);
    }

    @Override
    public LYnkValidation visitNumStatementParameterY(LYnkParser.NumStatementParameterYContext ctx){
        if(!ctx.arithmeticExpression().isEmpty()){
            final LYnkValidation arithmetic = visit(ctx.arithmeticExpression());
            if( arithmetic.isSkipError() || !arithmetic.hasValue()){
                addIssue(IssueType.ERROR, ctx.getStart(), ctx.arithmeticExpression().getText() + " does not return an accepted value.");
                return SKIP_ERROR;
            }
            return arithmetic;
        }
        if(!ctx.PERCENTAGE().getText().isEmpty()){
            return LYnkValidation.doubleVar(Double.parseDouble(ctx.PERCENTAGE().getText()));
        }
        return LYnkValidation.doubleVar(null);
    }

    /**
     * Statements
     */

    @Override
    public LYnkValidation visitIfStatement(LYnkParser.IfStatementContext ctx){
        final LYnkValidation boolExp = visit(ctx.booleanExpression());
        if(boolExp.isSkipError() || !boolExp.hasValue()){
            addIssue(IssueType.ERROR, ctx.getStart(), "Incorrect boolean expression in IF statement");
            return SKIP_ERROR;
        }
        final LYnkValidation block = visit(ctx.blockStatement());
        if(block.isSkipError() || !block.hasValue()){
            addIssue(IssueType.ERROR, ctx.getStart(), "An error occurred in the block below");
            return SKIP_ERROR;
        }
        return LYnkValidation.VOID;
    }

    @Override
    public LYnkValidation visitWhileStatement(LYnkParser.WhileStatementContext ctx){
        final LYnkValidation boolExp = visit(ctx.booleanExpression());
        if(boolExp.isSkipError() || !boolExp.hasValue()){
            addIssue(IssueType.ERROR, ctx.getStart(), "Incorrect boolean expression in IF statement");
            return SKIP_ERROR;
        }
        final LYnkValidation block = visit(ctx.blockStatement());
        if(block.isSkipError() || !block.hasValue()){
            addIssue(IssueType.ERROR, ctx.getStart(), "An error occurred in the block below :");
            return SKIP_ERROR;
        }
        return LYnkValidation.VOID;
    }

    @Override
    public LYnkValidation visitForStatement(LYnkParser.ForStatementContext ctx){
        if(this.varContext.hasVar(ctx.IDENTIFICATION().getText())){
            addIssue(IssueType.ERROR, ctx.to, ctx.IDENTIFICATION().getText() + " already exist in the context. This should be a new variable!");
            return SKIP_ERROR;
        }
        if( ctx.to.getType() != LYnkParser.LONG && ctx.to.getType() != LYnkParser.NUMBER ){
            addIssue(IssueType.ERROR, ctx.to, "Expected a long or an integer value (at to) but found : " + ctx.to.getText());
            return SKIP_ERROR;
        }
        if( ctx.from.getType() != LYnkParser.LONG && ctx.from.getType() != LYnkParser.NUMBER  ){
            addIssue(IssueType.ERROR, ctx.from, "Expected a long or an integer value (at from) but found : " + ctx.from.getText());
            return SKIP_ERROR;
        }
        if( ctx.step.getType() != LYnkParser.LONG && ctx.step.getType() != LYnkParser.NUMBER ){
            addIssue(IssueType.ERROR, ctx.step, "Expected a long or an integer value (at step) but found : " + ctx.step.getText());
            return SKIP_ERROR;
        }
        final LYnkValidation block = visit(ctx.blockStatement());
        if(block.isSkipError() || !block.hasValue()){
            addIssue(IssueType.ERROR, ctx.getStart(), "An error occurred in the block below :");
            return SKIP_ERROR;
        }
        return LYnkValidation.VOID;
    }

    @Override
    public LYnkValidation visitMimicStatement(LYnkParser.MimicStatementContext ctx){
        if(ctx.LONG().getText().isEmpty()){
            addIssue(IssueType.ERROR, ctx.LONG().getSymbol(), "Mimic statement is missing a cursorId");
            return SKIP_ERROR;
        }
        final LYnkValidation block = visit(ctx.blockStatement());
        return block;
    }

    @Override
    public LYnkValidation visitMirrorStatement(LYnkParser.MirrorStatementContext ctx){
        // 2 parameters (x1, y1)
        if(ctx.x2.isEmpty() && ctx.y2.isEmpty() && !ctx.x1.isEmpty() && !ctx.y1.isEmpty()){
            final LYnkValidation x1Value = visitNumStatementParameterX(ctx.x1);
            if( x1Value.isSkipError() || !x1Value.hasValue()){
                addIssue(IssueType.ERROR, ctx.x1.getStart(), "The first parameter has a null value or a wrong type : " + ctx.x1.getText());
                return SKIP_ERROR;
            }
            final LYnkValidation y1Value = visitNumStatementParameterY(ctx.y1);
            if( y1Value.isSkipError() || !y1Value.hasValue()){
                addIssue(IssueType.ERROR, ctx.y1.getStart(), "The second parameter has a null value or a wrong type : " + ctx.y1.getText());
                return SKIP_ERROR;
            }
            return VOID;
        }
        // 1 or 3 parameters
        if( !ctx.x2.isEmpty() && ctx.y2.isEmpty() || ctx.x2.isEmpty() && !ctx.y2.isEmpty() || ctx.x1.isEmpty() && !ctx.y1.isEmpty() || !ctx.x1.isEmpty() && ctx.y1.isEmpty()){
            addIssue(IssueType.ERROR, ctx.getStart(), "Mirror statement requires 2 or 4 arguments but found 3!");
            return SKIP_ERROR;
        }
        // 4 parameters (x1, y1, x2, y2)
        if(!ctx.x2.isEmpty() && !ctx.y2.isEmpty() && !ctx.x1.isEmpty() && !ctx.y1.isEmpty()){
            final LYnkValidation x1Value = visitNumStatementParameterX(ctx.x1);
            if( x1Value.isSkipError() || !x1Value.hasValue()){
                addIssue(IssueType.ERROR, ctx.x1.getStart(), "The first parameter has a null value or a wrong type : " + ctx.x1.getText());
                return SKIP_ERROR;
            }
            final LYnkValidation y1Value = visitNumStatementParameterY(ctx.y1);
            if( y1Value.isSkipError() || !y1Value.hasValue()){
                addIssue(IssueType.ERROR, ctx.y1.getStart(), "The second parameter has a null value or a wrong type : " + ctx.y1.getText());
                return SKIP_ERROR;
            }
            final LYnkValidation x2Value = visitNumStatementParameterX(ctx.x2);
            if( x2Value.isSkipError() || !x2Value.hasValue()){
                addIssue(IssueType.ERROR, ctx.x2.getStart(), "The third parameter has a null value or a wrong type : " + ctx.x2.getText());
                return SKIP_ERROR;
            }
            final LYnkValidation y2Value = visitNumStatementParameterY(ctx.y2);
            if( y2Value.isSkipError() || !y2Value.hasValue()){
                addIssue(IssueType.ERROR, ctx.y2.getStart(), "The fourth parameter has a null value or a wrong type : " + ctx.y2.getText());
                return SKIP_ERROR;
            }
            return VOID;
        }
        // No parameters
        addIssue(IssueType.ERROR, ctx.getStart(), "No parameters were found in MIRROR statement");
        return SKIP_ERROR;
    }

    @Override
    public LYnkValidation visitForwardStatement(LYnkParser.ForwardStatementContext ctx){
        if(!ctx.numStatementParameterX().isEmpty()){
            final LYnkValidation fwdValue = visitNumStatementParameterX(ctx.numStatementParameterX());
            if( fwdValue.isSkipError() || !fwdValue.hasValue()){
                addIssue(IssueType.ERROR, ctx.getStart(), "The value passed in FWD is null or the wrong type: " + ctx.numStatementParameterX().getText());
                return SKIP_ERROR;
            }
            return VOID;
        }
        // no params
        addIssue(IssueType.ERROR, ctx.getStart(), "FWD statement was not given a parameter");
        return SKIP_ERROR;
    }

    @Override
    public LYnkValidation visitBackwardStatement(LYnkParser.BackwardStatementContext ctx){
        if(!ctx.numStatementParameterX().isEmpty()){
            final LYnkValidation bwdValue = visitNumStatementParameterX(ctx.numStatementParameterX());
            if( bwdValue.isSkipError() || !bwdValue.hasValue()){
                addIssue(IssueType.ERROR, ctx.getStart(), "The value passed in FWD is null or the wrong type: " + ctx.numStatementParameterX().getText());
                return SKIP_ERROR;
            }
            return VOID;
        }
        // no params
        addIssue(IssueType.ERROR, ctx.getStart(), "BWD statement was not given a parameter");
        return SKIP_ERROR;
    }

    @Override
    public LYnkValidation visitMoveStatement(LYnkParser.MoveStatementContext ctx){
        // no params
        if(ctx.x.isEmpty() && ctx.y.isEmpty()){
            addIssue(IssueType.ERROR, ctx.getStart(), "MOV statement was not given a parameter");
            return SKIP_ERROR;
        }

        // x or y empty
        if(!ctx.x.isEmpty() && ctx.y.isEmpty() || ctx.x.isEmpty() && !ctx.y.isEmpty()){
            addIssue(IssueType.ERROR, ctx.getStart(), "MOV statement was given only 1 parameter");
            return SKIP_ERROR;
        }

        // 2 parameters
        final LYnkValidation xValue = visitNumStatementParameterX(ctx.x);
        if( xValue.isSkipError() || !xValue.hasValue()){
            addIssue(IssueType.ERROR, ctx.x.getStart(), "The x parameter has a null value or a wrong type : " + ctx.x.getText());
            return SKIP_ERROR;
        }
        final LYnkValidation yValue = visitNumStatementParameterY(ctx.y);
        if( yValue.isSkipError() || !yValue.hasValue()){
            addIssue(IssueType.ERROR, ctx.y.getStart(), "The y parameter has a null value or a wrong type : " + ctx.y.getText());
            return SKIP_ERROR;
        }
        return VOID;
    }

    @Override
    public LYnkValidation visitPositionStatement(LYnkParser.PositionStatementContext ctx){
        // no params
        if(ctx.x.isEmpty() && ctx.y.isEmpty()){
            addIssue(IssueType.ERROR, ctx.getStart(), "MOV statement was not given a parameter");
            return SKIP_ERROR;
        }

        // x or y empty
        if(!ctx.x.isEmpty() && ctx.y.isEmpty() || ctx.x.isEmpty() && !ctx.y.isEmpty()){
            addIssue(IssueType.ERROR, ctx.getStart(), "MOV statement was given only 1 parameter");
            return SKIP_ERROR;
        }

        // 2 parameters
        final LYnkValidation xValue = visitNumStatementParameterX(ctx.x);
        if( xValue.isSkipError() || !xValue.hasValue()){
            addIssue(IssueType.ERROR, ctx.x.getStart(), "The x parameter has a null value or a wrong type : " + ctx.x.getText());
            return SKIP_ERROR;
        }
        final LYnkValidation yValue = visitNumStatementParameterY(ctx.y);
        if( yValue.isSkipError() || !yValue.hasValue()){
            addIssue(IssueType.ERROR, ctx.y.getStart(), "The x parameter has a null value or a wrong type : " + ctx.y.getText());
            return SKIP_ERROR;
        }
        return VOID;
    }

    @Override
    public LYnkValidation visitHideStatement(LYnkParser.HideStatementContext ctx){
        return VOID;
    }

    @Override
    public LYnkValidation visitShowStatement(LYnkParser.ShowStatementContext ctx){
        return VOID;
    }

    @Override
    public LYnkValidation visitColorStatement(LYnkParser.ColorStatementContext ctx) {
        // no params
        if( ctx.HEXCODE().getText().isEmpty() || ctx.arithmeticExpression().isEmpty()){
            addIssue(IssueType.ERROR, ctx.getStart(), "COLOR statement was not given parameters");
            return SKIP_ERROR;
        }
        // 1 or 2 params when expected 3
        if( (ctx.arithmeticExpression(0).isEmpty() && ctx.arithmeticExpression(1).isEmpty() && !ctx.arithmeticExpression(2).isEmpty()) || (ctx.arithmeticExpression(0).isEmpty() && !ctx.arithmeticExpression(1).isEmpty() && !ctx.arithmeticExpression(2).isEmpty())){
            addIssue(IssueType.ERROR, ctx.getStart(), "COLOR statement found 1 or 2 parameter for arithmeticExpressions but expected 3: " + ctx.arithmeticExpression().toString());
            return SKIP_ERROR;
        }
        return VOID;
    }

    @Override
    public LYnkValidation visitCursorStatement(LYnkParser.CursorStatementContext ctx){
        // no params
        if( ctx.LONG().getText().isEmpty() ){
            addIssue(IssueType.ERROR, ctx.getStart(), "CURSOR statement was not given a parameter");
            return SKIP_ERROR;
        }
        if( this.cursorContext.cursorExist(Long.parseLong(ctx.LONG().getText())) ){
            addIssue(IssueType.ERROR, ctx.LONG().getSymbol(), "The cursor n°" + ctx.LONG().getText() + " is already in use");
            return SKIP_ERROR;
        }
        return VOID;
    }

    @Override
    public LYnkValidation visitSelectStatement(LYnkParser.SelectStatementContext ctx){
        // no params
        if( ctx.LONG().getText().isEmpty() ){
            addIssue(IssueType.ERROR, ctx.getStart(), "SELECT statement was not given a parameter");
            return SKIP_ERROR;
        }
        if( !this.cursorContext.cursorExist(Long.parseLong(ctx.LONG().getText())) ){
            addIssue(IssueType.ERROR, ctx.LONG().getSymbol(), "The cursor n°" + ctx.LONG().getText() + " does not exist in the current context!");
            return SKIP_ERROR;
        }
        return VOID;
    }

    @Override
    public LYnkValidation visitRemoveStatement(LYnkParser.RemoveStatementContext ctx){
        // no params
        if( ctx.LONG().getText().isEmpty() ){
            addIssue(IssueType.ERROR, ctx.getStart(), "REMOVE statement was not given a parameter");
            return SKIP_ERROR;
        }
        if( !this.cursorContext.cursorExist(Long.parseLong(ctx.LONG().getText())) ){
            addIssue(IssueType.ERROR, ctx.LONG().getSymbol(), "The cursor n°" + ctx.LONG().getText() + " does not exist in the current context!");
            return SKIP_ERROR;
        }
        return VOID;
    }

    @Override
    public LYnkValidation visitPressStatement(LYnkParser.PressStatementContext ctx){
        if( ctx.PERCENTAGE().getText().isEmpty() || ctx.arithmeticExpression().isEmpty()){
            addIssue(IssueType.ERROR, ctx.getStart(), "PRESS statement was not given a parameter");
            return SKIP_ERROR;
        }
        final LYnkValidation arithmetic = visit(ctx.arithmeticExpression());
        if( arithmetic.isSkipError() || !arithmetic.hasValue()){
            addIssue(IssueType.ERROR, ctx.getStart(), "The arithmetic expression passed in argument contains an error");
            return SKIP_ERROR;
        }
        return VOID;
    }

    @Override
    public LYnkValidation visitThickStatement(LYnkParser.ThickStatementContext ctx){
        if( ctx.arithmeticExpression().isEmpty() ){
            addIssue(IssueType.ERROR, ctx.getStart(), "THICK statement was not given a parameter");
            return SKIP_ERROR;
        }
        final LYnkValidation arithmetic = visit(ctx.arithmeticExpression());
        if( arithmetic.isSkipError() || !arithmetic.hasValue()){
            addIssue(IssueType.ERROR, ctx.getStart(), "The arithmetic expression passed in argument contains an error");
            return SKIP_ERROR;
        }
        return VOID;
    }

    @Override
    public LYnkValidation visitLookAtStatement(LYnkParser.LookAtStatementContext ctx){
        // no params
        if( ctx.x.isEmpty() && ctx.y.isEmpty() ){
            addIssue(IssueType.ERROR, ctx.getStart(), "LOOKAT statement was not given a parameter");
            return SKIP_ERROR;
        }
        // 1 param
        if( !ctx.x.isEmpty() && ctx.y.isEmpty() || ctx.x.isEmpty() && !ctx.y.isEmpty() ){
            addIssue(IssueType.ERROR, ctx.getStart(), "LOOKAT statement was given only 1 parameter");
            return SKIP_ERROR;
        }
        // 2 parameters
        final LYnkValidation xValue = visitNumStatementParameterX(ctx.x);
        if( xValue.isSkipError() || !xValue.hasValue()){
            addIssue(IssueType.ERROR, ctx.x.getStart(), "The x parameter has a null value or a wrong type : " + ctx.x.getText());
            return SKIP_ERROR;
        }
        final LYnkValidation yValue = visitNumStatementParameterY(ctx.y);
        if( yValue.isSkipError() || !yValue.hasValue()){
            addIssue(IssueType.ERROR, ctx.y.getStart(), "The y parameter has a null value or a wrong type : " + ctx.y.getText());
            return SKIP_ERROR;
        }
        return VOID;
    }

    @Override
    public LYnkValidation visitRotationStatement(LYnkParser.RotationStatementContext ctx){
        if( ctx.arithmeticExpression().isEmpty() ){
            addIssue(IssueType.ERROR, ctx.getStart(), "TURN statement was not given a parameter");
            return SKIP_ERROR;
        }
        final LYnkValidation arithmetic = visit(ctx.arithmeticExpression());
        if( arithmetic.isSkipError() || !arithmetic.hasValue()){
            addIssue(IssueType.ERROR, ctx.getStart(), "The arithmetic expression passed in argument contains an error");
            return SKIP_ERROR;
        }
        return VOID;
    }

    /**
     * Variable declarations
     */

    @Override
    public LYnkValidation visitStringDeclaration(LYnkParser.StringDeclarationContext ctx){
        String variable = ctx.IDENTIFICATION().getText();
        // no param
        if(variable.isEmpty()){
            addIssue(IssueType.ERROR, ctx.getStart(), "A variable name is needed in STR statement");
            return SKIP_ERROR;
        }
        // only var name
        if( !variable.isEmpty() && this.varContext.hasVar(variable) && ctx.LITERAL().getText().isEmpty()){
            addIssue(IssueType.ERROR, ctx.IDENTIFICATION().getSymbol(), variable + " already exist in this context!");
            return SKIP_ERROR;
        }
        if( !variable.isEmpty() && !this.varContext.hasVar(variable) && ctx.LITERAL().getText().isEmpty()){
            return VOID;
        }
        // var name and its value
        if( !variable.isEmpty() && this.varContext.hasVar(variable) && !ctx.LITERAL().getText().isEmpty()){
            return VOID;
        }
        if( !variable.isEmpty() && !this.varContext.hasVar(variable) && !ctx.LITERAL().getText().isEmpty()){
            return VOID;
        }
        return SKIP_ERROR;
    }

    @Override
    public LYnkValidation visitBoolDeclaration(LYnkParser.BoolDeclarationContext ctx){
        String variable = ctx.IDENTIFICATION().getText();
        // no param
        if(variable.isEmpty()){
            addIssue(IssueType.ERROR, ctx.getStart(), "A variable name is needed in BOOL statement");
            return SKIP_ERROR;
        }
        // only var name
        if( !variable.isEmpty() && this.varContext.hasVar(variable) && ctx.booleanExpression().isEmpty()){
            addIssue(IssueType.ERROR, ctx.IDENTIFICATION().getSymbol(), variable + " already exist in this context!");
            return SKIP_ERROR;
        }
        if( !variable.isEmpty() && !this.varContext.hasVar(variable) && ctx.booleanExpression().isEmpty()){
            return VOID;
        }
        // var name and its value
        if( !variable.isEmpty() && this.varContext.hasVar(variable) && !ctx.booleanExpression().isEmpty()){
            final LYnkValidation bool = visit(ctx.booleanExpression());
            if( bool.isSkipError() || !bool.hasValue()){
                addIssue(IssueType.ERROR, ctx.getStart(), "The bool expression has a null value or has an error");
                return SKIP_ERROR;
            }
            return VOID;
        }
        if( !variable.isEmpty() && !this.varContext.hasVar(variable) && !ctx.booleanExpression().isEmpty()){
            final LYnkValidation bool = visit(ctx.booleanExpression());
            if( bool.isSkipError() || !bool.hasValue()){
                addIssue(IssueType.ERROR, ctx.getStart(), "The bool expression has a null value or has an error");
                return SKIP_ERROR;
            }
            return VOID;
        }
        return SKIP_ERROR;
    }

    @Override
    public LYnkValidation visitNumberDeclaration(LYnkParser.NumberDeclarationContext ctx){
        String variable = ctx.IDENTIFICATION().getText();
        // no param
        if(variable.isEmpty()){
            addIssue(IssueType.ERROR, ctx.getStart(), "A variable name is needed in NUM statement");
            return SKIP_ERROR;
        }
        // only var name
        if( !variable.isEmpty() && this.varContext.hasVar(variable) && ctx.arithmeticExpression().isEmpty()){
            addIssue(IssueType.ERROR, ctx.IDENTIFICATION().getSymbol(), variable + " already exist in this context!");
            return SKIP_ERROR;
        }
        if( !variable.isEmpty() && !this.varContext.hasVar(variable) && ctx.arithmeticExpression().isEmpty()){
            return VOID;
        }
        // var name and its value
        if( !variable.isEmpty() && this.varContext.hasVar(variable) && !ctx.arithmeticExpression().isEmpty()){
            final LYnkValidation number = visit(ctx.arithmeticExpression());
            if( number.isSkipError() || !number.hasValue()){
                addIssue(IssueType.ERROR, ctx.getStart(), "The arithmetic expression has a null value or has an error");
                return SKIP_ERROR;
            }
            return VOID;
        }
        if( !variable.isEmpty() && !this.varContext.hasVar(variable) && !ctx.arithmeticExpression().isEmpty()){
            final LYnkValidation number = visit(ctx.arithmeticExpression());
            if( number.isSkipError() || !number.hasValue()){
                addIssue(IssueType.ERROR, ctx.getStart(), "The arithmetic expression has a null value or has an error");
                return SKIP_ERROR;
            }
            return VOID;
        }
        return SKIP_ERROR;
    }

    @Override
    public LYnkValidation visitDeleteDeclaration(LYnkParser.DeleteDeclarationContext ctx){
        String variable = ctx.IDENTIFICATION().getText();
        // no param
        if(variable.isEmpty()){
            addIssue(IssueType.ERROR, ctx.getStart(), "A variable name is needed in DEL statement");
            return SKIP_ERROR;
        }
        // Delete existing var
        if( !variable.isEmpty() && this.varContext.hasVar(variable)){
            return VOID;
        }
        // Delete non-existant var
        if( !variable.isEmpty() && !this.varContext.hasVar(variable)){
            addIssue(IssueType.ERROR, ctx.IDENTIFICATION().getSymbol(), ctx.IDENTIFICATION().getText() + " does not exist in the current context");
            return SKIP_ERROR;
        }
        return SKIP_ERROR;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException re){
        if(!REPORT_SYNTAX_ERRORS){
            return;
        }
        this.issues.add(new LYnkIssue(IssueType.ERROR, msg, charPositionInLine, line, "(syntax error)"));
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
