package analysers;

import client.UserSelection;
import results.Result;

public interface IAnalyser {
	
	/**
	 * Calculate data for a specific analysis mode
	 * @param selection data selected by the user composed of the country code, start year and end year
	 * @return calculated result
	 */
	public Result calculate(UserSelection selection);
	
	/**
	 * Method used to process data using parsing or other form of data manipulation 
	 */
	public void processData();
	
	/**
	 * toString method
	 * @return a string 
	 */
	public String toString();
	
}