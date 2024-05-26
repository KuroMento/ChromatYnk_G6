package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * A block statement's representation
 */
public class BlockStatement extends Statement{
    private List<Statement> block;

    public BlockStatement(LYnkVariableImpl varContext){
        super("BLOCK", varContext);
        this.block = new ArrayList<>();
    }

    public void addStatement(Statement statement){
        block.add(statement);
    }

    public List<Statement> getBlock() {
        return block;
    }

    @Override
    public String toString() {
        String blockMsg = "{\n";
        for(Statement statement : block){
            blockMsg = blockMsg + statement.toString();
        }
        blockMsg = blockMsg + "}";
        return blockMsg;
    }
}
