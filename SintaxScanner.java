import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SintaxScanner {

    private static boolean evaluate(String regex, String expression){
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression);
        return matcher.find();
    }
    /* 
    public static int getState(String expression) {
        if (evaluate("^[(]\\s*setq\\s+[a-z]+\\s+[0-9]+\\s*[)]$", expression)) {
            return 1; // Expresión de asignación
        } else if (evaluate("^[(]\\s*\\+\\s+.*[)]$", expression)) {
            return 2; // Expresión de suma
        } else if (evaluate("^[(]\\s*\\-\\s+.*[)]$", expression)) {
            return 3; // Expresión de resta
        } else if (evaluate("^[(]\\s*\\/\\s+.*[)]$", expression)) {
            return 4; // Expresión de división
        } else if (evaluate("^[(]\\s*\\*\\s+.*[)]$", expression)) {
            return 5; // Expresión de multiplicación
<<<<<<< HEAD
        } else if (expression.startsWith("(")) {
            // Si la expresión comienza con un paréntesis, es una expresión anidada
            // Evaluar el tipo de expresión anidada llamando recursivamente a getState
            String innerExpression = expression.substring(1, expression.length() - 1);
=======
        } else if(evaluate("^\\(atom\\s+.*\\)$", expression)){
            return 6; //Expresión atom REVISAR
        }
        else if (expression.matches("\\((-?\\d+(\\.\\d+)?)\\)")) { // Chequea si es un número entre paréntesis
            String innerExpression = expression.substring(1, expression.length()-1);
>>>>>>> 1249812b7ad055f8e82627c4ac62ed4556d4f51b
            return getState(innerExpression);
        } else {
            return -1;
        }
    } */

    public ArrayList<String> getState(String expression) {
        ArrayList<String> tokens = new ArrayList<>();
        Pattern pattern = Pattern.compile("\"[^\"]\"|\\(|\\)|\\w+|[+\\-/()<>=]");
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        return tokens;
    }
}