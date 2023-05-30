package com.gorest.CURDTest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasValue;

public class UserCURDTest extends TestBase {
    static String name = "David" + TestUtils.getRandomValue();
    static String email = TestUtils.getRandomString() + TestUtils.getRandomValue() + "@gmail.com";
    static String gender = "male";
    static String status = "active";
    static int userId;

    @Test
    public void verifyUserCreatedSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .header("Authorization", "Bearer b68804a565ce64279752413286834ff14f9d23abf9dc7dd8ed765b05b051f7e4")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(userPojo)
                .post("/users");
        response.then().log().all().statusCode(201);
    }

    @Test
    public void verifyUserGetSuccessfully() {
        String s1 = "findAll{it.name == '";
        String s2 = "'}.get(0)";
        HashMap<String, Object> userMap = given()
                //Response response = given()
                .header("Authorization", "Bearer b68804a565ce64279752413286834ff14f9d23abf9dc7dd8ed765b05b051f7e4")
                .header("Connection", "keep-alive")
                .when()
                .get("/users")
                .then().statusCode(200)
                .extract()
                .path(s1 + name + s2);
        Assert.assertThat(userMap, hasValue(name));
    }

    @Test()
    public void verifyUserIsUpdateSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName("William");
        userPojo.setEmail("qwe123@gmail.com");
        userPojo.setGender("male");
        userPojo.setStatus("active");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer b68804a565ce64279752413286834ff14f9d23abf9dc7dd8ed765b05b051f7e4")
                .header("Connection", "keep-alive")
                .when()
                .body(userPojo)
                .put("/users/2324429");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void VerifyUseruDeleteSuccessfully() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer b68804a565ce64279752413286834ff14f9d23abf9dc7dd8ed765b05b051f7e4")
                .header("Connection", "keep-alive")
                .when()
                .delete("/users/2324429");
        response.then().statusCode(204);
    }

}
