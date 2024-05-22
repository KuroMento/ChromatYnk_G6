package chromatynk.chromatynk_g6.interpreter;

import chromatynk.chromatynk_g6.Console;
import chromatynk.chromatynk_g6.Cursor;
import chromatynk.chromatynk_g6.CursorManager;
import chromatynk.chromatynk_g6.LYnk.LYnkBaseVisitor;
import chromatynk.chromatynk_g6.LYnk.LYnkParser;
import chromatynk.chromatynk_g6.exceptions.variableExceptions.VariableDoesNotExistException;
import chromatynk.chromatynk_g6.utils.BooleanUtil;
import chromatynk.chromatynk_g6.utils.NumberUtil;
import chromatynk.chromatynk_g6.utils.StringUtil;
import javafx.scene.paint.Color;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

import static chromatynk.chromatynk_g6.interpreter.LYnkInterpreter.VOID;

public class LYnkInterpreterVisitor extends LYnkBaseVisitor<Object> {

    private Console console;
    private final LYnkVariable variableList ;
    private CursorManager cursorManager;
    public LYnkInterpreterVisitor(final LYnkVariable variableList){
        super();
        this.console = new Console();
        this.cursorManager = new CursorManager();
        this.variableList = variableList;
        //this.behaviour = Behaviour.DIRECT;
        //this.instructions = new ArrayDeque<>();
    }

    /**
     * Visit a boolean expression in parentheses
     * @param ctx the parse tree
     * @return the expression in it
     */
    @Override
    public Object visitParenthesisVar(final LYnkParser.ParenthesisVarContext ctx){
        return visit(ctx.booleanExpression());
    }

    /**
     * Visit an expression with "AND" or "OR"
     * @param ctx the parse tree
     * @return the value of the expression
     */
    @Override
    public Object visitAndOrExpression(LYnkParser.AndOrExpressionContext ctx){
        final Object leftCondition = shouldBeBoolean(visit(ctx.left));
        final Object rightCondition = shouldBeBoolean(visit(ctx.right));
        if(leftCondition instanceof Boolean && rightCondition instanceof Boolean){
            return BooleanUtil.evalAndOrOperator((Boolean) leftCondition, (Boolean) rightCondition, ctx.op);
        }
        console.addLine("Operator " + "'" + ctx.op.getText() + "' " + "not supported for String");
        return VOID;
    }

    /**
     * Visit an expression with "NOT"
     * @param ctx the parse tree
     * @return the value of the expression
     */
    @Override
    public Object visitNotExpression(LYnkParser.NotExpressionContext ctx) {
        final Object condition = visit(ctx.booleanExpression());
        if (condition instanceof Boolean) {
            return ((Boolean) condition).booleanValue();
        }
        console.addLine("NOT needs a boolean comparison to function");
        return VOID;
    }

    /**
     * Visit an arithmetic comparison
     * @param ctx the parse tree
     * @return the value of the expression
     */
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

