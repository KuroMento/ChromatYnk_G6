// Generated from /home/cytech/_Cours/S2/Java/ChromatYnk_G6/src/main/java/chromatynk/chromatynk_g6/LYnk/LYnk.g4 by ANTLR 4.13.1
package chromatynk.chromatynk_g6.LYnk;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link chromatynk.chromatynk_g6.LYnk.LYnkParser}.
 */
public interface LYnkListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(chromatynk.chromatynk_g6.LYnk.LYnkParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(chromatynk.chromatynk_g6.LYnk.LYnkParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code trueVar}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterTrueVar(chromatynk.chromatynk_g6.LYnk.LYnkParser.TrueVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code trueVar}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitTrueVar(chromatynk.chromatynk_g6.LYnk.LYnkParser.TrueVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literalComparison}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterLiteralComparison(chromatynk.chromatynk_g6.LYnk.LYnkParser.LiteralComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literalComparison}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitLiteralComparison(chromatynk.chromatynk_g6.LYnk.LYnkParser.LiteralComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code falseVar}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterFalseVar(chromatynk.chromatynk_g6.LYnk.LYnkParser.FalseVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code falseVar}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitFalseVar(chromatynk.chromatynk_g6.LYnk.LYnkParser.FalseVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andOrExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndOrExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.AndOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andOrExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndOrExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.AndOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arithmeticComparison}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticComparison(chromatynk.chromatynk_g6.LYnk.LYnkParser.ArithmeticComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arithmeticComparison}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticComparison(chromatynk.chromatynk_g6.LYnk.LYnkParser.ArithmeticComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanComparison}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanComparison(chromatynk.chromatynk_g6.LYnk.LYnkParser.BooleanComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanComparison}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanComparison(chromatynk.chromatynk_g6.LYnk.LYnkParser.BooleanComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesisVar}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesisVar(chromatynk.chromatynk_g6.LYnk.LYnkParser.ParenthesisVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesisVar}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesisVar(chromatynk.chromatynk_g6.LYnk.LYnkParser.ParenthesisVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identificationVar}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterIdentificationVar(chromatynk.chromatynk_g6.LYnk.LYnkParser.IdentificationVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identificationVar}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitIdentificationVar(chromatynk.chromatynk_g6.LYnk.LYnkParser.IdentificationVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identificationExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterIdentificationExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.IdentificationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identificationExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitIdentificationExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.IdentificationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code longExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterLongExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.LongExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code longExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitLongExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.LongExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulDivExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterMulDivExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.MulDivExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulDivExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitMulDivExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.MulDivExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code plusMinusExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterPlusMinusExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.PlusMinusExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plusMinusExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitPlusMinusExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.PlusMinusExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doubleExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterDoubleExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.DoubleExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doubleExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitDoubleExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.DoubleExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterCompExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.CompExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitCompExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.CompExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numberExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumberExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.NumberExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numberExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumberExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.NumberExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesisExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesisExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.ParenthesisExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesisExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesisExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.ParenthesisExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticOperator}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticOperator(chromatynk.chromatynk_g6.LYnk.LYnkParser.ArithmeticOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticOperator}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticOperator(chromatynk.chromatynk_g6.LYnk.LYnkParser.ArithmeticOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#boolOperator}.
	 * @param ctx the parse tree
	 */
	void enterBoolOperator(chromatynk.chromatynk_g6.LYnk.LYnkParser.BoolOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#boolOperator}.
	 * @param ctx the parse tree
	 */
	void exitBoolOperator(chromatynk.chromatynk_g6.LYnk.LYnkParser.BoolOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#numOperator}.
	 * @param ctx the parse tree
	 */
	void enterNumOperator(chromatynk.chromatynk_g6.LYnk.LYnkParser.NumOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#numOperator}.
	 * @param ctx the parse tree
	 */
	void exitNumOperator(chromatynk.chromatynk_g6.LYnk.LYnkParser.NumOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#numParameter}.
	 * @param ctx the parse tree
	 */
	void enterNumParameter(chromatynk.chromatynk_g6.LYnk.LYnkParser.NumParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#numParameter}.
	 * @param ctx the parse tree
	 */
	void exitNumParameter(chromatynk.chromatynk_g6.LYnk.LYnkParser.NumParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#colorParameter}.
	 * @param ctx the parse tree
	 */
	void enterColorParameter(chromatynk.chromatynk_g6.LYnk.LYnkParser.ColorParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#colorParameter}.
	 * @param ctx the parse tree
	 */
	void exitColorParameter(chromatynk.chromatynk_g6.LYnk.LYnkParser.ColorParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#mimicStatement}.
	 * @param ctx the parse tree
	 */
	void enterMimicStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.MimicStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#mimicStatement}.
	 * @param ctx the parse tree
	 */
	void exitMimicStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.MimicStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#mirrorStatement}.
	 * @param ctx the parse tree
	 */
	void enterMirrorStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.MirrorStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#mirrorStatement}.
	 * @param ctx the parse tree
	 */
	void exitMirrorStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.MirrorStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#forwardStatement}.
	 * @param ctx the parse tree
	 */
	void enterForwardStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.ForwardStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#forwardStatement}.
	 * @param ctx the parse tree
	 */
	void exitForwardStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.ForwardStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#backwardStatement}.
	 * @param ctx the parse tree
	 */
	void enterBackwardStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.BackwardStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#backwardStatement}.
	 * @param ctx the parse tree
	 */
	void exitBackwardStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.BackwardStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#moveStatement}.
	 * @param ctx the parse tree
	 */
	void enterMoveStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.MoveStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#moveStatement}.
	 * @param ctx the parse tree
	 */
	void exitMoveStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.MoveStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#positionStatement}.
	 * @param ctx the parse tree
	 */
	void enterPositionStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.PositionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#positionStatement}.
	 * @param ctx the parse tree
	 */
	void exitPositionStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.PositionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#colorStatement}.
	 * @param ctx the parse tree
	 */
	void enterColorStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.ColorStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#colorStatement}.
	 * @param ctx the parse tree
	 */
	void exitColorStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.ColorStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#cursorStatement}.
	 * @param ctx the parse tree
	 */
	void enterCursorStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.CursorStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#cursorStatement}.
	 * @param ctx the parse tree
	 */
	void exitCursorStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.CursorStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.SelectStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.SelectStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#removeStatement}.
	 * @param ctx the parse tree
	 */
	void enterRemoveStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.RemoveStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#removeStatement}.
	 * @param ctx the parse tree
	 */
	void exitRemoveStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.RemoveStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#pressStatement}.
	 * @param ctx the parse tree
	 */
	void enterPressStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.PressStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#pressStatement}.
	 * @param ctx the parse tree
	 */
	void exitPressStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.PressStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#thickStatement}.
	 * @param ctx the parse tree
	 */
	void enterThickStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.ThickStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#thickStatement}.
	 * @param ctx the parse tree
	 */
	void exitThickStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.ThickStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#lookAtStatement}.
	 * @param ctx the parse tree
	 */
	void enterLookAtStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.LookAtStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#lookAtStatement}.
	 * @param ctx the parse tree
	 */
	void exitLookAtStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.LookAtStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#hideStatement}.
	 * @param ctx the parse tree
	 */
	void enterHideStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.HideStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#hideStatement}.
	 * @param ctx the parse tree
	 */
	void exitHideStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.HideStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.ShowStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.ShowStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#rotationStatement}.
	 * @param ctx the parse tree
	 */
	void enterRotationStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.RotationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#rotationStatement}.
	 * @param ctx the parse tree
	 */
	void exitRotationStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.RotationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#stringDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStringDeclaration(chromatynk.chromatynk_g6.LYnk.LYnkParser.StringDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#stringDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStringDeclaration(chromatynk.chromatynk_g6.LYnk.LYnkParser.StringDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#boolDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterBoolDeclaration(chromatynk.chromatynk_g6.LYnk.LYnkParser.BoolDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#boolDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitBoolDeclaration(chromatynk.chromatynk_g6.LYnk.LYnkParser.BoolDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#numberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterNumberDeclaration(chromatynk.chromatynk_g6.LYnk.LYnkParser.NumberDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#numberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitNumberDeclaration(chromatynk.chromatynk_g6.LYnk.LYnkParser.NumberDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#deleteDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterDeleteDeclaration(chromatynk.chromatynk_g6.LYnk.LYnkParser.DeleteDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#deleteDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitDeleteDeclaration(LYnkParser.DeleteDeclarationContext ctx);
}