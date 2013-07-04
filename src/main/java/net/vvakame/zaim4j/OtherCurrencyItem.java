package net.vvakame.zaim4j;

import java.io.IOException;
import java.io.StringWriter;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

/**
 * Currency item.
 * @author vvakame
 */
@JsonModel(decamelize = true, genToPackagePrivate = true, treatUnknownKeyAsError = true)
public class OtherCurrencyItem {

	@JsonKey
	CurrencyCode currencyCode;

	@JsonKey
	String unit;

	@JsonKey
	int point;

	@JsonKey
	String name;


	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		try {
			OtherCurrencyItemGen.encode(writer, this);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return writer.toString();
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
	 * @return the unit
	 * @category accessor
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 * @category accessor
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the point
	 * @category accessor
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * @param point the point to set
	 * @category accessor
	 */
	public void setPoint(int point) {
		this.point = point;
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
}
