package viewers;

import javax.swing.JPanel;

import results.*;

public interface IViewer {
	
	/**
	 * Initialize the viewer for the visualization method being used such as a
	 * bar chart, report, line chart, etc.
	 * @param viewPanel panel to use for initializing the viewer
	 */
	public void initialize(JPanel viewPanel);
	
	/**
	 * Draw the visualization onto the panel
	 * @param result Title of analysis
	 */
	public void draw(Result result);

	/**
	 * Remove a viewer from the window
	 * @param viewPanel panel to remove 
	 */
	public void remove(JPanel viewPanel);
	
	/**
	 * toString method
	 * @return string String value
	 */
	public String toString();

}
