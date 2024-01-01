package api.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.Users;
import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;

public class UserTest {
	Faker faker;
	Users userPayload;

	@BeforeClass

	public void setupData() {

		faker = new Faker();
		userPayload = new Users();
		userPayload.setId(0);
		userPayload.setId(0);

		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

	}
	
	@Test
	public void testresult() {
		System.out.println(this.userPayload.getEmail());
		
	

	}
	

	
	@Test(priority = 1,enabled = false) 
	public void testPostUser() {

		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();

		assertEquals(response.statusCode(), 200);
	}
}
