package java.time.chrono;

public class HijrahDates {

	private HijrahDates() {
		// prevent instantiation
	}

	public static HijrahDate plusDays(HijrahDate date, long days) {
		return date.plusDays(1);
	}

}
