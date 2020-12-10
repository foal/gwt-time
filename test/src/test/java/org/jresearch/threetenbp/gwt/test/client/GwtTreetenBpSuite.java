package org.jresearch.threetenbp.gwt.test.client;

import org.jresearch.threetenbp.gwt.test.client.format.TestCharLiteralParser;
import org.jresearch.threetenbp.gwt.test.client.format.TestCharLiteralPrinter;
import org.jresearch.threetenbp.gwt.test.client.format.TestDateTimeBuilderCombinations;
import org.jresearch.threetenbp.gwt.test.client.format.TestDateTimeFormatters;
import org.jresearch.threetenbp.gwt.test.client.format.TestDateTimeParsing;
import org.jresearch.threetenbp.gwt.test.client.format.TestFractionPrinterParser;
import org.jresearch.threetenbp.gwt.test.client.format.TestNumberParser;
import org.jresearch.threetenbp.gwt.test.client.format.TestNumberPrinter;
import org.jresearch.threetenbp.gwt.test.client.format.TestPadParserDecorator;
import org.jresearch.threetenbp.gwt.test.client.format.TestPadPrinterDecorator;
import org.jresearch.threetenbp.gwt.test.client.format.TestReducedParser;
import org.jresearch.threetenbp.gwt.test.client.format.TestReducedPrinter;
import org.jresearch.threetenbp.gwt.test.client.format.TestSettingsParser;
import org.jresearch.threetenbp.gwt.test.client.format.TestStringLiteralParser;
import org.jresearch.threetenbp.gwt.test.client.format.TestStringLiteralPrinter;
import org.jresearch.threetenbp.gwt.test.client.format.TestTextParser;
import org.jresearch.threetenbp.gwt.test.client.format.TestTextPrinter;
import org.jresearch.threetenbp.gwt.test.client.format.TestZoneIdParser;
import org.jresearch.threetenbp.gwt.test.client.format.TestZoneOffsetParser;
import org.jresearch.threetenbp.gwt.test.client.format.TestZoneOffsetPrinter;
import org.jresearch.threetenbp.gwt.test.client.temporal.TestYear;
import org.jresearch.threetenbp.gwt.test.client.zone.TestStandardZoneRules;
import org.jresearch.threetenbp.gwt.test.client.zone.TestZoneRulesProvider;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;

public class GwtTreetenBpSuite {

	public static Test suite() {
		GWTTestSuite suite = new GWTTestSuite("Tests with TZDB provider");

		// $JUnit-BEGIN$
		suite.addTestSuite(TestClock.class);
		suite.addTestSuite(TestClock_Fixed.class);
		suite.addTestSuite(TestClock_Offset.class);
		suite.addTestSuite(TestClock_System.class);
		suite.addTestSuite(TestClock_Tick.class);
		suite.addTestSuite(TestLocalDate.class);
		suite.addTestSuite(TestLocalDateTime.class);
		suite.addTestSuite(TestLocalTime.class);
		suite.addTestSuite(TestOffsetDateTime.class);
		suite.addTestSuite(TestOffsetTime.class);
		suite.addTestSuite(TestZonedDateTime.class);
		suite.addTestSuite(TestZoneId.class);
		suite.addTestSuite(TestStandardZoneRules.class);
		suite.addTestSuite(TestZoneRulesProvider.class);
		suite.addTestSuite(TestCharLiteralParser.class);
		suite.addTestSuite(TestCharLiteralPrinter.class);
		suite.addTestSuite(TestFractionPrinterParser.class);
		suite.addTestSuite(TestNumberParser.class);
		suite.addTestSuite(TestNumberPrinter.class);
		suite.addTestSuite(TestPadParserDecorator.class);
		suite.addTestSuite(TestPadPrinterDecorator.class);
		suite.addTestSuite(TestReducedParser.class);
		suite.addTestSuite(TestReducedPrinter.class);
		suite.addTestSuite(TestStringLiteralParser.class);
		suite.addTestSuite(TestStringLiteralPrinter.class);
		suite.addTestSuite(TestZoneOffsetParser.class);
		suite.addTestSuite(TestZoneOffsetPrinter.class);
		suite.addTestSuite(TestTextParser.class);
		suite.addTestSuite(TestTextPrinter.class);
		suite.addTestSuite(TestSettingsParser.class);
		suite.addTestSuite(TestZoneIdParser.class);
		suite.addTestSuite(TestDateTimeFormatters.class);
//		suite.addTestSuite(TestSimpleDateTimeTextProvider.class);
		suite.addTestSuite(TestDateTimeBuilderCombinations.class);
		suite.addTestSuite(TestDateTimeParsing.class);
		suite.addTestSuite(TestYear.class);
		// $JUnit-END$

		return suite;
	}
}
