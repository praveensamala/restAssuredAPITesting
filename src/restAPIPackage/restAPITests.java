package restAPIPackage;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.*;
import org.testng.annotations.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class restAPITests
{
	@Test
	public void sampleTest() {
		/*Response resp = get("http://services.groupkt.com/country/get/all");
		System.out.println("resp : "+resp.getStatusCode());
		
		String json = resp.asString();
		JsonPath jsonpath = new JsonPath (json);
		
		System.out.println("jsonpath : "+jsonpath.get("ResResponse").toString());*/
		
		given().get("http://services.groupkt.com/country/get/iso2code/IN").then().statusCode(200).log().all();
	}
}
