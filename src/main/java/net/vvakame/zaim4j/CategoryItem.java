package net.vvakame.zaim4j;

import java.io.IOException;
import java.io.StringWriter;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;
import net.vvakame.zaim4j.Active.ActiveConverter;
import net.vvakame.zaim4j.MoneyMode.MoneyModeConverter;

/**
 * Category item.
 * @author vvakame
 */
@JsonModel(decamelize = true, genToPackagePrivate = true, treatUnknownKeyAsError = true)
public class CategoryItem {

	@JsonKey
	long id;

	@JsonKey(converter = MoneyModeConverter.class)
	MoneyMode mode;

	@JsonKey
	String name;

	@JsonKey
	String color;

	@JsonKey
	String image;

	@JsonKey
	long budget;

	@JsonKey
	long sort;

	@JsonKey(converter = ActiveConverter.class)
	Active active;

	@JsonKey
	long parentCategoryId;

	@JsonKey
	String modified;

	@JsonKey
	String calc;


	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		try {
			CategoryItemGen.encode(writer, this);
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
	 * @return the image
	 * @category accessor
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 * @category accessor
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the budget
	 * @category accessor
	 */
	public long getBudget() {
		return budget;
	}

	/**
	 * @param budget the budget to set
	 * @category accessor
	 */
	public void setBudget(long budget) {
		this.budget = budget;
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

	/**
	 * @return the parentCategoryId
	 * @category accessor
	 */
	public long getParentCategoryId() {
		return parentCategoryId;
	}

	/**
	 * @param parentCategoryId the parentCategoryId to set
	 * @category accessor
	 */
	public void setParentCategoryId(long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
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
	 * @return the calc
	 * @category accessor
	 */
	public String getCalc() {
		return calc;
	}

	/**
	 * @param calc the calc to set
	 * @category accessor
	 */
	public void setCalc(String calc) {
		this.calc = calc;
	}
}
