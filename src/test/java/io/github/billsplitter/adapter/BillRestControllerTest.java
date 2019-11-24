package io.github.billsplitter.adapter;

import io.github.billsplitter.domain.BillAddService;
import io.github.billsplitter.domain.BillAddition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static io.github.billsplitter.JacksonTestUtils.body;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BillRestController.class)
public class BillRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BillAddService billAddService;

    @Test
    public void testExample() throws Exception {
        String uuid = UUID.randomUUID().toString();
        BillAddition billAddition = BillAddition.builder()
                .name("bill-name")
                .build();

        when(this.billAddService.addBill(billAddition)).thenReturn(uuid);

        this.mvc.perform(post("/bill/").content(body(billAddition)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(body(new BillDto(billAddition.getName(), uuid))));
    }

}
