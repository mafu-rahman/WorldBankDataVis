package jsonDataParser;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonNull;

public class JsonParseRetrivedData implements IJsonParser{

	private JsonArray data;
	
	public JsonParseRetrivedData(JsonArray data) {
		this.data = data;
	}
	
	@Override
	public Object parse() {
		
		HashMap<String, Double> result = new HashMap<>();
		
		this.data = data.get(1).getAsJsonArray();
		for(int i=0; i<data.size(); i++) {
			String date = data.get(i).getAsJsonObject().get("date").getAsString();
			
			Double value = 0.0;
			if(data.get(i).getAsJsonObject().get("value") != JsonNull.INSTANCE) {
				value = data.get(i).getAsJsonObject().get("value").getAsDouble();
			}
			
			result.put(date, value);
		}

		return result;
	}
}
