import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        int total = 0;

        while (matcher.find()) {
            String token = matcher.group().trim();

            if (Character.isDigit(token.charAt(0))) {
                total -= Integer.parseInt(token);
            } else {
                // Si el token es una variable, verifica si está en el contexto
                if (context.getVariable(token) != null) {
                    total -= Integer.parseInt(context.getVariable(token));
                } else {
                    throw new RuntimeException("Variable '" + token + "' no definida");
                }
            }
        }

        AritmethicOperationResult myResult = new AritmethicOperationResult();
        myResult.addResults("resta", " " + total);
        return myResult;
    }
}
