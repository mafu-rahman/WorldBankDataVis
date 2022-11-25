package viewers;

import javax.swing.JPanel;

import results.*;

public interface IViewer {
	
	public void initialize(JPanel viewPanel);
	public void draw(Result result);

	public void remove(JPanel viewPanel);
	
	public String toString();

}
