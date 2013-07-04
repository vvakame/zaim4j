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
 * Money mode.
 * @author vvakame
 */
public enum MoneyMode {
	/** Payment */
	Payment,
	/** Income */
	Income;

	static class MoneyModeConverter extends TokenConverter<MoneyMode> {

		static MoneyModeConverter getInstance() {
			return new MoneyModeConverter();
		}

		@Override
		public MoneyMode parse(JsonPullParser parser, OnJsonObjectAddListener listener)
				throws IOException, JsonFormatException {
			if (parser.lookAhead() == State.VALUE_NULL) {
				parser.discardValue();
				return null;
			}
			if (parser.getEventType() != State.VALUE_STRING) {
				throw new JsonFormatException("expect string value", parser);
			}
			String value = parser.getValueString();
			for (MoneyMode mode : MoneyMode.values()) {
				if (mode.name().equalsIgnoreCase(value)) {
					return mode;
				}
			}
			throw new JsonFormatException("unknown value:" + value);
		}

		@Override
		public void encodeNullToNull(Writer writer, MoneyMode obj) throws IOException {
			if (obj == null) {
				JsonUtil.put(writer, (String) null);
				return;
			}

			JsonUtil.put(writer, obj.name().toLowerCase());
		}
	}
}
