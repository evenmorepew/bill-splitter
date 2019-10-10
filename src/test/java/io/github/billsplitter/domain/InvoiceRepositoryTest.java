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
public class InvoiceRepositoryTest {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Test
    public void save_and_load() {
        Invoice invoice = Invoice.of("Test", UUID.randomUUID().toString());
        invoice = invoiceRepository.save(invoice);

        invoice = invoice.withName("Test 2");
        invoiceRepository.save(invoice);

        assertNotNull(invoice.getId());
        assertThat(invoice.getName(), is("Test 2"));
        assertThat(invoiceRepository.count(), is(1L));
    }
}
