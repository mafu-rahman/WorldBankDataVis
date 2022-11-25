package results;

public abstract class Result {
	private String title;
	
	public void addTitle(String s) {
		this.title = s;
	}
	
	public String getTitle() {
		return this.title;
	}
}
