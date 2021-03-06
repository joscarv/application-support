package com.sanborns.supportsap.view;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.sanborns.supportsap.model.Ctg_Store;
import com.sanborns.supportsap.model.DAOCtg_Store;
import com.sanborns.supportsap.model.DAOServer_State;
import com.sanborns.supportsap.model.Server_State;
import com.sanborns.supportsap.utils.Dates;

public class InternalOpenStore extends InterfaceReview 
{
	private static final long serialVersionUID = 1L;
	
	private int porcent;
	private int error;
	private int length;

	public InternalOpenStore()
	{
		this.setTitle("Unidades abiertas");
		this.button.setText("Buscar unidades abiertas");
		porcent = 0;
		error = 0;
		length = 0;
		setSize(600,500);
	}

	@Override
	public void initButton()
	{
		buttonPressed = false;
		appendLine("<< " + Dates.getNow() + " INICIA BUSQUEDA DE UNIDADES ABIERTAS >>");
		appendLine("");
		
		List<Ctg_Store> stores = new DAOCtg_Store().getSanborns().stream().filter(f -> f.getIdStore() != 881).collect(Collectors.toList());
		length = stores.size();
		if(stores.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Sin conexion a 10.128.10.24 (Info Tiendas)", "Error Server 24", JOptionPane.ERROR_MESSAGE);
			button.setEnabled(true);
			return;
		}

		stores.forEach(this::statusStore);

		appendLine("");
		appendLine("Unidades Revisadas: " + stores.size());
		appendLine("Unidades Cerradas: " + (stores.size() - error));
		appendLine("Unidades Abiertas: " + error);
		
		appendLine("");
		appendLine("<< " + Dates.getNow() + " FINALIZA BUSQUEDA DE UNIDADES ABIERTAS >>");
		button.setEnabled(true);
	}
	
	private void statusStore(Ctg_Store store)
	{
		int idStore = store.getIdStore() + 1000;
		Server_State server = new DAOServer_State().getOpenStore(store.getIpSap());
		if(server == null)
		{
			error++;
			appendLine("Unidad " + idStore + ": Sin conexión");
			progressBar.setValue(++porcent*100/length);
			return;
		}
		
		if(server.getStoreopen().contains("Y"))
		{
			appendLine("Unidad " + idStore + ": Abierta con businessdate " + server.getBusinessdateString());
			error++;
		}
		else
			appendLine("Unidad " + idStore + ": CERRADA");
		progressBar.setValue(++porcent*100/length);
	}
}
