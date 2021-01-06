package com.deckofcardsapi.models.responses;

import com.deckofcardsapi.models.Deck;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DrawCardResponse {
    private boolean success;

    private List<Deck> cards;

    @JsonProperty("deck_id")
    private String deckID;

    private int remaining;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Deck> getCards() {
        return cards;
    }

    public void setCards(List<Deck> cards) {
        this.cards = cards;
    }

    public String getDeckID() {
        return deckID;
    }

    public void setDeckID(String deckID) {
        this.deckID = deckID;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
}
