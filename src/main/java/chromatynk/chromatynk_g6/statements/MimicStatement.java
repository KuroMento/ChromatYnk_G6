package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

public class MimicStatement extends Statement{
    private long cursorId;
    private BlockStatement block;
    public MimicStatement(long cursorId, BlockStatement block, LYnkVariableImpl varContext){
        super("MIMIC", varContext);
        this.cursorId = cursorId;
        this.block = block;
    }

    public long getCursorId() {
        return cursorId;
    }
    public BlockStatement getBlock(){ return this.block; }

    @Override
    public String toString() {
        return super.toString() + " " + this.cursorId + " " + this.block.toString();
    }
}