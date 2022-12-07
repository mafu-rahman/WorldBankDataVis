package observers;

public class VisualizerObserver implements Observer {
	
	private static int tracker = 0;
	private int observerID; 
	
	public VisualizerObserver() {
		this.observerID = ++tracker;
		System.out.println("Observer created: " + this.observerID);
	}

	@Override
	public void update() {
	}

}