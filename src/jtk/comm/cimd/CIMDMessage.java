package jtk.comm.cimd;

import java.util.*;
import java.text.*;

public class CIMDMessage {

	static Map<String,String> specChar = CIMDSpecChar.CSpecChar;
	static Map<String,String> opCode = CIMDOpCode.COpCode;
	static Map<String,String> symbol = CIMDSymbol.CSymbol;
	static Map<Integer,String> commError = CIMDError.CCommError;
	static Map<Integer,String> statusError = CIMDError.CStatusError;
	
	private static int packetNumber;
	private static boolean useChecksum;

	public String message;
	
	static {
		packetNumber = 1;
		useChecksum = true;
	}

	
	// Default constructor
	public CIMDMessage() {
		
	}
	
	protected void resetPacketNumber() {
		packetNumber = 1;
	}
	
	protected int getPacketNumber() {
		return packetNumber;
	}
	
	protected void setPacketNumber(int newPacketNumberValue) throws IllegalArgumentException {
		if ((newPacketNumberValue < 1)||(newPacketNumberValue > 255))
			throw new IllegalArgumentException();
		else
			packetNumber = newPacketNumberValue;
	}
	
	protected void incrementPacketNumber() {
		packetNumber += 2;
		if (packetNumber > 255)
			packetNumber = 1;
	}
	
	private static byte calculateChecksum(byte[] message) {
		int checksum = 0;
		for (int i=0;i<message.length;i++) {
			checksum += message[i];
			checksum &= 0xFF;
		}
		return (byte)checksum;
	}
	
	/**
	 * Replaces special characters in message with human readable names
	 * @param message Message to decode
	 * @return Human readable message representation
	 */
	public static String decode(String message) {
		StringBuffer sb = new StringBuffer(1000);

		byte[] msgBytes = message.getBytes();
		
		for (int i=0; i< msgBytes.length; i++) {
			if (msgBytes[i]>31)
				sb.append((char)msgBytes[i]);
			else {
				switch(msgBytes[i]) {
				case 0:
						sb.append("<NUL>");
						break;
				case 2:
						sb.append("<STX>");
						break;
				case 3:
						sb.append("<ETX>");
						break;
				case 9:
						sb.append("<TAB>");
						break;
				default:
						sb.append("<"+Integer.toString((int)msgBytes[i])+">");
						break;
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * Creates complete CIMD compliant message
	 * @param opCode Message header opcode
	 * @param params List of parameters and their values
	 * @return CIMD compliant message
	 */
	
	protected String messageBuilder(String opCode, Map<String,String> params) {

		StringBuffer sb = new StringBuffer(2000);
		
		// Header
		sb.append(specChar.get("stx"));
		sb.append(opCode);
		sb.append(':');
		
		// -- We are formatting packet number to zero padded 3 char dec string
		NumberFormat formatter = new DecimalFormat("000");
	    sb.append(formatter.format(packetNumber));

	    sb.append(specChar.get("tab"));
	    
	    // Parameter block
	    for (Map.Entry<String, String> e : params.entrySet()) {
	        String paramCode = e.getKey();
	        String paramValue = e.getValue();	    	
			sb.append(paramCode);
			sb.append(':');
			sb.append(paramValue);
			sb.append(specChar.get("tab"));
	    }
	    
	    // Trailer
		// -- Checksum usage is optional
		if (useChecksum) {
			int checksum = (int) calculateChecksum(sb.toString().getBytes());	// Calculating message checksum
			String checksumHex = Integer.toHexString(checksum).toUpperCase();	// Converting checksum to hex and uppercase
			
			if (checksumHex.length() == 1)
				sb.append('0');		// zero padding if checksum is only one char long
			sb.append(checksumHex);	// 2 char hex checksum
		}
		sb.append(specChar.get("etx"));
	    
		return sb.toString();
	}

	/**
	 * Extracts value of the given parameter code from the message
	 * @param message
	 * @param parameterCode
	 * @return Parameter value or null if not present
	 */
	public static String extractParameterValue(String message, String parameterCode) {
		String value = new String();
		
		// We are formatting parameter number to zero padded 3 char dec string
		NumberFormat formatter = new DecimalFormat("000");
		// Parameter code is <TAB>, 3 dec chars and ':'
	    String paramString = new String(specChar.get("tab")+formatter.format(Integer.parseInt(parameterCode))+":");  		

	    // Trying to find parameter code in message
	    int paramBlockStart = message.indexOf(paramString); 						
	    if (paramBlockStart != -1) {
	    	int paramValueStart = paramBlockStart + 5; 		
    		int paramValueLength = message.substring(paramValueStart).indexOf(specChar.get("tab"));	// End of parameter value
    		if (paramValueLength != -1) {
    			value = message.substring(paramValueStart,paramValueStart+paramValueLength);		// Extracting parameter value
    		}
	    }	    
		return value;
	}	
	
	/**
	 * Performs basic message check for CIMD compliancy
	 * @param message Message to check
	 */
	public static void messageCheck(String message) throws CIMDException {

		// Check prefix
		if (!message.startsWith(specChar.get("stx"))) {
			throw new CIMDException("Invalid prefix");
		}
		
		// Check suffix
		if (!message.endsWith(specChar.get("etx"))) {
			throw new CIMDException("Invalid suffix");
		}
		
		// Looking for checksum
		StringBuffer sb = new StringBuffer(100);
		sb.append(specChar.get("tab"));
		sb.append(specChar.get("etx"));
		if (!message.endsWith(sb.toString())) {
			// Checksum is used, it takes 2 bytes after the last TAB
			int lastTabIndex = message.lastIndexOf(specChar.get("tab"));
			String checksumRead = new String(message.getBytes(),lastTabIndex+1,2);
			String messagePayload = new String(message.getBytes(),0,lastTabIndex+1);
			String checksumCalc = Integer.toHexString((int) calculateChecksum(messagePayload.getBytes())).toUpperCase();
			
			if (!checksumRead.equals(checksumCalc)) {
				throw new CIMDException("Invalid checksum");
			}
		}
		
	}

	public static String responseCheck(String message) {

		String responseCode = new String(message.getBytes(),1,2);
		
		// NACK
		if (responseCode.equals(opCode.get("nack")))
			return new String("Nack");		

		// General error response
		if (responseCode.equals(opCode.get("general_error_resp")))
			return new String("Error");
		
		// Negative response
		String errorCode = new String(extractParameterValue(message,symbol.get("error_code")));
		
		// -- Response contains 'error_code' parameter
		if (errorCode != null)	{			
			StringBuffer sb = new StringBuffer(200);
			sb.append("Error: ");
			sb.append(errorCode.toString());
			String errorText = new String(extractParameterValue(message,symbol.get("error_text")));
			if (!errorText.equals(null)) {
				sb.append(", ");
				try {
					sb.append(commError.get(Integer.valueOf(errorText)));
				}
				catch (NumberFormatException nfe) {
					sb.append("Invalid error text index");
				}
			}
			return sb.toString();
		}		
		
		
		return new String("OK");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Running CIMDMessage");
		
		System.out.println(opCode.get("login"));

		System.out.println("Stopping CIMDMessage");
	}

}
