package utility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class utility {
    //Variables used across the project
    public static String path;
    public static String jsonPathTerm;
    public static String api_key;
    public static String language;
    public static int movie_id;

    //baseURI defining for any API's base URI
    public static void setBaseURI (){
        RestAssured.baseURI = "https://api.themoviedb.org/3/";
    }

    //Set the base path for API
    public static void setBasePath(String basePathTerm){
        RestAssured.basePath = basePathTerm;
    }
    
  //Set Movie id
    public static void setMovieId(int movieId){
        movie_id = movieId;
    }
    
  //Set the API Key
    public static void setAPIKey(String apiKey){
        api_key = apiKey;
    }

    //Clear the base URI after usage for an API.
    public static void resetBaseURI (){
        RestAssured.baseURI = null;
    }

    //Clear basePath after usage for an API.
    public static void resetBasePath(){
        RestAssured.basePath = null;
    }

    //Provide the ContentType
    public static void setContentType (ContentType Type){
        given().contentType(Type);
    }

    //Provide the Json path
    public static void  setJsonPathTerm(String jsonPath){
        jsonPathTerm = jsonPath;
    }

    //construct the path
    //eg. https://api.themoviedb.org/3/movie/299537?api_key={api_key}
    //In path baseURI and basePath is considered prefix.
    public static void  constructPathForDetails(String apiKey) {
        path = "https://api.themoviedb.org/3/movie/299537" + "?" + api_key + "=" + apiKey;
    }
    //eg. https://api.themoviedb.org/3/movie/popular?api_key=aaa&language=en-US&page=1
    public static void  constructPathForPopular(String apiKey, String lang) {
        path = "https://api.themoviedb.org/3/movie/popular" + "?" + api_key + "=" + apiKey + "&" + language + "=" + lang;
    }

    //Return the response data for API
    public static Response getResponse(String path) {
        System.out.print("Response path: " + path +"\n");
        return get(path);
    }
    
  //Returns response by given path
    public static Response getResponsebyPath(String path) {
        return get(path);
    }    

    //Return JsonPath object for API
    public static JsonPath getJsonPath (Response res) {
        String json = res.asString();
        System.out.print("Returned json object: " + json +"\n");
        return new JsonPath(json);
    }
}
