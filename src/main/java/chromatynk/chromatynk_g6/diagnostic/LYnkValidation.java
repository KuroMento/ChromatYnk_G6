package chromatynk.chromatynk_g6.diagnostic;

public record LYnkValidation(LYnkType type, Object value) {
    public static final LYnkValidation VOID = new LYnkValidation(LYnkType.VOID, null);
    public static final LYnkValidation SKIP_ERROR = new LYnkValidation(LYnkType.SKIP_ERROR, null);

    public boolean isBoolean(){return this.type == LYnkType.BOOLEAN;}

    public boolean isNumber(){return this.type == LYnkType.NUMBER;}

    public boolean isDouble(){return this.type == LYnkType.DOUBLE;}

    public boolean isString(){return this.type == LYnkType.LITERAL;}

    public boolean isIdentification(){return this.type == LYnkType.IDENTIFICATION;}

    public boolean isVoid(){return this.type == LYnkType.VOID;}

    public boolean isSkipError(){return this.type == LYnkType.SKIP_ERROR; }

    public static LYnkValidation number(Long value){return new LYnkValidation(LYnkType.NUMBER, value);}
    public static LYnkValidation string(String value){return new LYnkValidation(LYnkType.LITERAL, value);}
    public static LYnkValidation bool(Boolean value){return new LYnkValidation(LYnkType.BOOLEAN, value);}
    public static LYnkValidation doubleVar(Double value){return new LYnkValidation(LYnkType.DOUBLE, value);}
    public static LYnkValidation identification(String value){return new LYnkValidation(LYnkType.IDENTIFICATION, value);}
}
