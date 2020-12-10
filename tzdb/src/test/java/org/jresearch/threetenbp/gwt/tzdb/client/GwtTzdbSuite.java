package org.jresearch.threetenbp.gwt.tzdb.client;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;

public class GwtTzdbSuite {

	public static Test suite() {
		GWTTestSuite suite = new GWTTestSuite("TZDB Tests");

		// $JUnit-BEGIN$
		suite.addTestSuite(GwtTest.class);
		suite.addTestSuite(GwtTzdbJsTest.class);
		// $JUnit-END$

		return suite;
	}
}
