package chromatynk.chromatynk_g6;

import chromatynk.chromatynk_g6.exceptions.InvalidNameException;
import chromatynk.chromatynk_g6.exceptions.variableExceptions.InvalidVariableTypeException;
import chromatynk.chromatynk_g6.exceptions.variableExceptions.VariableDoesNotExistException;

import java.util.HashMap;
import java.util.Map;

public class VariableManager {
    /**
     * Array of the current Variable
     */
    private Map<String, Variable> variableArray;

    /**
     * List of the illegal variable name
     */
    private final String[] banList = {"FWD", "BWD", "TURN", "MOV", "POS", "HIDE", "SHOW", "PRESS", "COLOR", "THICK",
            "LOOKAT", "CURSOR", "SELECT", "REMOVE", "IF", "FOR", "WHILE", "MIMIC", "MIRROR", "NUM", "STR", "BOOL",
            "DEL", "FROM", "TO", "STEP", "AND", "OR", "NOT"};

    /**
     * Constructor of CommandVariable
     */
    public VariableManager(){
        this.variableArray = new HashMap<>();
    }

    /**
     * Delete the variable name
     * @param name String name of the variable
     * @throws VariableDoesNotExistException the variable name does not exist
     */
    public void delete(String name) throws VariableDoesNotExistException{
        //If the variable does not exist
        if(!variableArray.containsKey(name)) throw new VariableDoesNotExistException("Variable " + name + " does not exist");
        //If the variable exist we remove it
        else{
            variableArray.remove(name);
        }
    }

    /**
     * Return if name is an illegal variable name
     * @param name String name to verify
     * @return boolean true if name is illegal
     */
    private boolean isIllegal(String name){
        for(String illegal : banList){
            if(name.equals(illegal)){
                return true;
            }
        }
        return false;
    }

    /**
     * Add the variable name of String value
     * @param name String name of the variable
     * @param value String value of variable
     * @throws InvalidNameException the variable name is not allowed
     */
    public void addVariableString(String name, String value) throws InvalidNameException {
        //Throws an error if the name is illegal
        if(this.isIllegal(name)) throw new InvalidNameException("Variable name illegal");
        VariableSTR var = new VariableSTR(value);
        variableArray.put(name, var);
    }

    /**
     * Add the variable String as an empty chain as default
     * @param name String name of the variable
     * @throws InvalidNameException the variable name is not allowed
     */
    public void addVariableString(String name) throws InvalidNameException {
        addVariableString(name,"");
    }

    /**
     * Add the variable name of double value
     * @param name String name of the variable
     * @param value double value of variable
     * @throws InvalidNameException the variable name is not allowed
     */
    public void addVariableDouble(String name, double value) throws InvalidNameException{
        //Throws an error if the name si illegal
        if(this.isIllegal(name)) throw new InvalidNameException("Variable name illegal");
        VariableDOUBLE var = new VariableDOUBLE(value);
        variableArray.put(name, var);
    }

    /**
     * Add the variable String as a 0 by default
     * @param name String name of the variable
     * @throws InvalidNameException the variable name is not allowed
     */
    public void addVariableDouble(String name) throws InvalidNameException{
        addVariableDouble(name,0);
    }

    /**
     * Add the variable name of boolean value
     * @param name String name of the variable
     * @param value boolean value of variable
     * @throws InvalidNameException the variable name is not allowed
     */
    public void addVariableBool(String name, boolean value) throws InvalidNameException{
        //Throws an error if the name si illegal
        if(this.isIllegal(name)) throw new InvalidNameException("Variable name illegal");
        VariableBOOL var = new VariableBOOL(value);
        variableArray.put(name, var);
    }

    /**
     * Add the variable name of boolean value to false as default
     * @param name String name of the variable
     * @throws InvalidNameException the variable name is not allowed
     */
    public void addVariableBool(String name) throws InvalidNameException{
        addVariableBool(name,false);
    }

