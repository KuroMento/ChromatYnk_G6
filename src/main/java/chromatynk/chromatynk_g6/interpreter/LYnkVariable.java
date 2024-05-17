package chromatynk.chromatynk_g6.interpreter;

import chromatynk.chromatynk_g6.exceptions.variableExceptions.VariableDoesNotExistException;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.PrintStream;

public interface LYnkVariable {
    boolean hasVar(String name);
    void delete(String name) throws VariableDoesNotExistException;
    Object getVarValue(final TerminalNode identifier) throws VariableDoesNotExistException;
    Object getVarValue(String name) throws VariableDoesNotExistException;
    String getStrVarValue(final TerminalNode identifier) throws VariableDoesNotExistException;
    String getStrVarValue(String name) throws VariableDoesNotExistException;
    boolean setStrVarValue(String name, final Object value);
    Boolean getBoolVarValue(final TerminalNode identifier) throws VariableDoesNotExistException;
    Boolean getBoolVarValue(String name) throws VariableDoesNotExistException;
    boolean setBoolVarValue(String name, final Object value);
    Double getNumVarValue(final TerminalNode identifier) throws VariableDoesNotExistException;
    Double getNumVarValue(String name) throws VariableDoesNotExistException;
    Object getVarType(final TerminalNode identifier) throws VariableDoesNotExistException;
    Object getVarType(String name) throws VariableDoesNotExistException;
    boolean setNumVarValue(String name, final Object value);

}
