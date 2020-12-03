package java.time.zone;

import java.nio.ByteBuffer;
import java.time.ZoneOffset;

public class Providers {

	private Providers() {
		// prevent instantiation
	}

	public static ZoneRulesProvider of(ByteBuffer data) {
		return new TzdbZoneRulesProvider(data);
	}

	public static ZoneRules of(long[] standardTransitions, ZoneOffset[] standardOffsets, long[] savingsInstantTransitions, ZoneOffset[] wallOffsets, ZoneOffsetTransitionRule[] lastRules) {
		return new StandardZoneRules(standardTransitions, standardOffsets, savingsInstantTransitions, wallOffsets, lastRules);
	}
}
