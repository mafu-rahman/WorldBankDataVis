package analysers;

import client.UserSelection;
import results.Result;

public interface IAnalyser {
	
	public Result calculate(UserSelection selection);
	
	public void processData();
	
	public String toString();
	
}