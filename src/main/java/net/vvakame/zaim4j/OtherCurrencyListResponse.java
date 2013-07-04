package net.vvakame.zaim4j;

import java.util.List;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

/**
 * Currency list response.
 * @author vvakame
 */
@JsonModel(decamelize = true, genToPackagePrivate = true, treatUnknownKeyAsError = true)
public class OtherCurrencyListResponse {

	@JsonKey
	List<OtherCurrencyItem> currencies;

	@JsonKey
	long requested;


	/**
	 * @return the currencies
	 * @category accessor
	 */
	public List<OtherCurrencyItem> getCurrencies() {
		return currencies;
	}

	/**
	 * @param currencies the currencies to set
	 * @category accessor
	 */
	public void setCurrencies(List<OtherCurrencyItem> currencies) {
		this.currencies = currencies;
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
