package com.sanborns.supportsap.view;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.sanborns.supportsap.model.Ctg_Store;
import com.sanborns.supportsap.model.DAOCtg_Store;
import com.sanborns.supportsap.model.DAODelivery;
import com.sanborns.supportsap.model.DAOTrn_Venta_Int;
import com.sanborns.supportsap.model.Delivery;
import com.sanborns.supportsap.model.Trn_Venta_Int;
import com.sanborns.supportsap.utils.Dates;

public class InternalSalesWeb extends InterfaceReview
{
	private static final long serialVersionUID = 1L;

	private int porcent;
	private int error;
	private String textError;
	
	public InternalSalesWeb()
	{
		setTitle("Revision de Ventas Internet dia anterior");
		setSize(600, 500);
		porcent = 0;
		error = 0;
		textError = "";
		button.setText("Buscar Ventas Internet en Tiendas");
	}
	
	@Override
	public void initButton() 
	{
		buttonPressed = false;
		porcent = 0;
		String date = Dates.getYesterday();
		appendLine(Dates.getNow() + " INICIA VALIDACION DE VENTAS INTERNET");
		appendLine("   Fecha de Operacion: " + date);
		appendLine("");
		List<Delivery> deliverys = new DAODelivery().getDelivery(date);
		if(deliverys.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Tiendas Internet 59", "No se pudo obtener tiendas con Ventas Internet del servidor 10.128.10.59", JOptionPane.ERROR_MESSAGE);
			button.setEnabled(true);
			return;
		}
		List<Ctg_Store> stores = new DAOCtg_Store().getSanborns()
									.stream()
									.filter(a -> deliverys.stream().anyMatch(b -> b.getIdStore() == (a.getIdStore()+1000)))
									.collect(Collectors.toList());
		if(stores.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Tiendas Internet 24", "No se pudo obtener info de tiendas del servidor 10.128.10.24", JOptionPane.ERROR_MESSAGE);
			button.setEnabled(true);
			return;
		}
		deliverys.forEach(d -> sapVSint(d, stores, date));
		appendLine("");
		appendLine("Unidades revisadas: " + stores.size());
		appendLine("Unidades correctas: " + (stores.size() - error));
		appendLine("Unidades con error: " + error);
		appendLine("");
		if(error > 0)
		{
			appendLine("Revisar las siguientes Unidades: ");
			appendLine(textError);
			appendLine("");
		}
		appendLine(Dates.getNow() + " FINALIZA VALIDACION DE VENTAS INTERNET");
		button.setEnabled(true);
	}

	private void sapVSint(Delivery d, List<Ctg_Store> stores, String date) 
	{
		int idDelivery = d.getIdStore();
		double amountInt = d.getAmount();
		Ctg_Store store = stores.stream().filter(f -> (f.getIdStore() + 1000) == idDelivery).findFirst().get();
		Trn_Venta_Int trn_Sap = new DAOTrn_Venta_Int().getTrn_venta(store.getIpSap(), date);
		if(trn_Sap == null)
		{
			error++;
			textError += "Unidad " + idDelivery + ": Sin conexión";
			appendLine("Unidad " + idDelivery + ": Sin conexión");
			progressBar.setValue(++porcent*100/stores.size());
			return;
		}
		double amountSap = trn_Sap.getTotal();
		if(amountInt != amountSap)
		{
			error++;
			textError += "REVISAR UNIDAD " + idDelivery + " MontoInt: $" + amountInt + " VS MontoSAP $" + amountSap + "\n";
		}
		appendLine(amountInt == amountSap ? "Unidad " + idDelivery + ": CORRECTA $" + amountInt : "REVISAR UNIDAD " + idDelivery + " MontoInt: $" + amountInt + " VS MontoSAP $" + amountSap);
		progressBar.setValue(++porcent*100/stores.size());
	}
}