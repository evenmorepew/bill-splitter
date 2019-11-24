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
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String uuid;

    @NotNull
    private String name;

    @Singular
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false, updatable = false)
    private Set<LineItem> lineItems = new HashSet<>();

    void addUser(LineItem lineItem) {
        this.lineItems.add(lineItem);
    }

    void removeUser(LineItem lineItem) {
        this.lineItems.remove(lineItem);
    }
}
