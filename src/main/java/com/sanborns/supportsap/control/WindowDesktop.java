package com.sanborns.supportsap.control;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
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
import com.sanborns.supportsap.view.InternalSanborns41;
import com.sanborns.supportsap.view.InternalStartEodProc;

public class WindowDesktop extends JFrame
{
	private JDesktopPane desktopPane;
	private JMenuBar menuBar;
	
	private JMenu jmSupport;
	private JMenu jmReview;
	
	private JMenuItem jmiISalesWeb;
	private JMenuItem jmiIOpenStore;
	private JMenuItem jmiIEodProc;
	private JMenuItem jmiIISanborns41;
	
	// VAR INTERNALFRAME NIGHT REVIEW 
	private InterfaceReview iSalesWeb;
	private InterfaceReview iOpenStore;
	private InterfaceReview iEodProc;
	private InterfaceReview iInterfacesSan41;

	private Font font;
	
	public WindowDesktop()
	{
		super("Support Aplication");
		setLookAndFeel("Nimbus");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationJFrame();
		initComponents();
	}
	
	
	private void initComponents()
	{
		setIconImage(new ImageIcon("./img/sabiduria.png").getImage());
		// DESKTOPPANE
		desktopPane = new JDesktopPane();
		setContentPane(desktopPane);
		
		font = new Font("Arial",Font.BOLD,12);
		// INITIALIZE MENUITEM NIGHT REVIEW
		jmiISalesWeb = new JMenuItem("Ventas Internet");
		jmiISalesWeb.addActionListener(actionListener());
		jmiISalesWeb.setFont(font);
		jmiISalesWeb.setIcon(new ImageIcon("./img/www.png"));
		jmiIOpenStore = new JMenuItem("Unidades Abiertas");
		jmiIOpenStore.addActionListener(actionListener());
		jmiIOpenStore.setFont(font);
		jmiIOpenStore.setIcon(new ImageIcon("./img/open.png"));
		jmiIEodProc = new JMenuItem("Inicio de Eodproc");
		jmiIEodProc.addActionListener(actionListener());
		jmiIEodProc.setFont(font);
		jmiIEodProc.setIcon(new ImageIcon("./img/start.png"));
		jmiIISanborns41 = new JMenuItem("Interfaces Sanborns 41");
		jmiIISanborns41.addActionListener(actionListener());
		jmiIISanborns41.setFont(font);
		jmiIISanborns41.setIcon(new ImageIcon("./img/fourty-one.png"));
		
		font = new Font("Arial",Font.BOLD,13);
		// INITIALIZE JMENU
		jmSupport = new JMenu("Soporte");
		jmSupport.setFont(font);
		jmSupport.setIcon(new ImageIcon("./img/support.png"));
		jmReview = new JMenu("Guardia Nocturna");
		jmReview.setFont(font);	
		jmReview.setIcon(new ImageIcon("./img/review.png"));
		jmReview.add(jmiISalesWeb);
		jmReview.add(jmiIOpenStore);
		jmReview.add(jmiIEodProc);
		jmReview.add(jmiIISanborns41);
		
		
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
		iInterfacesSan41 = new InternalSanborns41();
		this.add(iInterfacesSan41);
	}	
	
	private void setLocationJFrame()
	{
		Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
		double height = window.height / 1.2;
		double width = window.width / 1.2;
		setSize((int)width,(int)height);
		setLocationRelativeTo(null);
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
				if(e.getSource() == jmiIISanborns41)
					iInterfacesSan41.setVisible(true);	
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
