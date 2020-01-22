package jtk.comm.cimd;

import java.util.*;

public class CMLogin extends CIMDMessage {
	
	String user_id, password;
	
	// Constructor
	public CMLogin(String user_id, String password) throws IllegalArgumentException{

		Map<String,String> params = new HashMap<String,String>();
		
		params.put(symbol.get("user_id"), user_id);
		params.put(symbol.get("password"), password);
		
		this.resetPacketNumber();
		
		
		this.message = this.messageBuilder(opCode.get("login"), params);
	}
	
	private boolean checkResponse(String message) {
		try {
			CIMDMessage.messageCheck(message);
		}
		catch (CIMDException ce) {
			System.out.print("CIMDException: ");
			String s = ce.getMessage();
			if (s != null) {
				System.out.println(s);
			System.out.println();
			return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		
		System.out.println("Running CMLogin");
		
		CMLogin clm = new CMLogin("user","password");
		
		System.out.println(CIMDMessage.decode(clm.message));

		
		System.out.println("Stopping CMLogin");
	}
	

}
