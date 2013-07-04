package net.vvakame.zaim4j;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

/**
 * Genre item.
 * @author vvakame
 */
@JsonModel(decamelize = true, genToPackagePrivate = true, treatUnknownKeyAsError = true)
public class GenreItem {

	@JsonKey
	long id;

	@JsonKey
	String name;

	@JsonKey
	MoneyMode mode;

	@JsonKey
	long sort;

	@JsonKey
	long parentGenreId;

	@JsonKey
	long categoryId;

	@JsonKey
	long editFlag;

	@JsonKey
	long active;

	@JsonKey
	String modified;


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
	 * @return the parentGenreId
	 * @category accessor
	 */
	public long getParentGenreId() {
		return parentGenreId;
	}

	/**
	 * @param parentGenreId the parentGenreId to set
	 * @category accessor
	 */
	public void setParentGenreId(long parentGenreId) {
		this.parentGenreId = parentGenreId;
	}

	/**
	 * @return the categoryId
	 * @category accessor
	 */
	public long getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 * @category accessor
	 */
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the editFlag
	 * @category accessor
	 */
	public long getEditFlag() {
		return editFlag;
	}

	/**
	 * @param editFlag the editFlag to set
	 * @category accessor
	 */
	public void setEditFlag(long editFlag) {
		this.editFlag = editFlag;
	}

	/**
	 * @return the active
	 * @category accessor
	 */
	public long getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 * @category accessor
	 */
	public void setActive(long active) {
		this.active = active;
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
}
