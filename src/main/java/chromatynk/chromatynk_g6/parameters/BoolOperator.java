package chromatynk.chromatynk_g6.parameters;

public enum BoolOperator {
    EQUAL(48),
    NOT_EQUAL(49);

    private final int index;

    BoolOperator(int index){
        this.index = index;
    }

    public int getIndex(){
        return this.index;
    }
}
