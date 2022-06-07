package com.revature.models;

import javax.persistence.*;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_id")
    private int cardId;

    @Enumerated(EnumType.ORDINAL)
    private Rank rank;

    @Enumerated(EnumType.ORDINAL)
    private Suit suit;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deck_id")
    private Deck deck;

    public Card( int cardId, Rank rank, Suit suit ) {
        this.cardId = cardId;
        this.rank = rank;
        this.suit = suit;
    }

    public Card( Rank rank, Suit suit ) {
        this.rank = rank;
        this.suit = suit;
    }

    public Card( Rank rank, Suit suit, Deck deck ) {
        this.deck = deck;
        this.rank = rank;
        this.suit = suit;
    }

    public Card() {
    }


    public int getCardId() {
        return cardId;
    }

    public void setCardId( int cardId ) {
        this.cardId = cardId;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank( Rank rank ) {
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit( Suit suit ) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", rank=" + rank +
                ", suit=" + suit +
                '}';
    }
}
