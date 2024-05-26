package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;


/**
 * A REMOVE statement's representation
 */
public class RemoveStatement extends Statement{
    private long cursorId;

    /**
     * Constructor of RemoveStatement
     * @param cursorId The cursor id
     * @param varContext The variable context
     */
    public RemoveStatement(long cursorId, LYnkVariableImpl varContext){
        super("REMOVE", varContext);
        this.cursorId = cursorId;
    }

    public long getCursorId() {
        return cursorId;
    }

    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + " " + this.cursorId + " \n";
    }
}
