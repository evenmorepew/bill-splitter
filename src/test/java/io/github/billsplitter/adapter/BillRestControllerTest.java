package io.github.billsplitter.adapter;

import io.github.billsplitter.domain.BillService;
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
    private BillService billService;

    @Test
    public void testExample() throws Exception {
        String uuid = UUID.randomUUID().toString();
        BillCreation billCreation = new BillCreation("bill-name");
        when(this.billService.createBill(billCreation.getName())).thenReturn(uuid);

        this.mvc.perform(post("/bill/").content(body(billCreation)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(body(new BillDto(billCreation.getName(), uuid))));
    }

}