    /**
     * Visit a comparison expression between boolean
     * @param ctx the parse tree
     * @return the value of the expression
     */
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
     * @return the value of the comparison
     */
    @Override
    public Object visitLiteralComparison(LYnkParser.LiteralComparisonContext ctx){
        final int leftType = ctx.left.getType();
        final int rightType = ctx.right.getType();
        final Object leftCondition;
        final Object rightCondition;
        try {
            if (leftType == LYnkParser.LITERAL && rightType == LYnkParser.LITERAL) {
                leftCondition = ctx.left.getText();
                rightCondition = ctx.right.getText();
                return StringUtil.evalLiteralComparisonOperator((String) leftCondition, (String) rightCondition, ctx.arithmeticOperator().op);
            }
            if (leftType == LYnkParser.LITERAL && rightType == LYnkParser.IDENTIFICATION) {
                leftCondition = ctx.left.getText();
                rightCondition = variableList.getStrVarValue(ctx.IDENTIFICATION().get(0));
                return StringUtil.evalLiteralComparisonOperator((String) leftCondition, (String) rightCondition, ctx.arithmeticOperator().op);
            }
            if (leftType == LYnkParser.IDENTIFICATION && rightType == LYnkParser.LITERAL) {
                leftCondition = variableList.getStrVarValue(ctx.IDENTIFICATION().get(0));
                rightCondition = ctx.right.getText();
                return StringUtil.evalLiteralComparisonOperator((String) leftCondition, (String) rightCondition, ctx.arithmeticOperator().op);
            }
            if (leftType == LYnkParser.IDENTIFICATION && rightType == LYnkParser.IDENTIFICATION) {
                leftCondition = variableList.getStrVarValue(ctx.IDENTIFICATION().get(0));
                rightCondition = variableList.getStrVarValue(ctx.IDENTIFICATION().get(1));
                return StringUtil.evalLiteralComparisonOperator((String) leftCondition, (String) rightCondition, ctx.arithmeticOperator().op);
            }
        }
        finally {
            console.addLine("Operator " + "'" + ctx.op.getText() + "' " + "not supported for String");
            return VOID;
        }
    }

    /**
     * Visit an identification expression
     * @param ctx the parse tree
     * @return the value of the expression
     */
    @Override
    public Object visitIdentificationVar(LYnkParser.IdentificationVarContext ctx){
        try {
            return variableList.getVarValue(ctx.IDENTIFICATION());
        }
        finally{
            return VOID;
        }
    }

    /**
     * Visit the boolean "TRUE"
     * @param ctx the parse tree
     * @return TRUE
     */
    @Override
    public Object visitTrueVar(LYnkParser.TrueVarContext ctx) {
        return Boolean.TRUE;
    }

    /**
     * Visit the boolean "FALSE"
     * @param ctx the parse tree
     * @return FALSE
     */
    @Override
    public Object visitFalseVar(LYnkParser.FalseVarContext ctx){
        return Boolean.FALSE;
    }

    /**
     * Visit an expression in parentheses
     * @param ctx the parse tree
     * @return the value of the arithmetic expression
     */
    @Override
    public Object visitParenthesisExpression(final LYnkParser.ParenthesisExpressionContext ctx){
        return visit(ctx.arithmeticExpression());
    }

    /**
     * Visit an arithmetic expression with * or /
     * @param ctx the parse tree
     * @return the value of the expression
     */
    @Override
    public Object visitMulDivExpression(LYnkParser.MulDivExpressionContext ctx){
        final Object left = shouldBeNumber(visit(ctx.left));
        final Object right = shouldBeNumber(visit(ctx.right));
        if(left instanceof Number && right instanceof Number){
            return NumberUtil.evalBinaryOperator((Number) left, (Number) right, ctx.op);
        }
        return VOID;
    }

    /**
     * Visit an arithmetic expression with + or -
     * @param ctx the parse tree
     * @return the value of the expression
     */
    @Override
    public Object visitPlusMinusExpression(LYnkParser.PlusMinusExpressionContext ctx){
        final Object left = shouldBeNumber(visit(ctx.left));
        final Object right = shouldBeNumber(visit(ctx.right));

        if (left instanceof Number && right instanceof Number){
            return NumberUtil.evalBinaryOperator((Number) left, (Number) right, ctx.op);
        }
        return VOID;
    }

    /**
     * Visit an arithmetic expression with comparison >, >=; <, <=, ==, !=
     * @param ctx the parse tree
     * @return the value of the expression
     */
    @Override
    public Object visitCompExpression(LYnkParser.CompExpressionContext ctx){
        final Object left = shouldBeNumber(visit(ctx.left));
        final Object right = shouldBeNumber(visit(ctx.right));
        if (left instanceof Number && right instanceof Number){
            return NumberUtil.evalNumberComparisonOperator((Number) left, (Number) right, ctx.numOperator().op);
        }
        return VOID;
    }

