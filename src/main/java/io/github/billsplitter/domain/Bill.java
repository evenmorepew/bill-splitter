package io.github.billsplitter.domain;

import org.springframework.data.annotation.Id;

class Bill {

    @Id
    private final Long id;
    private final String name;
    private final String uuid;

    static Bill of(String name, String uuid) {
        return new Bill(null, name, uuid);
    }

    private Bill(Long id, String name, String uuid) {
        this.id = id;
        this.name = name;
        this.uuid = uuid;
    }

    Bill withName(String name) {
        return new Bill(this.id, name, this.uuid);
    }

    Bill withId(Long id) {
        return new Bill(id, this.name, this.uuid);
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
