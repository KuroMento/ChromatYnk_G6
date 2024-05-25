package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

public class ForStatement extends Statement{
    private String variableName;
    private int from;
    private int to;
    private int step;
    private BlockStatement block;
    public ForStatement(String variableName, int from, int to, int step, BlockStatement block, LYnkVariableImpl varContext){
        super("FOR", varContext);
        this.variableName = variableName;
        this.from = from;
        this.to = to;
        this.step = step;
        this.block = block;
    }

    public String getVariableName() {
        return variableName;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getStep() {
        return step;
    }

    public BlockStatement getBlock() {
        return block;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.variableName + " FROM " + this.from + " TO " + this.to + " STEP " + this.step + " " + this.block.toString();
    }

    /*
    public void execute(){
        if(to >= from && step > 0){
            for(int i = from; i < to; i = i + step){
                this.setVarContext(this.getVarContext());
                block.setVarContext(this.getVarContext());
                block.execute();
                this.setVarContext(block.getVarContext());
            }
        }
        if(to < from && step < 0){
            for(int i = from; i > to; i = i + step){
                block.setVarContext(this.getVarContext());
                block.execute();
                this.setVarContext(block.getVarContext());
            }
        }
    }*/
}
