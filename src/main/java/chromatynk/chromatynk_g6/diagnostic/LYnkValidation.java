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
 * @param type Represents the type of return when visiting the parse tree (Use {@link LYnkType})
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

    @Deprecated
    public boolean isBoolean(){return this.type == LYnkType.BOOLEAN;}

    @Deprecated
    public boolean isNumeric(){return this.type == LYnkType.NUMBER || this.type == LYnkType.DOUBLE;}

    @Deprecated
    public boolean isDouble(){ return this.type == LYnkType.DOUBLE; }

    @Deprecated
    public boolean isString(){return this.type == LYnkType.LITERAL;}

    /**
     * Checks if the <code>value</code> is of type: <code>{@link ArithmeticVariable}</code> , <code>{@link BoolVariable}</code> or <code>{@link LiteralVariable}</code>.
     * @return True if <code>value</code> is a variable.
     */
    public boolean isIdentification(){
        if( this.type == LYnkType.ARITHMETIC_EXPRESSION && this.value instanceof ArithmeticVariable)
            return true;
        if( this.type == LYnkType.BOOL_EXPRESSION && this.value instanceof BoolVariable)
            return true;
        if( this.type == LYnkType.LITERAL_EXPRESSION && this.value instanceof LiteralVariable)
            return true;
        return false;
    }

    /**
     * Checks if the <code>value</code> is of type: <code>{@link LYnkType}.VOID</code>
     * @return True if <code>value</code> is a <code>{@link VoidObject}</code>
     */
    public boolean isVoid(){return this.type == LYnkType.VOID;}

    /**
     * Checks if the <code>value</code> is of type: <code>{@link LYnkType}.SKIP_ERROR</code>
     * @return True if <code>value</code> is null (i.e. SKIP_ERROR has a null value)
     */
    public boolean isSkipError(){return this.type == LYnkType.SKIP_ERROR; }

    public boolean hasValue() {
        return value != null;
    }

    /**
     * Used to get variable names for variable context. Also used to debug the current <code>value</code>.
     * @return <code>value.toString()</code> if the <code>value</code> is not null.
     */
    public String asString() {
        if (!hasValue()) {
            throw new IllegalStateException("Expecting a not null value");
        }

        return value.toString();
    }

    @Deprecated
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

    @Deprecated
    public static LYnkValidation number(Long value){return new LYnkValidation(LYnkType.NUMBER, value);}
    @Deprecated
    public static LYnkValidation string(String value){return new LYnkValidation(LYnkType.LITERAL, value);}
    @Deprecated
    public static LYnkValidation bool(Boolean value){return new LYnkValidation(LYnkType.BOOLEAN, value);}
    @Deprecated
    public static LYnkValidation doubleVar(Double value){return new LYnkValidation(LYnkType.DOUBLE, value);}
    @Deprecated
    public static LYnkValidation identification(String value){return new LYnkValidation(LYnkType.IDENTIFICATION, value);}

    // Boolean, Arithmetic and Literal Expressions below as well as Statements

    /**
     * Construct a new <code>{@link LYnkValidation}</code> with a given <code>{@link BooleanExpression} value</code>.
     * @param value A <code>{@link BooleanExpression}</code>.
     * @return A new <code>{@link LYnkValidation}</code>.
     */
    public static LYnkValidation boolExp(BooleanExpression value){ return new LYnkValidation(LYnkType.BOOL_EXPRESSION, value); }
    /**
     * Construct a new <code>{@link LYnkValidation}</code> with a given <code>{@link ArithmeticExpression} value</code>.
     * @param value A <code>{@link ArithmeticExpression}</code>.
     * @return A new <code>{@link LYnkValidation}</code>.
     */
    public static LYnkValidation numExp(ArithmeticExpression value){ return new LYnkValidation(LYnkType.ARITHMETIC_EXPRESSION, value); }
    /**
     * Construct a new <code>{@link LYnkValidation}</code> with a given <code>{@link LiteralExpression} value</code>.
     * @param value A <code>{@link LiteralExpression}</code>.
     * @return A new <code>{@link LYnkValidation}</code>.
     */
    public static LYnkValidation literalExp(LiteralExpression value){ return new LYnkValidation(LYnkType.LITERAL_EXPRESSION, value); }
    /**
     * Construct a new <code>{@link LYnkValidation}</code> with a given <code>{@link Statement} value</code>.
     * @param value A <code>{@link Statement}</code>.
     * @return A new <code>{@link LYnkValidation}</code>.
     */
    public static LYnkValidation statement(Statement value){ return new LYnkValidation(LYnkType.STATEMENT,value); }

    /**
     * Checks if the <code>value</code> is of type: <code>{@link LYnkType}.BOOL_EXPRESSION</code>
     * @return True if <code>value</code> is a <code>{@link BooleanExpression}</code>
     */
    public boolean isBoolExp(){ return this.type == LYnkType.BOOL_EXPRESSION; }
    /**
     * Checks if the <code>value</code> is of type: <code>{@link LYnkType}.ARITHMETIC_EXPRESSION</code>
     * @return True if <code>value</code> is a <code>{@link ArithmeticExpression}</code>
     */
    public boolean isNumExp(){ return this.type == LYnkType.ARITHMETIC_EXPRESSION; }
    /**
     * Checks if the <code>value</code> is of type: <code>{@link LYnkType}.STATEMENT</code>
     * @return True if <code>value</code> is a <code>{@link Statement}</code>
     */
    public boolean isStatement(){ return this.type == LYnkType.STATEMENT; }
}
