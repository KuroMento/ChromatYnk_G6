package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

/**
 * A DEL declaration's representation
 */
public class DeleteDeclaration extends Statement{
    private String identification;

    /**
     * Constructor of DeleteDeclaration
     * @param identification name of the variable
     * @param varContext The variable context
     */
    public DeleteDeclaration(String identification, LYnkVariableImpl varContext){
        super("REMOVE", varContext);
        this.identification = identification;
    }

    public String getIdentification() {
        return this.identification;
    }
    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + " " + this.identification + "\n";
    }
}
