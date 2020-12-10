package org.jresearch.threetenbp.gwt.client.format.wrap;

import java.time.ZoneId;
import java.time.format.Parseds;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQuery;
import java.time.temporal.ValueRange;
import java.util.Map;
import java.util.Set;

public class ParsedTestWrapper {

    private final Object parsed;

	public ParsedTestWrapper(Object parsed) {
		this.parsed = parsed;
	}

	public ValueRange range(TemporalField field) {
		return Parseds.range(parsed, field);
	}

	public boolean isSupported(TemporalField field) {
		return Parseds.isSupported(parsed, field);
	}

	public int get(TemporalField field) {
		return Parseds.get(parsed, field);
	}

	public long getLong(TemporalField field) {
		return Parseds.getLong(parsed, field);
	}

	public <R> R query(TemporalQuery<R> query) {
		return Parseds.query(parsed, query);
	}

	public ZoneId zone() {
		return Parseds.zone(parsed);
	}

	public Map<TemporalField, Long> fieldValues() {
		return Parseds.fieldValues(parsed);
	}

}
