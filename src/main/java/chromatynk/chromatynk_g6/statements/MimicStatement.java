package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

public class MimicStatement extends Statement{
    private long cursorId;
    public MimicStatement(long cursorId, LYnkVariableImpl varContext){
        super("MIMIC", varContext);
        this.cursorId = cursorId;
    }

    public long getCursorId() {
        return cursorId;
    }
}
