import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LispInterpreter {

    private ExecutionContext context;

    public LispInterpreter() {
        this.context = new ExecutionContext();
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
                    stack.pop(); // Remove the "(" from the stack
                    Object operator = operands.remove(0); // Get the operator
                    if (isOperator(operator.toString())) {
                        // Perform arithmetic operations
                        stack.push(performOperation(operator.toString(), operands));
                    } else if (isPredicateOperator(operator.toString())) {
                        // Correctly perform predicate operations
                        stack.push(performPredicate(operator.toString(), operands, context)); // Correctly pass the operator, operands, and context
                    } else {
                        throw new IllegalArgumentException("Operador o predicado no reconocido: " + operator);
                    }
                    break;
                default:
                    stack.push(element); // Push other elements (operands) onto the stack
                    break;
            }
        }
    
        if (stack.size() == 1) {
            return stack.pop(); // Return the result of the Lisp expression
        } else {
            throw new IllegalArgumentException("Expresión no válida: " + expression);
        }
    }

    // Include previously shown implementations for getOperationCode, isPredicateOperator, performOperation, performPredicate, isOperand, isOperator here

    
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
        return token.equals("atom") || token.equals("list") || token.equals("quote") ||
               token.equals("equal") || token.equals("<") || token.equals(">") || token.equals("setq");
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

    private Object performPredicate(String operator, List<Object> operands, ExecutionContext context) {
    PredicadosOperations operations = new PredicadosOperations();
    switch (operator) {
        case "setq":
            return operations.setqOp(operands.get(0).toString(), operands.get(1).toString(), context).performPredicado();
        case "atom":
            return operations.atomOp(operands.get(0).toString(), context).performPredicado();
        case "list":
            return operations.listOp(operands).performPredicado();
            case "quote":
            // Assuming the entire list after "quote" is a single operand
            return operations.quoteOp(operands).performPredicado();
        case "equal":
            // Assumes operands are correctly parsed and ready to compare
            return operations.equalOp(operands.get(0), operands.get(1)).performPredicado();
        case "<":
            // Assumes operands are numbers; you may need conversion or validation
            return operations.lessThanOp((Number) operands.get(0), (Number) operands.get(1)).performPredicado();
        case ">":
            // Assumes operands are numbers; you may need conversion or validation
            return operations.greaterThanOp((Number) operands.get(0), (Number) operands.get(1)).performPredicado();
        default:
            throw new IllegalArgumentException("Unknown predicate: " + operator);
    }
}

    private boolean isOperand(String token) {
        return token.matches("[a-z]+") || token.matches("\\d+");
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")
               || token.equals("<") || token.equals(">");
    }
}
