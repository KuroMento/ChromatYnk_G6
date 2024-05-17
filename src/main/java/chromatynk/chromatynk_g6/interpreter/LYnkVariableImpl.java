package chromatynk.chromatynk_g6.interpreter;

import chromatynk.chromatynk_g6.Variable;
import chromatynk.chromatynk_g6.VariableDOUBLE;
import chromatynk.chromatynk_g6.VariableSTR;
import chromatynk.chromatynk_g6.VariableBOOL;
import chromatynk.chromatynk_g6.exceptions.variableExceptions.VariableDoesNotExistException;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class LYnkVariableImpl implements LYnkVariable{

    private final PrintStream stdout;

    private Map<String, Variable> variableMap;

    /**
     * List of the illegal variable name
     */
    private final String[] banList = {"FWD", "BWD", "TURN", "MOV", "POS", "HIDE", "SHOW", "PRESS", "COLOR", "THICK",
            "LOOKAT", "CURSOR", "SELECT", "REMOVE", "IF", "FOR", "WHILE", "MIMIC", "MIRROR", "NUM", "STR", "BOOL",
            "DEL", "FROM", "TO", "STEP", "AND", "OR", "NOT", "TRUE", "FALSE"};

    public  LYnkVariableImpl(final PrintStream stdout){
        this.stdout = stdout;
        this.variableMap = new HashMap<>();
    }

    /**
     * Return if name is an illegal variable name
     * @param name String name to verify
     * @return boolean true if name is legal
     */
    private boolean isLegal(String name){
        for(String illegal : banList){
            if(name.equals(illegal)){
                return false;
            }
        }
        return true;
    }

    /**
     * Delete the variable identifier
     * @param name the variable name
     * @throws VariableDoesNotExistException the variable name does not exist
     */
    @Override
    public void delete(String name) throws VariableDoesNotExistException{
        //If the variable does not exist
        if(!variableMap.containsKey(name)) throw new VariableDoesNotExistException("Variable " + name + " does not exist");
        //If the variable exist we remove it
        else{
            variableMap.remove(name);
        }
    }

    @Override
    public Object getVarValue(final TerminalNode identifier) throws VariableDoesNotExistException{
        if(variableMap.containsKey(identifier.getText())){
            Variable var = variableMap.get(identifier.getText());
            if(var instanceof VariableSTR){
                return (((VariableSTR) var).getValue());
            }
            if(var instanceof VariableBOOL){
                return (((VariableBOOL) var).getValue());
            }
            if(var instanceof VariableDOUBLE){
                return (((VariableDOUBLE) var).getValue());
            }
        }
        throw new VariableDoesNotExistException();
    }

    @Override
    public Object getVarValue(String name) throws VariableDoesNotExistException{
        if(variableMap.containsKey(name)){
            Variable var = variableMap.get(name);
            if(var instanceof VariableSTR){
                return (((VariableSTR) var).getValue());
            }
            if(var instanceof VariableBOOL){
                return (((VariableBOOL) var).getValue());
            }
            if(var instanceof VariableDOUBLE){
                return (((VariableDOUBLE) var).getValue());
            }
        }
        throw new VariableDoesNotExistException();
    }


    /**
     * Get the value of a String variable
     * @param identifier the variable
     * @return String value
     * @throws VariableDoesNotExistException the variable does not exist
     */
    @Override
    public String getStrVarValue(final TerminalNode identifier) throws VariableDoesNotExistException{
        if(variableMap.containsKey(identifier.getText())) {
            return ((VariableSTR) variableMap.get(identifier.getText())).getValue();
        }
        throw new VariableDoesNotExistException();
    }

    /**
     * Get the value of a String variable
     * @param name the String variable name
     * @return String value
     * @throws VariableDoesNotExistException the variable does not exist
     */
    @Override
    public String getStrVarValue(String name) throws VariableDoesNotExistException{
        if(variableMap.containsKey(name)) {
            return ((VariableSTR) variableMap.get(name)).getValue();
        }
        throw new VariableDoesNotExistException();
    }
    /**
     * Get the value of a boolean variable
     * @param identifier the boolean variable
     * @return boolean value
     * @throws VariableDoesNotExistException the variable does not exist
     */
    @Override
    public Boolean getBoolVarValue(final TerminalNode identifier) throws VariableDoesNotExistException{
        if(variableMap.containsKey(identifier.getText())) {
            return ((VariableBOOL) variableMap.get(identifier.getText())).getValue();
        }
        throw new VariableDoesNotExistException();
    }

    /**
     * Get the value of a boolean variable
     * @param name the boolean variable name
     * @return boolean value
     * @throws VariableDoesNotExistException the variable does not exist
     */
    @Override
    public Boolean getBoolVarValue(String name) throws VariableDoesNotExistException{
        if(variableMap.containsKey(name)) {
            return ((VariableBOOL) variableMap.get(name)).getValue();
        }
        throw new VariableDoesNotExistException();
    }
    /**
     * Get the value of a double variable
     * @param identifier the double variable
     * @return double value
     * @throws VariableDoesNotExistException the variable does not exist
     */
    @Override
    public Double getNumVarValue(final TerminalNode identifier) throws VariableDoesNotExistException{
        if(variableMap.containsKey(identifier.getText())) {
            return ((VariableDOUBLE) variableMap.get(identifier.getText())).getValue();
        }
        throw new VariableDoesNotExistException();
    }
    /**
     * Get the value of a double variable
     * @param name the double variable name
     * @return double value
     * @throws VariableDoesNotExistException the variable does not exist
     */
    @Override
    public Double getNumVarValue(String name) throws VariableDoesNotExistException{
        if(variableMap.containsKey(name)) {
            return ((VariableDOUBLE) variableMap.get(name)).getValue();
        }
        throw new VariableDoesNotExistException();
    }

    /**
     * Get the type of variable identifier
     * @param identifier the variable to check
     * @return the type of the variable
     * @throws VariableDoesNotExistException the variable does not exist
     */
    @Override
    public Object getVarType(final TerminalNode identifier) throws VariableDoesNotExistException{
        if(variableMap.containsKey(identifier.getText())){
            Variable var = variableMap.get(identifier.getText());
            if(var instanceof VariableSTR){
                return new String();
            }
            if(var instanceof VariableBOOL){
                return Boolean.TRUE;
            }
            if(var instanceof VariableDOUBLE){
                return 0d;
            }
        }
        throw new VariableDoesNotExistException();
    }
    /**
     * Get the type of variable identifier
     * @param name String name of the variable to check
     * @return the type of the variable
     * @throws VariableDoesNotExistException the variable does not exist
     */
    @Override
    public Object getVarType(String name) throws VariableDoesNotExistException{
        if(variableMap.containsKey(name)){
            Variable var = variableMap.get(name);
            if(var instanceof VariableSTR){
                return new String();
            }
            if(var instanceof VariableBOOL){
                return Boolean.TRUE;
            }
            if(var instanceof VariableDOUBLE){
                return 0d;
            }
        }
        throw new VariableDoesNotExistException();
    }

    /**
     * Verify if the variable exist
     * @param name the variable name
     * @return boolean if the variable exist
     */
    @Override
    public boolean hasVar(String name){
        return variableMap.containsKey(name);
    }

    /**
     * Set the value of a string variable
     * @param name the variable name
     * @param value the value of the variable
     */
    @Override
    public boolean setStrVarValue(String name, final Object value){
        if(isLegal(name)){
            variableMap.put(name,new VariableSTR((String) value));
            return true;
        }
        return false;
    }

    /**
     * Set the value of a boolean variable
     * @param name the variable name
     * @param value the value of the variable
     */
    @Override
    public boolean setBoolVarValue(String name, final Object value){
        if(isLegal(name)){
            variableMap.put(name,new VariableBOOL((Boolean) value));
            return true;
        }
        return false;
    }

    /**
     * Set the value of a double variable
     * @param name the variable name
     * @param value the value of the variable
     */
    @Override
    public boolean setNumVarValue(String name, final Object value){
        if(isLegal(name)){
            variableMap.put(name,new VariableDOUBLE((Double) value));
            return true;
        }
        return false;
    }
}
