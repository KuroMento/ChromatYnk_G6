package chromatynk.chromatynk_g6.interpreter;

import chromatynk.chromatynk_g6.Console;
import chromatynk.chromatynk_g6.Cursor;
import chromatynk.chromatynk_g6.CursorManager;
import chromatynk.chromatynk_g6.LYnk.LYnkBaseVisitor;
import chromatynk.chromatynk_g6.LYnk.LYnkParser;
import chromatynk.chromatynk_g6.Variable;
import chromatynk.chromatynk_g6.exceptions.variableExceptions.VariableDoesNotExistException;
import chromatynk.chromatynk_g6.utils.BooleanUtil;
import chromatynk.chromatynk_g6.utils.NumberUtil;
import chromatynk.chromatynk_g6.utils.StringUtil;
import javafx.scene.paint.Color;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;

import static chromatynk.chromatynk_g6.interpreter.LYnkInterpreter.VOID;

public class LYnkInterpreterVisitor extends LYnkBaseVisitor<Object> {

    private Console console;
    private LYnkVariable variableList ;
    private CursorManager cursorManager;
    public LYnkInterpreterVisitor(){
        super();
        //this.cursorManager = new CursorManager();
        //this.varList = new VariableManager();
        this.console = new Console();
        this.cursorManager = new CursorManager();
        //this.behaviour = Behaviour.DIRECT;
        //this.instructions = new ArrayDeque<>();
    }

    @Override
    public Object visitParenthesisVar(final LYnkParser.ParenthesisVarContext ctx){
        return visit(ctx.booleanExpression());
    }

    @Override
    public Object visitAndOrExpression(LYnkParser.AndOrExpressionContext ctx){
        final Object leftCondition = shouldBeBoolean(visit(ctx.left));
        final Object rightCondition = shouldBeBoolean(visit(ctx.right));
        if(leftCondition instanceof Boolean && rightCondition instanceof Boolean){
            return BooleanUtil.evalAndOrOperator((Boolean) leftCondition, (Boolean) rightCondition, ctx.op);
        }
        console.addLine("Operator " + "'" + ctx.op.getText() + "' " + "not supported for String");
        return VOID;
        //throw new IllegalStateException(String.format("Operator '%s' not supported for String", ctx.op.getText())); might be needed instead of the return VOID
    }

    @Override
    public Object visitNotExpression(LYnkParser.NotExpressionContext ctx) {
        final Object condition = visit(ctx.booleanExpression());
        if (condition instanceof Boolean) {
            return ((Boolean) condition).booleanValue();
        }
        console.addLine("NOT needs a boolean comparison to function");
        throw new IllegalStateException("NOT needs a boolean comparison to function");
    }

    @Override
    public Object visitArithmeticComparison(LYnkParser.ArithmeticComparisonContext ctx){
        final Object leftCondition = shouldBeNumber(visit(ctx.left));
        final Object rightCondition = shouldBeNumber(visit(ctx.right));
        if(leftCondition instanceof Number && rightCondition instanceof Number){
            return NumberUtil.evalNumberComparisonOperator((Number) leftCondition,(Number) rightCondition,ctx.arithmeticOperator().op);
        }
        console.addLine("Operator " + "'" + ctx.op.getText() + "' " + "not supported for Number");
        return VOID;
    }

    @Override
    public Object visitBooleanComparison(LYnkParser.BooleanComparisonContext ctx){
        final Object leftCondition = shouldBeBoolean(visit(ctx.left));
        final Object rightCondition = shouldBeBoolean(visit(ctx.right));
        if(leftCondition instanceof Boolean && rightCondition instanceof Boolean){
            return BooleanUtil.evalBooleanComparisonOperator((Boolean) leftCondition, (Boolean) rightCondition, ctx.boolOperator().op);
        }
        console.addLine("Operator " + "'" + ctx.op.getText() + "' " + "not supported for Boolean");
        return VOID;
    }

