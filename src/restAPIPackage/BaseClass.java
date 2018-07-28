package restAPIPackage;

import org.junit.BeforeClass;
import static com.jayway.restassured.RestAssured.*;

public class BaseClass {
	@BeforeClass
	public static void setup() {
		System.out.println("in beforeclass");
		baseURI = "http://services.groupkt.com";
		basePath = "/country/get/iso2code";
	}
}
