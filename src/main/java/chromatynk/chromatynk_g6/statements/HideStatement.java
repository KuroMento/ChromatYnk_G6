package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

/**
 * A HIDE statement's representation
 */
public class HideStatement extends Statement{
    /**
     * Constructor of HideStatement
     * @param varContext The variable context
     */
    public HideStatement(LYnkVariableImpl varContext){
        super("HIDE", varContext);
    }

    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + "\n";
    }
}
