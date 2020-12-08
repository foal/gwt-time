package org.jresearch.threetenbp.gwt.client.browser;

import org.jresearch.threetenbp.gwt.client.TestZonedDateTime;

public class BrowserTestZonedDateTime extends TestZonedDateTime {

	public void test_now_Clock_allSecsInDay_utc() {
		super.long_test_now_Clock_allSecsInDay_utc();
	}

	public void test_now_Clock_allSecsInDay_zone() {
		super.long_test_now_Clock_allSecsInDay_zone();
	}

	public void test_now_Clock_allSecsInDay_beforeEpoch() {
		super.long_test_now_Clock_allSecsInDay_beforeEpoch();
	}

	public void test_factory_ofInstant_allSecsInDay() {
		super.long_test_factory_ofInstant_allSecsInDay();
	}

	public void test_factory_ofInstant_allDaysInCycle() {
		super.long_test_factory_ofInstant_allDaysInCycle();
	}

	public void test_toEpochSecond_afterEpoch() {
		super.long_test_toEpochSecond_afterEpoch();
	}

	public void test_toEpochSecond_beforeEpoch() {
		super.long_test_toEpochSecond_beforeEpoch();
	}

	public void test_now_ZoneId() {
		super.disable_test_now_ZoneId();
	}

}
