package com.deckofcardsapi.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenNewDeckResponse {
    private boolean success;

    @JsonProperty("deck_id")
    private String deckID;

    private boolean shuffled;

    private int remaining;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDeckID() {
        return deckID;
    }

    public void setDeckID(String deckID) {
        this.deckID = deckID;
    }

    public boolean isShuffled() {
        return shuffled;
    }

    public void setShuffled(boolean shuffled) {
        this.shuffled = shuffled;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
}
