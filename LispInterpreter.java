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
            if (element.equals("(")) {
                stack.push(element);
            } else if (isOperand(element)) {
                stack.push(element);
            } else if (isOperator(element)) {
                stack.push(element);
            } else if (element.equals(")")) {
                ArrayList<Object> operands = new ArrayList<>();
                Object operator = null;
                // Pop elements from the stack until "(" is encountered
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    if (isOperator(stack.peek().toString())) {
                        operator = stack.pop();
                    } else {
                        operands.add(stack.pop());
                    }
                }

                // Remove "(" from the stack
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    throw new RuntimeException("Expresión no válida: paréntesis no balanceados");
                }

                if (operator == null) {
                    throw new RuntimeException("Operador no encontrado");
                }

                // Perform operation based on operator
                if (operator.equals("+") || operator.equals("-") || operator.equals("/") || operator.equals("*")) {
                    if (operands.size() < 2) {
                        throw new RuntimeException("Número insuficiente de operandos para el operador: " + operator);
                    }
                    Object result = performOperation(operator.toString(), operands.get(0), operands.get(1));
                    stack.push(result);
                } else {
                    throw new RuntimeException("Operador no válido: " + operator);
                }
            } else {
                stack.push(element);
            }
        }

        if (stack.size() == 1 && (stack.peek() instanceof Double || stack.peek() instanceof String)) {
            return stack.pop();
        } else {
            throw new RuntimeException("Expresión no válida: " + expression);
        }
    }

    private Object performOperation(String operator, Object operand1, Object operand2) {
        double op1 = Double.parseDouble(operand1.toString());
        double op2 = Double.parseDouble(operand2.toString());
        switch (operator) {
            case "+":
                return op1 + op2;
            case "-":
                return op1 - op2;
            case "*":
                return op1 * op2;
            case "/":
                if (op2 == 0) {
                    throw new ArithmeticException("División por cero");
                }
                return op1 / op2;
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
