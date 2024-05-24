package chromatynk.chromatynk_g6.interpreter;

public final class VariableSTR extends Variable{
    //String value of the variable
    private String value;

    /**
     * Constructor of VariableSTR
     * @param value set a String as the value of the variable
     */
    public VariableSTR(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof VariableSTR){
            return this.value.equals(((VariableSTR) obj).getValue());
        }
        return false;
    }

    @Override
    public String toString(){
        return(this.value);
    }
}
