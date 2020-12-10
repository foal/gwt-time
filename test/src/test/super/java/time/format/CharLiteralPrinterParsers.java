package java.time.format;

import java.time.format.DateTimeFormatterBuilder.CharLiteralPrinterParser;
import java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser;

import org.jresearch.threetenbp.gwt.client.format.wrap.DateTimeParseContextTestWrapper;
import org.jresearch.threetenbp.gwt.client.format.wrap.DateTimePrintContextTestWrapper;

public class CharLiteralPrinterParsers {

	public static Object create(char literal) {
		return new CharLiteralPrinterParser(literal);
	}

	public static boolean print(Object parser, DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return  ((DateTimePrinterParser)parser).print((DateTimePrintContext) context.getContext(), buf);
	}

	public static int parse(Object parser, DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return ((DateTimePrinterParser)parser).parse((DateTimeParseContext) context.getContext(), text, position);
	}

}
