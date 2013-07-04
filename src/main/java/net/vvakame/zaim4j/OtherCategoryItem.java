package net.vvakame.zaim4j;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;
import net.vvakame.zaim4j.MoneyMode.MoneyModeConverter;

/**
 * Category item.
 * @author vvakame
 */
@JsonModel(decamelize = true, genToPackagePrivate = true, treatUnknownKeyAsError = true)
public class OtherCategoryItem {

	@JsonKey
	long id;

	@JsonKey(converter = MoneyModeConverter.class)
	MoneyMode mode;

	@JsonKey
	String name;


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
	 * @return the mode
	 * @category accessor
	 */
	public MoneyMode getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 * @category accessor
	 */
	public void setMode(MoneyMode mode) {
		this.mode = mode;
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
