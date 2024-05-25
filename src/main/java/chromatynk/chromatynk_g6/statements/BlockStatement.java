package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

import java.util.ArrayList;
import java.util.List;

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
        String blockMsg = "{ ";
        for(Statement statement : block){
            blockMsg = blockMsg + " " + statement.toString();
        }
        blockMsg = blockMsg + " }";
        return blockMsg;
    }

    /*
    public void execute(){
        for(Statement currentStatement : this.block){
            currentStatement.setVarContext(this.getVarContext());
            currentStatement.execute();
            this.setVarContext(currentStatement.getVarContext());
        }
    }*/
}
