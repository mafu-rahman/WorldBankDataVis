package viewers;

import java.util.ArrayList;
import java.util.Vector;

import observers.Observer;
import observers.VisualizerObserver;

public class Viewer {
	
	private Vector<IViewer> viewers;
	private ArrayList<Observer> observers;
	private static int i=0;
	
	public Viewer() {
		this.viewers = new Vector<>();
		this.observers = new ArrayList<Observer>();
	}
	
	public void addViewer(IViewer v) {
		this.viewers.add(v);
		notifyRegister();
		i++;
	}
	
	public void removeViewer(IViewer v) {
		this.viewers.remove(v);
		notifyRegister();
		i--;
	}
	
	public Vector<IViewer> getViewers(){
		return this.viewers;
	}
	
	public void register(VisualizerObserver visualizerObserver) {
		observers.add(visualizerObserver);
	}
	
	public void unregister(Observer o) {
		int index = observers.indexOf(o);
		System.out.println("Observer " + (index+1) + " deleted");
		observers.remove(index);
	}
	
	public void notifyRegister() {
		for(Observer o : observers) {
			o.update(this);
		}
	}
	
	@Override
	public String toString() {
		return this.viewers.get(i).toString();
	}
}