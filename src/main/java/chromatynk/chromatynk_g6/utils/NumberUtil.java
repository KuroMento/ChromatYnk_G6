package chromatynk.chromatynk_g6.utils;

import chromatynk.chromatynk_g6.LYnk.LYnkParser;
import org.antlr.v4.runtime.Token;

import java.util.Objects;

public class NumberUtil {
    public static final String UNSUPPORTED_OPERATOR = "Unsupported operator: ";

    public static Number evalBinaryOperator(final Number left, final Number right, final Token op) {
        if (left instanceof Long && right instanceof Long) {
            return evalLongOperator((Long) left, (Long) right, op);
        } else {
            return evalDoubleOperator(left.doubleValue(), right.doubleValue(), op);
        }
    }

    public static Boolean evalNumberComparisonOperator(final Number left, final Number right, final Token operator) {
        final boolean value;
        final double leftVal = left.doubleValue();
        final double rightVal = right.doubleValue();

        switch (operator.getType()) {
            case LYnkParser.LESS:
                value = leftVal < rightVal;
                break;
            case LYnkParser.LESS_OR_EQUAL:
                value = leftVal <= rightVal;
                break;
            case LYnkParser.GREATER:
                value = leftVal > rightVal;
                break;
            case LYnkParser.GREATER_OR_EQUAL:
                value = leftVal >= rightVal;
                break;
            case LYnkParser.EQUAL:
                value = Objects.equals(left, right);
                break;
            case LYnkParser.NOT_EQUAL:
                value = !Objects.equals(left, right);
                break;
            default:
                throw new IllegalStateException(UNSUPPORTED_OPERATOR +operator.getText());
        }

        return value;
    }

    private static Long evalLongOperator(final Long left, final Long right, final Token op) {
        switch (op.getType()) {
            case LYnkParser.PLUS :
                return left + right;
            case LYnkParser.MINUS :
                return left - right;
            case LYnkParser.DIVISION:
                return left / right;
            case LYnkParser.MULTIPLICATION:
                return left * right;
            default :
                throw new IllegalStateException(UNSUPPORTED_OPERATOR + op);
        }
    }

    private static Double evalDoubleOperator(final Double left, final Double right, final Token op) {
        switch (op.getType()) {
            case LYnkParser.PLUS:
                return left + right;
            case LYnkParser.MINUS:
                return left - right;
            case LYnkParser.DIVISION:
                return left / right;
            case LYnkParser.MULTIPLICATION:
                return left * right;
            default:
                throw new IllegalStateException(UNSUPPORTED_OPERATOR + op);
        }
    }
}
