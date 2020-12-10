package org.jresearch.threetenbp.gwt.client.browser;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;

public class BrowserTestSet {

	public static Test suite() {
		GWTTestSuite suite = new GWTTestSuite("Browser Tests");

		// $JUnit-BEGIN$
		suite.addTestSuite(BrowserTestDateTimeTextPrinting.class);
		suite.addTestSuite(BrowserTestDecimalStyle.class);
		suite.addTestSuite(BrowserTestIsoFields.class);
		suite.addTestSuite(BrowserTestOffsetDateTime_instants.class);
		suite.addTestSuite(BrowserTestZoneOffset.class);
		suite.addTestSuite(BrowserTestZoneRulesBuilder.class);
		// $JUnit-END$

		return suite;
	}
}
