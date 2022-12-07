package adapters;

import server.BusinessDataObject;

/**
 * This is an Adapter interface for Fetcher classes
 * @author mafu
 *
 */
public interface IAdapter {
	
	/**
	 * This method fetches the data according to user selection.
	 * @param selection : the UserSelection class where all the selected options are stored
	 * @param analysisTypeCode : the unique code for a specific analysis type
	 * @return returns an Object of any type suitable with the class that is calling this adapter, eg, JSON Object, XML Object
	 */
	public Object fetchData(BusinessDataObject data, String analysisTypeCode);

}
