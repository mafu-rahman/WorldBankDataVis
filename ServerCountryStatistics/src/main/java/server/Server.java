package server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import adapters.AdapterFactory;
import adapters.IAdapter;
import analysers.AnalysisFactory;
import analysers.IAnalyser;
import results.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Server {
    // The server with the analyses runs and gets requests with parameters from your program 
	// creates the appropriate analysis by examining the URL parameters 
	// (see the call to the analysis factory) and then calls the appropriate analysis 
	// by calling its perform method
	public static void main(String[] args) throws Exception {
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		System.out.println("server started at ");
		server.createContext("/WorldBank", new WBServerHandler());
		server.createContext("/OpenCovid", new OpenCovid());
		server.setExecutor(null); // creates a default executor
		server.start();
	}

	static class WBServerHandler implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {
			
			System.out.println("WB Handler is called ");
			Map<String, String> params = queryToMap(t.getRequestURI().getQuery());
			String analysisParameter = params.get("p1");
			String countryParameter = params.get("p2");
			String fromYearParameter = params.get("p3");
			String toYearParameter = params.get("p4");
			
			AnalysisFactory analysisFactory = new AnalysisFactory();
			AdapterFactory adapterFactory = new AdapterFactory();
			IAdapter adapter = adapterFactory.createAdapter("WorldBank");
			IAnalyser analysis = analysisFactory.createAnalyser(analysisParameter, adapter);

			BusinessDataObject data = new BusinessDataObject();
			data.setCountryCode(countryParameter);
			data.setFromYear(fromYearParameter);
			data.setToYear(toYearParameter);
			
			Result result = (Result) analysis.calculate(data);
			
			Gson gson = new Gson();
			String json = gson.toJson(result);
						
			System.out.println(json);
			
			t.sendResponseHeaders(200, json.length());
			OutputStream os = t.getResponseBody();
			os.write(json.getBytes());
			os.close();
		}
	

		public Map<String, String> queryToMap(String query) {
			if (query == null) {
				return null;
			}
			Map<String, String> result = new HashMap<>();
			for (String param : query.split("&")) {
				String[] entry = param.split("=");
				if (entry.length > 1) {
					result.put(entry[0], entry[1]);
				} else {
					result.put(entry[0], "");
				}
			}
			return result;
		}
	}
	
	
	static class OpenCovid implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {
			
			Map<String, String> params = queryToMap(t.getRequestURI().getQuery());
			String analysisParameter = params.get("p1");
			String countryParameter = params.get("p2");
			String fromYearParameter = params.get("p3");
			String toYearParameter = params.get("p4");
			
			if(!countryParameter.equals("CAN")) {
				System.out.println("Covid cases available for Canada only");
			}
			
			AnalysisFactory analysisFactory = new AnalysisFactory();
			AdapterFactory adapterFactory = new AdapterFactory();
			IAdapter adapter = adapterFactory.createAdapter("OpenCovid");
			IAnalyser analysis = analysisFactory.createAnalyser(analysisParameter, adapter);

			BusinessDataObject data = new BusinessDataObject();
			//data.setCountryCode(countryParameter);
			data.setFromYear(fromYearParameter);
			data.setToYear(toYearParameter);
			
			Result result = (Result) analysis.calculate(data);
			
			Gson gson = new Gson();
			String json = gson.toJson(result);
						
			System.out.println(json);
			
			t.sendResponseHeaders(200, json.length());
			OutputStream os = t.getResponseBody();
			os.write(json.getBytes());
			os.close();
		}
	

		public Map<String, String> queryToMap(String query) {
			if (query == null) {
				return null;
			}
			Map<String, String> result = new HashMap<>();
			for (String param : query.split("&")) {
				String[] entry = param.split("=");
				if (entry.length > 1) {
					result.put(entry[0], entry[1]);
				} else {
					result.put(entry[0], "");
				}
			}
			return result;
		}
	}
}
