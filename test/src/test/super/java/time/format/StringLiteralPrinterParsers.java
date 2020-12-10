package java.time.format;

import java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser;
import java.time.format.DateTimeFormatterBuilder.StringLiteralPrinterParser;

import org.jresearch.threetenbp.gwt.test.client.format.wrap.DateTimeParseContextTestWrapper;
import org.jresearch.threetenbp.gwt.test.client.format.wrap.DateTimePrintContextTestWrapper;

public class StringLiteralPrinterParsers {

	public static Object create(String literal) {
		return new StringLiteralPrinterParser(literal);
	}

	public static boolean print(Object parser, DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return ((DateTimePrinterParser) parser).print((DateTimePrintContext) context.getContext(), buf);
	}

	public static int parse(Object parser, DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return ((DateTimePrinterParser) parser).parse((DateTimeParseContext) context.getContext(), text, position);
	}

}
