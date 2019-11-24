package io.github.billsplitter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import static lombok.AccessLevel.PACKAGE;

@Getter
@AllArgsConstructor(access = PACKAGE)
public class User {

    @Id
    private final Long id;
    private final String uuid;
    private final String name;
    private final Long billId;
}
