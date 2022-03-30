package com.sanborns.supportsap.model;

public class ConnectionSAP extends Crud
{	
	public ConnectionSAP(String host, String database)
	{
		super("jdbc:sqlserver://" + host + ":1433;databaseName=" + database,"sa","Sa@p0$d3$");
	}	
}
