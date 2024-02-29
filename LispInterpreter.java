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
                return performArithmeticOperation(expression, true);
            case 3:
                return performArithmeticOperation(expression, false);
            case 4:
                throw new RuntimeException("Invalid expression: " + expression);
            default:
                throw new RuntimeException("Unexpected state");
        }
    }

    private IOperationResult setVariable(String expression) {
        Pattern pattern = Pattern.compile("[(]\\s*setq\\s+([a-z]+)\\s+([0-9]+)\\s*[)]", Pattern.CASE_INSENSITIVE);
        // Implementar la lógica para establecer la variable según el patrón
        // y devolver el resultado adecuado
        return null;
    }

    private IOperationResult performArithmeticOperation(String expression, boolean isAddition) {
        AritmethicOperationResult result = new AritmethicOperationResult();
        if (isAddition) {
            return result.addOperation(expression, context);
        } else {
            return result.substractOperation(expression, context);
        }
    }
}