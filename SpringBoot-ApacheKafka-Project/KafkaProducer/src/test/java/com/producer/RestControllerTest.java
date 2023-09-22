package com.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;

@ContextConfiguration(classes = {RestController.class})
@ExtendWith(SpringExtension.class)
class RestControllerTest {

    @Autowired
    private RestController restController;

    @MockBean
    private KafkaProducer kafkaProducer;

    @Test
    void testPublish() throws Exception {
        doNothing().when(kafkaProducer).sendToTopic(Mockito.<String>any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/v1/reports/publish")
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString("foo"));
        MockMvcBuilders.standaloneSetup(restController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("JSON message sent to topic"));
    }
}