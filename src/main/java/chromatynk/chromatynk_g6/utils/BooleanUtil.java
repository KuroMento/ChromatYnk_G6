package chromatynk.chromatynk_g6.utils;

import org.antlr.v4.runtime.Token;
import chromatynk.chromatynk_g6.LYnk.LYnkParser;

public final class BooleanUtil {

    public static final String UNSUPPORTED_OPERATOR = "Unsupported operator : ";

    public static Boolean evalAndOrOperator(final Boolean left, final Boolean right, final Token operator){
        switch (operator.getText()){
            case "AND" :
                return left && right;
            case "OR" :
                return left || right;
            default :
                throw new IllegalStateException(UNSUPPORTED_OPERATOR + operator);
        }
    }

    public static Boolean evalBooleanComparisonOperator(final Boolean left, final Boolean right, final Token operator){
        switch (operator.getType()){
            case LYnkParser.EQUAL:
                return left == right;
            case LYnkParser.NOT_EQUAL:
                return left != right;
            default:
                throw new IllegalStateException(UNSUPPORTED_OPERATOR + operator);
        }
    }
}
