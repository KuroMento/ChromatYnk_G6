package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

public class ShowStatement extends Statement{
    public ShowStatement(LYnkVariableImpl varContext){
        super("SHOW", varContext);
    }


    @Override
    public String toString() {
        return getKeyword();
    }
}