package chromatynk.chromatynk_g6;

public final class VariableBOOL extends Variable{
    //Boolean value of the variable
    private boolean value;

    /**
     * Constructor of VariableBool
     * @param value set a boolean as the value of the variable
     */
    public VariableBOOL(Boolean value){
        this.value = value;
    }

    public boolean getValue(){
        return value;
    }

    public void setValue(boolean value){
        this.value = value;
    }
}
