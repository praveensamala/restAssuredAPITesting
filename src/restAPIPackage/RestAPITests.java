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

public class RestAPITests extends BaseClass {
	
	// This test case will just call the GET service and print the response
	@Test(enabled = false)
	public void sampleTest01() throws URISyntaxException, MalformedURLException {
		/*
		 * Response resp = get("http://services.groupkt.com/country/get/all");
		 * System.out.println("resp : "+resp.getStatusCode()); 
		 * String json = resp.asString(); JsonPath jsonpath = new JsonPath (json); 
		 * System.out.println("jsonpath : "+jsonpath.get("ResResponse").toString());
		 */
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
	@Test (enabled = false)
	public void sampleTest05() throws URISyntaxException, MalformedURLException {
		RestAssured.given().accept(ContentType.JSON).when().get(new URL("http://services.groupkt.com/country/get/iso2code/IN")).then().assertThat().statusCode(HttpStatus.SC_OK);
	}
	
	//This test case will fetch the body data in string format
	@Test (enabled = false)
	public void sampleTest06() throws URISyntaxException, MalformedURLException {
		String response = RestAssured.given().accept(ContentType.JSON).when().get("http://services.groupkt.com/country/get/iso2code/IN").thenReturn().body().asString();
		System.out.println("response : "+response);
	}
	
	//This test is to use the predefined environment variables of basepath, baseuri and port to call the service
	@Test (enabled = false)
	public void sampleTest07() throws URISyntaxException, MalformedURLException {
		//BaseClass.setup();
		System.out.println("baseURI : "+baseURI);
		RestAssured.given().accept(ContentType.JSON).when().get(new URI("/IN")).then().assertThat().statusCode(HttpStatus.SC_OK);
	}
	
	//This test is to use custom headers while calling the service
	@Test (enabled = false)
	public void sampleTest08() throws URISyntaxException, MalformedURLException {
		Map<String, String> headerslist = new HashMap<String, String>();
		headerslist.put("Accept", "Application/JSON");
		RestAssured.given().headers(headerslist).when().get(new URI("http://services.groupkt.com/country/get/iso2code/IN")).then().assertThat().statusCode(HttpStatus.SC_OK);
	}
	
	@Test (enabled = true)
	public void sampleTest09() throws URISyntaxException, MalformedURLException {
		boolean loopcall = true;
		while (loopcall) {
			System.out.println("wait, calling api... ");
			Response response = RestAssured.given().accept(ContentType.JSON).when().get(new URI("http://dummy.restapiexample.com/api/v1/employees"));
			if (response.asString().toLowerCase().contains("too many requests") || response.asString().toLowerCase().contains("too many attempts")) {
				
			}
			else {
				System.out.println("response : "+response.asString());
				JsonPath json = new JsonPath(response.asString());
				
				System.out.println("status    : "+json.getString("status"));
				System.out.println("id        : "+json.getInt("data[0].id"));
				System.out.println("list      : "+json.getString("data[0].employee_name"));
				
				String data = json.getString("data");
				System.out.println("data list : "+json.getString("data"));
				
				//below is to get the json object list as object
				List<Object> list = json.getList("data");
				System.out.println("\nlist size : "+list.size());
				for (Object text:list) {
					System.out.println("text : "+text.toString());
					JsonPath json2 = new JsonPath(text.toString());
					System.out.println("employee_name : "+json2.getString("employee_name"));
				}
				
				Assert.assertEquals("success", json.getString("status"));
				Assert.assertEquals(1, json.getInt("data[0].id"));
				loopcall = false;
			}
		}
	}
}

