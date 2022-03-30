package com.sanborns.supportsap;

import java.util.List;

import com.sanborns.supportsap.control.WindowDesktop;

public class App 
{	
    public static void main( String...args )
    {    	
    	java.awt.EventQueue.invokeLater(new Runnable() 
    	{
			@Override
			public void run() 
			{
				new WindowDesktop().setVisible(true);
			}
		});
    }
}
