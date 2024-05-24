package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

public class ForStatement extends Statement{
    private long variableId;
    private int from;
    private int to;
    private int step;
    private BlockStatement block;
    public ForStatement(long variableId, int from, int to, int step, BlockStatement block, LYnkVariableImpl varContext){
        super("FOR", varContext);
        this.variableId = variableId;
        this.from = from;
        this.to = to;
        this.step = step;
        this.block = block;
    }

    public long getVariableId() {
        return variableId;
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
