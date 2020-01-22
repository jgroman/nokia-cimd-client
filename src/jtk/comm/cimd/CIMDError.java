package jtk.comm.cimd;

import java.util.*;

public class CIMDError {

	static Map<Integer,String>CCommError	= new HashMap<Integer,String>();
	static Map<Integer,String>CStatusError	= new HashMap<Integer,String>();

	static {
		// GENERAL error codes
		CCommError.put(0,"No error");
   	    CCommError.put(1,"Unexpected operation");
   	    CCommError.put(2,"Syntax error");
   	    CCommError.put(3,"Unsupported parameter");
   	    CCommError.put(4,"Connection to MC lost");
   	    CCommError.put(5,"No response from MC");
   	    CCommError.put(6,"General system error");
   	    CCommError.put(7,"Cannot find information");
   	    CCommError.put(8,"Parameter formatting error");
   	    CCommError.put(9,"Requested operation failed");
   	    CCommError.put(10,"Temporary congestion error");
        // LOGIN error codes
   	    CCommError.put(100,"Invalid login");
   	    CCommError.put(101,"Incorrect access type");
   	    CCommError.put(102,"Too many users with this login ID");
   	    CCommError.put(103,"Login refused by SMSC");
   	    CCommError.put(104,"Invalid window size");
   	    CCommError.put(105,"Windowing disabled");
   	    CCommError.put(106,"Virtual SMS Center-based barring");
   	    CCommError.put(107,"Invalid subaddr");
   	    CCommError.put(108,"Alias account, login refused");
        // SUBMIT MESSAGE error codes:
   	    CCommError.put(300,"Incorrect destination address");
   	    CCommError.put(301,"Incorrect number of destination addresses");
   	    CCommError.put(302,"Syntax error in user data parameter");
   	    CCommError.put(303,"Incorrect bin/head/normal user data parameter combination");
   	    CCommError.put(304,"Incorrect dcs parameter usage");
   	    CCommError.put(305,"Incorrect validity period parameters usage");
   	    CCommError.put(306,"Incorrect originator address usage");
   	    CCommError.put(307,"Incorrect PID parameter usage");
   	    CCommError.put(308,"Incorrect first delivery parameter usage");
   	    CCommError.put(309,"Incorrect reply path usage");
   	    CCommError.put(310,"Incorrect status report request parameter usage");
   	    CCommError.put(311,"Incorrect cancel enabled parameter usage");
   	    CCommError.put(312,"Incorrect priority parameter usage");
   	    CCommError.put(313,"Incorrect tariff class parameter usage");
   	    CCommError.put(314,"Incorrect service description parameter usage");
   	    CCommError.put(315,"Incorrect transport type parameter usage");
   	    CCommError.put(316,"Incorrect message type parameter usage");
   	    CCommError.put(318,"Incorrect MMS parameter usage");
   	    CCommError.put(319,"Incorrect operation timer parameter usage");
   	    CCommError.put(320,"Incorrect dialogue ID parameter usage");
   	    CCommError.put(321,"Incorrect alpha originator address usage");
   	    CCommError.put(322,"Invalid data for alphanumeric originator");
   	    CCommError.put(323,"Online closed user group rejection");
   	    CCommError.put(324,"Licence expired");
        // ENQUIRE MESSAGE STATUS error codes:
   	    CCommError.put(400,"Incorrect address parameter usage");
   	    CCommError.put(401,"Incorrect scts parameter usage");
        // DELIVERY REQUEST error codes:
   	    CCommError.put(500,"Incorrect scts parameter usage");
   	    CCommError.put(501,"Incorrect mode parameter usage");
   	    CCommError.put(502,"Incorrect parameter combination");
        // CANCEL MESSAGE error codes:
   	    CCommError.put(600,"Incorrect scts parameter usage");
   	    CCommError.put(601,"Incorrect addresss parameter usage");
   	    CCommError.put(602,"Incorrect mode parameter usage");
   	    CCommError.put(603,"Incorrect parameter combination");
   	    // DELIVER MESSAGE error codes:
   	    CCommError.put(700,"Delivery OK / waiting for delivery");
   	    CCommError.put(710,"Generic failure");
   	    CCommError.put(711,"Unsupported DCS");
   	    CCommError.put(712,"Unsupported UDH");
   	    CCommError.put(730,"Unknown subscriber");
   	    // SET error codes
   	    CCommError.put(800,"Changing password failed");
   	    CCommError.put(801,"Changing password not allowed");
        // GET error codes
   	    CCommError.put(900,"Unsupported item requested");
   	    
        // SMSC status error codes
        CStatusError.put(0,"No error");
        CStatusError.put(1,"Unknown subscriber");
        CStatusError.put(9,"Illegal subscriber");
        CStatusError.put(11,"Teleservice not provisioned");
        CStatusError.put(13,"Call barred");
        CStatusError.put(15,"OCUG reject");
        CStatusError.put(19,"No SMS support in MS");
        CStatusError.put(20,"Error in MS");
        CStatusError.put(21,"Facility not supported");
        CStatusError.put(22,"Memory capacity exceeded");
        CStatusError.put(29,"Absent subscriber");
        CStatusError.put(30,"MS busy for MT-SMS");
        CStatusError.put(36,"Network/Protocol failure");
        CStatusError.put(44,"Illegal equipment");
        CStatusError.put(60,"No paging response");
        CStatusError.put(61,"GMSC congestion");
        CStatusError.put(63,"HLR timeout");
        CStatusError.put(64,"MSC/SGSN_timeout");
        CStatusError.put(70,"SMRSE/TCP error");
        CStatusError.put(72,"MT congestion");
        CStatusError.put(75,"GPRS suspended");
        CStatusError.put(80,"No paging response via MSC");
        CStatusError.put(81,"IMSI detached");
        CStatusError.put(82,"Roaming restriction");
        CStatusError.put(83,"Deregistered in HLR for GSM");
        CStatusError.put(84,"Purged for GSM");
        CStatusError.put(85,"No paging response via SGSN");
        CStatusError.put(86,"GPRS detached");
        CStatusError.put(87,"Deregistered in HLR for GPRS");
        CStatusError.put(88,"The MS purged for GPRS");
        CStatusError.put(89,"Unidentified subscriber via MSC");
        CStatusError.put(90,"Inidentified subscriber via SGSN");
        CStatusError.put(112,"Originator missing credit on prepaid account");
        CStatusError.put(113,"Destination missing credit on prepaid account");
        CStatusError.put(114,"Error in prepaid system");
        // USSD center connection errors
        CStatusError.put(750,"Release, call barred");
        CStatusError.put(751,"Release, system failure");
        CStatusError.put(752,"Release, data missing");
        CStatusError.put(753,"Release, unexpected data value");
        CStatusError.put(754,"Release, absent subscriber");
        CStatusError.put(755,"Release, illegal subscriber");
        CStatusError.put(756,"Release, illegal equipment");
        CStatusError.put(757,"Release, unknown alphabet");
        CStatusError.put(758,"Release, USSD busy");
        CStatusError.put(759,"Relase, operation timer expired");
        CStatusError.put(760,"Release, unexpected primitive");
        CStatusError.put(761,"Release, wait timer expired");
        CStatusError.put(762,"Release, data error");
        CStatusError.put(763,"Release, too long USSD data");
        CStatusError.put(764,"Release, unknown MS address");
        CStatusError.put(765,"Release, network congestion");
        CStatusError.put(766,"Release, internal congestion");
        CStatusError.put(767,"Release, no network connection");
        CStatusError.put(768,"Release, USSD not supported");
	}
}
