// Generated from /home/cytech/_Cours/S2/Java/ChromatYnk_G6/src/main/java/chromatynk/chromatynk_g6/LYnk/LYnk.g4 by ANTLR 4.13.1
package chromatynk.chromatynk_g6.LYnk;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class LYnkParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, IDENTIFICATION=36, LONG=37, NUMBER=38, 
		DOUBLE=39, LITERAL=40, PERCENTAGE=41, HEXCODE=42, MULTIPLICATION=43, DIVISION=44, 
		PLUS=45, MINUS=46, ASSIGN=47, EQUAL=48, NOT_EQUAL=49, LESS=50, LESS_OR_EQUAL=51, 
		GREATER=52, GREATER_OR_EQUAL=53, SPACE=54;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_booleanExpression = 2, RULE_arithmeticExpression = 3, 
		RULE_arithmeticOperator = 4, RULE_boolOperator = 5, RULE_numOperator = 6, 
		RULE_numStatementParameterX = 7, RULE_numStatementParameterY = 8, RULE_ifStatement = 9, 
		RULE_forStatement = 10, RULE_whileStatement = 11, RULE_mimicStatement = 12, 
		RULE_mirrorStatement = 13, RULE_forwardStatement = 14, RULE_backwardStatement = 15, 
		RULE_moveStatement = 16, RULE_positionStatement = 17, RULE_blockStatement = 18, 
		RULE_colorStatement = 19, RULE_cursorStatement = 20, RULE_selectStatement = 21, 
		RULE_removeStatement = 22, RULE_pressStatement = 23, RULE_thickStatement = 24, 
		RULE_lookAtStatement = 25, RULE_hideStatement = 26, RULE_showStatement = 27, 
		RULE_rotationStatement = 28, RULE_stringDeclaration = 29, RULE_boolDeclaration = 30, 
		RULE_numberDeclaration = 31, RULE_deleteDeclaration = 32;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "booleanExpression", "arithmeticExpression", 
			"arithmeticOperator", "boolOperator", "numOperator", "numStatementParameterX", 
			"numStatementParameterY", "ifStatement", "forStatement", "whileStatement", 
			"mimicStatement", "mirrorStatement", "forwardStatement", "backwardStatement", 
			"moveStatement", "positionStatement", "blockStatement", "colorStatement", 
			"cursorStatement", "selectStatement", "removeStatement", "pressStatement", 
			"thickStatement", "lookAtStatement", "hideStatement", "showStatement", 
			"rotationStatement", "stringDeclaration", "boolDeclaration", "numberDeclaration", 
			"deleteDeclaration"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'AND'", "'OR'", "'NOT'", "'TRUE'", "'FALSE'", "'IF'", 
			"'FOR'", "'FROM'", "'TO'", "'STEP'", "'WHILE'", "'MIMIC'", "'MIRROR'", 
			"'FWD'", "'BWD'", "'MOV'", "'POS'", "'{'", "'}'", "'COLOR'", "'CURSOR'", 
			"'SELECT'", "'REMOVE'", "'PRESS'", "'THICK'", "'LOOKAT'", "'HIDE'", "'SHOW'", 
			"'TURN'", "'STR'", "'BOOL'", "'NUM'", "'DEL'", null, null, null, null, 
			null, null, null, "'*'", "'/'", "'+'", "'-'", "'='", "'=='", "'!='", 
			"'<'", "'<='", "'>'", "'>='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"IDENTIFICATION", "LONG", "NUMBER", "DOUBLE", "LITERAL", "PERCENTAGE", 
			"HEXCODE", "MULTIPLICATION", "DIVISION", "PLUS", "MINUS", "ASSIGN", "EQUAL", 
			"NOT_EQUAL", "LESS", "LESS_OR_EQUAL", "GREATER", "GREATER_OR_EQUAL", 
			"SPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "LYnk.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LYnkParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(LYnkParser.EOF, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 68716323584L) != 0)) {
				{
				{
				setState(66);
				statement();
				}
				}
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(72);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public MimicStatementContext mimicStatement() {
			return getRuleContext(MimicStatementContext.class,0);
		}
		public MirrorStatementContext mirrorStatement() {
			return getRuleContext(MirrorStatementContext.class,0);
		}
		public CursorStatementContext cursorStatement() {
			return getRuleContext(CursorStatementContext.class,0);
		}
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public RemoveStatementContext removeStatement() {
			return getRuleContext(RemoveStatementContext.class,0);
		}
		public HideStatementContext hideStatement() {
			return getRuleContext(HideStatementContext.class,0);
		}
		public ShowStatementContext showStatement() {
			return getRuleContext(ShowStatementContext.class,0);
		}
		public PressStatementContext pressStatement() {
			return getRuleContext(PressStatementContext.class,0);
		}
		public ThickStatementContext thickStatement() {
			return getRuleContext(ThickStatementContext.class,0);
		}
		public ColorStatementContext colorStatement() {
			return getRuleContext(ColorStatementContext.class,0);
		}
		public LookAtStatementContext lookAtStatement() {
			return getRuleContext(LookAtStatementContext.class,0);
		}
		public ForwardStatementContext forwardStatement() {
			return getRuleContext(ForwardStatementContext.class,0);
		}
		public BackwardStatementContext backwardStatement() {
			return getRuleContext(BackwardStatementContext.class,0);
		}
		public RotationStatementContext rotationStatement() {
			return getRuleContext(RotationStatementContext.class,0);
		}
		public MoveStatementContext moveStatement() {
			return getRuleContext(MoveStatementContext.class,0);
		}
		public PositionStatementContext positionStatement() {
			return getRuleContext(PositionStatementContext.class,0);
		}
		public BoolDeclarationContext boolDeclaration() {
			return getRuleContext(BoolDeclarationContext.class,0);
		}
		public NumberDeclarationContext numberDeclaration() {
			return getRuleContext(NumberDeclarationContext.class,0);
		}
		public StringDeclarationContext stringDeclaration() {
			return getRuleContext(StringDeclarationContext.class,0);
		}
		public DeleteDeclarationContext deleteDeclaration() {
			return getRuleContext(DeleteDeclarationContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(97);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				ifStatement();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				whileStatement();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 3);
				{
				setState(76);
				forStatement();
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 4);
				{
				setState(77);
				mimicStatement();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 5);
				{
				setState(78);
				mirrorStatement();
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 6);
				{
				setState(79);
				cursorStatement();
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 7);
				{
				setState(80);
				selectStatement();
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 8);
				{
				setState(81);
				removeStatement();
				}
				break;
			case T__28:
				enterOuterAlt(_localctx, 9);
				{
				setState(82);
				hideStatement();
				}
				break;
			case T__29:
				enterOuterAlt(_localctx, 10);
				{
				setState(83);
				showStatement();
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 11);
				{
				setState(84);
				pressStatement();
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 12);
				{
				setState(85);
				thickStatement();
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 13);
				{
				setState(86);
				colorStatement();
				}
				break;
			case T__27:
				enterOuterAlt(_localctx, 14);
				{
				setState(87);
				lookAtStatement();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 15);
				{
				setState(88);
				forwardStatement();
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 16);
				{
				setState(89);
				backwardStatement();
				}
				break;
			case T__30:
				enterOuterAlt(_localctx, 17);
				{
				setState(90);
				rotationStatement();
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 18);
				{
				setState(91);
				moveStatement();
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 19);
				{
				setState(92);
				positionStatement();
				}
				break;
			case T__32:
				enterOuterAlt(_localctx, 20);
				{
				setState(93);
				boolDeclaration();
				}
				break;
			case T__33:
				enterOuterAlt(_localctx, 21);
				{
				setState(94);
				numberDeclaration();
				}
				break;
			case T__31:
				enterOuterAlt(_localctx, 22);
				{
				setState(95);
				stringDeclaration();
				}
				break;
			case T__34:
				enterOuterAlt(_localctx, 23);
				{
				setState(96);
				deleteDeclaration();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BooleanExpressionContext extends ParserRuleContext {
		public BooleanExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanExpression; }
	 
		public BooleanExpressionContext() { }
		public void copyFrom(BooleanExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TrueVarContext extends BooleanExpressionContext {
		public TrueVarContext(BooleanExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterTrueVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitTrueVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitTrueVar(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FalseVarContext extends BooleanExpressionContext {
		public FalseVarContext(BooleanExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterFalseVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitFalseVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitFalseVar(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AndOrExpressionContext extends BooleanExpressionContext {
		public BooleanExpressionContext left;
		public Token op;
		public BooleanExpressionContext right;
		public List<BooleanExpressionContext> booleanExpression() {
			return getRuleContexts(BooleanExpressionContext.class);
		}
		public BooleanExpressionContext booleanExpression(int i) {
			return getRuleContext(BooleanExpressionContext.class,i);
		}
		public AndOrExpressionContext(BooleanExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterAndOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitAndOrExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitAndOrExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarComparisonContext extends BooleanExpressionContext {
		public Token left;
		public ArithmeticOperatorContext op;
		public Token right;
		public List<TerminalNode> IDENTIFICATION() { return getTokens(LYnkParser.IDENTIFICATION); }
		public TerminalNode IDENTIFICATION(int i) {
			return getToken(LYnkParser.IDENTIFICATION, i);
		}
		public ArithmeticOperatorContext arithmeticOperator() {
			return getRuleContext(ArithmeticOperatorContext.class,0);
		}
		public VarComparisonContext(BooleanExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterVarComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitVarComparison(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitVarComparison(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotExpressionContext extends BooleanExpressionContext {
		public BooleanExpressionContext booleanExpression() {
			return getRuleContext(BooleanExpressionContext.class,0);
		}
		public NotExpressionContext(BooleanExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterNotExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitNotExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitNotExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanComparisonContext extends BooleanExpressionContext {
		public BooleanExpressionContext left;
		public BoolOperatorContext op;
		public BooleanExpressionContext right;
		public List<BooleanExpressionContext> booleanExpression() {
			return getRuleContexts(BooleanExpressionContext.class);
		}
		public BooleanExpressionContext booleanExpression(int i) {
			return getRuleContext(BooleanExpressionContext.class,i);
		}
		public BoolOperatorContext boolOperator() {
			return getRuleContext(BoolOperatorContext.class,0);
		}
		public BooleanComparisonContext(BooleanExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterBooleanComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitBooleanComparison(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitBooleanComparison(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArithmeticLiteralComparisonContext extends BooleanExpressionContext {
		public ArithmeticExpressionContext left;
		public Token leftLiteral;
		public ArithmeticOperatorContext op;
		public ArithmeticExpressionContext right;
		public Token rightLiteral;
		public ArithmeticOperatorContext arithmeticOperator() {
			return getRuleContext(ArithmeticOperatorContext.class,0);
		}
		public List<ArithmeticExpressionContext> arithmeticExpression() {
			return getRuleContexts(ArithmeticExpressionContext.class);
		}
		public ArithmeticExpressionContext arithmeticExpression(int i) {
			return getRuleContext(ArithmeticExpressionContext.class,i);
		}
		public List<TerminalNode> LITERAL() { return getTokens(LYnkParser.LITERAL); }
		public TerminalNode LITERAL(int i) {
			return getToken(LYnkParser.LITERAL, i);
		}
		public ArithmeticLiteralComparisonContext(BooleanExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterArithmeticLiteralComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitArithmeticLiteralComparison(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitArithmeticLiteralComparison(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenthesisVarContext extends BooleanExpressionContext {
		public BooleanExpressionContext booleanExpression() {
			return getRuleContext(BooleanExpressionContext.class,0);
		}
		public ParenthesisVarContext(BooleanExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterParenthesisVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitParenthesisVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitParenthesisVar(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdentificationVarContext extends BooleanExpressionContext {
		public TerminalNode IDENTIFICATION() { return getToken(LYnkParser.IDENTIFICATION, 0); }
		public IdentificationVarContext(BooleanExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterIdentificationVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitIdentificationVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitIdentificationVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanExpressionContext booleanExpression() throws RecognitionException {
		return booleanExpression(0);
	}

	private BooleanExpressionContext booleanExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BooleanExpressionContext _localctx = new BooleanExpressionContext(_ctx, _parentState);
		BooleanExpressionContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_booleanExpression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				_localctx = new ParenthesisVarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(100);
				match(T__0);
				setState(101);
				booleanExpression(0);
				setState(102);
				match(T__1);
				}
				break;
			case 2:
				{
				_localctx = new NotExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(104);
				match(T__4);
				setState(105);
				booleanExpression(7);
				}
				break;
			case 3:
				{
				_localctx = new VarComparisonContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(106);
				((VarComparisonContext)_localctx).left = match(IDENTIFICATION);
				setState(107);
				((VarComparisonContext)_localctx).op = arithmeticOperator();
				setState(108);
				((VarComparisonContext)_localctx).right = match(IDENTIFICATION);
				}
				break;
			case 4:
				{
				_localctx = new ArithmeticLiteralComparisonContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(112);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
				case IDENTIFICATION:
				case LONG:
				case NUMBER:
				case DOUBLE:
					{
					setState(110);
					((ArithmeticLiteralComparisonContext)_localctx).left = arithmeticExpression(0);
					}
					break;
				case LITERAL:
					{
					setState(111);
					((ArithmeticLiteralComparisonContext)_localctx).leftLiteral = match(LITERAL);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(114);
				((ArithmeticLiteralComparisonContext)_localctx).op = arithmeticOperator();
				setState(117);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
				case IDENTIFICATION:
				case LONG:
				case NUMBER:
				case DOUBLE:
					{
					setState(115);
					((ArithmeticLiteralComparisonContext)_localctx).right = arithmeticExpression(0);
					}
					break;
				case LITERAL:
					{
					setState(116);
					((ArithmeticLiteralComparisonContext)_localctx).rightLiteral = match(LITERAL);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 5:
				{
				_localctx = new IdentificationVarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(119);
				match(IDENTIFICATION);
				}
				break;
			case 6:
				{
				_localctx = new TrueVarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(120);
				match(T__5);
				}
				break;
			case 7:
				{
				_localctx = new FalseVarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(121);
				match(T__6);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(133);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(131);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new AndOrExpressionContext(new BooleanExpressionContext(_parentctx, _parentState));
						((AndOrExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_booleanExpression);
						setState(124);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(125);
						((AndOrExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__2 || _la==T__3) ) {
							((AndOrExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(126);
						((AndOrExpressionContext)_localctx).right = booleanExpression(9);
						}
						break;
					case 2:
						{
						_localctx = new BooleanComparisonContext(new BooleanExpressionContext(_parentctx, _parentState));
						((BooleanComparisonContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_booleanExpression);
						setState(127);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(128);
						((BooleanComparisonContext)_localctx).op = boolOperator();
						setState(129);
						((BooleanComparisonContext)_localctx).right = booleanExpression(5);
						}
						break;
					}
					} 
				}
				setState(135);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArithmeticExpressionContext extends ParserRuleContext {
		public ArithmeticExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticExpression; }
	 
		public ArithmeticExpressionContext() { }
		public void copyFrom(ArithmeticExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdentificationExpressionContext extends ArithmeticExpressionContext {
		public TerminalNode IDENTIFICATION() { return getToken(LYnkParser.IDENTIFICATION, 0); }
		public IdentificationExpressionContext(ArithmeticExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterIdentificationExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitIdentificationExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitIdentificationExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LongExpressionContext extends ArithmeticExpressionContext {
		public TerminalNode LONG() { return getToken(LYnkParser.LONG, 0); }
		public LongExpressionContext(ArithmeticExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterLongExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitLongExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitLongExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MulDivExpressionContext extends ArithmeticExpressionContext {
		public ArithmeticExpressionContext left;
		public Token op;
		public ArithmeticExpressionContext right;
		public List<ArithmeticExpressionContext> arithmeticExpression() {
			return getRuleContexts(ArithmeticExpressionContext.class);
		}
		public ArithmeticExpressionContext arithmeticExpression(int i) {
			return getRuleContext(ArithmeticExpressionContext.class,i);
		}
		public TerminalNode MULTIPLICATION() { return getToken(LYnkParser.MULTIPLICATION, 0); }
		public TerminalNode DIVISION() { return getToken(LYnkParser.DIVISION, 0); }
		public MulDivExpressionContext(ArithmeticExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterMulDivExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitMulDivExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitMulDivExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PlusMinusExpressionContext extends ArithmeticExpressionContext {
		public ArithmeticExpressionContext left;
		public Token op;
		public ArithmeticExpressionContext right;
		public List<ArithmeticExpressionContext> arithmeticExpression() {
			return getRuleContexts(ArithmeticExpressionContext.class);
		}
		public ArithmeticExpressionContext arithmeticExpression(int i) {
			return getRuleContext(ArithmeticExpressionContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(LYnkParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(LYnkParser.MINUS, 0); }
		public PlusMinusExpressionContext(ArithmeticExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterPlusMinusExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitPlusMinusExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitPlusMinusExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DoubleExpressionContext extends ArithmeticExpressionContext {
		public TerminalNode DOUBLE() { return getToken(LYnkParser.DOUBLE, 0); }
		public DoubleExpressionContext(ArithmeticExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterDoubleExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitDoubleExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitDoubleExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CompExpressionContext extends ArithmeticExpressionContext {
		public ArithmeticExpressionContext left;
		public ArithmeticExpressionContext right;
		public NumOperatorContext numOperator() {
			return getRuleContext(NumOperatorContext.class,0);
		}
		public List<ArithmeticExpressionContext> arithmeticExpression() {
			return getRuleContexts(ArithmeticExpressionContext.class);
		}
		public ArithmeticExpressionContext arithmeticExpression(int i) {
			return getRuleContext(ArithmeticExpressionContext.class,i);
		}
		public CompExpressionContext(ArithmeticExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterCompExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitCompExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitCompExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberExpressionContext extends ArithmeticExpressionContext {
		public TerminalNode NUMBER() { return getToken(LYnkParser.NUMBER, 0); }
		public NumberExpressionContext(ArithmeticExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterNumberExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitNumberExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitNumberExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenthesisExpressionContext extends ArithmeticExpressionContext {
		public ArithmeticExpressionContext arithmeticExpression() {
			return getRuleContext(ArithmeticExpressionContext.class,0);
		}
		public ParenthesisExpressionContext(ArithmeticExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterParenthesisExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitParenthesisExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitParenthesisExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticExpressionContext arithmeticExpression() throws RecognitionException {
		return arithmeticExpression(0);
	}

	private ArithmeticExpressionContext arithmeticExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ArithmeticExpressionContext _localctx = new ArithmeticExpressionContext(_ctx, _parentState);
		ArithmeticExpressionContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_arithmeticExpression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				{
				_localctx = new ParenthesisExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(137);
				match(T__0);
				setState(138);
				arithmeticExpression(0);
				setState(139);
				match(T__1);
				}
				break;
			case NUMBER:
				{
				_localctx = new NumberExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(141);
				match(NUMBER);
				}
				break;
			case LONG:
				{
				_localctx = new LongExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(142);
				match(LONG);
				}
				break;
			case DOUBLE:
				{
				_localctx = new DoubleExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(143);
				match(DOUBLE);
				}
				break;
			case IDENTIFICATION:
				{
				_localctx = new IdentificationExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(144);
				match(IDENTIFICATION);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(159);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(157);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new MulDivExpressionContext(new ArithmeticExpressionContext(_parentctx, _parentState));
						((MulDivExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_arithmeticExpression);
						setState(147);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(148);
						((MulDivExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MULTIPLICATION || _la==DIVISION) ) {
							((MulDivExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(149);
						((MulDivExpressionContext)_localctx).right = arithmeticExpression(8);
						}
						break;
					case 2:
						{
						_localctx = new PlusMinusExpressionContext(new ArithmeticExpressionContext(_parentctx, _parentState));
						((PlusMinusExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_arithmeticExpression);
						setState(150);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(151);
						((PlusMinusExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((PlusMinusExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(152);
						((PlusMinusExpressionContext)_localctx).right = arithmeticExpression(7);
						}
						break;
					case 3:
						{
						_localctx = new CompExpressionContext(new ArithmeticExpressionContext(_parentctx, _parentState));
						((CompExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_arithmeticExpression);
						setState(153);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(154);
						numOperator();
						setState(155);
						((CompExpressionContext)_localctx).right = arithmeticExpression(6);
						}
						break;
					}
					} 
				}
				setState(161);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArithmeticOperatorContext extends ParserRuleContext {
		public Token op;
		public TerminalNode EQUAL() { return getToken(LYnkParser.EQUAL, 0); }
		public TerminalNode GREATER() { return getToken(LYnkParser.GREATER, 0); }
		public TerminalNode LESS() { return getToken(LYnkParser.LESS, 0); }
		public TerminalNode GREATER_OR_EQUAL() { return getToken(LYnkParser.GREATER_OR_EQUAL, 0); }
		public TerminalNode LESS_OR_EQUAL() { return getToken(LYnkParser.LESS_OR_EQUAL, 0); }
		public TerminalNode NOT_EQUAL() { return getToken(LYnkParser.NOT_EQUAL, 0); }
		public ArithmeticOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterArithmeticOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitArithmeticOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitArithmeticOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticOperatorContext arithmeticOperator() throws RecognitionException {
		ArithmeticOperatorContext _localctx = new ArithmeticOperatorContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_arithmeticOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			((ArithmeticOperatorContext)_localctx).op = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 17732923532771328L) != 0)) ) {
				((ArithmeticOperatorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BoolOperatorContext extends ParserRuleContext {
		public Token op;
		public TerminalNode EQUAL() { return getToken(LYnkParser.EQUAL, 0); }
		public TerminalNode NOT_EQUAL() { return getToken(LYnkParser.NOT_EQUAL, 0); }
		public BoolOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterBoolOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitBoolOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitBoolOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolOperatorContext boolOperator() throws RecognitionException {
		BoolOperatorContext _localctx = new BoolOperatorContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_boolOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			((BoolOperatorContext)_localctx).op = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==EQUAL || _la==NOT_EQUAL) ) {
				((BoolOperatorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumOperatorContext extends ParserRuleContext {
		public Token op;
		public TerminalNode PLUS() { return getToken(LYnkParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(LYnkParser.MINUS, 0); }
		public TerminalNode MULTIPLICATION() { return getToken(LYnkParser.MULTIPLICATION, 0); }
		public TerminalNode DIVISION() { return getToken(LYnkParser.DIVISION, 0); }
		public NumOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterNumOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitNumOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitNumOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumOperatorContext numOperator() throws RecognitionException {
		NumOperatorContext _localctx = new NumOperatorContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_numOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			((NumOperatorContext)_localctx).op = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 131941395333120L) != 0)) ) {
				((NumOperatorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumStatementParameterXContext extends ParserRuleContext {
		public ArithmeticExpressionContext arithmeticExpression() {
			return getRuleContext(ArithmeticExpressionContext.class,0);
		}
		public TerminalNode PERCENTAGE() { return getToken(LYnkParser.PERCENTAGE, 0); }
		public NumStatementParameterXContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numStatementParameterX; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterNumStatementParameterX(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitNumStatementParameterX(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitNumStatementParameterX(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumStatementParameterXContext numStatementParameterX() throws RecognitionException {
		NumStatementParameterXContext _localctx = new NumStatementParameterXContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_numStatementParameterX);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case IDENTIFICATION:
			case LONG:
			case NUMBER:
			case DOUBLE:
				{
				setState(168);
				arithmeticExpression(0);
				}
				break;
			case PERCENTAGE:
				{
				setState(169);
				match(PERCENTAGE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumStatementParameterYContext extends ParserRuleContext {
		public ArithmeticExpressionContext arithmeticExpression() {
			return getRuleContext(ArithmeticExpressionContext.class,0);
		}
		public TerminalNode PERCENTAGE() { return getToken(LYnkParser.PERCENTAGE, 0); }
		public NumStatementParameterYContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numStatementParameterY; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterNumStatementParameterY(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitNumStatementParameterY(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitNumStatementParameterY(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumStatementParameterYContext numStatementParameterY() throws RecognitionException {
		NumStatementParameterYContext _localctx = new NumStatementParameterYContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_numStatementParameterY);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case IDENTIFICATION:
			case LONG:
			case NUMBER:
			case DOUBLE:
				{
				setState(172);
				arithmeticExpression(0);
				}
				break;
			case PERCENTAGE:
				{
				setState(173);
				match(PERCENTAGE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfStatementContext extends ParserRuleContext {
		public BooleanExpressionContext booleanExpression() {
			return getRuleContext(BooleanExpressionContext.class,0);
		}
		public BlockStatementContext blockStatement() {
			return getRuleContext(BlockStatementContext.class,0);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(T__7);
			setState(177);
			booleanExpression(0);
			setState(178);
			blockStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForStatementContext extends ParserRuleContext {
		public Token from;
		public Token to;
		public Token step;
		public TerminalNode IDENTIFICATION() { return getToken(LYnkParser.IDENTIFICATION, 0); }
		public BlockStatementContext blockStatement() {
			return getRuleContext(BlockStatementContext.class,0);
		}
		public List<TerminalNode> NUMBER() { return getTokens(LYnkParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(LYnkParser.NUMBER, i);
		}
		public List<TerminalNode> LONG() { return getTokens(LYnkParser.LONG); }
		public TerminalNode LONG(int i) {
			return getToken(LYnkParser.LONG, i);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitForStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitForStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_forStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(T__8);
			setState(181);
			match(IDENTIFICATION);
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(182);
				match(T__9);
				setState(183);
				((ForStatementContext)_localctx).from = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==LONG || _la==NUMBER) ) {
					((ForStatementContext)_localctx).from = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(186);
			match(T__10);
			setState(187);
			((ForStatementContext)_localctx).to = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==LONG || _la==NUMBER) ) {
				((ForStatementContext)_localctx).to = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(188);
				match(T__11);
				setState(189);
				((ForStatementContext)_localctx).step = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==LONG || _la==NUMBER) ) {
					((ForStatementContext)_localctx).step = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(192);
			blockStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhileStatementContext extends ParserRuleContext {
		public BooleanExpressionContext booleanExpression() {
			return getRuleContext(BooleanExpressionContext.class,0);
		}
		public BlockStatementContext blockStatement() {
			return getRuleContext(BlockStatementContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			match(T__12);
			setState(195);
			booleanExpression(0);
			setState(196);
			blockStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MimicStatementContext extends ParserRuleContext {
		public TerminalNode LONG() { return getToken(LYnkParser.LONG, 0); }
		public BlockStatementContext blockStatement() {
			return getRuleContext(BlockStatementContext.class,0);
		}
		public MimicStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mimicStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterMimicStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitMimicStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitMimicStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MimicStatementContext mimicStatement() throws RecognitionException {
		MimicStatementContext _localctx = new MimicStatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_mimicStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			match(T__13);
			setState(199);
			match(LONG);
			setState(200);
			blockStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MirrorStatementContext extends ParserRuleContext {
		public NumStatementParameterXContext x1;
		public NumStatementParameterYContext y1;
		public NumStatementParameterXContext x2;
		public NumStatementParameterYContext y2;
		public BlockStatementContext blockStatement() {
			return getRuleContext(BlockStatementContext.class,0);
		}
		public List<NumStatementParameterXContext> numStatementParameterX() {
			return getRuleContexts(NumStatementParameterXContext.class);
		}
		public NumStatementParameterXContext numStatementParameterX(int i) {
			return getRuleContext(NumStatementParameterXContext.class,i);
		}
		public List<NumStatementParameterYContext> numStatementParameterY() {
			return getRuleContexts(NumStatementParameterYContext.class);
		}
		public NumStatementParameterYContext numStatementParameterY(int i) {
			return getRuleContext(NumStatementParameterYContext.class,i);
		}
		public MirrorStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mirrorStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterMirrorStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitMirrorStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitMirrorStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MirrorStatementContext mirrorStatement() throws RecognitionException {
		MirrorStatementContext _localctx = new MirrorStatementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_mirrorStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			match(T__14);
			setState(211);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(203);
				((MirrorStatementContext)_localctx).x1 = numStatementParameterX();
				setState(204);
				((MirrorStatementContext)_localctx).y1 = numStatementParameterY();
				}
				break;
			case 2:
				{
				setState(206);
				((MirrorStatementContext)_localctx).x1 = numStatementParameterX();
				setState(207);
				((MirrorStatementContext)_localctx).y1 = numStatementParameterY();
				setState(208);
				((MirrorStatementContext)_localctx).x2 = numStatementParameterX();
				setState(209);
				((MirrorStatementContext)_localctx).y2 = numStatementParameterY();
				}
				break;
			}
			setState(213);
			blockStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForwardStatementContext extends ParserRuleContext {
		public NumStatementParameterXContext numStatementParameterX() {
			return getRuleContext(NumStatementParameterXContext.class,0);
		}
		public ForwardStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forwardStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterForwardStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitForwardStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitForwardStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForwardStatementContext forwardStatement() throws RecognitionException {
		ForwardStatementContext _localctx = new ForwardStatementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_forwardStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			match(T__15);
			setState(216);
			numStatementParameterX();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BackwardStatementContext extends ParserRuleContext {
		public NumStatementParameterXContext numStatementParameterX() {
			return getRuleContext(NumStatementParameterXContext.class,0);
		}
		public BackwardStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_backwardStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterBackwardStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitBackwardStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitBackwardStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BackwardStatementContext backwardStatement() throws RecognitionException {
		BackwardStatementContext _localctx = new BackwardStatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_backwardStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			match(T__16);
			setState(219);
			numStatementParameterX();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MoveStatementContext extends ParserRuleContext {
		public NumStatementParameterXContext x;
		public NumStatementParameterYContext y;
		public NumStatementParameterXContext numStatementParameterX() {
			return getRuleContext(NumStatementParameterXContext.class,0);
		}
		public NumStatementParameterYContext numStatementParameterY() {
			return getRuleContext(NumStatementParameterYContext.class,0);
		}
		public MoveStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moveStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterMoveStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitMoveStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitMoveStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MoveStatementContext moveStatement() throws RecognitionException {
		MoveStatementContext _localctx = new MoveStatementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_moveStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			match(T__17);
			setState(222);
			((MoveStatementContext)_localctx).x = numStatementParameterX();
			setState(223);
			((MoveStatementContext)_localctx).y = numStatementParameterY();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PositionStatementContext extends ParserRuleContext {
		public NumStatementParameterXContext x;
		public NumStatementParameterYContext y;
		public NumStatementParameterXContext numStatementParameterX() {
			return getRuleContext(NumStatementParameterXContext.class,0);
		}
		public NumStatementParameterYContext numStatementParameterY() {
			return getRuleContext(NumStatementParameterYContext.class,0);
		}
		public PositionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_positionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterPositionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitPositionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitPositionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PositionStatementContext positionStatement() throws RecognitionException {
		PositionStatementContext _localctx = new PositionStatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_positionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			match(T__18);
			setState(226);
			((PositionStatementContext)_localctx).x = numStatementParameterX();
			setState(227);
			((PositionStatementContext)_localctx).y = numStatementParameterY();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockStatementContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterBlockStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitBlockStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitBlockStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockStatementContext blockStatement() throws RecognitionException {
		BlockStatementContext _localctx = new BlockStatementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_blockStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			match(T__19);
			setState(233);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 68716323584L) != 0)) {
				{
				{
				setState(230);
				statement();
				}
				}
				setState(235);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(236);
			match(T__20);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ColorStatementContext extends ParserRuleContext {
		public TerminalNode HEXCODE() { return getToken(LYnkParser.HEXCODE, 0); }
		public List<ArithmeticExpressionContext> arithmeticExpression() {
			return getRuleContexts(ArithmeticExpressionContext.class);
		}
		public ArithmeticExpressionContext arithmeticExpression(int i) {
			return getRuleContext(ArithmeticExpressionContext.class,i);
		}
		public ColorStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_colorStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterColorStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitColorStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitColorStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColorStatementContext colorStatement() throws RecognitionException {
		ColorStatementContext _localctx = new ColorStatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_colorStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			match(T__21);
			setState(244);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case HEXCODE:
				{
				setState(239);
				match(HEXCODE);
				}
				break;
			case T__0:
			case IDENTIFICATION:
			case LONG:
			case NUMBER:
			case DOUBLE:
				{
				setState(240);
				arithmeticExpression(0);
				setState(241);
				arithmeticExpression(0);
				setState(242);
				arithmeticExpression(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CursorStatementContext extends ParserRuleContext {
		public TerminalNode LONG() { return getToken(LYnkParser.LONG, 0); }
		public CursorStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cursorStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterCursorStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitCursorStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitCursorStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CursorStatementContext cursorStatement() throws RecognitionException {
		CursorStatementContext _localctx = new CursorStatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_cursorStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			match(T__22);
			setState(247);
			match(LONG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelectStatementContext extends ParserRuleContext {
		public TerminalNode LONG() { return getToken(LYnkParser.LONG, 0); }
		public SelectStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterSelectStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitSelectStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitSelectStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectStatementContext selectStatement() throws RecognitionException {
		SelectStatementContext _localctx = new SelectStatementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_selectStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			match(T__23);
			setState(250);
			match(LONG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RemoveStatementContext extends ParserRuleContext {
		public TerminalNode LONG() { return getToken(LYnkParser.LONG, 0); }
		public RemoveStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_removeStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterRemoveStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitRemoveStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitRemoveStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RemoveStatementContext removeStatement() throws RecognitionException {
		RemoveStatementContext _localctx = new RemoveStatementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_removeStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			match(T__24);
			setState(253);
			match(LONG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PressStatementContext extends ParserRuleContext {
		public ArithmeticExpressionContext arithmeticExpression() {
			return getRuleContext(ArithmeticExpressionContext.class,0);
		}
		public TerminalNode PERCENTAGE() { return getToken(LYnkParser.PERCENTAGE, 0); }
		public PressStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pressStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterPressStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitPressStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitPressStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PressStatementContext pressStatement() throws RecognitionException {
		PressStatementContext _localctx = new PressStatementContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_pressStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			match(T__25);
			setState(258);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case IDENTIFICATION:
			case LONG:
			case NUMBER:
			case DOUBLE:
				{
				setState(256);
				arithmeticExpression(0);
				}
				break;
			case PERCENTAGE:
				{
				setState(257);
				match(PERCENTAGE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ThickStatementContext extends ParserRuleContext {
		public ArithmeticExpressionContext arithmeticExpression() {
			return getRuleContext(ArithmeticExpressionContext.class,0);
		}
		public ThickStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thickStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterThickStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitThickStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitThickStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ThickStatementContext thickStatement() throws RecognitionException {
		ThickStatementContext _localctx = new ThickStatementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_thickStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			match(T__26);
			setState(261);
			arithmeticExpression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LookAtStatementContext extends ParserRuleContext {
		public NumStatementParameterXContext x;
		public NumStatementParameterYContext y;
		public TerminalNode LONG() { return getToken(LYnkParser.LONG, 0); }
		public NumStatementParameterXContext numStatementParameterX() {
			return getRuleContext(NumStatementParameterXContext.class,0);
		}
		public NumStatementParameterYContext numStatementParameterY() {
			return getRuleContext(NumStatementParameterYContext.class,0);
		}
		public LookAtStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lookAtStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterLookAtStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitLookAtStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitLookAtStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LookAtStatementContext lookAtStatement() throws RecognitionException {
		LookAtStatementContext _localctx = new LookAtStatementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_lookAtStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			match(T__27);
			setState(268);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(264);
				match(LONG);
				}
				break;
			case 2:
				{
				setState(265);
				((LookAtStatementContext)_localctx).x = numStatementParameterX();
				setState(266);
				((LookAtStatementContext)_localctx).y = numStatementParameterY();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HideStatementContext extends ParserRuleContext {
		public HideStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hideStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterHideStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitHideStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitHideStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HideStatementContext hideStatement() throws RecognitionException {
		HideStatementContext _localctx = new HideStatementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_hideStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			match(T__28);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ShowStatementContext extends ParserRuleContext {
		public ShowStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_showStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterShowStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitShowStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitShowStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShowStatementContext showStatement() throws RecognitionException {
		ShowStatementContext _localctx = new ShowStatementContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_showStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RotationStatementContext extends ParserRuleContext {
		public ArithmeticExpressionContext arithmeticExpression() {
			return getRuleContext(ArithmeticExpressionContext.class,0);
		}
		public RotationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rotationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterRotationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitRotationStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitRotationStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RotationStatementContext rotationStatement() throws RecognitionException {
		RotationStatementContext _localctx = new RotationStatementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_rotationStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			match(T__30);
			setState(275);
			arithmeticExpression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StringDeclarationContext extends ParserRuleContext {
		public TerminalNode IDENTIFICATION() { return getToken(LYnkParser.IDENTIFICATION, 0); }
		public TerminalNode ASSIGN() { return getToken(LYnkParser.ASSIGN, 0); }
		public TerminalNode LITERAL() { return getToken(LYnkParser.LITERAL, 0); }
		public StringDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterStringDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitStringDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitStringDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringDeclarationContext stringDeclaration() throws RecognitionException {
		StringDeclarationContext _localctx = new StringDeclarationContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_stringDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(T__31);
			setState(278);
			match(IDENTIFICATION);
			setState(281);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(279);
				match(ASSIGN);
				setState(280);
				match(LITERAL);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BoolDeclarationContext extends ParserRuleContext {
		public TerminalNode IDENTIFICATION() { return getToken(LYnkParser.IDENTIFICATION, 0); }
		public TerminalNode ASSIGN() { return getToken(LYnkParser.ASSIGN, 0); }
		public BooleanExpressionContext booleanExpression() {
			return getRuleContext(BooleanExpressionContext.class,0);
		}
		public BoolDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterBoolDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitBoolDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitBoolDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolDeclarationContext boolDeclaration() throws RecognitionException {
		BoolDeclarationContext _localctx = new BoolDeclarationContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_boolDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			match(T__32);
			setState(284);
			match(IDENTIFICATION);
			setState(287);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(285);
				match(ASSIGN);
				setState(286);
				booleanExpression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumberDeclarationContext extends ParserRuleContext {
		public TerminalNode IDENTIFICATION() { return getToken(LYnkParser.IDENTIFICATION, 0); }
		public TerminalNode ASSIGN() { return getToken(LYnkParser.ASSIGN, 0); }
		public ArithmeticExpressionContext arithmeticExpression() {
			return getRuleContext(ArithmeticExpressionContext.class,0);
		}
		public NumberDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numberDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterNumberDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitNumberDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitNumberDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberDeclarationContext numberDeclaration() throws RecognitionException {
		NumberDeclarationContext _localctx = new NumberDeclarationContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_numberDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289);
			match(T__33);
			setState(290);
			match(IDENTIFICATION);
			setState(293);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(291);
				match(ASSIGN);
				setState(292);
				arithmeticExpression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeleteDeclarationContext extends ParserRuleContext {
		public TerminalNode IDENTIFICATION() { return getToken(LYnkParser.IDENTIFICATION, 0); }
		public DeleteDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).enterDeleteDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LYnkListener ) ((LYnkListener)listener).exitDeleteDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LYnkVisitor ) return ((LYnkVisitor<? extends T>)visitor).visitDeleteDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeleteDeclarationContext deleteDeclaration() throws RecognitionException {
		DeleteDeclarationContext _localctx = new DeleteDeclarationContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_deleteDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			match(T__34);
			setState(296);
			match(IDENTIFICATION);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return booleanExpression_sempred((BooleanExpressionContext)_localctx, predIndex);
		case 3:
			return arithmeticExpression_sempred((ArithmeticExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean booleanExpression_sempred(BooleanExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 4);
		}
		return true;
	}
	private boolean arithmeticExpression_sempred(ArithmeticExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 6);
		case 4:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00016\u012b\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0001\u0000\u0005\u0000D\b\u0000"+
		"\n\u0000\f\u0000G\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001b\b\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002q\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002"+
		"v\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002{\b\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0005\u0002\u0084\b\u0002\n\u0002\f\u0002\u0087\t\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0003\u0003\u0092\b\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0005\u0003\u009e\b\u0003\n\u0003\f\u0003\u00a1"+
		"\t\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0003\u0007\u00ab\b\u0007\u0001\b\u0001"+
		"\b\u0003\b\u00af\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0003\n\u00b9\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00bf"+
		"\b\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0003\r\u00d4\b\r\u0001\r\u0001\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0012\u0001\u0012\u0005\u0012\u00e8\b\u0012\n\u0012"+
		"\f\u0012\u00eb\t\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u00f5\b\u0013"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0003\u0017\u0103\b\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u010d\b\u0019"+
		"\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0003\u001d"+
		"\u011a\b\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e"+
		"\u0120\b\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0003\u001f"+
		"\u0126\b\u001f\u0001 \u0001 \u0001 \u0001 \u0000\u0002\u0004\u0006!\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c"+
		"\u001e \"$&(*,.02468:<>@\u0000\u0007\u0001\u0000\u0003\u0004\u0001\u0000"+
		"+,\u0001\u0000-.\u0001\u000005\u0001\u000001\u0001\u0000+.\u0001\u0000"+
		"%&\u013d\u0000E\u0001\u0000\u0000\u0000\u0002a\u0001\u0000\u0000\u0000"+
		"\u0004z\u0001\u0000\u0000\u0000\u0006\u0091\u0001\u0000\u0000\u0000\b"+
		"\u00a2\u0001\u0000\u0000\u0000\n\u00a4\u0001\u0000\u0000\u0000\f\u00a6"+
		"\u0001\u0000\u0000\u0000\u000e\u00aa\u0001\u0000\u0000\u0000\u0010\u00ae"+
		"\u0001\u0000\u0000\u0000\u0012\u00b0\u0001\u0000\u0000\u0000\u0014\u00b4"+
		"\u0001\u0000\u0000\u0000\u0016\u00c2\u0001\u0000\u0000\u0000\u0018\u00c6"+
		"\u0001\u0000\u0000\u0000\u001a\u00ca\u0001\u0000\u0000\u0000\u001c\u00d7"+
		"\u0001\u0000\u0000\u0000\u001e\u00da\u0001\u0000\u0000\u0000 \u00dd\u0001"+
		"\u0000\u0000\u0000\"\u00e1\u0001\u0000\u0000\u0000$\u00e5\u0001\u0000"+
		"\u0000\u0000&\u00ee\u0001\u0000\u0000\u0000(\u00f6\u0001\u0000\u0000\u0000"+
		"*\u00f9\u0001\u0000\u0000\u0000,\u00fc\u0001\u0000\u0000\u0000.\u00ff"+
		"\u0001\u0000\u0000\u00000\u0104\u0001\u0000\u0000\u00002\u0107\u0001\u0000"+
		"\u0000\u00004\u010e\u0001\u0000\u0000\u00006\u0110\u0001\u0000\u0000\u0000"+
		"8\u0112\u0001\u0000\u0000\u0000:\u0115\u0001\u0000\u0000\u0000<\u011b"+
		"\u0001\u0000\u0000\u0000>\u0121\u0001\u0000\u0000\u0000@\u0127\u0001\u0000"+
		"\u0000\u0000BD\u0003\u0002\u0001\u0000CB\u0001\u0000\u0000\u0000DG\u0001"+
		"\u0000\u0000\u0000EC\u0001\u0000\u0000\u0000EF\u0001\u0000\u0000\u0000"+
		"FH\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000\u0000HI\u0005\u0000\u0000"+
		"\u0001I\u0001\u0001\u0000\u0000\u0000Jb\u0003\u0012\t\u0000Kb\u0003\u0016"+
		"\u000b\u0000Lb\u0003\u0014\n\u0000Mb\u0003\u0018\f\u0000Nb\u0003\u001a"+
		"\r\u0000Ob\u0003(\u0014\u0000Pb\u0003*\u0015\u0000Qb\u0003,\u0016\u0000"+
		"Rb\u00034\u001a\u0000Sb\u00036\u001b\u0000Tb\u0003.\u0017\u0000Ub\u0003"+
		"0\u0018\u0000Vb\u0003&\u0013\u0000Wb\u00032\u0019\u0000Xb\u0003\u001c"+
		"\u000e\u0000Yb\u0003\u001e\u000f\u0000Zb\u00038\u001c\u0000[b\u0003 \u0010"+
		"\u0000\\b\u0003\"\u0011\u0000]b\u0003<\u001e\u0000^b\u0003>\u001f\u0000"+
		"_b\u0003:\u001d\u0000`b\u0003@ \u0000aJ\u0001\u0000\u0000\u0000aK\u0001"+
		"\u0000\u0000\u0000aL\u0001\u0000\u0000\u0000aM\u0001\u0000\u0000\u0000"+
		"aN\u0001\u0000\u0000\u0000aO\u0001\u0000\u0000\u0000aP\u0001\u0000\u0000"+
		"\u0000aQ\u0001\u0000\u0000\u0000aR\u0001\u0000\u0000\u0000aS\u0001\u0000"+
		"\u0000\u0000aT\u0001\u0000\u0000\u0000aU\u0001\u0000\u0000\u0000aV\u0001"+
		"\u0000\u0000\u0000aW\u0001\u0000\u0000\u0000aX\u0001\u0000\u0000\u0000"+
		"aY\u0001\u0000\u0000\u0000aZ\u0001\u0000\u0000\u0000a[\u0001\u0000\u0000"+
		"\u0000a\\\u0001\u0000\u0000\u0000a]\u0001\u0000\u0000\u0000a^\u0001\u0000"+
		"\u0000\u0000a_\u0001\u0000\u0000\u0000a`\u0001\u0000\u0000\u0000b\u0003"+
		"\u0001\u0000\u0000\u0000cd\u0006\u0002\uffff\uffff\u0000de\u0005\u0001"+
		"\u0000\u0000ef\u0003\u0004\u0002\u0000fg\u0005\u0002\u0000\u0000g{\u0001"+
		"\u0000\u0000\u0000hi\u0005\u0005\u0000\u0000i{\u0003\u0004\u0002\u0007"+
		"jk\u0005$\u0000\u0000kl\u0003\b\u0004\u0000lm\u0005$\u0000\u0000m{\u0001"+
		"\u0000\u0000\u0000nq\u0003\u0006\u0003\u0000oq\u0005(\u0000\u0000pn\u0001"+
		"\u0000\u0000\u0000po\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000"+
		"ru\u0003\b\u0004\u0000sv\u0003\u0006\u0003\u0000tv\u0005(\u0000\u0000"+
		"us\u0001\u0000\u0000\u0000ut\u0001\u0000\u0000\u0000v{\u0001\u0000\u0000"+
		"\u0000w{\u0005$\u0000\u0000x{\u0005\u0006\u0000\u0000y{\u0005\u0007\u0000"+
		"\u0000zc\u0001\u0000\u0000\u0000zh\u0001\u0000\u0000\u0000zj\u0001\u0000"+
		"\u0000\u0000zp\u0001\u0000\u0000\u0000zw\u0001\u0000\u0000\u0000zx\u0001"+
		"\u0000\u0000\u0000zy\u0001\u0000\u0000\u0000{\u0085\u0001\u0000\u0000"+
		"\u0000|}\n\b\u0000\u0000}~\u0007\u0000\u0000\u0000~\u0084\u0003\u0004"+
		"\u0002\t\u007f\u0080\n\u0004\u0000\u0000\u0080\u0081\u0003\n\u0005\u0000"+
		"\u0081\u0082\u0003\u0004\u0002\u0005\u0082\u0084\u0001\u0000\u0000\u0000"+
		"\u0083|\u0001\u0000\u0000\u0000\u0083\u007f\u0001\u0000\u0000\u0000\u0084"+
		"\u0087\u0001\u0000\u0000\u0000\u0085\u0083\u0001\u0000\u0000\u0000\u0085"+
		"\u0086\u0001\u0000\u0000\u0000\u0086\u0005\u0001\u0000\u0000\u0000\u0087"+
		"\u0085\u0001\u0000\u0000\u0000\u0088\u0089\u0006\u0003\uffff\uffff\u0000"+
		"\u0089\u008a\u0005\u0001\u0000\u0000\u008a\u008b\u0003\u0006\u0003\u0000"+
		"\u008b\u008c\u0005\u0002\u0000\u0000\u008c\u0092\u0001\u0000\u0000\u0000"+
		"\u008d\u0092\u0005&\u0000\u0000\u008e\u0092\u0005%\u0000\u0000\u008f\u0092"+
		"\u0005\'\u0000\u0000\u0090\u0092\u0005$\u0000\u0000\u0091\u0088\u0001"+
		"\u0000\u0000\u0000\u0091\u008d\u0001\u0000\u0000\u0000\u0091\u008e\u0001"+
		"\u0000\u0000\u0000\u0091\u008f\u0001\u0000\u0000\u0000\u0091\u0090\u0001"+
		"\u0000\u0000\u0000\u0092\u009f\u0001\u0000\u0000\u0000\u0093\u0094\n\u0007"+
		"\u0000\u0000\u0094\u0095\u0007\u0001\u0000\u0000\u0095\u009e\u0003\u0006"+
		"\u0003\b\u0096\u0097\n\u0006\u0000\u0000\u0097\u0098\u0007\u0002\u0000"+
		"\u0000\u0098\u009e\u0003\u0006\u0003\u0007\u0099\u009a\n\u0005\u0000\u0000"+
		"\u009a\u009b\u0003\f\u0006\u0000\u009b\u009c\u0003\u0006\u0003\u0006\u009c"+
		"\u009e\u0001\u0000\u0000\u0000\u009d\u0093\u0001\u0000\u0000\u0000\u009d"+
		"\u0096\u0001\u0000\u0000\u0000\u009d\u0099\u0001\u0000\u0000\u0000\u009e"+
		"\u00a1\u0001\u0000\u0000\u0000\u009f\u009d\u0001\u0000\u0000\u0000\u009f"+
		"\u00a0\u0001\u0000\u0000\u0000\u00a0\u0007\u0001\u0000\u0000\u0000\u00a1"+
		"\u009f\u0001\u0000\u0000\u0000\u00a2\u00a3\u0007\u0003\u0000\u0000\u00a3"+
		"\t\u0001\u0000\u0000\u0000\u00a4\u00a5\u0007\u0004\u0000\u0000\u00a5\u000b"+
		"\u0001\u0000\u0000\u0000\u00a6\u00a7\u0007\u0005\u0000\u0000\u00a7\r\u0001"+
		"\u0000\u0000\u0000\u00a8\u00ab\u0003\u0006\u0003\u0000\u00a9\u00ab\u0005"+
		")\u0000\u0000\u00aa\u00a8\u0001\u0000\u0000\u0000\u00aa\u00a9\u0001\u0000"+
		"\u0000\u0000\u00ab\u000f\u0001\u0000\u0000\u0000\u00ac\u00af\u0003\u0006"+
		"\u0003\u0000\u00ad\u00af\u0005)\u0000\u0000\u00ae\u00ac\u0001\u0000\u0000"+
		"\u0000\u00ae\u00ad\u0001\u0000\u0000\u0000\u00af\u0011\u0001\u0000\u0000"+
		"\u0000\u00b0\u00b1\u0005\b\u0000\u0000\u00b1\u00b2\u0003\u0004\u0002\u0000"+
		"\u00b2\u00b3\u0003$\u0012\u0000\u00b3\u0013\u0001\u0000\u0000\u0000\u00b4"+
		"\u00b5\u0005\t\u0000\u0000\u00b5\u00b8\u0005$\u0000\u0000\u00b6\u00b7"+
		"\u0005\n\u0000\u0000\u00b7\u00b9\u0007\u0006\u0000\u0000\u00b8\u00b6\u0001"+
		"\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9\u00ba\u0001"+
		"\u0000\u0000\u0000\u00ba\u00bb\u0005\u000b\u0000\u0000\u00bb\u00be\u0007"+
		"\u0006\u0000\u0000\u00bc\u00bd\u0005\f\u0000\u0000\u00bd\u00bf\u0007\u0006"+
		"\u0000\u0000\u00be\u00bc\u0001\u0000\u0000\u0000\u00be\u00bf\u0001\u0000"+
		"\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0\u00c1\u0003$\u0012"+
		"\u0000\u00c1\u0015\u0001\u0000\u0000\u0000\u00c2\u00c3\u0005\r\u0000\u0000"+
		"\u00c3\u00c4\u0003\u0004\u0002\u0000\u00c4\u00c5\u0003$\u0012\u0000\u00c5"+
		"\u0017\u0001\u0000\u0000\u0000\u00c6\u00c7\u0005\u000e\u0000\u0000\u00c7"+
		"\u00c8\u0005%\u0000\u0000\u00c8\u00c9\u0003$\u0012\u0000\u00c9\u0019\u0001"+
		"\u0000\u0000\u0000\u00ca\u00d3\u0005\u000f\u0000\u0000\u00cb\u00cc\u0003"+
		"\u000e\u0007\u0000\u00cc\u00cd\u0003\u0010\b\u0000\u00cd\u00d4\u0001\u0000"+
		"\u0000\u0000\u00ce\u00cf\u0003\u000e\u0007\u0000\u00cf\u00d0\u0003\u0010"+
		"\b\u0000\u00d0\u00d1\u0003\u000e\u0007\u0000\u00d1\u00d2\u0003\u0010\b"+
		"\u0000\u00d2\u00d4\u0001\u0000\u0000\u0000\u00d3\u00cb\u0001\u0000\u0000"+
		"\u0000\u00d3\u00ce\u0001\u0000\u0000\u0000\u00d4\u00d5\u0001\u0000\u0000"+
		"\u0000\u00d5\u00d6\u0003$\u0012\u0000\u00d6\u001b\u0001\u0000\u0000\u0000"+
		"\u00d7\u00d8\u0005\u0010\u0000\u0000\u00d8\u00d9\u0003\u000e\u0007\u0000"+
		"\u00d9\u001d\u0001\u0000\u0000\u0000\u00da\u00db\u0005\u0011\u0000\u0000"+
		"\u00db\u00dc\u0003\u000e\u0007\u0000\u00dc\u001f\u0001\u0000\u0000\u0000"+
		"\u00dd\u00de\u0005\u0012\u0000\u0000\u00de\u00df\u0003\u000e\u0007\u0000"+
		"\u00df\u00e0\u0003\u0010\b\u0000\u00e0!\u0001\u0000\u0000\u0000\u00e1"+
		"\u00e2\u0005\u0013\u0000\u0000\u00e2\u00e3\u0003\u000e\u0007\u0000\u00e3"+
		"\u00e4\u0003\u0010\b\u0000\u00e4#\u0001\u0000\u0000\u0000\u00e5\u00e9"+
		"\u0005\u0014\u0000\u0000\u00e6\u00e8\u0003\u0002\u0001\u0000\u00e7\u00e6"+
		"\u0001\u0000\u0000\u0000\u00e8\u00eb\u0001\u0000\u0000\u0000\u00e9\u00e7"+
		"\u0001\u0000\u0000\u0000\u00e9\u00ea\u0001\u0000\u0000\u0000\u00ea\u00ec"+
		"\u0001\u0000\u0000\u0000\u00eb\u00e9\u0001\u0000\u0000\u0000\u00ec\u00ed"+
		"\u0005\u0015\u0000\u0000\u00ed%\u0001\u0000\u0000\u0000\u00ee\u00f4\u0005"+
		"\u0016\u0000\u0000\u00ef\u00f5\u0005*\u0000\u0000\u00f0\u00f1\u0003\u0006"+
		"\u0003\u0000\u00f1\u00f2\u0003\u0006\u0003\u0000\u00f2\u00f3\u0003\u0006"+
		"\u0003\u0000\u00f3\u00f5\u0001\u0000\u0000\u0000\u00f4\u00ef\u0001\u0000"+
		"\u0000\u0000\u00f4\u00f0\u0001\u0000\u0000\u0000\u00f5\'\u0001\u0000\u0000"+
		"\u0000\u00f6\u00f7\u0005\u0017\u0000\u0000\u00f7\u00f8\u0005%\u0000\u0000"+
		"\u00f8)\u0001\u0000\u0000\u0000\u00f9\u00fa\u0005\u0018\u0000\u0000\u00fa"+
		"\u00fb\u0005%\u0000\u0000\u00fb+\u0001\u0000\u0000\u0000\u00fc\u00fd\u0005"+
		"\u0019\u0000\u0000\u00fd\u00fe\u0005%\u0000\u0000\u00fe-\u0001\u0000\u0000"+
		"\u0000\u00ff\u0102\u0005\u001a\u0000\u0000\u0100\u0103\u0003\u0006\u0003"+
		"\u0000\u0101\u0103\u0005)\u0000\u0000\u0102\u0100\u0001\u0000\u0000\u0000"+
		"\u0102\u0101\u0001\u0000\u0000\u0000\u0103/\u0001\u0000\u0000\u0000\u0104"+
		"\u0105\u0005\u001b\u0000\u0000\u0105\u0106\u0003\u0006\u0003\u0000\u0106"+
		"1\u0001\u0000\u0000\u0000\u0107\u010c\u0005\u001c\u0000\u0000\u0108\u010d"+
		"\u0005%\u0000\u0000\u0109\u010a\u0003\u000e\u0007\u0000\u010a\u010b\u0003"+
		"\u0010\b\u0000\u010b\u010d\u0001\u0000\u0000\u0000\u010c\u0108\u0001\u0000"+
		"\u0000\u0000\u010c\u0109\u0001\u0000\u0000\u0000\u010d3\u0001\u0000\u0000"+
		"\u0000\u010e\u010f\u0005\u001d\u0000\u0000\u010f5\u0001\u0000\u0000\u0000"+
		"\u0110\u0111\u0005\u001e\u0000\u0000\u01117\u0001\u0000\u0000\u0000\u0112"+
		"\u0113\u0005\u001f\u0000\u0000\u0113\u0114\u0003\u0006\u0003\u0000\u0114"+
		"9\u0001\u0000\u0000\u0000\u0115\u0116\u0005 \u0000\u0000\u0116\u0119\u0005"+
		"$\u0000\u0000\u0117\u0118\u0005/\u0000\u0000\u0118\u011a\u0005(\u0000"+
		"\u0000\u0119\u0117\u0001\u0000\u0000\u0000\u0119\u011a\u0001\u0000\u0000"+
		"\u0000\u011a;\u0001\u0000\u0000\u0000\u011b\u011c\u0005!\u0000\u0000\u011c"+
		"\u011f\u0005$\u0000\u0000\u011d\u011e\u0005/\u0000\u0000\u011e\u0120\u0003"+
		"\u0004\u0002\u0000\u011f\u011d\u0001\u0000\u0000\u0000\u011f\u0120\u0001"+
		"\u0000\u0000\u0000\u0120=\u0001\u0000\u0000\u0000\u0121\u0122\u0005\""+
		"\u0000\u0000\u0122\u0125\u0005$\u0000\u0000\u0123\u0124\u0005/\u0000\u0000"+
		"\u0124\u0126\u0003\u0006\u0003\u0000\u0125\u0123\u0001\u0000\u0000\u0000"+
		"\u0125\u0126\u0001\u0000\u0000\u0000\u0126?\u0001\u0000\u0000\u0000\u0127"+
		"\u0128\u0005#\u0000\u0000\u0128\u0129\u0005$\u0000\u0000\u0129A\u0001"+
		"\u0000\u0000\u0000\u0016Eapuz\u0083\u0085\u0091\u009d\u009f\u00aa\u00ae"+
		"\u00b8\u00be\u00d3\u00e9\u00f4\u0102\u010c\u0119\u011f\u0125";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}