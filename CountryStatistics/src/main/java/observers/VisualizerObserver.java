package observers;

import viewers.IViewer;
import java.util.Vector;

public class VisualizerObserver implements Observer {
	
	private static int tracker = 0;
	private int observerID; 
	private Vector<IViewer> viewers;
	
	public VisualizerObserver(Vector<IViewer> viewers) {
		this.observerID = ++tracker;
		this.viewers = viewers;
		System.out.println("Observer created: " + this.observerID);
	}

	@Override
	public void update() {
	}
	
}