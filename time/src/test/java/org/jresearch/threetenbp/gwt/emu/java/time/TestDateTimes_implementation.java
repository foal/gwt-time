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
package org.jresearch.threetenbp.gwt.emu.java.time;

import org.junit.Test;

/**
 * Test.
 */
//@Test
public class TestDateTimes_implementation extends AbstractTest {

	// -----------------------------------------------------------------------
	// safeAdd()
	// -----------------------------------------------------------------------
//    //@DataProvider(name="safeAddIntProvider")
	Object[][] safeAddIntProvider() {
        return new Object[][] {
            {Integer.MIN_VALUE, 1, Integer.MIN_VALUE + 1},
            {-1, 1, 0},
            {0, 0, 0},
            {1, -1, 0},
            {Integer.MAX_VALUE, -1, Integer.MAX_VALUE - 1},
        };
	}

	@Test(/* dataProvider="safeAddIntProvider" */)
	public void test_safeAddInt() {
		Object[][] data = safeAddIntProvider();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_safeAddInt((int) objects[0], (int) objects[1], (int) objects[2]);
		}
	}

	public void test_safeAddInt(int a, int b, int expected) {
		assertEquals(Math.addExact(a, b), expected);
	}

//    //@DataProvider(name="safeAddIntProviderOverflow")
	Object[][] safeAddIntProviderOverflow() {
        return new Object[][] {
            {Integer.MIN_VALUE, - 1},
            {Integer.MIN_VALUE + 1, -2},
            {Integer.MAX_VALUE - 1, 2},
            {Integer.MAX_VALUE, 1},
        };
	}

	@Test(/* dataProvider="safeAddIntProviderOverflow", */ expected = ArithmeticException.class)
	public void test_safeAddInt_overflow() {
		Object[][] data = safeAddIntProviderOverflow();
		for (int i = 0; i < data.length; i++) {
			try {
				Object[] objects = data[i];
				test_safeAddInt_overflow((int) objects[0], (int) objects[1]);
				fail("Missing exception");
			} catch (ArithmeticException e) {
				// expected
			}
		}
	}

	public void test_safeAddInt_overflow(int a, int b) {
		Math.addExact(a, b);
	}

//    //@DataProvider(name="safeAddLongProvider")
	Object[][] safeAddLongProvider() {
        return new Object[][] {
            {Long.MIN_VALUE, 1l, Long.MIN_VALUE + 1},
            {-1l, 1l, 0l},
            {0l, 0l, 0l},
            {1l, -1l, 0l},
            {Long.MAX_VALUE, -1l, Long.MAX_VALUE - 1},
        };
	}

	@Test(/* dataProvider="safeAddLongProvider" */)
	public void test_safeAddLong() {
		Object[][] data = safeAddLongProvider();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_safeAddLong((long) objects[0], (long) objects[1], (long) objects[2]);
		}
	}

	public void test_safeAddLong(long a, long b, long expected) {
		assertEquals(Math.addExact(a, b), expected);
	}

