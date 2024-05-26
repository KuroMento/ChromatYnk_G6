package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

/**
 * A HIDE statement's representation
 */
public class HideStatement extends Statement{
    public HideStatement(LYnkVariableImpl varContext){
        super("HIDE", varContext);
    }

    @Override
    public String toString() {
        return super.toString() + "\n";
    }
}
