package com.sanborns.supportsap.model;

public class Trn_Venta_Int
{
	private int idStore;
	private double total;

	public Trn_Venta_Int(int idStore, double total)
	{
		this.idStore = idStore;
		this.total = total;
	}
	
	public int getIdStore()
	{
		return idStore;
	}
	
	public void setIdStore(int idStore)
	{
		this.idStore = idStore;
	}

	public double getTotal()
	{
		return total;
	}

	public void setTotal(double total) 
	{
		this.total = total;
	}	
	
	@Override
	public String toString()
	{
		return "Unidad: " + idStore + " monto: $" + total;
	}
}
