import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LispInterpreter {
    String key;
    String result;
    
    private Map<String, Integer> environment; //guarda variables del ambiente
    AritmethicOperationResult aritmethicOperationResult = new AritmethicOperationResult();

    public LispInterpreter(){
        environment = new  HashMap<String,Integer>();
    }

    public IOperationResult Operate(String expresion){
        int state = SintaxScanner.getState(expresion);
        switch (state) {
            //case 1: return variableAssigment(expresion);
            case 2: return aritmethicOperationResult.addOperation(expresion);
            case 3: return aritmethicOperationResult.substractOperation(expresion);
            default : throw new RuntimeException("Invalid expression");
        }
    }

    public void performOperation(){
        System.out.println("El resultado de la operación "+ key + " fue efectuado");
    }

    // public String functionName;
    // public List<String> list;
    // private String var;
    // private Object val;
    // private String funcName;
    // private Object body;
    // private IEvaluator function;

    // public LispInterpreter(){
    //     this.environment = new HashMap<>();
    // }
}
