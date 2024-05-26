package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

/**
 * A CURSOR statement's representation
 */
public class CursorStatement extends Statement{
    private long cursorId;

    /**
     * Constructor of CursorStatement
     * @param cursorId The cursor id
     * @param varContext The variable context
     */
    public CursorStatement(long cursorId, LYnkVariableImpl varContext){
        super("CURSOR", varContext);
        this.cursorId = cursorId;
    }

    public long getCursorId() {
        return this.cursorId;
    }

    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + " " + cursorId + "\n";
    }
}
