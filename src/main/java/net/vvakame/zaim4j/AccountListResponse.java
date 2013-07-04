package net.vvakame.zaim4j;

import java.util.List;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

/**
 * Account list response.
 * @author vvakame
 */
@JsonModel(decamelize = true, genToPackagePrivate = true, treatUnknownKeyAsError = true)
public class AccountListResponse {

	@JsonKey
	List<AccountItem> accounts;

	@JsonKey
	long requested;


	/**
	 * @return the accounts
	 * @category accessor
	 */
	public List<AccountItem> getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts the accounts to set
	 * @category accessor
	 */
	public void setAccounts(List<AccountItem> accounts) {
		this.accounts = accounts;
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
