package adapters;

import client.UserSelection;

/**
 * This is an Adapter interface for Fetcher classes
 * @author mafu
 *
 */
public interface IAdapter {
	
	/**
	 * This method fetches the data according to user selection.
	 * 
	 * @param selection : the Userselection class where all the selected options are stored
	 * @param analysisTypeCode : the unique code for a specific analysis type
	 * @return it returns an Object of anytype suitable with the class that is calling this adapter, eg, Json Object, XML Object
	 */
	public Object fetchData(UserSelection selection, String analysisTypeCode);
	

}
