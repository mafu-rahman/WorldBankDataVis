package jsonDataParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParseSources implements IJsonParser {
	
	private String file;
	public JsonParseSources(String file) {
		this.file = file;
	}

	/**
	 * Parses from sources.json file to get available sources including 
	 * World Bank and OpenCovid API. 
	 * @return list of all available sources
	 */
	@SuppressWarnings("unchecked")
	public Object parse() {
		JSONParser jsonParser = new JSONParser();
		ArrayList<String> tempSources = new ArrayList<>();
		Vector<String> sources = null;
		try {
			FileReader reader = new FileReader(file);
			JSONObject sourcesJSON = (JSONObject) jsonParser.parse(reader);
			
			tempSources = (ArrayList<String>) sourcesJSON.get("sources");
			sources = new Vector<>(tempSources);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return sources;
	}

}
