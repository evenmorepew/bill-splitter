package io.github.billsplitter.domain;

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

    public String createBill(String name) {
        Bill bill = Bill.of(name, UUID.randomUUID().toString());
        bill = repository.save(bill);
        return bill.getUuid();
    }

}
