package chromatynk.chromatynk_g6.diagnostic;

import chromatynk.chromatynk_g6.interpreter.CursorManager;
import chromatynk.chromatynk_g6.LYnk.LYnkBaseVisitor;
import chromatynk.chromatynk_g6.LYnk.LYnkLexer;
import chromatynk.chromatynk_g6.LYnk.LYnkParser;
import chromatynk.chromatynk_g6.interpreter.Variable;
import chromatynk.chromatynk_g6.exceptions.cursorExceptions.CursorAlreadyExistingException;
import chromatynk.chromatynk_g6.exceptions.cursorExceptions.CursorException;
import chromatynk.chromatynk_g6.exceptions.cursorExceptions.MissingCursorException;
import chromatynk.chromatynk_g6.exceptions.variableExceptions.VariableDoesNotExistException;
import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.ArithmeticOperator;
import chromatynk.chromatynk_g6.parameters.BoolOperator;
import chromatynk.chromatynk_g6.parameters.NumberOperator;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticComparison;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticExpression;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticVariable;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.OperationExpression;
import chromatynk.chromatynk_g6.parameters.booleanExp.*;
import chromatynk.chromatynk_g6.parameters.literalExp.LiteralComparison;
import chromatynk.chromatynk_g6.parameters.literalExp.LiteralExpression;
import chromatynk.chromatynk_g6.parameters.literalExp.LiteralVariable;
import chromatynk.chromatynk_g6.statements.*;
import chromatynk.chromatynk_g6.utils.BooleanUtil;
import chromatynk.chromatynk_g6.utils.NumberUtil;
import chromatynk.chromatynk_g6.utils.StringUtil;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;
import java.util.*;
import java.nio.file.Files;

import static chromatynk.chromatynk_g6.diagnostic.LYnkValidation.SKIP_ERROR;
import static chromatynk.chromatynk_g6.diagnostic.LYnkValidation.VOID;

public class LYnkConsole extends LYnkBaseVisitor<LYnkValidation> implements ANTLRErrorListener {

    /**
     * A set of issues linked to the given code
     */
    private Set<LYnkIssue> issues;

    private LYnkVariableImpl varContext;
    private CursorManager cursorContext;

    private int width;
    private int height;
    private List<String> validStatements;

    //When the value is false, the syntaxError method returns without displaying errors.
    private static boolean REPORT_SYNTAX_ERRORS = true;

    public static void main(String[] args){
        // TODO: Verifiez les comp litérales
        try {
            File lynktest = new File("/home/cytech/_Cours/S2/Java/ChromatYnk_G6/src/main/java/lynkTest.lynk");
            String input = new String(Files.readAllBytes(lynktest.toPath()));
            LYnkConsole console = new LYnkConsole(input);

            console.verifyInput(input);
            System.out.println(console.getIssues());
            System.out.println(console.getValidStatements());
            System.out.println(console.getVarContext());
        }
        catch( IOException e ){
            System.out.println("T'es con: " + e.getMessage());
        }
    }

    public LYnkConsole(String input){
        this.issues = new LinkedHashSet<>(0);
        this.varContext = new LYnkVariableImpl();
        this.cursorContext = new CursorManager();
        this.validStatements = new ArrayList<String>();
        //verifyInput(input);
    }

    private void addIssue(final IssueType issueType, final Token token, String message) {
        if(!REPORT_SYNTAX_ERRORS){
            return;
        }
        final int line = token.getLine();
        final int offset = token.getCharPositionInLine() + 1;
        this.issues.add(new LYnkIssue(issueType, message, line, offset, ""));
    }

    public List<LYnkIssue> getIssues(){
        return issues.stream().toList();
    }

    public List<String> getValidStatements(){
        return this.validStatements;
    }

    public Map<String, Variable> getVarContext(){ return this.varContext.getVariableMap(); }

