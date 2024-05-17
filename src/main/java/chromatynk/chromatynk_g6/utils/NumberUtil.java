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

        value = switch (operator.getType()) {
            case LYnkParser.LESS -> leftVal < rightVal;
            case LYnkParser.LESS_OR_EQUAL -> leftVal <= rightVal;
            case LYnkParser.GREATER -> leftVal > rightVal;
            case LYnkParser.GREATER_OR_EQUAL -> leftVal >= rightVal;
            case LYnkParser.EQUAL -> Objects.equals(left, right);
            case LYnkParser.NOT_EQUAL -> !Objects.equals(left, right);
            default -> throw new IllegalStateException(UNSUPPORTED_OPERATOR + operator.getText());
        };

        return value;
    }

    private static Long evalLongOperator(final Long left, final Long right, final Token op) {
        return switch (op.getType()) {
            case LYnkParser.PLUS -> left + right;
            case LYnkParser.MINUS -> left - right;
            case LYnkParser.DIVISION -> left / right;
            case LYnkParser.MULTIPLICATION -> left * right;
            default -> throw new IllegalStateException(UNSUPPORTED_OPERATOR + op);
        };
    }

    private static Double evalDoubleOperator(final Double left, final Double right, final Token op) {
        return switch (op.getType()) {
            case LYnkParser.PLUS -> left + right;
            case LYnkParser.MINUS -> left - right;
            case LYnkParser.DIVISION -> left / right;
            case LYnkParser.MULTIPLICATION -> left * right;
            default -> throw new IllegalStateException(UNSUPPORTED_OPERATOR + op);
        };
    }
}
