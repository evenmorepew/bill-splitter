package io.github.billsplitter.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService {

    private final BillRepository repository;

    @Autowired
    public BillService(BillRepository repository) {
        this.repository = repository;
    }

    private Bill createBill() {
        return null;
    }

}
