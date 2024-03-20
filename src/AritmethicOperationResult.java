import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the result of an arithmetic operation.
 * Implements the {@link IOperationResult} interface.
 */
/**
 * Represents the result of an arithmetic operation.
 * Implements the {@link IOperationResult} interface.
 */
public class AritmethicOperationResult implements IOperationResult {

    private String key;
    private String result;

    @Override
    public void performOperation() {
        System.out.println("El resultado de la operación " + key + " es: " + result);
    }

    public void addResults(String key, String result) {
        this.key = key;
        this.result = result;
    }

    public IOperationResult addOperation(String expression, ExecutionContext context) {
        Pattern pattern = Pattern.compile("([a-z]+|\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression);
        int total = 0;

        while (matcher.find()) {
            String token = matcher.group().trim();

            if (Character.isDigit(token.charAt(0))) {
                total += Integer.parseInt(token);
            } else {
                // Si el token es una variable, verifica si está en el contexto
                if (context.getVariable(token) != null) {
                    total += Integer.parseInt(context.getVariable(token));
                } else {
                    throw new RuntimeException("Variable '" + token + "' no definida");
                }
            }
        }

        AritmethicOperationResult myResult = new AritmethicOperationResult();
        myResult.addResults("suma", " " + total);
        return myResult;
    }

    public IOperationResult substractOperation(String expression, ExecutionContext context) {
    Pattern pattern = Pattern.compile("([a-z]+|\\d+)", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(expression);
    boolean isFirstToken = true;
    int total = 0;

    while (matcher.find()) {
        String token = matcher.group().trim();

        if (Character.isDigit(token.charAt(0))) {
            if (isFirstToken) {
                total = Integer.parseInt(token);
                isFirstToken = false;
            } else {
                total -= Integer.parseInt(token);
            }
        } else {
            // Si el token es una variable, verifica si está en el contexto
            if (context.getVariable(token) != null) {
                if (isFirstToken) {
                    total = Integer.parseInt(context.getVariable(token));
                    isFirstToken = false;
                } else {
                    total -= Integer.parseInt(context.getVariable(token));
                }
            } else {
                throw new RuntimeException("Variable '" + token + "' no definida");
            }
        }
    }

    AritmethicOperationResult myResult = new AritmethicOperationResult();
    myResult.addResults("resta", " " + total);
    return myResult;
}


    public IOperationResult divisionOperation(String expression, ExecutionContext context) {
        Pattern pattern = Pattern.compile("([a-z]+|\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression);
        int total = 1; // Inicializar con el valor neutro de la división (1)

        if (matcher.find()) {
            String firstToken = matcher.group().trim();
            total = Character.isDigit(firstToken.charAt(0)) ? Integer.parseInt(firstToken) : Integer.parseInt(context.getVariable(firstToken));

            while (matcher.find()) {
                String token = matcher.group().trim();
                if (Character.isDigit(token.charAt(0))) {
                    if (Integer.parseInt(token) == 0) {
                        throw new RuntimeException("División por cero");
                    }
                    total /= Integer.parseInt(token);
                } else {
                    if (context.getVariable(token) != null) {
                        int value = Integer.parseInt(context.getVariable(token));
                        if (value == 0) {
                            throw new RuntimeException("División por cero");
                        }
                        total /= value;
                    } else {
                        throw new RuntimeException("Variable '" + token + "' no definida");
                    }
                }
            }
        } else {
            throw new RuntimeException("Expresión no válida: " + expression);
        }

        AritmethicOperationResult myResult = new AritmethicOperationResult();
        myResult.addResults("división", " " + total);
        return myResult;
    }

    public IOperationResult multiplicationOperation(String expression, ExecutionContext context) {
        Pattern pattern = Pattern.compile("([a-z]+|\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression);
        int total = 1; // Inicializar con el valor neutro de la multiplicación (1)

        if (matcher.find()) {
            String firstToken = matcher.group().trim();
            total = Character.isDigit(firstToken.charAt(0)) ? Integer.parseInt(firstToken) : Integer.parseInt(context.getVariable(firstToken));

            while (matcher.find()) {
                String token = matcher.group().trim();
                if (Character.isDigit(token.charAt(0))) {
                    total *= Integer.parseInt(token);
                } else {
                    if (context.getVariable(token) != null) {
                        total *= Integer.parseInt(context.getVariable(token));
                    } else {
                        throw new RuntimeException("Variable '" + token + "' no definida");
                    }
                }
            }
        } else {
            throw new RuntimeException("Expresión no válida: " + expression);
        }

        AritmethicOperationResult myResult = new AritmethicOperationResult();
        myResult.addResults("multiplicación", " " + total);
        return myResult;
    }
}
