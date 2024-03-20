import static org.junit.Assert.*;
import org.junit.Test;

public class PredicadosOperationsTest {
    @Test
public void testAtomOp() {
    ExecutionContext context = new ExecutionContext();
    context.setVariable("pi", "3.14");

    PredicadosOperations operations = new PredicadosOperations();
    IPredicadosResult result1 = operations.atomOp("(atom pi)", context);

    result1.performPredicado();
    assertEquals("3.14", context.getVariable("pi"));
}

    @Test
    public void testEqualOp() {
            ExecutionContext context = new ExecutionContext();
            context.setVariable("x", "10");
            context.setVariable("y", "20");
    
            PredicadosOperations operations = new PredicadosOperations();
            IPredicadosResult result1 = operations.equalOp("(equal 10 10)", context);
            IPredicadosResult result2 = operations.equalOp("(equal x 10)", context);
            IPredicadosResult result3 = operations.equalOp("(equal x y)", context);

            result1.performPredicado();
            assertEquals("NIL", context.getVariable("10"));
            
            result2.performPredicado();
            assertEquals("20", context.getVariable("y"));

            result3.performPredicado();
            assertEquals("10", context.getVariable("x")); //NIL = T

    }

    @Test
    public void testGreaterThanOp() {
        ExecutionContext context = new ExecutionContext();
        context.setVariable("x", "10");
        context.setVariable("y", "20");

        PredicadosOperations operations = new PredicadosOperations();
        IPredicadosResult result1 = operations.greaterThanOp("(> 20 10)", context);
        IPredicadosResult result2 = operations.greaterThanOp("(> y x)", context);
        IPredicadosResult result3 = operations.greaterThanOp("(> 10 x)", context);

        result1.performPredicado();
        assertEquals("NIL",  context.getVariable("20")); //NIL = T

        result2.performPredicado();
        assertEquals("20",  context.getVariable("y"));

        result3.performPredicado();
        assertEquals("10",  context.getVariable("x"));
    }

    @Test
    public void testLessThanOp() {
        ExecutionContext context = new ExecutionContext();
        context.setVariable("x", "10");
        context.setVariable("y", "20");

        PredicadosOperations operations = new PredicadosOperations();
        IPredicadosResult result1 = operations.lessThanOp("(< 10 20)", context);
        IPredicadosResult result2 = operations.lessThanOp("(< x y)", context);
        IPredicadosResult result3 = operations.lessThanOp("(< 30 x)", context);

        result1.performPredicado();
        assertEquals("NIL", context.getVariable("20"));
        result2.performPredicado();
        assertEquals("10",   context.getVariable("x"));
        result3.performPredicado();
        assertEquals("10", context.getVariable("x"));
    }

    @Test
public void testListOp() {
    ExecutionContext context = new ExecutionContext();
    context.setVariable("x", "10");
    context.setVariable("y", "20");

    PredicadosOperations operations = new PredicadosOperations();
    IPredicadosResult result1 = operations.listOp("(10, 10)", context);
    IPredicadosResult result2 = operations.listOp("(10, x)", context);
    IPredicadosResult result3 = operations.listOp("(10, y)", context);

    result1.performPredicado();
    assertEquals("NIL", context.getVariable("10"));

    result2.performPredicado();
    assertEquals("10", context.getVariable("x"));
    
    result3.performPredicado();
    assertEquals("20", context.getVariable("y"));
}

    @Test
    public void testSetqOp() {
        ExecutionContext context = new ExecutionContext();
        PredicadosOperations operations = new PredicadosOperations();
        IPredicadosResult result = operations.setqOp("setq x 10", context);
        result.performPredicado();
        assertEquals("10", context.getVariable("x"));
    }
}
