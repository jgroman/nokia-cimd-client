package jtk.comm.cimd;

import java.util.*;

public class CIMDSpecChar {

	static Map<String,String> CSpecChar	= new HashMap<String,String>();	

	// Static block, gets executed before constructor
	static {
		CSpecChar.put("nul","\u0000");
		CSpecChar.put("stx","\u0002");
		CSpecChar.put("etx","\u0003");
		CSpecChar.put("tab","\u0009");
	}
}
