package java.time.format;

import java.time.ZoneId;
import java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser;
import java.time.format.DateTimeFormatterBuilder.ZoneIdPrinterParser;
import java.time.temporal.TemporalQuery;

import org.jresearch.threetenbp.gwt.client.format.wrap.DateTimeParseContextTestWrapper;
import org.jresearch.threetenbp.gwt.client.format.wrap.DateTimePrintContextTestWrapper;

public class ZoneIdPrinterParsers {

	public static Object create(TemporalQuery<ZoneId> query, String description) {
		return new ZoneIdPrinterParser(query, description);
	}

	public static boolean print(Object parser, DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return ((DateTimePrinterParser) parser).print((DateTimePrintContext) context.getContext(), buf);
	}

	public static int parse(Object parser, DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return ((DateTimePrinterParser) parser).parse((DateTimeParseContext) context.getContext(), text, position);
	}

}
