package com.qacart.todo.api;

import com.qacart.todo.config.production.EndPoint;
import com.qacart.todo.objects.Task;
import com.qacart.todo.utils.ConfigUtils;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class TaskApi {
    public void addTask(String token) {
        Task task = new Task("learn selenium", false);


        Response response = RestAssured.
                given()
                .baseUri(ConfigUtils.getInstance().getBaseUrl())
                .header("Content-Type", "application/json")
                .body(task)
                .auth().oauth2(token)

                .when().post(EndPoint.API_TASK_ENDPOINT)
                .then().log().all().extract().response();

        if (response.statusCode()!=201){
            throw new RuntimeException("Something went wrong in adding the todo");
        }

    }
}