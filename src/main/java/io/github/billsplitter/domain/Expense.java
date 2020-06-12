package io.github.billsplitter.domain;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
@Builder
public class Expense {

    Identifier identifier;
    BigDecimal amount;
    User payer;
    List<User> involvedUsers;
}
