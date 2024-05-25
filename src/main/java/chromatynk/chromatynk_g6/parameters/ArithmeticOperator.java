package chromatynk.chromatynk_g6.parameters;

public enum ArithmeticOperator {
    VOID(0),
    EQUAL(48),
    NOT_EQUAL(49),
    GREATER(52),
    LESS(50),
    GREATER_EQUAL(53),
    LESS_EQUAL(51);

    private final int index;

    ArithmeticOperator(int index){
        this.index = index;
    }

    public int getIndex(){
        return this.index;
    }

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
