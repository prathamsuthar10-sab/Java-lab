package com.minibank;

import com.minibank.service.AccountService;
import java.io.*;

public class AccountServiceCheck {
    public static void main(String[] args) throws Exception {
        AccountService original = new AccountService();
        original.addAccount("Maya");
        original.addAccount("Ravi");

        try (ObjectOutputStream output =
                     new ObjectOutputStream(new FileOutputStream("accounts.dat"))) {
            output.writeObject(original);
        }

        AccountService loaded;
        try (ObjectInputStream input =
                     new ObjectInputStream(new FileInputStream("accounts.dat"))) {
            loaded = (AccountService) input.readObject();
        }

        if (loaded.getAccountCount() != original.getAccountCount()
                || !loaded.getAccount(0).getOwner().equals(original.getAccount(0).getOwner())
                || !loaded.getAccount(1).getOwner().equals(original.getAccount(1).getOwner())) {
            throw new AssertionError("Data changed after saving and loading.");
        }

        System.out.println("Save/load check passed. Account data is unchanged.");
    }
}