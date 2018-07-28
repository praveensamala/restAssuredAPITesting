package restAPIPackage;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import junit.framework.Assert;

public class restAPITests {
	// This test case will just call the GET service and print the response
	@Test(enabled = false)
	public void sampleTest01() throws URISyntaxException, MalformedURLException {
		/*
		 * Response resp = get("http://services.groupkt.com/country/get/all");
		 * System.out.println("resp : "+resp.getStatusCode());
		 * 
		 * String json = resp.asString(); JsonPath jsonpath = new JsonPath (json);
		 * 
		 * System.out.println("jsonpath : "+jsonpath.get("ResResponse").toString());
		 */

		// given().get("http://services.groupkt.com/country/get/iso2code/IN").then().statusCode(200).log().all();

		// Response resp = RestAssured.when().get(new
		// URI("http://services.groupkt.com/country/get/iso2code/IN"));
		Response resp = RestAssured.given().when().get(new URL("http://services.groupkt.com/country/get/iso2code/IN"));
		System.out.println(resp.asString());
	}

	// This test case will call the GET service but will specify that the response should be in JSON format
	@Test(enabled = false)
	public void sampleTest02() throws URISyntaxException, MalformedURLException {
		Response resp = RestAssured.given().accept(ContentType.JSON).when().get(new URL("http://services.groupkt.com/country/get/iso2code/IN"));
		System.out.println(resp.asString());
	}

	// This test case will call the GET service but will specify that the response should be in JSON format
	@Test (enabled = false)
	public void sampleTest03() throws URISyntaxException, MalformedURLException {
		Response resp = RestAssured.given().accept(ContentType.XML).when().get(new URL("http://services.groupkt.com/country/get/iso2code/IN"));
		System.out.println(resp.asString());
	}
	
	//This test case will capture the status code of the response and will validate the status code
	@Test (enabled = false)
	public void sampleTest04() throws URISyntaxException, MalformedURLException {
		int statuscode = RestAssured.given().accept(ContentType.JSON).when().get(new URL("http://services.groupkt.com/country/get/iso2code/IN")).thenReturn().statusCode();
		Assert.assertEquals(HttpStatus.SC_OK, statuscode);
	}
	
	//This test case will validate the status code with in the restassured get statement call itself as mentioned below
	@Test (enabled = true) 
	public void sampleTest05() throws URISyntaxException, MalformedURLException {
		RestAssured.given().accept(ContentType.JSON).when().get(new URL("http://services.groupkt.com/country/get/iso2code/IN")).then().assertThat().statusCode(HttpStatus.SC_OK);
	}
}
