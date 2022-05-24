package com.sanborns.supportsap.view;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.sanborns.supportsap.model.Ctg_Store;
import com.sanborns.supportsap.model.DAOCtg_Store;
import com.sanborns.supportsap.utils.Dates;
import com.sanborns.supportsap.utils.Config;
import com.sanborns.supportsap.utils.FtpClient;

public class InternalSanborns41 extends InterfaceReview
{
    private int error;
    private int porcent;
    private int length;
    private StringBuilder textError;
    private FtpClient ftpClient;

    public InternalSanborns41()
    {
        setTitle("Validacion de Interfaces Sanborns en Servidor 41");
		button.setText("Buscar interfaces");
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
		textError.append("Revisar las siguientes unidades pendientes:\n\n");
		error = 0;
		appendLine("<< " + Dates.getNow() + " INICIA BUSQUEDA DE INTERFACES SANBORNS EN EL 41 >>");
		appendLine("");
        String date = Dates.getToDay();
        List<String> interfaces41 = ftpClient.dirSanborns41_after(date);
		
		if(interfaces41.size() <= 1)
		{
			appendLine(interfaces41.get(0));
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
			JOptionPane.showMessageDialog(this, "Sin conexion a 10.128.10.24 (Info Tiendas)", "Error Server 24", JOptionPane.ERROR_MESSAGE);
			return;
		}

        stores.forEach(s -> matchFiles(s, interfaces41, date));
		interfaces41.clear();		

		appendLine(textError.toString());
		appendLine("Unidades revisadas:" + stores.size());
		appendLine("Unidades correctas:" + (stores.size() - error));
		appendLine("Unidades con error:" + error);		
        endMessage();
    }

    private void matchFiles(Ctg_Store store, List<String> interfaces41, String date)
    {
        List<String> iLocal = Config.getInstance().getFilesSanborns(store.getIdStore(), date).stream().sorted().collect(Collectors.toList());
		if(iLocal.isEmpty())
		{
			appendLine("Archivo config.conf no encontrado o no tiene interfaces a validar");
			return;
		}
		List<String> iRemote = interfaces41.stream().filter(f -> iLocal.contains(f)).collect(Collectors.toList());
		List<String> dif = iLocal.stream().filter(f -> !iRemote.contains(f)).collect(Collectors.toList());
		progressBar.setValue(++porcent*100/length);
		if(dif.isEmpty())
		{
			appendLine(" Sin archivos pendientes");
		}
		else
		{
			error ++;
			textError.append("Unidad " + (store.getIdStore() + 1000) + ": Faltan los siguientes archivos\n");
			dif.forEach(f -> textError.append(f + "\n"));
			textError.append("\n");
		}
		iLocal.clear();
		iRemote.clear();
		dif.clear();
    }

    private void endMessage()
	{
		appendLine("");
		appendLine("<< " + Dates.getNow() + " FINALIZA BUSQUEDA DE INTERFACES SANBORNS EN EL 41 >>");
		appendLine("");
		porcent = 0;
		error = 0;
		textError = new StringBuilder();
		button.setEnabled(true);
	}
}