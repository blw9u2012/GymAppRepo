package com.cs4750.finalproject;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class RoomTabActivity extends ListActivity{
	private ArrayList<Room> roomList;
	private RoomAdapter roomAdapter;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_tab);
        
        roomList = new ArrayList<Room>();
        roomList.add(new Room("Gym 1",true));
        roomList.add(new Room("Gym 2", false));
        roomList.add(new Room("Multipurpose Room", true));
        
        roomAdapter = new RoomAdapter(RoomTabActivity.this, R.layout.list_item_rooms,roomList);
        setListAdapter(roomAdapter);
        ListView lv = getListView();
        lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				AlertDialog.Builder builder = new AlertDialog.Builder(RoomTabActivity.this);
				builder.setMessage("Reserve this Room?")
				       .setCancelable(true)
				       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//new ChangeMachineAvail().execute("0","4mo1b");
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
        	
        });
        
    }
	@Override
	protected void onResume(){
		super.onResume();
		
        
	}
}
