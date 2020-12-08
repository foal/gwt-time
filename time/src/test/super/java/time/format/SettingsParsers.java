package java.time.format;

import java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser;
import java.time.format.DateTimeFormatterBuilder.SettingsParser;

import org.jresearch.threetenbp.gwt.client.format.wrap.DateTimeParseContextTestWrapper;
import org.jresearch.threetenbp.gwt.client.format.wrap.DateTimePrintContextTestWrapper;

public class SettingsParsers {

	public static Object insensitive() {
		return SettingsParser.INSENSITIVE;
	}

	public static Object lenient() {
		return SettingsParser.LENIENT;
	}

	public static Object sensitive() {
		return SettingsParser.SENSITIVE;
	}

	public static Object strict() {
		return SettingsParser.STRICT;
	}

	public static boolean print(Object parser, DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return  ((DateTimePrinterParser)parser).print((DateTimePrintContext) context.getContext(), buf);
	}

	public static int parse(Object parser, DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return ((DateTimePrinterParser)parser).parse((DateTimeParseContext) context.getContext(), text, position);
	}

}
