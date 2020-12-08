package java.time.format;

import java.time.chrono.IsoChronology;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQuery;
import java.util.Set;

public class DateTimeBuilders {

	public static TemporalAccessor create(TemporalField field, long value) {
		return new DateTimeBuilder(field, value);
	}

	public static TemporalAccessor addFieldValue(TemporalAccessor builder, TemporalField field, long value) {
		return ((DateTimeBuilder) builder).addFieldValue(field, value);
	}

	public static TemporalAccessor resolve(TemporalAccessor builder, ResolverStyle resolverStyle,
			Set<TemporalField> resolverFields) {
		return ((DateTimeBuilder) builder).resolve(resolverStyle, resolverFields);
	}

	public static <R> R build(TemporalAccessor builder, TemporalQuery<R> type) {
		return ((DateTimeBuilder) builder).build(type);
	}

	public static void chrono(TemporalAccessor builder, IsoChronology chrono) {
		((DateTimeBuilder) builder).chrono = chrono;
	}

	public static long getLong(TemporalAccessor builder, TemporalField field) {
		return ((DateTimeBuilder) builder).getLong(field);
	}

}
