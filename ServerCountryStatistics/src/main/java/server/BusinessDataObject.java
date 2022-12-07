package server;

public class BusinessDataObject {
	
	private String countryCode;
	private String fromYear;
	private String toYear;
	
	public void setCountryCode(String s) {
		this.countryCode = s;
	}
	
	public void setFromYear(String year) {
		this.fromYear = year;
	}
	
	public void setToYear(String year) {
		this.toYear = year;
	}
	
	public String getFromYear() {
		return this.fromYear;
	}
	
	public String getToYear() {
		return this.toYear;
	}
	
	public String getCountryCode() {
		return this.countryCode;
	}

}
