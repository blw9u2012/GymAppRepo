package com.cs4750.finalproject;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RoomAdapter extends ArrayAdapter<Room>{

	private ArrayList<Room> roomList;
	
	public RoomAdapter(Context context, int textViewResourceId,
			ArrayList<Room> items) {
		super(context, textViewResourceId, items);
		this.roomList = items;
	}

	public ArrayList<Room> getRoomList() {
		return roomList;
	}

	public void setRoomList(ArrayList<Room> roomList) {
		this.roomList = roomList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_item_rooms, null);
		}
		Room r = roomList.get(position);
		if (r != null) {
			TextView tt = (TextView) v.findViewById(R.id.roomName);
			TextView bt = (TextView) v.findViewById(R.id.roomAvailibity);
			if (tt != null) {
				tt.setText("Name: " + r.getName());
			}
			if (bt != null) {
				bt.setText("Availibility: " + r.isAvailable());
			}
		}
		return v;
	}
}
