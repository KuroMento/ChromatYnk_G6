package chromatynk.chromatynk_g6.utils;

import chromatynk.chromatynk_g6.LYnk.LYnkParser;
import org.antlr.v4.runtime.Token;

public class StringUtil {

    public static final String UNSUPPORTED_OPERATOR = "Unsupported operator: ";
    public static Boolean evalLiteralComparisonOperator(final String left, final String right, final Token operator){
        final int result;
        switch (operator.getType()){
            case LYnkParser.LESS :
                result = left.compareTo(right);
                if (result > 0){
                    return Boolean.TRUE;
                }
                else{
                    return Boolean.FALSE;
                }
            case LYnkParser.LESS_OR_EQUAL:
                result = left.compareTo(right);
                if (result >= 0){
                    return Boolean.TRUE;
                }
                else{
                    return Boolean.FALSE;
                }
            case LYnkParser.EQUAL:
                result = left.compareTo(right);
                if (result == 0){
                    return Boolean.TRUE;
                }
                else{
                    return Boolean.FALSE;
                }
            case LYnkParser.NOT_EQUAL:
                result = left.compareTo(right);
                if (result != 0){
                    return Boolean.TRUE;
                }
                else{
                    return Boolean.FALSE;
                }
            case LYnkParser.GREATER:
                result = left.compareTo(right);
                if (result < 0){
                    return Boolean.TRUE;
                }
                else{
                    return Boolean.FALSE;
                }
            case LYnkParser.GREATER_OR_EQUAL:
                result = left.compareTo(right);
                if (result <= 0){
                    return Boolean.TRUE;
                }
                else{
                    return Boolean.FALSE;
                }
            default:
                throw new IllegalStateException(UNSUPPORTED_OPERATOR +operator.getText());
        }
    }
}
