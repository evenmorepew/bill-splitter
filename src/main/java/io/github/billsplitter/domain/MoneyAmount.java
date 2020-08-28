package io.github.billsplitter.domain;

import lombok.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Value
public class MoneyAmount implements Comparable<MoneyAmount> {

    private static final RoundingMode ROUNDING_MODE = RoundingMode.DOWN;

    static final MoneyAmount ZERO = MoneyAmount.of(0.00);
    static final MoneyAmount ONE_CENT = MoneyAmount.of(0.01);

    BigDecimal value;

    static MoneyAmount of(double value) {
        return new MoneyAmount(BigDecimal.valueOf(value).setScale(2, ROUNDING_MODE));
    }

    public MoneyAmount divide(int divisor) {
        BigDecimal division = value.divide(BigDecimal.valueOf(divisor), ROUNDING_MODE);
        return of(division.doubleValue());
    }

    public MoneyAmount multiply(int factor2) {
        BigDecimal product = value.multiply(BigDecimal.valueOf(factor2));
        return of(product.doubleValue());
    }

    public MoneyAmount add(MoneyAmount amount) {
        return of(this.value.add(amount.value).doubleValue());
    }

    public MoneyAmount subtract(MoneyAmount amount) {
        return of(this.value.subtract(amount.value).doubleValue());
    }

    public MoneyAmount abs() {
        return of(this.value.abs().doubleValue());
    }

    @Override
    public int compareTo(MoneyAmount o) {
        return this.value.compareTo(o.value);
    }

    public boolean isGreaterZero() {
        return this.value.compareTo(BigDecimal.ZERO) > 0;
    }
}
