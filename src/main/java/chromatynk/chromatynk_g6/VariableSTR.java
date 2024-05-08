package chromatynk.chromatynk_g6;

public final class VariableSTR extends Variable{
    //String value of the variable
    private String value;

    public VariableSTR(String name, String value){
        super(name);
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
