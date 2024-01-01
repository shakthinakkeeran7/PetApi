package api.endpoints;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import api.payload.Users;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//create response for create ,get, update, delete,
public class UserEndPoints {

	public static Response createUser(Users payload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(Routes.post_url);

		return response;
	}

	public static Response readUser(String userName) {
		Response response = given()

				.pathParam("username", userName)

				.when().get(Routes.get_url);

		return response;

	}

	public static Response updateUser(String userName, Users payload) {

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", userName).body(payload).when().put(Routes.update_url);

		return response;
	}

	public static Response deleteUser(String userName) {

		Response response = given()

				.pathParam("username", userName)

				.when().get(Routes.delete_url);

		return response;
	}

}
