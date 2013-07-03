package net.vvakame.zaim4j;

/**
 * OAuth credential information about user.
 * @author vvakame
 */
public class OAuthCredential {

	OAuthConfiguration configuration;

	String requestToken;

	String requestTokenSecret;

	String oauthToken;

	String oauthTokenSecret;


	/**
	 * Builder for {@link OAuthCredential}.
	 * @author vvakame
	 */
	public static class Builder {

		OAuthCredential credential;


		/**
		 * Create new {@link Builder} instance.
		 * @param configuration 
		 * @return new builder instance
		 * @author vvakame
		 */
		public static Builder newBuild(OAuthConfiguration configuration) {
			return new Builder(configuration);
		}

		/**
		 * Create new {@link Builder} instance.
		 * @return new builder instance
		 * @author vvakame
		 */
		public static Builder newBuild() {
			return new Builder(null);
		}

		private Builder(OAuthConfiguration configuration) {
			this.credential = new OAuthCredential();
			this.credential.configuration = configuration;
		}

		/**
		 * @param configuration the configuration to set
		 * @return this
		 * @category accessor
		 */
		public Builder setConfiguration(OAuthConfiguration configuration) {
			credential.configuration = configuration;
			return this;
		}

		/**
		 * @param requestToken the requestToken to set
		 * @return this
		 * @category accessor
		 */
		public Builder setRequestToken(String requestToken) {
			credential.requestToken = requestToken;
			return this;
		}

		/**
		 * @param requestTokenSecret the requestTokenSecret to set
		 * @return this
		 * @category accessor
		 */
		public Builder setRequestTokenSecret(String requestTokenSecret) {
			credential.requestTokenSecret = requestTokenSecret;
			return this;
		}

		/**
		 * @param oauthToken the oauthToken to set
		 * @return this
		 * @category accessor
		 */
		public Builder setOauthToken(String oauthToken) {
			credential.oauthToken = oauthToken;
			return this;
		}

		/**
		 * @param oauthTokenSecret the oauthTokenSecret to set
		 * @return this
		 * @category accessor
		 */
		public Builder setOauthTokenSecret(String oauthTokenSecret) {
			credential.oauthTokenSecret = oauthTokenSecret;
			return this;
		}

		/**
		 * Build {@link OAuthCredential}.
		 * @return {@link OAuthCredential}
		 * @author vvakame
		 */
		public OAuthCredential build() {
			if (credential.configuration == null) {
				throw new IllegalStateException("OAuthConfiguration is required.");
			}
			if ((credential.oauthToken == null && credential.oauthTokenSecret != null)
					|| (credential.oauthToken != null && credential.oauthTokenSecret == null)) {
				throw new IllegalStateException(
						"OAuthToken and OAuthTokenSecret are both required.");
			}
			if ((credential.requestToken == null && credential.requestToken != null)
					|| (credential.requestToken != null && credential.requestTokenSecret == null)) {
				throw new IllegalStateException(
						"RequestToken and RequestTokenSecret are both required.");
			}
			return credential;
		}
	}


	/**
	 * Get auth url.
	 * @return URL of the page that you want to authorization.
	 * @author vvakame
	 */
	public String getAuthUrl() {
		if (requestToken == null) {
			throw new IllegalStateException("Do not have RequestToken.");
		}
		return configuration.getAuthorizeUrl() + "?oauth_token=" + requestToken;
	}

	/**
	 * @return the requestToken
	 * @category accessor
	 */
	public String getRequestToken() {
		return requestToken;
	}

	/**
	 * @return the requestTokenSecret
	 * @category accessor
	 */
	public String getRequestTokenSecret() {
		return requestTokenSecret;
	}

	/**
	 * @return the oauthToken
	 * @category accessor
	 */
	public String getOauthToken() {
		return oauthToken;
	}

	/**
	 * @return the oauthTokenSecret
	 * @category accessor
	 */
	public String getOauthTokenSecret() {
		return oauthTokenSecret;
	}
}
