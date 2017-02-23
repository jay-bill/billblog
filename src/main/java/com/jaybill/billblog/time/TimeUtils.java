package com.jaybill.billblog.time;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimeUtils {

	/**
	 * 取出小数点后的数字，只保留到时分秒
	 * @param time
	 * @return
	 */
	public static String normalTimestamp(Timestamp time){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String tmp = df.format(time);
		return tmp;
//		Timestamp t = Timestamp.valueOf(tmp);
//		return t;
	}
}
