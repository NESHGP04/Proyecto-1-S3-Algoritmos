import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the result of an arithmetic operation.
 * Implements the {@link IOperationResult} interface.
 */
public class AritmethicOperationResult implements IOperationResult {

    private String key;
    private String result;

    /**
     * Performs the arithmetic operation.
     */
    @Override
    public void performOperation() {
        System.out.println("El resultado de la operación " + key + " es: " + result);
    }

    /**
     * Adds the results of the arithmetic operation.
     * 
     * @param key The key representing the type of operation.
     * @param result The result of the operation.
     */
    public void addResults(String key, String result) {
        this.key = key;
        this.result = result;
    }

    /**
     * Performs an addition operation.
     * 
     * @param expression The arithmetic expression.
     * @param context The execution context containing variables.
     * @return The result of the addition operation.
     */
    public IOperationResult addOperation(String expression, ExecutionContext context) {
        Pattern pattern = Pattern.compile("([a-z]+|\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression);
        int total = 0;

        while (matcher.find()) {
            String token = matcher.group().trim();

            if (Character.isDigit(token.charAt(0))) {
                total += Integer.parseInt(token);
            } else {
                // If the token is a variable, check if it's in the context
                if (context.getVariable(token) != null) {
                    total += Integer.parseInt(context.getVariable(token));
                } else {
                    throw new RuntimeException("Variable '" + token + "' not defined");
                }
            }
        }

        AritmethicOperationResult myResult = new AritmethicOperationResult();
        myResult.addResults("suma", " " + total);
        return myResult;
    }

    /**
     * Performs a subtraction operation.
     * 
     * @param expression The arithmetic expression.
     * @param context The execution context containing variables.
     * @return The result of the subtraction operation.
     */
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
                // If the token is a variable, check if it's in the context
                if (context.getVariable(token) != null) {
                    if (isFirstToken) {
                        total = Integer.parseInt(context.getVariable(token));
                        isFirstToken = false;
                    } else {
                        total -= Integer.parseInt(context.getVariable(token));
                    }
                } else {
                    throw new RuntimeException("Variable '" + token + "' not defined");
                }
            }
        }

        AritmethicOperationResult myResult = new AritmethicOperationResult();
        myResult.addResults("resta", " " + total);
        return myResult;
    }

    /**
     * Performs a division operation.
     * 
     * @param expression The arithmetic expression.
     * @param context The execution context containing variables.
     * @return The result of the division operation.
     */
    public IOperationResult divisionOperation(String expression, ExecutionContext context) {
        Pattern pattern = Pattern.compile("([a-z]+|\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression);
        int total = 1; // Initialize with the division's neutral value (1)

        if (matcher.find()) {
            String firstToken = matcher.group().trim();
            total = Character.isDigit(firstToken.charAt(0)) ? Integer.parseInt(firstToken) : Integer.parseInt(context.getVariable(firstToken));

            while (matcher.find()) {
                String token = matcher.group().trim();
                if (Character.isDigit(token.charAt(0))) {
                    if (Integer.parseInt(token) == 0) {
                        throw new RuntimeException("Division by zero");
                    }
                    total /= Integer.parseInt(token);
                } else {
                    if (context.getVariable(token) != null) {
                        int value = Integer.parseInt(context.getVariable(token));
                        if (value == 0) {
                            throw new RuntimeException("Division by zero");
                        }
                        total /= value;
                    } else {
                        throw new RuntimeException("Variable '" + token + "' not defined");
                    }
                }
            }
        } else {
            throw new RuntimeException("Invalid expression: " + expression);
        }

        AritmethicOperationResult myResult = new AritmethicOperationResult();
        myResult.addResults("division", " " + total);
        return myResult;
    }

    /**
     * Performs a multiplication operation.
     * 
     * @param expression The arithmetic expression.
     * @param context The execution context containing variables.
     * @return The result of the multiplication operation.
     */
    public IOperationResult multiplicationOperation(String expression, ExecutionContext context) {
        Pattern pattern = Pattern.compile("([a-z]+|\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression);
        int total = 1; // Initialize with the multiplication's neutral value (1)

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
                        throw new RuntimeException("Variable '" + token + "' not defined");
                    }
                }
            }
        } else {
            throw new RuntimeException("Invalid expression: " + expression);
        }

        AritmethicOperationResult myResult = new AritmethicOperationResult();
        myResult.addResults("multiplication", " " + total);
        return myResult;
    }
}
