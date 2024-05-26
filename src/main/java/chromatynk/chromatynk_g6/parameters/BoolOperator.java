package chromatynk.chromatynk_g6.parameters;

import chromatynk.chromatynk_g6.diagnostic.LYnkValidation;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticExpression;
import chromatynk.chromatynk_g6.parameters.booleanExp.*;
import chromatynk.chromatynk_g6.interpreter.LYnkConsole;

/**
 * An enumeration for comparisons between <code>{@link BooleanExpression}</code>.
 */
public enum BoolOperator {
    VOID(0),
    EQUAL(48),
    NOT_EQUAL(49);

    private final int index;

    BoolOperator(int index){
        this.index = index;
    }

    public int getIndex(){
        return this.index;
    }

    /**
     * Used to get back the operator from the ctx in visit methods in <code>{@link LYnkConsole}</code> for comparing <code>{@link BooleanExpression}</code>.
     * @param index The ctx.op.getType() in <code>{@link LYnkConsole}</code> methods.
     * @return An <code>{@link BoolOperator}</code>.
     */
    public static BoolOperator getOp(int index){
        switch (index){
            case 48: return EQUAL;
            case 49: return NOT_EQUAL;
            default: return VOID;
        }
    }

    @Override
    public String toString(){
        switch (this.index){
            case 48: return "==";
            case 49: return "!=";
            default: return "VOID";
        }
    }
}