    /**
     * Compare to string together and returns the result of the comparison
     * @param ctx the parse tree
     * @return The result of the comparison
     * @throws VariableDoesNotExistException the variable does not exist
     */
    @Override
    public Object visitLiteralComparison(LYnkParser.LiteralComparisonContext ctx) throws VariableDoesNotExistException{
        final int leftType = ctx.left.getType();
        final int rightType = ctx.right.getType();
        final Object leftCondition;
        final Object rightCondition;
        if(leftType == LYnkParser.LITERAL && rightType == LYnkParser.LITERAL){
            leftCondition = ctx.left.getText();
            rightCondition = ctx.right.getText();
            return StringUtil.evalLiteralComparisonOperator((String) leftCondition, (String) rightCondition, ctx.arithmeticOperator().op);
        }
        if(leftType == LYnkParser.LITERAL && rightType == LYnkParser.IDENTIFICATION){
            leftCondition = ctx.left.getText();
            rightCondition = variableList.getStrVarValue(ctx.IDENTIFICATION().get(0));
            return StringUtil.evalLiteralComparisonOperator((String) leftCondition, (String) rightCondition, ctx.arithmeticOperator().op);
        }
        if(leftType == LYnkParser.IDENTIFICATION && rightType == LYnkParser.LITERAL){
            leftCondition = variableList.getStrVarValue(ctx.IDENTIFICATION().get(0));
            rightCondition = ctx.right.getText();
            return StringUtil.evalLiteralComparisonOperator((String) leftCondition, (String) rightCondition, ctx.arithmeticOperator().op);
        }
        if(leftType == LYnkParser.IDENTIFICATION && rightType == LYnkParser.IDENTIFICATION){
            leftCondition = variableList.getStrVarValue(ctx.IDENTIFICATION().get(0));
            rightCondition = variableList.getStrVarValue(ctx.IDENTIFICATION().get(1));
            return StringUtil.evalLiteralComparisonOperator((String) leftCondition, (String) rightCondition, ctx.arithmeticOperator().op);
        }
        console.addLine("Operator " + "'" + ctx.op.getText() + "' " + "not supported for String");
        return VOID;
    }

    @Override
    public Object visitIdentificationVar(LYnkParser.IdentificationVarContext ctx) throws VariableDoesNotExistException {
        return variableList.getVarValue(ctx.IDENTIFICATION());
    }

    @Override
    public Object visitTrueVar(LYnkParser.TrueVarContext ctx) {
        return Boolean.TRUE;
    }
    @Override
    public Object visitFalseVar(LYnkParser.FalseVarContext ctx){
        return Boolean.FALSE;
    }

    @Override
    public Object visitParenthesisExpression(final LYnkParser.ParenthesisExpressionContext ctx){
        return visit(ctx.arithmeticExpression());
    }

    @Override
    public Object visitMulDivExpression(LYnkParser.MulDivExpressionContext ctx){
        final Object left = shouldBeNumber(visit(ctx.left));
        final Object right = shouldBeNumber(visit(ctx.right));
        if(left instanceof Number && right instanceof Number){
            return NumberUtil.evalBinaryOperator((Number) left, (Number) right, ctx.op);
        }
        return VOID;
    }

    @Override
    public Object visitPlusMinusExpression(LYnkParser.PlusMinusExpressionContext ctx){
        final Object left = shouldBeNumber(visit(ctx.left));
        final Object right = shouldBeNumber(visit(ctx.right));

        if (left instanceof Number && right instanceof Number){
            return NumberUtil.evalBinaryOperator((Number) left, (Number) right, ctx.op);
        }
        return VOID;
    }

    @Override
    public Object visitCompExpression(LYnkParser.CompExpressionContext ctx){
        final Object left = shouldBeNumber(visit(ctx.left));
        final Object right = shouldBeNumber(visit(ctx.right));
        if (left instanceof Number && right instanceof Number){
            return NumberUtil.evalNumberComparisonOperator((Number) left, (Number) right, ctx.numOperator().op);
        }
        return VOID;
    }

    @Override
    public Object visitNumberExpression(final LYnkParser.NumberExpressionContext ctx){
        return Long.valueOf(ctx.NUMBER().getText());
    }

    @Override
    public Object visitLongExpression(final LYnkParser.LongExpressionContext ctx){
        return Long.valueOf(ctx.LONG().getText());
    }

