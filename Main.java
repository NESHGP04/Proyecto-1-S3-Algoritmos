import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LispInterpreter myLispInterpreter = new LispInterpreter();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n===BIENVENIDO AL INTÉRPRETE DE LISP===");
        System.out.println("\nIngrese una expresión en formato Lisp: ");
        String opt = " ";

        do{
            opt = scanner.nextLine();
            if(!opt.equals("exit")){
                myLispInterpreter.operate(opt).performOperation(); //performOperation en ArithmeticOperation no Interprete
                break;
            }
        } while(!opt.equals("exit"));

        scanner.close();
    }
}
