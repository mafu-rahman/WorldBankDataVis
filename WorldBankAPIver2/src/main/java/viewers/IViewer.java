package viewers;

import javax.swing.JPanel;

public interface IViewer {
	
	public void draw(JPanel viewPanel);
	public void remove(JPanel viewPanel);
	
	public String toString();

}
