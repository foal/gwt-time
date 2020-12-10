package org.jresearch.threetenbp.gwt.time.client.zone.wrap;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.zone.ZoneOffsetTransitionRule.TimeDefinition;
import java.time.zone.ZoneRules;
import java.time.zone.ZoneRulesBuilders;

public class ZoneRulesBuilderTestWrapper {

	private final Object builder;

	public ZoneRulesBuilderTestWrapper() {
		builder = ZoneRulesBuilders.create();
	}

	public ZoneRules toRules(String string) {
		return ZoneRulesBuilders.toRules(builder, string);
	}

	public ZoneRulesBuilderTestWrapper addWindowForever(ZoneOffset standardOffset) {
		ZoneRulesBuilders.addWindowForever(builder, standardOffset);
		return this;
	}

	public ZoneRulesBuilderTestWrapper addWindow(ZoneOffset standardOffset, LocalDateTime until,
			TimeDefinition untilDefinition) {
		ZoneRulesBuilders.addWindow(builder, standardOffset, until, untilDefinition);
		return this;
	}

	public ZoneRulesBuilderTestWrapper addRuleToWindow(LocalDateTime transitionDateTime, TimeDefinition timeDefinition,
			int savingAmountSecs) {
		ZoneRulesBuilders.addRuleToWindow(builder, transitionDateTime, timeDefinition, savingAmountSecs);
		return this;
	}

	public ZoneRulesBuilderTestWrapper addRuleToWindow(int year, Month month, int dayOfMonthIndicator, LocalTime time,
			boolean timeEndOfDay, TimeDefinition timeDefinition, int savingAmountSecs) {
		ZoneRulesBuilders.addRuleToWindow(builder, year, month, dayOfMonthIndicator, time, timeEndOfDay, timeDefinition,
				savingAmountSecs);
		return this;
	}

	public ZoneRulesBuilderTestWrapper addRuleToWindow(int startYear, int endYear, Month month, int dayOfMonthIndicator,
			DayOfWeek dayOfWeek, LocalTime time, boolean timeEndOfDay, TimeDefinition timeDefinition,
			int savingAmountSecs) {
		ZoneRulesBuilders.addRuleToWindow(builder, startYear, endYear, month, dayOfMonthIndicator, dayOfWeek, time,
				timeEndOfDay, timeDefinition, savingAmountSecs);
		return this;
	}

	public ZoneRulesBuilderTestWrapper setFixedSavingsToWindow(int fixedSavingAmountSecs) {
		ZoneRulesBuilders.setFixedSavingsToWindow(builder, fixedSavingAmountSecs);
		return this;
	}

}
