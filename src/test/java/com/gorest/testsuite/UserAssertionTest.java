package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in";
        response = given()
                .when()
                .get("/public/v2/users")
                .then().statusCode(200);
    }


    //1. Verify the if the total record is 20
    @Test
    public void VerifyTheIfTheTotalRecordIs21() {
        response.body("size", equalTo(10));
    }

    // 2. Verify the if the name of id = 2272630 is equal to ”Bhaumik Varma”
    @Test
    public void test002() {

        response.body("[0].name", equalTo("Gudakesha Ahluwalia"));
    }

    // 3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void test003() {

        response.body("[2].name", equalTo("Leela Talwar"));
    }


    //4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan Guha, Karthik Dubashi IV)
    @Test
    public void test004() {

        response.body("name", hasItems("Bharat Butt", "Guru Talwar", "Susheel Dutta"));
    }


    // 5. Verify the emai of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void test005() {

        response.body("[3].id", equalTo(2272481));
        response.body("[3].email", equalTo("guru_talwar@ledner.test"));

    }

    // 6. Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void test006() {

        response.body("[5].status", equalTo("active"));
        response.body("[5].name", equalTo("Rukmin Embranthiri"));

    }


    // 7. Verify the Gender = male of user name is  "Ganaka Prajapat DVM"

    @Test
    public void test007() {
        response.body("[6].gender", equalTo("male"));
        response.body("[6].name",equalTo( "Gudakesha Ahluwalia"));
    }

}



