package Day1;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.internal.net.http.common.Log;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class HTTPRequest {
	@Test
	void Getusers() {
		given()
		.when()
		.get("https://reqres.in/api/users/2")
		//https://reqres.in/api/users?page=2
		.then()
		.statusCode(200);
		//.body("per_page", equalTo(6))
		
		 
	}

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @Test
    public void testGetRequest() {
        Response response = given().get(BASE_URL + "/posts/1");
        response.then().statusCode(200)
        .log().all();
        System.out.println(response.getBody().asString());
    }
	
    @Test
    public void testPostRequest() {
        String requestBody = "{\"title\": \"Test\", \"body\": \"TestUser\", \"userId\": 2}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(BASE_URL + "/posts");

        response.then().statusCode(201);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void testPutRequest() {
        String requestBody = "{\"id\": 1, \"title\": \"updated title\", \"body\": \"updated body\", \"userId\": 1}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put(BASE_URL + "/posts/1");

        response.then().statusCode(200);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void testDeleteRequest() {
        Response response = given().delete(BASE_URL + "/posts/1");
        response.then().statusCode(200);
        System.out.println(response.getBody().asString());
    }
    
    @Test
    public void testStatus304() {
        Response response = given().get("https://httpstat.us/304");
        response.then().statusCode(304);
        System.out.println("Status 304: Not Modified");
    }
}