    /**
     * Visit a number expression
     * @param ctx the parse tree
     * @return the value of the expression
     */
    @Override
    public Object visitNumberExpression(final LYnkParser.NumberExpressionContext ctx){
        return Long.valueOf(ctx.NUMBER().getText());
    }

    /**
     * Visit a long expression
     * @param ctx the parse tree
     * @return the value of the expression
     */
    @Override
    public Object visitLongExpression(final LYnkParser.LongExpressionContext ctx){
        return Long.valueOf(ctx.LONG().getText());
    }

    /**
     * Visit a double expression
     * @param ctx the parse tree
     * @return the value of the expression
     */
    @Override
    public Object visitDoubleExpression(final LYnkParser.DoubleExpressionContext ctx){
        return Double.valueOf(ctx.DOUBLE().getText());
    }

    /**
     * Visit an identification (variable) in an expression
     * @param ctx the parse tree
     * @return the value of the expression
     */
    @Override
    public Object visitIdentificationExpression(final LYnkParser.IdentificationExpressionContext ctx){
        try {
            return variableList.getVarValue(ctx.IDENTIFICATION());
        }
        finally{
            return VOID;
        }
    }

    /**
     * Visit a condition if
     * @param ctx the parse tree
     * @return VOID as the if execute instructions
     */
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

    /**
     * Visit a for statement
     * @param ctx the parse tree
     * @return VOID as the for execute instructions
     */
    @Override
    public Object visitForStatement(final LYnkParser.ForStatementContext ctx){
        final Object fromCondition = ctx.from;
        final String toCondition = ctx.to.getText();
        final Object stepCondition = ctx.step;
        int from = 0;
        int step = 1;
        try {
            if (variableList.hasVar(ctx.IDENTIFICATION().getText())) {
                console.addLine("the variable used for the for statement already exists");
                return VOID;
            }
            if (toCondition.isEmpty()) {
                console.addLine("the TO in the for statement cannot be empty");
                return VOID;
            }
            int to = Integer.parseInt(ctx.to.getText());
            if (!(fromCondition == null)) {
                from = Integer.parseInt(ctx.from.getText());
            }
            if (!(stepCondition == null)) {
                step = Integer.parseInt(ctx.step.getText());
            }
            String variableName = ctx.IDENTIFICATION().getText();
            int variableValue;
            for (variableValue = from; variableValue < to; variableValue += step) {
                if (variableList.setNumVarValue(variableName, variableValue)) {
                    visit(ctx.blockStatement());
                }
                ;
            }
            variableList.delete(variableName);
        }
        finally {
            return VOID;
        }
    }

    /**
     * Visit a while statement
     * @param ctx the parse tree
     * @return VOID as the while execute instructions
     */
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

    /**
     * Visit a parameter = a value, variable or %
     * @param ctx the parse tree
     * @return the value of the expression with % between 0 and 1
     */
    @Override
    public Object visitNumParameter(LYnkParser.NumParameterContext ctx){
        if(ctx.getChild(0).getText().contains("%")){
            return Double.parseDouble(ctx.getChild(0).getText().replace("%",""))/100;
        }
        else{
            return (Number) ctx.getChild(0);
        }
    }

    /**
     * Visit a parameter = a value, variable or %
     * @param ctx the parse tree
     * @return the value of the expression with % the value on the x-axis
     */
    @Override
    public Object visitNumStatementParameterX(LYnkParser.NumStatementParameterXContext ctx){
        if(!ctx.PERCENTAGE().getText().isEmpty()){
            String tmp = ctx.PERCENTAGE().getText();
            tmp = tmp.substring(0, tmp.length()-1); //the entry without the % (only the number is kept)
            Double value = Double.parseDouble(tmp);
            value = (value/100)*1920; //TODO: Convertir 1920 en attribut de la classe
            int result = (int) value.doubleValue();
            return result;

        }
        else{
            return visit(ctx.arithmeticExpression());
        }
    }

