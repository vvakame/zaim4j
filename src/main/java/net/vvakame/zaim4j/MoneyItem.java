package net.vvakame.zaim4j;

import java.io.IOException;
import java.io.StringWriter;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;
import net.vvakame.zaim4j.Active.ActiveConverter;
import net.vvakame.zaim4j.MoneyMode.MoneyModeConverter;

/**
 * Money item.
 * @author vvakame
 */
@JsonModel(decamelize = true, genToPackagePrivate = true, treatUnknownKeyAsError = true)
public class MoneyItem {

	@JsonKey(sortOrder = 0)
	long id;

	@JsonKey
	long userId;

	@JsonKey
	String date;

	@JsonKey(converter = MoneyModeConverter.class)
	MoneyMode mode;

	@JsonKey
	long categoryId;

	@JsonKey
	long genreId;

	@JsonKey
	long fromAccountId;

	@JsonKey
	long toAccountId;

	@JsonKey
	double amount;

	@JsonKey
	String comment;

	@JsonKey(converter = ActiveConverter.class)
	Active active;

	@JsonKey
	String created;

	@JsonKey
	CurrencyCode currencyCode;

	@JsonKey
	String place;

	@JsonKey
	String name;

	@JsonKey
	long receiptId;


	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		try {
			MoneyItemGen.encode(writer, this);
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
	 * @return the userId
	 * @category accessor
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 * @category accessor
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the date
	 * @category accessor
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 * @category accessor
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the mode
	 * @category accessor
	 */
	public MoneyMode getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 * @category accessor
	 */
	public void setMode(MoneyMode mode) {
		this.mode = mode;
	}

	/**
	 * @return the categoryId
	 * @category accessor
	 */
	public long getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 * @category accessor
	 */
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the genreId
	 * @category accessor
	 */
	public long getGenreId() {
		return genreId;
	}

	/**
	 * @param genreId the genreId to set
	 * @category accessor
	 */
	public void setGenreId(long genreId) {
		this.genreId = genreId;
	}

	/**
	 * @return the fromAccountId
	 * @category accessor
	 */
	public long getFromAccountId() {
		return fromAccountId;
	}

	/**
	 * @param fromAccountId the fromAccountId to set
	 * @category accessor
	 */
	public void setFromAccountId(long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	/**
	 * @return the toAccountId
	 * @category accessor
	 */
	public long getToAccountId() {
		return toAccountId;
	}

	/**
	 * @param toAccountId the toAccountId to set
	 * @category accessor
	 */
	public void setToAccountId(long toAccountId) {
		this.toAccountId = toAccountId;
	}

	/**
	 * @return the amount
	 * @category accessor
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 * @category accessor
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the comment
	 * @category accessor
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 * @category accessor
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the active
	 * @category accessor
	 */
	public Active getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 * @category accessor
	 */
	public void setActive(Active active) {
		this.active = active;
	}

	/**
	 * @return the created
	 * @category accessor
	 */
	public String getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 * @category accessor
	 */
	public void setCreated(String created) {
		this.created = created;
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
	 * @return the place
	 * @category accessor
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * @param place the place to set
	 * @category accessor
	 */
	public void setPlace(String place) {
		this.place = place;
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
	 * @return the receiptId
	 * @category accessor
	 */
	public long getReceiptId() {
		return receiptId;
	}

	/**
	 * @param receiptId the receiptId to set
	 * @category accessor
	 */
	public void setReceiptId(long receiptId) {
		this.receiptId = receiptId;
	}
}
