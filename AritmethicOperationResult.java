import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AritmethicOperationResult implements IOperationResult { //implements IOperationResult o IEvaluator

    String key;
    String result;

    @Override
    public void performOperation(){
        System.out.println("El resultado de la operación "+ key + " fue efectuado");
    }

    public void addResults(String key, String result){
        this.key = key;
        this.result = result;
    }

    public IOperationResult addOperation(String expression){ //o IEvaluator?
        Pattern pattern = Pattern.compile("([a-z]+|[0-9])",Pattern.CASE_INSENSITIVE );
        Matcher matcher = pattern.matcher(expression);
        int total = 0;

        while (matcher.find()){
            total += Integer.parseInt(matcher.group().trim());
        }

        AritmethicOperationResult miResult = new AritmethicOperationResult();
        miResult.addResults("suma ", " " + total);
        return miResult;
    }

    public IOperationResult substractOperation(String expression){ //o IEvaluator?
        Pattern pattern = Pattern.compile("([a-z]+|[0-9])",Pattern.CASE_INSENSITIVE );
        Matcher matcher = pattern.matcher(expression);
        int total = 0;

        while (matcher.find()){
            total += Integer.parseInt(matcher.group().trim());
        }

        AritmethicOperationResult miResult = new AritmethicOperationResult();
        miResult.addResults("resta ", " " + total);
        return miResult;
    }

}
