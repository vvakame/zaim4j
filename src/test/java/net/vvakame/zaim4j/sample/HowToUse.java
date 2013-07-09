package net.vvakame.zaim4j.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.vvakame.util.jsonpullparser.JsonFormatException;
import net.vvakame.zaim4j.ErrorResponse;
import net.vvakame.zaim4j.MoneyListArgument;
import net.vvakame.zaim4j.MoneyListResponse;
import net.vvakame.zaim4j.MoneyMode;
import net.vvakame.zaim4j.MoneyPaymentInsertArgument;
import net.vvakame.zaim4j.MoneyPostInsertResponse;
import net.vvakame.zaim4j.OAuthConfiguration;
import net.vvakame.zaim4j.OAuthConfiguration.Builder;
import net.vvakame.zaim4j.OAuthCredential;
import net.vvakame.zaim4j.OtherAccountListResponse;
import net.vvakame.zaim4j.UserVerifyResponse;
import net.vvakame.zaim4j.Zaim;
import net.vvakame.zaim4j.Zaim.ZaimListener;
import net.vvakame.zaim4j.ZaimResult;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * How to use Zaim4J.
 * @author vvakame
 */
@Ignore("Please set your ConsumerKey and ConsumerSecret, and others...")
public class HowToUse {

	static final String CONSUMER_KEY = "XXXX";

	static final String CONSUMER_SECRET = "YYYY";

	static final String CALLBACK_URL = "http://localhost:8888/callback";

	static final String OAUTH_TOKEN_JSON =
			"{\"oauthToken\":\"WWWW\",\"oauthTokenSecret\":\"ZZZZ\",\"requestToken\":null,\"requestTokenSecret\":null}";


	/**
	 * Step1: get AccessToken!
	 * @throws IOException
	 * @author vvakame
	 */
	@Test
	public void step1_getAccessToken() throws IOException {
		// setup OAuthConfiguration
		Builder builder = OAuthConfiguration.Builder.newBuilder();
		builder.setConsumerKey(CONSUMER_KEY);
		builder.setConsumerSecret(CONSUMER_SECRET);
		builder.setCallbackUrl(CALLBACK_URL);
		OAuthConfiguration configuration = builder.build();

		OAuthCredential credential;
		// get RequestToken
		credential = Zaim.getRequestToken(configuration);
		// autorization url for User, please open by browser.
		String authUrl = credential.getAuthUrl();
		System.out.println("open: " + authUrl);
		System.out.print("input oauthVerifier: ");
		String oauthVerifier = new BufferedReader(new InputStreamReader(System.in)).readLine();

		// get AccessToken
		credential = Zaim.getAccessToken(configuration, credential, oauthVerifier);

		// Mission completed!
		System.out.println(credential.toJson()); // → OAUTH_TOKEN_JSON
		System.out.println(credential.getOauthToken());
		System.out.println(credential.getOauthTokenSecret());
	}

