package com.deckofcardsapi.clients;

import com.deckofcardsapi.base.ClientBase;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class DeckClient extends ClientBase {
    public DeckClient(String baseURI, String basePath, Parser parser) {
        super(baseURI, basePath, parser);
    }
}
