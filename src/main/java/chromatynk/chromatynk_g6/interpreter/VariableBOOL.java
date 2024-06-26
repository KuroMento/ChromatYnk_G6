package chromatynk.chromatynk_g6.interpreter;

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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof VariableBOOL){
            return this.value == ((VariableBOOL) obj).value;
        }
        return false;
    }

    @Override
    public String toString(){
        return(String.valueOf(this.value).toUpperCase());
    }
}
