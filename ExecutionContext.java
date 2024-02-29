import java.util.HashMap;
import java.util.Map;

public class ExecutionContext {
    private Map<String, String> variables = new HashMap<>();

    public void setVariable(String name, String value){
        variables.put(name, value);
    }

    public String getVariable(String name){
        return variables.getOrDefault(name, "NIL");
    }

    public ExecutionContext clone(){
        ExecutionContext newClone = new ExecutionContext();
        newClone.variables.putAll(this.variables);
        return newClone;
    }
}
