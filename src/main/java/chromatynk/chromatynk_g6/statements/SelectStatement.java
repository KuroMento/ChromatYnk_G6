package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;


/**
 * A SELECT statement's representation
 */
public class SelectStatement extends Statement{
    private long cursorId;

    /**
     * Constructor of SelectStatement
     * @param cursorId The cursor id
     * @param varContext The variable context
     */
    public SelectStatement(long cursorId, LYnkVariableImpl varContext){
        super("SELECT", varContext);
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
        return super.toString() + " " + this.cursorId + "\n";
    }
}
