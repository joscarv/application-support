package com.sanborns.supportsap.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DAOCtg_Store 
{
	public List<Ctg_Store> getSanborns() 
	{
		String query = "SELECT Id_Store, Id_Company, Desc_Store, Ip_Sap, tipo, Ip_Sap_Backup FROM Ctg_Store "
				+ "WHERE id_Company = 1 AND id_Store NOT IN (359,360,3000)";						
		return getList(query);
	}
	
	public List<Ctg_Store> getMixup() 
	{
		String query = "SELECT Id_Store, Id_Company, Desc_Store, Ip_Sap, tipo, Ip_Sap_Backup FROM Ctg_Store "
				+ "WHERE id_Company = 2 AND id_Store NOT IN (700)";
		return getList(query);
	}
	
	public List<Ctg_Store> getCafe() 
	{
		String query = "SELECT Id_Store, Id_Company, Desc_Store, Ip_Sap, tipo, Ip_Sap_Backup FROM Ctg_Store "
				+ "WHERE id_Company = 4 AND id_Store NOT IN (3001)";
		return getList(query);
	}
	
	public List<Ctg_Store> getIshop() 
	{
		String query = "SELECT Id_Store, Id_Company, Desc_Store, Ip_Sap, tipo, Ip_Sap_Backup FROM Ctg_Store "
				+ "WHERE id_Company = 6 AND id_Store NOT IN (3002)";
		return getList(query);
	}
	
	public List<Ctg_Store> getAllStores() 
	{
		String query = "SELECT Id_Store, Id_Company, Desc_Store, Ip_Sap, tipo, Ip_Sap_Backup FROM Ctg_Store "
				+ "WHERE id_Store NOT IN (700, 3000, 3001, 3002) ORDER BY Id_Company, Id_Store";
		return getList(query);
	}
	
	public List<Ctg_Store> getList(String query)
	{
		Connection24 server24 = Connection24.getInstance();
		List<Ctg_Store> stores = new ArrayList<>();
		if(server24.getIsConnected())
		{
			List<Map<String, Object>> list = server24.execute(query);
			list.forEach(s -> stores.add(initializeCtg_Store(s)));
		}
		return stores;
	}
	
	private Ctg_Store initializeCtg_Store(Map<String, Object> map)
	{
		Ctg_Store store = new Ctg_Store(
				(Integer)map.get("Id_Store"),
				(Integer)map.get("Id_Company"), 
				(String)map.get("Desc_Store"),
				(String)map.get("Ip_Sap"),
				(Integer)map.get("tipo"),
				(String)map.get("Ip_Sap_Backup"));
		return store;
	}
}
