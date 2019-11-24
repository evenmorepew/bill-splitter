package io.github.billsplitter.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@DataJpaTest
class BillRepositoryTest {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LineItemRepository lineItemRepository;

    @Test
    void save_and_load() {
        Bill bill = Bill.builder()
                .name("Test")
                .uuid(UUID.randomUUID().toString())
                .currency(Currency.EUR)
                .build();
        bill = billRepository.save(bill);

        bill.setName("Test 2");
        billRepository.save(bill);

        assertNotNull(bill.getId());
        assertThat(bill.getName(), is("Test 2"));
        assertThat(billRepository.count(), is(1L));
    }

    @Test
    void save_with_user_and_line_items() {
        Bill bill = Bill.builder()
                .name("Test")
                .uuid(UUID.randomUUID().toString())
                .currency(Currency.EUR)
                .user(createUser("Test User 1"))
                .user(createUser("Test User 2"))
                .build();

        billRepository.save(bill);

        assertThat(userRepository.count(), is(2L));
        assertThat(lineItemRepository.count(), is(4L));
    }

    private User createUser(String name) {
        return User.builder()
                .name(name)
                .uuid(UUID.randomUUID().toString())
                .lineItem(createLineItem("Cinema", "20.00"))
                .lineItem(createLineItem("Food", "10.00"))
                .build();
    }

    private LineItem createLineItem(String description, String amount) {
        return LineItem.builder()
                .description(description)
                .amount(new BigDecimal(amount))
                .uuid(UUID.randomUUID().toString())
                .build();
    }
}
