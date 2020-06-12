package io.github.billsplitter.domain;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class Payment {

    User issuer;
    User recipient;
    BigDecimal amount;
}
