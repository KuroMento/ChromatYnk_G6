// Generated from /home/cytech/_Cours/S2/Java/ChromatYnk_G6/src/main/java/chromatynk/chromatynk_g6/LYnk/LYnk.g4 by ANTLR 4.13.1
package chromatynk.chromatynk_g6.LYnk;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LYnkParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LYnkVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LYnkParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(LYnkParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(LYnkParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code trueVar}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueVar(LYnkParser.TrueVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalComparison}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralComparison(LYnkParser.LiteralComparisonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code falseVar}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalseVar(LYnkParser.FalseVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andOrExpression}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndOrExpression(LYnkParser.AndOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpression(LYnkParser.NotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arithmeticComparison}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticComparison(LYnkParser.ArithmeticComparisonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanComparison}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanComparison(LYnkParser.BooleanComparisonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesisVar}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesisVar(LYnkParser.ParenthesisVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identificationVar}
	 * labeled alternative in {@link LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentificationVar(LYnkParser.IdentificationVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identificationExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentificationExpression(LYnkParser.IdentificationExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code longExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLongExpression(LYnkParser.LongExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulDivExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivExpression(LYnkParser.MulDivExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plusMinusExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusMinusExpression(LYnkParser.PlusMinusExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code doubleExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleExpression(LYnkParser.DoubleExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompExpression(LYnkParser.CompExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numberExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberExpression(LYnkParser.NumberExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesisExpression}
	 * labeled alternative in {@link LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesisExpression(LYnkParser.ParenthesisExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#arithmeticOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticOperator(LYnkParser.ArithmeticOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#boolOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolOperator(LYnkParser.BoolOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#numOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumOperator(LYnkParser.NumOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(LYnkParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(LYnkParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(LYnkParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#numParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumParameter(LYnkParser.NumParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#numStatementParameterX}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumStatementParameterX(LYnkParser.NumStatementParameterXContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#numStatementParameterY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumStatementParameterY(LYnkParser.NumStatementParameterYContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#mimicStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMimicStatement(LYnkParser.MimicStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#mirrorStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMirrorStatement(LYnkParser.MirrorStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#forwardStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForwardStatement(LYnkParser.ForwardStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#backwardStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBackwardStatement(LYnkParser.BackwardStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#moveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoveStatement(LYnkParser.MoveStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#positionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPositionStatement(LYnkParser.PositionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#blockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(LYnkParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#colorStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColorStatement(LYnkParser.ColorStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#cursorStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCursorStatement(LYnkParser.CursorStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#selectStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectStatement(LYnkParser.SelectStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#removeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemoveStatement(LYnkParser.RemoveStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#pressStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPressStatement(LYnkParser.PressStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#thickStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThickStatement(LYnkParser.ThickStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#lookAtStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLookAtStatement(LYnkParser.LookAtStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#hideStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHideStatement(LYnkParser.HideStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowStatement(LYnkParser.ShowStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#rotationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRotationStatement(LYnkParser.RotationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#stringDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringDeclaration(LYnkParser.StringDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#boolDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolDeclaration(LYnkParser.BoolDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#numberDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberDeclaration(LYnkParser.NumberDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link LYnkParser#deleteDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeleteDeclaration(LYnkParser.DeleteDeclarationContext ctx);
}