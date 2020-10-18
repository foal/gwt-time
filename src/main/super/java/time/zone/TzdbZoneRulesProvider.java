/*
 * Copyright (c) 2007-present, Stephen Colebourne & Michael Nascimento Santos
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * Neither the name of JSR-310 nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package java.time.zone;

//import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import org.gwtproject.nio.TypedArrayHelper;
import org.gwtproject.typedarrays.client.ArrayBufferNative;
import org.gwtproject.typedarrays.client.Int8ArrayNative;

import elemental2.core.ArrayBuffer;
import jsinterop.base.Js;

/**
 * Loads time-zone rules for 'TZDB'.
 * <p>
 * This class is public for the service loader to access.
 *
 * <h3>Specification for implementors</h3> This class is immutable and
 * thread-safe.
 */
public final class TzdbZoneRulesProvider extends ZoneRulesProvider {

	/**
	 * All the regions that are available.
	 */
	private List<String> regionIds;
	/**
	 * All the versions that are available.
	 */
	private final NavigableMap<String, Version> versions = new TreeMap<String, Version>();

	/**
	 * Creates an instance and loads the specified input stream.
	 * <p>
	 * This could be used to wrap this provider in another instance.
	 *
	 * @param stream the stream to load, not null, not closed after use
	 * @throws ZoneRulesException if unable to load
	 */
	public TzdbZoneRulesProvider(ByteBuffer data) {
		super();
		try {
			load(data);
		} catch (Exception ex) {
			throw new ZoneRulesException("Unable to load TZDB time-zone rules", ex);
		}
	}

	// -----------------------------------------------------------------------
	@Override
	protected Set<String> provideZoneIds() {
		return new HashSet<String>(regionIds);
	}

	@Override
	protected ZoneRules provideRules(String zoneId, boolean forCaching) {
		Objects.requireNonNull(zoneId, "zoneId");
		ZoneRules rules = versions.lastEntry().getValue().getRules(zoneId);
		if (rules == null) {
			throw new ZoneRulesException("Unknown time-zone ID: " + zoneId);
		}
		return rules;
	}

	@Override
	protected NavigableMap<String, ZoneRules> provideVersions(String zoneId) {
		TreeMap<String, ZoneRules> map = new TreeMap<String, ZoneRules>();
		for (Version version : versions.values()) {
			ZoneRules rules = version.getRules(zoneId);
			if (rules != null) {
				map.put(version.versionId, rules);
			}
		}
		return map;
	}

	// -------------------------------------------------------------------------
	/**
	 * Loads the rules from an input stream.
	 *
	 * @param in the stream to load, not null, not closed after use
	 * @throws Exception if an error occurs
	 */
	private boolean load(ByteBuffer in) throws IOException {
		boolean updated = false;
		Iterable<Version> loadedVersions = loadData(in);
		for (Version loadedVersion : loadedVersions) {
			// see https://github.com/ThreeTen/threetenbp/pull/28 for issue wrt
			// multiple versions of lib on classpath
			Version existing = versions.putIfAbsent(loadedVersion.versionId, loadedVersion);
			if (existing != null && !existing.versionId.equals(loadedVersion.versionId)) {
				throw new ZoneRulesException(
						"Data already loaded for TZDB time-zone rules version: " + loadedVersion.versionId);
			}
			updated = true;
		}
		return updated;
	}

	/**
	 * Loads the rules from an input stream.
	 *
	 * @param in the stream to load, not null, not closed after use
	 * @throws Exception if an error occurs
	 */
	private Iterable<Version> loadData(ByteBuffer dis) throws IOException {
		if (dis.get() != 1) {
			// GWT change
			throw new IOException("File format not recognised");
		}
		// group
		String groupId = readUtf(dis);
		if ("TZDB".equals(groupId) == false) {
			// GWT change
			throw new IOException("File format not recognised");
		}
		// versions
		int versionCount = dis.getShort();
		String[] versionArray = new String[versionCount];
		for (int i = 0; i < versionCount; i++) {
			versionArray[i] = readUtf(dis);
		}
		// regions
		int regionCount = dis.getShort();
		String[] regionArray = new String[regionCount];
		for (int i = 0; i < regionCount; i++) {
			regionArray[i] = readUtf(dis);
		}
		regionIds = Arrays.asList(regionArray);
		// rules
		int ruleCount = dis.getShort();
//		ZoneRules[] ruleArray = new ZoneRules[ruleCount];
		ArrayBuffer[] ruleArray = new ArrayBuffer[ruleCount];
		for (int i = 0; i < ruleCount; i++) {
			short length = dis.getShort();
			ArrayBufferNative buffer = new ArrayBufferNative(length);
			Int8ArrayNative array = new Int8ArrayNative(buffer);
			for (int j = 0; j < length; j++) {
				array.set(j, dis.get());
			}
//			ruleArray[i] = (ZoneRules) Ser.read(dis);
			ruleArray[i] = Js.cast(buffer);
		}
		// link version-region-rules
		Set<Version> versionSet = new HashSet<Version>(versionCount);
		for (int i = 0; i < versionCount; i++) {
			int versionRegionCount = dis.getShort();
			String[] versionRegionArray = new String[versionRegionCount];
			short[] versionRulesArray = new short[versionRegionCount];
			for (int j = 0; j < versionRegionCount; j++) {
				versionRegionArray[j] = regionArray[dis.getShort()];
				versionRulesArray[j] = dis.getShort();
			}
			versionSet.add(new Version(versionArray[i], versionRegionArray, versionRulesArray, ruleArray));
		}
		return versionSet;
	}

	private String readUtf(ByteBuffer dis) throws IOException {
		int stringSize = dis.getShort() & 0xffff;
		byte[] data = new byte[stringSize];
		dis.get(data);
		return new String(data, StandardCharsets.UTF_8);
	}

	@Override
	public String toString() {
		return "TZDB";
	}

	// -----------------------------------------------------------------------
	/**
	 * A version of the TZDB rules.
	 */
	static class Version {
		private final String versionId;
		private final String[] regionArray;
		private final short[] ruleIndices;
		private final ArrayBuffer[] encodeRuleData;
		private final ZoneRules[] ruleData;

		Version(String versionId, String[] regionIds, short[] ruleIndices, ArrayBuffer[] encodeRuleData) {
			this.encodeRuleData = encodeRuleData;
			this.ruleData = new ZoneRules[encodeRuleData.length];
			this.versionId = versionId;
			this.regionArray = regionIds;
			this.ruleIndices = ruleIndices;
		}

		ZoneRules getRules(String regionId) {
			int regionIndex = Arrays.binarySearch(regionArray, regionId);
			if (regionIndex < 0) {
				return null;
			}
			try {
				return createRule(ruleIndices[regionIndex]);
			} catch (Exception ex) {
				throw new ZoneRulesException(
						"Invalid binary time-zone data: TZDB:" + regionId + ", version: " + versionId, ex);
			}
		}

		ZoneRules createRule(short index) throws Exception {
			ZoneRules rule = ruleData[index];
			if (rule == null) {
				ArrayBuffer arrayBuffer = encodeRuleData[index];
				rule = (ZoneRules) Ser.read(TypedArrayHelper.wrap(arrayBuffer));
				ruleData[index] = rule;
				encodeRuleData[index] = null;
			}
			return rule;
		}

		@Override
		public String toString() {
			return versionId;
		}
	}

}
