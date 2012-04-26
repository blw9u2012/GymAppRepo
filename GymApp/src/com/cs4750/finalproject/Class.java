package com.cs4750.finalproject;

public class Class {
	
	private String title;
	private String instructor;
	private String location;
	
	private int startTime;
	private int endTime;	
	private int capactity;
	private int enrolled;
	private int id;

	private boolean availible;
	
	public Class(String title, int id, String instructor, int startTime, int endTime, boolean avail) {
		this.title = title;
		this.id = id;
		this.instructor = instructor;
		this.startTime = startTime;
		this.endTime = endTime;
		this.availible = avail;
	}
	
	public Class(String title){
		this.title = title;
	}
	
	public Class(String title, int capacity){
		this.title = title;
		this.capactity = capacity;
	}	
	public Class(String title, String instructor){
		this.title = title;
		this.instructor = instructor;
	}
	
	public Class(String title, String location, int start, int end, int cap){
		this.title = title;
		this.location = location;
		this.startTime = start;
		this.endTime = end;
		this.capactity = cap;
	}
	
	public Class(int id, String title, String location, int start, int end, int cap, int enrolled){
		this.id = id;
		this.title = title;
		this.location = location;
		this.startTime = start;
		this.endTime = end;
		this.capactity = cap;
		this.enrolled = enrolled;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	public int getCapactity() {
		return capactity;
	}

	public void setCapactity(int capactity) {
		this.capactity = capactity;
	}
	public boolean isAvailible() {
		return availible;
	}

	public void setAvailible(boolean availible) {
		this.availible = availible;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	public int getEnrolled() {
		return enrolled;
	}

	public void setEnrolled(int enrolled) {
		this.enrolled = enrolled;
	}
}


