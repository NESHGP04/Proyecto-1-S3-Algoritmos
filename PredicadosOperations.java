import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PredicadosOperations implements IPredicadosResult {

    private String key;
    private String result;

    @Override
    public void performPredicado() {
        System.out.println("El resultado " + key + " es: " + result);
    }

    public void joinResults(String key, String result) {
        this.key = key;
        this.result = result;
    }

    //ATOM
    public IPredicadosResult atomOp(String expression, ExecutionContext context) {
        Pattern pattern = Pattern.compile("(atom\\s+\\S+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression);
        boolean isAtom = false;

        if (matcher.find()) {
            String token = matcher.group().trim();

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

    //LIST
    public IPredicadosResult listOp(String expression, ExecutionContext context) {
        return null;
    }

    //EQUAL
    public IPredicadosResult equalOp(String expression, ExecutionContext context) {
        
        Pattern pattern = Pattern.compile("EQUAL\\s*(\\w+)\\s", Pattern.CASE_INSENSITIVE);//CAMBIAR REGEX

        Matcher matcher = pattern.matcher(expression);
        boolean isAtom = false;
        
        // String[] arguments = extractArguments(expression, 2); // Implementa esta función para extraer exactamente dos argumentos
        // double operand1 = Double.parseDouble(eval(arguments[0], context));
        // double operand2 = Double.parseDouble(eval(arguments[1], context));
        // return operand1 == operand2 ? "T" : "NIL";
        return null;
    }

    //<<
    public IPredicadosResult lessThanOp(String expression, ExecutionContext context) {
        return null;
    }

    //>>
    public IPredicadosResult greaterThanOp(String expression, ExecutionContext context) {
        return null;
    }


}
