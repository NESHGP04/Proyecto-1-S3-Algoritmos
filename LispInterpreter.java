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
            case 2: // Suma
            case 3: // Resta
            case 4: // División
            case 5: // Multiplicación
                return performArithmeticOperation(expression, state);
            default:
                throw new RuntimeException("Unexpected state: " + state);
        }
    }

    private IOperationResult setVariable(String expression) {
        Pattern pattern = Pattern.compile("[(]\\s*setq\\s+([a-z]+)\\s+([0-9]+)\\s*[)]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression);
        if (matcher.matches()) {
            String variable = matcher.group(1);
            String value = matcher.group(2);
            context.setVariable(variable, value);
            AritmethicOperationResult result = new AritmethicOperationResult();
            result.addResults("asignación", " " + variable + " = " + value);
            return result;
        } else {
            throw new RuntimeException("Expresión de asignación no válida: " + expression);
        }
    }

    private IOperationResult performArithmeticOperation(String expression, int operation) {
        AritmethicOperationResult result = new AritmethicOperationResult();
        switch (operation) {
            case 2: // Suma
                return result.addOperation(expression, context);
            case 3: // Resta
                return result.substractOperation(expression, context);
            case 4: // División
                return result.divisionOperation(expression, context);
            case 5: // Multiplicación
                return result.multiplicationOperation(expression, context);
            default:
                throw new RuntimeException("Unexpected operation");
        }
    }
}
