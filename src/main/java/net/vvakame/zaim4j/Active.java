package net.vvakame.zaim4j;

import java.io.IOException;
import java.io.Writer;

import net.vvakame.util.jsonpullparser.JsonFormatException;
import net.vvakame.util.jsonpullparser.JsonPullParser;
import net.vvakame.util.jsonpullparser.JsonPullParser.State;
import net.vvakame.util.jsonpullparser.util.JsonUtil;
import net.vvakame.util.jsonpullparser.util.OnJsonObjectAddListener;
import net.vvakame.util.jsonpullparser.util.TokenConverter;

/**
 * Active state.
 * @author vvakame
 */
public enum Active {
	/** Visible */
	Visible(1),
	/** Hidden */
	Hidden(-1);

	int value;


	Active(int value) {
		this.value = value;
	}

	/**
	 * get enum value.
	 * @return value
	 * @author vvakame
	 */
	public int getValue() {
		return value;
	}


	static class ActiveConverter extends TokenConverter<Active> {

		public static ActiveConverter getInstance() {
			return new ActiveConverter();
		}

		@Override
		public Active parse(JsonPullParser parser, OnJsonObjectAddListener listener)
				throws IOException, JsonFormatException {
			if (parser.lookAhead() == State.VALUE_NULL) {
				parser.discardValue();
				return null;
			}
			if (parser.getEventType() != State.VALUE_LONG) {
				throw new JsonFormatException("expect long value", parser);
			}
			long value = parser.getValueLong();
			for (Active active : Active.values()) {
				if (active.getValue() == value) {
					return active;
				}
			}
			throw new JsonFormatException("unknown value:" + value);
		}

		@Override
		public void encodeNullToNull(Writer writer, Active obj) throws IOException {
			if (obj == null) {
				JsonUtil.put(writer, (String) null);
				return;
			}

			JsonUtil.put(writer, obj.getValue());
		}
	}
}
