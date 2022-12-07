package viewers;

public class ViewerFactory {
	
	public IViewer createViewer(String viewer) {
		if(viewer.equals("Bar Chart")) {
			return new BarChart();
		}
		
		else if(viewer.equals("Line Chart")) {
			return new LineChart();
		}
		
		else if(viewer.equals("Pie Chart")) {
			return new PieChart();
		}
		
		else if(viewer.equals("Report")) {
			return new Report();
		}
		
		else if(viewer.equals("Scatter Chart")) {
			return new ScatterChart();
		}		
		
		return null;
	}

}
