package jtk.comm.cimd;

import java.util.*;

public class CIMDSymbol {

	static Map<String,String> CSymbol		= new HashMap<String,String>();

	static {
        CSymbol.put("user_id",				"010");		// String, max. length 32
		CSymbol.put("password",             "011");		// String, max. length 32
		CSymbol.put("subaddr",              "012");		// Integer, max. length 3, values 0-9
		CSymbol.put("window_size",          "019");		// Integer, max. length 3, values 1-128
		CSymbol.put("dest_addr",            "021");
		CSymbol.put("orig_addr",            "023");
		CSymbol.put("orig_imsi",            "026");
		CSymbol.put("alpha_orig_addr",      "027");		// String, max.length 11, only alphanumeric chars and space allowed
		CSymbol.put("orig_vmsc_addr",       "028");
		CSymbol.put("data_coding_scheme",   "030");		// Integer, max. length 3, values 0-255
		CSymbol.put("user_data_header",     "032");
		CSymbol.put("user_data",            "033");
		CSymbol.put("user_data_binary",     "034");
		CSymbol.put("transport_type",       "041");		// Obsolete
		CSymbol.put("msg_type",             "042");		// Obsolete
		CSymbol.put("more_msgs",            "044");		// Integer, max.length 1, values 0-1
		CSymbol.put("oper_timer",           "045");		// Obsolete
		CSymbol.put("dialogue_id",          "046");		// Obsolete
		CSymbol.put("ussd_phase",           "047");		// Obsolete
		CSymbol.put("service_code",         "048");
		CSymbol.put("validity_period_rel",  "050");
		CSymbol.put("validity_period_abs",  "051");
		CSymbol.put("protocol_id",          "052");
		CSymbol.put("first_deli_time_rel",  "053");
		CSymbol.put("fisrt_deli_time_abs",  "054");
		CSymbol.put("reply_path",           "055");
		CSymbol.put("status_report_req",    "056");
		CSymbol.put("cancel_enabled",       "058");
		CSymbol.put("cancel_mode",          "059");
		CSymbol.put("serv_centre_timestamp","060");		// Integer, max.length 12
		CSymbol.put("status_code",          "061");
		CSymbol.put("status_error_code",    "062");
		CSymbol.put("discharge_time",       "063");
		CSymbol.put("tariff_class",         "064");
		CSymbol.put("service_descr",        "065");
		CSymbol.put("msg_count",            "066");
		CSymbol.put("priority",             "067");
		CSymbol.put("deli_req_mode",        "068");
		CSymbol.put("serv_center_addr",     "069");
		CSymbol.put("get_param",            "500");
		CSymbol.put("smsc_time",            "501");		// Integer, max. length 12, 'yymmddhhmmss'
		CSymbol.put("error_code",           "900");
		CSymbol.put("error_text",           "901");
	}
}
