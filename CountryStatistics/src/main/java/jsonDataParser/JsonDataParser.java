package jsonDataParser;

public class JsonDataParser {
	public IJsonParser JsonParser;
	
	/*
	 * Strategy Pattern
	 */
	public JsonDataParser() {
		
	}
	
	public void setParser(IJsonParser JsonParser) {
		this.JsonParser = JsonParser;
	}
	
	public Object parse() {
		return this.JsonParser.parse();
	}
}
