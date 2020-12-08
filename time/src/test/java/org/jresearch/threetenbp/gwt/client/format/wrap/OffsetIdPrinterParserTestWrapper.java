package org.jresearch.threetenbp.gwt.client.format.wrap;

import java.time.format.OffsetIdPrinterParsers;

public class OffsetIdPrinterParserTestWrapper {

    private final Object parser;

	public OffsetIdPrinterParserTestWrapper(String noOffsetText, String pattern) {
		parser = OffsetIdPrinterParsers.create(noOffsetText, pattern);
	}

	public boolean print(DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return OffsetIdPrinterParsers.print(parser, context, buf);
	}

	public int parse(DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return OffsetIdPrinterParsers.parse(parser, context, text, position);
	}

	@Override
	public String toString() {
		return parser.toString();
	}

}
