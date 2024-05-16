package chromatynk.chromatynk_g6.diagnostic;

import chromatynk.chromatynk_g6.LYnk.LYnkBaseVisitor;
import chromatynk.chromatynk_g6.LYnk.LYnkParser;
import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.PrintStream;
import java.util.BitSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static chromatynk.chromatynk_g6.diagnostic.LYnkValidation.SKIP_ERROR;

public class LYnkConsole extends LYnkBaseVisitor<LYnkValidation> implements ANTLRErrorListener {

    /**
     * A set of issues linked to the given code
     */
    private Set<LYnkIssue> issues;

    private LYnkVariableImpl varContext;

    //When the value is false, the syntaxError method returns without displaying errors.
    private static final boolean REPORT_SYNTAX_ERRORS = true;
    private String errorMsg = "";

    public LYnkConsole(){
        this.issues = new LinkedHashSet<>(0);
        this.varContext = new LYnkVariableImpl(new PrintStream(stdout));
    }

    /**
     * Visite the whole program to verify for errors.
     * @param ctx the parse tree
     * @return The validation indicating of an error if they occur
     */
    @Override
    public LYnkValidation visitProgram(final LYnkParser.ProgramContext ctx){
        final LYnkValidation result = super.visitProgram(ctx);
        return result;
    }

    /**
     * Overriding visit function to check for error in the given code with <code>LYnkValidation</code> class to detect those.
     * @param tree A tree representing a rule
     * @return The type of the Object in check or SKIP_ERROR if an error should occur
     */
    @Override
    public LYnkValidation visit(final ParseTree tree) {
        if (tree instanceof ParserRuleContext ruleContext && ruleContext.exception != null) {
            // skip validating on syntax error
            return SKIP_ERROR;
        }

        return super.visit(tree);
    }

    // Arithmetic Expression Visit
    @Override
    public LYnkValidation visitParenthesisExpression(LYnkParser.ParenthesisExpressionContext ctx){
        return visit(ctx.arithmeticExpression());
    }

    @Override
    public LYnkValidation visitMulDivExpression(LYnkParser.MulDivExpressionContext ctx){
        final LYnkValidation left = visit(ctx.left);
        if(left.isSkipError()){
            return SKIP_ERROR;
        }
        final LYnkValidation right = visit(ctx.right);
        if(right.isSkipError()){
            return SKIP_ERROR;
        }
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException re){
        if(!REPORT_SYNTAX_ERRORS){
            return;
        }

        String sourceName = recognizer.getInputStream().getSourceName();
        if (!sourceName.isEmpty()) {
            sourceName = String.format("%s:%d:%d: ", sourceName, line, charPositionInLine);
        }

        System.err.println(sourceName+"line "+line+":"+charPositionInLine+" "+msg);
        this.errorMsg = this.errorMsg + "\n" + sourceName+"line "+line+":"+charPositionInLine+" "+msg;
    }

    @Override
    public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean b, BitSet bitSet, ATNConfigSet atnConfigSet) {
        //no implementation at the moment
    }

    @Override
    public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitSet, ATNConfigSet atnConfigSet) {
        //no implementation at the moment
    }

    @Override
    public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atnConfigSet) {
        //no implementation at the moment
    }
}
