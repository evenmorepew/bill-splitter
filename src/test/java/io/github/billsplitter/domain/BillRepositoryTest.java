package io.github.billsplitter.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@SpringBootTest
@AutoConfigureJdbc
class BillRepositoryTest {

    @Autowired
    private BillRepository billRepository;

    @Test
    void save_and_load() {
        Bill bill = Bill.of("Test", UUID.randomUUID().toString());
        bill = billRepository.save(bill);

        bill = bill.withName("Test 2");
        billRepository.save(bill);

        assertNotNull(bill.getId());
        assertThat(bill.getName(), is("Test 2"));
        assertThat(billRepository.count(), is(1L));
    }

    @Test
    void save_bill_with_item() {
        Bill bill = Bill.of("Test", UUID.randomUUID().toString());
        LineItemAddition lineItemAddition = LineItemAddition.builder()
                .amount("20.00")
                .description("Cinema")
                .build();

        bill.addLineItem(lineItemAddition, 123L);

        bill = billRepository.save(bill);

        bill = bill.withName("Update");

        billRepository.save(bill);
    }
}
