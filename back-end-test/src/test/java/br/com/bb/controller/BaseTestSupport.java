package br.com.bb.controller;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.springframework.boot.web.server.LocalServerPort;


import static io.restassured.RestAssured.given;

public class BaseTestSupport {

    @LocalServerPort
    protected int port;

    protected int getLocalServerPort() {
        return port;
    }

    @Before
    public void setup() {
    }

    protected static Response doGetRequest(String endpoint, String body) {
        return
                given().log().all()
                        .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                        .body(body)
                        .when().get(endpoint);
    }

    protected static Response doPostRequest(String endpoint, String body) {
        return
                given().log().all()
                        .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                        .body(body)
                        .when().post(endpoint);
    }
}
