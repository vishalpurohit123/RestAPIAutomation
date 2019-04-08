package tmdb.movie.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import utility.HelperMethods;
import utility.utility;

public class GetPopularTest {
	private Response res = null; //Response
    private JsonPath jp = null; //JsonPath
    
  //Instantiate a Helper Test Methods (htm) Object
    HelperMethods hm = new HelperMethods();
    
    @Before
    public void setup (){
        //Test Setup
        utility.setBaseURI(); //Setup Base URI
        utility.setBasePath("movie/popular"); //Setup Base Path
        utility.setContentType(ContentType.JSON); //Setup Content Type
        utility.setAPIKey("api_key"); 
        //eg. https://api.themoviedb.org/3/movie/popular?api_key={aaa}&language=en-US&page=1
        //utility.path = "/?api_key={aaa}&language=en-US&page=1";
        utility.constructPathForPopular("place_holder_for_api_key", "en-US"); //Construct the path
        res = utility.getResponse(utility.path); //Get response
        jp = utility.getJsonPath(res); //Set JsonPath
    }

    @Test
    public void UC1_StatusCodeTest() {
        //Verify the https response status returned. Check Status Code is 200
        hm.checkStatusIs200(res);
    }
    
    @Test
    public void UC2_StatusLineTest() {
        //Verify the status line content of API response.
    	hm.checkStatusLine(res);    
    }

    @Test
    public void UC3_headersTest() {
        //verify response headers content, print all response headers (if any)
    	Headers allHeaders = res.headers();
    	
    	//validate content type assertion
    	String contentType = res.header("Content-Type");
    	 System.out.println("Content-Type value: " + contentType);
    	 Assert.assertEquals(contentType, "application/json;charset=utf-8");
    	 
    	 // Iterate over all the response Headers
    	 for(Header header : allHeaders)
    	 {
    	 System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
    	 }
    }

    @Test
    public void UC4_printResponseBody() {
    	// Retrieve the body of the Response
    	 ResponseBody body = res.getBody();
    	System.out.println("Response Body is =>  " + res.asString());
    }
    
    @Test
    public void UC5_searchContentInResponseBody() {
    //Validate Response Body contains some specific data.
    	// Retrieve the body of the Response
    	ResponseBody body = res.getBody();

    	// To check for sub string presence get the Response body as a String.
    	// Do a String.contains
    	String bodyAsString = body.asString();
    	// convert the body into lower case and then do a comparison to ignore casing.
    	Assert.assertEquals(bodyAsString.toLowerCase().contains("total_results"), true);   
    }

    @After
    public void afterTest (){
        //Reset Values
        utility.resetBaseURI();
        utility.resetBasePath();
    }


}
