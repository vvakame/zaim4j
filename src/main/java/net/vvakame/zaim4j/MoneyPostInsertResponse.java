package net.vvakame.zaim4j;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

/**
 * Money payment response.
 * @see Zaim.Money.Payment.Insert#execute(net.vvakame.zaim4j.Zaim.ZaimListener)
 * @see Zaim.Money.Income.Insert#execute(net.vvakame.zaim4j.Zaim.ZaimListener)
 * @see Zaim.Money.Transfer.Insert#execute(net.vvakame.zaim4j.Zaim.ZaimListener)
 * @author vvakame
 */
@JsonModel(decamelize = true, genToPackagePrivate = true, treatUnknownKeyAsError = true)
public class MoneyPostInsertResponse {

	@JsonKey
	MoneyStamps stamps;

	@JsonKey
	MoneyUser user;

	@JsonKey
	MoneyInfo money;

	@JsonKey
	long requested;


	/**
	 * @return the stamps
	 * @category accessor
	 */
	public MoneyStamps getStamps() {
		return stamps;
	}

	/**
	 * @param stamps the stamps to set
	 * @category accessor
	 */
	public void setStamps(MoneyStamps stamps) {
		this.stamps = stamps;
	}

	/**
	 * @return the user
	 * @category accessor
	 */
	public MoneyUser getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 * @category accessor
	 */
	public void setUser(MoneyUser user) {
		this.user = user;
	}

	/**
	 * @return the money
	 * @category accessor
	 */
	public MoneyInfo getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 * @category accessor
	 */
	public void setMoney(MoneyInfo money) {
		this.money = money;
	}

	/**
	 * @return the requested
	 * @category accessor
	 */
	public long getRequested() {
		return requested;
	}

	/**
	 * @param requested the requested to set
	 * @category accessor
	 */
	public void setRequested(long requested) {
		this.requested = requested;
	}
}
