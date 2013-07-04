package net.vvakame.zaim4j;

/**
 * Argument for Money income API.
 * @see Zaim.Money#income(MoneyIncomeArgument)
 * @author vvakame
 */
public class MoneyIncomeArgument {

	// reqruired
	long categoryId;

	double amount;

	String date;

	// not required
	Long toAccountId;

	String comment;


	/**
	 * the constructor.
	 * @param categoryId
	 * @param amount
	 * @param date
	 * @category constructor
	 */
	public MoneyIncomeArgument(long categoryId, double amount, String date) {
		super();
		this.categoryId = categoryId;
		this.amount = amount;
		this.date = date;
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
