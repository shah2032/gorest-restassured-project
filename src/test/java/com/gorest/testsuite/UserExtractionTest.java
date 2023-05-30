package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in";
        response = given()
                .when()
                .get("/public/v2/users")
                .then().statusCode(200);
    }
    //Extract the All Ids
    @Test
    public void test001() {
        List<Integer> allIds = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The All Ids are : " + allIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the all Names
    @Test
    public void test002() {
        List<String> allNames = response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all Names are : " + allNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the name of 5th object
    @Test
    public void test003() {
        String name = response.extract().path("[5].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th object is : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List<String> names = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all object whose status are : " + names);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<String> gender = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all object whose status are : " + gender);
        System.out.println("------------------End of Test---------------------------");
    }

    //Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<String> names = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of the object whose gender = female are : " + names);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get all the emails of the object where status = inactive
    @Test
    public void test007() {
        List<String> emails = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The emails of the object where status = inactive are : " + emails);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get the ids of the object where gender = male
    @Test
    public void test008() {
        List<String> ids = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The emails of the object where status = inactive are : " + ids);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get all the status
    @Test
    public void test009() {
        List<String> status = response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all the status are : " + status);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get email of the object where name = Himadri Naik
    @Test
    public void test010() {
        String email = response.extract().path("find{it.name == 'Guru Talwar'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The email of the object where name = Guru Talwar is : " + email);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get gender of id = 2272603
    @Test
    public void test011() {
        String gender = response.extract().path("find{it.id == 2272483}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ender of id = 2272483 is : " + gender);
        System.out.println("------------------End of Test----------------------");
}
}