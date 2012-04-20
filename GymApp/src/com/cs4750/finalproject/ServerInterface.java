package com.cs4750.finalproject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ServerInterface {
	private static String SERVER_URL = "http://people.virginia.edu/~blw9u/cs4750/server.php";
	
	private static String executeHttpRequest(String data){
		String result = "";
		try {
			
			//open the connection to the php "server"....
			URL url = new URL(SERVER_URL);
			URLConnection conn = url.openConnection();
			
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			//Send the post data...
			DataOutputStream dataOut = new DataOutputStream(conn.getOutputStream());
			dataOut.writeBytes(data);
			dataOut.flush();
			dataOut.close();
			
			//get the response from the server and store it in the result string...
			DataInputStream dataIn = new DataInputStream(conn.getInputStream());
			String inputLine;
			while((inputLine = dataIn.readLine()) != null){
				result += inputLine;
			}
			dataIn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String getRooms() throws Exception{
		String data = "command="+URLEncoder.encode("getRooms","UTF-8");
		return executeHttpRequest(data);
	}
}
