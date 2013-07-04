package net.vvakame.zaim4j;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

/**
 * User in money response.
 * @author vvakame
 */
@JsonModel(decamelize = true, genToPackagePrivate = true, treatUnknownKeyAsError = true)
public class MoneyUser {

	@JsonKey
	int inputCount;

	@JsonKey
	int repeatCount;

	@JsonKey
	int dayCount;

	@JsonKey
	String dataModified;


	/**
	 * @return the inputCount
	 * @category accessor
	 */
	public int getInputCount() {
		return inputCount;
	}

	/**
	 * @param inputCount the inputCount to set
	 * @category accessor
	 */
	public void setInputCount(int inputCount) {
		this.inputCount = inputCount;
	}

	/**
	 * @return the repeatCount
	 * @category accessor
	 */
	public int getRepeatCount() {
		return repeatCount;
	}

	/**
	 * @param repeatCount the repeatCount to set
	 * @category accessor
	 */
	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	/**
	 * @return the dayCount
	 * @category accessor
	 */
	public int getDayCount() {
		return dayCount;
	}

	/**
	 * @param dayCount the dayCount to set
	 * @category accessor
	 */
	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}

	/**
	 * @return the dataModified
	 * @category accessor
	 */
	public String getDataModified() {
		return dataModified;
	}

	/**
	 * @param dataModified the dataModified to set
	 * @category accessor
	 */
	public void setDataModified(String dataModified) {
		this.dataModified = dataModified;
	}
}
