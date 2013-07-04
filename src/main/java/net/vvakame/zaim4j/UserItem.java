package net.vvakame.zaim4j;

import java.io.IOException;
import java.io.StringWriter;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

/**
 * User item.
 * @author vvakame
 */
@JsonModel(decamelize = true, genToPackagePrivate = true, treatUnknownKeyAsError = true)
public class UserItem {

	@JsonKey
	long id;

	@JsonKey
	String login;

	@JsonKey
	String name;

	@JsonKey
	int inputCount;

	@JsonKey
	int dayCount;

	@JsonKey
	int repeatCount;

	@JsonKey
	int day;

	@JsonKey
	int week;

	@JsonKey
	int month;

	@JsonKey
	CurrencyCode currencyCode;

	@JsonKey
	String dataModified;

	@JsonKey
	String profileImageUrl;

	@JsonKey
	String coverImageUrl;


	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		try {
			UserItemGen.encode(writer, this);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return writer.toString();
	}

	/**
	 * @return the id
	 * @category accessor
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 * @category accessor
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the login
	 * @category accessor
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 * @category accessor
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the name
	 * @category accessor
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 * @category accessor
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the inputCount
	 * @category accessor
	 */
	public int getInputCount() {
		return inputCount;
	}

	/**
	 * @param inputCount the inputCount to set
	 * @category accessor
	 */
	public void setInputCount(int inputCount) {
		this.inputCount = inputCount;
	}

	/**
	 * @return the dayCount
	 * @category accessor
	 */
	public int getDayCount() {
		return dayCount;
	}

	/**
	 * @param dayCount the dayCount to set
	 * @category accessor
	 */
	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}

	/**
	 * @return the repeatCount
	 * @category accessor
	 */
	public int getRepeatCount() {
		return repeatCount;
	}

	/**
	 * @param repeatCount the repeatCount to set
	 * @category accessor
	 */
	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	/**
	 * @return the day
	 * @category accessor
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 * @category accessor
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * @return the week
	 * @category accessor
	 */
	public int getWeek() {
		return week;
	}

	/**
	 * @param week the week to set
	 * @category accessor
	 */
	public void setWeek(int week) {
		this.week = week;
	}

	/**
	 * @return the month
	 * @category accessor
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 * @category accessor
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * @return the currencyCode
	 * @category accessor
	 */
	public CurrencyCode getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * @param currencyCode the currencyCode to set
	 * @category accessor
	 */
	public void setCurrencyCode(CurrencyCode currencyCode) {
		this.currencyCode = currencyCode;
	}

	/**
	 * @return the dataModified
	 * @category accessor
	 */
	public String getDataModified() {
		return dataModified;
	}

	/**
	 * @param dataModified the dataModified to set
	 * @category accessor
	 */
	public void setDataModified(String dataModified) {
		this.dataModified = dataModified;
	}

	/**
	 * @return the profileImageUrl
	 * @category accessor
	 */
	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	/**
	 * @param profileImageUrl the profileImageUrl to set
	 * @category accessor
	 */
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	/**
	 * @return the coverImageUrl
	 * @category accessor
	 */
	public String getCoverImageUrl() {
		return coverImageUrl;
	}

	/**
	 * @param coverImageUrl the coverImageUrl to set
	 * @category accessor
	 */
	public void setCoverImageUrl(String coverImageUrl) {
		this.coverImageUrl = coverImageUrl;
	}
}