    /**
     * Visit a parameter = a value, variable or %
     * @param ctx the parse tree
     * @return the value of the expression with % the value on the y-axis
     */
    @Override
    public Object visitNumStatementParameterY(LYnkParser.NumStatementParameterYContext ctx){
        if(!ctx.PERCENTAGE().getText().isEmpty()){
            String tmp = ctx.PERCENTAGE().getText();
            tmp = tmp.substring(0, tmp.length()-1); //the entry without the % (only the number is kept)
            Double value = Double.parseDouble(tmp);
            value = (value/100)*1080; //TODO: Convertir 1080 en attribut de la classe
            int result = (int) value.doubleValue();
            return result;
        }
        else{
            return visit(ctx.arithmeticExpression());
        }
    }

    /**
     * Visit a mimic statement
     * @param ctx the parse tree
     * @return VOID as mimic is applied to a block of instruction
     */
    @Override
    public Object visitMimicStatement(LYnkParser.MimicStatementContext ctx){
        try {
            int toCopyId = Integer.parseInt(ctx.LONG().getText());
            cursorManager.addMimics(toCopyId);
            visit(ctx.blockStatement());
            cursorManager.deleteMimics(toCopyId);
        }
        finally {
            return VOID;
        }
    }

    /**
     * Visit a mirror statement
     * @param ctx the parse tree
     * @return VOID as mirror is applied to a block of instruction
     */
    @Override
    public Object visitMirrorStatement(LYnkParser.MirrorStatementContext ctx){
        try{
            int x1 = (int) visit(ctx.x1);
            int y1 = (int) visit(ctx.y1);
            long id = cursorManager.getSelectedCursorId();
            if(!ctx.x2.isEmpty()){
                int x2 = (int) visit(ctx.x2);
                int y2 = (int) visit(ctx.y2);
                cursorManager.addMirror(x1, y1, x2, y2);
            }
            else{
                cursorManager.addMirror(x1, y1);
            }
            visit(ctx.blockStatement());
            cursorManager.deleteMirror(id);
        }
        finally {
            return VOID;
        }
    }

    /**
     * Visit a color statement
     * @param ctx the parse tree
     * @return VOID as the color is applied to the selected cursor
     */
    @Override
    public Object visitColorStatement(LYnkParser.ColorStatementContext ctx){
        if(!(ctx.HEXCODE().getText().isEmpty())){
            cursorManager.getSelectedCursor().setColor(Color.web(ctx.HEXCODE().getText()));
        }
        else{
            final int red = (int) visit(ctx.arithmeticExpression(0));
            final int green = (int) visit(ctx.arithmeticExpression(1));
            final int blue = (int) visit(ctx.arithmeticExpression(2));

            cursorManager.getSelectedCursor().setColor(Color.rgb(red, green, blue));
        }
        return VOID;
    }

    /**
     * Visit a forward ("FWD") statement
     * @param ctx the parse tree
     * @return VOID as forward is applied to the selected cursor
     */
    @Override
    public Object visitForwardStatement(LYnkParser.ForwardStatementContext ctx){
        try {
            cursorManager.forward((int) visit(ctx.numStatementParameterX()));
        }
        finally {
            return VOID;
        }
    }

    /**
     * Visit a backward ("BWD") statement
     * @param ctx the parse tree
     * @return VOID as backward is applied to the selected cursor
     */
    @Override
    public Object visitBackwardStatement(LYnkParser.BackwardStatementContext ctx){
        try {
            cursorManager.forward((int) visit(ctx.numStatementParameterX()));
        }
        finally {
            return VOID;
        }
    }

    /**
     * Visit a move ("MOV") statement
     * @param ctx the parse tree
     * @return VOID as the move is applied to the selected cursor
     */
    @Override
    public Object visitMoveStatement(LYnkParser.MoveStatementContext ctx){
        try {
            int x;
            int y;
            x = (int) visit(ctx.numStatementParameterX());
            y = (int) visit(ctx.numStatementParameterY());
            cursorManager.move(x, y);
        }
        finally{
            return VOID;
        }
    }

