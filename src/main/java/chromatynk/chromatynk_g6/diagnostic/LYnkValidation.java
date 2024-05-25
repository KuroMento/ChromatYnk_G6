package chromatynk.chromatynk_g6.diagnostic;

import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticExpression;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticVariable;
import chromatynk.chromatynk_g6.parameters.booleanExp.BoolVariable;
import chromatynk.chromatynk_g6.parameters.booleanExp.BooleanExpression;
import chromatynk.chromatynk_g6.parameters.literalExp.LiteralExpression;
import chromatynk.chromatynk_g6.parameters.literalExp.LiteralVariable;
import chromatynk.chromatynk_g6.statements.Statement;

/**
 * Base Object to verify and get back expressions and statements for a LYnk input
 * @param type Represents the type of return when visiting the parse tree
 * @param value Represents the Object interpretation of a specific input within the code (i.e. could be a statement or an expression
 */
public record LYnkValidation(LYnkType type, Object value) {
    // VOID object for when a statement does need a Statement value (for visitProgram only)
    public static final LYnkValidation VOID = new LYnkValidation(LYnkType.VOID, new VoidObject());
    private static class VoidObject {
        @Override
        public String toString() {
            return "VOID";
        }
    }

    // SKIP_ERROR Object for detecting and skipping error if one was found in the given code
    public static final LYnkValidation SKIP_ERROR = new LYnkValidation(LYnkType.SKIP_ERROR, null);

    public boolean isBoolean(){return this.type == LYnkType.BOOLEAN;}

    public boolean isNumeric(){return this.type == LYnkType.NUMBER || this.type == LYnkType.DOUBLE;}

    public boolean isDouble(){ return this.type == LYnkType.DOUBLE; }
    public boolean isString(){return this.type == LYnkType.LITERAL;}

    public boolean isIdentification(){
        if( this.type == LYnkType.ARITHMETIC_EXPRESSION && this.value instanceof ArithmeticVariable)
            return true;
        if( this.type == LYnkType.BOOL_EXPRESSION && this.value instanceof BoolVariable)
            return true;
        if( this.type == LYnkType.LITERAL_EXPRESSION && this.value instanceof LiteralVariable)
            return true;
        return false;
    }

    public boolean isVoid(){return this.type == LYnkType.VOID;}

    public boolean isSkipError(){return this.type == LYnkType.SKIP_ERROR; }

    public boolean hasValue() {
        return value != null;
    }

    public String asString() {
        if (!hasValue()) {
            throw new IllegalStateException("Expecting a not null value");
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

    // Boolean, Arithmetic and Literal Expressions below as well as Statements
    public static LYnkValidation boolExp(BooleanExpression value){ return new LYnkValidation(LYnkType.BOOL_EXPRESSION, value); }
    public static LYnkValidation numExp(ArithmeticExpression value){ return new LYnkValidation(LYnkType.ARITHMETIC_EXPRESSION, value); }
    public static LYnkValidation literalExp(LiteralExpression value){ return new LYnkValidation(LYnkType.LITERAL_EXPRESSION, value); }
    public static LYnkValidation statement(Statement value){ return new LYnkValidation(LYnkType.STATEMENT,value); }

    public boolean isBoolExp(){ return this.type == LYnkType.BOOL_EXPRESSION; }
    public boolean isNumExp(){ return this.type == LYnkType.ARITHMETIC_EXPRESSION; }

    public boolean isLiteralExp(){ return this.type == LYnkType.LITERAL_EXPRESSION; }
    public boolean isStatement(){ return this.type == LYnkType.STATEMENT; }
}
