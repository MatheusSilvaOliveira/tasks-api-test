package br.ce.msoliveira.tasks.apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;


public class APITest {

    private JSONObject getJson(){

        JSONParser parser = new JSONParser();
        JSONObject jsonObject;

        try {
            Object obj = parser.parse(new FileReader("src/test/resources/mocks/date.json"));
            jsonObject = (JSONObject) obj;

            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://localhost:8001/tasks-backend";
    }

    @DisplayName("Should list all date.")
    @Test
    public void getAllDate(){
        RestAssured.
                when().
                    get("/todo").
                then().
                    statusCode(200);
    }


    @DisplayName("Should create a date.")
    @Test
    public void createDate(){
        RestAssured.
                given().
                    body(getJson().toString()).contentType(ContentType.JSON).
                when().
                    post("/todo").
                then().
                    statusCode(201);
    }

//    @DisplayName("Can't create with past date.")
//    @Test
//    public void failCreateDate(){
//        RestAssured.
//                given().
//                body(getJson().toString()).contentType(ContentType.JSON).
//                when().
//                post("/todo").
//                then().
//                statusCode(201);
//        System.out.println(getJson());
//    }
//
//    @DisplayName("Can't create with empty Task.")
//    @Test
//    public void failCreateTask(){
//        RestAssured.
//                given().
//                body(getJson().put("task","s")).contentType(ContentType.JSON).
//                when().
//                post("/todo").
//                then().
//                statusCode(201);
//    }
}
