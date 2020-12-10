package org.jresearch.threetenbp.gwt.client.format.wrap;

import java.time.ZoneId;
import java.time.format.CharLiteralPrinterParsers;
import java.time.format.ZoneIdPrinterParsers;
import java.time.temporal.TemporalQuery;

public class ZoneIdPrinterParserTestWrapper implements PrinterParserTestWrapper {

	private final Object parser;

	public ZoneIdPrinterParserTestWrapper(TemporalQuery<ZoneId> query, String description) {
		parser = ZoneIdPrinterParsers.create(query, description);
	}

	@Override
	public boolean print(DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return ZoneIdPrinterParsers.print(parser, context, buf);
	}

	@Override
	public int parse(DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return ZoneIdPrinterParsers.parse(parser, context, text, position);
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
