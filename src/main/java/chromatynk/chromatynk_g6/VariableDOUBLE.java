package chromatynk.chromatynk_g6;

public final class VariableDOUBLE extends Variable{
    //Double value of the variable
    private double value;

    public VariableDOUBLE(String name){
        super(name);
        this.value = 0;
    }
    public VariableDOUBLE(String name, double value){
        super(name);
        this.value = value;
    }
}
