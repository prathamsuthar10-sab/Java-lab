import java.util.Scanner;

class DivideByZeroException extends Exception {
    DivideByZeroException(String message) {
        super(message);
    }
}

public class GuardedCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean success = false;
        int attempt = 0;

        while (!success) {
            attempt++;

            try {
                System.out.print("Enter first number: ");
                double first = Double.parseDouble(sc.nextLine());

                System.out.print("Enter second number: ");
                double second = Double.parseDouble(sc.nextLine());

                System.out.print("Enter operator (+, -, *, /): ");
                String operator = sc.nextLine();

                double result;

                switch (operator) {
                    case "+":
                        result = first + second;
                        break;

                    case "-":
                        result = first - second;
                        break;

                    case "*":
                        result = first * second;
                        break;

                    case "/":
                        if (second == 0) {
                            throw new DivideByZeroException(
                                    "Division by zero is not allowed"
                            );
                        }
                        result = first / second;
                        break;

                    default:
                        throw new IllegalArgumentException(
                                "Please enter a valid operator"
                        );
                }

                System.out.println("Result: " + result);
                success = true;

            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid number. Please enter numeric values."
                );

            } catch (DivideByZeroException e) {
                System.out.println(e.getMessage());

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());

            } finally {
                System.out.println("Attempt " + attempt + " logged.");
                System.out.println();
            }
        }

        sc.close();
    }
}