package jsonDataParser;

public class JsonDataParser {
	public IJsonParser JsonParser;
	
	public JsonDataParser() {
		
	}
	
	public void setParser(IJsonParser JsonParser) {
		this.JsonParser = JsonParser;
	}
	
	public Object parse() {
		return this.JsonParser.parse();
	}
}
