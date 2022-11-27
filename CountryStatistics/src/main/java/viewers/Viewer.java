package viewers;

import java.util.Vector;

public class Viewer {
	
	private Vector<IViewer> viewers;
	
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
}