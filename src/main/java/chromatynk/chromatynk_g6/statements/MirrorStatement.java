package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

public class MirrorStatement extends Statement{
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    public MirrorStatement(int x1, int y1, int x2, int y2, LYnkVariableImpl varContext){
        super("MIRROR", varContext);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }
}