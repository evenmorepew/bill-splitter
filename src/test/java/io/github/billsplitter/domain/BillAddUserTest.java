package io.github.billsplitter.domain;

import org.junit.jupiter.api.Test;

import static io.github.billsplitter.domain.Identifier.fromString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class BillAddUserTest {

    @Test
    void givenAnEmptyBill_shouldIncreaseNumberOfUsers_whenUserIsAdded() {

        Bill bill = new Bill();
        User user = User.builder()
                .identifier(fromString("123"))
                .build();

        bill.addUser(user);

        int numberOfUsers = bill.getNumberOfUsers();
        assertThat(numberOfUsers, is(1));
    }
}