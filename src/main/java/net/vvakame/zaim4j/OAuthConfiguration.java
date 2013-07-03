package net.vvakame.zaim4j;

/**
 * OAuth configuration for application.
 * @author vvakame
 */
public class OAuthConfiguration {

	String consumerKey;

	String consumerSecret;

	String baseUrl = "https://api.zaim.net/";

	String requestTokenUrl = "https://api.zaim.net/v2/auth/request";

	String authorizeUrl = "https://www.zaim.net/users/auth";

	String accessTokenUrl = "https://api.zaim.net/v2/auth/access";

	String callbackUrl;


	private OAuthConfiguration() {
	}


	/**
	 * Builder for {@link OAuthConfiguration}.
	 * @author vvakame
	 */
	public static class Builder {

		OAuthConfiguration configuration;


		/**
		 * Create new {@link Builder} instance.
		 * @return new builder instance
		 * @author vvakame
		 */
		public static Builder newBuild() {
			return new Builder();
		}

		private Builder() {
			this.configuration = new OAuthConfiguration();
		}

		/**
		 * @param consumerKey the consumerKey to set
		 * @return this
		 * @category accessor
		 */
		public Builder setConsumerKey(String consumerKey) {
			configuration.consumerKey = consumerKey;
			return this;
		}

		/**
		 * @param consumerSecret the consumerSecret to set
		 * @return this
		 * @category accessor
		 */
		public Builder setConsumerSecret(String consumerSecret) {
			configuration.consumerSecret = consumerSecret;
			return this;
		}

		/**
		 * @param baseUrl the baseUrl to set
		 * @return this
		 * @category accessor
		 */
		public Builder setBaseUrl(String baseUrl) {
			configuration.baseUrl = baseUrl;
			return this;
		}

		/**
		 * @param requestTokenUrl the requestTokenUrl to set
		 * @return this
		 * @category accessor
		 */
		public Builder setRequestTokenUrl(String requestTokenUrl) {
			configuration.requestTokenUrl = requestTokenUrl;
			return this;
		}

		/**
		 * @param authorizeUrl the authorizeUrl to set
		 * @return this
		 * @category accessor
		 */
		public Builder setAuthorizeUrl(String authorizeUrl) {
			configuration.authorizeUrl = authorizeUrl;
			return this;
		}

		/**
		 * @param accessTokenUrl the accessTokenUrl to set
		 * @return this
		 * @category accessor
		 */
		public Builder setAccessTokenUrl(String accessTokenUrl) {
			configuration.accessTokenUrl = accessTokenUrl;
			return this;
		}

		/**
		 * @param callbackUrl the callbackUrl to set
		 * @return this
		 * @category accessor
		 */
		public Builder setCallbackUrl(String callbackUrl) {
			configuration.callbackUrl = callbackUrl;
			return this;
		}

		/**
		 * Build {@link OAuthConfiguration}.
		 * @return {@link OAuthConfiguration}
		 * @author vvakame
		 */
		public OAuthConfiguration build() {
			if (configuration.consumerKey == null) {
				throw new IllegalStateException("ConsumerKey is required.");
			}
			if (configuration.consumerSecret == null) {
				throw new IllegalStateException("ConsumerSecret is required.");
			}
			if (configuration.callbackUrl == null) {
				throw new IllegalStateException("callback url is required.");
			}
			return configuration;
		}
	}


	/**
	 * @return the consumerKey
	 * @category accessor
	 */
	public String getConsumerKey() {
		return consumerKey;
	}

	/**
	 * @return the consumerSecret
	 * @category accessor
	 */
	public String getConsumerSecret() {
		return consumerSecret;
	}

	/**
	 * @return the baseUrl
	 * @category accessor
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * @return the requestTokenUrl
	 * @category accessor
	 */
	public String getRequestTokenUrl() {
		return requestTokenUrl;
	}

	/**
	 * @return the authorizeUrl
	 * @category accessor
	 */
	public String getAuthorizeUrl() {
		return authorizeUrl;
	}

	/**
	 * @return the accessTokenUrl
	 * @category accessor
	 */
	public String getAccessTokenUrl() {
		return accessTokenUrl;
	}

	/**
	 * @return the callbackUrl
	 * @category accessor
	 */
	public String getCallbackUrl() {
		return callbackUrl;
	}
}
