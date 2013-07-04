package net.vvakame.zaim4j.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import net.vvakame.util.jsonpullparser.JsonFormatException;
import net.vvakame.zaim4j.ErrorResponse;
import net.vvakame.zaim4j.MoneyIncomeArgument;
import net.vvakame.zaim4j.MoneyListResponse;
import net.vvakame.zaim4j.MoneyPaymentArgument;
import net.vvakame.zaim4j.MoneyPostResponse;
import net.vvakame.zaim4j.MoneyTransferArgument;
import net.vvakame.zaim4j.OAuthConfiguration;
import net.vvakame.zaim4j.OAuthCredential;
import net.vvakame.zaim4j.Zaim;
import net.vvakame.zaim4j.Zaim.ZaimListener;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

/**
 * Test for {@link Zaim}.
 * @author vvakame
 */
public class ZaimTest {

	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.Money.List#execute(ZaimListener)}.
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
					assertThat(success.getRequested(), not(0L));
					assertThat(success.getMoney().size(), not(0));
					checker.ok();
				}

				@Override
				public void onFailure(ErrorResponse failure) {
					fail(failure.getMessage());
				}

				@Override
				public void onError(Exception e) {
					throw new RuntimeException(e);
				}
			});
			assertThat(checker.isOk(), is(true));
		}
	}

	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.Money.Payment#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @author vvakame
	 */
	@Test
	@Ignore("this test create actual data...")
	public void money_payment() throws IOException, JsonFormatException {
		Zaim zaim = getZaimInstance();

		final Checker checker = new Checker();
		MoneyPaymentArgument argument = new MoneyPaymentArgument(101, 10103, 888, "2013-7-7");
		zaim.money().payment(argument).execute(new ZaimListener<MoneyPostResponse>() {

			@Override
			public void onSuccess(MoneyPostResponse success) {
				assertThat(success.getRequested(), not(0L));
				assertThat(success.getMoney(), notNullValue());
				assertThat(success.getUser(), notNullValue());
				checker.ok();
			}

			@Override
			public void onFailure(ErrorResponse failure) {
				fail(failure.getMessage());
			}

			@Override
			public void onError(Exception e) {
				throw new RuntimeException(e);
			}
		});
		assertThat(checker.isOk(), is(true));
	}

	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.Money.Income#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @author vvakame
	 */
	@Test
	@Ignore("this test create actual data...")
	public void money_income() throws IOException, JsonFormatException {
		Zaim zaim = getZaimInstance();

		final Checker checker = new Checker();
		MoneyIncomeArgument argument = new MoneyIncomeArgument(11, 999, "2013-07-07");
		zaim.money().income(argument).execute(new ZaimListener<MoneyPostResponse>() {

			@Override
			public void onSuccess(MoneyPostResponse success) {
				assertThat(success.getRequested(), not(0L));
				assertThat(success.getMoney(), notNullValue());
				assertThat(success.getUser(), notNullValue());
				checker.ok();
			}

			@Override
			public void onFailure(ErrorResponse failure) {
				fail(failure.getMessage());
			}

			@Override
			public void onError(Exception e) {
				throw new RuntimeException(e);
			}
		});
		assertThat(checker.isOk(), is(true));
	}

	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.Money.Transfer#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @author vvakame
	 */
	@Test
	@Ignore("this test create actual data...")
	public void money_transfer() throws IOException, JsonFormatException {
		Zaim zaim = getZaimInstance();

		final Checker checker = new Checker();
		MoneyTransferArgument argument = new MoneyTransferArgument(999, "2013-07-07", 1, 2);
		zaim.money().transfer(argument).execute(new ZaimListener<MoneyPostResponse>() {

			@Override
			public void onSuccess(MoneyPostResponse success) {
				assertThat(success.getRequested(), not(0L));
				assertThat(success.getMoney(), notNullValue());
				assertThat(success.getUser(), notNullValue());
				checker.ok();
			}

			@Override
			public void onFailure(ErrorResponse failure) {
				fail(failure.getMessage());
			}

			@Override
			public void onError(Exception e) {
				throw new RuntimeException(e);
			}
		});
		assertThat(checker.isOk(), is(true));
	}

	Zaim getZaimInstance() throws IOException, JsonFormatException {
		OAuthConfiguration configuration =
				OAuthConfiguration.Builder.fromProperties("/zaim-oauth.properties").build();

		InputStream is = this.getClass().getResourceAsStream("/valid-oauthToken.json");
		String json = streamToString(is);
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
