package observers;

import viewers.IViewer;

public class VisualizerObserver implements Observer {
	
	private static int tracker = 0;
	private int observerID; 
	private IViewer viewer;
	
	public VisualizerObserver(IViewer viewer) {
		this.viewer = viewer;
		this.observerID = ++tracker;
		System.out.println("Observer created: " + this.observerID);
		viewer.register(this);
	}

	@Override
	public void update(IViewer viewer) {
		this.viewer = viewer;
	}
	
	public IViewer getViewer() {
		return this.viewer;
	}
	
}