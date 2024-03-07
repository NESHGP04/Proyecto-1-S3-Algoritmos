import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SintaxScanner {

    private static boolean evaluate(String regex, String expression){
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression);
        return matcher.find();
    }

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
        } else {
            return - 1;
        }
    }
}
