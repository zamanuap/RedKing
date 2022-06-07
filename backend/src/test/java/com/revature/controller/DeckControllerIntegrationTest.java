package com.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.BlackJackAppApplication;
import com.revature.models.*;
import com.revature.repository.DeckRepo;
import com.revature.repository.UserRepo;
import com.revature.services.DeckService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BlackJackAppApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class DeckControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepo ur;

    @Autowired
    private DeckRepo dr;

    private ObjectMapper om = new ObjectMapper();

    @Test
    @Transactional
    public void testInitializeDeck() throws Exception {
        User testUser = new User("test@email.com", "test_first", "test_last", "test_password", 8);
        ur.save(testUser);

        testUser.setUserId(1);

        mockMvc.perform(post("/deck/initialize")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(testUser))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deckSize").value(52));
    }

    @Test
    @Transactional
    public void testGetDeck() throws Exception {
        Deck testDeck = new Deck();
        testDeck.setDeckId(1);
        testDeck.setDeckSize(49);
        dr.save(testDeck);

        mockMvc.perform(get("/deck/getDeck/" + 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deckSize").value(49));
    }

    @Test
    @Transactional
    public void testDealNoCard() throws Exception {
        Deck testDeck = new Deck();

        testDeck.setDeckId(2);
        testDeck.setDeckSize(0);

        dr.save(testDeck);

        mockMvc.perform(get("/deck/deal/" + 2))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @Transactional
    public void testGetDeckById() throws Exception {
        User testUser = new User("test@email.com", "test_first", "test_last", "test_password", 8);
        testUser.setUserId(0);
        ur.save(testUser);

        Deck testDeck = new Deck(1, 49);
        testDeck.setUser(testUser);
        dr.save(testDeck);

        mockMvc.perform(get("/deck/getDeckByUID/" + 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deckSize").value(49));
    }
}
