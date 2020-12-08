package java.time.format;

public class DecimalStyles {

	public static int convertToDigit(DecimalStyle style, char ch) {
		return style.convertToDigit(ch);
	}

	public static String convertNumberToI18N(DecimalStyle style, String numericText) {
		return style.convertNumberToI18N(numericText);
	}

}
