package net.vvakame.zaim4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import net.vvakame.util.jsonpullparser.JsonFormatException;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Test for {@link OAuthConnector}.
 * @author vvakame
 */
public class OAuthConnectorTest {

	/**
	 * Test for {@link OAuthConnector#getRequestToken()} and {@link OAuthConnector#getAccessToken(String)}.
	 * @throws IOException
	 * @author vvakame
	 */
	@Test
	@Ignore("required manually operation")
	public void getAccessToken() throws IOException {
		OAuthConfiguration configuration =
				OAuthConfiguration.Builder.fromProperties("/zaim-oauth.properties").build();

		OAuthCredential credential = null;
		OAuthConnector connector = new OAuthConnector(configuration, credential);
		credential = connector.getRequestToken();
		System.out.println(credential.getAuthUrl());
		String oauthVerifier = new BufferedReader(new InputStreamReader(System.in)).readLine();
		credential = connector.getAccessToken(oauthVerifier);
		System.out.println(credential.toJson());

		{
			Map<String, String> params = new HashMap<String, String>();
			params.put("category_id", "113");
			HttpURLConnection connection = connector.doGet("/v2/home/money", params);
			System.out.println(OAuthConnector.connectionToString(connection));
		}
	}

	/**
	 * Test for use {@link OAuthConnector#exec(net.vvakame.zaim4j.OAuthConnector.ProcessType, String, String, Map, Map)}.
	 * @throws IOException
	 * @author vvakame
	 * @throws JsonFormatException 
	 */
	@Test
	@Ignore("zaim-oauth.properties and valid-oauthToken.json are not committed")
	public void callAPI() throws IOException, JsonFormatException {
		OAuthConfiguration configuration =
				OAuthConfiguration.Builder.fromProperties("/zaim-oauth.properties").build();

		InputStream is = OAuthConnectorTest.class.getResourceAsStream("/valid-oauthToken.json");
		String json = OAuthConnector.streamToString(is);
		OAuthCredential credential = OAuthCredential.Builder.newBuild(configuration, json).build();
		OAuthConnector connector = new OAuthConnector(configuration, credential);

		{
			Map<String, String> params = new HashMap<String, String>();
			params.put("mode", "payment");
			HttpURLConnection connection = connector.doGet("/v2/home/category", params);
			System.out.println(OAuthConnector.connectionToString(connection));
		}
		{
			Map<String, String> params = new HashMap<String, String>();
			params.put("mode", "payment");
			HttpURLConnection connection = connector.doGet("/v2/home/genre", params);
			System.out.println(OAuthConnector.connectionToString(connection));
		}
		{
			HttpURLConnection connection = connector.doGet("/v2/home/account", null);
			System.out.println(OAuthConnector.connectionToString(connection));
		}
		{
			HttpURLConnection connection = connector.doGet("/v2/account", null);
			System.out.println("other account " + OAuthConnector.connectionToString(connection));
		}
	}
}
