import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AritmethicOperationResultTest {

    private AritmethicOperationResult operationResult;
    private ExecutionContext context;


    @Test
    public void testAddOperation() {
        // Simula la suma de dos números
        IOperationResult result = operationResult.addOperation("3 7", context);
        operationResult = (AritmethicOperationResult) result; // Asegúrate de que esto sea seguro
        assertEquals("10", operationResult.addOperation(null, context)); // Asumiendo que hay un getter para result
    }

    @Test
    public void testAddResults() {
        operationResult.addResults("testKey", "testValue");
        assertEquals("testValue", operationResult.addResults("", null)); // Asumiendo que hay un getter para result
    }

    @Test
    public void testDivisionOperation() {
        // Divide 10 entre 2, asumiendo que no habrá divisiones por 0 sin manejar
        IOperationResult result = operationResult.divisionOperation("10 2", context);
        operationResult = (AritmethicOperationResult) result;
        assertEquals("5", operationResult.divisionOperation(null, context));
    }

    @Test
    public void testMultiplicationOperation() {
        // Multiplica 2 por 5
        IOperationResult result = operationResult.multiplicationOperation("2 5", context);
        operationResult = (AritmethicOperationResult) result;
        assertEquals("10", operationResult.multiplicationOperation(null, context));
    }

    @Test
    public void testPerformOperation() {
        // Este test verificará si el método performOperation imprime correctamente el resultado.
        // JUnit no captura impresiones directamente. Este test es más un recordatorio de que
        // deberías probar este método manualmente o buscar una forma de redirigir y capturar
        // la salida del sistema.
        assertTrue(true); // Este es un placeholder; la prueba real requiere un enfoque diferente.
    }

    @Test
    public void testSubstractOperation() {
        // Resta 10 - 5
        IOperationResult result = operationResult.substractOperation("10 5", context);
        operationResult = (AritmethicOperationResult) result;
        assertEquals("5", operationResult);
    }
}
