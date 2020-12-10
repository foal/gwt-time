package java.time.format;

import java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser;
import java.time.format.DateTimeFormatterBuilder.TextPrinterParser;
import java.time.temporal.TemporalField;

import org.jresearch.threetenbp.gwt.client.format.wrap.DateTimeParseContextTestWrapper;
import org.jresearch.threetenbp.gwt.client.format.wrap.DateTimePrintContextTestWrapper;

public class TextPrinterParsers {

	public static Object create(TemporalField field, TextStyle textStyle, Object provider) {
		return new TextPrinterParser(field, textStyle, (DateTimeTextProvider) provider);
	}

	public static boolean print(Object parser, DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return ((DateTimePrinterParser) parser).print((DateTimePrintContext) context.getContext(), buf);
	}

	public static int parse(Object parser, DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return ((DateTimePrinterParser) parser).parse((DateTimeParseContext) context.getContext(), text, position);
	}

}
