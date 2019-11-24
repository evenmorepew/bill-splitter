package io.github.billsplitter.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class MoneyAmount {

    private static final int SCALE = 2;

    private final BigDecimal amount;

    public MoneyAmount(String amount) {
        this.amount = new BigDecimal(amount).setScale(SCALE, RoundingMode.HALF_UP);

    }
}
