package com.sanborns.supportsap.model;

public class Connection59 extends Crud 
{
	private static Connection59 instance;
	private static boolean isConnected;
	
	private Connection59()
	{
		super("jdbc:sqlserver://10.128.10.59:1433;databaseName=SalesAuditSears", "SapPsi", "Pa$$w0rd");
		isConnected = getIsConnected();
		System.out.println("Inicia una instancia de Connection59");
	}
	
	public static synchronized Connection59 getInstance()
	{
		if( instance == null || !isConnected )
		{
			instance = new Connection59();
		}
		return instance;
	}
}
