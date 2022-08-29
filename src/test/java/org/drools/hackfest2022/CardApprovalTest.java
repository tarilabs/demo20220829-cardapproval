package org.drools.hackfest2022;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class CardApprovalTest {

    @Test
    public void testStandardCard() {
        given()
          .body("{ \"Annual Income\": 35, \"Assets\": 150, \"Product\": \"Standard\"}")
          .contentType(ContentType.JSON)
          .when()
            .post("/Card approval")
          .then()
            .statusCode(200)
            .body("'Automatic approval'", is(true))
            .body("'Determined score'", is(458));
    }

    @Test
    public void testGoldCard() {
        given()
          .body("{ \"Annual Income\": 35, \"Assets\": 150, \"Product\": \"Gold\"}")
          .contentType(ContentType.JSON)
          .when()
            .post("/Card approval")
          .then()
            .statusCode(200)
            .body("'Automatic approval'", is(false))
            .body("'Determined score'", is(438));
    }
}
