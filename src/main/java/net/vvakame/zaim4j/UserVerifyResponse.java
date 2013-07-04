package net.vvakame.zaim4j;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

/**
 * User verify response.
 * @see Zaim.User.Verify#execute(net.vvakame.zaim4j.Zaim.ZaimListener)
 * @author vvakame
 */
@JsonModel(decamelize = true, genToPackagePrivate = true, treatUnknownKeyAsError = true)
public class UserVerifyResponse {

	@JsonKey
	UserItem me;

	@JsonKey
	long requested;


	/**
	 * @return the me
	 * @category accessor
	 */
	public UserItem getMe() {
		return me;
	}

	/**
	 * @param me the me to set
	 * @category accessor
	 */
	public void setMe(UserItem me) {
		this.me = me;
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
