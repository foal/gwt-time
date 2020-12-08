package java.time.chrono;

import java.time.LocalDate;

public class JapaneseEras {

	private JapaneseEras() {
		// prevent instantiation
	}

	public static LocalDate endDate(JapaneseEra era) {
		return era.endDate();
	}

	public static JapaneseEra registerEra(LocalDate since, String name) {
		return JapaneseEra.registerEra(since, name);
	}
}
