import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Account {
    private final String owner;

    public Account(String owner) {
        if (owner == null || owner.trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be empty.");
        }
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }
}

class AccountService {
    private final List<Account> accounts = new ArrayList<>();

    public void addAccount(String owner) {
        accounts.add(new Account(owner));
    }

    public int getAccountCount() {
        return accounts.size();
    }

    public Account getAccount(int index) {
        if (index < 0 || index >= accounts.size()) {
            throw new IllegalArgumentException("Invalid account index.");
        }
        return accounts.get(index);
    }
}

public class MiniBankApp {
    public static void main(String[] args) {
        AccountService service = new AccountService();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n1. Add account");
                System.out.println("2. Show account count");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");

                String input = scanner.nextLine();

                switch (input) {
                    case "1":
                        System.out.print("Enter account owner: ");
                        service.addAccount(scanner.nextLine());
                        System.out.println("Account added.");
                        break;
                    case "2":
                        System.out.println("Account count: " + service.getAccountCount());
                        break;
                    case "3":
                        System.out.println("Goodbye.");
                        return;
                    default:
                        System.out.println("Invalid action.");
                }
            }
        }
    }
}