package java.time.zone;

import java.nio.ByteBuffer;
import java.time.ZoneOffset;

//JDK implementation/ real in /src/main/super
public class Providers {

	private Providers() {
		// prevent instantiation
	}

	public static ZoneRulesProvider of(ByteBuffer data) {
		return null;
	}

	public static ZoneRules of(long[] standardTransitions, ZoneOffset[] standardOffsets, long[] savingsInstantTransitions, ZoneOffset[] wallOffsets, ZoneOffsetTransitionRule[] lastRules) {
		return null;
	}
}
