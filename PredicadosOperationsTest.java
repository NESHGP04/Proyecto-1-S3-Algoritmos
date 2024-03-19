import static org.junit.Assert.*;
import org.junit.Test;

public class PredicadosOperationsTest {
    @Test
public void testAtomOp() {
    ExecutionContext context = new ExecutionContext();
    context.setVariable("x", "10");

    PredicadosOperations operations = new PredicadosOperations();
    IPredicadosResult result1 = operations.atomOp("(atom x)", context);
    result1.performPredicado();
    assertNotNull(context.getVariable("x")); // Verificamos que la variable 'x' está definida
    assertFalse(Boolean.parseBoolean(context.getVariable("x"))); // 'x' debería ser false ya que no es un átomo
    //assertEquals("false", context.getVariable("x"));

    IPredicadosResult result2 = operations.atomOp("(atom 10)", context);
    result2.performPredicado();
    assertNotNull(context.getVariable("10")); // Verificamos que la variable '10' está definida
    assertTrue(Boolean.parseBoolean(context.getVariable("10"))); // '10' debería ser true ya que es un átomo

    IPredicadosResult result3 = operations.atomOp("(atom y)", context);
    result3.performPredicado();
    assertNull(context.getVariable("y")); // Verificamos que la variable 'y' no está definida
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
            assertTrue(Boolean.parseBoolean(context.getVariable("true"))); // El predicado (equal 10 10) debe ser verdadero
    }

    @Test
    public void testGreaterThanOp() {

    }

    @Test
    public void testJoinResults() {

    }

    @Test
    public void testLessThanOp() {

    }

    @Test
public void testListOp() {
    ExecutionContext context = new ExecutionContext();
    context.setVariable("x", "10");

    PredicadosOperations operations = new PredicadosOperations();
    IPredicadosResult result1 = operations.listOp("(10, 10)", context);
    IPredicadosResult result2 = operations.listOp("(10, x)", context);
    IPredicadosResult result3 = operations.listOp("(10, y)", context);

    result1.performPredicado();
    assertEquals("NIL", context.getVariable("10"));

    result2.performPredicado();
    assertEquals("10", context.getVariable("x"));
    
    result3.performPredicado();
    assertEquals("NIL", context.getVariable("y"));
}

    @Test
    public void testPerformPredicado() {

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
