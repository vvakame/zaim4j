package net.vvakame.zaim4j;

import java.io.IOException;
import java.io.StringWriter;
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


	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		try {
			OtherCurrencyListResponseGen.encode(writer, this);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return writer.toString();
	}

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
