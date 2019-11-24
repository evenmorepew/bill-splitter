package io.github.billsplitter.adapter;

import io.github.billsplitter.domain.BillAddService;
import io.github.billsplitter.domain.BillAddition;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/bill")
@CrossOrigin
public class BillRestController {

    private final BillAddService billAddService;

    public BillRestController(BillAddService billAddService) {
        this.billAddService = billAddService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BillDto> addBill(@RequestBody BillAddition billAddition) {
        var uuid = billAddService.addBill(billAddition);
        var body = new BillDto(billAddition.getName(), uuid);
        return ResponseEntity.ok(body);
    }

}
