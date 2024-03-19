import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LispInterpreter {

    private ExecutionContext context;

    public LispInterpreter() {
        context = new ExecutionContext();
    }

    public Object operate(String expression) {
        Stack<Object> stack = new Stack<>();
        SintaxScanner sintaxScanner = new SintaxScanner();
        ArrayList<String> elements = sintaxScanner.getState(expression);
    
        for (String element : elements) {
            switch (element) {
                case "(":
                    // Simplemente apilar el paréntesis de apertura
                    stack.push(element);
                    break;
                case ")":
                    ArrayList<Object> operands = new ArrayList<>();
                    while (!stack.isEmpty() && !stack.peek().equals("(")) {
                        operands.add(0, stack.pop()); // Añadir al inicio para mantener el orden
                    }
                    if (stack.isEmpty()) {
                        throw new RuntimeException("Expresión no válida: paréntesis no balanceados");
                    }
                    stack.pop(); // Quitar el "(" de la pila
                    Object operator = operands.remove(0); // El operador es el primer elemento
                    stack.push(performOperation(operator.toString(), operands));
                    break;
                default:
                    stack.push(element); // Operandos y operadores
                    break;
            }
        }
    
        if (stack.size() == 1) {
            return stack.pop();
        } else {
            throw new RuntimeException("Expresión no válida: " + expression);
        }
    }
    
    private Object performOperation(String operator, ArrayList<Object> operands) {
        // Supongamos que todos los operandos son Double para simplificar
        double result = 0;
        switch (operator) {
            case "+":
                for (Object operand : operands) {
                    result += Double.parseDouble(operand.toString());
                }
                return result;
            case "-":
                result = Double.parseDouble(operands.remove(0).toString());
                for (Object operand : operands) {
                    result -= Double.parseDouble(operand.toString());
                }
                return result;
            case "*":
                result = 1; // Valor neutro de la multiplicación
                for (Object operand : operands) {
                    result *= Double.parseDouble(operand.toString());
                }
                return result;
            case "/":
                if (operands.size() < 2) throw new IllegalArgumentException("División necesita al menos dos operandos");
                result = Double.parseDouble(operands.remove(0).toString());
                for (Object operand : operands) {
                    double divisor = Double.parseDouble(operand.toString());
                    if (divisor == 0) throw new ArithmeticException("División por cero");
                    result /= divisor;
                }
                return result;
            default:
                throw new IllegalArgumentException("Operador no reconocido: " + operator);
        }
    }
    

    private boolean isOperand(String token) {
        return token.matches("[a-z]+") || token.matches("\\d+");
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("/") || token.equals("*"); //< y > sirven?
    }

    private IPredicadosResult performPredicado(String expression, int operation){
        PredicadosOperations resultP = new PredicadosOperations();
        switch (operation){
            case 6: //setq
                return resultP.setqOp(expression, context);
            case 7:// ATOM
                return resultP.atomOp(expression, context);
            case 8: //LIST
                return resultP.listOp(expression, context);
            case 9: //EQUAL
                return resultP.equalOp(expression, context);
            case 10: //<
                return resultP.lessThanOp(expression, context);
            case 11: //>
                return resultP.greaterThanOp(expression, context);
            default:
                //throw new RuntimeException("Unexpected operation");
        }
        return null;
    }
}
