package chromatynk.chromatynk_g6.parameters;

public enum NumberOperator {
    VOID(0),
    PLUS(45),
    MINUS(46),
    MULTIPLICATION(43),
    DIVISION(44);

    private final int index;

    NumberOperator(int index){
        this.index = index;
    }

    public int getIndex(){
        return this.index;
    }

    public static NumberOperator getOp(int index){
        switch (index){
            case 45: return PLUS;
            case 46: return MINUS;
            case 43: return MULTIPLICATION;
            case 44: return DIVISION;
            default: return VOID;
        }
    }

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
