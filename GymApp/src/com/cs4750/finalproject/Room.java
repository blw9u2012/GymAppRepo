package com.cs4750.finalproject;

public class Room {

	private String id;
	private String name;
	private String floor;
	private int capacity;
	
	private boolean available;

	public Room(String name, String id, String f, boolean res) {
		this.name = name;
		this.id = id;	
		this.floor = f;
		this.available = res;
	}
	
	public Room(String name, String id, String f, int cap) {
		this.name = name;
		this.id = id;	
		this.floor = f;
		this.capacity = cap;
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

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String f) {
		this.floor = f;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
