package io.github.billsplitter.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
@AutoConfigureJdbc
public class BillRepositoryTest {

    @Autowired
    private BillRepository billRepository;

    @Test
    public void save_and_load() {
        Bill bill = Bill.of("Test", UUID.randomUUID().toString());
        bill = billRepository.save(bill);

        bill = bill.withName("Test 2");
        billRepository.save(bill);

        assertNotNull(bill.getId());
        assertThat(bill.getName(), is("Test 2"));
        assertThat(billRepository.count(), is(1L));
    }
}
