package org.jresearch.threetenbp.gwt.client;

import org.jresearch.threetenbp.gwt.client.chrono.TestChronoLocalDate;
import org.jresearch.threetenbp.gwt.client.chrono.TestChronoLocalDateTime;
import org.jresearch.threetenbp.gwt.client.chrono.TestChronoZonedDateTime;
import org.jresearch.threetenbp.gwt.client.chrono.TestHijrahChronology;
import org.jresearch.threetenbp.gwt.client.chrono.TestIsoChronology;
import org.jresearch.threetenbp.gwt.client.chrono.TestJapaneseChronology;
import org.jresearch.threetenbp.gwt.client.chrono.TestMinguoChronology;
import org.jresearch.threetenbp.gwt.client.chrono.TestThaiBuddhistChronology;
import org.jresearch.threetenbp.gwt.client.format.TestDateTimeFormatter;
import org.jresearch.threetenbp.gwt.client.format.TestDateTimeFormatterBuilder;
import org.jresearch.threetenbp.gwt.client.format.TestDateTimeTextPrinting;
import org.jresearch.threetenbp.gwt.client.format.TestDecimalStyle;
import org.jresearch.threetenbp.gwt.client.temporal.TestChronoField;
import org.jresearch.threetenbp.gwt.client.temporal.TestChronoUnit;
import org.jresearch.threetenbp.gwt.client.temporal.TestIsoFields;
import org.jresearch.threetenbp.gwt.client.temporal.TestJulianFields;
import org.jresearch.threetenbp.gwt.client.temporal.TestMonthDay;
import org.jresearch.threetenbp.gwt.client.temporal.TestTemporalAdjusters;
import org.jresearch.threetenbp.gwt.client.temporal.TestValueRange;
import org.jresearch.threetenbp.gwt.client.temporal.TestYearMonth;
import org.jresearch.threetenbp.gwt.client.zone.TestFixedZoneRules;
import org.jresearch.threetenbp.gwt.client.zone.TestZoneOffsetTransition;
import org.jresearch.threetenbp.gwt.client.zone.TestZoneOffsetTransitionRule;
import org.jresearch.threetenbp.gwt.client.zone.TestZoneRulesBuilder;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;

public class GwtTreetenBpSuite {

	public static Test suite() {
		GWTTestSuite suite = new GWTTestSuite("Tests");

		// $JUnit-BEGIN$
		suite.addTestSuite(GwtTest.class);
		suite.addTestSuite(GwtSupportTest.class);
		suite.addTestSuite(TestDayOfWeek.class);
		suite.addTestSuite(TestDateTimes_implementation.class);
		suite.addTestSuite(TestDateTimeUtils.class);
		suite.addTestSuite(TestDuration.class);
		suite.addTestSuite(TestInstant.class);
		suite.addTestSuite(TestMonth.class);
		suite.addTestSuite(TestOffsetDateTime_instants.class);
		suite.addTestSuite(TestPeriod.class);
		suite.addTestSuite(TestZoneOffset.class);
		suite.addTestSuite(TestChronoLocalDate.class);
		suite.addTestSuite(TestChronoLocalDateTime.class);
		suite.addTestSuite(TestChronoZonedDateTime.class);
		suite.addTestSuite(TestHijrahChronology.class);
		suite.addTestSuite(TestIsoChronology.class);
		suite.addTestSuite(TestJapaneseChronology.class);
		suite.addTestSuite(TestMinguoChronology.class);
		suite.addTestSuite(TestThaiBuddhistChronology.class);
		suite.addTestSuite(TestChronoField.class);
		suite.addTestSuite(TestChronoUnit.class);
		suite.addTestSuite(TestIsoFields.class);
		suite.addTestSuite(TestJulianFields.class);
		suite.addTestSuite(TestMonthDay.class);
		suite.addTestSuite(TestTemporalAdjusters.class);
		suite.addTestSuite(TestValueRange.class);
		suite.addTestSuite(TestYearMonth.class);
		suite.addTestSuite(TestFixedZoneRules.class);
		suite.addTestSuite(TestZoneOffsetTransition.class);
		suite.addTestSuite(TestZoneOffsetTransitionRule.class);
		suite.addTestSuite(TestZoneRulesBuilder.class);
		suite.addTestSuite(TestDateTimeFormatter.class);
		suite.addTestSuite(TestDateTimeFormatterBuilder.class);
		suite.addTestSuite(TestDateTimeTextPrinting.class);
		suite.addTestSuite(TestDecimalStyle.class);
		// $JUnit-END$

		return suite;
	}
}
