package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;


/**
 * A SHOW statement's representation
 */
public class ShowStatement extends Statement{
    /**
     * Constructor of ShowStatement
     * @param varContext The variable context
     */
    public ShowStatement(LYnkVariableImpl varContext){
        super("SHOW", varContext);
    }

    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString() {
        return getKeyword() + "\n";
    }
}