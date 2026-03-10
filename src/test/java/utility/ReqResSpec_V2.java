package utility;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import authmanager.TokenGenerator;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ReqResSpec_V2 {

	@Step
	public static RequestSpecification reqSpec() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		return new RequestSpecBuilder()
				.addHeader("Content-Type", "application/json")
				.addHeader("Authorization",
				"Bearer "+TokenGenerator.getToken())
				.setBaseUri("https://api.spotify.com")
				.setBasePath("/v1")
				.addFilter(new AllureRestAssured())
				.log(LogDetail.ALL)
				.build();

	}

	@Step
	public static ResponseSpecification resSpec(int stsCode) {
		return new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.log(LogDetail.ALL)
				.expectStatusCode(stsCode)
				.build();

	}

}
