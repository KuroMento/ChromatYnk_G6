package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

/**
 * A MIMIC statement's representation
 */
public class MimicStatement extends Statement{
    private long cursorId;
    private BlockStatement block;

    /**
     * Constructor of MimicStatement
     * @param cursorId The cursor id
     * @param block The block statement
     * @param varContext The variable context
     */
    public MimicStatement(long cursorId, BlockStatement block, LYnkVariableImpl varContext){
        super("MIMIC", varContext);
        this.cursorId = cursorId;
        this.block = block;
    }

    public long getCursorId() {
        return cursorId;
    }
    public BlockStatement getBlock(){ return this.block; }

    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + " " + this.cursorId + " " + this.block.toString() + "\n";
    }
}
