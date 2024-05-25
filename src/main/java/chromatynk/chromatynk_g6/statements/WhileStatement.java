package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.booleanExp.BooleanExpression;

public class WhileStatement extends Statement{
    private BooleanExpression expression;
    private BlockStatement block;

    public WhileStatement(BooleanExpression expression, BlockStatement block, LYnkVariableImpl varContext){
        super("WHILE", varContext);
        this.expression = expression;
        this.block = block;
    }

    public BooleanExpression getExpression() {
        return expression;
    }

    public BlockStatement getBlock() {
        return block;
    }

    @Override
    public String toString(){
        return "WHILE " + expression.toString() + " " + block.toString();
    }
    /*
    public void execute(){
        while(expression.evaluate()){
            block.setVarContext(this.getVarContext());
            block.execute();
            this.setVarContext(block.getVarContext());
        }
    }*/
}
