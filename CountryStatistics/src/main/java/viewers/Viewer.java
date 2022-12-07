package viewers;

import java.util.ArrayList;
import java.util.Vector;

import observers.Observer;

public class Viewer {
	
	private Vector<IViewer> viewers;
	private ArrayList<Observer> observers;
	
	public Viewer() {
		this.viewers = new Vector<>();
	}
	
	public void addViewer(IViewer v) {
		this.viewers.add(v);
	}
	
	public void removeViewer(IViewer v) {
		this.viewers.remove(v);
	}
	
	public Vector<IViewer> getViewers(){
		return this.viewers;
	}
	
	public void register(Observer o) {
		observers.add(o);
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
}