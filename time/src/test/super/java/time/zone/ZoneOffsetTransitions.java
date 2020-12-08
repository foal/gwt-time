package java.time.zone;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ZoneOffsetTransitions {

	private ZoneOffsetTransitions() {
		// prevent instantiation
	}

	public static ZoneOffsetTransition create(LocalDateTime transition, ZoneOffset offsetBefore, ZoneOffset offsetAfter) {
		return new ZoneOffsetTransition(transition, offsetBefore, offsetAfter);
	}

}
