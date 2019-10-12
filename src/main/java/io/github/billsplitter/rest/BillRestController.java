package io.github.billsplitter.rest;

import io.github.billsplitter.domain.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/bills")
public class BillRestController {

    private final BillService billService;

    @Autowired
    public BillRestController(BillService billService) {
        this.billService = billService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public void createBill() {

    }

}
