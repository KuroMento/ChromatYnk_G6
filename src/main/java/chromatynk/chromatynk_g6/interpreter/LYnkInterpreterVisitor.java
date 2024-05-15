package chromatynk.chromatynk_g6.interpreter;

import chromatynk.chromatynk_g6.Behaviour;
import chromatynk.chromatynk_g6.Console;
import chromatynk.chromatynk_g6.CursorManager;
import chromatynk.chromatynk_g6.LYnk.LYnkBaseVisitor;
import chromatynk.chromatynk_g6.LYnk.LYnkLexer;
import chromatynk.chromatynk_g6.LYnk.LYnkParser;
import chromatynk.chromatynk_g6.VariableManager;

import java.util.ArrayDeque;
import java.util.LinkedList;

public class LYnkInterpreterVisitor extends LYnkBaseVisitor<Object> {

    private Console console;

    public LYnkInterpreterVisitor(){
        //this.cursorManager = new CursorManager();
        //this.varList = new VariableManager();
        this.console = new Console();
        //this.behaviour = Behaviour.DIRECT;
        //this.instructions = new ArrayDeque<>();
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
    public Object visitForStatement(final LYnkParser.ForStatementContext ctx){
        return VOID;
    }
    @Override
    public Object visitParenthesisVar(final LYnkParser.ParenthesisVarContext ctx){
        return visit(ctx.booleanExpression());
    }
    @Override
    public Object visitParenthesisExpression(final LYnkParser.ParenthesisExpressionContext ctx){
        return visit(ctx.arithmeticExpression());
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
    public Object visitAndOrExpression(LYnkParser.AndOrExpressionContext ctx){ //method needs to be completed
        final Object conditionLeft = visit(ctx.left);
    }

    @Override
    public Object visitTrueVar(LYnkParser.TrueVarContext ctx) {
        return Boolean.TRUE;
    }
    @Override
    public Object visitFalseVar(LYnkParser.FalseVarContext ctx){
        return Boolean.FALSE;
    }
}
