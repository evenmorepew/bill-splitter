package io.github.billsplitter.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class MoneyAmountTest {

    @Test
    void test_precision() {
        MoneyAmount moneyAmount = new MoneyAmount("1.00");

        assertThat(moneyAmount.getAmount(), is(new BigDecimal("1.00")));
    }
}