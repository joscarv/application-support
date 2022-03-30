package com.sanborns.supportsap.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.sanborns.supportsap.view.InterfaceReview;
import com.sanborns.supportsap.view.InternalOpenStore;
import com.sanborns.supportsap.view.InternalSalesWeb;
import com.sanborns.supportsap.view.InternalStartEodProc;

public class WindowDesktop extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JDesktopPane desktopPane;
	private JMenuBar menuBar;
	
	private JMenu jmSupport;
	private JMenu jmReview;
	
	private JMenuItem jmiISalesWeb;
	private JMenuItem jmiIOpenStore;
	private JMenuItem jmiIEodProc;
	
	// VAR INTERNALFRAME NIGHT REVIEW 
	InterfaceReview iSalesWeb;
	InterfaceReview iOpenStore;
	InterfaceReview iEodProc;
	InterfaceReview iInterfaces41;	
	
	public WindowDesktop()
	{
		super("Support Aplication");
		setLookAndFeel("Nimbus");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		initComponents();
	}
	
	
	
	private void initComponents()
	{
		// DESKTOPPANE
		desktopPane = new JDesktopPane();
		setContentPane(desktopPane);
		
		// INITIALIZE MENUITEM NIGHT REVIEW
		jmiISalesWeb = new JMenuItem("Ventas Internet");
		jmiISalesWeb.addActionListener(actionListener());
		jmiIOpenStore = new JMenuItem("Unidades Abiertas");
		jmiIOpenStore.addActionListener(actionListener());
		jmiIEodProc = new JMenuItem("Inicio de Eodproc");
		jmiIEodProc.addActionListener(actionListener());
		
		// INITIALIZE JMENU
		jmSupport = new JMenu("Soporte");
		jmReview = new JMenu("Guardia Nocturna");
		jmReview.add(jmiISalesWeb);
		jmReview.add(jmiIOpenStore);
		jmReview.add(jmiIEodProc);
		
		// INITIALIZE MENUBAR
		menuBar = new JMenuBar();
		menuBar.add(jmSupport);
		menuBar.add(jmReview);
		this.setJMenuBar(menuBar);
		
		// INITIALIZE INTERNALFRAME NIGHT REVIEW
		iSalesWeb = new InternalSalesWeb();
		this.add(iSalesWeb);
		iOpenStore = new InternalOpenStore();
		this.add(iOpenStore);
		iEodProc = new InternalStartEodProc();
		this.add(iEodProc);
	}
	
	private ActionListener actionListener()
	{
		return new ActionListener() 
		{			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == jmiISalesWeb)
					iSalesWeb.setVisible(true);
				if(e.getSource() == jmiIOpenStore)
					iOpenStore.setVisible(true);
				if(e.getSource() == jmiIEodProc)
					iEodProc.setVisible(true);
			}
		};
	}
	
	private void setLookAndFeel(String lookAndFeel)
	{
		try
		{
			for(UIManager.LookAndFeelInfo look : UIManager.getInstalledLookAndFeels())
			{
				if(lookAndFeel.equals(look.getName()))
				{
					UIManager.setLookAndFeel(look.getClassName());
					break;
				}
			}
		}
		catch(Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage(), "Error al cargar interfaz Ninbus", JOptionPane.WARNING_MESSAGE);
		}
	}
}
