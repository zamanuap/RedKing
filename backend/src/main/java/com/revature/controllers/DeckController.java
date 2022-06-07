package com.revature.controllers;

import com.revature.exceptions.DeckIsEmptyException;
import com.revature.exceptions.NoDeckInPlay;
import com.revature.models.Card;
import com.revature.models.Deck;
import com.revature.models.User;
import com.revature.services.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/deck")
public class DeckController {

    private DeckService ds;

    @Autowired
    public DeckController( DeckService ds ) {
        this.ds = ds;
    }

    @PostMapping("/initialize")
    @ResponseBody
    public Deck handleLogin( @RequestBody User u) {
        return ds.initializeDeck(u);
    }
    
    @GetMapping("/deal/{deckId}")
    @ResponseBody
    public Card handleDealCard( @PathVariable("deckId") Deck deck) throws DeckIsEmptyException {
        return ds.dealCard(deck);
    }
    
    @GetMapping("/getDeck/{deckId}")
    @ResponseBody
    public Deck handleGetDeck (@PathVariable("deckId") Deck deck) {
        return ds.getDeck(deck.getDeckId());
    }

    @GetMapping("/getDeckByUID/{userId}")
    @ResponseBody
    public Deck handleGetDeckByUID (@PathVariable("userId") User user) throws NoDeckInPlay {
        return ds.getDeckByUserId(user);
    }

}
