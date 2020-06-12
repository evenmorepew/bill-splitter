package io.github.billsplitter.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class PaymentCalculator {

    Collection<Payment> calc(Map<User, BigDecimal> userBalance) {
        List<Map.Entry<User, BigDecimal>> sortedByValue = userBalance.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());

        Collection<Payment> payments = new ArrayList<>();

        int i = 0;
        int j = sortedByValue.size() - 1;
        while (i < j) {

            BigDecimal first = sortedByValue.get(i).getValue();
            BigDecimal last = sortedByValue.get(j).getValue();

            BigDecimal firstAbs = first.abs();

            if (firstAbs.compareTo(last) < 0) {

                Payment paymentFirstToLast = Payment.builder()
                        .issuer(sortedByValue.get(i).getKey())
                        .recipient(sortedByValue.get(j).getKey())
                        .amount(firstAbs)
                        .build();
                payments.add(paymentFirstToLast);

                sortedByValue.get(j).setValue(last.subtract(firstAbs));

                i++;

            } else if (firstAbs.compareTo(last) == 0) {

                Payment paymentFirstToLastFull = Payment.builder()
                        .issuer(sortedByValue.get(i).getKey())
                        .recipient(sortedByValue.get(j).getKey())
                        .amount(firstAbs)
                        .build();
                payments.add(paymentFirstToLastFull);

                i++;
                j--;

            } else {

                Payment paymentFirstToLast = Payment.builder()
                        .issuer(sortedByValue.get(i).getKey())
                        .recipient(sortedByValue.get(j).getKey())
                        .amount(sortedByValue.get(j).getValue())
                        .build();
                payments.add(paymentFirstToLast);

                sortedByValue.get(i).setValue(first.add(last));

                j--;
            }

        }

        return payments;
    }
}
