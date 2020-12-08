package org.jresearch.threetenbp.gwt.client.browser;

import org.jresearch.threetenbp.gwt.client.format.TestDecimalStyle;

public class BrowserTestDecimalStyle extends TestDecimalStyle {

	public void test_getAvailableLocales() {
		super.disables_test_getAvailableLocales();
	}

	public void test_of_Locale_Fr() {
		super.disable_test_of_Locale_Fr();
	}

}
