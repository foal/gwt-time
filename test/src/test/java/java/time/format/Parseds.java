package java.time.format;

import java.time.ZoneId;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQuery;
import java.time.temporal.ValueRange;
import java.util.Map;

//JDK version, see for real code in /src/test/super
public class Parseds {

	public static ValueRange range(Object parsed, TemporalField field) {
		return null;
	}

	public static boolean isSupported(Object parsed, TemporalField field) {
		return false;
	}

	public static int get(Object parsed, TemporalField field) {
		return 0;
	}

	public static long getLong(Object parsed, TemporalField field) {
		return 0;
	}

	public static <R> R query(Object parsed, TemporalQuery<R> query) {
		return null;
	}

	public static ZoneId zone(Object parsed) {
		return null;
	}

	public static Map<TemporalField, Long> fieldValues(Object parsed) {
		return null;
	}

}
