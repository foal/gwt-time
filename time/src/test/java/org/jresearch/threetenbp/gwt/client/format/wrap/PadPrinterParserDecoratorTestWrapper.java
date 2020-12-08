package org.jresearch.threetenbp.gwt.client.format.wrap;

import java.time.format.CharLiteralPrinterParsers;
import java.time.format.PadPrinterParserDecorators;

public class PadPrinterParserDecoratorTestWrapper {

	private final Object parser;

	public PadPrinterParserDecoratorTestWrapper(PrinterParserTestWrapper printerParser, int padWidth, char padChar) {
		parser = PadPrinterParserDecorators.create(printerParser.getParser(), padWidth, padChar);
	}

	public boolean print(DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return PadPrinterParserDecorators.print(parser, context, buf);
	}

	public int parse(DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return PadPrinterParserDecorators.parse(parser, context, text, position);
	}

	@Override
	public String toString() {
		return parser.toString();
	}

}
