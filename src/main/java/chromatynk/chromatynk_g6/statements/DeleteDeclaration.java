package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

public class DeleteDeclaration extends Statement{
    private String identification;
    public DeleteDeclaration(String identification, LYnkVariableImpl varContext){
        super("REMOVE", varContext);
        this.identification = identification;
    }

    public String getIdentification() {
        return this.identification;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.identification;
    }
}
