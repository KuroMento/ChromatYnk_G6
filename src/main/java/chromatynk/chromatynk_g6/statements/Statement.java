package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;


/**
 * A generic statement's representation
 */
public abstract class Statement {
    private String keyword;

    private LYnkVariableImpl varContext;

    /**
     * Constructor of Statement
     * @param keyword The keyword (indicates the instruction)
     * @param varContext The variable context
     */
    public Statement(String keyword, LYnkVariableImpl varContext){
        this.keyword = keyword;
        this.varContext = varContext;
    }


    public String getKeyword(){ return this.keyword; }

    public LYnkVariableImpl getVarContext() {
        return this.varContext;
    }

    public void setVarContext(LYnkVariableImpl varContext) {
        this.varContext = varContext;
    }

    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString() {
        return this.keyword;
    }
}
