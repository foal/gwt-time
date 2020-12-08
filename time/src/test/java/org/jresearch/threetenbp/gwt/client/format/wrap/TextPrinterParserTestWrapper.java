package org.jresearch.threetenbp.gwt.client.format.wrap;

import java.time.format.TextPrinterParsers;
import java.time.format.TextStyle;
import java.time.temporal.TemporalField;

public class TextPrinterParserTestWrapper implements PrinterParserTestWrapper {

    private final Object parser;

	public TextPrinterParserTestWrapper(TemporalField field, TextStyle textStyle, DateTimeTextProviderTestWrapper provider) {
		parser = TextPrinterParsers.create(field, textStyle, provider.getProvider());
	}

	@Override
	public boolean print(DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return TextPrinterParsers.print(parser, context, buf);
	}

	@Override
	public int parse(DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return TextPrinterParsers.parse(parser, context, text, position);
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
