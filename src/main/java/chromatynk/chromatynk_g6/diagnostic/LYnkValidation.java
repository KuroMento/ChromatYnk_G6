package chromatynk.chromatynk_g6.diagnostic;


public record LYnkValidation(LYnkType type, Object value) {
    public static final LYnkValidation VOID = new LYnkValidation(LYnkType.VOID, new VoidObject());
    private static class VoidObject {
        @Override
        public String toString() {
            return "VOID";
        }
    }
    public static final LYnkValidation SKIP_ERROR = new LYnkValidation(LYnkType.SKIP_ERROR, null);

    public boolean isBoolean(){return this.type == LYnkType.BOOLEAN;}

    public boolean isNumeric(){return this.type == LYnkType.NUMBER || this.type == LYnkType.DOUBLE;}

    public boolean isDouble(){ return this.type == LYnkType.DOUBLE; }
    public boolean isString(){return this.type == LYnkType.LITERAL;}

    public boolean isIdentification(){return this.type == LYnkType.IDENTIFICATION;}

    public boolean isVoid(){return this.type == LYnkType.VOID;}

    public boolean isSkipError(){return this.type == LYnkType.SKIP_ERROR; }

    public boolean hasValue() {
        return value != null;
    }

    public String asString() {
        if (!hasValue()) {
            throw new IllegalStateException("Expected String value");
        }

        return value.toString();
    }

    public static double numericAsDouble(Number value){
        if( value instanceof Double dbl){
            return dbl;
        }
        else if (value instanceof Long lng) {
            return lng.doubleValue();
        } else {
            throw new IllegalStateException("The object " + value + " is not suported!");
        }
    }

    public static LYnkValidation number(Long value){return new LYnkValidation(LYnkType.NUMBER, value);}
    public static LYnkValidation string(String value){return new LYnkValidation(LYnkType.LITERAL, value);}
    public static LYnkValidation bool(Boolean value){return new LYnkValidation(LYnkType.BOOLEAN, value);}
    public static LYnkValidation doubleVar(Double value){return new LYnkValidation(LYnkType.DOUBLE, value);}
    public static LYnkValidation identification(String value){return new LYnkValidation(LYnkType.IDENTIFICATION, value);}
}
