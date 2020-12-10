package org.jresearch.threetenbp.gwt.time.client;

import org.jresearch.threetenbp.gwt.time.client.chrono.TestChronoLocalDate;
import org.jresearch.threetenbp.gwt.time.client.chrono.TestChronoLocalDateTime;
import org.jresearch.threetenbp.gwt.time.client.chrono.TestChronoZonedDateTime;
import org.jresearch.threetenbp.gwt.time.client.chrono.TestHijrahChronology;
import org.jresearch.threetenbp.gwt.time.client.chrono.TestIsoChronology;
import org.jresearch.threetenbp.gwt.time.client.chrono.TestJapaneseChronology;
import org.jresearch.threetenbp.gwt.time.client.chrono.TestMinguoChronology;
import org.jresearch.threetenbp.gwt.time.client.chrono.TestThaiBuddhistChronology;
import org.jresearch.threetenbp.gwt.time.client.format.TestDateTimeFormatter;
import org.jresearch.threetenbp.gwt.time.client.format.TestDateTimeFormatterBuilder;
import org.jresearch.threetenbp.gwt.time.client.format.TestDateTimeTextPrinting;
import org.jresearch.threetenbp.gwt.time.client.format.TestDecimalStyle;
import org.jresearch.threetenbp.gwt.time.client.temporal.TestChronoField;
import org.jresearch.threetenbp.gwt.time.client.temporal.TestChronoUnit;
import org.jresearch.threetenbp.gwt.time.client.temporal.TestIsoFields;
import org.jresearch.threetenbp.gwt.time.client.temporal.TestJulianFields;
import org.jresearch.threetenbp.gwt.time.client.temporal.TestMonthDay;
import org.jresearch.threetenbp.gwt.time.client.temporal.TestTemporalAdjusters;
import org.jresearch.threetenbp.gwt.time.client.temporal.TestValueRange;
import org.jresearch.threetenbp.gwt.time.client.temporal.TestYearMonth;
import org.jresearch.threetenbp.gwt.time.client.zone.TestFixedZoneRules;
import org.jresearch.threetenbp.gwt.time.client.zone.TestZoneOffsetTransition;
import org.jresearch.threetenbp.gwt.time.client.zone.TestZoneOffsetTransitionRule;
import org.jresearch.threetenbp.gwt.time.client.zone.TestZoneRulesBuilder;

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
