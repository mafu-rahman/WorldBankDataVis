package dataFetchers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.Vector;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import analysers.*;
import client.UserSelection;
import jsonDataParser.DATAPARSER;

public interface Fetcher {
	
	
	/**
	 * Method used to fetch data from API 
	 * @param analysisTypeCode code from which data is to be fetched, for example, "SP.POP.TOTL" 
	 * @return retrievedJsonArray JSONArray of data that has been fetched from the API 
	 */
	public Object fetchData(UserSelection selection, String analysisTypeCode);	
}
