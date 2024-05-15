package chromatynk.chromatynk_g6.interpreter;

import chromatynk.chromatynk_g6.LYnk.LYnkLexer;
import chromatynk.chromatynk_g6.LYnk.LYnkParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class LYnkInterpreter {
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

    public Object eval(final String input){
        // parsing of the source code in tokens
        final LYnkLexer lexer = new LYnkLexer(CharStreams.fromString(input));
        // create an AST-tree
        final LYnkParser parser = new LYnkParser(new CommonTokenStream(lexer));
        // create an object of the class LYnkInterpreterVisitor
        final LYnkInterpreterVisitor interpreterVisitor = new LYnkInterpreterVisitor(new LYnkContextImpl(stdout));
        // start the interpreter
        return interpreterVisitor.visitProgram(parser.program());
    }

}