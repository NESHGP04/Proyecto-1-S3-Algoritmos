import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The SintaxScanner class provides methods for evaluating regular expressions and extracting tokens from an expression.
 */
public class SintaxScanner {

    /**
     * Evaluates a regular expression against an expression and returns whether there is a match.
     *
     * @param regex      the regular expression to evaluate
     * @param expression the expression to match against the regular expression
     * @return true if there is a match, false otherwise
     */
    private static boolean evaluate(String regex, String expression) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression);
        return matcher.find();
    }

    /**
     * Extracts tokens from an expression.
     *
     * @param expression the expression to extract tokens from
     * @return an ArrayList of tokens extracted from the expression
     */
    public ArrayList<String> getState(String expression) {
        ArrayList<String> tokens = new ArrayList<>();
        Pattern pattern = Pattern.compile("\"[^\"]*\"|\\(|\\)|\\w+|[+\\-/*()]");//"\"[^\"]*\"|\\(|\\)|\\w+|[+\\-/*()]"
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        return tokens;
    }
}
