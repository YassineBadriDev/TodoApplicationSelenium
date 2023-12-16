package com.qacart.todo.api;

import com.qacart.todo.objects.User;
import com.qacart.todo.utils.userUtils;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RegisterApi {
    private   List<Cookie> restAssuredCookies;
    private String accessToken;
    private String userId;
    private String firstName;
    public String getFirstName(){
        return this.firstName;
    }
    public String getToken(){
        return this.accessToken;
    }
    public String getUserId(){
        return this.userId;
    }
    public  List<Cookie>  getCookies(){

        return this.restAssuredCookies;
    }
    public void register(){
     // User user = new userUtils().generateRandomUser();
        // User user = new User("tessjktjkdjkd","saaaaidcdfg","yassen@badrikfg114.com","Yads1783172");
    String user="{\n" +
                "    \"email\":\"yassen@badriyassine.com\",\n" +
                "    \"firstName\":\"yassine\",\n" +
                "    \"lastName\":\"badri\",\n" +
                "    \"password\":\"Yass17g83172\"\n" +
                "\n" +
                "}";
        Response response= given()
                                  .baseUri("https://qacart-todo.herokuapp.com")
                                  .header("Content-Type","application/json")
                                  .body(user).log().all()
                          .when()
                                  .post("/api/v1/users/register")
                          .then().log().all()
                                  .extract().response();
if(response.statusCode()!=201){
    throw new RuntimeException("something went wrong with the exception");
}
        restAssuredCookies=   response.detailedCookies().asList();
 accessToken= response.path("access_token");
 userId=response.path("userID");
        firstName=response.path("firstName");
    }
}
