package com.sanborns.supportsap.utils;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FtpClient 
{
	private final String server41 = "10.128.10.41";
	private final String userSanborns = "SanbSAP";
	private final String passSanborns = "S4nbS4p";
	private final String userCafe = "SanbCafeSAP";
	private final String passCafe = "S4nbC4feS4P";
	
	private final String server24 = "10.128.10.24";
	private final String userEodProc = "psiftp";
	private final String passEodProc = "Pa$$word1";	
	
	private org.apache.commons.net.ftp.FTPClient client;
	
	public FtpClient()
	{
		client = new org.apache.commons.net.ftp.FTPClient();
	}
	
	public List<String> dirSanborns41()
	{
		List<String> list = new ArrayList<>();
		try 
		{
			client.connect(server41);
			if(client.login(userSanborns, passSanborns))
			{				
				String[] temp = client.listNames();
				list = Arrays.asList(temp).
						stream().
						filter(f -> f.split("\\.").length > 2).
						map(f -> f.split("\\.")[0] + "." + f.split("\\.")[1] + "." + f.split("\\.")[2]).
						collect(Collectors.toList());					
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
	
	public List<String> dirSanborns41_after()
	{
		String userSanborns = "Soporte";
		String passSanborns = "S0p0rt3";
		List<String> list = new ArrayList<>();
		try 
		{
			client.connect(server41);
			if(client.login(userSanborns, passSanborns))
			{
				client.changeWorkingDirectory("/Temporales/Temporal_Sanborns");
				String[] temp = client.listNames();
				list = Arrays.asList(temp).
							stream().
							filter(f -> f.split("\\.").length > 2).
							map(f -> f.split("\\.")[0] + "." + f.split("\\.")[1] + "." + f.split("\\.")[2]).
							collect(Collectors.toList());
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
	
	public List<String> dirCafe41()
	{
		List<String> list = new ArrayList<>();
		try 
		{
			client.connect(server41);
			if(client.login(userCafe, passCafe))
			{			
				String[] temp = client.listNames();
				list = Arrays.asList(temp).
						stream().
						filter(f -> f.split("\\.").length > 2).
						map(f -> f.split("\\.")[0] + "." + f.split("\\.")[1] + "." + f.split("\\.")[2]).
						collect(Collectors.toList());
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
	
}
