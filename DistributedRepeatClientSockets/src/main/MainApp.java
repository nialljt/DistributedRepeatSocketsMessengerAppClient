package main;

import javax.swing.SwingUtilities;

import selectClient.SelectClientFrame;

public class MainApp {

	
	public static void main(String[] args) {
		Runnable CreateAndShowGUI = new Runnable() {
			public void run(){
				new SelectClientFrame();
			}
		};
		SwingUtilities.invokeLater(CreateAndShowGUI);

	}

}
