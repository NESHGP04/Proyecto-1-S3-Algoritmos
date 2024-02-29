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

    public IOperationResult addOperation(String expression) {
        Pattern pattern = Pattern.compile("([a-z]+|\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression);
        int total = 0;

        while (matcher.find()) {
            total += Integer.parseInt(matcher.group().trim());
        }

        AritmethicOperationResult myResult = new AritmethicOperationResult();
        myResult.addResults("suma", " " + total);
        return myResult;
    }

    public IOperationResult substractOperation(String expression) {
        Pattern pattern = Pattern.compile("([a-z]+|\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression);
        int total = 0;

        while (matcher.find()) {
            total -= Integer.parseInt(matcher.group().trim());
        }

        AritmethicOperationResult myResult = new AritmethicOperationResult();
        myResult.addResults("resta", " " + total);
        return myResult;
    }
}