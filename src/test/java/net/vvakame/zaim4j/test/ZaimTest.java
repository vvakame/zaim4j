package net.vvakame.zaim4j.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.vvakame.util.jsonpullparser.JsonFormatException;
import net.vvakame.zaim4j.AccountListResponse;
import net.vvakame.zaim4j.CategoryListResponse;
import net.vvakame.zaim4j.GenreListResponse;
import net.vvakame.zaim4j.MoneyIncomeInsertArgument;
import net.vvakame.zaim4j.MoneyInfo;
import net.vvakame.zaim4j.MoneyListResponse;
import net.vvakame.zaim4j.MoneyMode;
import net.vvakame.zaim4j.MoneyModifiedResponse;
import net.vvakame.zaim4j.MoneyPaymentInsertArgument;
import net.vvakame.zaim4j.MoneyPostInsertResponse;
import net.vvakame.zaim4j.MoneyTransferInsertArgument;
import net.vvakame.zaim4j.MoneyUpdateArgument;
import net.vvakame.zaim4j.OAuthConfiguration;
import net.vvakame.zaim4j.OAuthCredential;
import net.vvakame.zaim4j.OtherAccountItem;
import net.vvakame.zaim4j.OtherAccountListResponse;
import net.vvakame.zaim4j.OtherCategoryItem;
import net.vvakame.zaim4j.OtherCategoryListResponse;
import net.vvakame.zaim4j.OtherCurrencyListResponse;
import net.vvakame.zaim4j.OtherGenreItem;
import net.vvakame.zaim4j.OtherGenreListResponse;
import net.vvakame.zaim4j.UserVerifyResponse;
import net.vvakame.zaim4j.Zaim;
import net.vvakame.zaim4j.Zaim.ZaimListener;
import net.vvakame.zaim4j.ZaimResult;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

/**
 * Test for {@link Zaim}.
 * @author vvakame
 */
