import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SintaxScanner {

    private static boolean evaluate(String regex, String expression){
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression);
        return matcher.find();
    }

    public static int getState(String expression){
        if(evaluate("ˆ[(][ ]*setq[ ]+[a-z]+[ ]+[0-9]+[ ]*[)]$", expression)){
            return 1;
        } else if(evaluate ("ˆ[(][ ]*[+][ ]+([a-z]+| [0-9]+)[ ]+([a-z]+[0-9]+)*[)]$", expression)){
            return 2;
        } else if(evaluate("ˆ[(][ ]*[-][ ]+([a-z]+|[0-9]+)[ ]+([a-z]+|[0-9]+)*[)]$", expression)){
            return 3;
        }
        else{
            return -1;
        }
    }

}