import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PredicadosOperations implements IPredicadosResult {

    private String key;
    private String result;

    public PredicadosOperations() {
    }

    public PredicadosOperations(String result) {
        this.result = result;
    }

    @Override
    public void performPredicado() {
        System.out.println("El resultado " + key + " es: " + result);
    }

    public void joinResults(String key, String result) {
        this.key = key;
        this.result = result;
    }

    public IPredicadosResult setqOp(String expression, ExecutionContext context) {
        Pattern pattern = Pattern.compile("setq\\s+(\\w+)\\s+(\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression);

        if (matcher.find()) {
            String variable = matcher.group(1);
            String value = matcher.group(2);
            context.setVariable(variable, value);
        }

        PredicadosOperations myResult = new PredicadosOperations();
        myResult.joinResults("setq", "NIL");
        return myResult;
    }

    public IPredicadosResult atomOp(String expression, ExecutionContext context) {
        Pattern pattern = Pattern.compile("(atom\\s+\\S+)", Pattern.CASE_INSENSITIVE); //"(\\[a-zA-Z]+\\)" //(atom\\s+\\S+)

        Matcher matcher = pattern.matcher(expression);
        boolean isAtom = false;

        if (matcher.find()) {
            String token = matcher.group(0).trim();
            System.out.print(token);

            if (token.startsWith("atom")) {
                String value = token.substring(4).trim();
                if (value.matches("\\d+") || context.getVariable(value) != null) {
                    isAtom = true;
                }
            }
        }

        PredicadosOperations myResult = new PredicadosOperations();
        myResult.joinResults("atom", Boolean.toString(isAtom));
        return myResult;
    }

    public IPredicadosResult listOp(String expression, ExecutionContext context) {
        Pattern pattern = Pattern.compile("\\(([^()]+)\\)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression);
        boolean isListEqual = false;

        if (matcher.find()) {
            String listContent = matcher.group(1).trim();
            String[] elements = listContent.split("\\s*,\\s*");

            List<String> evaluatedElements = new ArrayList<>();

            for (String element : elements) {
                if (element.matches("\\d+")) {
                    evaluatedElements.add(element);
                } else if (context.getVariable(element) != null) {
                    evaluatedElements.add(context.getVariable(element));
                }
            }

            if (evaluatedElements.size() > 1) {
                // Comparar los elementos de la lista
                isListEqual = true;
                for (int i = 1; i < evaluatedElements.size(); i++) {
                    if (!evaluatedElements.get(i).equals(evaluatedElements.get(i - 1))) {
                        isListEqual = false;
                        break;
                    }
                }
            }
        }

        PredicadosOperations myResult = new PredicadosOperations();
        myResult.joinResults("list", Boolean.toString(isListEqual));
        return myResult;
    }

    public IPredicadosResult equalOp(String expression, ExecutionContext context) {
        Pattern pattern = Pattern.compile("equal\\s+(\\w+)\\s+(\\w+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression);
        boolean isEqual = false;

        if (matcher.find()) {
            String firstValue = matcher.group(1).trim();
            String secondValue = matcher.group(2).trim();

            if ((firstValue.matches("\\d+") || context.getVariable(firstValue) != null) &&
                    (secondValue.matches("\\d+") || context.getVariable(secondValue) != null)) {
                isEqual = firstValue.equals(secondValue);
            }
        }

        PredicadosOperations myResult = new PredicadosOperations();
        myResult.joinResults("equal", Boolean.toString(isEqual));


        return myResult;
    }

    public IPredicadosResult lessThanOp(String expression, ExecutionContext context) {
        Pattern pattern = Pattern.compile("(\\w+)\\s*<\\s*(\\w+)", Pattern.CASE_INSENSITIVE); //"(\\w+)\\s*<\\s*(\\w+)"
        Matcher matcher = pattern.matcher(expression);
        boolean isLessThan = false;

        if (matcher.find()) {
            String firstValue = matcher.group(1).trim();
            String secondValue = matcher.group(2).trim();

            int first = evaluateValue(firstValue, context);
            int second = evaluateValue(secondValue, context);

            if (first < second) {
                isLessThan = true;
            }
        }

        PredicadosOperations myResult = new PredicadosOperations();
        myResult.joinResults("<", Boolean.toString(isLessThan));
        return myResult;
    }

    public IPredicadosResult greaterThanOp(String expression, ExecutionContext context) {
        Pattern pattern = Pattern.compile("(\\w+)\\s*>\\s*(\\w+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression);
        boolean isGreaterThan = false;

        if (matcher.find()) {
            String firstValue = matcher.group(1).trim();
            String secondValue = matcher.group(2).trim();

            int first = evaluateValue(firstValue, context);
            int second = evaluateValue(secondValue, context);

            if (first > second) {
                isGreaterThan = true;
            }
        }

        PredicadosOperations myResult = new PredicadosOperations();
        myResult.joinResults(">", Boolean.toString(isGreaterThan));
        return myResult;
    }

    private int evaluateValue(String value, ExecutionContext context) {
        try {
            if (value.matches("\\d+")) {
                return Integer.parseInt(value);
            } else if (context.getVariable(value) != null && context.getVariable(value).matches("\\d+")) {
                return Integer.parseInt(context.getVariable(value));
            }
        } catch (NumberFormatException e) {
            // En caso de que la conversión a entero falle, simplemente retornamos 0
        }
        return 0; // Si no es un número ni una variable válida, retornamos 0
    }
}
