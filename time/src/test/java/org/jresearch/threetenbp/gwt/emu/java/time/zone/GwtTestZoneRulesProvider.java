package org.jresearch.threetenbp.gwt.emu.java.time.zone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import org.jresearch.threetenbp.gwt.emu.java.time.DayOfWeek;
import org.jresearch.threetenbp.gwt.emu.java.time.LocalDateTime;
import org.jresearch.threetenbp.gwt.emu.java.time.LocalTime;
import org.jresearch.threetenbp.gwt.emu.java.time.Month;
import org.jresearch.threetenbp.gwt.emu.java.time.ZoneOffset;
import org.jresearch.threetenbp.gwt.emu.java.time.zone.ZoneOffsetTransitionRule.TimeDefinition;

public final class GwtTestZoneRulesProvider extends ZoneRulesProvider {

	/**
	 * All the versions that are available.
	 */
	private final Map<String, ZoneRules> rules = new HashMap<>();

	public GwtTestZoneRulesProvider() {
		List<ZoneOffsetTransitionRule> r1 = new ArrayList<>();
		r1.add(ZoneOffsetTransitionRule.of(Month.MARCH, 25, DayOfWeek.SUNDAY, LocalTime.of(1, 0), false, TimeDefinition.UTC, ZoneOffset.of("+01:00"), ZoneOffset.of("+01:00"), ZoneOffset.of("+02:00")));
		r1.add(ZoneOffsetTransitionRule.of(Month.OCTOBER, 25, DayOfWeek.SUNDAY, LocalTime.of(1, 0), false, TimeDefinition.UTC, ZoneOffset.of("+01:00"), ZoneOffset.of("+02:00"), ZoneOffset.of("+01:00")));
		List<ZoneOffsetTransition> st1 = new ArrayList<>();
		st1.add(ZoneOffsetTransition.of(LocalDateTime.of(1911, 3, 11, 0, 0), ZoneOffset.ofHoursMinutesSeconds(0, 9, 21), ZoneOffset.of("+00:00")));
		st1.add(ZoneOffsetTransition.of(LocalDateTime.of(1940, 6, 14, 0, 0), ZoneOffset.of("+00:00"), ZoneOffset.of("+01:00")));
		st1.add(ZoneOffsetTransition.of(LocalDateTime.of(1944, 8, 25, 0, 0), ZoneOffset.of("+01:00"), ZoneOffset.of("+00:00")));
		st1.add(ZoneOffsetTransition.of(LocalDateTime.of(1945, 9, 16, 0, 0), ZoneOffset.of("+00:00"), ZoneOffset.of("+01:00")));
		List<ZoneOffsetTransition> t1 = new ArrayList<>();
		t1.add(ZoneOffsetTransition.of(LocalDateTime.of(1847, 12, 1, 0, 0), ZoneOffset.ofHoursMinutesSeconds(0, 9, 21), ZoneOffset.of("+00:00")));
		rules.put("Europe/Paris", ZoneRules.of(ZoneOffset.ofHoursMinutesSeconds(0, 9, 21), ZoneOffset.ofHoursMinutesSeconds(0, 9, 21), st1, t1, r1));

		List<ZoneOffsetTransitionRule> r2 = new ArrayList<>();
		r2.add(ZoneOffsetTransitionRule.of(Month.MARCH, 25, DayOfWeek.SUNDAY, LocalTime.of(1, 0), false, TimeDefinition.UTC, ZoneOffset.of("Z"), ZoneOffset.of("Z"), ZoneOffset.of("+01:00")));
		r2.add(ZoneOffsetTransitionRule.of(Month.OCTOBER, 25, DayOfWeek.SUNDAY, LocalTime.of(1, 0), false, TimeDefinition.UTC, ZoneOffset.of("Z"), ZoneOffset.of("+01:00"), ZoneOffset.of("Z")));
		List<ZoneOffsetTransition> st2 = new ArrayList<>();
		st2.add(ZoneOffsetTransition.of(LocalDateTime.of(1847, 12, 1, 0, 0), ZoneOffset.ofHoursMinutesSeconds(0, -1, -15), ZoneOffset.of("+00:00")));
		st2.add(ZoneOffsetTransition.of(LocalDateTime.of(1969, 1, 1, 0, 0), ZoneOffset.of("+00:00"), ZoneOffset.of("+01:00")));
		st2.add(ZoneOffsetTransition.of(LocalDateTime.of(1972, 1, 1, 0, 0), ZoneOffset.of("+01:00"), ZoneOffset.of("+00:00")));
		List<ZoneOffsetTransition> t2 = new ArrayList<>();
		t2.add(ZoneOffsetTransition.of(LocalDateTime.of(1847, 12, 1, 0, 0), ZoneOffset.ofHoursMinutesSeconds(0, -1, -15), ZoneOffset.of("+00:00")));
		t2.add(ZoneOffsetTransition.of(LocalDateTime.of(1916, 5, 21, 2, 0), ZoneOffset.of("+00:00"), ZoneOffset.of("+01:00")));
		t2.add(ZoneOffsetTransition.of(LocalDateTime.of(1916, 10, 1, 3, 0), ZoneOffset.of("+01:00"), ZoneOffset.of("+00:00")));
		t2.add(ZoneOffsetTransition.of(LocalDateTime.of(1989, 3, 31, 1, 0), ZoneOffset.of("+00:00"), ZoneOffset.of("+01:00")));
		t2.add(ZoneOffsetTransition.of(LocalDateTime.of(1989, 10, 28, 2, 0), ZoneOffset.of("+01:00"), ZoneOffset.of("+00:00")));
		t2.add(ZoneOffsetTransition.of(LocalDateTime.of(1990, 3, 25, 1, 0), ZoneOffset.of("+00:00"), ZoneOffset.of("+01:00")));
		t2.add(ZoneOffsetTransition.of(LocalDateTime.of(1990, 10, 28, 2, 0), ZoneOffset.of("+01:00"), ZoneOffset.of("+00:00")));
		t2.add(ZoneOffsetTransition.of(LocalDateTime.of(1991, 3, 31, 1, 0), ZoneOffset.of("+00:00"), ZoneOffset.of("+01:00")));
		t2.add(ZoneOffsetTransition.of(LocalDateTime.of(1991, 10, 27, 2, 0), ZoneOffset.of("+01:00"), ZoneOffset.of("+00:00")));
		t2.add(ZoneOffsetTransition.of(LocalDateTime.of(1992, 3, 29, 1, 0), ZoneOffset.of("+00:00"), ZoneOffset.of("+01:00")));
		t2.add(ZoneOffsetTransition.of(LocalDateTime.of(1992, 10, 25, 2, 0), ZoneOffset.of("+01:00"), ZoneOffset.of("+00:00")));
		t2.add(ZoneOffsetTransition.of(LocalDateTime.of(1993, 3, 28, 1, 0), ZoneOffset.of("+00:00"), ZoneOffset.of("+01:00")));
		t2.add(ZoneOffsetTransition.of(LocalDateTime.of(1993, 10, 24, 2, 0), ZoneOffset.of("+01:00"), ZoneOffset.of("+00:00")));
		t2.add(ZoneOffsetTransition.of(LocalDateTime.of(1994, 3, 27, 1, 0), ZoneOffset.of("+00:00"), ZoneOffset.of("+01:00")));
		t2.add(ZoneOffsetTransition.of(LocalDateTime.of(1994, 10, 23, 2, 0), ZoneOffset.of("+01:00"), ZoneOffset.of("+00:00")));
		t2.add(ZoneOffsetTransition.of(LocalDateTime.of(1995, 3, 26, 1, 0), ZoneOffset.of("+00:00"), ZoneOffset.of("+01:00")));
		t2.add(ZoneOffsetTransition.of(LocalDateTime.of(1995, 10, 22, 2, 0), ZoneOffset.of("+01:00"), ZoneOffset.of("+00:00")));
		t2.add(ZoneOffsetTransition.of(LocalDateTime.of(1996, 3, 31, 1, 0), ZoneOffset.of("+00:00"), ZoneOffset.of("+01:00")));
		t2.add(ZoneOffsetTransition.of(LocalDateTime.of(1996, 10, 27, 2, 0), ZoneOffset.of("+01:00"), ZoneOffset.of("+00:00")));
		t2.add(ZoneOffsetTransition.of(LocalDateTime.of(1997, 3, 30, 1, 0), ZoneOffset.of("+00:00"), ZoneOffset.of("+01:00")));
		t2.add(ZoneOffsetTransition.of(LocalDateTime.of(1997, 10, 26, 2, 0), ZoneOffset.of("+01:00"), ZoneOffset.of("+00:00")));
		rules.put("Europe/London", ZoneRules.of(ZoneOffset.ofHoursMinutesSeconds(0, -1, -15), ZoneOffset.ofHoursMinutesSeconds(0, -1, -15), st2, t2, r2));

		List<ZoneOffsetTransitionRule> r3 = new ArrayList<>();
		rules.put("Europe/Moscow", ZoneRules.of(ZoneOffset.of("+03:00"), ZoneOffset.of("+03:00"), new ArrayList<>(), new ArrayList<>(), r3));

		List<ZoneOffsetTransitionRule> r4 = new ArrayList<>();
		r4.add(ZoneOffsetTransitionRule.of(Month.MARCH, 24, DayOfWeek.SATURDAY, LocalTime.of(0, 0), false, TimeDefinition.WALL, ZoneOffset.of("+02:00"), ZoneOffset.of("+02:00"), ZoneOffset.of("+03:00")));
		r4.add(ZoneOffsetTransitionRule.of(Month.OCTOBER, 24, DayOfWeek.SATURDAY, LocalTime.of(1, 0), false, TimeDefinition.WALL, ZoneOffset.of("+02:00"), ZoneOffset.of("+03:00"), ZoneOffset.of("+02:00")));
		List<ZoneOffsetTransition> t4 = new ArrayList<>();
		t4.add(ZoneOffsetTransition.of(LocalDateTime.of(2007, 04, 01, 0, 0), ZoneOffset.of("+02:00"), ZoneOffset.of("+03:00")));
		rules.put("Asia/Gaza", ZoneRules.of(ZoneOffset.of("+02:00"), ZoneOffset.of("+02:00"), new ArrayList<>(), t4, r4));

		List<ZoneOffsetTransitionRule> r5 = new ArrayList<>();
		r5.add(ZoneOffsetTransitionRule.of(Month.MARCH, 8, DayOfWeek.SUNDAY, LocalTime.of(2, 0), false, TimeDefinition.WALL, ZoneOffset.of("-05:00"), ZoneOffset.of("-05:00"), ZoneOffset.of("-04:00")));
		r5.add(ZoneOffsetTransitionRule.of(Month.NOVEMBER, 1, DayOfWeek.SUNDAY, LocalTime.of(2, 0), false, TimeDefinition.WALL, ZoneOffset.of("-05:00"), ZoneOffset.of("-04:00"), ZoneOffset.of("-05:00")));
		List<ZoneOffsetTransition> st5 = new ArrayList<>();
		st5.add(ZoneOffsetTransition.of(LocalDateTime.of(1883, 11, 18, 0, 0), ZoneOffset.of("-04:56:02"), ZoneOffset.of("-05:00")));
		List<ZoneOffsetTransition> t5 = new ArrayList<>();
		t5.add(ZoneOffsetTransition.of(LocalDateTime.of(1900, 3, 11, 2, 0), ZoneOffset.of("-04:56:02"), ZoneOffset.of("-04:00")));
		rules.put("America/New_York", ZoneRules.of(ZoneOffset.of("-04:56:02"), ZoneOffset.of("-04:56:02"), st5, t5, r5));

		List<ZoneOffsetTransitionRule> r6 = new ArrayList<>();
		r6.add(ZoneOffsetTransitionRule.of(Month.MARCH, 25, DayOfWeek.SUNDAY, LocalTime.of(1, 0), false, TimeDefinition.UTC, ZoneOffset.of("+01:00"), ZoneOffset.of("+01:00"), ZoneOffset.of("+02:00")));
		r6.add(ZoneOffsetTransitionRule.of(Month.OCTOBER, 25, DayOfWeek.SUNDAY, LocalTime.of(1, 0), false, TimeDefinition.UTC, ZoneOffset.of("+01:00"), ZoneOffset.of("+02:00"), ZoneOffset.of("+01:00")));
		rules.put("Europe/Berlin", ZoneRules.of(ZoneOffset.of("Z"), ZoneOffset.of("Z"), new ArrayList<>(), new ArrayList<>(), r6));

		List<ZoneOffsetTransitionRule> r7 = new ArrayList<>();
		r7.add(ZoneOffsetTransitionRule.of(Month.MARCH, 25, DayOfWeek.SUNDAY, LocalTime.of(1, 0), false, TimeDefinition.UTC, ZoneOffset.of("+01:00"), ZoneOffset.of("+01:00"), ZoneOffset.of("+02:00")));
		r7.add(ZoneOffsetTransitionRule.of(Month.OCTOBER, 25, DayOfWeek.SUNDAY, LocalTime.of(1, 0), false, TimeDefinition.UTC, ZoneOffset.of("+01:00"), ZoneOffset.of("+02:00"), ZoneOffset.of("+01:00")));
		rules.put("Europe/Madrid", ZoneRules.of(ZoneOffset.of("Z"), ZoneOffset.of("Z"), new ArrayList<>(), new ArrayList<>(), r7));

		List<ZoneOffsetTransitionRule> r8 = new ArrayList<>();
		r8.add(ZoneOffsetTransitionRule.of(Month.MARCH, 25, DayOfWeek.SUNDAY, LocalTime.of(1, 0), false, TimeDefinition.UTC, ZoneOffset.of("Z"), ZoneOffset.of("Z"), ZoneOffset.of("+01:00")));
		r8.add(ZoneOffsetTransitionRule.of(Month.OCTOBER, 25, DayOfWeek.SUNDAY, LocalTime.of(1, 0), false, TimeDefinition.UTC, ZoneOffset.of("Z"), ZoneOffset.of("+01:00"), ZoneOffset.of("Z")));
		List<ZoneOffsetTransition> st8 = new ArrayList<>();
		st8.add(ZoneOffsetTransition.of(LocalDateTime.of(1880, 8, 2, 0, 0), ZoneOffset.ofHoursMinutes(0, -25), ZoneOffset.ofHoursMinutesSeconds(0, -25, -21)));
		st8.add(ZoneOffsetTransition.of(LocalDateTime.of(1916, 10, 1, 2, 0), ZoneOffset.ofHoursMinutesSeconds(0, -25, -21), ZoneOffset.of("+00:00")));
		st8.add(ZoneOffsetTransition.of(LocalDateTime.of(1968, 10, 27, 2, 0), ZoneOffset.of("+00:00"), ZoneOffset.of("+01:00")));
		st8.add(ZoneOffsetTransition.of(LocalDateTime.of(1971, 10, 31, 2, 0), ZoneOffset.of("+01:00"), ZoneOffset.of("+00:00")));
		List<ZoneOffsetTransition> t8 = new ArrayList<>();
		t8.add(ZoneOffsetTransition.of(LocalDateTime.of(1959, 3, 27, 2, 0), ZoneOffset.of("+00:00"), ZoneOffset.of("+01:00")));
		t8.add(ZoneOffsetTransition.of(LocalDateTime.of(1959, 10, 27, 2, 0), ZoneOffset.of("+01:00"), ZoneOffset.of("+00:00")));
		t8.add(ZoneOffsetTransition.of(LocalDateTime.of(1960, 3, 27, 2, 0), ZoneOffset.of("+00:00"), ZoneOffset.of("+01:00")));
		t8.add(ZoneOffsetTransition.of(LocalDateTime.of(1960, 10, 27, 2, 0), ZoneOffset.of("+01:00"), ZoneOffset.of("+00:00")));
		t8.add(ZoneOffsetTransition.of(LocalDateTime.of(1968, 10, 27, 2, 0), ZoneOffset.of("+00:00"), ZoneOffset.of("+01:00")));
		t8.add(ZoneOffsetTransition.of(LocalDateTime.of(1971, 10, 31, 2, 0), ZoneOffset.of("+01:00"), ZoneOffset.of("+00:00")));
		rules.put("Europe/Dublin", ZoneRules.of(ZoneOffset.ofHoursMinutes(0, -25), ZoneOffset.ofHoursMinutes(0, -25), st8, t8, r8));

		List<ZoneOffsetTransition> t9 = new ArrayList<>();
		t9.add(ZoneOffsetTransition.of(LocalDateTime.of(1956, 04, 01, 1, 0), ZoneOffset.of("+00:00"), ZoneOffset.of("+01:00")));
		t9.add(ZoneOffsetTransition.of(LocalDateTime.of(1956, 11, 01, 1, 0), ZoneOffset.of("+01:00"), ZoneOffset.of("+00:00")));
		rules.put("Asia/Kathmandu", ZoneRules.of(ZoneOffset.ofHoursMinutes(5, 45), ZoneOffset.ofHoursMinutes(5, 45), new ArrayList<>(), t9, new ArrayList<>()));

		rules.put("Etc/GMT", ZoneRules.of(ZoneOffset.UTC));

		List<ZoneOffsetTransitionRule> r10 = new ArrayList<>();
		rules.put("America/Denver", ZoneRules.of(ZoneOffset.of("-07:00"), ZoneOffset.of("-07:00"), new ArrayList<>(), new ArrayList<>(), r10));
	}

	// -----------------------------------------------------------------------
	@Override
	protected Set<String> provideZoneIds() {
		return new HashSet<>(rules.keySet());
	}

	@Override
	protected ZoneRules provideRules(String zoneId, boolean forCaching) {
		Objects.requireNonNull(zoneId, "zoneId");
		ZoneRules result = rules.get(zoneId);
		if (result == null) {
			throw new ZoneRulesException("Unknown time-zone ID: " + zoneId);
		}
		return result;
	}

	@Override
	protected NavigableMap<String, ZoneRules> provideVersions(String zoneId) {
		TreeMap<String, ZoneRules> map = new TreeMap<>();
		map.put("test", provideRules(zoneId, false));
		return map;
	}

	@Override
	public String toString() {
		return "GWTTest";
	}

}
