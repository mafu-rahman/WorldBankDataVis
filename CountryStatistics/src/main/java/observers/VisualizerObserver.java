package observers;

import viewers.Viewer;

public class VisualizerObserver implements Observer{
	
	private static int tracker = 0;
	private int observerID; 
	private Viewer viewer;
	
	public VisualizerObserver(Viewer viewer) {
		this.viewer = viewer;
		this.observerID = ++tracker;
		System.out.println("Observer created: " + this.observerID);
		viewer.register(this);
	}

	/**
	 * Update The Observer with the new viewer
	 */
	public void update(Viewer viewer) {
		this.viewer = viewer;
		printViewer();
	}
	
	private void printViewer() {
		System.out.println("Observer Active -> Added: " + viewer.toString());
	}
	
}