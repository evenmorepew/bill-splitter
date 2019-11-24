package io.github.billsplitter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

import static lombok.AccessLevel.PACKAGE;

@Getter
@AllArgsConstructor(access = PACKAGE)
class Bill {

    @Id
    private final Long id;
    private final String name;
    private final String uuid;
    private final Currency currency;
    private final Set<LineItem> lineItems = new HashSet<>();

    static Bill of(String name, String uuid, Currency currency) {
        return new Bill(null, name, uuid, currency);
    }

    static Bill of(String name, String uuid) {
        return of(name, uuid, Currency.EUR);
    }

    Bill withName(String name) {
        return new Bill(this.id, name, this.uuid, this.currency);
    }

    Bill withId(Long id) {
        return new Bill(id, this.name, this.uuid, this.currency);
    }

    void addLineItem(LineItemAddition lineItemAddition, Long userId) {
        LineItem lineItem = createLineItem(lineItemAddition, userId);
        lineItems.add(lineItem);
    }

    private LineItem createLineItem(LineItemAddition lineItemAddition, Long userId) {
        return LineItem.of(lineItemAddition.getDescription(), lineItemAddition.getAmount(), userId);
    }
}
