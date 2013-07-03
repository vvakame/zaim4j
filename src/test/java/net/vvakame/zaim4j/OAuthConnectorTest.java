package net.vvakame.zaim4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

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
	public void test() throws IOException {
		OAuthConfiguration configuration =
				OAuthConfiguration.Builder.fromProperties("/zaim-oauth.properties").build();

		OAuthCredential credential = null;
		OAuthConnector connector = new OAuthConnector(configuration, credential);
		credential = connector.getRequestToken();
		System.out.println(credential.getAuthUrl());
		String oauthVerifier = new BufferedReader(new InputStreamReader(System.in)).readLine();
		credential = connector.getAccessToken(oauthVerifier);

		{
			Map<String, String> params = new HashMap<String, String>();
			params.put("category_id", "113");
			HttpURLConnection connection =
					connector.doGet("https://api.zaim.net/v2/home/money", params);
			System.out.println(OAuthConnector.connectionToString(connection));
		}
	}

	/**
	 * Test for use {@link OAuthConnector#exec(net.vvakame.zaim4j.OAuthConnector.ProcessType, String, String, Map, Map)}.
	 * @throws IOException
	 * @author vvakame
	 */
	@Test
	@Ignore("zaim-oauth.properties is not committed")
	public void callAPI() throws IOException {
		OAuthConfiguration configuration =
				OAuthConfiguration.Builder.fromProperties("/zaim-oauth.properties").build();

		OAuthCredential credential = OAuthCredential.Builder.newBuild(configuration).build();
		credential.oauthToken =
				"1rQil5UlpfJpT4lmAseJMywDDMdGeav0fpe3TI1Ki4uMLWa6Ts0rhvGIOdozAB3qvhMEmWBAks";
		credential.oauthTokenSecret =
				"GJX68AiJYyaGKFX74EMrorz2MTbTndhwj9mUKyHhVTNdtFehINXjLOGaQIeGJpfLehIK1Y";
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
	}
}
