package com.shop.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.shop.rules.DiscountRule;
import com.shop.service.DiscountEngine;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Double> prices = new ArrayList<>();

        System.out.print("How many products? ");
        int count = sc.nextInt();

        for (int i = 0; i < count; i++) {
            System.out.print("Enter price " + (i + 1) + ": ");
            prices.add(sc.nextDouble());
        }

        DiscountRule[] rules = {
            price -> price * 0.90,
            price -> Math.max(0, price - 100),
            price -> price >= 1000 ? price * 0.80 : price
        };

        System.out.println("\nChoose a discount:");
        System.out.println("1. 10% off");
        System.out.println("2. Rs.100 off");
        System.out.println("3. 20% off if price is Rs.1000 or more");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();

        if (choice < 1 || choice > 3) {
            System.out.println("Invalid choice");
            sc.close();
            return;
        }

        DiscountRule selectedRule = rules[choice - 1];
        DiscountEngine engine = new DiscountEngine();
        List<Double> finalPrices = engine.applyDiscount(prices, selectedRule);

        System.out.println("\nFinal prices:");

        for (int i = 0; i < prices.size(); i++) {
            System.out.printf("Rs.%.2f -> Rs.%.2f%n",
                    prices.get(i), finalPrices.get(i));
        }

        sc.close();
    }
}