// Generated from /home/cytech/_Cours/S2/Java/ChromatYnk_G6/src/main/java/chromatynk/chromatynk_g6/LYnk/LYnk.g4 by ANTLR 4.13.1
package chromatynk.chromatynk_g6.LYnk;
import chromatynk.chromatynk_g6.exceptions.variableExceptions.VariableDoesNotExistException;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LYnkVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(chromatynk.chromatynk_g6.LYnk.LYnkParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code trueVar}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueVar(chromatynk.chromatynk_g6.LYnk.LYnkParser.TrueVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalComparison}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralComparison(chromatynk.chromatynk_g6.LYnk.LYnkParser.LiteralComparisonContext ctx) throws VariableDoesNotExistException;
	/**
	 * Visit a parse tree produced by the {@code falseVar}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalseVar(chromatynk.chromatynk_g6.LYnk.LYnkParser.FalseVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andOrExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndOrExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.AndOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.NotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arithmeticComparison}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticComparison(chromatynk.chromatynk_g6.LYnk.LYnkParser.ArithmeticComparisonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanComparison}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanComparison(chromatynk.chromatynk_g6.LYnk.LYnkParser.BooleanComparisonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesisVar}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesisVar(chromatynk.chromatynk_g6.LYnk.LYnkParser.ParenthesisVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identificationVar}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentificationVar(chromatynk.chromatynk_g6.LYnk.LYnkParser.IdentificationVarContext ctx) throws VariableDoesNotExistException;
	/**
	 * Visit a parse tree produced by the {@code identificationExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentificationExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.IdentificationExpressionContext ctx) throws VariableDoesNotExistException;
	/**
	 * Visit a parse tree produced by the {@code longExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLongExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.LongExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulDivExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.MulDivExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plusMinusExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusMinusExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.PlusMinusExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code doubleExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.DoubleExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.CompExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numberExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.NumberExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesisExpression}
	 * labeled alternative in {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesisExpression(chromatynk.chromatynk_g6.LYnk.LYnkParser.ParenthesisExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#arithmeticOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticOperator(chromatynk.chromatynk_g6.LYnk.LYnkParser.ArithmeticOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#boolOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolOperator(chromatynk.chromatynk_g6.LYnk.LYnkParser.BoolOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#numOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumOperator(chromatynk.chromatynk_g6.LYnk.LYnkParser.NumOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.ForStatementContext ctx) throws VariableDoesNotExistException;
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#numParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumParameter(chromatynk.chromatynk_g6.LYnk.LYnkParser.NumParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#colorParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColorParameter(chromatynk.chromatynk_g6.LYnk.LYnkParser.ColorParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#mimicStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMimicStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.MimicStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#mirrorStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMirrorStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.MirrorStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#forwardStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForwardStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.ForwardStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#backwardStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBackwardStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.BackwardStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#moveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoveStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.MoveStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#positionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPositionStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.PositionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#blockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#colorStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColorStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.ColorStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#cursorStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCursorStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.CursorStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#selectStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.SelectStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#removeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemoveStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.RemoveStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#pressStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPressStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.PressStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#thickStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThickStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.ThickStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#lookAtStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLookAtStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.LookAtStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#hideStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHideStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.HideStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.ShowStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#rotationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRotationStatement(chromatynk.chromatynk_g6.LYnk.LYnkParser.RotationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#stringDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringDeclaration(chromatynk.chromatynk_g6.LYnk.LYnkParser.StringDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#boolDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolDeclaration(chromatynk.chromatynk_g6.LYnk.LYnkParser.BoolDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#numberDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberDeclaration(chromatynk.chromatynk_g6.LYnk.LYnkParser.NumberDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link chromatynk.chromatynk_g6.LYnk.LYnkParser#deleteDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeleteDeclaration(LYnkParser.DeleteDeclarationContext ctx);
}