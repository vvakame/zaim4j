package net.vvakame.zaim4j;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

/**
 * Stamp.
 * @author vvakame
 */
@JsonModel(decamelize = true, genToPackagePrivate = true, treatUnknownKeyAsError = true)
public class MoneyStamp {

	@JsonKey
	String imageUrl;

	@JsonKey
	String name;

	@JsonKey
	String description;


	/**
	 * @return the imageUrl
	 * @category accessor
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 * @category accessor
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
}
