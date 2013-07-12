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
public class AccountItem {

	@JsonKey
	long id;

	@JsonKey
	String name;

	@JsonKey
	String color;

	@JsonKey
	String iconUrl;

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

	/**
	 * @return the color
	 * @category accessor
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 * @category accessor
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the iconUrl
	 * @category accessor
	 */
	public String getIconUrl() {
		return iconUrl;
	}

	/**
	 * @param iconUrl the iconUrl to set
	 * @category accessor
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
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
