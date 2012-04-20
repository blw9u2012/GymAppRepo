package com.cs4750.finalproject;

public class Machine {

	private String name;
	private String body_focus;
	private String inv_id;
	private boolean available;
	private String exercise_type;

	public Machine(String name, String body_focus, String inv_id,
			boolean available, String exercise_type) {
		this.name = name;
		this.body_focus = body_focus;
		this.inv_id = inv_id;
		this.available = available;
		this.exercise_type = exercise_type;
	}
	
	public Machine(String name){
		this.name = name;
	}
	
	public Machine(String name, Boolean available){
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
	public String getInv_id() {
		return inv_id;
	}
	public void setInv_id(String inv_id) {
		this.inv_id = inv_id;
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
}
