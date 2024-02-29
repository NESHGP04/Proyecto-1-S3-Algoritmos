import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LispInterpreter {
    private Map<String, Integer> environment; // Guarda variables del ambiente

    public LispInterpreter() {
        environment = new HashMap<>();
    }

    public IOperationResult operate(String expression) {
        int state = SintaxScanner.getState(expression);

        switch (state) {
            // Agrega casos adicionales según las necesidades
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

    public IOperationResult performArithmeticOperation(String expression) {
        return arithmethicOperation(expression, "+");
    }

    public IOperationResult performSubtractionOperation(String expression) {
        return arithmethicOperation(expression, "-");
    }

public IOperationResult arithmethicOperation(String expression, String operation) {
    Pattern pattern = Pattern.compile("([a-z]+|\\d+)", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(expression);

    int total = 0;

    while (matcher.find()) {
        String token = matcher.group().trim();

        if (Character.isDigit(token.charAt(0))) {
            total += Integer.parseInt(token);
        } else {
            // Si el token es una variable, verifica si está en el ambiente
            if (environment.containsKey(token)) {
                total += environment.get(token);
            } else {
                throw new RuntimeException("Variable '" + token + "' no definida");
            }
        }
    }

    AritmethicOperationResult result = new AritmethicOperationResult();
    result.addResults(operation, " " + total);
    return result;
    }
}
    // Agrega métodos para manejar otras operaciones según sea necesario
