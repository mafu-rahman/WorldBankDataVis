package observers;

import viewers.Viewer;

public class VisualizerObserver implements Observer {
	
	private static int tracker = 0;
	private int observerID; 
	private Viewer viewer;
	
	public VisualizerObserver(Viewer viewer) {
		this.viewer = viewer;
		this.observerID = ++tracker;
		System.out.println("Observer created: " + this.observerID);
		viewer.register(this);
	}

	@Override
	public void update(Viewer viewer) {
		this.viewer = viewer;
		printViewer();
	}
	
	private void printViewer() {
		System.out.println("Added: " + viewer.toString());
	}
	
}