package net.vvakame.zaim4j;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

/**
 * Money list response.
 * @see Zaim.Money.List#execute(net.vvakame.zaim4j.Zaim.ZaimListener)
 * @author vvakame
 */
@JsonModel(decamelize = true, genToPackagePrivate = true, treatUnknownKeyAsError = true)
public class MoneyListResponse {

	@JsonKey
	List<MoneyItem> money;

	@JsonKey
	long requested;


	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		try {
			MoneyListResponseGen.encode(writer, this);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return writer.toString();
	}

	/**
	 * @return the money
	 * @category accessor
	 */
	public List<MoneyItem> getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 * @category accessor
	 */
	public void setMoney(List<MoneyItem> money) {
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
