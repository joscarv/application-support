package com.sanborns.supportsap.model;

public class Ctg_Store 
{
	private int idStore;
	private int idCompany;
	private String name;
	private String ipSap;
	private int tipo;
	private String ipBackup;
	
	public Ctg_Store(int idStore, int idCompany, String name, String ipSap, int tipo, String ipBackup) 
	{
		this.idStore = idStore;
		this.idCompany = idCompany;
		this.name = name.trim();
		this.ipSap = ipSap.trim();
		this.tipo = tipo;
		this.ipBackup = ipBackup.trim();
	}

	public int getIdStore() 
	{
		return idStore;
	}

	public void setIdStore(int idStore) 
	{
		this.idStore = idStore;
	}

	public int getIdCompany() 
	{
		return idCompany;
	}

	public void setIdCompany(int idCompany) 
	{
		this.idCompany = idCompany;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getIpSap() 
	{
		return ipSap;
	}

	public void setIpSap(String ipSap) 
	{
		this.ipSap = ipSap;
	}

	public int getTipo() 
	{
		return tipo;
	}

	public void setTipo(int tipo) 
	{
		this.tipo = tipo;
	}

	public String getIpBackup() 
	{
		return ipBackup;
	}

	public void setIpBackup(String ipBackup) 
	{
		this.ipBackup = ipBackup;
	}
	
	@Override
	public String toString()
	{
		return "Unidad: " + idStore + " " + name + " ipPRI: " + ipSap + " ipBKP: " + ipBackup;
	}
}
