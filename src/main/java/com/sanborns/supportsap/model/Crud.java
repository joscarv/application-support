package com.sanborns.supportsap.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.microsoft.sqlserver.jdbc.SQLServerResultSet;

public abstract class Crud 
{
	private Connection connection;
	private final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private boolean isConnected;
	
	public Crud(String url, String user, String passwd)
	{
		try
		{
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, passwd);
			isConnected = true;
		} catch (ClassNotFoundException e)
		{
			isConnected = false;
			e.printStackTrace();
		} catch (SQLException e)
		{
			isConnected = false;
			e.printStackTrace();
		}
	}
	
	protected boolean getIsConnected()
	{
		return isConnected;
	}
	
	protected int update(String query)
	{
		try 
		{
			Statement st = connection.createStatement();
			return st.executeUpdate(query);
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	protected List<Map<String, Object>> execute(String query) 
	{
		SQLServerResultSet rs = null;
		List<Map<String, Object>> list = new ArrayList<>();
		try 
		{
			PreparedStatement st = connection.prepareStatement(query);
			rs = (SQLServerResultSet)st.executeQuery();
			list = listData(rs);
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	private List<Map<String, Object>> listData(SQLServerResultSet rs) 
	{		
		List<Map<String, Object>> rows = new ArrayList<>();
		try 
		{
			int countColumns = rs.getMetaData().getColumnCount();
			while(rs.next()) 
			{
				Map<String, Object> row = new HashMap<>();
				for(int i = 1; i <= countColumns; i++) 
				{
					String key = rs.getMetaData().getColumnName(i);
					Object value = rs.getObject(key);
					row.put(key, value);
				}
				rows.add(row);
			}
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return rows;
	}
	
	protected boolean executeProcedure(String procedure)
	{
		try
		{
			CallableStatement cs = connection.prepareCall("{call " + procedure + "}");
			return cs.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	protected void close()
	{
		try 
		{
			if(connection != null)
				connection.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}