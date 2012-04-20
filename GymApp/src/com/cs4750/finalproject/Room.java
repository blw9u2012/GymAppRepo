package com.cs4750.finalproject;

public class Room {

	private String id;
	private String name;
	private int room_number;
	private int floor;
	
	private boolean available;

	public Room(String name, String id, int room_number, int floor, boolean available) {
		this.name = name;
		this.id = id;
		this.room_number = room_number;
		this.floor = floor;
		this.available = available;
	}
	
	public Room(String name, boolean avail ){
		this.name = name;
		this.available = avail;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRoom_number() {
		return room_number;
	}

	public void setRoom_number(int room_number) {
		this.room_number = room_number;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
}
