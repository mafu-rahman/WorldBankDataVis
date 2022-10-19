package gui;

import javax.swing.JPanel;

public class panel extends JPanel{
	
	public panel() {
		
	}
	
	
	public static JPanel getPanelFactory() {
		return new panel();
	}

}
