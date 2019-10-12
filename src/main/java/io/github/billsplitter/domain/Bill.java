package io.github.billsplitter.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Bill {

    @Id
    private final Long id;
    private final String name;
    private final String uuid;

    static Bill of(String name, String uuid) {
        return new Bill(null, name, uuid);
    }

    Bill withName(String name) {
        return new Bill(this.id, name, this.uuid);
    }

    Bill withId(Long id) {
        return new Bill(id, this.name, this.uuid);
    }
}
