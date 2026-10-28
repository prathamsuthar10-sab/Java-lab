package com.minibank;

import com.minibank.service.AccountService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountService service = new AccountService();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n1. Add account");
                System.out.println("2. Show account count");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");

                switch (scanner.nextLine()) {
                    case "1":
                        System.out.print("Enter account owner: ");
                        service.addAccount(scanner.nextLine());
                        System.out.println("Account added.");
                        break;
                    case "2":
                        System.out.println("Account count: " + service.getAccountCount());
                        break;
                    case "3":
                        return;
                    default:
                        System.out.println("Invalid action.");
                }
            }
        }
    }
}