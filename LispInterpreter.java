import java.util.HashMap;
import java.util.Map;

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
            default:
                throw new RuntimeException("Invalid expression");
        }
    }

    private IOperationResult setVariable(String expression) {
        // Implementa la lógica para el caso 1 (setq)
        // Puedes usar expresiones regulares para extraer el nombre de la variable y su valor
        // y luego agregarlos al contexto
        // Ejemplo: (setq variable 42)
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
