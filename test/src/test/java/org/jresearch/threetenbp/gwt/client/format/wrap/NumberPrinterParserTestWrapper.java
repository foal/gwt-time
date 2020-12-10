package org.jresearch.threetenbp.gwt.client.format.wrap;

import java.time.format.NumberPrinterParsers;
import java.time.format.SignStyle;
import java.time.format.StringLiteralPrinterParsers;
import java.time.temporal.TemporalField;

public class NumberPrinterParserTestWrapper implements PrinterParserTestWrapper {

    private final Object parser;

	public NumberPrinterParserTestWrapper(TemporalField field, int minWidth, int maxWidth, SignStyle signStyle) {
		parser = NumberPrinterParsers.create(field, minWidth, maxWidth, signStyle);
	}

	public NumberPrinterParserTestWrapper(Object parser) {
		this.parser = parser;
	}

	@Override
	public boolean print(DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return NumberPrinterParsers.print(parser, context, buf);
	}

	@Override
	public int parse(DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return NumberPrinterParsers.parse(parser, context, text, position);
	}

	@Override
	public String toString() {
		return parser.toString();
	}

	public NumberPrinterParserTestWrapper withSubsequentWidth(int subsequentWidth) {
		return NumberPrinterParsers.withSubsequentWidth(parser, subsequentWidth);
	}

	@Override
	public Object getParser() {
		return parser;
	}

}
