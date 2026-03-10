package authmanager;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utility.CryptoUtils;
import utility.PropertyReader;

public class TokenGenerator {
	static Instant expiry_time;
	
	static String accessToken;
	
	public static String getToken() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
	{
		// to verify if we really need to create the token or we need to reuse the same
		
		if(accessToken == null || Instant.now().isAfter(expiry_time))
		{
		Response resp = renewToken();
		
		JsonPath jp = resp.jsonPath();
		
		accessToken	= jp.getString("access_token");
		
		int expiryTimeInSeconds = jp.getInt("expires_in");
		
		expiry_time= Instant.now().plusSeconds(expiryTimeInSeconds-300);
		
		
		}
		
		else
		{
			System.out.println("Token is good to use");
		}
		
		return accessToken;
		
	}
	
	
	
	public static Response renewToken() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
	{
//		to generate the access token which is used inside the api to perform the action
	String	enCrypedRefreshToken = PropertyReader.readPropData("en_refresh_token");
	String enCryptedClientId =	PropertyReader.readPropData("en_client_id");
	String enCryptedClientSecret = 	PropertyReader.readPropData("en_client_secret");
		
	
	String refreshToken = CryptoUtils.decryptData(enCrypedRefreshToken);
	
	String clientId = CryptoUtils.decryptData(enCryptedClientId);
	
	String clientSecret = CryptoUtils.decryptData(enCryptedClientSecret);
	
		
		HashMap<String, String> param = new HashMap<String, String>();
		
		param.put("grant_type", "refresh_token");
		param.put("refresh_token", refreshToken);
		param.put("client_id", clientId);
		param.put("client_secret", clientSecret);	
		
		baseURI = "https://accounts.spotify.com";
		
		
Response resp = given()
		
		.header("Content-Type", "application/x-www-form-urlencoded")
		
		.formParams(param)
			
		.log().all()
		
		.when()
		
		.post("/api/token")
		
		.then()
		
		.log().all()
		
		.extract()
		
		.response();

		if(resp.statusCode()!=200)
		{
			throw new RuntimeException("Token generation API call failed!!!!!");
		}

		return resp;
		
		
	}
	
	public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		renewToken();
	}
	


}
