package tmdb.movie.test;

import utility.utility;
import utility.HelperMethods;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.*;
import org.junit.runners.MethodSorters;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) //Ascending order test execution
public class GetDetailsTest {

	private Response res = null; //Response
    private JsonPath jp = null; //JsonPath
    
  //Instantiate a Helper Test Methods (hm) Object
    HelperMethods hm = new HelperMethods();
    
    @Before
    public void setup (){
        //Test Setup
        utility.setBaseURI(); //Setup Base URI
        utility.setBasePath("movie"); //Setup Base Path
        utility.setContentType(ContentType.JSON); //Setup Content Type
        utility.setMovieId(299537); //captain-marvel movie id        
        utility.setAPIKey("api_key");   
        //eg. https://api.themoviedb.org/3/movie/299537?api_key={aaa}&language=en-US
        //utility.path = "/299537?api_key={aaa}&language=en-US";
        utility.constructPathForDetails("place_holder_for_api_key"); //Construct the path
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
    	//compare the output for the name of the movie i.e Captain Marvel in response object
    	Assert.assertEquals(bodyAsString.contains("Captain Marvel"), true);
    	//It can also be search for lower case after conversion, to avoid any case sensitive usecases.
    	//Assert.assertEquals(bodyAsString.toLowerCase().contains("captain marvel"), true);
    }
   

	@After
    public void afterTest (){
        //Reset Values
        utility.resetBaseURI();
        utility.resetBasePath();
    }
    

}

