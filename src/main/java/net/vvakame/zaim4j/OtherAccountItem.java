package net.vvakame.zaim4j;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

/**
 * Account item.
 * @author vvakame
 */
@JsonModel(decamelize = true, genToPackagePrivate = true, treatUnknownKeyAsError = true)
public class OtherAccountItem {

	@JsonKey
	long id;

	@JsonKey
	String name;

	@JsonKey
	String color;

	@JsonKey
	String iconUrl;


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
}
