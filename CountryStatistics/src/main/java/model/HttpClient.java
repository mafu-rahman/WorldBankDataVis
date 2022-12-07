package model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import results.Result;

public class HttpClient {
		
	public String call(UserSelection selection) {
		String country = selection.getCountryCode();
		String fromYear = selection.getFromYear() + "";
		String toYear = selection.getToYear() + "";
		String source = selection.getSource();
		String analysis = selection.getAnalysis();
		
		String urlString = String.format("http://localhost:8000/%s/?p1=%s&p2=%s&p3=%s&p4=%s", source, analysis,
					country, fromYear, toYear);

		System.out.println(urlString);
		String inline = "";
		
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.connect();
			int responsecode = conn.getResponseCode();
			System.out.println("Response Code " + responsecode);
			
			// IF THE RESPONSE IS 200 OK GET THE LINE WITH THE RESULTS
			if (responsecode == 200) {
				
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				System.out.println("-------- Here is the response to the client: " + inline);
				sc.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block e.printStackTrace();
		}
		
		return inline;
	}
}