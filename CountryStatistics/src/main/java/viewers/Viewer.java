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
	
	/**
	 * Register the observer by adding it to the list of observer
	 * @param observer : observer to add to the list
	 */
	public void register(Observer observer) {
		observers.add(observer);
	}
	
	/**
	 * Unregister an observer by removing it from the list
	 * @param o : observer to remove from the list
	 */
	public void unregister(Observer o) {
		int index = observers.indexOf(o);
		System.out.println("Observer " + (index+1) + " deleted");
		observers.remove(index);
	}
	
	/**
	 *  Notification for when a new viewer is being added
	 */
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