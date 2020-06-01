package io.github.billsplitter.domain;

import java.util.HashSet;
import java.util.Set;

public class User {

    private String uuid;
    private String name;

    private Set<LineItem> lineItems = new HashSet<>();

    void addUser(LineItem lineItem) {
        this.lineItems.add(lineItem);
    }

    void removeUser(LineItem lineItem) {
        this.lineItems.remove(lineItem);
    }
}