    /**
     * Visit a position ("POS") statement
     * @param ctx the parse tree
     * @return VOID as the position is applied to the selected cursor
     */
    @Override
    public Object visitPositionStatement(LYnkParser.PositionStatementContext ctx){
        try {
            int x;
            int y;
            x = (int) visit(ctx.numStatementParameterX());
            y = (int) visit(ctx.numStatementParameterY());
            cursorManager.position(x, y);
        }
        finally{
            return VOID;
        }
    }

    /**
     * Visit a cursor statement adding a cursor
     * @param ctx the parse tree
     * @return VOID as adding cursor is applied to cursorManager
     */
    @Override
    public Object visitCursorStatement(LYnkParser.CursorStatementContext ctx){
        try {
            cursorManager.addCursor(Long.parseLong(ctx.LONG().getText()));
        }
        finally {
            return VOID;
        }
    }

    /**
     * Visit a select cursor statement
     * @param ctx the parse tree
     * @return VOID as selecting a cursor is applied to cursorManager
     */
    @Override
    public Object visitSelectStatement(LYnkParser.SelectStatementContext ctx){
        try {
            if (cursorManager.cursorExist(Long.parseLong(ctx.LONG().getText()))) {
                cursorManager.selectCursor(Long.parseLong(ctx.LONG().getText()));
            }
        }
        finally {
            return VOID;
        }
    }

    /**
     * Visit a remove cursor statement
     * @param ctx the parse tree
     * @return VOID as selecting a cursor is applied to cursorManager
     */
    @Override
    public Object visitRemoveStatement(LYnkParser.RemoveStatementContext ctx){
        try {
            cursorManager.removeCursor(Long.parseLong(ctx.LONG().getText()));
        }
        finally {
            return VOID;
        }
    }

    /**
     * Visit a press statement
     * @param ctx the parse tree
     * @return VOID as press is applied to the selected cursor
     */
    @Override
    public Object visitPressStatement(LYnkParser.PressStatementContext ctx){
        try {
            if (!ctx.arithmeticExpression().isEmpty()) {
                cursorManager.press((float) visit(ctx.arithmeticExpression()));
            } else {
                String tmp = ctx.PERCENTAGE().getText();
                tmp = tmp.substring(0, tmp.length() - 1); //the entry without the % (only the number is kept)
                Float value = Float.parseFloat(tmp);
                cursorManager.press((float) value);
            }
        }
        finally {
            return VOID;
        }
    }

    /**
     * Visit a thick statement.
     * @param ctx the parse tree
     * @return Change the thickness of a cursor.
     */
    @Override
    public Object visitThickStatement(LYnkParser.ThickStatementContext ctx){
        try {
            cursorManager.thick((float) visit(ctx.arithmeticExpression()));
        }
        finally {
            return VOID;
        }
    }

    /**
     * Visit a look at statement.
     * @param ctx the parse tree
     * @return The selected cursor looks at a position or other cursor given.
     */
    @Override
    public Object visitLookAtStatement(LYnkParser.LookAtStatementContext ctx){
        int x = 0;
        int y = 0;
        //if a cursor to look at exist
        if(!ctx.LONG().getText().isEmpty() && cursorManager.cursorExist(Long.parseLong(ctx.LONG().getText()))){
            //the cursor to look at
            Cursor cursorToLookAt = cursorManager.getCursor(Long.parseLong(ctx.LONG().getText()));
            //the selected cursor look at the position x and y
            cursorManager.getSelectedCursor().lookAt(cursorToLookAt.getPosX(),cursorToLookAt.getPosY());
        }
        //if a position was given
        else{
            x = (int) visit(ctx.numStatementParameterX());
            y = (int) visit(ctx.numStatementParameterY());
            cursorManager.getSelectedCursor().lookAt(x,y);
        }
        return VOID;
    }