	/**
	 * Step2: use Zaim API!
	 * @throws IOException
	 * @throws JsonFormatException 
	 * @author vvakame
	 * @throws IllegalAccessException 
	 */
	@Test
	public void step2_use_ZaimAPI_by_result() throws IOException, JsonFormatException,
			IllegalAccessException {
		// setup OAuthConfiguration & OAuthCredential
		Builder builder = OAuthConfiguration.Builder.newBuilder();
		builder.setConsumerKey(CONSUMER_KEY);
		builder.setConsumerSecret(CONSUMER_SECRET);
		builder.setCallbackUrl(CALLBACK_URL);
		OAuthConfiguration configuration = builder.build();

		OAuthCredential credential =
				OAuthCredential.Builder.newBuild(configuration, OAUTH_TOKEN_JSON).build();

		// get Zaim instance.
		Zaim zaim = Zaim.newInstance(credential);

		// call API, it not required authorization.
		// get https://api.zaim.net/v2/account
		{
			ZaimResult<OtherAccountListResponse> result = zaim.other().account().list().execute();
			if (result.isSuccess()) {
				System.out.println(result.getValue());
			} else {
				fail();
			}
		}

		// and others.
		// https://api.zaim.net/v2/category	zaim.other().category().list()
		// https://api.zaim.net/v2/genre	zaim.other().genre().list()
		// https://api.zaim.net/v2/currency	zaim.other().currency().list()

		// call API, it required authorization.
		// get https://api.zaim.net/v2/home/user/verify
		{
			ZaimResult<UserVerifyResponse> result = zaim.user().verify().execute();
			if (result.isSuccess()) {
				System.out.println(result.getValue());
			} else {
				fail();
			}
		}

		// and others.
		// /v2/home/money ..., /v2/home/category, /v2/home/genre, /v2/home/account
		// for example.
		{
			MoneyListArgument argument = new MoneyListArgument();
			argument.setMode(MoneyMode.Payment);
			ZaimResult<MoneyListResponse> result = zaim.money().list(argument).execute();
			if (result.isSuccess()) {
				System.out.println(result.getValue());
			} else {
				fail();
			}
		}

		// If API required some parametes that argument class has constructor with arguments.
		// see original document. https://dev.zaim.net/home/api?url=%2Fhome%2Fapi#money_get
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			MoneyPaymentInsertArgument argument =
					new MoneyPaymentInsertArgument(101, 10103, 888, sdf.format(new Date()));
			ZaimResult<MoneyPostInsertResponse> result =
					zaim.money().payment().insert(argument).execute();
			if (result.isSuccess()) {
				System.out.println(result.getValue());
			} else {
				fail();
			}
		}
	}

	/**
	 * Step2: use Zaim API!
	 * @throws IOException
	 * @throws JsonFormatException 
	 * @author vvakame
	 */
	@Test
	public void step2_use_ZaimAPI_by_listener() throws IOException, JsonFormatException {
		// setup OAuthConfiguration & OAuthCredential
		Builder builder = OAuthConfiguration.Builder.newBuilder();
		builder.setConsumerKey(CONSUMER_KEY);
		builder.setConsumerSecret(CONSUMER_SECRET);
		builder.setCallbackUrl(CALLBACK_URL);
		OAuthConfiguration configuration = builder.build();

		OAuthCredential credential =
				OAuthCredential.Builder.newBuild(configuration, OAUTH_TOKEN_JSON).build();

		// get Zaim instance.
		Zaim zaim = Zaim.newInstance(credential);

		// call API, it not required authorization.
		// get https://api.zaim.net/v2/account
		zaim.other().account().list().execute(new ZaimListener<OtherAccountListResponse>() {

			@Override
			public void onSuccess(OtherAccountListResponse success) {
				System.out.println(success.toString());
			}

			@Override
			public void onFailure(ErrorResponse failure) {
				fail();
			}

			@Override
			public void onError(Exception e) {
				fail();
			}
		});

		// and others.
		// https://api.zaim.net/v2/category	zaim.other().category().list()
		// https://api.zaim.net/v2/genre	zaim.other().genre().list()
		// https://api.zaim.net/v2/currency	zaim.other().currency().list()

		// call API, it required authorization.
		// get https://api.zaim.net/v2/home/user/verify
		zaim.user().verify().execute(new ZaimListener<UserVerifyResponse>() {

			@Override
			public void onSuccess(UserVerifyResponse success) {
				System.out.println(success.toString());
			}

			@Override
			public void onFailure(ErrorResponse failure) {
				fail();
			}

			@Override
			public void onError(Exception e) {
				fail();
			}
		});

		// and others.
		// /v2/home/money ..., /v2/home/category, /v2/home/genre, /v2/home/account
		// for example.
		{
			MoneyListArgument argument = new MoneyListArgument();
			argument.setMode(MoneyMode.Payment);
			zaim.money().list(argument).execute(new ZaimListener<MoneyListResponse>() {

				@Override
				public void onSuccess(MoneyListResponse success) {
					System.out.println(success.toString());
				}

				@Override
				public void onFailure(ErrorResponse failure) {
					fail();
				}

				@Override
				public void onError(Exception e) {
					fail();
				}
			});
		}

		// If API required some parametes that argument class has constructor with arguments.
		// see original document. https://dev.zaim.net/home/api?url=%2Fhome%2Fapi#money_get
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			MoneyPaymentInsertArgument argument =
					new MoneyPaymentInsertArgument(101, 10103, 888, sdf.format(new Date()));
			zaim.money().payment().insert(argument)
				.execute(new ZaimListener<MoneyPostInsertResponse>() {

					@Override
					public void onSuccess(MoneyPostInsertResponse success) {
						System.out.println(success.toString());
					}

					@Override
					public void onFailure(ErrorResponse failure) {
						fail();
					}

					@Override
					public void onError(Exception e) {
						fail();
					}
				});
		}
	}
}
