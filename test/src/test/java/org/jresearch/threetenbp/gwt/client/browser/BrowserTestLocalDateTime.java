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
package org.jresearch.threetenbp.gwt.client.browser;

import java.time.LocalDateTime;

import org.jresearch.threetenbp.gwt.client.TestLocalDateTime;

public class BrowserTestLocalDateTime extends TestLocalDateTime {

	public void test_now_Clock_allSecsInDay_utc() {
		super.long_test_now_Clock_allSecsInDay_utc();
	}

	public void test_now_Clock_allSecsInDay_offset() {
		super.long_test_now_Clock_allSecsInDay_offset();
	}

	public void test_now_Clock_allSecsInDay_beforeEpoch() {
		super.long_test_now_Clock_allSecsInDay_beforeEpoch();
	}

	public void test_factory_ofEpochSecond_longOffset_afterEpoch() {
		super.long_test_factory_ofEpochSecond_longOffset_afterEpoch();
	}

	public void test_factory_ofEpochSecond_longOffset_beforeEpoch() {
		super.long_test_factory_ofEpochSecond_longOffset_beforeEpoch();
	}

	public void test_plusWeeks_symmetry() {
		super.long_test_plusWeeks_symmetry();
	}

	public void test_plusWeeks_symmetry(LocalDateTime reference) {
		super.long_test_plusWeeks_symmetry(reference);
	}

	public void test_plusDays_symmetry() {
		super.long_test_plusDays_symmetry();
	}

	public void test_plusDays_symmetry(LocalDateTime reference) {
		super.long_test_plusDays_symmetry(reference);
	}

	public void test_minusWeeks_symmetry() {
		super.long_test_minusWeeks_symmetry();
	}

	public void test_minusWeeks_symmetry(LocalDateTime reference) {
		super.long_test_minusWeeks_symmetry(reference);
	}

	public void test_minusDays_symmetry() {
		super.long_test_minusDays_symmetry();
	}

	public void test_minusDays_symmetry(LocalDateTime reference) {
		super.long_test_minusDays_symmetry(reference);
	}

	public void test_toEpochSecond_afterEpoch() {
		super.long_test_toEpochSecond_afterEpoch();
	}

	public void test_toEpochSecond_beforeEpoch() {
		super.long_test_toEpochSecond_beforeEpoch();
	}

	public void test_now_ZoneId() {
		super.disable_test_now_ZoneId();
	}

}
