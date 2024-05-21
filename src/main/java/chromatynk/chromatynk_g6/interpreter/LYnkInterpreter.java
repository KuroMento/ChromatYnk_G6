package chromatynk.chromatynk_g6.interpreter;

import chromatynk.chromatynk_g6.LYnk.LYnkLexer;
import chromatynk.chromatynk_g6.LYnk.LYnkParser;
import chromatynk.chromatynk_g6.diagnostic.LYnkConsole;
import chromatynk.chromatynk_g6.diagnostic.LYnkIssue;
import chromatynk.chromatynk_g6.exceptions.CodeValidateException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

@Deprecated
public class LYnkInterpreter {

    public static final Object VOID = new VoidObject();
    private final PrintStream stdout;

    public LYnkInterpreter(final OutputStream stdoutStream){
        this.stdout = new PrintStream(stdoutStream, true, StandardCharsets.UTF_8);
    }

    public LYnkInterpreter(final PrintStream stdout) {
        this.stdout = stdout;
    }

    public LYnkInterpreter() {
        this(System.out);
    }

    public Object eval(final String input, final List<LYnkIssue> warnings) throws CodeValidateException {
        // parsing of the source code in tokens
        final LYnkLexer lexer = new LYnkLexer(CharStreams.fromString(input));
        // create an AST-tree
        final LYnkParser parser = new LYnkParser(new CommonTokenStream(lexer));
        final List<LYnkIssue> issues = validate(input);

        if(issues.stream().anyMatch(LYnkIssue::isError)){
            throw new CodeValidateException(issues);
        }

        if (!issues.isEmpty()){
            warnings.addAll(issues);
        }
        // create an object of the class LYnkInterpreterVisitor
        final LYnkInterpreterVisitor interpreterVisitor = new LYnkInterpreterVisitor(new LYnkVariableImpl(stdout));
        // start the interpreter
        return interpreterVisitor.visitProgram(parser.program());
    }

    public List<LYnkIssue> validate(final String input){
        final LYnkLexer lexer = new LYnkLexer(CharStreams.fromString(input));
        final LYnkParser parser = new LYnkParser(new CommonTokenStream(lexer));
        final LYnkConsole console = new LYnkConsole();
        //remove standard listeners
        lexer.removeErrorListeners();
        parser.removeErrorListeners();
        //add our listeners
        lexer.addErrorListener(console);
        parser.addErrorListener(console);
        console.visitProgram(parser.program());
        return console.getIssues();

    }

    public static class VoidObject{
        @Override
        public String toString(){ return "VOID"; }
    }
}