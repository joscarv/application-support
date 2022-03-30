package com.sanborns.supportsap.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Dates 
{
	public static String getToDay() 
	{		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		Date today = c.getTime();
		return format.format(today);
	}
	
	public static String getYesterday() 
	{		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, -1);
		Date yesterday = c.getTime();
		return format.format(yesterday);
	}
	
	public static String getNow()
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		Date now = c.getTime();
		return format.format(now);
	}
	
	public static String getYearMonth()
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		Date yearMonth = c.getTime();
		return format.format(yearMonth);
	}
}
