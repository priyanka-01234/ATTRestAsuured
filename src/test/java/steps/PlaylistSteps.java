package steps;

import com.spotify.pojo.Playlist;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.DateAndTimeProvider;
import utility.ReqResSpec_V2;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.hamcrest.Matchers;

public class PlaylistSteps {
	
	RequestSpecification reqs;
	static String playlistId;
	Response res;
	@Given("create playlist api payload")
	public void create_playlist_api_payload() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
//	body of api request   
		Playlist reqPlaylist = new Playlist();
		
		reqPlaylist.setName("Feb 18 Playlist-2");
		reqPlaylist.setDescription("This is a sample playlist");
		reqPlaylist.setPublic(false);
// pre-requisite using spec-builders
		
		 reqs = given(ReqResSpec_V2.reqSpec())
		
		.body(reqPlaylist);
		
		
		
	}
	@When("user calls with POST http request for create playlist")
	public void user_calls_with_post_http_request_for_create_playlist() {
		 res = reqs.when()
		.post("me/playlists");
	}
	
	
	@Then("API call executed with status code {int}")
	public void api_call_executed_with_status_code(Integer int1) {
		Playlist playlistObject = res.as(Playlist.class);
		
		String nameOfPlaylist = playlistObject.getName();
		
		
		res.then()
		
		.body("name", Matchers.equalTo(nameOfPlaylist))
		
		.spec(ReqResSpec_V2.resSpec(int1))
		
		.extract()
		
		.response();
		
	 playlistId = playlistObject.getId();

	}

	@Given("GET playlist api payload")
	public void get_playlist_api_payload() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
	 reqs=  given(ReqResSpec_V2.reqSpec())
	   .pathParam("pId", playlistId);
	   
	   
	}
	@When("user calls with GET http request")
	public void user_calls_with_get_http_request() {
	  res = reqs.when()
	   
	   .get("playlists/{pId}");
	}
	@Then("API call executes with status code {int}")
	public void api_call_executes_with_status_code(Integer int1) {
	   
		res.then()
		
		.spec(ReqResSpec_V2.resSpec(int1));
	}

	
	@Given("update playlist api payload")
	public void update_playlist_api_payload() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		Playlist reqPlaylist = new Playlist();
		
		reqPlaylist.setName("Feb 18 Playlist-2-Updated on "+DateAndTimeProvider.getCurrentDateAndTime());
		reqPlaylist.setDescription("This is a sample playlist updated");
		reqPlaylist.setPublic(false);
		
reqs	=	given(ReqResSpec_V2.reqSpec())
		
		.body(reqPlaylist)
		.pathParam("putPid", playlistId);
	}
	@When("user calls with PUT http request")
	public void user_calls_with_put_http_request() {
	
res	=	reqs.when()
		
		.put("playlists/{putPid}");
		
		
	}
	@Then("API call should executes with status code {int}")
	public void api_call_should_executes_with_status_code(Integer int1) {
	  		
		res.then()
		
		.statusCode(int1);
		
	}

}
