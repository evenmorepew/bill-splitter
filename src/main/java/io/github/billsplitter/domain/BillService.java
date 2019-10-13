package io.github.billsplitter.domain;

import io.github.billsplitter.rest.BillCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BillService {

    private final BillRepository repository;

    @Autowired
    public BillService(BillRepository repository) {
        this.repository = repository;
    }

    public String createBill(BillCreation billCreation) {
        Bill bill = Bill.of(billCreation.getName(), UUID.randomUUID().toString());
        bill = repository.save(bill);
        return bill.getUuid();
    }

}
