package io.github.billsplitter.adapter;

import io.github.billsplitter.domain.BillCreation;
import io.github.billsplitter.domain.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/bill")
@CrossOrigin
public class BillRestController {

    private final BillService billService;

    @Autowired
    public BillRestController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BillDto> createBill(@RequestBody BillCreation billCreation) {
        var uuid = billService.createBill(billCreation);
        var body = new BillDto(billCreation.getName(), uuid);
        return ResponseEntity.ok(body);
    }

}
