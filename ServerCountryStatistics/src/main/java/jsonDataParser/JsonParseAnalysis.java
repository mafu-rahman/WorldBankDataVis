package jsonDataParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParseAnalysis implements IJsonParser{

	
	private String file;
	public JsonParseAnalysis(String file) {
		this.file = file;
	}
	
	/**
	 * Parses from countries.json file to get available countries
	 * @return countries List of all available countries 
	 */
	@SuppressWarnings("unchecked")
	public Object parse() {
		JSONParser jsonParser = new JSONParser();
		ArrayList<String> tempAnalysis = new ArrayList<>();
		Vector<String> countries = null;
        try{
        	FileReader reader = new FileReader(file);
        	JSONObject analysisJSON = (JSONObject) jsonParser.parse(reader);
        	
        	tempAnalysis = (ArrayList<String>) analysisJSON.get("analysis");
        	countries = new Vector<>(tempAnalysis);
     		
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return countries;
	}
	

}
