package chromatynk.chromatynk_g6.interpreter;

public enum Behaviour {
    DIRECT(0),
    ONEBYONE(1),
    ONEBYONETIMER(2);

    private final int index;

    Behaviour(int index){
        this.index = index;
    }

    public int getBehaviour(){
        return this.index;
    }
}
