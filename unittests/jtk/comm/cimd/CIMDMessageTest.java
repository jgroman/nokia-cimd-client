package jtk.comm.cimd;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CIMDMessageTest {

	CIMDMessage cimd;
	
	@Before
	public void setUp() throws Exception {
		cimd = new CIMDMessage();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testResetPacketNumber() {
		cimd.setPacketNumber(20);
		cimd.resetPacketNumber();
		assertEquals(cimd.getPacketNumber(),1);	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetPacketNumber() {
		cimd.setPacketNumber(10);
		assertEquals(cimd.getPacketNumber(),10);

		// This one throws exception on too high packet number
		cimd.setPacketNumber(400);
	}

	@Test
	public void testIncrementPacketNumber() {
		cimd.setPacketNumber(20);
		cimd.incrementPacketNumber();
		assertEquals(cimd.getPacketNumber(),22);
	}

	@Test
	public void testDecode() {
		StringBuilder sb = new StringBuilder(50);

		sb.append(CIMDMessage.specChar.get("nul"));
		sb.append(CIMDMessage.specChar.get("stx"));
		sb.append(CIMDMessage.specChar.get("tab"));
		sb.append(CIMDMessage.specChar.get("etx"));
		assertEquals(CIMDMessage.decode(sb.toString()),"<NUL><STX><TAB><ETX>");
		
	}
	
}
