// Generated from /home/cytech/_Cours/S2/Java/ChromatYnk_G6/src/main/java/chromatynk/chromatynk_g6/LYnk/LYnk.g4 by ANTLR 4.13.1
package chromatynk.chromatynk_g6.LYnk;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LYnkParser}.
 */
public interface LYnkListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LYnkParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(LYnkParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(LYnkParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(LYnkParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(LYnkParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code trueVar}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterTrueVar(LYnkParser.TrueVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code trueVar}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitTrueVar(LYnkParser.TrueVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code falseVar}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterFalseVar(LYnkParser.FalseVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code falseVar}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitFalseVar(LYnkParser.FalseVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andOrExpression}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndOrExpression(LYnkParser.AndOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andOrExpression}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndOrExpression(LYnkParser.AndOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varComparison}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterVarComparison(LYnkParser.VarComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varComparison}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitVarComparison(LYnkParser.VarComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(LYnkParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(LYnkParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanComparison}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanComparison(LYnkParser.BooleanComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanComparison}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanComparison(LYnkParser.BooleanComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arithmeticLiteralComparison}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticLiteralComparison(LYnkParser.ArithmeticLiteralComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arithmeticLiteralComparison}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticLiteralComparison(LYnkParser.ArithmeticLiteralComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesisVar}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesisVar(LYnkParser.ParenthesisVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesisVar}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesisVar(LYnkParser.ParenthesisVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identificationVar}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterIdentificationVar(LYnkParser.IdentificationVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identificationVar}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitIdentificationVar(LYnkParser.IdentificationVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identificationExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterIdentificationExpression(LYnkParser.IdentificationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identificationExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitIdentificationExpression(LYnkParser.IdentificationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code longExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterLongExpression(LYnkParser.LongExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code longExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitLongExpression(LYnkParser.LongExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulDivExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterMulDivExpression(LYnkParser.MulDivExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulDivExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitMulDivExpression(LYnkParser.MulDivExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code plusMinusExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterPlusMinusExpression(LYnkParser.PlusMinusExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plusMinusExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitPlusMinusExpression(LYnkParser.PlusMinusExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doubleExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterDoubleExpression(LYnkParser.DoubleExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doubleExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitDoubleExpression(LYnkParser.DoubleExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterCompExpression(LYnkParser.CompExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitCompExpression(LYnkParser.CompExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numberExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumberExpression(LYnkParser.NumberExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numberExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumberExpression(LYnkParser.NumberExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesisExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesisExpression(LYnkParser.ParenthesisExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesisExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesisExpression(LYnkParser.ParenthesisExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#arithmeticOperator}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticOperator(LYnkParser.ArithmeticOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#arithmeticOperator}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticOperator(LYnkParser.ArithmeticOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#boolOperator}.
	 * @param ctx the parse tree
	 */
	void enterBoolOperator(LYnkParser.BoolOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#boolOperator}.
	 * @param ctx the parse tree
	 */
	void exitBoolOperator(LYnkParser.BoolOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#numOperator}.
	 * @param ctx the parse tree
	 */
	void enterNumOperator(LYnkParser.NumOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#numOperator}.
	 * @param ctx the parse tree
	 */
	void exitNumOperator(LYnkParser.NumOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#numStatementParameterX}.
	 * @param ctx the parse tree
	 */
	void enterNumStatementParameterX(LYnkParser.NumStatementParameterXContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#numStatementParameterX}.
	 * @param ctx the parse tree
	 */
	void exitNumStatementParameterX(LYnkParser.NumStatementParameterXContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#numStatementParameterY}.
	 * @param ctx the parse tree
	 */
	void enterNumStatementParameterY(LYnkParser.NumStatementParameterYContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#numStatementParameterY}.
	 * @param ctx the parse tree
	 */
	void exitNumStatementParameterY(LYnkParser.NumStatementParameterYContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(LYnkParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(LYnkParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(LYnkParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(LYnkParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(LYnkParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(LYnkParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#mimicStatement}.
	 * @param ctx the parse tree
	 */
	void enterMimicStatement(LYnkParser.MimicStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#mimicStatement}.
	 * @param ctx the parse tree
	 */
	void exitMimicStatement(LYnkParser.MimicStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#mirrorStatement}.
	 * @param ctx the parse tree
	 */
	void enterMirrorStatement(LYnkParser.MirrorStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#mirrorStatement}.
	 * @param ctx the parse tree
	 */
	void exitMirrorStatement(LYnkParser.MirrorStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#forwardStatement}.
	 * @param ctx the parse tree
	 */
	void enterForwardStatement(LYnkParser.ForwardStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#forwardStatement}.
	 * @param ctx the parse tree
	 */
	void exitForwardStatement(LYnkParser.ForwardStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#backwardStatement}.
	 * @param ctx the parse tree
	 */
	void enterBackwardStatement(LYnkParser.BackwardStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#backwardStatement}.
	 * @param ctx the parse tree
	 */
	void exitBackwardStatement(LYnkParser.BackwardStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#moveStatement}.
	 * @param ctx the parse tree
	 */
	void enterMoveStatement(LYnkParser.MoveStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#moveStatement}.
	 * @param ctx the parse tree
	 */
	void exitMoveStatement(LYnkParser.MoveStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#positionStatement}.
	 * @param ctx the parse tree
	 */
	void enterPositionStatement(LYnkParser.PositionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#positionStatement}.
	 * @param ctx the parse tree
	 */
	void exitPositionStatement(LYnkParser.PositionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(LYnkParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(LYnkParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#colorStatement}.
	 * @param ctx the parse tree
	 */
	void enterColorStatement(LYnkParser.ColorStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#colorStatement}.
	 * @param ctx the parse tree
	 */
	void exitColorStatement(LYnkParser.ColorStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#cursorStatement}.
	 * @param ctx the parse tree
	 */
	void enterCursorStatement(LYnkParser.CursorStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#cursorStatement}.
	 * @param ctx the parse tree
	 */
	void exitCursorStatement(LYnkParser.CursorStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectStatement(LYnkParser.SelectStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectStatement(LYnkParser.SelectStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#removeStatement}.
	 * @param ctx the parse tree
	 */
	void enterRemoveStatement(LYnkParser.RemoveStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#removeStatement}.
	 * @param ctx the parse tree
	 */
	void exitRemoveStatement(LYnkParser.RemoveStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#pressStatement}.
	 * @param ctx the parse tree
	 */
	void enterPressStatement(LYnkParser.PressStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#pressStatement}.
	 * @param ctx the parse tree
	 */
	void exitPressStatement(LYnkParser.PressStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#thickStatement}.
	 * @param ctx the parse tree
	 */
	void enterThickStatement(LYnkParser.ThickStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#thickStatement}.
	 * @param ctx the parse tree
	 */
	void exitThickStatement(LYnkParser.ThickStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#lookAtStatement}.
	 * @param ctx the parse tree
	 */
	void enterLookAtStatement(LYnkParser.LookAtStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#lookAtStatement}.
	 * @param ctx the parse tree
	 */
	void exitLookAtStatement(LYnkParser.LookAtStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#hideStatement}.
	 * @param ctx the parse tree
	 */
	void enterHideStatement(LYnkParser.HideStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#hideStatement}.
	 * @param ctx the parse tree
	 */
	void exitHideStatement(LYnkParser.HideStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowStatement(LYnkParser.ShowStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowStatement(LYnkParser.ShowStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#rotationStatement}.
	 * @param ctx the parse tree
	 */
	void enterRotationStatement(LYnkParser.RotationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#rotationStatement}.
	 * @param ctx the parse tree
	 */
	void exitRotationStatement(LYnkParser.RotationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#stringDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStringDeclaration(LYnkParser.StringDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#stringDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStringDeclaration(LYnkParser.StringDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#boolDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterBoolDeclaration(LYnkParser.BoolDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#boolDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitBoolDeclaration(LYnkParser.BoolDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#numberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterNumberDeclaration(LYnkParser.NumberDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#numberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitNumberDeclaration(LYnkParser.NumberDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LYnkParser#deleteDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterDeleteDeclaration(LYnkParser.DeleteDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LYnkParser#deleteDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitDeleteDeclaration(LYnkParser.DeleteDeclarationContext ctx);
}