package com.sanborns.supportsap.model;

public class Connection24 extends Crud
{	
	private static Connection24 instance = null;
	private static boolean isConnected;
	
	private Connection24()
	{
		super("jdbc:sqlserver://10.128.10.24:1433;databaseName=Db_Util", "SapPsi", "kTIX3wxO8?");
		isConnected = getIsConnected();
		System.out.println("Inicia una instancia de Connection24");
	}
	
	public static synchronized Connection24 getInstance()
	{
		if( instance == null || !isConnected )
		{
			instance = new Connection24();
		}
		return instance;
	}
}
