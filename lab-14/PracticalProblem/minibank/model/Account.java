package minibank.model;

package minibank.model;

import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

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