public class AccountServiceCheck {
    public static void main(String[] args) {
        AccountService service = new AccountService();

        service.addAccount("Maya");
        check(service.getAccountCount() == 1, "Adding one account should make the count 1.");

        service.addAccount("Ravi");
        check(service.getAccountCount() == 2, "Adding two accounts should make the count 2.");

        try {
            service.getAccount(-1);
            throw new AssertionError("An invalid account index should throw an exception.");
        } catch (IllegalArgumentException expected) {
            System.out.println("Invalid account check passed.");
        }

        System.out.println("All checks passed.");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}