package com.cs4750.finalproject;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RoomTabActivity extends ListActivity{
	private ArrayList<Room> roomList;
	private RoomAdapter roomListAdapter;
	String user_name;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_tab);
		Bundle bundle = getIntent().getExtras();
		user_name = bundle.getString("user_name");
		
		TextView userTV = (TextView)findViewById(R.id.pageusername);
		userTV.setText(user_name);
        
        roomList = new ArrayList<Room>();

        ListView lv = getListView();
        new LoadRooms().execute(new ArrayList<Room>());
        lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position,
					long id) {
				final String[] users = {"FullTimeStaff","PesonalTrainers","MassageTherapist","DropInInstructor","Nutritionist","Club"};
				
				AlertDialog.Builder roomAlertBox = new AlertDialog.Builder(RoomTabActivity.this);
				roomAlertBox.setTitle("Are you a:");
				roomAlertBox.setSingleChoiceItems(users, -1, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//validate user...
						String user_type = users[which];
						Room r = roomList.get(position);
						
						new ValidateUser().execute(user_type, r.getId());
						
					}
				});
				roomAlertBox.create().show();
			}
        	
        });
 /*       lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
				AlertDialog.Builder builder = new AlertDialog.Builder(RoomTabActivity.this);
				builder.setMessage("Reserve this Room?")
				       .setCancelable(true)
				       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Room r = roomList.get(position);
							if(r.isAvailable()){
								new ChangeRoomAvail().execute("0",r.getId());
								
								//reset list
								roomList.clear();
								new LoadRooms().execute(roomList);
								
								
							}
							else{
								new ChangeRoomAvail().execute("1",r.getId());
								
								//reset list
								roomList.clear();
								new LoadRooms().execute(roomList);
							}
							
						}
					})
				       .setNegativeButton("No", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
							
						}
					});
				AlertDialog alert = builder.create();
				alert.show();
				
			}
        	
        });*/
        
    }
	@Override
	protected void onResume(){
		super.onResume();
		
        
	}
	
    private class LoadRooms extends AsyncTask <ArrayList<Room>, Void, ArrayList<Room>>{

		@Override
		protected ArrayList<Room> doInBackground(ArrayList<Room>... params) {
	        ServerHandler sv = new ServerHandler();
	        
	        ArrayList<Room> rooms = params[0];
	        ArrayList<String> result = sv.createPostRequestArrayList("getRooms");
	        for(int i = 0; i < result.size(); i++){
	        	String rm = result.get(i);
	        	rm.trim();
	        	String delims = "[,]";
	        	String[] tokens = rm.split(delims);
	        	
	        	String id = tokens[0];
	        	String name = tokens[1];
	        	String floor = tokens[2];
	        	int capacity = Integer.parseInt(tokens[3]);
	        	int available = Integer.parseInt(tokens[4]);

	        	//if res = 1 then the room is reserved meaning that the room is not available, if res = 0
	        	//then the room is not reserved meaning th room is available...
	        	Room room = new Room(name,id,floor,capacity);
	        	if(available == 0){
	        		room.setAvailable(false);
	        	}
	        	else{
	        		room.setAvailable(true);
	        	}
	        	
	        	roomList.add(room);
	        	rooms = roomList;
	        	
	        }
			return rooms;
		}
    	protected void onPostExecute(ArrayList<Room> result){
    		roomListAdapter = new RoomAdapter(RoomTabActivity.this, R.layout.list_item_rooms,roomList);
    		roomListAdapter.notifyDataSetChanged();
            setListAdapter(roomListAdapter);
    		
    	}
    }
    private class ValidateUser extends AsyncTask<String, Void,String>{
    	String room_id;
		@Override
		protected String doInBackground(String... params) {
			ServerHandler sv = new ServerHandler();
			String user_type = params[0];
		    room_id = params[1];
			String result = sv.validateUser(user_name, user_type);
			return result;
		}
		
		protected void onPostExecute(String result){
			if(result.equals("Failed")){
				Toast.makeText(RoomTabActivity.this, "You do not have permission to reserve a room :(", Toast.LENGTH_LONG).show();
			}
			else{
				Intent i = new Intent(RoomTabActivity.this, RoomActivity.class);
				i.putExtra("room_id", room_id);
				i.putExtra("user_name", user_name);
				startActivity(i);
			}
		}
    	
    }
    private class ChangeRoomAvail extends AsyncTask <String, Void, String>{

 		@Override
 		protected String doInBackground(String... params) {
 			String availibility = params[0];
 			String id = params[1];
 			
 			ServerHandler sv = new ServerHandler();
 			String result = sv.changeAvailability(availibility, id,"room");
 			return result;
 		}
 		
 		protected void onPostExecute(String result){
 			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT);
 		}
     	
     }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.room_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.RefreshList:
	        	roomList.clear();
	        	new LoadRooms().execute(roomList);
	        	return true;
	        	
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
