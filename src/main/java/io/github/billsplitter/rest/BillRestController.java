package io.github.billsplitter.rest;

import io.github.billsplitter.domain.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/bill")
public class BillRestController {

    private final BillService billService;

    @Autowired
    public BillRestController(BillService billService) {
        this.billService = billService;
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createBill(@RequestBody BillCreation billCreation) {
        return billService.createBill(billCreation);
    }

}
