package net.vvakame.zaim4j.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.vvakame.util.jsonpullparser.JsonFormatException;
import net.vvakame.zaim4j.CategoryListResponse;
import net.vvakame.zaim4j.ErrorResponse;
import net.vvakame.zaim4j.GenreListResponse;
import net.vvakame.zaim4j.MoneyIncomeInsertArgument;
import net.vvakame.zaim4j.MoneyListResponse;
import net.vvakame.zaim4j.MoneyModifiedResponse;
import net.vvakame.zaim4j.MoneyPaymentInsertArgument;
import net.vvakame.zaim4j.MoneyPostInsertResponse;
import net.vvakame.zaim4j.MoneyTransferInsertArgument;
import net.vvakame.zaim4j.MoneyUpdateArgument;
import net.vvakame.zaim4j.OAuthConfiguration;
import net.vvakame.zaim4j.OAuthCredential;
import net.vvakame.zaim4j.UserVerifyResponse;
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

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	String date = sdf.format(new Date(new Date().getTime() + 5 * 24 * 60 * 60 * 1000));


	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.User.Verify#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @author vvakame
	 */
	@Test
	public void user_verify() throws IOException, JsonFormatException {
		Zaim zaim = getZaimInstance();

		final Holder<UserVerifyResponse> holder = new Holder<UserVerifyResponse>();
		{
			zaim.user().verify().execute(new ZaimListener<UserVerifyResponse>() {

				@Override
				public void onSuccess(UserVerifyResponse success) {
					assertThat(success.getRequested(), not(0L));
					assertThat(success.getMe(), notNullValue());
					holder.ok(success);
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
			assertThat(holder.getObject(), notNullValue());
		}
	}

	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.Money.List#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @author vvakame
	 */
	@Test
	public void money_list() throws IOException, JsonFormatException {
		Zaim zaim = getZaimInstance();

		final Holder<MoneyListResponse> holder = new Holder<MoneyListResponse>();
		zaim.money().list().execute(new ZaimListener<MoneyListResponse>() {

			@Override
			public void onSuccess(MoneyListResponse success) {
				assertThat(success.getRequested(), not(0L));
				assertThat(success.getMoney().size(), not(0));
				holder.ok(success);
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
		assertThat(holder.getObject(), notNullValue());
	}

	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.Money.Payment.Insert#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @author vvakame
	 */
	@Test
	@Ignore("this test create actual data...")
	public void money_payment() throws IOException, JsonFormatException {
		Zaim zaim = getZaimInstance();

		final long moneyId;
		{ // insert
			final Holder<MoneyPostInsertResponse> holder = new Holder<MoneyPostInsertResponse>();
			MoneyPaymentInsertArgument argument =
					new MoneyPaymentInsertArgument(101, 10103, 888, date);
			zaim.money().payment().insert(argument)
				.execute(new ZaimListener<MoneyPostInsertResponse>() {

					@Override
					public void onSuccess(MoneyPostInsertResponse success) {
						assertThat(success.getRequested(), not(0L));
						assertThat(success.getMoney(), notNullValue());
						assertThat(success.getUser(), notNullValue());
						holder.ok(success);
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
			assertThat(holder.getObject(), notNullValue());
			moneyId = holder.getObject().getMoney().getId();
		}
		{ // update
			final Holder<MoneyModifiedResponse> holder = new Holder<MoneyModifiedResponse>();
			MoneyUpdateArgument argument = new MoneyUpdateArgument(moneyId, 333, date);
			zaim.money().payment().update(argument)
				.execute(new ZaimListener<MoneyModifiedResponse>() {

					@Override
					public void onSuccess(MoneyModifiedResponse success) {
						assertThat(success.getRequested(), not(0L));
						assertThat(success.getMoney(), notNullValue());
						assertThat(success.getUser(), notNullValue());
						holder.ok(success);
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
			assertThat(holder.getObject(), notNullValue());
		}
		{ // delete
			final Holder<MoneyModifiedResponse> holder = new Holder<MoneyModifiedResponse>();
			zaim.money().payment().delete(moneyId)
				.execute(new ZaimListener<MoneyModifiedResponse>() {

					@Override
					public void onSuccess(MoneyModifiedResponse success) {
						assertThat(success.getRequested(), not(0L));
						assertThat(success.getMoney(), notNullValue());
						assertThat(success.getUser(), notNullValue());
						holder.ok(success);
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
			assertThat(holder.getObject(), notNullValue());
		}
	}

	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.Money.Income.Insert#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @author vvakame
	 */
	@Test
	@Ignore("this test create actual data...")
	public void money_income() throws IOException, JsonFormatException {
		Zaim zaim = getZaimInstance();

		final long moneyId;
		{
			final Holder<MoneyPostInsertResponse> holder = new Holder<MoneyPostInsertResponse>();
			MoneyIncomeInsertArgument argument = new MoneyIncomeInsertArgument(11, 999, date);
			zaim.money().income().insert(argument)
				.execute(new ZaimListener<MoneyPostInsertResponse>() {

					@Override
					public void onSuccess(MoneyPostInsertResponse success) {
						assertThat(success.getRequested(), not(0L));
						assertThat(success.getMoney(), notNullValue());
						assertThat(success.getUser(), notNullValue());
						holder.ok(success);
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
			assertThat(holder.getObject(), notNullValue());
			moneyId = holder.getObject().getMoney().getId();
		}
		{ // update
			final Holder<MoneyModifiedResponse> holder = new Holder<MoneyModifiedResponse>();
			MoneyUpdateArgument argument = new MoneyUpdateArgument(moneyId, 333, date);
			zaim.money().income().update(argument)
				.execute(new ZaimListener<MoneyModifiedResponse>() {

					@Override
					public void onSuccess(MoneyModifiedResponse success) {
						assertThat(success.getRequested(), not(0L));
						assertThat(success.getMoney(), notNullValue());
						assertThat(success.getUser(), notNullValue());
						holder.ok(success);
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
			assertThat(holder.getObject(), notNullValue());
		}
		{ // delete
			final Holder<MoneyModifiedResponse> holder = new Holder<MoneyModifiedResponse>();
			zaim.money().income().delete(moneyId)
				.execute(new ZaimListener<MoneyModifiedResponse>() {

					@Override
					public void onSuccess(MoneyModifiedResponse success) {
						assertThat(success.getRequested(), not(0L));
						assertThat(success.getMoney(), notNullValue());
						assertThat(success.getUser(), notNullValue());
						holder.ok(success);
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
			assertThat(holder.getObject(), notNullValue());
		}
	}

	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.Money.Transfer.Insert#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @author vvakame
	 */
	@Test
	@Ignore("this test create actual data...")
	public void money_transfer_insert() throws IOException, JsonFormatException {
		Zaim zaim = getZaimInstance();

		final long moneyId;
		{ // insert
			final Holder<MoneyPostInsertResponse> holder = new Holder<MoneyPostInsertResponse>();
			MoneyTransferInsertArgument argument = new MoneyTransferInsertArgument(999, date, 1, 2);
			zaim.money().transfer().insert(argument)
				.execute(new ZaimListener<MoneyPostInsertResponse>() {

					@Override
					public void onSuccess(MoneyPostInsertResponse success) {
						assertThat(success.getRequested(), not(0L));
						assertThat(success.getMoney(), notNullValue());
						assertThat(success.getUser(), notNullValue());
						holder.ok(success);
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
			assertThat(holder.getObject(), notNullValue());
			moneyId = holder.getObject().getMoney().getId();
		}
		{ // update
			final Holder<MoneyModifiedResponse> holder = new Holder<MoneyModifiedResponse>();
			MoneyUpdateArgument argument = new MoneyUpdateArgument(moneyId, 333, date);
			zaim.money().transfer().update(argument)
				.execute(new ZaimListener<MoneyModifiedResponse>() {

					@Override
					public void onSuccess(MoneyModifiedResponse success) {
						assertThat(success.getRequested(), not(0L));
						assertThat(success.getMoney(), notNullValue());
						assertThat(success.getUser(), notNullValue());
						holder.ok(success);
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
			assertThat(holder.getObject(), notNullValue());
		}
		{ // delete
			final Holder<MoneyModifiedResponse> holder = new Holder<MoneyModifiedResponse>();
			zaim.money().transfer().delete(moneyId)
				.execute(new ZaimListener<MoneyModifiedResponse>() {

					@Override
					public void onSuccess(MoneyModifiedResponse success) {
						assertThat(success.getRequested(), not(0L));
						assertThat(success.getMoney(), notNullValue());
						assertThat(success.getUser(), notNullValue());
						holder.ok(success);
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
			assertThat(holder.getObject(), notNullValue());
		}
	}

	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.Category.List#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @author vvakame
	 */
	@Test
	public void category_list() throws IOException, JsonFormatException {
		Zaim zaim = getZaimInstance();

		final Holder<CategoryListResponse> holder = new Holder<CategoryListResponse>();
		zaim.category().list().execute(new ZaimListener<CategoryListResponse>() {

			@Override
			public void onSuccess(CategoryListResponse success) {
				assertThat(success.getRequested(), not(0L));
				assertThat(success.getCategories().size(), not(0));
				holder.ok(success);
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
		assertThat(holder.getObject(), notNullValue());
	}

	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.Genre.List#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @author vvakame
	 */
	@Test
	public void genre_list() throws IOException, JsonFormatException {
		Zaim zaim = getZaimInstance();

		final Holder<GenreListResponse> holder = new Holder<GenreListResponse>();
		zaim.genre().list().execute(new ZaimListener<GenreListResponse>() {

			@Override
			public void onSuccess(GenreListResponse success) {
				assertThat(success.getRequested(), not(0L));
				assertThat(success.getGenres().size(), not(0));
				holder.ok(success);
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
		assertThat(holder.getObject(), notNullValue());
	}

	Zaim getZaimInstance() throws IOException, JsonFormatException {
		OAuthConfiguration configuration =
				OAuthConfiguration.Builder.fromProperties("/zaim-oauth.properties").build();

		InputStream is = this.getClass().getResourceAsStream("/valid-oauthToken.json");
		String json = streamToString(is);
		OAuthCredential credential = OAuthCredential.Builder.newBuild(configuration, json).build();
		return Zaim.newInstance(credential);
	}


	static class Holder<T> {

		private T obj;


		public void ok(T obj) {
			this.obj = obj;
		}

		public T getObject() {
			return obj;
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
