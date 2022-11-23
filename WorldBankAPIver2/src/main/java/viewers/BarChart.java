package viewers;

public class BarChart implements IViewer{

	@Override
	public void draw() {
		System.out.println("Drawing using BarChart Viewer");

		
	}
	
	public String toString() {
		return "Bar Chart";
	}

}
