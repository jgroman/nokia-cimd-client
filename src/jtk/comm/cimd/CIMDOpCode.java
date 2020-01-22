package jtk.comm.cimd;

import java.util.*;

public class CIMDOpCode {

	static Map<String,String> COpCode		= new HashMap<String,String>();
	
	static {
		COpCode.put("login",			 "01");
		COpCode.put("login_resp",		 "51");
		COpCode.put("logout",			 "02");
		COpCode.put("logout_resp",		 "52");
		COpCode.put("submit_msg",		 "03");
		COpCode.put("submit_msg_resp",	 "53");
		COpCode.put("enq_msg_status",	 "04");
		COpCode.put("enq_msg_status_resp","54");
		COpCode.put("delivery_req",		 "05");
		COpCode.put("delivery_req_resp", "55");
		COpCode.put("cancel_msg",	     "06");
		COpCode.put("cancel_msg_resp",   "56");
		COpCode.put("deliver_msg",		 "20");
		COpCode.put("deliver_msg_resp",	 "70");
		COpCode.put("deliver_status_rep","23");
		COpCode.put("deliver_status_rep_resp","73");
		COpCode.put("set",				 "08");
		COpCode.put("set_resp",			 "58");
		COpCode.put("get",				 "09");
		COpCode.put("get_resp",			 "59");
        COpCode.put("alive",			 "40");
        COpCode.put("alive_resp",		 "90");
        COpCode.put("general_error_resp","98");
        COpCode.put("nack",				 "99");
	}	
}
