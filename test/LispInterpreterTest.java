import org.junit.Test;
import static org.junit.Assert.*;

public class LispInterpreterTest {

    @Test
    public void testBasicOperations() {
        LispInterpreter interpreter = new LispInterpreter();

        // Test addition
        assertEquals("6", interpreter.operate("(+ 2 4)").toString());

        // Test subtraction
        assertEquals("-2", interpreter.operate("(- 2 4)").toString());

        // Test multiplication
        assertEquals("8", interpreter.operate("(* 2 4)").toString());

        // Test division
        assertEquals("2", interpreter.operate("(/ 8 4)").toString());
    }

    @Test
    public void testPredicateOperations() {
            LispInterpreter interpreter = new LispInterpreter();

            // Test setq
            assertEquals("Variable set: x = 5", interpreter.operate("(setq x 5)").toString());

            // Test atom
            assertEquals("true", interpreter.operate("(atom 5)").toString());

            // Test list
            assertEquals("[1, 2, 3]", interpreter.operate("(list 1 2 3)").toString());
        }

        @Test
        public void testInvalidExpressions() {
            LispInterpreter interpreter = new LispInterpreter();

            // Test invalid expression with unbalanced parentheses
            assertThrows(IllegalArgumentException.class, () -> interpreter.operate("(+ 2 4"));

            // Test invalid expression with unrecognized operator
            assertThrows(IllegalArgumentException.class, () -> interpreter.operate("(^ 2 4)"));
        }

    }
