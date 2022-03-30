package com.sanborns.supportsap.model;

public class Delivery 
{
	private int idStore;
	private double amount;
	
	public Delivery(int idStore, double amount) 
	{
		this.idStore = idStore;
		this.amount = amount;
	}
	
	public int getIdStore() 
	{
		return idStore;
	}
	
	public void setIdStore(int idStore) 
	{
		this.idStore = idStore;
	}
	
	public double getAmount() 
	{
		return amount;
	}
	
	public void setAmount(double amount) 
	{
		this.amount = amount;
	}
	
	@Override
	public String toString()
	{
		return "Unidad: " + idStore + " Importe: " + amount;
	}
}
