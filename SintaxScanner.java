import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SintaxScanner {

    private static boolean evaluate(String regex, String expression){
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression);
        return matcher.find();
    }

    public ArrayList<String> getState(String expression) {
        ArrayList<String> tokens = new ArrayList<>();
        Pattern pattern = Pattern.compile("\"[^\"]*\"|\\(|\\)|\\w+|[+\\-/*()]");
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        return tokens;
    }
}
