import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LispInterpreter {

    private ExecutionContext context;

    public LispInterpreter() {
        context = new ExecutionContext();
    }

    public IOperationResult operate(String expression) {
        int state = SintaxScanner.getState(expression);

        switch (state) {
            case 1:
                return setVariable(expression);
            case 2:
                return performArithmeticOperation(expression);
            case 3:
                return performSubtractionOperation(expression);
            case 4:
                throw new RuntimeException("a");
            default:
                throw new RuntimeException("Invalid expression");
        }
    }

    private IOperationResult setVariable(String expression) {
        Pattern pattern = Pattern.compile("[(]\\s*setq\\s+([a-z]+)\\s+([0-9]+)\\s*[)]", Pattern.CASE_INSENSITIVE);
        pattern.matcher(expression);
        
        return null;
    }

    private IOperationResult performArithmeticOperation(String expression) {
        AritmethicOperationResult result = new AritmethicOperationResult();
        return result.addOperation(expression, context);
    }

    private IOperationResult performSubtractionOperation(String expression) {
        AritmethicOperationResult result = new AritmethicOperationResult();
        return result.substractOperation(expression, context);
    }
}
