import java.util.Scanner;
/**
 * Basic calculator class
 * @author Alejandro Serna
 **/
public class Calculator{

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the calculator!");

        //Sentinel value set to -1.0
        double answer = -1.0;

        while(answer == -1.0){
            //Displays the main menu
            displayMenu();

            //User input used for operation. Only first character is considered
            char operation = scan.nextLine().charAt(0);

            //If operation is not valid, skip iteration and print message
            if(!isValidOperation(operation)){
                System.out.println("Invalid operation, please try again");
                continue;
            }

            if(operation == 'q'){
                System.out.println("Goodbye");
                break;
            } else if(operation == 'f'){
                System.out.println("Only the first number will be used. Type anything for the second one");
            } else if(operation == 'P'){
                System.out.println("Use first number for percent, second number for the whole number");
            } else if(operation == 'p'){
                System.out.println("Use first number for base, second number for power");
            } else if(operation == 'r'){
                System.out.println("Use first number as the index ex: 2 for square root, second number for radicand ex: square root of 4");
            }

            System.out.println("Please enter the first number");
            //If the input is not a valid number, print message
            if(!scan.hasNextDouble()){
                System.out.println("Invalid input, please enter a valid number");
                //Consume the invalid input
                scan.nextLine();
            }

            double firstNumber = scan.nextDouble();
            //Consume character left by .nextDouble
            scan.nextLine();

            System.out.println("Please enter the second number");
            //If the input is not a valid number, print message
            if(!scan.hasNextDouble()){
                System.out.println("Invalid input, please enter a valid number");
                scan.nextLine();
            }
            double secondNumber = scan.nextDouble();
            //Consume character left by .nextDouble
            scan.nextLine();

            //Prevents exception if user tries to divide by 0
            if(operation == 'd' && secondNumber == 0){
                System.out.println("Cannot divide by 0, please try again");
                //Skip iteration in order to try again
                continue;
            }

            //Closing scanner
            scan.close();

            //Switch statement to direct operation
            answer = switch (operation) {
                case 'a' -> add(firstNumber, secondNumber);
                case 's' -> subtract(firstNumber, secondNumber);
                case 'm' -> multiply(firstNumber, secondNumber);
                case 'd' -> divide(firstNumber, secondNumber);
                case 'p' -> power(firstNumber, secondNumber);
                case 'f' -> factorial(firstNumber);
                case 'P' -> percentage(firstNumber, secondNumber);
                case 'r' ->root(firstNumber,secondNumber);
                default -> throw new IllegalStateException("Unexpected value: " + operation);
            };

            System.out.println("The final answer is: " + answer);
        }

    }

    /**
     * Displays the menu
     */
    public static void displayMenu(){
        String menu = """
                
                Type a to add, s to subtract, m to multiply, d to divide
                p for powers, f for factorials, P for percentages, r for roots
                Type q to quit""";
        System.out.println(menu);
    }

    /**
     * Returns true if operation is valid, false otherwise
     * @param operation char with operation value
     * @return Boolean condition
     */
    public static boolean isValidOperation (char operation){
        return operation == 'a' || operation == 's' || operation == 'm' || operation == 'd' ||
                operation == 'p' || operation == 'f' || operation == 'P' || operation == 'r';
    }

    /**
     * Solves additions
     * @param x First number
     * @param y Second number
     * @return Sum
     */
    public static double add (double x, double y){
        return x + y;
    }

    /**
     * Solves subtractions
     * @param x Whole number
     * @param y Subtracting number
     * @return Difference
     */
    public static double subtract (double x, double y){
        return x - y;
    }

    /**
     * Solves multiplications
     * @param x Multiplicand
     * @param y Multiplier
     * @return Product
     */
    public static double multiply (double x, double y){
        return x * y;
    }

    /**
     * Solves divisions
     * @param x Dividend
     * @param y Divisor
     * @return Quotient
     * @throws ArithmeticException if there is division by 0
     */
    public static double divide (double x, double y){
        if(y == 0){
            throw new ArithmeticException("Cannot divide by 0");
        }
        return x / y;
    }

    /**
     * Solves powers
     * @param x base
     * @param y power
     * @return Result of x^y
     */
    public static double power (double x, double y){
        if(y == 0){
            return 1;
        }
        return x * power(x, y-1);
    }

    /**
     * Solves factorials
     * @param x number factorial
     * @return Result of x!
     */
    public static double factorial (double x){
        if(x == 1){
            return 1;
        }
        return x * factorial(x-1);
    }


    /**
     * Solves percentages
     * @param x Percentage
     * @param y Whole number
     * @return Resulting percentage
     * @throws ArithmeticException if the whole number is zero
     */
    public static double percentage(double x, double y) {
        if (y == 0) {
            throw new ArithmeticException("Whole number cannot be zero for percentage calculation");
        }
        return (x / y) * (100);
    }

    /**
     * Solves roots
     * @param x Index
     * @param y Radicand
     * @return Xth root of y
     */
    public static double root (double x, double y){
        return Math.pow(y, (1.0 / x));
    }

}
