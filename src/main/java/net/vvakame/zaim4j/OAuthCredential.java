package net.vvakame.zaim4j;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;

import net.vvakame.util.jsonpullparser.JsonFormatException;
import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

/**
 * OAuth credential information about user.
 * @author vvakame
 */
@JsonModel(genToPackagePrivate = true, treatUnknownKeyAsError = true)
public class OAuthCredential implements Serializable {

	private static final long serialVersionUID = 1L;

	OAuthConfiguration configuration;

	@JsonKey
	String requestToken;

	@JsonKey
	String requestTokenSecret;

	@JsonKey
	String oauthToken;

	@JsonKey
	String oauthTokenSecret;


	/**
	 * Convert to JSON.<br>
	 * @return JSON string
	 * @see Builder#newBuild(OAuthConfiguration, String)
	 * @author vvakame
	 */
	public String toJson() {
		StringWriter writer = new StringWriter();
		try {
			OAuthCredentialGen.encode(writer, this);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return writer.toString();
	}


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
		static Builder newBuild(OAuthConfiguration configuration) {
			return new Builder(configuration);
		}

		/**
		 * Create new {@link Builder} instance.
		 * @param configuration 
		 * @param json JSON of OAuthCredential
		 * @return new builder instance
		 * @author vvakame
		 * @throws JsonFormatException 
		 */
		public static Builder newBuild(OAuthConfiguration configuration, String json)
				throws JsonFormatException {
			Builder builder = new Builder(configuration);
			try {
				builder.credential = OAuthCredentialGen.get(json);
				builder.credential.configuration = configuration;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return builder;
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

	/**
	 * @param requestToken the requestToken to set
	 * @category accessor
	 */
	void setRequestToken(String requestToken) {
		this.requestToken = requestToken;
	}

	/**
	 * @param requestTokenSecret the requestTokenSecret to set
	 * @category accessor
	 */
	void setRequestTokenSecret(String requestTokenSecret) {
		this.requestTokenSecret = requestTokenSecret;
	}

	/**
	 * @param oauthToken the oauthToken to set
	 * @category accessor
	 */
	void setOauthToken(String oauthToken) {
		this.oauthToken = oauthToken;
	}

	/**
	 * @param oauthTokenSecret the oauthTokenSecret to set
	 * @category accessor
	 */
	void setOauthTokenSecret(String oauthTokenSecret) {
		this.oauthTokenSecret = oauthTokenSecret;
	}
}
