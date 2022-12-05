package server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.util.HashMap;
import java.util.Map;

public class Server {
	
	public static void main(String[] args) throws Exception {
		System.out.println("Server Starting...");
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		System.out.println("Server Active!");
		server.createContext("/WBAnalysis", new WBServerHandler());
		server.setExecutor(null);
		server.start();
	}
	
	static class WBServerHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			// TODO Auto-generated method stub
			
		}
		
	}
}
