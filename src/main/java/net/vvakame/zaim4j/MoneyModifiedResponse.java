package net.vvakame.zaim4j;

import java.io.IOException;
import java.io.StringWriter;

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
public class MoneyModifiedResponse {

	@JsonKey
	MoneyUser user;

	@JsonKey
	MoneyInfo money;

	@JsonKey
	long requested;


	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		try {
			MoneyModifiedResponseGen.encode(writer, this);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return writer.toString();
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
