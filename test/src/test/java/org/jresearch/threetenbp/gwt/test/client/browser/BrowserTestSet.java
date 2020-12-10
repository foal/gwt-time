package org.jresearch.threetenbp.gwt.test.client.browser;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;

public class BrowserTestSet {

	public static Test suite() {
		GWTTestSuite suite = new GWTTestSuite("Browser Tests");

		// $JUnit-BEGIN$
		suite.addTestSuite(BrowserTestDateTimeParsing.class);
		suite.addTestSuite(BrowserTestLocalDate.class);
		suite.addTestSuite(BrowserTestLocalDateTime.class);
		suite.addTestSuite(BrowserTestLocalTime.class);
		suite.addTestSuite(BrowserTestOffsetDateTime.class);
		suite.addTestSuite(BrowserTestTextParser.class);
		suite.addTestSuite(BrowserTestTextPrinter.class);
		suite.addTestSuite(BrowserTestSimpleDateTimeTextProvider.class);
		suite.addTestSuite(BrowserTestZonedDateTime.class);
		suite.addTestSuite(BrowserTestOffsetTime.class);
		suite.addTestSuite(BrowserTestYear.class);
		// $JUnit-END$

		return suite;
	}
}
