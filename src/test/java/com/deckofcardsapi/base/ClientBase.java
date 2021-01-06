package com.deckofcardsapi.base;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class ClientBase {
    protected String baseURI;
    protected String basePath;
    protected Parser parser;
    protected Map<String, String> headers;

    public ClientBase(String baseURI, String basePath, Parser parser) {
        this.baseURI = baseURI;
        this.basePath = basePath;
        this.parser = parser;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public RequestSpecification initRequest() {
        RestAssured.baseURI = this.baseURI;
        RestAssured.basePath = this.basePath;
        RestAssured.defaultParser = this.parser;
        RequestSpecification httpRequest = RestAssured.given();
        if (headers != null) {
            httpRequest.headers(headers);
        }
        return httpRequest;
    }

    public Response sendGetRequest(String endpoint) {
        RequestSpecification httpRequest = initRequest();
        return httpRequest.get(endpoint).thenReturn();
    }

    public Response sendGetRequestWithParams(String endpoint, Map<String, ?> params) {
        RequestSpecification httpRequest = initRequest();
        if (params != null && params.size() > 0) {
            httpRequest.queryParams(params);
        }
        return httpRequest.get(endpoint).thenReturn();
    }
}
