public class Driver {

    public static void main(String[] args) {

        String[] passwords = {
            "abc",
            "abcdefgh",
            "Abcdefgh",
            "Abcd1234",
            "Abcd1234!"
        };

        for (String pw : passwords) {

            System.out.println("Password: " + pw);
            int count = 0;

            if (PasswordChecker.lengthCheck(pw)) {
                count++;
                System.out.println("Length >= 8: Passed");
            } else {
                System.out.println("Length >= 8: Failed");
            }

            if (PasswordChecker.uppercaseCheck(pw)) {
                count++;
                System.out.println("Uppercase letter: Passed");
            } else {
                System.out.println("Uppercase letter: Failed");
            }

            if (PasswordChecker.digitCheck(pw)) {
                count++;
                System.out.println("Digit: Passed");
            } else {
                System.out.println("Digit: Failed");
            }

            if (PasswordChecker.specialCheck(pw)) {
                count++;
                System.out.println("Special character: Passed");
            } else {
                System.out.println("Special character: Failed");
            }
            System.out.println("Strength: " + PasswordChecker.strength(pw));
            System.out.println("Passed checks: " + count);
            System.out.println();
        }
    }
}