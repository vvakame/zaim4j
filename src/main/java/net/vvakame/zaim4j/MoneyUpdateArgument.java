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

	Long genre;

	Long CategoryId;

	String comment;


	/**
	 * the constructor.
	 * @param id
	 * @param amount
	 * @param date
	 * @category constructor
	 */
	public MoneyUpdateArgument(long id, double amount, String date) {
		this.id = id;
		this.amount = amount;
		this.date = date;
	}

	@Override
	public String toString() {
		return "MoneyUpdateArgument [id=" + id + ", amount=" + amount + ", date=" + date
				+ ", fromAccountId=" + fromAccountId + ", toAccountId=" + toAccountId + ", genre="
				+ genre + ", CategoryId=" + CategoryId + ", comment=" + comment + "]";
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
	 * @param fromAccountId the fromAccountId to set
	 * @category accessor
	 */
	public void setFromAccountId(Long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	/**
	 * @return the toAccountId
	 * @category accessor
	 */
	public Long getToAccountId() {
		return toAccountId;
	}

	/**
	 * @param toAccountId the toAccountId to set
	 * @category accessor
	 */
	public void setToAccountId(Long toAccountId) {
		this.toAccountId = toAccountId;
	}

	/**
	 * @return the genre
	 * @category accessor
	 */
	public Long getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 * @category accessor
	 */
	public void setGenre(Long genre) {
		this.genre = genre;
	}

	/**
	 * @return the categoryId
	 * @category accessor
	 */
	public Long getCategoryId() {
		return CategoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 * @category accessor
	 */
	public void setCategoryId(Long categoryId) {
		CategoryId = categoryId;
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
