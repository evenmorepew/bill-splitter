package io.github.billsplitter.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
class Bill {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String uuid;

    @NotNull
    private Currency currency;

    @Singular
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "BILL_ID", nullable = false, updatable = false)
    private Set<User> users = new HashSet<>();

    void addUser(User user) {
        this.users.add(user);
    }

    void removeUser(User user) {
        this.users.remove(user);
    }
}
