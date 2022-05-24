package com.sanborns.supportsap.view;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.sanborns.supportsap.model.Ctg_Store;
import com.sanborns.supportsap.model.DAOCtg_Store;
import com.sanborns.supportsap.utils.Dates;
import com.sanborns.supportsap.utils.FtpClient;

public class InternalStartEodProc extends InterfaceReview 
{
	private static final long serialVersionUID = 1L;
	private int porcent;
	private int length;
	private int error;
	private StringBuilder textError;
	private FtpClient ftpClient;
	
	public InternalStartEodProc()
	{
		setTitle("Validacion de inicio de EodProc.bat");
		button.setText("Validar inicio de EodProc");
		setSize(600,500);
		porcent = 0;
		error = 0;
		textError = new StringBuilder();
		ftpClient = new FtpClient();
	}
	
	@Override
	public void initButton() 
	{		
		buttonPressed = false;
		appendLine("<< " + Dates.getNow() + " - INICIA VALIDACION DE INICIO EODPROC >>");
		appendLine("");
		
		List<String> eodprocs = ftpClient.dirEodProc();
		
		if(eodprocs.size() == 0)
		{
			appendLine("NO HAY UNIDADES CON EODPROC INICIADO");
			progressBar.setValue(100);
			endMessage();
			return;
		}
		
		if(eodprocs.size() == 1)
		{
			appendLine(eodprocs.get(0));
			progressBar.setValue(100);
			endMessage();
			return;
		}
			
		List<Ctg_Store> stores = new DAOCtg_Store().getSanborns().stream().filter(f -> f.getIdStore() != 881).collect(Collectors.toList());
		length = stores.size();
		if(stores.isEmpty())
		{
			appendLine("No se pudo obtener info de tiendas desde servidor 10.128.10.24");			
			endMessage();
			JOptionPane.showMessageDialog(this, "Tiendas Internet 24", "No se pudo obtener info de tiendas desde servidor 10.128.10.24", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String date = Dates.getToDay();
		stores.forEach(s -> existStarteod(s, eodprocs, date));
		if(error > 0)
		{
			appendLine("Revisar las siguientes Unidades: \n");
			appendLine(textError.toString());
		} else
		{
			appendLine("Todas las unidades iniciaron EODPROC");
		}		
		appendLine("");
		appendLine("Unidades revisadas: " + stores.size());
		appendLine("Unidades correctas: " + (stores.size() - error));
		appendLine("Unidades con error: " + error);
		endMessage();
	}
	
	private void existStarteod(Ctg_Store store, List<String> eodprocs, String date)
	{
		int idStore = store.getIdStore() + 1000;
		String fileStore = "STARTEOD." + idStore + "." + date + ".FLG";
		if(!eodprocs.contains(fileStore))
		{
			error++;
			textError.append("Unidad " + idStore + ": SIN EODPROC INICIADO\n");			
		}
		progressBar.setValue(++porcent*100/length);
	}
	
	private void endMessage()
	{
		appendLine("");
		appendLine("<< " + Dates.getNow() + " - FINALIZA VALIDACION DE INICIO EODPROC >>");
		appendLine("");
		porcent = 0;
		error = 0;
		textError = new StringBuilder();
		button.setEnabled(true);
	}
}
