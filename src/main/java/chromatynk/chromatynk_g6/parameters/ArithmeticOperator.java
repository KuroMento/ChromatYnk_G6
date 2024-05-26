package chromatynk.chromatynk_g6.parameters;

import chromatynk.chromatynk_g6.diagnostic.LYnkValidation;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.*;
import chromatynk.chromatynk_g6.parameters.booleanExp.*;
import chromatynk.chromatynk_g6.parameters.literalExp.*;
import chromatynk.chromatynk_g6.interpreter.LYnkConsole;

/**
 * An enumeration for comparisons between <code>{@link ArithmeticExpression}</code> and <code>{@link LiteralExpression}</code>.
 */
public enum ArithmeticOperator {
    VOID(0),
    EQUAL(48),
    NOT_EQUAL(49),
    GREATER(52),
    LESS(50),
    GREATER_EQUAL(53),
    LESS_EQUAL(51);

    private final int index;

    /** constructor
     *
     * @param index
     */
    ArithmeticOperator(int index){
        this.index = index;
    }


    public int getIndex(){
        return this.index;
    }

    /**
     * Used to get back the operator from the ctx in visit methods in <code>{@link LYnkConsole}</code> for comparing <code>{@link ArithmeticExpression}</code> or <code>{@link LiteralExpression}</code>.
     * @param index The ctx.op.getType() in <code>{@link LYnkConsole}</code> methods.
     * @return An <code>{@link ArithmeticOperator}</code>.
     */
    public static ArithmeticOperator getOp(int index){
        switch (index){
            case 48: return EQUAL;
            case 49: return NOT_EQUAL;
            case 52: return GREATER;
            case 50: return LESS;
            case 53: return GREATER_EQUAL;
            case 51: return LESS_EQUAL;
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
            case 48: return "==";
            case 49: return "!=";
            case 52: return ">";
            case 50: return "<";
            case 53: return ">=";
            case 51: return "<=";
            default: return "VOID";
        }
    }
}