@Ignore("write actual data...")
public class ZaimTest {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	String date = sdf.format(new Date(new Date().getTime() + 60 * 24 * 60 * 60 * 1000));


	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.User.Verify#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @throws IllegalAccessException 
	 * @author vvakame
	 */
	@Test
	public void user_verify() throws IOException, JsonFormatException, IllegalAccessException {
		Zaim zaim = getZaimInstance();

		ZaimResult<UserVerifyResponse> result = zaim.user().verify().execute();
		assertThat(result.isSuccess(), is(true));
		assertThat(result.getValue().getRequested(), not(0L));
		assertThat(result.getValue().getMe(), notNullValue());
	}

	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.Money.List#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @throws IllegalAccessException 
	 * @author vvakame
	 */
	@Test
	public void money_list() throws IOException, JsonFormatException, IllegalAccessException {
		Zaim zaim = getZaimInstance();

		{
			OtherGenreItem genreItem = getGenreListByMoneyMode(zaim, MoneyMode.Payment).get(0);
			MoneyPaymentInsertArgument argument =
					new MoneyPaymentInsertArgument(genreItem, 888, date);
			ZaimResult<MoneyPostInsertResponse> result =
					zaim.money().payment().insert(argument).execute();
			assertThat(result.isSuccess(), is(true));
		}

		ZaimResult<MoneyListResponse> result = zaim.money().list().execute();
		assertThat(result.isSuccess(), is(true));
		assertThat(result.getValue().getRequested(), not(0L));
		assertThat(result.getValue().getMoney().size(), not(0));
	}

	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.Money.Payment.Insert#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @author vvakame
	 * @throws IllegalAccessException 
	 */
	@Test
	public void money_payment() throws IOException, JsonFormatException, IllegalAccessException {
		Zaim zaim = getZaimInstance();

		final MoneyInfo money;
		{ // insert
			OtherGenreItem genreItem = getGenreListByMoneyMode(zaim, MoneyMode.Payment).get(0);
			MoneyPaymentInsertArgument argument =
					new MoneyPaymentInsertArgument(genreItem, 888, date);
			ZaimResult<MoneyPostInsertResponse> result =
					zaim.money().payment().insert(argument).execute();
			assertThat(result.isSuccess(), is(true));
			assertThat(result.getValue().getRequested(), not(0L));
			assertThat(result.getValue().getMoney(), notNullValue());
			assertThat(result.getValue().getUser(), notNullValue());

			money = result.getValue().getMoney();
		}
		{ // update
			MoneyUpdateArgument argument = new MoneyUpdateArgument(money, 333, date);
			ZaimResult<MoneyModifiedResponse> result =
					zaim.money().payment().update(argument).execute();
			assertThat(result.isSuccess(), is(true));
			assertThat(result.getValue().getRequested(), not(0L));
			assertThat(result.getValue().getMoney(), notNullValue());
			assertThat(result.getValue().getUser(), notNullValue());
		}
		{ // delete
			ZaimResult<MoneyModifiedResponse> result =
					zaim.money().payment().delete(money).execute();
			assertThat(result.isSuccess(), is(true));
			assertThat(result.getValue().getRequested(), not(0L));
			assertThat(result.getValue().getMoney(), notNullValue());
			assertThat(result.getValue().getUser(), notNullValue());
		}
	}

	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.Money.Income.Insert#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @author vvakame
	 * @throws IllegalAccessException 
	 */
	@Test
	public void money_income() throws IOException, JsonFormatException, IllegalAccessException {
		Zaim zaim = getZaimInstance();

		final MoneyInfo money;
		{
			OtherCategoryItem categoryItem =
					getCategoryListByMoneyMode(zaim, MoneyMode.Income).get(0);
			MoneyIncomeInsertArgument argument =
					new MoneyIncomeInsertArgument(categoryItem, 888, date);
			ZaimResult<MoneyPostInsertResponse> result =
					zaim.money().income().insert(argument).execute();
			assertThat(result.isSuccess(), is(true));
			assertThat(result.getValue().getRequested(), not(0L));
			assertThat(result.getValue().getMoney(), notNullValue());
			assertThat(result.getValue().getUser(), notNullValue());
			money = result.getValue().getMoney();
		}
		{ // update
			MoneyUpdateArgument argument = new MoneyUpdateArgument(money, 333, date);
			ZaimResult<MoneyModifiedResponse> result =
					zaim.money().income().update(argument).execute();
			assertThat(result.isSuccess(), is(true));
			assertThat(result.getValue().getRequested(), not(0L));
			assertThat(result.getValue().getMoney(), notNullValue());
			assertThat(result.getValue().getUser(), notNullValue());
		}
		{ // delete
			ZaimResult<MoneyModifiedResponse> result =
					zaim.money().income().delete(money).execute();
			assertThat(result.isSuccess(), is(true));
			assertThat(result.getValue().getRequested(), not(0L));
			assertThat(result.getValue().getMoney(), notNullValue());
			assertThat(result.getValue().getUser(), notNullValue());
		}
	}

	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.Money.Transfer.Insert#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @author vvakame
	 * @throws IllegalAccessException 
	 */
	@Test
	public void money_transfer() throws IOException, JsonFormatException, IllegalAccessException {
		Zaim zaim = getZaimInstance();

		final MoneyInfo money;
		{ // insert
			List<OtherAccountItem> accountList = getAccountList(zaim);
			OtherAccountItem from = accountList.get(0);
			OtherAccountItem to = accountList.get(1);
			MoneyTransferInsertArgument argument =
					new MoneyTransferInsertArgument(999, date, from, to);
			ZaimResult<MoneyPostInsertResponse> result =
					zaim.money().transfer().insert(argument).execute();
			assertThat(result.isSuccess(), is(true));
			assertThat(result.getValue().getRequested(), not(0L));
			assertThat(result.getValue().getMoney(), notNullValue());
			assertThat(result.getValue().getUser(), notNullValue());

			money = result.getValue().getMoney();
		}
		{ // update
			MoneyUpdateArgument argument = new MoneyUpdateArgument(money, 333, date);
			ZaimResult<MoneyModifiedResponse> result =
					zaim.money().transfer().update(argument).execute();
			assertThat(result.isSuccess(), is(true));
			assertThat(result.getValue().getRequested(), not(0L));
			assertThat(result.getValue().getMoney(), notNullValue());
			assertThat(result.getValue().getUser(), notNullValue());
		}
		{ // delete
			ZaimResult<MoneyModifiedResponse> result =
					zaim.money().transfer().delete(money).execute();
			assertThat(result.isSuccess(), is(true));
			assertThat(result.getValue().getRequested(), not(0L));
			assertThat(result.getValue().getMoney(), notNullValue());
			assertThat(result.getValue().getUser(), notNullValue());
		}
	}

	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.Category.List#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @author vvakame
	 * @throws IllegalAccessException 
	 */
	@Test
	public void category_list() throws IOException, JsonFormatException, IllegalAccessException {
		Zaim zaim = getZaimInstance();

		ZaimResult<CategoryListResponse> result = zaim.category().list().execute();
		assertThat(result.isSuccess(), is(true));
		assertThat(result.getValue().getRequested(), not(0L));
		assertThat(result.getValue().getCategories().size(), not(0));
	}

	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.Genre.List#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @author vvakame
	 * @throws IllegalAccessException 
	 */
	@Test
	public void genre_list() throws IOException, JsonFormatException, IllegalAccessException {
		Zaim zaim = getZaimInstance();

		ZaimResult<GenreListResponse> result = zaim.genre().list().execute();
		assertThat(result.isSuccess(), is(true));
		assertThat(result.getValue().getRequested(), not(0L));
		assertThat(result.getValue().getGenres().size(), not(0));
	}

	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.Account.List#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @author vvakame
	 * @throws IllegalAccessException 
	 */
	@Test
	public void account_list() throws IOException, JsonFormatException, IllegalAccessException {
		Zaim zaim = getZaimInstance();

		ZaimResult<AccountListResponse> result = zaim.account().list().execute();
		assertThat(result.isSuccess(), is(true));
		assertThat(result.getValue().getRequested(), not(0L));
		assertThat(result.getValue().getAccounts().size(), not(0));
	}

	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.Other.Account.List#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @author vvakame
	 * @throws IllegalAccessException 
	 */
	@Test
	public void other_account_list() throws IOException, JsonFormatException,
			IllegalAccessException {
		Zaim zaim = getZaimInstance();

		ZaimResult<OtherAccountListResponse> result = zaim.other().account().list().execute();
		assertThat(result.isSuccess(), is(true));
		assertThat(result.getValue().getRequested(), not(0L));
		assertThat(result.getValue().getAccounts().size(), not(0));
	}

	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.Other.Category.List#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @author vvakame
	 * @throws IllegalAccessException 
	 */
	@Test
	public void other_category_list() throws IOException, JsonFormatException,
			IllegalAccessException {
		Zaim zaim = getZaimInstance();

		ZaimResult<OtherCategoryListResponse> result = zaim.other().category().list().execute();
		assertThat(result.isSuccess(), is(true));
		assertThat(result.getValue().getRequested(), not(0L));
		assertThat(result.getValue().getCategories().size(), not(0));
	}

	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.Other.Genre.List#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @author vvakame
	 * @throws IllegalAccessException 
	 */
	@Test
	public void other_genre_list() throws IOException, JsonFormatException, IllegalAccessException {
		Zaim zaim = getZaimInstance();

		ZaimResult<OtherGenreListResponse> result = zaim.other().genre().list().execute();
		assertThat(result.isSuccess(), is(true));
		assertThat(result.getValue().getRequested(), not(0L));
		assertThat(result.getValue().getGenres().size(), not(0));
	}

