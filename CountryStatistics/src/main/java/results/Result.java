package results;

public abstract class Result {
	private String title;
	
	/**
	 * Add a title
	 * @param s String value of title 
	 */
	public void addTitle(String s) {
		this.title = s;
	}
	
	/**
	 * Get a title
	 * @return title String value of title
	 */
	public String getTitle() {
		return this.title;
	}
}
