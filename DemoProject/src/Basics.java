import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.ReUsableMethods;
import Files.payload;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Validate if add place API is working as expected
		
		// given - All input details
		// when - Submit the API - Resource, http method
		// Then - Validate the response
		// Content of the file to String -> content of the file can convert into Byte-> Byte data to String
		
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);   //for parsing Json
		String placeId = js.getString("place_id");
		System.out.println(placeId);
		System.out.println("Testing");
		
		
		// update place
		String newAddress = "Summer walk, Africa";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		// Get place
		
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
		String ActualAddress = js1.getString("address");
		System.out.println(ActualAddress);
		Assert.assertEquals(ActualAddress, newAddress);
		
		
		
		
		
		
		// Add place -> Update place with new address -> Get place to validate if new address is present in response

	}

}
