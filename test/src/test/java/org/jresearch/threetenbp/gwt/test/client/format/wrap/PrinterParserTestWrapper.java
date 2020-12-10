package org.jresearch.threetenbp.gwt.test.client.format.wrap;

import java.time.format.FractionPrinterParsers;
import java.time.temporal.TemporalField;

public interface PrinterParserTestWrapper {

	public Object getParser();

	public boolean print(DateTimePrintContextTestWrapper context, StringBuilder buf);

	public int parse(DateTimeParseContextTestWrapper context, CharSequence text, int position);

}
