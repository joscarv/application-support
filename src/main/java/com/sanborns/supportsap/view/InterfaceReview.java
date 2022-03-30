package com.sanborns.supportsap.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public abstract class InterfaceReview extends JInternalFrame implements ActionListener, Runnable
{

	private static final long serialVersionUID = 1L;
	protected boolean buttonPressed;
	protected JButton button;
	protected JProgressBar progressBar;
	private JTextArea text;
	private JScrollPane jsp;
	private JPanel panel;
	
	public InterfaceReview()
	{
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setResizable(true);
		setClosable(true);
		
		buttonPressed = false;
		button = new JButton();
		button.addActionListener(this);
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		text = new JTextArea();
		text.setEditable(false);
		jsp = new JScrollPane(text);
		panel = new JPanel();
		
		panel.setBackground(new Color(17,13,13));
		panel.setLayout(new FlowLayout());
		panel.add(button);
		
		setLayout(new BorderLayout());
		add(panel, BorderLayout.NORTH);
		add(jsp, BorderLayout.CENTER);
		add(progressBar, BorderLayout.SOUTH);
		
		Thread t = new Thread(this);
		t.start();
	}
	
	public abstract void initButton();

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		buttonPressed = true;
		button.setEnabled(false);
	}

	@Override
	public void run() 
	{	
		while(true)
		{
			try
			{
				Thread.sleep(50);
				if(buttonPressed)
					initButton();
			} catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	protected void appendLine(String text)
	{
		this.text.append(text + "\n");
		this.text.setCaretPosition(this.text.getDocument().getLength());
	}
}
