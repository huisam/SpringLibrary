package com.huisam.springstudy.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(OrderValidController.class)
class OrderValidControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Request 이름이 null이면 BadRequest를 응답한다")
    void when_request_name_is_null_then_return_badReqeust() throws Exception {
        /* given */
        OrderRequest orderRequest = OrderRequest.builder()
                .name(null)
                .product("computer")
                .address("Seoul")
                .build();

        /* when & then */
        mockMvc.perform(post("/valid/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderRequest))
        )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}