	/**
	 * Test for {@link net.vvakame.zaim4j.Zaim.Other.Currency.List#execute(ZaimListener)}.
	 * @throws IOException
	 * @throws JsonFormatException
	 * @author vvakame
	 * @throws IllegalAccessException 
	 */
	@Test
	public void other_currency_list() throws IOException, JsonFormatException,
			IllegalAccessException {
		Zaim zaim = getZaimInstance();

		ZaimResult<OtherCurrencyListResponse> result = zaim.other().currency().list().execute();
		assertThat(result.isSuccess(), is(true));
		assertThat(result.getValue().getRequested(), not(0L));
		assertThat(result.getValue().getCurrencies().size(), not(0));
	}

	Zaim getZaimInstance() throws IOException, JsonFormatException {
		OAuthConfiguration configuration =
				OAuthConfiguration.Builder.fromProperties("/zaim-oauth.properties").build();

		InputStream is = this.getClass().getResourceAsStream("/valid-oauthToken.json");
		String json = streamToString(is);
		OAuthCredential credential = OAuthCredential.Builder.newBuild(configuration, json).build();
		return Zaim.newInstance(credential);
	}

	static void createMoneyPayment(Zaim zaim, int amount) {
		zaim.money().payment().insert(new MoneyPaymentInsertArgument(null, amount, "2014-07-07"));
	}

	static List<OtherCategoryItem> getCategoryListByMoneyMode(Zaim zaim, MoneyMode mode)
			throws IllegalAccessException, IOException, JsonFormatException {
		ZaimResult<OtherCategoryListResponse> result = zaim.other().category().list().execute();
		if (!result.isSuccess()) {
			throw new RuntimeException();
		}
		List<OtherCategoryItem> resultList = new ArrayList<OtherCategoryItem>();
		for (OtherCategoryItem item : result.getValue().getCategories()) {
			if (item.getMode() == mode) {
				resultList.add(item);
			}
		}
		return resultList;
	}

	static List<OtherGenreItem> getGenreListByMoneyMode(Zaim zaim, MoneyMode mode)
			throws IllegalAccessException, IOException, JsonFormatException {
		ZaimResult<OtherGenreListResponse> result = zaim.other().genre().list().execute();
		if (!result.isSuccess()) {
			throw new RuntimeException();
		}
		List<OtherGenreItem> resultList = new ArrayList<OtherGenreItem>();
		List<OtherCategoryItem> categoryList = getCategoryListByMoneyMode(zaim, mode);
		for (OtherGenreItem genreItem : result.getValue().getGenres()) {
			for (OtherCategoryItem categoryItem : categoryList) {
				if (genreItem.getCategoryId() == categoryItem.getId()) {
					resultList.add(genreItem);
					break;
				}
			}
		}
		return resultList;
	}

	static List<OtherAccountItem> getAccountList(Zaim zaim) throws IllegalAccessException,
			IOException, JsonFormatException {
		ZaimResult<OtherAccountListResponse> result = zaim.other().account().list().execute();
		if (!result.isSuccess()) {
			throw new RuntimeException();
		}
		return result.getValue().getAccounts();
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
