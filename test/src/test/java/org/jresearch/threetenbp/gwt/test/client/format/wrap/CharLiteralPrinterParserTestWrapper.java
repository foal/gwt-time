package org.jresearch.threetenbp.gwt.test.client.format.wrap;

import java.time.format.CharLiteralPrinterParsers;

public class CharLiteralPrinterParserTestWrapper implements PrinterParserTestWrapper {

    private final Object parser;

	public CharLiteralPrinterParserTestWrapper(char literal) {
		parser = CharLiteralPrinterParsers.create(literal);
	}

	@Override
	public boolean print(DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return CharLiteralPrinterParsers.print(parser, context, buf);
	}

	@Override
	public int parse(DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return CharLiteralPrinterParsers.parse(parser, context, text, position);
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
