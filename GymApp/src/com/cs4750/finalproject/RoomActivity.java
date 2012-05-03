package com.cs4750.finalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RoomActivity extends Activity {
	TextView roomTitle, roomFloor, roomOccupancy , roomCapacity, roomAvailability;
	Button reserveButton, unReserveButton;
	String class_id, user_name, user_id, room_id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.room_page_dialog);
		
		Bundle bundle = getIntent().getExtras();
		room_id = bundle.getString("room_id");
		user_name = bundle.getString("user_name");
		
		//get room info...
		new LoadRoomInfo().execute(room_id);
		
		reserveButton = (Button)findViewById(R.id.roomReserveButton);
		unReserveButton = (Button)findViewById(R.id.roomUnReserveButton);
		
		reserveButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				new ReserveRoom().execute("0",room_id);
			}
			
		});
		
		unReserveButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(RoomActivity.this);
				builder.setTitle("Release this Room?");
				builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						new ReserveRoom().execute("1",room_id);
						Intent i = new Intent(RoomActivity.this,RoomTabActivity.class);
						i.putExtra("user_name",user_name);
						startActivity(i);
					}
				});
				builder.setCancelable(true);
				builder.create().show();
				
			}
		});
		roomTitle = (TextView)findViewById(R.id.roomName);
		roomOccupancy = (TextView)findViewById(R.id.roomOccupancy);
		roomFloor = (TextView)findViewById(R.id.roomFloor);
		roomCapacity = (TextView)findViewById(R.id.roomCapacity);		
		roomAvailability = (TextView)findViewById(R.id.roomAvailability);
	}
	private class ReserveRoom extends AsyncTask<String, Void, String>{

 		@Override
 		protected String doInBackground(String... params) {
 			String availibility = params[0];
 			String room_id = params[1];
 			
 			ServerHandler sv = new ServerHandler();
 			String result = sv.changeAvailability(availibility, room_id,"room");
 			return result;
 		}
 		
 		protected void onPostExecute(String result){
 			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
 		}
	}
	
	private class LoadRoomInfo extends AsyncTask<String, Void, String>{
		@Override
		protected String doInBackground(String... params) {
			String room_id = params[0];
			ServerHandler sv = new ServerHandler();
			String result = sv.getRoom(room_id);
			return result;
		}
		
		protected void onPostExecute(String result){
        	String delims = "[,]";
        	String[] tokens = result.split(delims);
        	
        	//String id = tokens[0];
        	String name = tokens[1];
        	String floor = tokens[2];
        	String capacity = tokens[3];
        	String available = tokens[4];
        	
        	if(available.equals("1")){
        		roomAvailability.setText("Availability: true");
        	}
        	else{
        		roomAvailability.setText("Availability: false");
        	}
        	roomTitle.setText("Room: "+name);
        	roomFloor.setText("Floor: "+floor);
        	roomCapacity.setText("Capacity: "+String.valueOf(capacity));
        	roomOccupancy.setText("21");
		}
		
	}
}