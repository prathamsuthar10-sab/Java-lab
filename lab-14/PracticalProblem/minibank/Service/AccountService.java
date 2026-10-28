package minibank.Service;

import com.minibank.model.Account;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AccountService implements Serializable {
    private static final long serialVersionUID = 1L;

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