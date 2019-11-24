package io.github.billsplitter.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class LineItem {

    @Id
    private final Long id;
    private final String uuid;
    private final String description;
    @Embedded.Empty
    private final MoneyAmount amount;
    private final Long userId;

    static LineItem of(String description, String amount, Long userId) {
        String uuid = UUID.randomUUID().toString();
        return new LineItem(null, uuid, description, new MoneyAmount(amount), userId);
    }

    LineItem withId(Long id) {
        return new LineItem(id, this.uuid, this.description, this.amount, this.userId);
    }
}
