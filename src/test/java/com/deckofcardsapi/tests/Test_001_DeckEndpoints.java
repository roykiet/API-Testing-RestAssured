package com.deckofcardsapi.tests;

import com.deckofcardsapi.base.TestBase;

import com.deckofcardsapi.clients.DeckClient;
import com.deckofcardsapi.contants.Properties;
import com.deckofcardsapi.models.responses.DrawCardResponse;
import io.restassured.parsing.Parser;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Test_001_DeckEndpoints extends TestBase {
    private DeckClient client;
    private String deckID;

    @BeforeClass
    public void setupClass() {
        logger.log(Level.INFO, "Start Test_001_DeckEndpoints");
        logger.log(Level.INFO, "Configure base URI and base path");
        String baseURI = properties.getProperty(Properties.BASE_URL, "http://deckofcardsapi.com");
        String basePath = properties.getProperty(Properties.DECK_ENDPOINT_PATH, "/api/deck");
        Parser parser = Parser.JSON;
        client = new DeckClient(baseURI, basePath, parser);
    }

    @BeforeMethod
    public void setupTestMethod() { // This method covers Open New Deck endpoint
        logger.log(Level.INFO, "Opening a new deck");
        // Open a new Deck of Cards and ensure that opening is success
        deckID = client.sendGetRequest("/new").then().assertThat().statusCode(200).
                and().extract().response().jsonPath().getString("deck_id");
        Assert.assertNotNull(deckID);
        Assert.assertNotEquals("", deckID);
        logger.log(Level.INFO, "Opened deck is " + deckID);
    }

    @DataProvider(name = "DrawOneOrMultiCardsData")
    public static Object[][] provideDrawOneOrMultiCardsData() {
        return new Object[][]{
            {0, 52},
            {1, 51},
            {2, 50},
            {3, 49}
        };
    }

    @Test (dataProvider = "DrawOneOrMultiCardsData")
    public void testDrawOneOrMultiCards(int number, int expectedRemaining) {
        // Prepare path
        Map<String, Integer> params = new HashMap<>();
        params.put("count", number);

        // Draw a card
        DrawCardResponse drawCardResponse = client.sendGetRequestWithParams("/" + deckID + "/draw", params).then().assertThat().statusCode(200).
                and().extract().response().getBody().as(DrawCardResponse.class);

        Assert.assertTrue(drawCardResponse.isSuccess());
        Assert.assertNotNull(drawCardResponse);
        Assert.assertEquals(number, drawCardResponse.getCards().size());
        Assert.assertEquals(expectedRemaining, drawCardResponse.getRemaining());
        Assert.assertEquals(deckID, drawCardResponse.getDeckID());
    }

    @Test
    public void testDrawCardWithoutParams() {
        // Draw card without params
        DrawCardResponse drawCardResponse = client.sendGetRequest("/" + deckID + "/draw").then().assertThat().statusCode(200).
                and().extract().response().getBody().as(DrawCardResponse.class);

        // Default number of drawn card is 1
        Assert.assertTrue(drawCardResponse.isSuccess());
        Assert.assertNotNull(drawCardResponse);
        Assert.assertEquals(1, drawCardResponse.getCards().size());
        Assert.assertEquals(51, drawCardResponse.getRemaining());
        Assert.assertEquals(deckID, drawCardResponse.getDeckID());
    }

    @Test
    public void testDrawCardMultiTimes() {
        // Draw card without params
        DrawCardResponse drawCardResponse = client.sendGetRequest("/" + deckID + "/draw").then().assertThat().statusCode(200).
                and().extract().response().getBody().as(DrawCardResponse.class);

        // Default number of drawn card is 1
        Assert.assertTrue(drawCardResponse.isSuccess());
        Assert.assertNotNull(drawCardResponse);
        Assert.assertEquals(1, drawCardResponse.getCards().size());
        Assert.assertEquals(51, drawCardResponse.getRemaining());
        Assert.assertEquals(deckID, drawCardResponse.getDeckID());

        // Draw 2 more cards
        Map<String, Integer> params = new HashMap<>();
        params.put("count", 2);

        // Draw a card
        drawCardResponse = client.sendGetRequestWithParams("/" + deckID + "/draw", params).then().assertThat().statusCode(200).
                and().extract().response().getBody().as(DrawCardResponse.class);

        Assert.assertTrue(drawCardResponse.isSuccess());
        Assert.assertNotNull(drawCardResponse);
        Assert.assertEquals(2, drawCardResponse.getCards().size());
        Assert.assertEquals(49, drawCardResponse.getRemaining());
        Assert.assertEquals(deckID, drawCardResponse.getDeckID());
    }
}
