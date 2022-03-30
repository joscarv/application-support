package com.sanborns.supportsap.model;

public class Connection59 extends Crud 
{
	private static Connection59 instance;
	
	private Connection59()
	{
		super("jdbc:sqlserver://10.128.10.59:1433;databaseName=SalesAuditSears", "SapPsi", "Pa$$w0rd");
	}
	
	public static synchronized Connection59 getInstance()
	{
		if( instance == null)
		{
			instance = new Connection59();
		}
		return instance;
	}
}