    @Override
    public Object visitDoubleExpression(final LYnkParser.DoubleExpressionContext ctx){
        return Double.valueOf(ctx.DOUBLE().getText());
    }

    @Override
    public Object visitIdentificationExpression(final LYnkParser.IdentificationExpressionContext ctx) throws VariableDoesNotExistException{
        return variableList.getVarValue(ctx.IDENTIFICATION());
    }

    @Override
    public Object visitIfStatement(final LYnkParser.IfStatementContext ctx) {
        final Object condition = visit(ctx.booleanExpression());
        if(!(condition instanceof Boolean)){
            console.addLine("IF needs a boolean comparison to function.");
        }
        if(Boolean.TRUE.equals(condition)){
            visit(ctx.blockStatement());
        }
        return VOID;
    }

    @Override
    public Object visitForStatement(final LYnkParser.ForStatementContext ctx){
        final Object fromCondition = ctx.from;
        final String toCondition = ctx.to.getText();
        final Object stepCondition = ctx.step;
        if(variableList.hasVar(ctx.IDENTIFICATION())){
            console.addLine("the variable used for the for statement already exists");
            return VOID;
        }
        int from = Integer.parseInt(ctx.from.getText());
        int to = Integer.parseInt(ctx.to.getText());
        int step = Integer.parseInt(ctx.step.getText());
        if(){

        }
        if(!(stepCondition == null)){
            step = Integer.parseInt(ctx.step.getText());
        }
        String variableName = ctx.IDENTIFICATION().getText();
        int variableValue;
        for(variableValue=from;variableValue<to ; variableValue += step){
            if(variableList.setNumVarValue(variableName, variableValue)){
                visit(ctx.blockStatement());
            };
        }
        return VOID;
    }

    @Override
    public Object visitWhileStatement(final LYnkParser.WhileStatementContext ctx){
        final Object condition = visit(ctx.booleanExpression());
        if(!(condition instanceof Boolean)){
            console.addLine("WHILE needs a boolean comparison to function");
        }
        if(Boolean.TRUE.equals(condition)){
            visit(ctx.blockStatement());
            visitWhileStatement(ctx);
        }
        return VOID;
    }

    @Override
    public Object visitNumParameter(LYnkParser.NumParameterContext ctx){
        if(ctx.getChild(0).getText().contains("%")){
            return Double.parseDouble(ctx.getChild(0).getText().replace("%",""))/100;
        }
        else{
            return (Number) ctx.getChild(0);
        }
    }

    @Override
    public Object visitColorParameter(LYnkParser.ColorParameterContext ctx){
        if(!ctx.DOUBLE().getText().isEmpty()){
            return Double.valueOf(ctx.DOUBLE().getText());
        }
        if(!ctx.LONG().getText().isEmpty()){
            return Long.valueOf(ctx.LONG().getText());
        }
        return VOID;
    }

    @Override
    public Object visitColorStatement(LYnkParser.ColorStatementContext ctx){
        if(!(ctx.HEXCODE().getText().isEmpty())){
            cursorManager.getSelectedCursor().setColor(Color.web(ctx.HEXCODE().getText()));
        }
        else{
            final Object red = visit(ctx.colorParameter(0));
            final Object green = visit(ctx.colorParameter(1));
            final Object blue = visit(ctx.colorParameter(2));

            cursorManager.getSelectedCursor().setColor(Color.rgb(red, green, blue));
        }
        return VOID;
    }

    @Override
    public Object visitForwardStatement(LYnkParser.ForwardStatementContext ctx) throws NegativeNumberException {
        if(!ctx.numParameter().PERCENTAGE().getText().isEmpty()) {
            //the % is transformed into an int.
        }
        else{
            cursorManager.getSelectedCursor().forward(Integer.parseInt(ctx.numParameter().getText()));
        }
        return VOID;
    }

    @Override
    public Object visitBackwardStatement(LYnkParser.BackwardStatementContext ctx) throws NegativeNumberException{
        if(!ctx.numParameter().PERCENTAGE().getText().isEmpty()){
            //the % is transformed into an int
            visitBackwardStatement(ctx); //we reuse the entry with the % transformed into an int.
        }
        else{
            cursorManager.getSelectedCursor().backward(Integer.parseInt(ctx.numParameter().getText()));
        }
        return VOID;
    }

