package steps;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import static io.restassured.RestAssured.*;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import static org.hamcrest.Matchers.*;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.ReqResSpec_V2;



public class SearchSteps {
	RequestSpecification reqs;
	Response response;
	String songNameValue;
	@Given("Get a search song payload")
	public void get_a_search_song_payload(DataTable dataTable) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
	{
		List<Map<String, String>> data = dataTable.asMaps();
		
		Map<String, String> firstLine = data.get(0);
		
		 songNameValue = firstLine.get("songname");
		
		String typeValue  = firstLine.get("type");
		
		String artistValue = firstLine.get("artist");
		
		
	reqs = 	given(ReqResSpec_V2.reqSpec())
		
		.queryParams("q", songNameValue, "type", typeValue, "artist", artistValue);
		
	}
	
	@When("user calls with GET request")
	public void user_calls_with_GET_request()
	{
		 response = reqs.when()
		.get("search");
	}
	
	@Then("API executes with status code as {int}")
	public void api_executes_with_status_code_as(Integer int1)
	{
		response.then()
		.spec(ReqResSpec_V2.resSpec(int1))
		.assertThat()
		.body(containsString(songNameValue));
		
		
		
		
	}

}
