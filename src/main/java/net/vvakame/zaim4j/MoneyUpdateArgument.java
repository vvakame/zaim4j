package net.vvakame.zaim4j;

/**
 * Argument for Money payment update API.
 * @see Zaim.Money.Payment#update(MoneyUpdateArgument)
 * @author vvakame
 */
public class MoneyUpdateArgument {

	// required
	long id;

	double amount;

	String date;

	// not required
	Long fromAccountId;

	Long toAccountId;

	Long genreId;

	Long categoryId;

	String comment;


	/**
	 * the constructor.
	 * @param id
	 * @param amount
	 * @param date
	 * @category constructor
	 * @deprecated replaced to {@link #MoneyUpdateArgument(MoneyItem, double, String)}
	 */
	@Deprecated
	public MoneyUpdateArgument(long id, double amount, String date) {
		this.id = id;
		this.amount = amount;
		this.date = date;
	}

	/**
	 * the constructor.
	 * @param money
	 * @param amount
	 * @param date
	 * @category constructor
	 */
	public MoneyUpdateArgument(MoneyItem money, double amount, String date) {
		this(money.getId(), amount, date);
	}

	@Override
	public String toString() {
		return "MoneyUpdateArgument [id=" + id + ", amount=" + amount + ", date=" + date
				+ ", fromAccountId=" + fromAccountId + ", toAccountId=" + toAccountId + ", genre="
				+ genreId + ", categoryId=" + categoryId + ", comment=" + comment + "]";
	}

	/**
	 * set id.<br>
	 * id = {@link MoneyItem#getId()}.
	 * @param money
	 * @category accessor
	 */
	public void setId(MoneyItem money) {
		setId(money.getId());
	}

	/**
	 * @param id the id to set
	 * @category accessor
	 * @deprecated replace to {@link #setId(MoneyItem)}
	 */
	@Deprecated
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * set categoryId and genreId.<br>
	 * categoryId = {@link OtherCategoryItem#getId()}, genreId = null.
	 * @param category
	 * @author vvakame
	 */
	public void setCategory(OtherCategoryItem category) {
		if (category != null) {
			setCategoryId(category.getId());
			setGenreId(null);
		} else {
			setCategoryId(null);
			setGenreId(null);
		}
	}

	/**
	 * set genreId.<br>
	 * categoryId = {@link OtherGenreItem#getCategoryId()}, genreId = {@link OtherGenreItem#getId()}.
	 * @param genre
	 * @author vvakame
	 */
	public void setGenre(OtherGenreItem genre) {
		if (genre != null) {
			setCategoryId(genre.getCategoryId());
			setGenreId(genre.getId());
		} else {
			setGenreId(null);
		}
	}

	/**
	 * @param categoryId the categoryId to set
	 * @category accessor
	 * @deprecated replace to {@link #setCategory(OtherCategoryItem)}.
	 */
	@Deprecated
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @param genreId the genreId to set
	 * @category accessor
	 * @deprecated replace to {@link #setGenre(OtherGenreItem)}.
	 */
	@Deprecated
	public void setGenreId(Long genreId) {
		this.genreId = genreId;
	}

	/**
	 * set fromAccountId.<br>
	 * fromAccountId = {@link AccountItem#getId()}.
	 * @param fromAccount
	 * @category accessor
	 */
	public void setFromAccount(AccountItem fromAccount) {
		if (fromAccount != null) {
			setFromAccountId(fromAccount.getId());
		} else {
			setFromAccountId(null);
		}
	}

	/**
	 * @param fromAccountId the fromAccountId to set
	 * @category accessor
	 * @deprecated replace to {@link #setFromAccount(AccountItem)}.
	 */
	@Deprecated
	public void setFromAccountId(Long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	/**
	 * set toAccountId.<br>
	 * toAccountId = {@link AccountItem#getId()}.
	 * @param toAccount
	 * @category accessor
	 */
	public void setToAccount(AccountItem toAccount) {
		if (toAccount != null) {
			setToAccountId(toAccount.getId());
		} else {
			setToAccountId(null);
		}
	}

	/**
	 * @param toAccountId the toAccountId to set
	 * @category accessor
	 */
	@Deprecated
	public void setToAccountId(Long toAccountId) {
		this.toAccountId = toAccountId;
	}

	// ---- generated by eclipse 

	/**
	 * @return the id
	 * @category accessor
	 */
	public long getId() {
		return id;
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
	 * @return the fromAccountId
	 * @category accessor
	 */
	public Long getFromAccountId() {
		return fromAccountId;
	}

	/**
	 * @return the toAccountId
	 * @category accessor
	 */
	public Long getToAccountId() {
		return toAccountId;
	}

	/**
	 * @return the genre
	 * @category accessor
	 */
	public Long getGenreId() {
		return genreId;
	}

	/**
	 * @return the categoryId
	 * @category accessor
	 */
	public Long getCategoryId() {
		return categoryId;
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
}
