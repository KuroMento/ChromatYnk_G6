package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.booleanExp.BooleanExpression;

public class IfStatement extends Statement{
    private BooleanExpression expression;
    private BlockStatement block;

    public IfStatement(BooleanExpression expression, BlockStatement block, LYnkVariableImpl varContext){
        super("IF", varContext);
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
    public String toString() {
        return super.toString() + " ( " + this.expression.toString() + " ) " + this.block.toString();
    }

    /*
    public void execute(){
        if(expression.evaluate()){
            block.setVarContext(this.getVarContext());
            block.execute();
            this.setVarContext(block.getVarContext());
        }
    }*/
}
