package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "deck")
public class Deck {

    @Id
    @Column(name = "deck_id")
    private int deckId;

    @Column(name = "deck_size")
    private int deckSize;

    //One Deck to Many Cards
    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL )
    List<Card> cards;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public Deck( int deckId, int deckSize ) {
        this.deckId = deckId;
        this.deckSize = deckSize;
        this.cards = new ArrayList<>();
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId( int deckId ) {
        this.deckId = deckId;
    }

    public int getDeckSize() {
        return deckSize;
    }

    public void setDeckSize( int deckSize ) {
        this.deckSize = deckSize;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards( List<Card> cards ) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deckId=" + deckId +
                ", deckSize=" + deckSize +
                ", cards=" + cards +
                '}';
    }
}
