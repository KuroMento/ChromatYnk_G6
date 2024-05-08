package chromatynk.chromatynk_g6;

public abstract class Variable {
    // Name used in the interpreter to access the variable
    private String name;

    // Constructor
    public Variable(String name){
        this.name = name;
    }

    // getters/setters
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
}
