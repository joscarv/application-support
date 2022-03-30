package com.sanborns.supportsap.model;

import java.util.List;
import java.util.Map;

public class DAOTrn_Venta_Int 
{
	public Trn_Venta_Int getTrn_venta(String host, String date)
	{
		String query = "SELECT CAST(id_tda_vfp AS int) AS id_tda_vfp, SUM(total_vfp) AS total_vfp "
				+ "FROM trn_venta_forma_pago_vfp "
				+ "where date_operacion_vfp = '" + date + "' "
				+ "and id_cajero_vfp in ('777','888') "
				+ "group by id_tda_vfp "
				+ "union "
				+ "select top 1 CAST(id_tda_vfp AS int), 0 as total_vfp from trn_venta_forma_pago_vfp "
				+ "WHERE not exists (select "
				+ "cast(id_tda_vfp as int) as id_tda_vfp, "
				+ "SUM(total_vfp) as total_vfp "
				+ "from trn_venta_forma_pago_vfp "
				+ "where date_operacion_vfp = '" + date + "' "
				+ "and id_cajero_vfp in ('777','888') "
				+ "group by id_tda_vfp)";
		ConnectionSAP sap = new ConnectionSAP(host, "SalesAudit");
		Trn_Venta_Int venta = null;
		if(sap.getIsConnected())
		{
			List<Map<String, Object>> list = sap.execute(query);
			venta = new Trn_Venta_Int((Integer)list.get(0).get("id_tda_vfp"), ((java.math.BigDecimal)list.get(0).get("total_vfp")).doubleValue());
			sap.close();
		}
		return venta;
	}
}
