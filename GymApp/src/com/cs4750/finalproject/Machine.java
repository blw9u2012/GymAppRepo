package com.cs4750.finalproject;

public class Machine {

	private String name;
	private String body_focus;
	private boolean available;
	private String exercise_type;
	private int id;
	private String user;

	public Machine(int id, String name, String body_focus,boolean available, String exercise_type) {
		this.name = name;
		this.body_focus = body_focus;
		this.id = id;
		this.available = available;
		this.exercise_type = exercise_type;
	}
	
	public Machine(String name){
		this.name = name;
	}
	
	public Machine(int id, String name, Boolean available){
		this.id = id;
		this.name = name;
		this.available = available;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBody_focus() {
		return body_focus;
	}
	public void setBody_focus(String body_focus) {
		this.body_focus = body_focus;
	}

	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public String getExercise_type() {
		return exercise_type;
	}
	public void setExercise_type(String exercise_type) {
		this.exercise_type = exercise_type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
