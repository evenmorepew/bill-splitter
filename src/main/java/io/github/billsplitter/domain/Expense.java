package io.github.billsplitter.domain;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Expense {

    Identifier identifier;
    MoneyAmount amount;
    User payer;
    List<User> involvedUsers;
}
