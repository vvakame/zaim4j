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
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

class OAuthConnector {

	static final Random rand = new Random();

	static String NONCE_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	final OAuthConfiguration configuration;

	final OAuthCredential credential;


	enum ProcessType {
		RequestToken, AccessToken, ApiAccess
	}


	OAuthConnector(OAuthConfiguration configuration, OAuthCredential credential) {
		this.configuration = configuration;
		if (credential == null) {
			this.credential = OAuthCredential.Builder.newBuild(configuration).build();
		} else {
			this.credential = credential;
		}
	}

	public HttpURLConnection exec(ProcessType type, String method, String urlStr,
			Map<String, String> authParams, Map<String, String> apiParams) {

		SortedMap<String, String> params = new TreeMap<String, String>();
		if (authParams != null && authParams.size() != 0) {
			params.putAll(authParams);
		}
		String parameterizedUrl = urlStr;
		if (apiParams != null && apiParams.size() != 0) {
			params.putAll(apiParams);
			if ("GET".equalsIgnoreCase(method)) {
				String paramStr = toParameterString(apiParams);
				parameterizedUrl = urlStr + "?" + paramStr;
			}
		}
		try {
			params.put("oauth_version", "1.0");
			params.put("oauth_nonce", generateNonce());
			params.put("oauth_timestamp", String.valueOf(System.currentTimeMillis() / 1000));
			params.put("oauth_consumer_key", configuration.getConsumerKey());
			params.put("oauth_signature_method", "HMAC-SHA1");
			params.put("oauth_signature", generateSignature(type, method, urlStr, params));

			String authHeader = generateAuthorizationHeader(params);

			URL url = new URL(parameterizedUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Authorization", authHeader);

			if (!"GET".equalsIgnoreCase(method) && apiParams != null && apiParams.size() != 0) {
				connection.setDoOutput(true);
				OutputStream os = connection.getOutputStream();
				String paramStr = toParameterString(apiParams);
				os.write(paramStr.getBytes());
				os.flush();
				os.close();
			}

			return connection;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public HttpURLConnection doGet(String url, Map<String, String> params) {
		Map<String, String> authParams = new HashMap<String, String>();
		authParams.put("oauth_token", credential.getOauthToken());
		if (url.startsWith("/")) {
			url = configuration.getBaseUrl() + url.substring(1);
		} else {
			url = configuration.getBaseUrl() + url;
		}
		return exec(ProcessType.ApiAccess, "GET", url, authParams, params);
	}

	public HttpURLConnection doPost(String url, Map<String, String> params) {
		Map<String, String> authParams = new HashMap<String, String>();
		authParams.put("oauth_token", credential.getOauthToken());
		if (url.startsWith("/")) {
			url = configuration.getBaseUrl() + url.substring(1);
		} else {
			url = configuration.getBaseUrl() + url;
		}
		return exec(ProcessType.ApiAccess, "POST", url, authParams, params);
	}

	public HttpURLConnection doPut(String url, Map<String, String> params) {
		Map<String, String> authParams = new HashMap<String, String>();
		authParams.put("oauth_token", credential.getOauthToken());
		if (url.startsWith("/")) {
			url = configuration.getBaseUrl() + url.substring(1);
		} else {
			url = configuration.getBaseUrl() + url;
		}
		return exec(ProcessType.ApiAccess, "PUT", url, authParams, params);
	}

	public HttpURLConnection doDelete(String url, Map<String, String> params) {
		Map<String, String> authParams = new HashMap<String, String>();
		authParams.put("oauth_token", credential.getOauthToken());
		if (url.startsWith("/")) {
			url = configuration.getBaseUrl() + url.substring(1);
		} else {
			url = configuration.getBaseUrl() + url;
		}
		return exec(ProcessType.ApiAccess, "DELETE", url, authParams, params);
	}

	public OAuthCredential getRequestToken() throws IOException {
		SortedMap<String, String> params = new TreeMap<String, String>();
		params.put("oauth_callback", configuration.getCallbackUrl());

		HttpURLConnection connection =
				exec(ProcessType.RequestToken, "POST", configuration.getRequestTokenUrl(), params,
						null);
		if (connection.getResponseCode() == 200) {
			String response = streamToString(connection.getInputStream());
			Map<String, String> responseMap = toParameterMap(response);
			credential.requestToken = responseMap.get("oauth_token");
			credential.requestTokenSecret = responseMap.get("oauth_token_secret");
			return credential;
		} else {
			throw new IOException(connection.getResponseCode() + ":"
					+ streamToString(connection.getErrorStream()));
		}
	}

	public OAuthCredential getAccessToken(String oauthVerifier) throws IOException {
		SortedMap<String, String> params = new TreeMap<String, String>();
		params.put("oauth_token", credential.getRequestToken());
		params.put("oauth_verifier", oauthVerifier);
		params.put("oauth_callback", configuration.getCallbackUrl());

		HttpURLConnection connection =
				exec(ProcessType.AccessToken, "POST", configuration.getAccessTokenUrl(), params,
						null);
		if (connection.getResponseCode() == 200) {
			String response = streamToString(connection.getInputStream());
			Map<String, String> responseMap = toParameterMap(response);
			credential.oauthToken = responseMap.get("oauth_token");
			credential.oauthTokenSecret = responseMap.get("oauth_token_secret");
			credential.requestToken = null;
			credential.requestTokenSecret = null;
			return credential;
		} else {
			throw new IOException(connection.getResponseCode() + ":"
					+ streamToString(connection.getErrorStream()));
		}
	}

	String generateAuthorizationHeader(Map<String, String> params) {
		StringBuilder builder = new StringBuilder();
		for (Entry<String, String> param : params.entrySet()) {
			builder.append(param.getKey()).append("=\"").append(urlEncode(param.getValue()))
				.append("\"").append(", ");
		}
		builder.setLength(builder.length() - 2);
		return "OAuth " + builder.toString();
	}

	String generateSignature(ProcessType type, String method, String url, Map<String, String> params)
			throws InvalidKeyException, NoSuchAlgorithmException {
		switch (type) {
			case RequestToken:
				return generateSignature("", method, url, params);
			case AccessToken:
				return generateSignature(credential.getRequestTokenSecret(), method, url, params);
			case ApiAccess:
				return generateSignature(credential.getOauthTokenSecret(), method, url, params);
			default:
				throw new IllegalStateException("unknown process type:" + type);
		}
	}

	String generateSignature(String oauthSecret, String method, String url,
			Map<String, String> params) throws InvalidKeyException, NoSuchAlgorithmException {
		String text = generateSignatureBase(method, url, params);
		String key = generateSignatureKey(configuration.getConsumerSecret(), oauthSecret);
		return generateSignatureString(key, text);
	}

	String toParameterString(Map<String, String> params) {
		StringBuilder builder = new StringBuilder();
		for (String key : params.keySet()) {
			builder.append(key).append("=").append(urlEncode(params.get(key))).append("&");
		}
		if (builder.length() != 0) {
			builder.setLength(builder.length() - 1);
		}
		return builder.toString();
	}

	Map<String, String> toParameterMap(String params) {
		params = params.trim();
		Map<String, String> result = new TreeMap<String, String>();

		for (String param : params.split("&")) {
			String[] kv = param.split("=");
			result.put(kv[0], kv[1]);
		}
		return result;
	}

	String generateSignatureBase(String method, String url, Map<String, String> params) {
		return method + "&" + urlEncode(url) + "&" + urlEncode(toParameterString(params));
	}

	String urlEncode(String string) {
		try {
			return URLEncoder.encode(string, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	String generateSignatureKey(String consumerSecret, String oauthTokenSecret) {
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
			return streamToString(connection.getErrorStream());
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
