package org.jresearch.threetenbp.gwt.client.format.wrap;

import java.time.chrono.IsoChronology;
import java.time.format.DateTimeBuilders;
import java.time.format.ResolverStyle;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQuery;
import java.util.Set;

public class DateTimeBuilderTestWrapper implements TemporalAccessor {

    private final TemporalAccessor builder;

	public DateTimeBuilderTestWrapper(TemporalField field, long value) {
		builder = DateTimeBuilders.create(field, value);
	}

	@Override
	public boolean isSupported(TemporalField field) {
		return builder.isSupported(field);
	}

	@Override
	public long getLong(TemporalField field) {
		return builder.getLong(field);
	}

	public DateTimeBuilderTestWrapper addFieldValue(TemporalField field, long value) {
		DateTimeBuilders.addFieldValue(builder, field, value);
		return this;
	}

	public DateTimeBuilderTestWrapper resolve(ResolverStyle resolverStyle, Set<TemporalField> resolverFields) {
		DateTimeBuilders.resolve(builder, resolverStyle, resolverFields);
		return this;
	}

	public <R> R build(TemporalQuery<R> query) {
		return DateTimeBuilders.build(builder, query);
	}

	public void chrono(IsoChronology chrono) {
		DateTimeBuilders.chrono(builder, chrono);
	}

}
