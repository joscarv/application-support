package com.sanborns.supportsap.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class Config 
{
	private final String pathFile = "./config.conf";
	private Properties properties;
	private static InputStream inStream;
	private static Config instance;

	private Config()
	{
		properties = new Properties();
		try
		{
			inStream = new FileInputStream(pathFile);
			properties.load(inStream);
		} catch(IOException e)
		{
			inStream = null;
		}
	}

	public static synchronized Config getInstance()
	{
		if (instance == null || inStream == null)
			instance = new Config();
		return instance;
	}	

	public List<String> getFilesSanborns(int idStore, String date) 
	{  	
		List<String> list = new ArrayList<>();
		if(inStream == null)
			return list;

		String id = idStore < 10 ? "000" + idStore : idStore < 100 ? "00" + idStore : "0" + idStore;
		Enumeration<Object> e = properties.keys();
		while(e.hasMoreElements())
		{
			String key = (String)e.nextElement();
			if(key.contains("interfaceSanborns"))
				list.add(properties.getProperty(key).toUpperCase() + "." + id + "." + date);
		}
		e = null;
		return list;
	}

	public List<String> getFilesCafe(int idStore, String date)
	{
		List<String> list = new ArrayList<>();
		if(inStream == null)
			return list;

		String id = "0" + idStore;
		Enumeration<Object> e = properties.keys();
		while(e.hasMoreElements())
		{
			String key = (String)e.nextElement();
			if(key.contains("interfaceCafe"))
				list.add(properties.getProperty(key).toUpperCase() + "." + id + "." + date);
		}
		e = null;
		return list;
	}
}
