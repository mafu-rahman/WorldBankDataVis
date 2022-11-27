package jsonDataParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParseYears implements IJsonParser{

	private String file;
	public JsonParseYears(String file) {
		this.file = file;
	}
	
	/**
	 * Parse Data
	 * @return years
	 */
	@SuppressWarnings("unchecked")
	public Object parse() {
		JSONParser jsonParser = new JSONParser();
		ArrayList<Integer> tempYears = new ArrayList<>();
		Vector<Integer> years = null;
        try{
        	FileReader reader = new FileReader(file);
        	JSONObject yearsJSON = (JSONObject) jsonParser.parse(reader);
        	
        	tempYears = (ArrayList<Integer>) yearsJSON.get("years");
        	years = new Vector<Integer>(tempYears);
     		
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return years;
	}

}
