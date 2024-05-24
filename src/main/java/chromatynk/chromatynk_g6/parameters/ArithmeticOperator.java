package chromatynk.chromatynk_g6.parameters;

public enum ArithmeticOperator {
    EQUAL(0),
    NOT_EQUAL(1),
    GREATER(2),
    LESS(3),
    GREATER_EQUAL(4),
    LESS_EQUAL(5);

    private final int index;

    ArithmeticOperator(int index){
        this.index = index;
    }

    public int getIndex(){
        return this.index;
    }
}
