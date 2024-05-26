package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

/**
 * A FOR statement's representation
 */
public class ForStatement extends Statement{
    private String variableName;
    private int from;
    private int to;
    private int step;
    private BlockStatement block;

    /**
     * Constructor of ForStatement
     * @param variableName Name of the variable
     * @param from Variable the for start from
     * @param to Variable the for goes to
     * @param step Variable the for uses as step
     * @param block The block statement
     * @param varContext The variable context
     */
    public ForStatement(String variableName, int from, int to, int step, BlockStatement block, LYnkVariableImpl varContext){
        super("FOR", varContext);
        this.variableName = variableName;
        this.from = from;
        this.to = to;
        this.step = step;
        this.block = block;
    }

    public String getVariableName() {
        return variableName;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getStep() {
        return step;
    }

    public BlockStatement getBlock() {
        return block;
    }
    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + " " + this.variableName + " FROM " + this.from + " TO " + this.to + " STEP " + this.step + " " + this.block.toString() + "\n";
    }
}
