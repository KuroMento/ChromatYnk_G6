package chromatynk.chromatynk_g6.parameters.literalExp;

public class LiteralExpression {
    private String value;

    /** constructor
     *
     * @param value
     */
    public LiteralExpression(String value){
        this.value = value;
    }
    /**
     * This method returns a String.
     * @return
     */
    public String evaluate(){
        return this.value;
    }
    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString(){
        if( this instanceof LiteralVariable variable){
            return variable.toString();
        }
        return this.value;
    }
}
