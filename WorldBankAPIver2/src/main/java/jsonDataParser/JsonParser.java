package jsonDataParser;

public class JsonParser {
	public IJsonParser JsonParser;
	
	public JsonParser() {
		
	}
	
	public void setParser(IJsonParser JsonParser) {
		this.JsonParser = JsonParser;
	}
	
	public Object parse() {
		return this.JsonParser.parse();
	}
}
