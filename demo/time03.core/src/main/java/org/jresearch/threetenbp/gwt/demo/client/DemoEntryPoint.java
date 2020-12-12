package org.jresearch.threetenbp.gwt.demo.client;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DecimalStyle;
import java.util.Locale;

import org.jresearch.threetenbp.gwt.time.client.Support;

import com.google.gwt.core.client.EntryPoint;

import elemental2.dom.DomGlobal;

public class DemoEntryPoint implements EntryPoint {

	@SuppressWarnings("nls")
	@Override
	public void onModuleLoad() {
		DomGlobal.document.getElementById("reportTime").innerHTML = LocalTime.now() != null ? "yes" : "no";
		String reportTimeZone = "no";
		try {
			ZoneId.of("Europe/Paris");
			reportTimeZone = "yes";
		} catch (Exception e) {
			// ignore it
		}
		DomGlobal.document.getElementById("reportTimeZone").innerHTML = reportTimeZone;
		DomGlobal.document.getElementById("reportAvailableGeneralLocale").innerHTML = String.valueOf(Locale.getAvailableLocales().length);
		DomGlobal.document.getElementById("reportAvailableDecimalStyleLocale").innerHTML = String.valueOf(DecimalStyle.getAvailableLocales().size());
		DomGlobal.document.getElementById("reportAvailableDateTimeFormatLocale").innerHTML = String.valueOf(Support.supportedLocalesOfDateTimeFormat(Locale.getAvailableLocales()).length);
	}

}
