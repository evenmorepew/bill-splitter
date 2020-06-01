package io.github.billsplitter.domain;

import java.util.HashSet;
import java.util.Set;

class Bill {

    private String name;

    private String uuid;

    private Currency currency;

    private Set<User> users = new HashSet<>();

    void addUser(User user) {
        this.users.add(user);
    }

    void removeUser(User user) {
        this.users.remove(user);
    }
}
