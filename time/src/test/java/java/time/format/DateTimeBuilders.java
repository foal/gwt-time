package java.time.format;

import java.time.chrono.IsoChronology;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQuery;
import java.util.Set;

//JDK version, see for real code in /src/test/super
public class DateTimeBuilders {

	public static TemporalAccessor create(TemporalField field, long value) {
		return null;
	}

	public static void addFieldValue(TemporalAccessor builder, TemporalField field, long value) {
	}

	public static void resolve(TemporalAccessor builder, ResolverStyle resolverStyle,
			Set<TemporalField> resolverFields) {
	}

	public static <R> R build(TemporalAccessor builder, TemporalQuery<R> query) {
		return null;
	}

	public static void chrono(TemporalAccessor builder, IsoChronology chrono) {
	}

}
