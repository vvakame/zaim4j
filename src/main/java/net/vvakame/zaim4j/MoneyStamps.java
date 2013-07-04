package net.vvakame.zaim4j;

import java.io.IOException;
import java.io.StringWriter;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

/**
 * Stamps.
 * @author vvakame
 */
@JsonModel(decamelize = true, genToPackagePrivate = true, treatUnknownKeyAsError = true)
public class MoneyStamps {

	@JsonKey
	MoneyStamp kiriban;

	@JsonKey
	MoneyStamp repeat;

	@JsonKey
	MoneyStamp first;


	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		try {
			MoneyStampsGen.encode(writer, this);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return writer.toString();
	}

	/**
	 * @return the kiriban
	 * @category accessor
	 */
	public MoneyStamp getKiriban() {
		return kiriban;
	}

	/**
	 * @param kiriban the kiriban to set
	 * @category accessor
	 */
	public void setKiriban(MoneyStamp kiriban) {
		this.kiriban = kiriban;
	}

	/**
	 * @return the repeat
	 * @category accessor
	 */
	public MoneyStamp getRepeat() {
		return repeat;
	}

	/**
	 * @param repeat the repeat to set
	 * @category accessor
	 */
	public void setRepeat(MoneyStamp repeat) {
		this.repeat = repeat;
	}

	/**
	 * @return the first
	 * @category accessor
	 */
	public MoneyStamp getFirst() {
		return first;
	}

	/**
	 * @param first the first to set
	 * @category accessor
	 */
	public void setFirst(MoneyStamp first) {
		this.first = first;
	}
}
