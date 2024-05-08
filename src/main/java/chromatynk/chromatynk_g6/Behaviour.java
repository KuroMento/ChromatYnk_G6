package chromatynk.chromatynk_g6;

public enum Behaviour {
    DIRECT(0),
    ONEBYONE(1),
    ONEBYONETIMER(2);

    private final int index;
    Behaviour(int index){ this.index = index;}
}
