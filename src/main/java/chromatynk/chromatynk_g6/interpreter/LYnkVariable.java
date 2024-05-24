package chromatynk.chromatynk_g6.interpreter;

import chromatynk.chromatynk_g6.exceptions.variableExceptions.VariableDoesNotExistException;

import java.util.Map;

public interface LYnkVariable {
    boolean hasVar(String name);
    void delete(String name) throws VariableDoesNotExistException;
    Object getVarValue(String name) throws VariableDoesNotExistException;
    String getStrVarValue(String name) throws VariableDoesNotExistException;
    boolean setStrVarValue(String name, final Object value);
    Boolean getBoolVarValue(String name) throws VariableDoesNotExistException;
    boolean setBoolVarValue(String name, final Object value);
    Double getNumVarValue(String name) throws VariableDoesNotExistException;
    Object getVarType(String name) throws VariableDoesNotExistException;
    Map<String, Variable> getVariableMap();
    boolean setNumVarValue(String name, final Object value);

}
