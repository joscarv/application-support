package com.sanborns.supportsap.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class DAOServer_State 
{
	public Server_State getOpenStore(String host)
	{
		String query = "SELECT businessdate, storeopen FROM Server_State";
		ConnectionSAP sap = new ConnectionSAP(host, "backoff");
		Server_State server = null;
		if(sap.getIsConnected())
		{
			List<Map<String, Object>> list = sap.execute(query);
			server = new Server_State((Date)list.get(0).get("businessdate"), (String)list.get(0).get("storeopen"));
			sap.close();			
		}
		sap = null;
		return server;
	}
}
