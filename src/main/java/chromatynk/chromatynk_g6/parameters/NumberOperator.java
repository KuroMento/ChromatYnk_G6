package chromatynk.chromatynk_g6.parameters;

public enum NumberOperator {
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
}
