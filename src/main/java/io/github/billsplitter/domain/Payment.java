package io.github.billsplitter.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Payment {

    User issuer;
    User recipient;
    MoneyAmount amount;
}