    /**
     * Change the value of name String variable
     * @param name String name of the variable
     * @param value String variable value
     * @throws VariableDoesNotExistException the variable name does not exist
     * @throws InvalidVariableTypeException the variable name is not a String variable
     */
    public void setVariableString(String name, String value) throws VariableDoesNotExistException, InvalidVariableTypeException {
        //If the variable does not exist
        if(!variableArray.containsKey(name)) throw new VariableDoesNotExistException("The variable " + name + " does not exist");
        //If the variable exist
        Variable var = variableArray.get(name);
        //If the variable is not a String variable
        if(!(var instanceof VariableSTR)){
            throw new InvalidVariableTypeException("The variable " + name + " is not a String variable");
        }
        //Change the value of name variable
        ((VariableSTR) var).setValue(value);
    }

    /**
     * Change the value of name Double variable
     * @param name String name of the variable
     * @param value double variable value
     * @throws VariableDoesNotExistException the variable name does not exist
     * @throws InvalidVariableTypeException the variable name is not a Double variable
     */
    public void setVariableDouble(String name, double value) throws VariableDoesNotExistException, InvalidVariableTypeException{
        //If the variable does not exist
        if(!variableArray.containsKey(name)) throw new VariableDoesNotExistException("The variable " + name + " does not exist");
        //If the variable exist
        Variable var = variableArray.get(name);
        //If the variable is not a double variable
        if(!(var instanceof VariableDOUBLE)){
            throw new InvalidVariableTypeException("The variable " + name + " is not a double variable");
        }
        //Change the value of name variable
        ((VariableDOUBLE) var).setValue(value);
    }


    /**
     * Change the value of name boolean variable
     * @param name String name of the variable
     * @param value boolean variable value
     * @throws VariableDoesNotExistException the variable name does not exist
     * @throws InvalidVariableTypeException the variable name is not a boolean variable
     */
    public void setVariableBool(String name, Boolean value) throws VariableDoesNotExistException, InvalidVariableTypeException{
        //If the variable does not exist
        if(!variableArray.containsKey(name)) throw new VariableDoesNotExistException("The variable " + name + " does not exist");
        //If the variable exist
        Variable var = variableArray.get(name);
        //If the variable is not a boolean variable
        if(!(var instanceof VariableBOOL)){
            throw new InvalidVariableTypeException("The variable " + name + " is not a boolean variable");
        }
        //Change the value of name variable
        ((VariableBOOL) var).setValue(value);
    }

    /**
     * Return the value of a String variable
     * @param name String name of the variable
     * @return String of variable
     * @throws VariableDoesNotExistException the variable name does not exist
     * @throws InvalidVariableTypeException the variable name is not a String variable
     */
    public String getVariableString(String name) throws VariableDoesNotExistException, InvalidVariableTypeException{
        //If the variable does not exist
        if(!variableArray.containsKey(name)) throw new VariableDoesNotExistException("The variable " + name + " does not exist");
        //If the variable exist
        Variable var = variableArray.get(name);
        //If the variable is not a String variable
        if(!(var instanceof VariableSTR)){
            throw new InvalidVariableTypeException("The variable " + name + " is not a String variable");
        }
        return ((VariableSTR) var).getValue();
    }

    /**
     * Return the value of a double variable
     * @param name String name of the variable
     * @return double of variable
     * @throws VariableDoesNotExistException the variable name does not exist
     * @throws InvalidVariableTypeException the variable name is not a double variable
     */
    public double getVariableDouble(String name) throws VariableDoesNotExistException, InvalidVariableTypeException{
        //If the variable does not exist
        if(!variableArray.containsKey(name)) throw new VariableDoesNotExistException("The variable " + name + " does not exist");
        //If the variable exist
        Variable var = variableArray.get(name);
        //If the variable is not a double variable
        if(!(var instanceof VariableDOUBLE)){
            throw new InvalidVariableTypeException("The variable " + name + " is not a double variable");
        }
        return ((VariableDOUBLE) var).getValue();
    }

