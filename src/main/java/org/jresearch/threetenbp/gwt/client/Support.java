package org.jresearch.threetenbp.gwt.client;

import java.nio.ByteBuffer;
import java.time.zone.Providers;
import java.time.zone.ZoneRulesProvider;
import java.util.Locale;
import java.util.stream.Stream;

import javax.annotation.Nonnull;

import org.gwtproject.core.client.ScriptInjector;
import org.gwtproject.nio.TypedArrayHelper;
import org.gwtproject.typedarrays.shared.Uint8Array;
import org.gwtproject.xhr.client.ReadyStateChangeHandler;
import org.gwtproject.xhr.client.XMLHttpRequest;
import org.gwtproject.xhr.client.XMLHttpRequest.ResponseType;
import org.jresearch.threetenbp.gwt.client.cldr.LocaleInfo;
import org.jresearch.threetenbp.gwt.client.loader.TimeJsBundle;
import org.jresearch.threetenbp.gwt.client.locale.Locales;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.core.client.GWT;

import elemental2.core.ArrayBuffer;
import jsinterop.base.Js;

@SuppressWarnings("nls")
public class Support {

	private static final Logger LOGGER = LoggerFactory.getLogger(Support.class);

	private static final TimeJsBundle bundle = GWT.create(TimeJsBundle.class);

	private static boolean commonInitialized = false;
	private static boolean tzTnitializing = false;
	private static boolean tzTnitialized = false;

	static {
		init();
	}

	public static void init() {
		if (!commonInitialized) {
			LOGGER.trace("common initialization");
			ScriptInjector.fromString(bundle.support().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
			ScriptInjector.fromString(bundle.base64binary().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
			commonInitialized = true;
			XMLHttpRequest request = XMLHttpRequest.create();
			request.open("GET", bundle.tzdb().getSafeUri().asString());
			request.setResponseType(ResponseType.ArrayBuffer);
			request.setOnReadyStateChange(new ReadyStateChangeHandler() {
				@SuppressWarnings({ "synthetic-access", "boxing" })
				@Override
				public void onReadyStateChange(XMLHttpRequest xhr) {
					if (xhr.getReadyState() == XMLHttpRequest.DONE) {
						if (xhr.getStatus() == 200) {
							if (!tzTnitialized && !tzTnitializing) {
								tzTnitializing = true;
								LOGGER.trace("tz asynch initialization");
								ArrayBuffer buffer = Js.cast(xhr.getResponseArrayBuffer());
								ByteBuffer data = TypedArrayHelper.wrap(buffer);
								ZoneRulesProvider provider = Providers.of(data);
								ZoneRulesProvider.registerProvider(provider);
								tzTnitialized = true;
							}
						} else {
							LOGGER.error("Can't load TZDB asynch. Response status: {} {}", xhr.getStatus(),
									xhr.getStatusText());
						}
					}
				}
			});
			request.send();
		}
	}

	public static void initTzData() {
		if (!tzTnitialized && !tzTnitializing) {
			tzTnitializing = true;
			LOGGER.trace("tz synch initialization");
			String tzData = bundle.tzdbEncoded().getText();
			ArrayBuffer buffer = Support.decodeArrayBuffer(tzData);
			ByteBuffer data = TypedArrayHelper.wrap(buffer);
			ZoneRulesProvider provider = Providers.of(data);
			ZoneRulesProvider.registerProvider(provider);
			tzTnitialized = true;
		}
	}

	public static float getTimestamp() {
		return SupportJs.getTimestamp();
	}

	@Nonnull
	public static String getTimezone() {
		return SupportJs.getTimezone();
	}

	public static void sleep(int milliseconds) {
		SupportJs.sleep(milliseconds);
	}

	/**
	 * @param style  - "short", "long"
	 * @param locale - language tag
	 */
	@Nonnull
	public static String displayTimeZone(boolean daylight, String timeZone, String style, String locale) {
		return SupportJs.displayTimeZone(daylight, timeZone, style, locale);
	}

	/**
	 * @param style  - "2-digit", "numeric", "narrow", "short", "long"
	 * @param locale - language tag
	 */
	@Nonnull
	public static String[] displayMonths(String style, boolean standalone, String locale) {
		return SupportJs.displayMonths(style, standalone, locale);
	}

	/**
	 * @param style  - "narrow", "short", "long"
	 * @param locale - language tag
	 */
	@Nonnull
	public static String[] displayWeekdays(String style, boolean standalone, String locale) {
		return SupportJs.displayWeekdays(style, standalone, locale);
	}

	/**
	 * @param style  - "narrow", "short", "long"
	 * @param locale - language tag
	 */
	@Nonnull
	public static String[] displayEras(String style, String locale) {
		return SupportJs.displayEras(style, locale);
	}

	/**
	 * @param style  - "narrow", "short", "long"
	 * @param locale - language tag
	 */
	@Nonnull
	public static String[] displayAmpm(String style, String locale) {
		return SupportJs.displayAmpm(style, locale);
	}

	@Nonnull
	public static DecimalProperty displayNumber(String locale) {
		return SupportJs.displayNumber(locale);
	}

	@Nonnull
	public static ArrayBuffer decodeArrayBuffer(String base64) {
		return SupportJs.decodeArrayBuffer(base64);
	}

	@Nonnull
	public static Uint8Array decode(String base64) {
		return SupportJs.decode(base64);
	}

	@Nonnull
	public static Locale[] supportedLocalesOfDateTimeFormat(Locale[] locales) {
		String[] a = Stream.of(locales).map(Locale::toLanguageTag).filter(l -> !"root".equalsIgnoreCase(l)).toArray(String[]::new);
		String[] supportedLocales = SupportJs.supportedLocalesOfDateTimeFormat(a);
		return Stream.of(supportedLocales).map(Support::toLocale).toArray(Locale[]::new);
	}

	@Nonnull
	public static Locale[] supportedLocalesOfNumberFormat(Locale[] locales) {
		String[] a = Stream.of(locales).map(Locale::toLanguageTag).filter(l -> !"root".equalsIgnoreCase(l)).toArray(String[]::new);
		String[] supportedLocales = SupportJs.supportedLocalesOfNumberFormat(a);
		return Stream.of(supportedLocales).map(Support::toLocale).toArray(Locale[]::new);
	}

	public static Locale toLocale(String langTag) {
		return Stream.of(LocaleInfo.LOCALES).filter(l -> langTag.equalsIgnoreCase(l.toLanguageTag())).findAny().orElseGet(() -> createLocale(langTag));
	}

	public static Locale createLocale(String langTag) {
		String[] parts = langTag.split("-", 3);
		return parts.length == 1 ? new Locale(parts[0]) : parts.length == 2 ? createLocale(parts[0], parts[1]) : createLocale(parts[0], parts[1], parts[2]);
	}

	public static Locale createLocale(String lang, String something) {
		return isScript(something) ? Locales.create(lang, "", something, "") : Locales.create(lang, something, "", "");
	}

	public static Locale createLocale(String lang, String something01, String something02) {
		return isScript(something01) ? Locales.create(lang, something02, something01, "") : Locales.create(lang, something01, "", something02);
	}

	private static boolean isScript(String something) {
		return something.length() == 4;
	}

}
