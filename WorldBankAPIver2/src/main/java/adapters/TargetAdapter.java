package adapters;

import client.UserSelection;

public interface TargetAdapter {
	
	public Object fetchData(UserSelection selection, String analysisTypeCode);
	

}
