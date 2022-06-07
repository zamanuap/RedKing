package com.revature.services;

import com.revature.exceptions.DeckIsEmptyException;
import com.revature.exceptions.NoDeckInPlay;
import com.revature.models.*;
import com.revature.repository.DeckRepo;
import com.revature.services.DeckService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DeckServiceTest {

    @MockBean
    public static DeckRepo dr;

    @Autowired
    public static DeckService ds;

    @Test
    public void testDeckInitializer() {
        ds = new DeckService(dr);
        Deck testDeck = new Deck();

        //Create a list of 1 card
        List<Card> lCards = new ArrayList<>();
        Card testCard = new Card();
        testCard.setCardId(1);
        testCard.setRank(Rank.ACE);
        testCard.setSuit(Suit.CLOVERS);
        lCards.add(0, testCard);

        //Create a temp user
        User u = new User("test@gmail.com", "test_first", "test_last", "test_password", 0);

        //add user and cards to deck
        testDeck.setCards(lCards);
        testDeck.setUser(u);
        testDeck.setDeckSize(52);

        Mockito.when(dr.save(Mockito.any())).thenReturn(testDeck);
        Assertions.assertEquals(testDeck, ds.initializeDeck(u));
    }

    @Test
    public void testDealCard() throws DeckIsEmptyException {
        ds = new DeckService(dr);
        Deck testDeck = new Deck();

        //Create a list of 1 card
        List<Card> lCards = new ArrayList<>();
        Card testCard1 = new Card(Rank.ACE, Suit.CLOVERS, testDeck);
        Card testCard2 = new Card(Rank.ACE, Suit.CLOVERS, testDeck);
        Card testCard3 = new Card(Rank.ACE, Suit.CLOVERS, testDeck);

        testCard1.setCardId(1);
        lCards.add(0, testCard1);
        lCards.add(1, testCard2);
        lCards.add(2, testCard3);

        testDeck.setDeckSize(3);
        //Create a temp user
        User u = new User("test@gmail.com", "test_first", "test_last", "test_password", 0);

        //add user and cards to deck
        testDeck.setCards(lCards);
        testDeck.setUser(u);
        testDeck.setDeckSize(lCards.size());

        Mockito.when(dr.findDeckByDeckId(Mockito.anyInt())).thenReturn(testDeck);
        Assertions.assertEquals(Rank.ACE, ds.dealCard(testDeck).getRank());
    }

    @Test
    public void testDealCardException() throws DeckIsEmptyException {
        ds = new DeckService(dr);
        Deck testDeck = new Deck();

        testDeck.setDeckSize(0);

        Mockito.when(dr.findDeckByDeckId(Mockito.anyInt())).thenReturn(testDeck);
        Assertions.assertThrows(DeckIsEmptyException.class, () -> {
            ds.dealCard( testDeck );
        });
    }

    @Test
    public void testGenUniqueId() {
        ds = new DeckService(dr);
        Deck testDeck = new Deck();
        testDeck.setDeckId(1);

        Mockito.when(dr.findDeckByDeckId(0)).thenReturn(testDeck);
        Assertions.assertEquals(1, ds.generateUniqueId());
    }

    @Test
    public void testGetDeck() {
        ds = new DeckService(dr);
        Deck deck = new Deck();

        deck.setDeckId(777);
        Mockito.when(dr.findDeckByDeckId(Mockito.anyInt())).thenReturn(deck);
        Assertions.assertEquals(777, ds.getDeck(777).getDeckId());

    }

    @Test
    public void testGetDeckByUID() throws NoDeckInPlay {
        ds = new DeckService(dr);
        List<Deck> deckList = new ArrayList<>();
        Deck deck = new Deck();

        User t1 = new User("t1@email.com", "t1", "lastT1", "t1-password", 86);
        t1.setUserId(0);

        /*create a mock deck*/
        deck.setDeckId(0);
        deck.setUser(t1);
        deck.setDeckSize(1);
        deckList.add(deck);

        /*Mockito and Unit tests*/
        Mockito.when(dr.findAll()).thenReturn(deckList);
        Assertions.assertEquals(0, ds.getDeckByUserId(t1).getDeckId());
    }

    @Test
    public void testGetDeckByUIDException() throws NoDeckInPlay {
        ds = new DeckService(dr);
        List<Deck> deckList = new ArrayList<>();
        Deck deck = new Deck();

        User t1 = new User("t1@email.com", "t1", "lastT1", "t1-password", 86);
        t1.setUserId(0);

        /*create a mock deck*/
        deck.setDeckId(0);
        deck.setUser(t1);
        deckList.add(deck);

        /*Mockito and Unit tests*/
        Mockito.when(dr.findAll()).thenReturn(deckList);
        Assertions.assertThrows(NoDeckInPlay.class, () -> {
            ds.getDeckByUserId(t1);
        });
    }
}
