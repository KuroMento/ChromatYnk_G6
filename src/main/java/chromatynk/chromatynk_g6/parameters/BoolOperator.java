package chromatynk.chromatynk_g6.parameters;

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
