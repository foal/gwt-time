package org.jresearch.threetenbp.gwt.client.format.wrap;

import java.time.format.OffsetIdPrinterParsers;
import java.time.format.StringLiteralPrinterParsers;

public class StringLiteralPrinterParserTestWrapper implements PrinterParserTestWrapper {

    private final Object parser;

	public StringLiteralPrinterParserTestWrapper(String literal) {
		parser = StringLiteralPrinterParsers.create(literal);
	}

	@Override
	public boolean print(DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return StringLiteralPrinterParsers.print(parser, context, buf);
	}

	@Override
	public int parse(DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return StringLiteralPrinterParsers.parse(parser, context, text, position);
	}

	@Override
	public String toString() {
		return parser.toString();
	}

	@Override
	public Object getParser() {
		return parser;
	}

}
