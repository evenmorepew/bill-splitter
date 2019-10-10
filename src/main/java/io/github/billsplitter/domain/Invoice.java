package io.github.billsplitter.domain;

import org.springframework.data.annotation.Id;

class Invoice {

    @Id
    private final Long id;
    private final String name;
    private final String uuid;

    static Invoice of(String name, String uuid) {
        return new Invoice(null, name, uuid);
    }

    private Invoice(Long id, String name, String uuid) {
        this.id = id;
        this.name = name;
        this.uuid = uuid;
    }

    Invoice withName(String name) {
        return new Invoice(this.id, name, this.uuid);
    }

    Invoice withId(Long id) {
        return new Invoice(id, this.name, this.uuid);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }
}
