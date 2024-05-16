package chromatynk.chromatynk_g6.interpreter;

import chromatynk.chromatynk_g6.exceptions.variableExceptions.VariableDoesNotExistException;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.PrintStream;

public interface LYnkVariable {
    boolean hasVar(final TerminalNode identifier);
    void delete(final TerminalNode identifier) throws VariableDoesNotExistException;
    Object getVarValue(final TerminalNode identifier) throws VariableDoesNotExistException;
    String getStrVarValue(final TerminalNode identifier) throws VariableDoesNotExistException;
    void setStrVarValue(final TerminalNode identifier, final Object value);
    Boolean getBoolVarValue(final TerminalNode identifier) throws VariableDoesNotExistException;
    void setBoolVarValue(final TerminalNode identifier, final Object value);
    Double getNumVarValue(final TerminalNode identifier) throws VariableDoesNotExistException;
    void setNumVarValue(final TerminalNode identifier, final Object value);

}
