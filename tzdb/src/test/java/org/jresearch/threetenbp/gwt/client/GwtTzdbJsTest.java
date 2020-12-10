package org.jresearch.threetenbp.gwt.client;

import org.gwtproject.typedarrays.shared.Uint8Array;
import org.junit.Test;


@SuppressWarnings({ "static-method", "nls" })
public class GwtTzdbJsTest extends AbstractTest {

	@Test
	public void testDecodeArrayBuffer() {
		Uint8Array data = TzdbJs.decode("VGhlIHF1");
		assertNotNull(data);
		assertEquals(6, data.length());
		assertEquals(0x54, data.get(0));
		assertEquals(0x68, data.get(1));
		assertEquals(0x65, data.get(2));
		assertEquals(0x20, data.get(3));
		assertEquals(0x71, data.get(4));
		assertEquals(0x75, data.get(5));
	}

}
