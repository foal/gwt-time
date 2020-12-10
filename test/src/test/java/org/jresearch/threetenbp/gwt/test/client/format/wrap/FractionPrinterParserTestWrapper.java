package org.jresearch.threetenbp.gwt.test.client.format.wrap;

import java.time.format.FractionPrinterParsers;
import java.time.temporal.TemporalField;

public class FractionPrinterParserTestWrapper {

    private final Object parser;

	public FractionPrinterParserTestWrapper(TemporalField field, int minWidth, int maxWidth, boolean decimalPoint) {
		parser = FractionPrinterParsers.create(field, minWidth, maxWidth, decimalPoint);
	}

	public boolean print(DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return FractionPrinterParsers.print(parser, context, buf);
	}

	public int parse(DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return FractionPrinterParsers.parse(parser, context, text, position);
	}

	@Override
	public String toString() {
		return parser.toString();
	}

}