    /**
     * Return the value of a boolean variable
     * @param name String name of the variable
     * @return boolean value of name variable
     * @throws VariableDoesNotExistException the variable name does not exist
     * @throws InvalidVariableTypeException the variable name is not a boolean variable
     */
    public boolean getVariableBool(String name) throws VariableDoesNotExistException, InvalidVariableTypeException{
        //If the variable does not exist
        if(!variableArray.containsKey(name)) throw new VariableDoesNotExistException("The variable " + name + " does not exist");
        //If the variable exist
        Variable var = variableArray.get(name);
        //If the variable is not a boolean variable
        if(!(var instanceof VariableBOOL)){
            throw new InvalidVariableTypeException("The variable " + name + " is not a boolean variable");
        }
        return ((VariableBOOL) var).getValue();
    }

    /**
     * Apply and on two boolean variable
     * @param name1 String name of the first boolean variable
     * @param name2 String name of the second boolean variable
     * @return boolean of name1 and name2
     * @throws VariableDoesNotExistException the variable name does not exist
     * @throws InvalidVariableTypeException the variable name is not a boolean variable
     */
    public boolean andVariable(String name1, String name2) throws VariableDoesNotExistException, InvalidVariableTypeException{
        //If one of the variable does not exist
        if(!variableArray.containsKey(name1)) throw new VariableDoesNotExistException("The variable " + name1 + " does not exist");
        if(!variableArray.containsKey(name2)) throw new VariableDoesNotExistException("The variable " + name2 + " does not exist");
        //If the variables exist
        Variable var1 = variableArray.get(name1);
        Variable var2 = variableArray.get(name2);
        //If the variable 1 is not boolean
        if(!(var1 instanceof VariableBOOL)){
            throw new InvalidVariableTypeException("The variable " + name1 + " is not a boolean variable");
        }
        //If the variable 2 is not boolean
        if(!(var2 instanceof VariableBOOL)){
            throw new InvalidVariableTypeException("The variable " + name2 + " is not a boolean variable");
        }
        return ((VariableBOOL) var1).getValue() && ((VariableBOOL) var2).getValue();
    }

    /**
     * Apply or on two boolean variable
     * @param name1 String name of the first boolean variable
     * @param name2 String name of the second boolean variable
     * @return boolean of name1 or name2
     * @throws VariableDoesNotExistException the variable does not exist
     * @throws InvalidVariableTypeException the variable name is not a boolean variable
     */
    public boolean orVariable(String name1, String name2) throws VariableDoesNotExistException, InvalidVariableTypeException{
        //If one of the variable does not exist
        if(!variableArray.containsKey(name1)) throw new VariableDoesNotExistException("The variable " + name1 + " does not exist");
        if(!variableArray.containsKey(name2)) throw new VariableDoesNotExistException("The variable " + name2 + " does not exist");
        //If the variables exist
        Variable var1 = variableArray.get(name1);
        Variable var2 = variableArray.get(name2);
        //If the variable 1 is not boolean
        if(!(var1 instanceof VariableBOOL)){
            throw new InvalidVariableTypeException("The variable " + name1 + " is not a boolean variable");
        }
        //If the variable 2 is not boolean
        if(!(var2 instanceof VariableBOOL)){
            throw new InvalidVariableTypeException("The variable " + name2 + " is not a boolean variable");
        }
        return ((VariableBOOL) var1).getValue() || ((VariableBOOL) var2).getValue();
    }

    /**
     * Apply not to a boolean variable
     * @param name String name of the variable
     * @return boolean of not name
     * @throws VariableDoesNotExistException the variable does not exist
     * @throws InvalidVariableTypeException the variable name is not a boolean variable
     */
    public boolean notVariable(String name) throws VariableDoesNotExistException, InvalidVariableTypeException{
        //If the variable does not exist
        if(!variableArray.containsKey(name)) throw new VariableDoesNotExistException("The variable " + name + " does not exist");
        //If the variables exist
        Variable var = variableArray.get(name);
        //If the variable is not boolean
        if(!(var instanceof VariableBOOL)){
            throw new InvalidVariableTypeException("The variable " + name + " is not a boolean variable");
        }
        return !((VariableBOOL) var).getValue();
    }
}
