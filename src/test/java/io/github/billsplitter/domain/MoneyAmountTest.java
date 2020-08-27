package io.github.billsplitter.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class MoneyAmountTest {

    @Test
    void divide_1() {
        MoneyAmount dividend = MoneyAmount.of(10.00);
        MoneyAmount division = dividend.divide(3);

        assertThat(division, is(MoneyAmount.of(3.33)));
    }

    @Test
    void divide_2() {
        MoneyAmount dividend = MoneyAmount.of(10.00);
        MoneyAmount division = dividend.divide(6);

        assertThat(division, is(MoneyAmount.of(1.66)));
    }

    @Test
    void divide_3() {
        MoneyAmount dividend = MoneyAmount.of(10.00);
        MoneyAmount division = dividend.divide(5);

        assertThat(division, is(MoneyAmount.of(2.00)));
    }

    @Test
    void divide_4() {
        MoneyAmount dividend = MoneyAmount.of(0.00);
        MoneyAmount division = dividend.divide(5);

        assertThat(division, is(MoneyAmount.of(0.00)));
    }

    @Test
    void divide_5() {
        MoneyAmount dividend = MoneyAmount.of(0.50);
        MoneyAmount division = dividend.divide(5);

        assertThat(division, is(MoneyAmount.of(0.10)));
    }

    @Test
    void divide_6() {
        MoneyAmount dividend = MoneyAmount.of(0.40);
        MoneyAmount division = dividend.divide(5);

        assertThat(division, is(MoneyAmount.of(0.08)));
    }

    @Test
    void divide_7() {
        MoneyAmount dividend = MoneyAmount.of(0.40);
        MoneyAmount division = dividend.divide(7);

        assertThat(division, is(MoneyAmount.of(0.05)));
    }

    @Test
    void divide_8() {
        MoneyAmount dividend = MoneyAmount.of(25.99);
        MoneyAmount division = dividend.divide(5);

        assertThat(division, is(MoneyAmount.of(5.19)));
    }
}