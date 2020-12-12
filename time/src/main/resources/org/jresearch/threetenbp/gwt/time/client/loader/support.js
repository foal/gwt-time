if (window.performance.now) {
    getTimestamp = function() { return window.performance.timing.navigationStart + window.performance.now(); };
} else {
    getTimestamp = function() { return new Date().getTime(); };
}

if (typeof Intl == 'object' && typeof Intl.DateTimeFormat == 'function'  && typeof Intl.DateTimeFormat().resolvedOptions == 'function') {
	getTimezone = function() { return Intl.DateTimeFormat().resolvedOptions().timeZone; };
} else {
	getTimezone = function() { return new Date().toTimeString().slice(9, 17); };
}

getMinutesOffset = function() { return -(new Date().getTimezoneOffset()); };

if (typeof Intl == 'object' && typeof Intl.DateTimeFormat == 'function'  && typeof Intl.DateTimeFormat.supportedLocalesOf == 'function') {
	supportedLocalesOfDateTimeFormat = function(locales) {
		return Intl.DateTimeFormat.supportedLocalesOf(locales, { localeMatcher: 'lookup' });
	};
} else {
	supportedLocalesOfDateTimeFormat = function(locales) {
		return ["ROOT", "US"];
	};
}

if (typeof Intl == 'object' && typeof Intl.NumberFormat == 'function'  && typeof Intl.NumberFormat.supportedLocalesOf == 'function') {
	supportedLocalesOfNumberFormat = function(locales) {
		return Intl.NumberFormat.supportedLocalesOf(locales, { localeMatcher: 'lookup' });
	};
} else {
	supportedLocalesOfNumberFormat = function(locales) {
		return ["ROOT", "US"];
	};
}

if (typeof Intl == 'object' && typeof Intl.DateTimeFormat == 'function'  && typeof Intl.DateTimeFormat().formatToParts == 'function') {
	displayTimeZone = function(daylight, timeZone, style, locale) {
		try {
			const region = new Intl.DateTimeFormat(locale, { timeZone: timeZone, timeZoneName: style });
			const month = daylight ? 6 : 12;
			const date = new Date(Date.UTC(2012, month, 20, 6, 0, 0));
			return region.formatToParts(date).find(checkTimeZone).value;
		} catch (e) {
			console.error("Error while call displayTimeZone with daylight %s, timeZone %s, style %s, locale %s: %s", daylight, timeZone, style, locale, e);
			return timeZone;
		}
	};
} else {
	displayTimeZone = function(daylight, timeZone, style, locale) {
		return timeZone;
	};
}

if (typeof Intl == 'object' && typeof Intl.DateTimeFormat == 'function'  && typeof Intl.DateTimeFormat().formatToParts == 'function') {
	displayMonths = function(style, standalone, locale) {
		try {
			var result = [];
			const region = standalone ? new Intl.DateTimeFormat(locale, { month: style }) : new Intl.DateTimeFormat(locale, { month: style, day: 'numeric' });
			for (i = 0; i < 12; i++) {
				const date = new Date(Date.UTC(2020, i, 4, 6, 0, 0));
				result.push(region.formatToParts(date).find(checkMonth).value);
			}
			return result;
		} catch (e) {
			console.error("Error while call displayMonths with style %s, locale %s: %s", style, locale, e);
			return [];
		}
	};
} else {
	displayMonths = function(style, locale) {
		switch (style) {
		case "narrow":
			return ["J", "F", "M", "A", "M", "J", "J", "A", "S", "O", "N", "D"];
		case "short":
			return ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
		default:
			return ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
		}
	};
}

if (typeof Intl == 'object' && typeof Intl.DateTimeFormat == 'function'  && typeof Intl.DateTimeFormat().formatToParts == 'function') {
	displayWeekdays = function(style, standalone, locale) {
		try {
			var result = [];
			const region = standalone ? new Intl.DateTimeFormat(locale, { weekday: style }) : new Intl.DateTimeFormat(locale, { weekday: style, day: 'numeric' });
			for (i = 1; i <= 7; i++) {
				const date = new Date(Date.UTC(2020, 5, i, 6, 0, 0));
				result.push(region.formatToParts(date).find(checkWeekday).value);
			}
			return result;
		} catch (e) {
			console.error("Error while call displayMonths with style %s, locale %s: %s", style, locale, e);
			return [];
		}
	};
} else {
	displayWeekdays = function(style, locale) {
		switch (style) {
		case "narrow":
			return ["M", "T", "W", "T", "F", "S", "S"];
		case "short":
			return ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"];
		default:
			return ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];
		}
	};
}

