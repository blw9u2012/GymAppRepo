package com.cs4750.finalproject;

public class User {

	private int id;
	private String name;
	private String email;
	private String phone_number;
	private int age;
	private String username;
	
	//constructor
	public User(int id, String newName, String newEmail, String newPhone){
		this.id = id;
		this.setName(newName);
		this.setEmail(newEmail);
		this.setPhone_number(newPhone);
	}
	
	//constructor
	public User( String newName, String newEmail, String newPhone){
		this.setName(newName);
		this.setEmail(newEmail);
		this.setPhone_number(newPhone);
	}
	
	public User(int id, String newName, String newEmail, String newPhone, int age){
		this.id = id;
		this.setName(newName);
		this.setEmail(newEmail);
		this.setPhone_number(newPhone);
		this.setAge(age);
	}
	
	public User(int id, String newName, String newEmail, String newPhone, int age, String user){
		this.id = id;
		this.setName(newName);
		this.setEmail(newEmail);
		this.setPhone_number(newPhone);
		this.setAge(age);
		this.username = user;
	}
	
	public User( String newName, String newEmail, String newPhone, int age){
		this.setName(newName);
		this.setEmail(newEmail);
		this.setPhone_number(newPhone);
		this.setAge(age);
	}
	//retrieve id
	public int getID(){
		return this.id;
	}
	
	//setting id
	public void setID(int id){
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