    public List<String> verifyInput(String input){
        final LYnkLexer lexer = new LYnkLexer(CharStreams.fromString(input));
        final LYnkParser parser = new LYnkParser(new CommonTokenStream(lexer));

        visitProgram(parser.program());

        return getValidStatements();
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
                if (REPORT_SYNTAX_ERRORS && (tempValidation.isSkipError() || !tempValidation.hasValue()) ){
                    return SKIP_ERROR;
                }
                final LYnkValidation statementValidation = visit(statement);
                if (!statementValidation.isSkipError()) {
                    System.out.println(statementValidation.asString());
                    this.validStatements.add(statementValidation.asString()); // no voc du coup?
                }
                tempValidation = statementValidation;

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
    
    @Override
    public LYnkValidation visitBlockStatement(LYnkParser.BlockStatementContext ctx){
        BlockStatement block = new BlockStatement(varContext);
        LYnkValidation tempValidation = VOID;
        for (LYnkParser.StatementContext statement : ctx.statement()) {
            if (tempValidation.isSkipError() || !tempValidation.hasValue()) {
                return SKIP_ERROR;
            }
            final LYnkValidation statementValidation = visit(statement);
            if( statementValidation.isStatement() && statementValidation.hasValue() ){
                final Statement blockStatement = (Statement) statementValidation.value();
                block.addStatement(blockStatement);
            }
            //TODO: Creer liste de valid block statement qu'on renvoie dans le return
            tempValidation = statementValidation;
        }
        return LYnkValidation.statement(block);
    }

    // Arithmetic Expression Visit
    /**
     * Arithmetic Expressions
     */
    @Override
    public LYnkValidation visitParenthesisExpression(LYnkParser.ParenthesisExpressionContext ctx){
        final LYnkValidation exp = visit(ctx.arithmeticExpression());
        if( exp.isSkipError() || !exp.hasValue() ){
            addIssue(IssueType.ERROR, ctx.getStart(), "The parenthesis arithmetic expression is empty or null");
            return SKIP_ERROR;
        }
        return exp;
    }

    @Override
    public LYnkValidation visitMulDivExpression(LYnkParser.MulDivExpressionContext ctx){
        // Either empty value or error already detected deeper in the tree
        final LYnkValidation left = visit(ctx.left);
        if(left.isSkipError() || !left.hasValue()){
            addIssue(IssueType.ERROR, ctx.getStart(), "An expression in a division/multiplication is null");
            return SKIP_ERROR;
        }
        final LYnkValidation right = visit(ctx.right);
        if(right.isSkipError() || !right.hasValue()){
            addIssue(IssueType.ERROR, ctx.getStart(), "An expression in a division/multiplication is null");
            return SKIP_ERROR;
        }

        try {
            if( NumberOperator.getOp(ctx.op.getType()).toString().equals("VOID")){
                addIssue(IssueType.ERROR, ctx.op.getTokenSource().nextToken(), "Wrong operator: " + ctx.op.getText());
                return SKIP_ERROR;
            }
            // Left and Right are either a long or a double
            if (left.isNumExp() && right.isNumExp()) {
                final OperationExpression multDivExp = new OperationExpression((ArithmeticExpression) left.value(), NumberOperator.getOp(ctx.op.getType()), (ArithmeticExpression) right.value());
                if (ctx.op.getType() == LYnkParser.DIVISION && ((ArithmeticExpression) right.value()).evaluate() == 0d ) {
                    addIssue(IssueType.ERROR, ctx.op, "Division by 0 ou 0.0");
                    return SKIP_ERROR;
                }
                return LYnkValidation.numExp(multDivExp);
            }

            // Left is a numeral and right is a variable
            if (left.isNumExp() && right.isIdentification()) {
                if (this.varContext.getVarType(right.asString()) instanceof Boolean || this.varContext.getVarType(right.asString()) instanceof String) {
                    addIssue(IssueType.ERROR, ctx.op, "The right variable does not have a supported type for this operation: " + this.varContext.getVarType(right.asString()));
                    return SKIP_ERROR;
                }
                if (ctx.op.getType() == LYnkParser.DIVISION && (this.varContext.getNumVarValue(right.asString()).equals(Double.valueOf("0")))) {
                    addIssue(IssueType.ERROR, ctx.op, "Division by " + right.asString() + " whose value is 0 or 0.0");
                    return SKIP_ERROR;
                }
                final Number rightValue = this.varContext.getNumVarValue(right.asString());
                final OperationExpression multDivExp = new OperationExpression((ArithmeticExpression) left.value(), NumberOperator.getOp(ctx.op.getType()), new ArithmeticExpression(rightValue.doubleValue()));
                return LYnkValidation.numExp(multDivExp);
            }

            // Right is a numeral and left is a variable
            if (left.isIdentification() && right.isNumExp()) {
                if (this.varContext.getVarType(left.asString()) instanceof Boolean || this.varContext.getVarType(left.asString()) instanceof String) {
                    addIssue(IssueType.ERROR, ctx.op, "The left variable does not have a supported type for this operation: " + this.varContext.getVarType(left.asString()));
                    return SKIP_ERROR;
                }
                final Number leftValue = this.varContext.getNumVarValue(right.asString());
                final OperationExpression multDivExp = new OperationExpression( new ArithmeticExpression(leftValue.doubleValue()), NumberOperator.getOp(ctx.op.getType()), (ArithmeticExpression) right.value());
                return LYnkValidation.numExp(multDivExp);
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
                if (ctx.op.getType() == LYnkParser.DIVISION && (this.varContext.getNumVarValue(right.asString()).equals(Double.valueOf("0")))) {
                    addIssue(IssueType.ERROR, ctx.op, "Division by " + right.asString() + " whose value is 0 or 0.0");
                    return SKIP_ERROR;
                }
                final Number leftValue = this.varContext.getNumVarValue(left.asString());
                final Number rightValue = this.varContext.getNumVarValue(right.asString());
                final OperationExpression multDivExp = new OperationExpression( new ArithmeticExpression(leftValue.doubleValue()), NumberOperator.getOp(ctx.op.getType()), new ArithmeticExpression(rightValue.doubleValue()));
                return LYnkValidation.numExp(multDivExp);
            }
        }
        catch( VariableDoesNotExistException e){
            addIssue(IssueType.ERROR, ctx.op, "The variable does not exist in the current context!" );
            return SKIP_ERROR;
        }

        return LYnkValidation.numExp(null);
    }

    @Override
    public LYnkValidation visitPlusMinusExpression(LYnkParser.PlusMinusExpressionContext ctx){
        // Either empty value or error already detected deeper in the tree
        final LYnkValidation left = visit(ctx.left);
        if(left.isSkipError() || !left.hasValue()){
            addIssue(IssueType.ERROR, ctx.getStart(), "An expression in a plus/minus is null");
            return SKIP_ERROR;
        }
        final LYnkValidation right = visit(ctx.right);
        if(right.isSkipError() || !right.hasValue()){
            addIssue(IssueType.ERROR, ctx.getStart(), "An expression in a plus/minus is null");
            return SKIP_ERROR;
        }

        try {
            if( NumberOperator.getOp(ctx.op.getType()).toString().equals("VOID")){
                addIssue(IssueType.ERROR, ctx.op.getTokenSource().nextToken(), "Wrong operator: " + ctx.op.getText());
                return SKIP_ERROR;
            }
            // Left and Right are either a long or a double
            if (left.isNumExp() && right.isNumExp()) {
                final OperationExpression plusMinusExp = new OperationExpression((ArithmeticExpression) left.value(), NumberOperator.getOp(ctx.op.getType()), (ArithmeticExpression) right.value());
                if (ctx.op.getType() == LYnkParser.DIVISION && ((ArithmeticExpression) right.value()).evaluate() == 0d ) {
                    addIssue(IssueType.ERROR, ctx.op, "Division by 0 ou 0.0");
                    return SKIP_ERROR;
                }
                return LYnkValidation.numExp(plusMinusExp);
            }

            // Left is a numeral and right is a variable
            if (left.isNumExp() && right.isIdentification()) {
                if (this.varContext.getVarType(right.asString()) instanceof Boolean || this.varContext.getVarType(right.asString()) instanceof String) {
                    addIssue(IssueType.ERROR, ctx.op, "The right variable does not have a supported type for this operation: " + this.varContext.getVarType(right.asString()));
                    return SKIP_ERROR;
                }
                if (ctx.op.getType() == LYnkParser.DIVISION && (this.varContext.getNumVarValue(right.asString()).equals(Double.valueOf("0")))) {
                    addIssue(IssueType.ERROR, ctx.op, "Division by " + right.asString() + " whose value is 0 or 0.0");
                    return SKIP_ERROR;
                }
                final Number rightValue = this.varContext.getNumVarValue(right.asString());
                final OperationExpression plusMinusExp = new OperationExpression((ArithmeticExpression) left.value(), NumberOperator.getOp(ctx.op.getType()), new ArithmeticExpression(rightValue.doubleValue()));
                return LYnkValidation.numExp(plusMinusExp);
            }

            // Right is a numeral and left is a variable
            if (left.isIdentification() && right.isNumExp()) {
                if (this.varContext.getVarType(left.asString()) instanceof Boolean || this.varContext.getVarType(left.asString()) instanceof String) {
                    addIssue(IssueType.ERROR, ctx.op, "The left variable does not have a supported type for this operation: " + this.varContext.getVarType(left.asString()));
                    return SKIP_ERROR;
                }
                final Number leftValue = this.varContext.getNumVarValue(right.asString());
                final OperationExpression plusMinusExp = new OperationExpression( new ArithmeticExpression(leftValue.doubleValue()), NumberOperator.getOp(ctx.op.getType()), (ArithmeticExpression) right.value());
                return LYnkValidation.numExp(plusMinusExp);
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
                if (ctx.op.getType() == LYnkParser.DIVISION && (this.varContext.getNumVarValue(right.asString()).equals(Double.valueOf("0")))) {
                    addIssue(IssueType.ERROR, ctx.op, "Division by " + right.asString() + " whose value is 0 or 0.0");
                    return SKIP_ERROR;
                }
                final Number leftValue = this.varContext.getNumVarValue(left.asString());
                final Number rightValue = this.varContext.getNumVarValue(right.asString());
                final OperationExpression plusMinusExp = new OperationExpression( new ArithmeticExpression(leftValue.doubleValue()), NumberOperator.getOp(ctx.op.getType()), new ArithmeticExpression(rightValue.doubleValue()));
                return LYnkValidation.numExp(plusMinusExp);
            }
        }
        catch( VariableDoesNotExistException e){
            addIssue(IssueType.ERROR, ctx.op, "The variable does not exist in the current context!" );
            return SKIP_ERROR;
        }

        return LYnkValidation.numExp(null);
    }

    @Override
    public LYnkValidation visitCompExpression(LYnkParser.CompExpressionContext ctx){
        // Either empty value or error already detected deeper in the tree
        final LYnkValidation left = visit(ctx.left);
        if(left.isSkipError() || !left.hasValue()){
            addIssue(IssueType.ERROR, ctx.getStart(), "An arithmetic expression is null");
            return SKIP_ERROR;
        }
        final LYnkValidation right = visit(ctx.right);
        if(right.isSkipError() || !right.hasValue()){
            addIssue(IssueType.ERROR, ctx.getStart(), "An arithmetic expression is null");
            return SKIP_ERROR;
        }

        try {
            if( NumberOperator.getOp(ctx.numOperator().op.getType()).toString().equals("VOID")){
                addIssue(IssueType.ERROR, ctx.numOperator().op.getTokenSource().nextToken(), "Wrong operator: " + ctx.numOperator().op.getText());
                return SKIP_ERROR;
            }
            // Left and Right are either a long or a double
            if (left.isNumExp() && right.isNumExp()) {
                final OperationExpression Exp = new OperationExpression((ArithmeticExpression) left.value(), NumberOperator.getOp(ctx.numOperator().op.getType()), (ArithmeticExpression) right.value());
                if (ctx.numOperator().op.getType() == LYnkParser.DIVISION && ((ArithmeticExpression) right.value()).evaluate() == 0d ) {
                    addIssue(IssueType.ERROR, ctx.numOperator().op, "Division by 0 ou 0.0");
                    return SKIP_ERROR;
                }
                return LYnkValidation.numExp(Exp);
            }

            // Left is a numeral and right is a variable
            if (left.isNumExp() && right.isIdentification()) {
                if (this.varContext.getVarType(right.asString()) instanceof Boolean || this.varContext.getVarType(right.asString()) instanceof String) {
                    addIssue(IssueType.ERROR, ctx.numOperator().op, "The right variable does not have a supported type for this operation: " + this.varContext.getVarType(right.asString()));
                    return SKIP_ERROR;
                }
                if (ctx.numOperator().op.getType() == LYnkParser.DIVISION && (this.varContext.getNumVarValue(right.asString()).equals(Double.valueOf("0")))) {
                    addIssue(IssueType.ERROR, ctx.numOperator().op, "Division by " + right.asString() + " whose value is 0 or 0.0");
                    return SKIP_ERROR;
                }
                final Number rightValue = this.varContext.getNumVarValue(right.asString());
                final OperationExpression Exp = new OperationExpression((ArithmeticExpression) left.value(), NumberOperator.getOp(ctx.numOperator().op.getType()), new ArithmeticExpression(rightValue.doubleValue()));
                return LYnkValidation.numExp(Exp);
            }

            // Right is a numeral and left is a variable
            if (left.isIdentification() && right.isNumExp()) {
                if (this.varContext.getVarType(left.asString()) instanceof Boolean || this.varContext.getVarType(left.asString()) instanceof String) {
                    addIssue(IssueType.ERROR, ctx.numOperator().op, "The left variable does not have a supported type for this operation: " + this.varContext.getVarType(left.asString()));
                    return SKIP_ERROR;
                }
                final Number leftValue = this.varContext.getNumVarValue(right.asString());
                final OperationExpression Exp = new OperationExpression( new ArithmeticExpression(leftValue.doubleValue()), NumberOperator.getOp(ctx.numOperator().op.getType()), (ArithmeticExpression) right.value());
                return LYnkValidation.numExp(Exp);
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
                if (ctx.numOperator().op.getType() == LYnkParser.DIVISION && (this.varContext.getNumVarValue(right.asString()).equals(Double.valueOf("0")))) {
                    addIssue(IssueType.ERROR, ctx.numOperator().op, "Division by " + right.asString() + " whose value is 0 or 0.0");
                    return SKIP_ERROR;
                }
                final Number leftValue = this.varContext.getNumVarValue(left.asString());
                final Number rightValue = this.varContext.getNumVarValue(right.asString());
                final OperationExpression Exp = new OperationExpression( new ArithmeticExpression(leftValue.doubleValue()), NumberOperator.getOp(ctx.numOperator().op.getType()), new ArithmeticExpression(rightValue.doubleValue()));
                return LYnkValidation.numExp(Exp);
            }
        }
        catch( VariableDoesNotExistException e){
            addIssue(IssueType.ERROR, ctx.numOperator().op, "The variable does not exist in the current context!" );
            return SKIP_ERROR;
        }

        return LYnkValidation.numExp(null);
    }

    @Override
    public LYnkValidation visitNumberExpression(LYnkParser.NumberExpressionContext ctx) {
        final Double value = Double.parseDouble(ctx.NUMBER().getText());
        if( value == null ){
            addIssue(IssueType.ERROR, ctx.getStart(), "A value in a number expression is null");
            return SKIP_ERROR;
        }
        return LYnkValidation.numExp(new ArithmeticExpression(value.doubleValue()));
    }

    @Override
    public LYnkValidation visitLongExpression(LYnkParser.LongExpressionContext ctx){
        Double value = Double.parseDouble(ctx.LONG().getText());
        if( value == null ){
            addIssue(IssueType.ERROR, ctx.getStart(), "A value in a long expression is null");
            return SKIP_ERROR;
        }
        return LYnkValidation.numExp(new ArithmeticExpression(value.doubleValue()));
    }

    @Override
    public LYnkValidation visitIdentificationExpression(LYnkParser.IdentificationExpressionContext ctx){
        String variable = ctx.IDENTIFICATION().getText();
        try {
            if (variable == null || (!(this.varContext.getVarType(variable) instanceof Double) && !(this.varContext.getVarType(variable) instanceof String))) {
                addIssue(IssueType.ERROR, ctx.IDENTIFICATION().getSymbol(), variable + " is not a double or a literal");
                return SKIP_ERROR;
            }
            if( this.varContext.getVarType(variable) instanceof Double ){
                final Double result = this.varContext.getNumVarValue(variable);
                return LYnkValidation.numExp(new ArithmeticVariable(variable, this.varContext, result.doubleValue()));
            }
            if( this.varContext.getVarType(variable) instanceof String ){
                final String result = this.varContext.getStrVarValue(variable);
                return LYnkValidation.literalExp(new LiteralVariable(result, this.varContext, variable));
            }
        }
        catch (VariableDoesNotExistException e){
            addIssue(IssueType.ERROR, ctx.IDENTIFICATION().getSymbol(), variable + " does not exist in the current context");
            return SKIP_ERROR;
        }
        return LYnkValidation.numExp(null);
    }

    @Override
    public LYnkValidation visitDoubleExpression(LYnkParser.DoubleExpressionContext ctx){
        Double value = Double.parseDouble(ctx.DOUBLE().getText());
        if( value == null ){
            addIssue(IssueType.ERROR, ctx.getStart(), "A value in an arithmetic expression is null");
            return SKIP_ERROR;
        }
        return LYnkValidation.numExp(new ArithmeticExpression(value.doubleValue()));
    }
    // Boolean Expression

    /**
     * Boolean Expressions
     */

    @Override
    public LYnkValidation visitParenthesisVar(LYnkParser.ParenthesisVarContext ctx){
        if( ctx.booleanExpression() == null ){
            addIssue(IssueType.ERROR, ctx.getStart(), "The parenthesis boolean expression is empty or null");
            return SKIP_ERROR;
        }
        return visit(ctx.booleanExpression());
    }

    @Override
    public LYnkValidation visitNotExpression(LYnkParser.NotExpressionContext ctx){
        if(ctx.booleanExpression() == null){
            addIssue(IssueType.ERROR, ctx.getStart(), "The NOT boolean expression is empty or null");
            return SKIP_ERROR;
        }
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
        if(right == null || left == null){
            addIssue(IssueType.ERROR, ctx.getStart(), "A value is empty in a boolean comparaison");
            return SKIP_ERROR;
        }

        try {
            // Both boolean
            if (left.isBoolExp() && right.isBoolExp()) {
                if( ctx.op.getText() == "AND" ){
                    final AndExpression andExp = new AndExpression((BooleanExpression) left.value(), (BooleanExpression) right.value());
                    return LYnkValidation.boolExp(andExp);
                }
                else{
                    final OrExpression orExp = new OrExpression((BooleanExpression) left.value(), (BooleanExpression) right.value());
                    return LYnkValidation.boolExp(orExp);
                }
            }

            // left boolean right variable
            if (left.isBoolExp() && right.isIdentification()) {
                if (!(this.varContext.getVarType(right.asString()) instanceof Boolean)) {
                    return SKIP_ERROR;
                }
                final Boolean rightValue = varContext.getBoolVarValue(right.asString());
                if( ctx.op.getText() == "AND" ){
                    final AndExpression andExp = new AndExpression((BooleanExpression) left.value(), new BoolVariable(right.asString(), this.varContext, rightValue.booleanValue()));
                    return LYnkValidation.boolExp(andExp);
                }
                else{
                    final OrExpression orExp = new OrExpression((BooleanExpression) left.value(), new BoolVariable(right.asString(), this.varContext, rightValue.booleanValue()));
                    return LYnkValidation.boolExp(orExp);
                }
            }

            // right boolean left variable
            if (right.isBoolExp() && left.isIdentification()) {
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
            addIssue(IssueType.ERROR, ctx.getStart(), e.getMessage() + " does not exist in the current context");
            return SKIP_ERROR;
        }
        return LYnkValidation.VOID;
    }


    @Override
    public LYnkValidation visitVarComparison(LYnkParser.VarComparisonContext ctx){
        final Token left = ctx.left;
        final Token right = ctx.right;

        // left or right is null
        if( left == null || right == null){
            addIssue(IssueType.ERROR, left, "One or both variables in a comparison are missing");
            return SKIP_ERROR;
        }

        // left and right not null
        try {
            if (left != null && right != null){
                // Both Boolean
                if(varContext.getVarType(left.getText()) instanceof Boolean && varContext.getVarType(right.getText()) instanceof Boolean){
                    if( ctx.op.op.getType() != LYnkParser.EQUAL && ctx.op.op.getType() != LYnkParser.NOT_EQUAL){
                        addIssue(IssueType.ERROR, ctx.getStart(), "Wrong operator for booleans: " + ctx.op.op.getText());
                        return SKIP_ERROR;
                    }
                    final Boolean leftValue = this.varContext.getBoolVarValue(left.getText());
                    final Boolean rightValue = this.varContext.getBoolVarValue(right.getText());
                    final BoolComparison boolComp = new BoolComparison(new BoolVariable(left.getText(), this.varContext, leftValue.booleanValue()), BoolOperator.getOp(ctx.op.op.getType()), new BoolVariable(right.getText(), this.varContext, rightValue.booleanValue()));
                    return LYnkValidation.boolExp(boolComp);
                }
                // Both String
                if(varContext.getVarType(left.getText()) instanceof String && varContext.getVarType(right.getText()) instanceof String){
                    final String leftValue = this.varContext.getStrVarValue(left.getText());
                    final String rightValue = this.varContext.getStrVarValue(right.getText());
                    final LiteralComparison literalComp = new LiteralComparison(new LiteralVariable(leftValue, this.varContext, left.getText()), ArithmeticOperator.getOp(ctx.op.op.getType()), new LiteralVariable(rightValue, this.varContext, right.getText()));
                    return LYnkValidation.boolExp(literalComp);
                }
                // Both Boolean
                if(varContext.getVarType(left.getText()) instanceof Double && varContext.getVarType(right.getText()) instanceof Double){
                    final Number leftValue = this.varContext.getNumVarValue(left.getText());
                    final Number rightValue = this.varContext.getNumVarValue(right.getText());
                    final ArithmeticComparison numComp = new ArithmeticComparison( new ArithmeticVariable(left.getText(), this.varContext, leftValue.doubleValue()), ArithmeticOperator.getOp(ctx.op.op.getType()), new ArithmeticVariable(right.getText(), this.varContext, rightValue.doubleValue()));
                    return LYnkValidation.boolExp(numComp);
                }
                addIssue(IssueType.ERROR, ctx.getStart(), "Both variables " + left.getText() + " and " + right.getText() + " are not of the same type");
                return SKIP_ERROR;
            }
        }
        catch( VariableDoesNotExistException e ){
            addIssue(IssueType.ERROR, ctx.getStart(), e.getMessage() + " does not exist in the current context");
            return SKIP_ERROR;
        }
        return LYnkValidation.boolExp(null);
    }

    @Override
    public LYnkValidation visitArithmeticLiteralComparison(LYnkParser.ArithmeticLiteralComparisonContext ctx) {
        try {
            // left and right not null ( 2 arithmetic Expression )
            if (ctx.left != null && ctx.right != null && ctx.leftLiteral == null && ctx.rightLiteral == null) {
                // Either empty value or error already detected deeper in the tree
                final LYnkValidation left = visit(ctx.left);
                if (left.isSkipError()) {
                    return SKIP_ERROR;
                }
                final LYnkValidation right = visit(ctx.right);
                if (right.isSkipError()) {
                    return SKIP_ERROR;
                }

                //left and right are numbers
                if (left.isNumExp() && right.isNumExp()) {
                    final ArithmeticComparison numComp = new ArithmeticComparison( (ArithmeticExpression) left.value(), ArithmeticOperator.getOp(ctx.op.op.getType()), (ArithmeticExpression) right.value());
                    return LYnkValidation.boolExp(numComp);
                }
                // left number right variable
                if (left.isNumExp() && right.isIdentification()) {
                    if (!(this.varContext.getVarType(right.asString()) instanceof Double)) {
                        addIssue(IssueType.ERROR, ctx.right.getStart(), ctx.right.getText() + " isn't a Double but is compared to another one");
                        return SKIP_ERROR;
                    }
                    final Number rightValue = varContext.getNumVarValue(right.asString());
                    final ArithmeticComparison numComp = new ArithmeticComparison( (ArithmeticExpression) left.value(), ArithmeticOperator.getOp(ctx.op.op.getType()), new ArithmeticVariable(right.asString(), this.varContext, rightValue.doubleValue()));
                    return LYnkValidation.boolExp(numComp);
                }
                // left variable right number
                if (left.isIdentification() && right.isNumExp()) {
                    if (!(this.varContext.getVarType(left.asString()) instanceof Double)) {
                        addIssue(IssueType.ERROR, ctx.left.getStart(), ctx.left.getText() + " isn't a Double but is compared to another one");
                        return SKIP_ERROR;
                    }
                    final Number leftValue = varContext.getNumVarValue(left.asString());
                    final ArithmeticComparison numComp = new ArithmeticComparison(new ArithmeticVariable(left.asString(), this.varContext, leftValue.doubleValue()), ArithmeticOperator.getOp(ctx.op.op.getType()), (ArithmeticExpression) right.value());
                    return LYnkValidation.boolExp(numComp);
                }
            }
            // leftLiteral and right not null
            if (ctx.left == null && ctx.right != null && ctx.leftLiteral != null && ctx.rightLiteral == null){
                final Token leftL = ctx.leftLiteral;
                final LYnkValidation right = visit(ctx.right);
                if (right.isSkipError()) {
                    return SKIP_ERROR;
                }
                if( !right.isIdentification() ){
                    addIssue(IssueType.ERROR, leftL, right.asString() + " is not a variable but an arithmetic expression compared to: " + leftL.getText());
                    return SKIP_ERROR;
                }
                if( right.isIdentification() && !(this.varContext.getVarType(right.asString()) instanceof String)){
                    addIssue(IssueType.ERROR, leftL, right.asString() + " is a variable but does not have a String value and is compared to: " + leftL.getText());
                    return SKIP_ERROR;
                }
                final String rightValue = this.varContext.getStrVarValue(right.asString());
                final LiteralComparison literalComp = new LiteralComparison(new LiteralExpression(leftL.getText()), ArithmeticOperator.getOp(ctx.op.op.getType()), new LiteralVariable(rightValue, this.varContext, right.asString()));
                return LYnkValidation.boolExp(literalComp);
            }
            // left and rightLiteral not null
            if (ctx.left != null && ctx.right == null && ctx.leftLiteral == null && ctx.rightLiteral != null){
                final Token rightL = ctx.rightLiteral;
                final LYnkValidation left = visit(ctx.left);
                if (left.isSkipError()) {
                    return SKIP_ERROR;
                }
                if( !left.isIdentification() ){
                    addIssue(IssueType.ERROR, rightL, left.asString() + " is not a variable but an arithmetic expression compared to: " + rightL.getText());
                    return SKIP_ERROR;
                }
                if( left.isIdentification() && !(this.varContext.getVarType(left.asString()) instanceof String)){
                    addIssue(IssueType.ERROR, rightL, left.asString() + " is a variable but does not have a String value and is compared to: " + rightL.getText());
                    return SKIP_ERROR;
                }
                final String leftValue = this.varContext.getStrVarValue(left.asString());
                final LiteralComparison literalComp = new LiteralComparison(new LiteralVariable(leftValue, this.varContext, left.asString()), ArithmeticOperator.getOp(ctx.op.op.getType()), new LiteralExpression(rightL.getText()));
                return LYnkValidation.boolExp(literalComp);
            }
            // leftLiteral and rightLiteral not null
            if (ctx.left == null && ctx.right == null && ctx.leftLiteral != null && ctx.rightLiteral != null){
                final Token leftL = ctx.leftLiteral;
                final Token rightL = ctx.rightLiteral;
                final LiteralComparison literalComp = new LiteralComparison(new LiteralExpression(leftL.getText()), ArithmeticOperator.getOp(ctx.op.op.getType()), new LiteralExpression(rightL.getText()));
                return LYnkValidation.boolExp(literalComp);
            }
        }
        catch( VariableDoesNotExistException e ){
            addIssue(IssueType.ERROR, ctx.getStart(), e.getMessage() + " does not exist in the current context");
            return SKIP_ERROR;
        }
        catch(IllegalStateException e){
            addIssue(IssueType.ERROR, ctx.arithmeticOperator().op, e.getMessage());
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

        if( ctx.op.op.getType() != LYnkParser.EQUAL && ctx.op.op.getType() != LYnkParser.NOT_EQUAL){
            addIssue(IssueType.ERROR, ctx.getStart(), "Wrong operator for booleans: " + ctx.op.op.getText());
            return SKIP_ERROR;
        }
        // Both boolean
        try {
            if (left.isBoolean() && right.isBoolean()) {
                final Boolean value = BooleanUtil.evalBooleanComparisonOperator((Boolean) left.value(), (Boolean) right.value(), ctx.boolOperator().op);
                return LYnkValidation.bool(value);
            }
            // left boolean right variable
            if (left.isBoolExp() && right.isIdentification()) {
                if (!(this.varContext.getVarType(right.asString()) instanceof Boolean)) {
                    addIssue(IssueType.ERROR, ctx.right.getStart(), ctx.right.getText() + " isn't a Boolean but is compared to another one");
                    return SKIP_ERROR;
                }
                final Boolean rightValue = varContext.getBoolVarValue(right.asString());
                final Boolean value = BooleanUtil.evalBooleanComparisonOperator((Boolean) left.value(), rightValue, ctx.boolOperator().op);
                return LYnkValidation.bool(value);
            }

            // right boolean left variable
            if (right.isBoolExp() && left.isIdentification()) {
                if (!(this.varContext.getVarType(left.asString()) instanceof Boolean)) {
                    addIssue(IssueType.ERROR, ctx.left.getStart(), ctx.left.getText() + " isn't a Boolean but is compared to another one");
                    return SKIP_ERROR;
                }
                final Boolean leftValue = varContext.getBoolVarValue(left.asString());
                final Boolean value = BooleanUtil.evalBooleanComparisonOperator(leftValue, (Boolean) right.value(), ctx.boolOperator().op);
                return LYnkValidation.bool(value);
            }

            // left variable right variable
            if( left.isIdentification() && right.isIdentification() ){
                if (!(this.varContext.getVarType(left.asString()) instanceof Boolean)) {
                    addIssue(IssueType.ERROR, ctx.left.getStart(), ctx.left.getText() + " isn't a Boolean but is compared to another one");
                    return SKIP_ERROR;
                }
                if (!(this.varContext.getVarType(right.asString()) instanceof Boolean)) {
                    addIssue(IssueType.ERROR, ctx.right.getStart(), ctx.right.getText() + " isn't a Boolean but is compared to another one");
                    return SKIP_ERROR;
                }
                final Boolean leftValue = this.varContext.getBoolVarValue(left.asString());
                final Boolean rightValue = this.varContext.getBoolVarValue(right.asString());
                final Boolean value = BooleanUtil.evalBooleanComparisonOperator(leftValue, rightValue, ctx.boolOperator().op);
                return LYnkValidation.bool(value);
            }
        }
        catch(IllegalStateException e){
            addIssue(IssueType.ERROR, ctx.boolOperator().op, e.getMessage());
            return SKIP_ERROR;
        }
        catch(VariableDoesNotExistException e){
            addIssue(IssueType.ERROR, ctx.getStart(), e.getMessage() + " does not exist in the current context");
            return SKIP_ERROR;
        }
    }
    
    @Override
    public LYnkValidation visitIdentificationVar(LYnkParser.IdentificationVarContext ctx){
        // TODO: Revoir la maniere dont on stocke les variables et leurs valeurs
        if(ctx.IDENTIFICATION() == null){
            addIssue(IssueType.ERROR, ctx.IDENTIFICATION().getSymbol(), "The variable name is empty");
            return SKIP_ERROR;
        }
        try {
            final String variable = ctx.IDENTIFICATION().getText();
            final Object variableValue = varContext.getVarValue(variable);
            if( variableValue instanceof Boolean){
                final BoolVariable boolVar = new BoolVariable(variable, this.varContext, ((Boolean)variableValue).booleanValue());
                return LYnkValidation.boolExp(boolVar);
            }
            if( variableValue instanceof String){
                final LiteralVariable literalVar = new LiteralVariable( (String) variableValue, this.varContext, variable);
                return LYnkValidation.literalExp(literalVar);
            }
            if( variableValue instanceof Double){
                final ArithmeticVariable numVar = new ArithmeticVariable(variable, this.varContext, ((Double)variableValue).doubleValue());
                return LYnkValidation.numExp(numVar);
            }
        }
        catch(VariableDoesNotExistException e){
            addIssue(IssueType.ERROR, ctx.getStart(), e.getMessage() + " does not exist in the current context");
            return SKIP_ERROR;
        }
        return LYnkValidation.boolExp(null);
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
        if(!(ctx.arithmeticExpression() == null)){
            final LYnkValidation arithmetic = visit(ctx.arithmeticExpression());
            if( arithmetic.isSkipError() || !arithmetic.hasValue()){
                addIssue(IssueType.ERROR, ctx.getStart(), ctx.arithmeticExpression().getText() + " does not return an accepted value.");
                return SKIP_ERROR;
            }
            return arithmetic;
        }
        if(!(ctx.PERCENTAGE().getText() == null)){
            final String value = ctx.PERCENTAGE().getText().substring(0,ctx.PERCENTAGE().getText().length()-1);
            return LYnkValidation.doubleVar(Double.parseDouble(value));
        }
        addIssue(IssueType.ERROR, ctx.getStart(), "Statement parameter X is empty!");
        return LYnkValidation.numExp(null);
    }

    @Override
    public LYnkValidation visitNumStatementParameterY(LYnkParser.NumStatementParameterYContext ctx){
        if(!(ctx.arithmeticExpression()==null)){
            final LYnkValidation arithmetic = visit(ctx.arithmeticExpression());
            if( arithmetic.isSkipError() || !arithmetic.hasValue()){
                addIssue(IssueType.ERROR, ctx.getStart(), ctx.arithmeticExpression().getText() + " does not return an accepted value.");
                return SKIP_ERROR;
            }
            return arithmetic;
        }
        if(!(ctx.PERCENTAGE().getText() == null)){
            final String value = ctx.PERCENTAGE().getText().substring(0,ctx.PERCENTAGE().getText().length()-1);
            return LYnkValidation.doubleVar(Double.parseDouble(value));
        }
        addIssue(IssueType.ERROR, ctx.getStart(), "Statement parameter Y is empty!");
        return LYnkValidation.numExp(null);
    }

    /**
     * Statements
     */

    @Override
    public LYnkValidation visitIfStatement(LYnkParser.IfStatementContext ctx){

        if( ctx.booleanExpression() == null){
            addIssue(IssueType.ERROR, ctx.getStart(), " IF statement should have a boolean expression!");
            return SKIP_ERROR;
        }
        final LYnkValidation boolExp = visit(ctx.booleanExpression());
        if(boolExp.isSkipError() || !boolExp.hasValue()){
            addIssue(IssueType.ERROR, ctx.getStart(), "Incorrect boolean expression in IF statement");
            return SKIP_ERROR;
        }
        if( ctx.blockStatement() == null){
            addIssue(IssueType.ERROR, ctx.getStart(), " IF statement should have a block statement!");
            return SKIP_ERROR;
        }
        final LYnkValidation block = visit(ctx.blockStatement()); // block = null
        if(block.isSkipError() || !block.hasValue()){
            addIssue(IssueType.ERROR, ctx.getStart(), "An error occurred in the block below");
            return SKIP_ERROR;
        }
        return LYnkValidation.VOID;
    }

    @Override
    public LYnkValidation visitWhileStatement(LYnkParser.WhileStatementContext ctx){
        System.out.println(ctx.children);
        if( ctx.booleanExpression() == null){
            addIssue(IssueType.ERROR, ctx.getStart(), " WHILE statement should have a boolean expression!");
            return SKIP_ERROR;
        }
        final LYnkValidation boolExp = visit(ctx.booleanExpression());
        if(boolExp.isSkipError() || !boolExp.hasValue()){
            addIssue(IssueType.ERROR, ctx.getStart(), "Incorrect boolean expression in WHILE statement");
            return SKIP_ERROR;
        }
        if( ctx.blockStatement() == null){
            addIssue(IssueType.ERROR, ctx.getStart(), " WHILE statement should have a block statement!");
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
        //
        if( ctx.IDENTIFICATION() == null || ctx.to == null){
            addIssue(IssueType.ERROR, ctx.getStart(), " FOR statement have a null expression!");
            return SKIP_ERROR;
        }

        if(this.varContext.hasVar(ctx.IDENTIFICATION().getText())){
            addIssue(IssueType.ERROR, ctx.to, ctx.IDENTIFICATION().getText() + " already exist in the context. This should be a new variable!");
            return SKIP_ERROR;
        }
        final String forVar = ctx.IDENTIFICATION().getText();
        this.varContext.setNumVarValue(forVar, Double.parseDouble(ctx.to.getText()));

        if( ctx.to.getType() != LYnkParser.LONG && ctx.to.getType() != LYnkParser.NUMBER ){
            addIssue(IssueType.ERROR, ctx.to, "Expected a long or an integer value (at to) but found : " + ctx.to.getText());
            return SKIP_ERROR;
        }
        if( ctx.from != null && ctx.from.getType() != LYnkParser.LONG && ctx.from.getType() != LYnkParser.NUMBER  ){
            addIssue(IssueType.ERROR, ctx.from, "Expected a long or an integer value (at from) but found : " + ctx.from.getText());
            return SKIP_ERROR;
        }
        if( ctx.step != null && ctx.step.getType() != LYnkParser.LONG && ctx.step.getType() != LYnkParser.NUMBER ){
            addIssue(IssueType.ERROR, ctx.step, "Expected a long or an integer value (at step) but found : " + ctx.step.getText());
            return SKIP_ERROR;
        }
        final LYnkValidation block = visit(ctx.blockStatement());
        if(block.isSkipError() || !block.hasValue()){
            addIssue(IssueType.ERROR, ctx.getStart(), "An error occurred in the block below :");
            return SKIP_ERROR;
        }
        // Delete the temporary variable
        try{
            this.varContext.delete(ctx.IDENTIFICATION().getText());
        }
        catch (VariableDoesNotExistException e){
            addIssue(IssueType.ERROR, ctx.IDENTIFICATION().getSymbol(), " The temporary variable " + e.getMessage() + " does not exist anymore in the FOR statement");
            return SKIP_ERROR;
        }
        // Getting back all the values for the ForStatement
        int to = Integer.parseInt(ctx.to.getText());
        int from;
        int step;
        if( ctx.from == null ){
            from = 0;
        }
        else{
            from = Integer.parseInt(ctx.from.getText());
        }
        if( ctx.step == null ){
            step = 1;
        }
        else{
            step = Integer.parseInt(ctx.step.getText());
        }
        return LYnkValidation.statement(new ForStatement(forVar,from,to,step,(BlockStatement) block.value(), this.varContext));
    }

    @Override
    public LYnkValidation visitMimicStatement(LYnkParser.MimicStatementContext ctx){
        if(ctx.LONG()==null){
            addIssue(IssueType.ERROR, ctx.LONG().getSymbol(), "Mimic statement is missing a cursorId");
            return SKIP_ERROR;
        }
        final LYnkValidation block = visit(ctx.blockStatement());
        return block;
    }

    @Override
    public LYnkValidation visitMirrorStatement(LYnkParser.MirrorStatementContext ctx){
        // 2 parameters (x1, y1)
        if(ctx.x2==null && ctx.y2==null && !(ctx.x1==null) && !(ctx.y1==null)){
            final LYnkValidation x1Value = visit(ctx.x1);
            if( x1Value.isSkipError() || !x1Value.hasValue()){
                addIssue(IssueType.ERROR, ctx.x1.getStart(), "The first parameter has a null value or a wrong type : " + ctx.x1.getText());
                return SKIP_ERROR;
            }
            final LYnkValidation y1Value = visit(ctx.y1);
            if( y1Value.isSkipError() || !y1Value.hasValue()){
                addIssue(IssueType.ERROR, ctx.y1.getStart(), "The second parameter has a null value or a wrong type : " + ctx.y1.getText());
                return SKIP_ERROR;
            }
            final LYnkValidation block = visit(ctx.blockStatement());
            if( block.isSkipError() || !block.hasValue()){
                addIssue(IssueType.ERROR, ctx.blockStatement().getStart(), "An error occurred in a MIRROR block");
                return SKIP_ERROR;
            }
            return VOID;
        }
        // 1 or 3 parameters
        if( !(ctx.x2 == null) && ctx.y2 == null || ctx.x2 == null && !(ctx.y2 == null) || ctx.x1 == null && !(ctx.y1 == null) || !(ctx.x1 == null) && ctx.y1 == null){
            addIssue(IssueType.ERROR, ctx.getStart(), "Mirror statement requires 2 or 4 arguments but found 1 or 3!");
            return SKIP_ERROR;
        }
        // 4 parameters (x1, y1, x2, y2)
        if(!(ctx.x2==null) && !(ctx.y2==null) && !(ctx.x1==null) && !(ctx.y1==null)){
            final LYnkValidation x1Value = visit(ctx.x1);
            if( x1Value.isSkipError() || !x1Value.hasValue()){
                addIssue(IssueType.ERROR, ctx.x1.getStart(), "The first parameter has a null value or a wrong type : " + ctx.x1.getText());
                return SKIP_ERROR;
            }
            final LYnkValidation y1Value = visitNumStatementParameterY(ctx.y1);
            if( y1Value.isSkipError() || !y1Value.hasValue()){
                addIssue(IssueType.ERROR, ctx.y1.getStart(), "The second parameter has a null value or a wrong type : " + ctx.y1.getText());
                return SKIP_ERROR;
            }
            final LYnkValidation x2Value = visit(ctx.x2);
            if( x2Value.isSkipError() || !x2Value.hasValue()){
                addIssue(IssueType.ERROR, ctx.x2.getStart(), "The third parameter has a null value or a wrong type : " + ctx.x2.getText());
                return SKIP_ERROR;
            }
            final LYnkValidation y2Value = visitNumStatementParameterY(ctx.y2);
            if( y2Value.isSkipError() || !y2Value.hasValue()){
                addIssue(IssueType.ERROR, ctx.y2.getStart(), "The fourth parameter has a null value or a wrong type : " + ctx.y2.getText());
                return SKIP_ERROR;
            }
            final LYnkValidation block = visit(ctx.blockStatement());
            if( block.isSkipError() || !block.hasValue()){
                addIssue(IssueType.ERROR, ctx.blockStatement().getStart(), "An error occured in a MIRROR block");
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
        if(!(ctx.numStatementParameterX() == null)){
            if ( isLetter( ctx.numStatementParameterX().getText().charAt(0)) ){
                addIssue(IssueType.ERROR, ctx.getStart(), "FWD statement was not given a parameter");
                return SKIP_ERROR;
            }
            final LYnkValidation fwdValue = visit(ctx.numStatementParameterX());
            if( fwdValue.isSkipError() || !fwdValue.hasValue()){
                addIssue(IssueType.ERROR, ctx.getStart(), "The value passed in FWD is null or the wrong type: " + ctx.numStatementParameterX().getText());
                return SKIP_ERROR;
            }
            return VOID;
        }
        // no params (antlr does not support having nothing, so we detect if the given parameter is the beginning of a statement
        return SKIP_ERROR;
    }

    @Override
    public LYnkValidation visitBackwardStatement(LYnkParser.BackwardStatementContext ctx){
        if(!(ctx.numStatementParameterX() == null)){
            if ( isLetter( ctx.numStatementParameterX().getText().charAt(0)) ){
                addIssue(IssueType.ERROR, ctx.getStart(), "FWD statement was not given a parameter");
                return SKIP_ERROR;
            }
            final LYnkValidation bwdValue = visit(ctx.numStatementParameterX());
            if( bwdValue.isSkipError() || !bwdValue.hasValue()){
                addIssue(IssueType.ERROR, ctx.getStart(), "The value passed in FWD is null or the wrong type: " + ctx.numStatementParameterX().getText());
                return SKIP_ERROR;
            }
            return LYnkValidation.statement(new BackwardStatement((ArithmeticExpression) bwdValue.value(), varContext));
        }
        // no params
        addIssue(IssueType.ERROR, ctx.getStart(), "BWD statement was not given a parameter");
        return SKIP_ERROR;
    }

    @Override
    public LYnkValidation visitMoveStatement(LYnkParser.MoveStatementContext ctx){
        // no params
        if((ctx.x == null && ctx.y == null) || isLetter( ctx.numStatementParameterX().getText().charAt(0))){
            addIssue(IssueType.ERROR, ctx.getStart(), "MOV statement was not given a parameter");
            return SKIP_ERROR;
        }

        // x or y empty
        if(!(ctx.x == null) && ctx.y == null || ctx.x == null && !(ctx.y == null)){
            addIssue(IssueType.ERROR, ctx.getStart(), "MOV statement was given only 1 parameter");
            return SKIP_ERROR;
        }

        // 2 parameters
        final LYnkValidation xValue = visit(ctx.x);
        if( xValue.isSkipError() || !xValue.hasValue()){
            addIssue(IssueType.ERROR, ctx.x.getStart(), "The x parameter has a null value or a wrong type : " + ctx.x.getText());
            return SKIP_ERROR;
        }
        final LYnkValidation yValue = visit(ctx.y);
        if( yValue.isSkipError() || !yValue.hasValue()){
            addIssue(IssueType.ERROR, ctx.y.getStart(), "The y parameter has a null value or a wrong type : " + ctx.y.getText());
            return SKIP_ERROR;
        }
        return VOID;
    }

    @Override
    public LYnkValidation visitPositionStatement(LYnkParser.PositionStatementContext ctx){
        // no params
        if((ctx.x == null && ctx.y == null) || isLetter( ctx.numStatementParameterX().getText().charAt(0))){
            addIssue(IssueType.ERROR, ctx.getStart(), "MOV statement was not given a parameter");
            return SKIP_ERROR;
        }

        // x or y empty
        if(!(ctx.x == null) && ctx.y == null || ctx.x == null && !(ctx.y == null)){
            addIssue(IssueType.ERROR, ctx.getStart(), "MOV statement was given only 1 parameter");
            return SKIP_ERROR;
        }

        // 2 parameters
        final LYnkValidation xValue = visit(ctx.x);
        if( xValue.isSkipError() || !xValue.hasValue()){
            addIssue(IssueType.ERROR, ctx.x.getStart(), "The x parameter has a null value or a wrong type : " + ctx.x.getText());
            return SKIP_ERROR;
        }
        final LYnkValidation yValue = visit(ctx.y);
        if( yValue.isSkipError() || !yValue.hasValue()){
            addIssue(IssueType.ERROR, ctx.y.getStart(), "The x parameter has a null value or a wrong type : " + ctx.y.getText());
            return SKIP_ERROR;
        }
        return LYnkValidation.statement(new PositionStatement((ArithmeticExpression) xValue.value(), (ArithmeticExpression) yValue.value(), this.varContext));
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
        if( (ctx.HEXCODE() == null && ctx.arithmeticExpression() == null)  || isLetter( ctx.HEXCODE().getText().charAt(0)) || isLetter( ctx.arithmeticExpression(0).getText().charAt(0))){
            addIssue(IssueType.ERROR, ctx.getStart(), "COLOR statement was not given parameters");
            return SKIP_ERROR;
        }
        // 1 or 2 params when expected 3 for rgb
        if( (ctx.arithmeticExpression(0)==null && ctx.arithmeticExpression(1)==null && !(ctx.arithmeticExpression(2)==null)) || (ctx.arithmeticExpression(0)==null && !(ctx.arithmeticExpression(1)==null) && !(ctx.arithmeticExpression(2)==null))){
            addIssue(IssueType.ERROR, ctx.getStart(), "COLOR statement found 1 or 2 parameter for arithmeticExpressions but expected 3: " + ctx.arithmeticExpression().toString());
            return SKIP_ERROR;
        }
        // 1 param and hexcode
        if( ctx.HEXCODE() != null ){
            final String hexcode = ctx.HEXCODE().getText();
            return LYnkValidation.statement(new ColorStatement(hexcode, this.varContext));
        }
        // 3 param rgb (WORK FOR hsv FORMAT AS WELL)
        if( ctx.arithmeticExpression(0) != null && ctx.arithmeticExpression(1) != null && ctx.arithmeticExpression(2) != null ){
            final ArithmeticExpression red = (ArithmeticExpression) visit(ctx.arithmeticExpression(0)).value();
            final ArithmeticExpression green = (ArithmeticExpression) visit(ctx.arithmeticExpression(1)).value();
            final ArithmeticExpression blue = (ArithmeticExpression) visit(ctx.arithmeticExpression(2)).value();
            return LYnkValidation.statement(new ColorStatement(red,green,blue,this.varContext));
        }
        return SKIP_ERROR;
    }

    @Override
    public LYnkValidation visitCursorStatement(LYnkParser.CursorStatementContext ctx){
        // no params
        if( ctx.LONG() == null){
            addIssue(IssueType.ERROR, ctx.getStart(), "CURSOR statement was not given a parameter");
            return SKIP_ERROR;
        }
        try {
            this.cursorContext.addCursor(Long.parseLong(ctx.LONG().getText()));
            return VOID;
        }
        catch( CursorAlreadyExistingException e ){
            addIssue(IssueType.ERROR, ctx.LONG().getSymbol(), "The cursor n°" + ctx.LONG().getText() + " is already in use");
            return SKIP_ERROR;
        }
    }

    @Override
    public LYnkValidation visitSelectStatement(LYnkParser.SelectStatementContext ctx){
        // no params
        if( ctx.LONG() == null ){
            addIssue(IssueType.ERROR, ctx.getStart(), "SELECT statement was not given a parameter");
            return SKIP_ERROR;
        }
        try {
            this.cursorContext.selectCursor(Long.parseLong(ctx.LONG().getText()));
            return VOID;
        }
        catch( MissingCursorException e){
            addIssue(IssueType.ERROR, ctx.LONG().getSymbol(), "The cursor n°" + ctx.LONG().getText() + " does not exist in the current context");
            return SKIP_ERROR;
        }
        catch( CursorException e){
            addIssue(IssueType.ERROR, ctx.LONG().getSymbol(), "The cursor n°" + ctx.LONG().getText() + " is invalid!");
            return SKIP_ERROR;
        }
    }

    @Override
    public LYnkValidation visitRemoveStatement(LYnkParser.RemoveStatementContext ctx){
        // no params
        if( ctx.LONG() == null ){
            addIssue(IssueType.ERROR, ctx.getStart(), "REMOVE statement was not given a parameter");
            return SKIP_ERROR;
        }
        try {
            this.cursorContext.removeCursor(Long.parseLong(ctx.LONG().getText()));
            return VOID;
        }
        catch( MissingCursorException e){
            addIssue(IssueType.ERROR, ctx.LONG().getSymbol(), "The cursor n°" + ctx.LONG().getText() + " does not exist in the current context!");
            return SKIP_ERROR;
        }
        catch( CursorException e){
            addIssue(IssueType.ERROR, ctx.LONG().getSymbol(), "The cursor n°" + ctx.LONG().getText() + " is in use in a block!");
            return SKIP_ERROR;
        }
    }

    @Override
    public LYnkValidation visitPressStatement(LYnkParser.PressStatementContext ctx){
        if( (ctx.PERCENTAGE() == null) && ctx.arithmeticExpression() ==null){
            addIssue(IssueType.ERROR, ctx.getStart(), "PRESS statement was not given a parameter");
            return SKIP_ERROR;
        }
        if(ctx.arithmeticExpression() != null) {
            final LYnkValidation arithmetic = visit(ctx.arithmeticExpression());
            if (arithmetic.isSkipError() || !arithmetic.hasValue()) {
                addIssue(IssueType.ERROR, ctx.getStart(), "The arithmetic expression passed in argument contains an error");
                return SKIP_ERROR;
            }
            return VOID;
        }
        if(ctx.PERCENTAGE() != null){
            return VOID;
        }
        addIssue(IssueType.WARNING, ctx.getStart(), "Press statement unrecognizable");
        return SKIP_ERROR;
    }

    @Override
    public LYnkValidation visitThickStatement(LYnkParser.ThickStatementContext ctx){
        if( ctx.arithmeticExpression() == null ){
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
        if( ctx.LONG() == null && ctx.x == null && ctx.y == null ){
            addIssue(IssueType.ERROR, ctx.getStart(), "LOOKAT statement was not given a parameter");
            return SKIP_ERROR;
        }
        // 1 param
        if( ctx.LONG() == null && (!(ctx.x == null) && ctx.y == null || ctx.x == null && !(ctx.y == null)) ){
            addIssue(IssueType.ERROR, ctx.getStart(), "LOOKAT statement was given only 1 parameter");
            return SKIP_ERROR;
        }
        if( ctx.LONG() == null && ctx.x != null && ctx.y != null ) {
            // 2 parameters
            final LYnkValidation xValue = visitNumStatementParameterX(ctx.x);
            if (xValue.isSkipError() || !xValue.hasValue()) {
                addIssue(IssueType.ERROR, ctx.x.getStart(), "The x parameter has a null value or a wrong type : " + ctx.x.getText());
                return SKIP_ERROR;
            }
            final LYnkValidation yValue = visitNumStatementParameterY(ctx.y);
            if (yValue.isSkipError() || !yValue.hasValue()) {
                addIssue(IssueType.ERROR, ctx.y.getStart(), "The y parameter has a null value or a wrong type : " + ctx.y.getText());
                return SKIP_ERROR;
            }
            return LYnkValidation.statement(new LookAtStatement((ArithmeticExpression) xValue.value(), (ArithmeticExpression) yValue.value(), this.varContext));
        }
        // 1 id
        if(ctx.LONG() != null && ctx.x == null && ctx.y == null ){
            if(!cursorContext.cursorExist(Long.parseLong(ctx.LONG().getText()))){
                addIssue(IssueType.ERROR, ctx.LONG().getSymbol(),  "Looking at a non-existant cursor");
                return SKIP_ERROR;
            }
            return VOID;
        }
        return VOID;
    }

    @Override
    public LYnkValidation visitRotationStatement(LYnkParser.RotationStatementContext ctx){
        if( ctx.arithmeticExpression() == null ){
            addIssue(IssueType.ERROR, ctx.getStart(), "TURN statement was not given a parameter");
            return SKIP_ERROR;
        }
        final LYnkValidation arithmetic = visit(ctx.arithmeticExpression());
        if( arithmetic.isSkipError() || !arithmetic.hasValue()){
            addIssue(IssueType.ERROR, ctx.getStart(), "The arithmetic expression passed in argument contains an error");
            return SKIP_ERROR;
        }
        return LYnkValidation.statement(new RotationStatement((ArithmeticExpression) arithmetic.value(), this.varContext));
    }

    /**
     * Variable declarations
     */

    @Override
    public LYnkValidation visitStringDeclaration(LYnkParser.StringDeclarationContext ctx){
        String variable = ctx.IDENTIFICATION().getText();
        //System.out.println(ctx.children);
        // no param
        if( variable.equals("<missing IDENTIFICATION>")){
            addIssue(IssueType.ERROR, ctx.getStart(), "A variable name is needed in STR statement");
            return SKIP_ERROR;
        }
        // only var name
        if( !(variable==null) && this.varContext.hasVar(variable) && ctx.LITERAL() == null){
            addIssue(IssueType.ERROR, ctx.IDENTIFICATION().getSymbol(), variable + " already exist in this context!");
            return SKIP_ERROR;
        }
        if( !(variable==null) && !this.varContext.hasVar(variable) && ctx.LITERAL() == null){
            this.varContext.setStrVarValue(variable, "");
            return LYnkValidation.statement(new StringDeclaration(variable, this.varContext ));
        }
        // var name and its value
        if( !(variable==null) && this.varContext.hasVar(variable) && !(ctx.LITERAL() == null)){
            this.varContext.setStrVarValue(variable, ctx.LITERAL().getText());
            return LYnkValidation.statement(new StringDeclaration(variable, ctx.LITERAL().getText(), this.varContext ));
        }
        if( !(variable==null) && !this.varContext.hasVar(variable) && !(ctx.LITERAL() == null)){
            this.varContext.setStrVarValue(variable, ctx.LITERAL().getText());
            return LYnkValidation.statement(new StringDeclaration(variable, ctx.LITERAL().getText(), this.varContext ));
        }
        addIssue(IssueType.WARNING, ctx.getStart(), "STR declaration unrecognizable");
        return SKIP_ERROR;
    }

    @Override
    public LYnkValidation visitBoolDeclaration(LYnkParser.BoolDeclarationContext ctx){
        String variable = ctx.IDENTIFICATION().getText();
        // no param
        if(variable.equals("<missing IDENTIFICATION>") || ctx.IDENTIFICATION() == null){
            addIssue(IssueType.ERROR, ctx.getStart(), "A variable name is needed in BOOL statement");
            return SKIP_ERROR;
        }
        // only var name
        if( !(variable==null) && this.varContext.hasVar(variable) && ctx.booleanExpression()==null){
            addIssue(IssueType.ERROR, ctx.IDENTIFICATION().getSymbol(), variable + " already exist in this context!");
            return SKIP_ERROR;
        }
        if( !(variable==null) && !this.varContext.hasVar(variable) && ctx.booleanExpression()==null){
            this.varContext.setBoolVarValue(variable, Boolean.FALSE);
            return VOID;
        }
        // var name and its value
        if( !(variable==null) && this.varContext.hasVar(variable) && !(ctx.booleanExpression()==null)){
            final LYnkValidation bool = visit(ctx.booleanExpression());
            if( bool.isSkipError() || !bool.hasValue()){
                addIssue(IssueType.ERROR, ctx.getStart(), "The bool expression has a null value or has an error");
                return SKIP_ERROR;
            }
            this.varContext.setBoolVarValue(variable, bool.value());
            return VOID;
        }
        if( !(variable==null) && !this.varContext.hasVar(variable) && !(ctx.booleanExpression()==null)){
            final LYnkValidation bool = visit(ctx.booleanExpression());
            if( bool.isSkipError() || !bool.hasValue()){
                addIssue(IssueType.ERROR, ctx.getStart(), "The bool expression has a null value or has an error");
                return SKIP_ERROR;
            }
            this.varContext.setBoolVarValue(variable, bool.value());
            return VOID;
        }
        addIssue(IssueType.WARNING, ctx.getStart(), "BOOL declaration unrecognizable");
        return SKIP_ERROR;
    }

    @Override
    public LYnkValidation visitNumberDeclaration(LYnkParser.NumberDeclarationContext ctx){
        String variable = ctx.IDENTIFICATION().getText();
        // no param
        if( variable.equals("<missing IDENTIFICATION>") ){
            addIssue(IssueType.ERROR, ctx.getStart(), "A variable name is needed in NUM statement");
            return SKIP_ERROR;
        }
        // only var name
        if( !(variable==null) && this.varContext.hasVar(variable) && ctx.arithmeticExpression() == null){
            addIssue(IssueType.ERROR, ctx.IDENTIFICATION().getSymbol(), variable + " already exist in this context!");
            return SKIP_ERROR;
        }
        if( !(variable==null) && !this.varContext.hasVar(variable) && ctx.arithmeticExpression() == null){
            this.varContext.setNumVarValue(variable, Double.parseDouble("0"));
            return LYnkValidation.statement(new NumberDeclaration(variable, this.varContext));
        }
        // var name and its value
        if( !(variable== null) && this.varContext.hasVar(variable) && !(ctx.arithmeticExpression()== null)){
            final LYnkValidation number = visit(ctx.arithmeticExpression());
            if( number.isSkipError() || !number.hasValue()){
                addIssue(IssueType.ERROR, ctx.getStart(), "The arithmetic expression has a null value or has an error");
                return SKIP_ERROR;
            }
            this.varContext.setNumVarValue(variable, ((ArithmeticExpression)number.value()).evaluate());
            return LYnkValidation.statement(new NumberDeclaration(variable,(ArithmeticExpression) number.value(), this.varContext));
        }
        if( !variable.isEmpty() && !this.varContext.hasVar(variable) && !ctx.arithmeticExpression().isEmpty()){
            final LYnkValidation number = visit(ctx.arithmeticExpression());
            if( number.isSkipError() || !number.hasValue()){
                addIssue(IssueType.ERROR, ctx.getStart(), "The arithmetic expression has a null value or has an error");
                return SKIP_ERROR;
            }
            this.varContext.setNumVarValue(variable, ((ArithmeticExpression)number.value()).evaluate());
            return LYnkValidation.statement(new NumberDeclaration(variable,(ArithmeticExpression) number.value(), this.varContext));
        }
        addIssue(IssueType.WARNING, ctx.getStart(), "NUM declaration unrecognizable");
        return SKIP_ERROR;
    }

    @Override
    public LYnkValidation visitDeleteDeclaration(LYnkParser.DeleteDeclarationContext ctx){
        String variable = ctx.IDENTIFICATION().getText();
        // no param
        if( variable.equals("<missing IDENTIFICATION>") ){
            addIssue(IssueType.ERROR, ctx.getStart(), "A variable name is needed in DEL statement");
            return SKIP_ERROR;
        }
        try {
            // Delete existing var
            if (!(variable == null)) {
                this.varContext.delete(variable);
                return LYnkValidation.statement(new DeleteDeclaration(variable, this.varContext));
            }
        }
        catch( VariableDoesNotExistException e){
            addIssue(IssueType.ERROR, ctx.IDENTIFICATION().getSymbol(), ctx.IDENTIFICATION().getText() + " does not exist in the current context");
            return SKIP_ERROR;
        }
        addIssue(IssueType.WARNING, ctx.getStart(), "DEL declaration unrecognizable");
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

    /**
     * Return if a character is a letter
     * @param value char
     * @return true if value is a letter
     */
    public boolean isLetter(char value){
        // Majuscule
        if( value >= 65 || value <= 90){
            return true;
        }
        // Minuscule
        if( value >= 97 || value <= 122){
            return true;
        }
        return false;
    }
}
