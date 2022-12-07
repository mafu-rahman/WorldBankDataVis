package dataFetchers;

import client.UserSelection;
import server.BusinessDataObject;

public interface IFetcher {
	
	/**
	 * Method used to fetch data from API 
	 * @param analysisTypeCode code from which data is to be fetched, for example, "SP.POP.TOTL" 
	 * @return it returns an object of appropriate type depending on the implemented class, exampl, JsonArray
	 */
	public Object fetchData(BusinessDataObject data, String analysisTypeCode);	
}
