package chromatynk.chromatynk_g6;

public final class VariableDOUBLE extends Variable{
    //Double value of the variable
    private double value;

    /**
     * Default constructor of VariableDouble setting the value at 0
     */
    public VariableDOUBLE(){
        this.value = 0;
    }

    /**
     * Constructor of VariableDouble
     * @param value set a double as the value of the variable
     */
    public VariableDOUBLE(double value){
        this.value = value;
    }

    public double getValue(){
        return value;
    }

    public void setValue(double value){
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if( obj instanceof VariableDOUBLE){
            return this.value == ((VariableDOUBLE) obj).value;
        }
        return false;
    }
}
