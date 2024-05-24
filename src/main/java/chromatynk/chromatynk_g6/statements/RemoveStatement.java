package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

public class RemoveStatement extends Statement{
    private long cursorId;
    public RemoveStatement(long cursorId, LYnkVariableImpl varContext){
        super("REMOVE", varContext);
        this.cursorId = cursorId;
    }

    public long getCursorId() {
        return cursorId;
    }
}
