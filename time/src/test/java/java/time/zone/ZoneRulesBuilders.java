package java.time.zone;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.zone.ZoneOffsetTransitionRule.TimeDefinition;

//JDK version, see for real code in /src/test/super
public class ZoneRulesBuilders {

	public static Object create() {
		return null;
	}

	public static ZoneRules toRules(Object builder, String zoneId) {
		return null;
	}

	public static Object addWindowForever(Object builder, ZoneOffset standardOffset) {
		return null;
	}

	public static Object addWindow(Object builder, ZoneOffset standardOffset, LocalDateTime until,
			TimeDefinition untilDefinition) {
		return null;
	}

	public static Object addRuleToWindow(Object builder, LocalDateTime transitionDateTime,
			TimeDefinition timeDefinition, int savingAmountSecs) {
		return null;
	}

	public static Object addRuleToWindow(Object builder, int year, Month month, int dayOfMonthIndicator, LocalTime time,
			boolean timeEndOfDay, TimeDefinition timeDefinition, int savingAmountSecs) {
		return null;
	}

	public static Object addRuleToWindow(Object builder, int startYear, int endYear, Month month,
			int dayOfMonthIndicator, DayOfWeek dayOfWeek, LocalTime time, boolean timeEndOfDay,
			TimeDefinition timeDefinition, int savingAmountSecs) {
		return null;
	}

	public static Object setFixedSavingsToWindow(Object builder, int fixedSavingAmountSecs) {
		return null;
	}

}
