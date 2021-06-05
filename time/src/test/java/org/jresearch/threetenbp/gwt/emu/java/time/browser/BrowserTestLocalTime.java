package org.jresearch.threetenbp.gwt.emu.java.time.browser;

import org.jresearch.threetenbp.gwt.emu.java.time.TestLocalTime;

public class BrowserTestLocalTime extends TestLocalTime {

	public void test_now_Clock_allSecsInDay() {
		super.long_test_now_Clock_allSecsInDay();
	}

	public void test_now_Clock_beforeEpoch() {
		super.long_test_now_Clock_beforeEpoch();
	}

	public void test_toNanoOfDay() {
		super.long_test_toNanoOfDay();
	}

	public void test_toNanoOfDay_fromNanoOfDay_symmetry() {
		super.long_test_toNanoOfDay_fromNanoOfDay_symmetry();
	}

	public void test_now_ZoneId() {
		super.disabled_test_now_ZoneId();
	}

}
