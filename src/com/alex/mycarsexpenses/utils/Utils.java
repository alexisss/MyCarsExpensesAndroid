package com.alex.mycarsexpenses.utils;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;

public class Utils {
	public static Metrics getMetrics(Activity activity){
		TelephonyManager telephonyManager = (TelephonyManager)activity.getSystemService(Context.TELEPHONY_SERVICE);
		String countryCode = telephonyManager.getSimCountryIso();
		if("us".equals(countryCode))
		{
			return Metrics.IMPERIAL;
		}
		
		return Metrics.METRIC;
	}
	
//	public double convertToCurrentSystem(UnitTypes type, double metricValue){
//		
//		
//	}
}
