package org.jresearch.threetenbp.gwt.client;

import java.time.zone.ZoneRulesProvider;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

import javax.annotation.Nonnull;

import org.gwtproject.core.client.ScriptInjector;
import org.jresearch.threetenbp.gwt.client.loader.TimeJsBundle;
import org.jresearch.threetenbp.gwt.client.zone.GwtZoneRuleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.core.client.GWT;

@SuppressWarnings("nls")
public class Support {

	private static final Logger LOGGER = LoggerFactory.getLogger(Support.class);

	public static final TimeJsBundle bundle = GWT.create(TimeJsBundle.class);
	private static final Map<String, GwtZoneRuleProvider> gwtZoneRuleProviders = new HashMap<>();

	private static boolean commonInitialized = false;
	private static boolean loadAsync = true;

	public static void init() {
		if (!commonInitialized) {
			LOGGER.debug("common initialization");
			ScriptInjector.fromString(bundle.support().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
			commonInitialized = true;
			if (loadAsync) {
				gwtZoneRuleProviders.values().stream()
						.filter(GwtZoneRuleProvider::isAsyncInitializeSupported)
						.forEach(GwtZoneRuleProvider::initiatedAsyncInitialize);
			}
		}
	}

	public static void initTzData() {
		LOGGER.debug("initTzData called");
		if (!commonInitialized) {
			// prevent from asynch load
			loadAsync = false;
			init();
		}
		if (!isTzTnitialized()) {
			gwtZoneRuleProviders.values().forEach(GwtZoneRuleProvider::initialize);
		}
	}

	public static void registerGwtZoneRuleProvider(GwtZoneRuleProvider gwtZoneRuleProvider) {
		if (!gwtZoneRuleProviders.containsKey(gwtZoneRuleProvider.getProviderId())) {
			LOGGER.debug("Register GWT zone rule provider: {}", gwtZoneRuleProvider.getProviderId());
			gwtZoneRuleProviders.put(gwtZoneRuleProvider.getProviderId(), gwtZoneRuleProvider);
			if (gwtZoneRuleProvider.isAsyncInitializeSupported()) {
				gwtZoneRuleProvider.initiatedAsyncInitialize();
			}
			ZoneRulesProvider.refresh();
		}
	}

	public static boolean isTzTnitialized() {
		return !gwtZoneRuleProviders.isEmpty() && gwtZoneRuleProviders.values().stream().allMatch(GwtZoneRuleProvider::isInitialized);
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
	public static Locale[] supportedLocalesOfDateTimeFormat(Locale[] locales) {
		String[] a = Stream.of(locales).map(l -> l.toLanguageTag()).filter(l -> !"und".equalsIgnoreCase(l)).toArray(String[]::new);
		String[] supportedLocales = SupportJs.supportedLocalesOfDateTimeFormat(a);
		return Stream.of(supportedLocales).map(Support::jsRootToJava).map(Locale::forLanguageTag).toArray(Locale[]::new);
	}

	@Nonnull
	public static Locale[] supportedLocalesOfNumberFormat(Locale[] locales) {
		String[] a = Stream.of(locales).map(l -> l.toLanguageTag()).filter(l -> !"und".equalsIgnoreCase(l)).toArray(String[]::new);
		String[] supportedLocales = SupportJs.supportedLocalesOfNumberFormat(a);
		return Stream.of(supportedLocales).map(Support::jsRootToJava).map(Locale::forLanguageTag).toArray(Locale[]::new);
	}

	public static String jsRootToJava(String tag) {
		return "root".equalsIgnoreCase(tag) ? "" : tag;
	}

}