//    //@DataProvider(name="safeAddLongProviderOverflow")
	Object[][] safeAddLongProviderOverflow() {
        return new Object[][] {
            {Long.MIN_VALUE, - 1l},
            {Long.MIN_VALUE + 1, -2l},
            {Long.MAX_VALUE - 1, 2l},
            {Long.MAX_VALUE, 1l},
        };
	}

	@Test(/* dataProvider="safeAddLongProviderOverflow", */ expected = ArithmeticException.class)
	public void test_safeAddLong_overflow() {
		Object[][] data = safeAddLongProviderOverflow();
		for (int i = 0; i < data.length; i++) {
			try {
				Object[] objects = data[i];
				test_safeAddLong_overflow((long) objects[0], (long) objects[1]);
				fail("Missing exception");
			} catch (ArithmeticException e) {
				// expected
			}
		}
	}

	public void test_safeAddLong_overflow(long a, long b) {
		Math.addExact(a, b);
	}

	// -----------------------------------------------------------------------
	// safeSubtract()
	// -----------------------------------------------------------------------
	// @DataProvider(name="safeSubtractIntProvider")
	Object[][] safeSubtractIntProvider() {
        return new Object[][] {
            {Integer.MIN_VALUE, -1, Integer.MIN_VALUE + 1},
            {-1, -1, 0},
            {0, 0, 0},
            {1, 1, 0},
            {Integer.MAX_VALUE, 1, Integer.MAX_VALUE - 1},
        };
	}

	@Test(/* dataProvider="safeSubtractIntProvider" */)
	public void test_safeSubtractInt() {
		Object[][] data = safeSubtractIntProvider();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_safeSubtractInt((int) objects[0], (int) objects[1], (int) objects[2]);
		}
	}

	public void test_safeSubtractInt(int a, int b, int expected) {
		assertEquals(Math.subtractExact(a, b), expected);
	}

	// @DataProvider(name="safeSubtractIntProviderOverflow")
	Object[][] safeSubtractIntProviderOverflow() {
        return new Object[][] {
            {Integer.MIN_VALUE,  1},
            {Integer.MIN_VALUE + 1, 2},
            {Integer.MAX_VALUE - 1, -2},
            {Integer.MAX_VALUE, -1},
        };
	}

	@Test(/* dataProvider="safeSubtractIntProviderOverflow", */ expected = ArithmeticException.class)
	public void test_safeSubtractInt_overflow() {
		Object[][] data = safeSubtractIntProviderOverflow();
		for (int i = 0; i < data.length; i++) {
			try {
				Object[] objects = data[i];
				test_safeSubtractInt_overflow((int) objects[0], (int) objects[1]);
				fail("Missing exception");
			} catch (ArithmeticException e) {
				// expected
			}
		}
	}

	public void test_safeSubtractInt_overflow(int a, int b) {
		Math.subtractExact(a, b);
	}

	// @DataProvider(name="safeSubtractLongProvider")
	Object[][] safeSubtractLongProvider() {
        return new Object[][] {
            {Long.MIN_VALUE, -1l, Long.MIN_VALUE + 1},
            {-1l, -1l, 0l},
            {0l, 0l, 0l},
            {1l, 1l, 0l},
            {Long.MAX_VALUE, 1l, Long.MAX_VALUE - 1},
        };
	}

	@Test(/* dataProvider="safeSubtractLongProvider" */)
	public void test_safeSubtractLong() {
		Object[][] data = safeSubtractLongProvider();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_safeSubtractLong((long) objects[0], (long) objects[1], (long) objects[2]);
		}
	}

	public void test_safeSubtractLong(long a, long b, long expected) {
		assertEquals(Math.subtractExact(a, b), expected);
	}

	// @DataProvider(name="safeSubtractLongProviderOverflow")
	Object[][] safeSubtractLongProviderOverflow() {
        return new Object[][] {
            {Long.MIN_VALUE, 1l},
            {Long.MIN_VALUE + 1, 2l},
            {Long.MAX_VALUE - 1, -2l},
            {Long.MAX_VALUE, -1l},
        };
	}

	@Test(/* dataProvider="safeSubtractLongProviderOverflow", */ expected = ArithmeticException.class)
	public void test_safeSubtractLong_overflow() {
		Object[][] data = safeSubtractLongProviderOverflow();
		for (int i = 0; i < data.length; i++) {
			try {
				Object[] objects = data[i];
				test_safeSubtractLong_overflow((long) objects[0], (long) objects[1]);
				fail("Missing exception");
			} catch (ArithmeticException e) {
				// expected
			}
		}
	}

	public void test_safeSubtractLong_overflow(long a, long b) {
		Math.subtractExact(a, b);
	}

	// -----------------------------------------------------------------------
	// safeMultiply()
	// -----------------------------------------------------------------------
	// @DataProvider(name="safeMultiplyIntProvider")
	Object[][] safeMultiplyIntProvider() {
        return new Object[][] {
            {Integer.MIN_VALUE, 1, Integer.MIN_VALUE},
            {Integer.MIN_VALUE / 2, 2, Integer.MIN_VALUE},
            {-1, -1, 1},
            {-1, 1, -1},
            {0, -1, 0},
            {0, 0, 0},
            {0, 1, 0},
            {1, -1, -1},
            {1, 1, 1},
				{ Integer.MAX_VALUE / 2, 2, Integer.MAX_VALUE - 1 },
				{ Integer.MAX_VALUE, -1, Integer.MIN_VALUE + 1 }, };
	}

	@Test(/* dataProvider="safeMultiplyIntProvider" */)
	public void test_safeMultiplyInt() {
		Object[][] data = safeMultiplyIntProvider();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_safeMultiplyInt((int) objects[0], (int) objects[1], (int) objects[2]);
		}
	}

	public void test_safeMultiplyInt(int a, int b, int expected) {
		assertEquals(Math.multiplyExact(a, b), expected);
	}

	// @DataProvider(name="safeMultiplyIntProviderOverflow")
	Object[][] safeMultiplyIntProviderOverflow() {
        return new Object[][] {
            {Integer.MIN_VALUE, 2},
            {Integer.MIN_VALUE / 2 - 1, 2},
            {Integer.MAX_VALUE, 2},
            {Integer.MAX_VALUE / 2 + 1, 2},
            {Integer.MIN_VALUE, -1},
            {-1, Integer.MIN_VALUE},
        };
	}

	@Test(/* dataProvider="safeMultiplyIntProviderOverflow", */ expected = ArithmeticException.class)
	public void test_safeMultiplyInt_overflow() {
		Object[][] data = safeMultiplyIntProviderOverflow();
		for (int i = 0; i < data.length; i++) {
			try {
				Object[] objects = data[i];
				test_safeMultiplyInt_overflow((int) objects[0], (int) objects[1]);
				fail("Missing exception");
			} catch (ArithmeticException e) {
				// expected
			}
		}
	}

	public void test_safeMultiplyInt_overflow(int a, int b) {
		Math.multiplyExact(a, b);
	}

	// -----------------------------------------------------------------------
	// @DataProvider(name="safeMultiplyLongProvider")
	Object[][] safeMultiplyLongProvider() {
        return new Object[][] {
            {Long.MIN_VALUE, 1, Long.MIN_VALUE},
            {Long.MIN_VALUE / 2, 2, Long.MIN_VALUE},
            {-1l, -1, 1l},
            {-1l, 1, -1l},
            {0l, -1, 0l},
            {0l, 0, 0l},
            {0l, 1, 0l},
            {1l, -1, -1l},
            {1l, 1, 1l},
            {Long.MAX_VALUE / 2, 2, Long.MAX_VALUE - 1l},
            {Long.MAX_VALUE, -1, Long.MIN_VALUE + 1l},
            {-1l, Integer.MIN_VALUE, -((long) Integer.MIN_VALUE)},
        };
	}

	@Test(/* dataProvider="safeMultiplyLongProvider" */)
	public void test_safeMultiplyLong() {
		Object[][] data = safeMultiplyLongProvider();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_safeMultiplyLong((long) objects[0], (int) objects[1], (long) objects[2]);
		}
	}

	public void test_safeMultiplyLong(long a, int b, long expected) {
		assertEquals(Math.multiplyExact(a, b), expected);
	}

	// @DataProvider(name="safeMultiplyLongProviderOverflow")
	Object[][] safeMultiplyLongProviderOverflow() {
        return new Object[][] {
            {Long.MIN_VALUE, 2},
            {Long.MIN_VALUE / 2 - 1, 2},
            {Long.MAX_VALUE, 2},
            {Long.MAX_VALUE / 2 + 1, 2},
            {Long.MIN_VALUE, -1},
        };
	}

	@Test(/* dataProvider="safeMultiplyLongProviderOverflow", */ expected = ArithmeticException.class)
	public void test_safeMultiplyLong_overflow() {
		Object[][] data = safeMultiplyLongProviderOverflow();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			try {
				test_safeMultiplyLong_overflow((long) objects[0], (int) objects[1]);
				fail("Missing exception");
			} catch (ArithmeticException e) {
				// expected
			}
		}
	}

	public void test_safeMultiplyLong_overflow(long a, int b) {
		Math.multiplyExact(a, b);
	}

	// -----------------------------------------------------------------------
	// @DataProvider(name="safeMultiplyLongLongProvider")
	Object[][] safeMultiplyLongLongProvider() {
        return new Object[][] {
            {Long.MIN_VALUE, 1l, Long.MIN_VALUE},
            {Long.MIN_VALUE / 2, 2l, Long.MIN_VALUE},
            {-1l, -1l, 1l},
            {-1l, 1l, -1l},
            {0l, -1l, 0l},
            {0l, 0l, 0l},
            {0l, 1l, 0l},
            {1l, -1l, -1l},
            {1l, 1l, 1l},
            {Long.MAX_VALUE / 2, 2l, Long.MAX_VALUE - 1},
            {Long.MAX_VALUE, -1l, Long.MIN_VALUE + 1},
        };
	}

	@Test(/* dataProvider="safeMultiplyLongLongProvider" */)
	public void test_safeMultiplyLongLong() {
		Object[][] data = safeMultiplyLongLongProvider();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_safeMultiplyLongLong((long) objects[0], (long) objects[1], (long) objects[2]);
		}
	}

	public void test_safeMultiplyLongLong(long a, long b, long expected) {
		assertEquals(Math.multiplyExact(a, b), expected);
	}

	// @DataProvider(name="safeMultiplyLongLongProviderOverflow")
	Object[][] safeMultiplyLongLongProviderOverflow() {
        return new Object[][] {
            {Long.MIN_VALUE, 2l},
            {Long.MIN_VALUE / 2 - 1, 2l},
            {Long.MAX_VALUE, 2l},
            {Long.MAX_VALUE / 2 + 1, 2l},
            {Long.MIN_VALUE, -1l},
            {-1l, Long.MIN_VALUE},
        };
	}

	@Test(/* dataProvider="safeMultiplyLongLongProviderOverflow", */ expected = ArithmeticException.class)
	public void test_safeMultiplyLongLong_overflow() {
		Object[][] data = safeMultiplyLongLongProviderOverflow();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			try {
				test_safeMultiplyLongLong_overflow((long) objects[0], (long) objects[1]);
				fail("Missing exception");
			} catch (ArithmeticException e) {
				// expected
			}
		}
	}

	public void test_safeMultiplyLongLong_overflow(long a, long b) {
		Math.multiplyExact(a, b);
	}

	// -----------------------------------------------------------------------
	// safeToInt()
	// -----------------------------------------------------------------------
	// @DataProvider(name="safeToIntProvider")
	Object[][] safeToIntProvider() {
        return new Object[][] {
            {(long)Integer.MIN_VALUE},
            {Integer.MIN_VALUE + 1l},
            {-1l},
            {0l},
            {1l},
            {Integer.MAX_VALUE - 1l},
            {(long)Integer.MAX_VALUE},
        };
	}

	@Test(/* dataProvider="safeToIntProvider" */)
	public void test_safeToInt() {
		Object[][] data = safeToIntProvider();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_safeToInt((long) objects[0]);
		}
	}

	public void test_safeToInt(long l) {
		assertEquals(Math.toIntExact(l), l);
	}

	// @DataProvider(name="safeToIntProviderOverflow")
	Object[][] safeToIntProviderOverflow() {
        return new Object[][] {
            {Long.MIN_VALUE},
            {Integer.MIN_VALUE - 1L},
            {Integer.MAX_VALUE + 1L},
            {Long.MAX_VALUE},
        };
	}

	@Test(/* dataProvider="safeToIntProviderOverflow", */ expected = ArithmeticException.class)
	public void test_safeToInt_overflow() {
		Object[][] data = safeToIntProviderOverflow();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			try {
				test_safeToInt_overflow((long) objects[0]);
				fail("Missing exception");
			} catch (ArithmeticException e) {
				// expected
			}
		}
	}

	public void test_safeToInt_overflow(long l) {
		Math.toIntExact(l);
	}

	// -----------------------------------------------------------------------
	// safeCompare()
	// -----------------------------------------------------------------------
	public void test_safeCompare_int() {
        doTest_safeCompare_int(
            Integer.MIN_VALUE,
            Integer.MIN_VALUE + 1,
            Integer.MIN_VALUE + 2,
            -2,
            -1,
            0,
            1,
            2,
            Integer.MAX_VALUE - 2,
            Integer.MAX_VALUE - 1,
            Integer.MAX_VALUE
        );
	}

	private void doTest_safeCompare_int(int... values) {
		for (int i = 0; i < values.length; i++) {
			int a = values[i];
			for (int j = 0; j < values.length; j++) {
				int b = values[j];
				assertEquals(a + " <=> " + b, Integer.compare(a, b), a < b ? -1 : (a > b ? 1 : 0));
			}
		}
	}

	public void test_safeCompare_long() {
        doTest_safeCompare_long(
            Long.MIN_VALUE,
            Long.MIN_VALUE + 1,
            Long.MIN_VALUE + 2,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE + 1,
            Integer.MIN_VALUE + 2,
            -2,
            -1,
            0,
            1,
            2,
            Integer.MAX_VALUE - 2,
            Integer.MAX_VALUE - 1,
            Integer.MAX_VALUE,
            Long.MAX_VALUE - 2,
            Long.MAX_VALUE - 1,
            Long.MAX_VALUE
        );
	}

	private void doTest_safeCompare_long(long... values) {
		for (int i = 0; i < values.length; i++) {
			long a = values[i];
			for (int j = 0; j < values.length; j++) {
				long b = values[j];
				assertEquals(a + " <=> " + b, Long.compare(a, b), a < b ? -1 : (a > b ? 1 : 0));
			}
		}
	}

	// -------------------------------------------------------------------------
	// @DataProvider(name="FloorDiv")
	Object[][] data_floorDiv() {
        return new Object[][] {
            {5L, 4, 1L},
            {4L, 4, 1L},
            {3L, 4, 0L},
            {2L, 4, 0L},
            {1L, 4, 0L},
            {0L, 4, 0L},
            {-1L, 4, -1L},
            {-2L, 4, -1L},
            {-3L, 4, -1L},
            {-4L, 4, -1L},
            {-5L, 4, -2L},
        };
	}

	@Test(/* dataProvider="FloorDiv" */)
	public void test_floorDiv_long() {
		Object[][] data = data_floorDiv();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_floorDiv_long((long) objects[0], (int) objects[1], (long) objects[2]);
		}
	}

	public void test_floorDiv_long(long a, int b, long expected) {
		assertEquals(Math.floorDiv(a, b), expected);
	}

	@Test(/* dataProvider="FloorDiv" */)
	public void test_floorDiv_int() {
		Object[][] data = data_floorDiv();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_floorDiv_int((long) objects[0], (Integer) objects[1], (long) objects[2]);
		}
	}

	public void test_floorDiv_int(long a, int b, long expected) {
		if (a <= Integer.MAX_VALUE && a >= Integer.MIN_VALUE) {
			assertEquals(Math.floorDiv((int) a, b), (int) expected);
		}
	}

	// -------------------------------------------------------------------------
	// @DataProvider(name="FloorMod")
	Object[][] data_floorMod() {
        return new Object[][] {
            {5L, 4, 1},
            {4L, 4, 0},
            {3L, 4, 3},
            {2L, 4, 2},
            {1L, 4, 1},
            {0L, 4, 0},
            {-1L, 4, 3},
            {-2L, 4, 2},
            {-3L, 4, 1},
            {-4L, 4, 0},
            {-5L, 4, 3},
        };
	}

	@Test(/* dataProvider="FloorMod" */)
	public void test_floorMod_long01() {
		Object[][] data = data_floorMod();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_floorMod_long((long) objects[0], (long) ((int) objects[1]), (int) objects[2]);
		}
	}

	public void test_floorMod_long(long a, long b, int expected) {
		assertEquals(Math.floorMod(a, b), expected);
	}

	@Test(/* dataProvider="FloorMod" */)
	public void test_floorMod_long02() {
		Object[][] data = data_floorMod();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_floorMod_long((long) objects[0], (int) objects[1], (int) objects[2]);
		}
	}

	public void test_floorMod_long(long a, int b, int expected) {
		assertEquals(Math.floorMod(a, b), expected);
	}

	@Test(/* dataProvider="FloorMod" */)
	public void test_floorMod_int() {
		Object[][] data = data_floorMod();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_floorMod_int((long) objects[0], (int) objects[1], (int) objects[2]);
		}
	}

	public void test_floorMod_int(long a, int b, int expected) {
		if (a <= Integer.MAX_VALUE && a >= Integer.MIN_VALUE) {
			assertEquals(Math.floorMod((int) a, b), expected);
		}
	}

}
