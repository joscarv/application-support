package com.sanborns.supportsap.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DAODelivery 
{
	public List<Delivery> getDelivery(String date)
	{
		String query = "SELECT tienda_dt AS Unidad, SUM(importe_bruto_di) AS Importe "
				+ "FROM delivery_tran_dt, delivery_item_di "
				+ "WHERE tienda_di=tienda_dt "
				+ "AND terminal_di=terminal_dt "
				+ "AND transaccion_di=transaccion_dt "
				+ "AND fecha_di=CONVERT(VARCHAR,fecha_dt,112) "
				+ "AND CONVERT(VARCHAR,fecha_dt,112)='" + date + "'"
				+ "GROUP BY tienda_dt";
		List<Delivery> deliverys = new ArrayList<>();
		Connection59 server59 = Connection59.getInstance();
		if(server59.getIsConnected())
		{
			List<Map<String, Object>> list =  server59.execute(query);
			list.forEach(d -> deliverys.add(initializeDelivery(d)));
		}
		return deliverys;
	}
	
	private Delivery initializeDelivery(Map<String, Object> map)
	{
		Delivery delivery = new Delivery((int)map.get("Unidad"), 
				((java.math.BigDecimal)map.get("Importe")).doubleValue());
		return delivery;
	}
}
