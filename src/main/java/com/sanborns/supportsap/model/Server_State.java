package com.sanborns.supportsap.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Server_State 
{
	private Date businessdate;
	private String storeopen;
	private SimpleDateFormat format;
	private Calendar c;
	
	public Server_State(Date businessdate, String storeopen) 
	{
		this.businessdate = businessdate;
		this.storeopen = storeopen;
		this.format = new SimpleDateFormat("yyyyMMdd");
		this.c = Calendar.getInstance();
	}
	
	public Date getBusinessdate() 
	{
		return businessdate;
	}
	
	public void setBusinessdate(Date businessdate) 
	{
		this.businessdate = businessdate;
	}
	
	public String getStoreopen() 
	{
		return storeopen;
	}
	
	public void setStoreopen(String storeopen) 
	{
		this.storeopen = storeopen;
	}
	
	public String getBusinessdateString()
	{
		c.setTime(businessdate);
		return format.format(c.getTime());
	}

	@Override
	public String toString() 
	{
		return "Server_State [businessdate=" + businessdate + ", storeopen=" + storeopen + "]";
	}
	
}
