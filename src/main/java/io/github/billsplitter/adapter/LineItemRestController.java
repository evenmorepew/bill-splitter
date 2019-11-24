package io.github.billsplitter.adapter;

import io.github.billsplitter.domain.LineItemAddService;
import io.github.billsplitter.domain.LineItemAddition;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/line-item")
@CrossOrigin
public class LineItemRestController {

    private final LineItemAddService lineItemAddService;

    public LineItemRestController(LineItemAddService lineItemAddService) {
        this.lineItemAddService = lineItemAddService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<LineItemDto> addLineItem(@RequestBody LineItemAddition lineItemAddition) {
        var uuid = lineItemAddService.addLineItem(lineItemAddition);
        var body = new LineItemDto(uuid);
        return ResponseEntity.ok(body);
    }


}
