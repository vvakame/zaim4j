package net.vvakame.zaim4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

/**
 * Test for OAuth 1.0a implementation.
 * @author vvakame
 */
public class SampleTest {

	static String CONSUMER_KEY = "13a6679e63189570c8a562ad9cc9511f17f363bb";

	static String CONSUMER_SECRET = "c77921594a416df396d43fd37c638bbf4f520324";

	static String REQUEST_TOKEN_ENDPOINT = "https://api.zaim.net/v2/auth/request";

	static String AUTHORIZE_ENDPOINT = "https://www.zaim.net/users/auth";

	static final Random rand = new Random();

	static String NONCE_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";


	/**
	 * Get RequestToken.
	 * @throws IOException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @author vvakame
	 */
	@Test
	public void getRequestToken() throws IOException, InvalidKeyException, NoSuchAlgorithmException {
		Map<String, String> params = new TreeMap<String, String>();
		{
			params.put("oauth_version", "1.0");
			params.put("oauth_nonce", generateNonce());
			params.put("oauth_timestamp", String.valueOf(System.currentTimeMillis() / 1000));
			params.put("oauth_consumer_key", CONSUMER_KEY);
			params.put("oauth_callback", "http://localhost:8888/callback"); // "oob");
			params.put("oauth_signature_method", "HMAC-SHA1");
			params
				.put("oauth_signature", generateSignature("POST", REQUEST_TOKEN_ENDPOINT, params));
		}
		String authHeader = generateAuthorizationHeader(params);

		URL url = new URL(REQUEST_TOKEN_ENDPOINT);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
		connection.setRequestProperty("Authorization", authHeader);

		String str = connectionToString(connection);
		System.out.println(str);
	}

	static HttpURLConnection doGet(String urlStr, Map<String, String> params) throws IOException {
		StringBuilder builder = new StringBuilder(urlStr);
		builder.append("?").append(toParameterString(params));
		URL url = new URL(builder.toString());
		return (HttpURLConnection) url.openConnection();
	}

	static HttpURLConnection doPost(String urlStr, Map<String, String> params) throws IOException {
		byte[] body = toParameterString(params).getBytes();

		URL url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(body);
		outputStream.flush();
		outputStream.close();

		return connection;
	}

	static String generateAuthorizationHeader(Map<String, String> params)
			throws UnsupportedEncodingException {
		StringBuilder builder = new StringBuilder();
		for (Entry<String, String> param : params.entrySet()) {
			builder.append(param.getKey()).append("=\"").append(urlEncode(param.getValue()))
				.append("\"").append(", ");
		}
		builder.setLength(builder.length() - 2);
		return "OAuth " + builder.toString();
	}

	static String generateSignature(String method, String url, Map<String, String> params)
			throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
		String text = generateSignatureBase(method, url, params);
		String key = generateSignatureKey(CONSUMER_SECRET, "");
		return generateSignatureString(key, text);
	}

	static String toParameterString(Map<String, String> params) throws UnsupportedEncodingException {
		StringBuilder builder = new StringBuilder();
		for (String key : params.keySet()) {
			builder.append(key).append("=").append(urlEncode(params.get(key))).append("&");
		}
		if (builder.length() != 0) {
			builder.setLength(builder.length() - 1);
		}
		return builder.toString();
	}

	static String generateSignatureBase(String method, String url, Map<String, String> params)
			throws UnsupportedEncodingException {
		return method + "&" + urlEncode(url) + "&" + urlEncode(toParameterString(params));
	}

	static String urlEncode(String string) throws UnsupportedEncodingException {
		return URLEncoder.encode(string, "UTF-8");
	}

	static String generateSignatureKey(String consumerSecret, String oauthTokenSecret)
			throws UnsupportedEncodingException {
		return urlEncode(consumerSecret) + "&" + urlEncode(oauthTokenSecret);
	}

	static String generateSignatureString(String key, String text) throws NoSuchAlgorithmException,
			InvalidKeyException {
		SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
		Mac mac = Mac.getInstance(signingKey.getAlgorithm());
		mac.init(signingKey);
		byte[] rawHmac = mac.doFinal(text.getBytes());
		return Base64.encode(rawHmac);
	}

	static String generateNonce() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 32; i++) {
			char c = NONCE_CHARS.charAt(rand.nextInt(NONCE_CHARS.length()));
			builder.append(c);
		}
		return builder.toString();
	}

	static String connectionToString(HttpURLConnection connection) throws IOException {
		if (connection.getResponseCode() < 400) {
			return streamToString(connection.getInputStream());
		} else {
			String str = streamToString(connection.getErrorStream());
			throw new RuntimeException(str);
		}
	}

	static String streamToString(InputStream is) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder builder = new StringBuilder();

		String str;
		while ((str = br.readLine()) != null) {
			builder.append(str).append("\n");
		}
		br.close();

		return builder.toString();
	}
}
