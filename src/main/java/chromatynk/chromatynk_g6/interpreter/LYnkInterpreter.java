package chromatynk.chromatynk_g6.interpreter;

import chromatynk.chromatynk_g6.LYnk.LYnkLexer;
import chromatynk.chromatynk_g6.LYnk.LYnkParser;
import chromatynk.chromatynk_g6.diagnostic.LYnkConsole;
import chromatynk.chromatynk_g6.diagnostic.LYnkIssue;
import chromatynk.chromatynk_g6.exceptions.CodeValidateException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class LYnkInterpreter {

    public static final Object VOID = new VoidObject();

    public LYnkInterpreter(final OutputStream stdoutStream){
        //this.stdout = new PrintStream(stdoutStream, true, StandardCharsets.UTF_8);
    }

    public static class VoidObject{
        @Override
        public String toString(){ return "VOID"; }
    }
}