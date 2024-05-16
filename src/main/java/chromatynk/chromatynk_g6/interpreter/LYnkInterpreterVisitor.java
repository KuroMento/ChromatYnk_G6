package chromatynk.chromatynk_g6.interpreter;

import chromatynk.chromatynk_g6.Console;
import chromatynk.chromatynk_g6.LYnk.LYnkBaseVisitor;
import chromatynk.chromatynk_g6.LYnk.LYnkParser;
import chromatynk.chromatynk_g6.Variable;
import chromatynk.chromatynk_g6.utils.BooleanUtil;
import chromatynk.chromatynk_g6.utils.NumberUtil;

import static chromatynk.chromatynk_g6.interpreter.LYnkInterpreter.VOID;

public class LYnkInterpreterVisitor extends LYnkBaseVisitor<Object> {

    private Console console;

    public LYnkInterpreterVisitor(){
        super();
        //this.cursorManager = new CursorManager();
        //this.varList = new VariableManager();
        this.console = new Console();
        //this.behaviour = Behaviour.DIRECT;
        //this.instructions = new ArrayDeque<>();
    }
    @Override
    public Object visitProgram(final LYnkParser.ProgramContext ctx){
        return visitChildren(ctx);
    }
    @Override
    public Object visitStringDeclaration(final LYnkParser.StringDeclarationContext ctx){
        // return string literal (pas vraiment!)
        return cleanStringLiteral(ctx.LITERAL().getText());
    }

    private String cleanStringLiteral(final String literal){
        //remove string literal from the char sequence
        return literal.length() > 1 ? literal.substring(1, literal.length() - 1) : literal;
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
            return ValidateInfo(BOOL, condition).asBoolean();
        } else {
            console.addLine("NOT needs a boolean comparison to function");
        }
        return VOID;
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

    @Override
    public Object visitLiteralComparison(LYnkParser.LiteralComparisonContext ctx){
        final Object leftCondition =
        final Object rightCondition =
    }

    @Override
    public Object visitIdentificationVar(LYnkParser.IdentificationVarContext ctx){
        return
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
    public Object visitIdentificationExpression(final LYnkParser.IdentificationExpressionContext ctx){ //to be completed when variables will be available
        return
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
