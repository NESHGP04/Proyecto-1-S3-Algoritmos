/**
 * The IEvaluator interface represents an evaluator that can evaluate an expression
 * using the provided execution context.
 */
public interface IEvaluator {
    /**
     * Evaluates the given expression using the provided execution context.
     *
     * @param expression the expression to evaluate
     * @param context the execution context
     * @return the result of the evaluation
     * @throws Exception if an error occurs during evaluation
     */
    String eval(String expression, ExecutionContext context) throws Exception;
}
¿
