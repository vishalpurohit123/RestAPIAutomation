package utility;

import io.restassured.response.Response;
import static org.junit.Assert.assertEquals;

public class HelperMethods {
	
	public void checkStatusIs200 (Response res) {
		int statusCode = res.getStatusCode();
		assertEquals("Correct status code returned", 200, statusCode);		 
    }   
	
	public void checkStatusLine (Response res) {
        String statusLine = res.getStatusLine();
        assertEquals("Correct status Line returned", "HTTP/1.1 200 OK", statusLine);
        
    } 
    

}
