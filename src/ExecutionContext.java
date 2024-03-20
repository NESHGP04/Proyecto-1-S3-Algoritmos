import java.util.HashMap;
import java.util.Map;

/**
 * The ExecutionContext class represents the execution context of a program.
 * It stores variables and provides methods to set and retrieve their values.
 */
public class ExecutionContext {
    private Map<String, String> variables = new HashMap<>();

    /**
     * Sets the value of a variable in the execution context.
     * If the variable already exists, its value is updated.
     * If the variable does not exist, a new variable is created.
     *
     * @param name  the name of the variable
     * @param value the value of the variable
     */
    public void setVariable(String name, String value){
        variables.put(name, value);
    }

    /**
     * Retrieves the value of a variable from the execution context.
     * If the variable does not exist, the method returns "NIL".
     *
     * @param name the name of the variable
     * @return the value of the variable, or "NIL" if the variable does not exist
     */
    public String getVariable(String name){
        return variables.getOrDefault(name, "NIL");
    }

    /**
     * Creates a clone of the execution context.
     * The clone contains a copy of all the variables in the original execution context.
     *
     * @return a clone of the execution context
     */
    public ExecutionContext clone(){
        ExecutionContext newClone = new ExecutionContext();
        newClone.variables.putAll(this.variables);
        return newClone;
    }
}
