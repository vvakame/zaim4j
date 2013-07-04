package net.vvakame.zaim4j;

import java.io.IOException;
import java.io.InputStream;

import net.vvakame.util.jsonpullparser.JsonFormatException;
import net.vvakame.zaim4j.Zaim.ZaimListener;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

/**
 * Test for {@link Zaim}.
 * @author vvakame
 */
public class ZaimTest {

	/**
	 * Test for {@link Zaim.Money.List#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @author vvakame
	 */
	@Test
	public void money_list() throws IOException, JsonFormatException {
		Zaim zaim = getZaimInstance();

		{
			final Checker checker = new Checker();
			zaim.money().list().execute(new ZaimListener<MoneyListResponse>() {

				@Override
				public void onSuccess(MoneyListResponse success) {
					assertThat(success.requested, not(0L));
					assertThat(success.money.size(), not(0));
					checker.ok();
				}

				@Override
				public void onFailure(ErrorResponse failure) {
					fail();
				}

				@Override
				public void onError(Exception e) {
					throw new RuntimeException(e);
				}
			});
			assertThat(checker.isOk(), is(true));
		}
	}

	Zaim getZaimInstance() throws IOException, JsonFormatException {
		OAuthConfiguration configuration =
				OAuthConfiguration.Builder.fromProperties("/zaim-oauth.properties").build();

		InputStream is = OAuthConnectorTest.class.getResourceAsStream("/valid-oauthToken.json");
		String json = OAuthConnector.streamToString(is);
		OAuthCredential credential = OAuthCredential.Builder.newBuild(configuration, json).build();
		return Zaim.newInstance(credential);
	}


	static class Checker {

		private boolean ok = false;


		public void ok() {
			ok = true;
		}

		public boolean isOk() {
			return ok;
		}
	}
}
