package java.time;

public class LocalTimes {

	private LocalTimes() {
		// prevent instantiation
	}

	public static LocalTime ofSecondOfDay(long secondOfDay, int nanoOfSecond) {
		return LocalTime.ofSecondOfDay(secondOfDay);
	}

}
