package chromatynk.chromatynk_g6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandVariable {
    //Array of Variable
    private Map<String, Variable> variableArray;

    /**
     * Constructor of CommandVariable
     */
    public CommandVariable(){
        this.variableArray = new HashMap<String, Variable>();
    }

    //Delete the variable name
    public void delete(String name){
        //If the variable does not exist
        //Insert the call to the method that display the issue to the user
        if(!variableArray.containsKey(name)) System.out.println("The variable " + name + " does not exist");
        //If the variable exist we remove it
        else{
            variableArray.remove(name);
        }
    }

    //Change the value of name variable
    public void setVariableString(String name, String value){
        //If the variable does not exist
        //Insert the call to the method that display the issue to the user
        if(!variableArray.containsKey(name)) System.out.println("The variable " + name + " does not exist");
        //If the variable exist
        else{
            Variable var = variableArray.get(name);
            //If the variable is not a String variable
            if(!(var instanceof VariableSTR)){
                //Throws error name not a String variable (to implement)
                System.out.println("The variable " + name + " is not a String variable");
            }
            else{
                //Change the value of name variable
                ((VariableSTR) var).setValue(value);
            }
        }
    }
}
