package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

public abstract class Statement {
    private String keyword;

    private LYnkVariableImpl varContext;

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

    public void execute(){}
}
