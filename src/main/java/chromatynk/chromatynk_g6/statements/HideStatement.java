package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

public class HideStatement extends Statement{
    public HideStatement(LYnkVariableImpl varContext){
        super("HIDE", varContext);
    }
}
