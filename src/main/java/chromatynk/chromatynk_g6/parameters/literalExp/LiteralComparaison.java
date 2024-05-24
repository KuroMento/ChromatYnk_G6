package chromatynk.chromatynk_g6.parameters.literalExp;

import chromatynk.chromatynk_g6.LYnk.LYnkParser;
import chromatynk.chromatynk_g6.parameters.ArithmeticOperator;
import chromatynk.chromatynk_g6.parameters.booleanExp.BooleanExpression;

public class LiteralComparaison extends BooleanExpression {*
    private LiteralExpression left;
    private LiteralExpression right;

    private ArithmeticOperator operator;

    public LiteralComparaison(LiteralExpression left, ArithmeticOperator operator, LiteralExpression right){

    }

    @Override
    public boolean evaluate(){

    }
}
