import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;

public class LispInterpreter {

    private ExecutionContext context;

    public LispInterpreter() {
        context = new ExecutionContext();
    }

    public Object operate(String expression) {
        Stack<Object> stack = new Stack<>();
        SintaxScanner syntaxScanner = new SintaxScanner();
        ArrayList<String> elements = syntaxScanner.getState(expression);
    
        for (String element : elements) {
            switch (element) {
                case "(":
                    stack.push(element);
                    break;
                case ")":
                    ArrayList<Object> operands = new ArrayList<>();
                    while (!stack.isEmpty() && !stack.peek().equals("(")) {
                        operands.add(0, stack.pop());
                    }
                    if (stack.isEmpty()) {
                        throw new IllegalArgumentException("Expresión no válida: paréntesis no balanceados");
                    }
                    stack.pop();
                    Object operator = operands.remove(0);
                    if (isOperator(operator.toString())) {
                        stack.push(performOperation(operator.toString(), operands));
                    } else if (isPredicateOperator(operator.toString())) {
                        int operationCode = getOperationCode(operator.toString());
                        stack.push(performPredicate(expression, operationCode));
                    } else {
                        throw new IllegalArgumentException("Operador o predicado no reconocido: " + operator);
                    }
                    break;
                default:
                    stack.push(element);
                    break;
            }
        }
    
        if (stack.size() == 1) {
            return stack.pop();
        } else {
            throw new IllegalArgumentException("Expresión no válida: " + expression);
        }
    }
    
    private int getOperationCode(String operator) {
        switch (operator) {
            case "atom":
                return 7;
            case "list":
                return 8;
            case "=":
                return 9;
            case "<":
                return 10;
            case ">":
                return 11;
            case "setq":
                return 6;
            default:
                throw new IllegalArgumentException("Operador no reconocido: " + operator);
        }
    }
    
    private boolean isPredicateOperator(String token) {
        return token.equals("atom") || token.equals("list") || token.equals("=") ||
                token.equals("<") || token.equals(">") || token.equals("setq");
    }
    
    private Object performOperation(String operator, ArrayList<Object> operands) {
        // Use BigDecimal for precise arithmetic
        BigDecimal result = BigDecimal.ZERO;
        switch (operator) {
            case "+":
                for (Object operand : operands) {
                    result = result.add(new BigDecimal(operand.toString()));
                }
                return result;
            case "-":
                result = new BigDecimal(operands.remove(0).toString());
                for (Object operand : operands) {
                    result = result.subtract(new BigDecimal(operand.toString()));
                }
                return result;
            case "*":
                result = BigDecimal.ONE;
                for (Object operand : operands) {
                    result = result.multiply(new BigDecimal(operand.toString()));
                }
                return result;
            case "/":
                if (operands.size() < 2) throw new IllegalArgumentException("División necesita al menos dos operandos");
                result = new BigDecimal(operands.remove(0).toString());
                for (Object operand : operands) {
                    BigDecimal divisor = new BigDecimal(operand.toString());
                    if (divisor.equals(BigDecimal.ZERO)) throw new ArithmeticException("División por cero");
                    result = result.divide(divisor);
                }
                return result;
            default:
                throw new IllegalArgumentException("Operador no reconocido: " + operator);
        }
    }

    private IPredicadosResult performPredicate(String expression, int operation) {
        PredicadosOperations resultP = new PredicadosOperations();
        
        switch (operation) {
            case 6: // setq
                IPredicadosResult resultI = resultP.setqOp(expression, context);
                resultI.performPredicado();
                return resultI;
            case 7: // ATOM
                IPredicadosResult resultA = resultP.atomOp(expression, context);
                resultA.performPredicado();
                return resultA;
            case 8: // LIST
                IPredicadosResult resultL = resultP.listOp(expression, context);
                resultL.performPredicado();
                return resultL;
            case 9: // EQUAL
                IPredicadosResult resultE = resultP.equalOp(expression, context);
                resultE.performPredicado();
                return resultE;
            case 10: // <
                IPredicadosResult resultM = resultP.lessThanOp(expression, context);
                resultM.performPredicado();
                return resultM;
            case 11: // >
                IPredicadosResult resultN = resultP.listOp(expression, context);
                resultN.performPredicado();
                return resultN;
            default:
                throw new IllegalArgumentException("Operación no reconocida: " + operation);
        }
    }

    private boolean isOperand(String token) {
        return token.matches("[a-z]+") || token.matches("\\d+");
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("/") || token.equals("*"); //< y > sirven?
    }
}