    @Override
    public Object visitCursorStatement(LYnkParser.CursorStatementContext ctx){
        cursorManager.addCursor(Long.parseLong(ctx.LONG().getText()));
        return VOID;
    }

    @Override
    public Object visitRemoveStatement(LYnkParser.RemoveStatementContext ctx){
        cursorManager.removeCursor(Long.parseLong(ctx.LONG().getText()));
        return VOID;
    }

    @Override
    public Object visitSelectStatement(LYnkParser.SelectStatementContext ctx){
        if(cursorManager.cursorExist(Long.parseLong(ctx.LONG().getText()))){
            cursorManager.selectCursor(Long.parseLong(ctx.LONG().getText())));
        }
        return VOID;
    }

    @Override
    public Object visitThickStatement(LYnkParser.ThickStatementContext ctx){
        cursorManager.getSelectedCursor().setThickness(Long.parseLong(ctx.LONG().getText()));
        return VOID;
    }

    @Override
    public Object visitLookAtStatement(LYnkParser.LookAtStatementContext ctx){
        //if the cursor to lookat exist
        if(cursorManager.cursorExist(Long.parseLong(ctx.LONG().getText()))){
            //the cursor to look at
            Cursor cursorToLookAt = cursorManager.getCursor(Long.parseLong(ctx.LONG().getText()));
            //the selected cursor look at the position x and y
            cursorManager.getSelectedCursor().lookAt(cursorToLookAt.getPosX(),cursorToLookAt.getPosY());
        }
        return VOID;
    }

    @Override
    public Object visitBoolDeclaration(LYnkParser.BoolDeclarationContext ctx){
        if(ctx.booleanExpression().isEmpty()){
            variableList.setBoolVarValue(ctx.IDENTIFICATION().getText(),false);
        }
        else {
            variableList.setBoolVarValue(ctx.IDENTIFICATION().getText(), ctx.booleanExpression());
        }
        return VOID;

    }
    @Override
    public Object visitStringDeclaration(LYnkParser.StringDeclarationContext ctx) {
        if(ctx.LITERAL().getText().isEmpty()) {
            variableList.setStrVarValue(ctx.IDENTIFICATION(),"" );
        }
        else {
            variableList.setStrVarValue(ctx.IDENTIFICATION(), ctx.LITERAL());
        }
        return VOID;
    }

    @Override
    public Object visitNumberDeclaration(LYnkParser.NumberDeclarationContext ctx) {
        if(ctx.arithmeticExpression().isEmpty()) {
            variableList.setNumVarValue(ctx.IDENTIFICATION(),0 );
        }
        else {
            variableList.setNumVarValue(ctx.IDENTIFICATION(), ctx.arithmeticExpression());
        }
        return VOID;
    }

    @Override
    public Object visitDeleteDeclaration(LYnkParser.DeleteDeclarationContext ctx){
        try {
            variableList.delete(ctx.IDENTIFICATION().getText());
        } catch (VariableDoesNotExistException e) {
            throw new RuntimeException(e);
        }
        return VOID;
    }

    private Object shouldBeNumber(final Object o){
        if (o instanceof Number ){
            return  o;
        }
        console.addLine("Expected a number but found :" + getTypeName(o));
        throw new IllegalStateException("Expected a number but found :" + getTypeName(o));
    }

    private Object shouldBeString(final Object o){
        if (o instanceof String ){
            return  o;
        }
        console.addLine("Expected a string but found :" + getTypeName(o));
        throw new IllegalStateException("Expected a string but found :" + getTypeName(o));
    }

    private Object shouldBeBoolean(final Object o){
        if(o instanceof Boolean){
            return o;
        }
        console.addLine("Expected a boolean but found : " + getTypeName(o));
        throw new IllegalStateException("Expected a boolean but found : " + getTypeName(o));
    }

    private static String getTypeName(final Object o){
        return o != null ? o.getClass().getSimpleName() : "null";
    }

}
