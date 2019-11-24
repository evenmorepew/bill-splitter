package io.github.billsplitter.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BillAddService {

    private final BillRepository repository;

    @Autowired
    public BillAddService(BillRepository repository) {
        this.repository = repository;
    }

    public String addBill(BillAddition billAddition) {
        Bill bill = Bill.of(billAddition.getName(), UUID.randomUUID().toString());
        bill = repository.save(bill);
        return bill.getUuid();
    }

}
