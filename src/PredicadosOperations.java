import java.util.List;

/**
 * The PredicadosOperations class provides operations for working with predicates.
 * Predicates are functions that return a boolean value based on certain conditions.
 * This class contains methods for various predicate operations such as setting variables,
 * checking if a token is an atom, working with lists, and comparing values.
 */
public class PredicadosOperations {

    public IPredicadosResult setqOp(String variable, String value, ExecutionContext context) {
        context.setVariable(variable, value);
        return () -> "Variable set: " + variable + " = " + value; // For simplicity, we're just returning a confirmation string.
    }

    public IPredicadosResult atomOp(String token, ExecutionContext context) {
        boolean isAtom = !token.startsWith("(") && !token.endsWith(")"); // Simple check for atoms. Adjust as necessary.
        return () -> isAtom;
    }

    public IPredicadosResult listOp(List<Object> elements) {
        return () -> elements; // Simply returns the list.
    }

    public IPredicadosResult quoteOp(Object expression) {
        return () -> expression; // Returns the expression without evaluation.
    }

    public IPredicadosResult equalOp(Object operand1, Object operand2) {
        boolean isEqual = operand1.equals(operand2);
        return () -> isEqual;
    }

    public IPredicadosResult lessThanOp(Number operand1, Number operand2) {
        boolean isLessThan = operand1.doubleValue() < operand2.doubleValue();
        return () -> isLessThan;
    }

    public IPredicadosResult greaterThanOp(Number operand1, Number operand2) {
        boolean isGreaterThan = operand1.doubleValue() > operand2.doubleValue();
        return () -> isGreaterThan;
    }
}
