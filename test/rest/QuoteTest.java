/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.parsing.Parser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sabre
 */
public class QuoteTest {

    public QuoteTest() {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        baseURI = "http://localhost:8084";
        defaultParser = Parser.JSON;
        basePath = "/RESTwithAJAX/api/quote";
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getRandomQuote method, of class Quote.
     */
    @Test
    public void testGetRandomQuote() throws Exception {
        given()
                // Set the content type
                .contentType(MediaType.APPLICATION_JSON
                )
                // add the object to be sent (will be converted to JSON)
                .body(c)
                .when()
                .get("")
                .then().
                // Assert that there is a categoryName of that type
                body("categoryName", equalTo(c.getCategoryName()));
    }

}