    /**
     * Visit a hide statement.
     * @param ctx the parse tree
     * @return Hides a cursor.
     */
    @Override
    public Object visitHideStatement(LYnkParser.HideStatementContext ctx){
        cursorManager.hide();
        return VOID;
    }

    /**
     * Visit a show statement.
     * @param ctx the parse tree
     * @return Shows a cursor.
     */
    @Override
    public Object visitShowStatement(LYnkParser.ShowStatementContext ctx){
        cursorManager.show();
        return VOID;
    }

    /**
     * Visit a rotation statement.
     * @param ctx the parse tree
     * @return Rotates a cursor.
     */
    @Override
    public Object visitRotationStatement(LYnkParser.RotationStatementContext ctx){
        Object value = visit(ctx.arithmeticExpression());
        cursorManager.turn((float) value);
        return VOID;
    }

    /**
     * Visit a boolean declaration.
     * @param ctx the parse tree
     * @return Creates the boolean variable.
     */
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

    /**
     * Visit a string declaration.
     * @param ctx the parse tree
     * @return Creates the string variable.
     */
    @Override
    public Object visitStringDeclaration(LYnkParser.StringDeclarationContext ctx) {
        if(ctx.LITERAL().getText().isEmpty()) {
            variableList.setStrVarValue(ctx.IDENTIFICATION().getText(),"" );
        }
        else {
            variableList.setStrVarValue(ctx.IDENTIFICATION().getText(), ctx.LITERAL());
        }
        return VOID;
    }

    /**
     * Visit a number declaration.
     * @param ctx the parse tree
     * @return Creates the numeral variable.
     */
    @Override
    public Object visitNumberDeclaration(LYnkParser.NumberDeclarationContext ctx) {
        if(ctx.arithmeticExpression().isEmpty()) {
            variableList.setNumVarValue(ctx.IDENTIFICATION().getText(),0 );
        }
        else {
            variableList.setNumVarValue(ctx.IDENTIFICATION().getText(), ctx.arithmeticExpression());
        }
        return VOID;
    }

    /**
     * Visit a delete declaration.
     * @param ctx the parse tree
     * @return Remove the variable.
     */
    @Override
    public Object visitDeleteDeclaration(LYnkParser.DeleteDeclarationContext ctx){
        try {
            variableList.delete(ctx.IDENTIFICATION().getText());
        } catch (VariableDoesNotExistException e) {
            throw new RuntimeException(e);
        }
        return VOID;
    }

    /**
     * Verify if the object passed in argument is a number.
     * @param o The object.
     * @return The object if it is a number or an error.
     */
    private Object shouldBeNumber(final Object o){
        if (o instanceof Number ){
            return  o;
        }
        console.addLine("Expected a number but found :" + getTypeName(o));
        throw new IllegalStateException("Expected a number but found :" + getTypeName(o));
    }

    /**
     * Verify if the object passed in argument is a string.
     * @param o The object.
     * @return The object if it is a string or an error.
     */
    private Object shouldBeString(final Object o){
        if (o instanceof String ){
            return  o;
        }
        console.addLine("Expected a string but found :" + getTypeName(o));
        throw new IllegalStateException("Expected a string but found :" + getTypeName(o));
    }

    /**
     * Verify if the object passed in argument is a boolean.
     * @param o The object.
     * @return The object if it is a boolean or an error.
     */
    private Object shouldBeBoolean(final Object o){
        if(o instanceof Boolean){
            return o;
        }
        console.addLine("Expected a boolean but found : " + getTypeName(o));
        throw new IllegalStateException("Expected a boolean but found : " + getTypeName(o));
    }

    /**
     * Return the type of the object.
     * @param o The object.
     * @return The type of the object passed in argument.
     */
    private static String getTypeName(final Object o){
        return o != null ? o.getClass().getSimpleName() : "null";
    }
}
