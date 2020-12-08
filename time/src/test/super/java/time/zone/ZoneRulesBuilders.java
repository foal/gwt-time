package java.time.zone;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.zone.ZoneOffsetTransitionRule.TimeDefinition;

public class ZoneRulesBuilders {

	public static Object create() {
		return new ZoneRulesBuilder();
	}

	public static ZoneRules toRules(Object builder, String zoneId) {
		return ((ZoneRulesBuilder) builder).toRules(zoneId);
	}

	public static Object addWindowForever(Object builder, ZoneOffset standardOffset) {
		return ((ZoneRulesBuilder) builder).addWindowForever(standardOffset);
	}

	public static Object addWindow(Object builder, ZoneOffset standardOffset, LocalDateTime until,
			TimeDefinition untilDefinition) {
		return ((ZoneRulesBuilder) builder).addWindow(standardOffset, until, untilDefinition);
	}

	public static Object addRuleToWindow(Object builder, LocalDateTime transitionDateTime,
			TimeDefinition timeDefinition, int savingAmountSecs) {
		return ((ZoneRulesBuilder) builder).addRuleToWindow(transitionDateTime, timeDefinition, savingAmountSecs);
	}

	public static Object addRuleToWindow(Object builder, int year, Month month, int dayOfMonthIndicator, LocalTime time,
			boolean timeEndOfDay, TimeDefinition timeDefinition, int savingAmountSecs) {
		return ((ZoneRulesBuilder) builder).addRuleToWindow(year, month, dayOfMonthIndicator, time, timeEndOfDay,
				timeDefinition, savingAmountSecs);
	}

	public static Object addRuleToWindow(Object builder, int startYear, int endYear, Month month,
			int dayOfMonthIndicator, DayOfWeek dayOfWeek, LocalTime time, boolean timeEndOfDay,
			TimeDefinition timeDefinition, int savingAmountSecs) {
		return ((ZoneRulesBuilder) builder).addRuleToWindow(startYear, endYear, month, dayOfMonthIndicator, dayOfWeek,
				time, timeEndOfDay, timeDefinition, savingAmountSecs);
	}

	public static Object setFixedSavingsToWindow(Object builder, int fixedSavingAmountSecs) {
		return ((ZoneRulesBuilder) builder).setFixedSavingsToWindow(fixedSavingAmountSecs);
	}

}
