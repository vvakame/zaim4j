package net.vvakame.zaim4j;

import java.io.IOException;
import java.io.StringWriter;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;
import net.vvakame.zaim4j.Active.ActiveConverter;

/**
 * Genre item.
 * @author vvakame
 */
@JsonModel(decamelize = true, genToPackagePrivate = true, treatUnknownKeyAsError = true)
public class GenreItem extends OtherGenreItem {

	@JsonKey
	long sort;

	@JsonKey
	long parentGenreId;

	@JsonKey(converter = ActiveConverter.class)
	Active active;

	@JsonKey
	String modified;


	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		try {
			GenreItemGen.encode(writer, this);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return writer.toString();
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
