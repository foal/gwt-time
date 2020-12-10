package java.time.format;

import java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser;
import java.time.format.DateTimeFormatterBuilder.OffsetIdPrinterParser;

import org.jresearch.threetenbp.gwt.client.format.wrap.DateTimeParseContextTestWrapper;
import org.jresearch.threetenbp.gwt.client.format.wrap.DateTimePrintContextTestWrapper;

public class OffsetIdPrinterParsers {

	public static Object create(String noOffsetText, String pattern) {
		return new OffsetIdPrinterParser(noOffsetText, pattern);
	}

	public static boolean print(Object parser, DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return  ((DateTimePrinterParser)parser).print((DateTimePrintContext) context.getContext(), buf);
	}

	public static int parse(Object parser, DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return ((DateTimePrinterParser)parser).parse((DateTimeParseContext) context.getContext(), text, position);
	}

}
