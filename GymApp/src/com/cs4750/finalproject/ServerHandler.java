package com.cs4750.finalproject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class ServerHandler {
	
	private static final String server = "http://people.virginia.edu/~blw9u/cs4750/server.php";
	String result;
	ArrayList<String> dataResult;
	ArrayList<NameValuePair> nameValuePairs;
	HttpClient httpClient;

	public ServerHandler(){
		httpClient = new DefaultHttpClient();
		nameValuePairs = new ArrayList<NameValuePair>();
	}

	public String createPostRequest(String data){
		nameValuePairs.add(new BasicNameValuePair("command",data));
		String line;
		try {
			HttpPost httpPost = new HttpPost(server);
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse httpResponse  = httpClient.execute(httpPost);
			HttpEntity entity = httpResponse.getEntity();
			InputStream is = entity.getContent();
			
			//get response string...
			BufferedReader bf = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			while((line = bf.readLine()) != null){        
				sb.append(line + "\n");
				dataResult.add(line);
			}
			is.close();
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		nameValuePairs.clear();
		return result;
	}
	
	public ArrayList<String> createPostRequestArrayList(String data){
		dataResult = new ArrayList<String>();
		nameValuePairs.add(new BasicNameValuePair("command",data));
		String line;
		try {
			HttpPost httpPost = new HttpPost(server);
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse httpResponse  = httpClient.execute(httpPost);
			HttpEntity entity = httpResponse.getEntity();
			InputStream is = entity.getContent();
			
			//get response string...
			BufferedReader bf = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			while((line = bf.readLine()) != null){        
				sb.append(line + "\n");
				dataResult.add(line);
			}
			is.close();
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		nameValuePairs.clear();
		return dataResult;
	}
	
	public String checkIn(String userId, String classId ){
		nameValuePairs.add(new BasicNameValuePair("command","checkIn"));
		nameValuePairs.add(new BasicNameValuePair("user_id",userId));
		nameValuePairs.add(new BasicNameValuePair("class_id",classId));
		String line;
	
		try {
			HttpPost httpPost = new HttpPost(server);
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity entity = httpResponse.getEntity();
			InputStream is = entity.getContent();
			//get response string...
			BufferedReader bf = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			while((line = bf.readLine()) != null){        
				sb.append(line + "\n");
			}

			is.close();
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//for machines
	public String changeAvailibility(String avail, String id){
		nameValuePairs.add(new BasicNameValuePair("command","changeAvailibility"));
		nameValuePairs.add(new BasicNameValuePair("avail",avail));
		nameValuePairs.add(new BasicNameValuePair("id",id));
		String line;
		try{
			HttpPost httpPost = new HttpPost(server);
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity entity = httpResponse.getEntity();
			InputStream is = entity.getContent();
			
			//get response string...
			BufferedReader bf = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			while((line = bf.readLine()) != null){        
				sb.append(line + "\n");
			}

			is.close();
			result = sb.toString();
		} catch (Exception e) {
		e.printStackTrace();
	}
	nameValuePairs.clear();
	return result;
		
	}
	
	
	//nameValuePairs.add(new BasicNameValuePair("command","getRooms"));
	
/*	try{
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://people.virginia.edu/~blw9u/cs4750/server.php");
		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		HttpResponse response= httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		InputStream is = entity.getContent();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = "";
		while((line = reader.readLine()) != null){
			sb.append(line + "\n");
		}
		is.close();
		result = sb.toString();
} catch(Exception e){
	e.printStackTrace();
}*/
}