if (typeof Intl == 'object' && typeof Intl.DateTimeFormat == 'function'  && typeof Intl.DateTimeFormat().formatToParts == 'function') {
	displayEras = function(style, locale) {
		try {
			var result = [];
			const region = new Intl.DateTimeFormat(locale, { era: style });
			var date = new Date(-99999999999999);
			result.push(region.formatToParts(date).find(checkEra).value);
			date = new Date(99999999999999);
			result.push(region.formatToParts(date).find(checkEra).value);
			return result;
		} catch (e) {
			console.error("Error while call displayMonths with style %s, locale %s: %s", style, locale, e);
			return [];
		}
	};
} else {
	displayEras = function(style, locale) {
		switch (style) {
		case "narrow":
			return ["B", "A"];
		case "short":
			return ["BC", "AD"];
		default:
			return ["Before Christ", "Anno Domini"];
		}
	};
}

if (typeof Intl == 'object' && typeof Intl.DateTimeFormat == 'function'  && typeof Intl.DateTimeFormat().formatToParts == 'function') {
	displayAmpm = function(style, locale) {
		try {
			var result = [];
			const region = new Intl.DateTimeFormat(locale, { dayPeriod: style, hour: 'numeric', hour12: 'false' });
			var date = new Date(Date.UTC(2020, 5, 4, 6, 0, 0));
			result.push(region.formatToParts(date).find(checkAmpm).value);
			date = new Date(Date.UTC(2020, 5, 4, 16, 0, 0));
			result.push(region.formatToParts(date).find(checkAmpm).value);
			return result;
		} catch (e) {
			console.error("Error while call displayMonths with style %s, locale %s: %s", style, locale, e);
			return [];
		}
	};
} else {
	displayAmpm = function(style, locale) {
		return ["AM", "PM"]
	};
}

if (typeof Intl == 'object' && typeof Intl.NumberFormat == 'function'  && typeof Intl.NumberFormat().formatToParts == 'function') {
	displayNumber = function(locale) {
		var result = new Object();
		try {
			const numFormat = new Intl.NumberFormat(locale, { signDisplay:'always' });
			result.zeroDigit = numFormat.formatToParts(0).find(checkZero).value;
			//FF does not support signDisplay return +
			var res = numFormat.formatToParts(1).find(checkPlus)
			result.positiveSign = res === undefined ? '+' : res.value ;
			result.negativeSign = numFormat.formatToParts(-1).find(checkMinus).value;
			result.decimalSeparator = numFormat.formatToParts(1.5).find(checkPoint).value;
		} catch (e) {
			console.error("Error while call displayNumber, locale %s: %s", locale, e);
			result.zeroDigit = '0';
			result.positiveSign = '+';
			result.negativeSign = '-';
			result.decimalSeparator = '.';
		}
		return result;
	};
} else {
	displayNumber = function(locale) {
		var result = new Object();
		result.zeroDigit = '0';
		result.positiveSign = '+';
		result.negativeSign = '-';
		result.decimalSeparator = '.';
		return result;
	};
}

function sleep(milliseconds) {
	var start = getTimestamp();
	var current = getTimestamp();
	while(current - start < milliseconds) {
		current = getTimestamp();
	}
}

function displayTimeZoneModern(daylight, timeZone, style, locale) {
	const region = new Intl.DateTimeFormat(locale, { timeZone: timeZone, timeZoneName: style });
	const month = daylight ? 6 : 12;
	const date = new Date(Date.UTC(2012, month, 20, 6, 0, 0));
	return region.formatToParts(date).find(checkType).value;
}

function checkZero(part) {
	  return part.type === "integer";
}

function checkPlus(part) {
	  return part.type === "plusSign";
}

function checkMinus(part) {
	  return part.type === "minusSign";
}

function checkPoint(part) {
	  return part.type === "decimal";
}

function checkTimeZone(part) {
	  return part.type === "timeZoneName";
}

function checkMonth(part) {
	  return part.type === "month";
}

function checkWeekday(part) {
	  return part.type === "weekday";
}

function checkEra(part) {
	  return part.type === "era";
}

function checkAmpm(part) {
	  return part.type === "dayPeriod";
}

function displayTimeZoneLegacy(daylight, timeZone, style, locale) {
	const month = daylight ? 6 : 12;
	const date = new Date(Date.UTC(2012, month, 20, 6, 0, 0));
	return date.toLocaleDateString(locale, { timeZone: timeZone, timeZoneName: style }).slice(11);
}