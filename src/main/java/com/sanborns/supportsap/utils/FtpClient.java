package com.sanborns.supportsap.utils;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FtpClient 
{
	private final String server41 = Config.getInstance().getServerFTP41();
	private final String userSanborns = Config.getInstance().getUserSanb41();
	private final String passSanborns = Config.getInstance().getPassSanb41();
	private final String userCafe = Config.getInstance().getUserCafe41();
	private final String passCafe = Config.getInstance().getPassCafe41();
	
	private final String server24 = Config.getInstance().getServerFTP24();
	private final String userEodProc = Config.getInstance().getUserFTP24();
	private final String passEodProc = Config.getInstance().getPassFTP24();	
	
	private org.apache.commons.net.ftp.FTPClient client;
	
	public FtpClient()
	{
		client = new org.apache.commons.net.ftp.FTPClient();
	}
	
	public List<String> dirSanborns41(String date)
	{
		List<String> list = new ArrayList<>();
		try 
		{
			client.connect(server41);
			if(client.login(userSanborns, passSanborns))
			{				
				String[] array = client.listNames();
				list = arrayToList(array, date);
			} 
			else
			{
				list.add("Credenciales incorrectas");
			}
		} 
		catch (SocketException e) 
		{
			list.add("Sin conexion por ftp al servidor: " + server41);
		} 
		catch (IOException e) 
		{
			list.add("Error en ftp: " + e.getMessage());
		}
		return list;
	}
	
	public List<String> dirSanborns41_after(String date)
	{
		String userSanborns = Config.getInstance().getUserSanb_bkp();
		String passSanborns = Config.getInstance().getPassSanb_bkp();
		List<String> list = new ArrayList<>();
		try 
		{
			client.connect(server41);
			if(client.login(userSanborns, passSanborns))
			{
				client.changeWorkingDirectory("/Temporales/Temporal_Sanborns");
				String[] array = client.listNames();
				list = arrayToList(array, date);
			}
			else
			{
				list.add("Credenciales incorrectas");
			}
		} 
		catch (SocketException e) 
		{
			list.add("Sin conexion por ftp al servidor: " + server41);
		} 
		catch (IOException e) 
		{
			list.add("Error en ftp: " + e.getMessage());
		}
		return list;
	}
	
	public List<String> dirCafe41(String date)
	{
		List<String> list = new ArrayList<>();
		try 
		{
			client.connect(server41);
			if(client.login(userCafe, passCafe))
			{			
				String[] array = client.listNames();
				list = arrayToList(array, date);
			} 
			else
			{
				list.add("Credenciales incorrectas");
			}
		} 
		catch (SocketException e) 
		{
			list.add("Sin conexion por ftp al servidor: " + server41);
		} 
		catch (IOException e) 
		{
			list.add("Error en ftp: " + e.getMessage());
		}
		return list;
	}	
	
		
	public List<String> dirEodProc()
	{
		List<String> list = new ArrayList<>();
		try 
		{
			client.connect(server24);
			if(client.login(userEodProc, passEodProc))
			{
				client.changeWorkingDirectory("/EJOURNAL/EJOURN." + Dates.getYearMonth());
				String[] temp = client.listNames();
				list = Arrays.asList(temp).stream().filter(f -> f.contains("STARTEOD.1")).collect(Collectors.toList());
			}
			else
			{
				list.add("Credenciales incorrectas");
			}
		}
		catch (SocketException e)
		{
			list.add("Sin conexion por ftp al servidor: " + server24);
		}
		catch (IOException e) 
		{
			list.add("Error en ftp: " + e.getMessage());
		}
		return list;
	}

	private List<String> arrayToList(String[] array, String date)
	{
		List<String> list = new ArrayList<>();
		list = Arrays.asList(array).
						stream().
						filter(f -> f.contains(date)).
						filter(this::isFileInterface).
						map(this::getNameInterface).
						collect(Collectors.toList());
		if(list.size() == 0)
			list.add("No se encontraron interfaces");
		else if(list.size() == 1)
			list.set(0, "No se encontraron interfaces");
		return list;
	}

	private boolean isFileInterface(String file)
	{
		return file.split("\\.").length > 2;
	}
	
	private String getNameInterface(String file)
	{
		String[] f = file.split("\\.");
		return f[0] + "." + f[1] + "." + f[2];
	}
}
