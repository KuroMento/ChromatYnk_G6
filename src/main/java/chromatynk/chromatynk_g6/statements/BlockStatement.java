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

    public List<Statement> getBlock() {
        return block;
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
