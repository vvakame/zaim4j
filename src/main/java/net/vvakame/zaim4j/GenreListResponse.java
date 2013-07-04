package net.vvakame.zaim4j;

import java.util.List;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

/**
 * Genre list response.
 * @author vvakame
 */
@JsonModel(decamelize = true, genToPackagePrivate = true, treatUnknownKeyAsError = true)
public class GenreListResponse {

	@JsonKey
	List<GenreItem> genres;

	@JsonKey
	long requested;


	/**
	 * @return the genres
	 * @category accessor
	 */
	public List<GenreItem> getGenres() {
		return genres;
	}

	/**
	 * @param genres the genres to set
	 * @category accessor
	 */
	public void setGenres(List<GenreItem> genres) {
		this.genres = genres;
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
