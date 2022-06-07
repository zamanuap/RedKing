package com.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.BlackJackAppApplication;
import com.revature.models.MailHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BlackJackAppApplication.class)
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@ActiveProfiles("test")

public class MailControllerIntegrationTest {
        @Autowired
        private MockMvc mockMvc;
        @Autowired
        private ObjectMapper om;

        @Test
        public void testHandleSendEmail() throws Exception{
            MailHelper mailHelper = new MailHelper("Mohammad", "test@gmail.com", "Register");

            mockMvc.perform(post("/mail")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(om.writeValueAsString(mailHelper))
                    )
                    .andDo(print())
                    .andExpect(status().isOk());
                    //.andExpect(content().string(containsString("mail sent")));
            }

    }