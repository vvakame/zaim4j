package net.vvakame.zaim4j;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

/**
 * Account list response.
 * @author vvakame
 */
@JsonModel(decamelize = true, genToPackagePrivate = true, treatUnknownKeyAsError = true)
public class OtherAccountListResponse {

	@JsonKey("categories")
	List<OtherAccountItem> accounts;

	@JsonKey
	long requested;


	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		try {
			OtherAccountListResponseGen.encode(writer, this);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return writer.toString();
	}

	/**
	 * @return the accounts
	 * @category accessor
	 */
	public List<OtherAccountItem> getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts the accounts to set
	 * @category accessor
	 */
	public void setAccounts(List<OtherAccountItem> accounts) {
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
