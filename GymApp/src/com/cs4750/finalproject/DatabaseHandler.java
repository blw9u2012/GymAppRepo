package com.cs4750.finalproject;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper{
	
	//Database version
	private static final int DATABASE_VERSION = 1;
	
	//Database name
	//private static final String DATABASE_NAME = "GYM_APP_MANAGER";
	private static final String DATABASE_NAME = "gymApp.db";
	
	//Users table name...
	private static final String TABLE_USER = "User";
	
	//User column names or attributes..
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_PHONE = "phone";
	private static final String KEY_AGE = "age";
	private static final String KEY_USERNAME = "username";

    Context context;

	public DatabaseHandler(Context context, String name, CursorFactory factory,int version) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	//Creating the tables...
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER 
				+"(" + KEY_ID + " INTEGER NOT NULL, " 
				+ KEY_NAME + " VARCHAR(50) NOT NULL, " 
				+ KEY_EMAIL + " VARCHAR(25) NOT NULL, " 
				+ KEY_PHONE + " VARCHAR(10) NOT NULL, " 
				+ KEY_AGE + " INTEGER NOT NULL, " 
				+ KEY_USERNAME + " VARCHAR(30) NOT NULL, "
				+ "PRIMARY KEY("+KEY_ID+"))";
		db.execSQL(CREATE_USER_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//Drop older table if existed...
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
		
		//create tables again...
		onCreate(db);
		
	}
	
	//Do the CRUD operations...
	
	//add a new user...
	public void addUser(User user){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, user.getName());
		values.put(KEY_EMAIL, user.getEmail());
		values.put(KEY_PHONE, user.getPhone_number());
		values.put(KEY_AGE, user.getAge());
		values.put(KEY_USERNAME, user.getUsername());
		
		//Insert the values...
		db.insert(TABLE_USER, null, values);
		db.close();		
	}
	
	//retrieve a single user...
	public User getUser(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(TABLE_USER, new String[]{KEY_ID, KEY_NAME, KEY_PHONE, KEY_EMAIL, KEY_AGE, KEY_USERNAME}, KEY_ID+"=?", new String[]{String.valueOf(id)}, null, 
				null, null, null);
		
		//moves the cursor to the first row...
		if(cursor != null)cursor.moveToFirst();
		
		User user = new User(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getString(5));
		
		return user;
		
	}
	//retrieve a single user...
	public User getUser(String username){
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(TABLE_USER, new String[]{KEY_ID, KEY_NAME, KEY_PHONE, KEY_EMAIL, KEY_AGE, KEY_USERNAME}, KEY_USERNAME+"=?",
				new String[]{username}, null, 
				null, null, null);
		
		//moves the cursor to the first row...
		if(cursor != null)cursor.moveToFirst();
		
		User user = new User(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getString(5));
		
		return user;
		
	}
	
	//retrieve all users...
	public List<User> getAllUsers(){
		List<User> userList = new ArrayList<User>();
		
		//create a select all query
		String selectAllQuery = "SELECT * FROM " + TABLE_USER;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectAllQuery, null);
		
		//loop through all the rows...
		if(cursor.moveToFirst()){
			do{
				User user = new User(null,null,null);
				user.setID(cursor.getInt(0));
				user.setName(cursor.getString(1));
				user.setEmail(cursor.getString(2));
				user.setPhone_number(cursor.getString(3));
				user.setAge(cursor.getInt(4));
				
				//add user to contactlist
				userList.add(user);
			} while(cursor.moveToNext());
		}
		return userList;
	}
	
	//update a user..
	public int updateUser(User user){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, user.getName());
		values.put(KEY_EMAIL, user.getEmail());
		values.put(KEY_PHONE, user.getPhone_number());
		values.put(KEY_AGE, user.getAge());
		
		return db.update(TABLE_USER, values, KEY_ID + "=?", new String[] {String.valueOf(user.getID()) });
		
	}
	
	//delete a user....
	public void deleteUser(User user){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_USER, KEY_ID + "=?", new String[] {String.valueOf(user.getID()) });
		db.close();
	}
	
	//getting user count...
	public int getUserCount(){
		String countQuery = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        
        
        return cursor.getCount();
	}
	
}

