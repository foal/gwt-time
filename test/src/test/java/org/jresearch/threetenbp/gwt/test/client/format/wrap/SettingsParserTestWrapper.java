package org.jresearch.threetenbp.gwt.test.client.format.wrap;

import java.time.format.CharLiteralPrinterParsers;
import java.time.format.SettingsParsers;

public class SettingsParserTestWrapper implements PrinterParserTestWrapper {

    private final Object parser;

    public static SettingsParserTestWrapper insensitive() {
		return new SettingsParserTestWrapper(SettingsParsers.insensitive());
	}

    public static SettingsParserTestWrapper lenient() {
		return new SettingsParserTestWrapper(SettingsParsers.lenient());
	}

    public static SettingsParserTestWrapper sensitive() {
		return new SettingsParserTestWrapper(SettingsParsers.sensitive());
	}

    public static SettingsParserTestWrapper strict() {
		return new SettingsParserTestWrapper(SettingsParsers.strict());
	}

	private SettingsParserTestWrapper(Object parser) {
		this.parser = parser;
	}

	@Override
	public boolean print(DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return SettingsParsers.print(parser, context, buf);
	}

	@Override
	public int parse(DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return SettingsParsers.parse(parser, context, text, position);
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
