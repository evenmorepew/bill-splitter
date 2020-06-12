package io.github.billsplitter.domain;

import java.util.Collection;

import static java.util.Collections.unmodifiableCollection;

public class BillStatus {

    private final Collection<Payment> payments;

    static BillStatus of(Collection<Payment> payments) {
        return new BillStatus(payments);
    }

    private BillStatus(Collection<Payment> payments) {
        this.payments = payments;
    }

    public Collection<Payment> getPayments() {
        return unmodifiableCollection(payments);
    }
}
