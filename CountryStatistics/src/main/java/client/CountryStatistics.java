package client;


import gui.LoginUI;
import observers.VisualizerObserver;


/**
 * This is the main client class.
 * ALl the viewers are setup here
 * All the analysets with their specific adapters are initialised here
 * @author mafu
 *
 */
public class CountryStatistics {
	
	/**
	 * Main method for client access
	 * @param args
	 */
	public static void main(String args[]) {
		System.out.println("Country Statistics Application");
		System.out.println("-------------------------------");
		System.out.println("");
		
		new LoginUI();
	}

}
