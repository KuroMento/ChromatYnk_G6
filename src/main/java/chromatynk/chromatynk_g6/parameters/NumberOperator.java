package chromatynk.chromatynk_g6.parameters;

import chromatynk.chromatynk_g6.interpreter.LYnkConsole;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticExpression;
import chromatynk.chromatynk_g6.parameters.booleanExp.BooleanExpression;

/**
 * An enumeration for arithmetic operations between <code>{@link ArithmeticExpression}</code>.
 */
public enum NumberOperator {
    VOID(0),
    PLUS(45),
    MINUS(46),
    MULTIPLICATION(43),
    DIVISION(44);

    private final int index;

    /** constructor
     *
     * @param index
     */
    NumberOperator(int index){
        this.index = index;
    }


    public int getIndex(){
        return this.index;
    }

    /**
     * Used to get back the operator from the ctx in visit methods in <code>{@link LYnkConsole}</code> to evaluate an <code>{@link ArithmeticExpression}</code>.
     * @param index The ctx.op.getType() in <code>{@link LYnkConsole}</code> methods.
     * @return An <code>{@link NumberOperator}</code>.
     */
    public static NumberOperator getOp(int index){
        switch (index){
            case 45: return PLUS;
            case 46: return MINUS;
            case 43: return MULTIPLICATION;
            case 44: return DIVISION;
            default: return VOID;
        }
    }
    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString(){
        switch (this.index){
            case 45: return "+";
            case 46: return "-";
            case 43: return "*";
            case 44: return "/";
            default: return "VOID";
        }
    }
}
