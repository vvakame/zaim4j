package net.vvakame.zaim4j;

import java.io.IOException;
import java.io.StringWriter;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;
import net.vvakame.zaim4j.Active.ActiveConverter;

/**
 * Account item.
 * @author vvakame
 */
@JsonModel(decamelize = true, genToPackagePrivate = true, treatUnknownKeyAsError = true)
public class AccountItem extends OtherAccountItem {

	@JsonKey
	String description;

	@JsonKey
	CurrencyCode currencyCode;

	@JsonKey
	long sort;

	@JsonKey
	String modified;

	@JsonKey(converter = ActiveConverter.class)
	Active active;


	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		try {
			AccountItemGen.encode(writer, this);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return writer.toString();
	}

	/**
	 * @return the description
	 * @category accessor
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 * @category accessor
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the sort
	 * @category accessor
	 */
	public long getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 * @category accessor
	 */
	public void setSort(long sort) {
		this.sort = sort;
	}

	/**
	 * @return the modified
	 * @category accessor
	 */
	public String getModified() {
		return modified;
	}

	/**
	 * @param modified the modified to set
	 * @category accessor
	 */
	public void setModified(String modified) {
		this.modified = modified;
	}

	/**
	 * @return the active
	 * @category accessor
	 */
	public Active getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 * @category accessor
	 */
	public void setActive(Active active) {
		this.active = active;
	}
}
