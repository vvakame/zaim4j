package net.vvakame.zaim4j;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

/**
 * ErrorResponse from Zaim API.
 * @author vvakame
 */
@JsonModel(decamelize = true, genToPackagePrivate = true, treatUnknownKeyAsError = true)
public class ErrorResponse {

	@JsonKey
	boolean error;

	@JsonKey
	String message;

	@JsonKey
	String extraMessage;


	/**
	 * @return the error
	 * @category accessor
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * @param error the error to set
	 * @category accessor
	 */
	public void setError(boolean error) {
		this.error = error;
	}

	/**
	 * @return the message
	 * @category accessor
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 * @category accessor
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the extraMessage
	 * @category accessor
	 */
	public String getExtraMessage() {
		return extraMessage;
	}

	/**
	 * @param extraMessage the extraMessage to set
	 * @category accessor
	 */
	public void setExtraMessage(String extraMessage) {
		this.extraMessage = extraMessage;
	}
}
