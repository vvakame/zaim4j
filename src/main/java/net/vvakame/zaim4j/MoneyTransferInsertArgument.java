package net.vvakame.zaim4j;

/**
 * Argument for Money transfer insert API.
 * @see Zaim.Money.Transfer.Insert#execute(net.vvakame.zaim4j.Zaim.ZaimListener)
 * @author vvakame
 */
public class MoneyTransferInsertArgument {

	// reqruired
	double amount;

	String date;

	long fromAccountId;

	long toAccountId;

	// not required

	String comment;


	/**
	 * the constructor.
	 * @param amount
	 * @param date
	 * @param fromAccountId
	 * @param toAccountId
	 * @category constructor
	 */
	public MoneyTransferInsertArgument(double amount, String date, long fromAccountId, long toAccountId) {
		super();
		this.amount = amount;
		this.date = date;
		this.fromAccountId = fromAccountId;
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
