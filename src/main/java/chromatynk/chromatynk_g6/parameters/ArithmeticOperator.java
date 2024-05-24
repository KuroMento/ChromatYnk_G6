package chromatynk.chromatynk_g6.parameters;

public enum ArithmeticOperator {
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
}
