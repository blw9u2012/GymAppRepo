package com.cs4750.finalproject;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterUserActivity extends Activity{
	String spinnerValue;
	EditText userName;
	EditText userEmail;
	EditText userPhone;
	EditText userScreenName;
	EditText userPassword;
	EditText confirmUserPassword;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_user);
		ArrayList<Integer> age = new ArrayList<Integer>();
		Spinner spinner = (Spinner)findViewById(R.id.registerUserSpinner);
		
		for(int i = 16; i < 65; i++ ){
			age.add(i);
		}
		ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,age);
		spinner.setAdapter(spinnerAdapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view ,int position, long id) {
				Object item = parent.getItemAtPosition(position);
				spinnerValue = item.toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
			
		});
		
		
		//get all of users information...
		userName = (EditText)findViewById(R.id.registerUserName);
		userEmail = (EditText)findViewById(R.id.registerUserEmail);
		userPhone = (EditText)findViewById(R.id.registerUserPhone);
		userScreenName = (EditText)findViewById(R.id.registerUserScreenName);
		userPassword = (EditText)findViewById(R.id.registerUserPassword);
		confirmUserPassword = (EditText)findViewById(R.id.registerUserConfirmPassword);
		Button registerButton = (Button)findViewById(R.id.registerUserButton);
		
		registerButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String user_name = userName.getText().toString();
				String user_email = userEmail.getText().toString();
				String user_phone = userPhone.getText().toString();
				String user_screenName = userScreenName.getText().toString();
				String user_password = userPassword.getText().toString();
				String confirm_user_password = confirmUserPassword.getText().toString();
				
				if(user_password.equals(confirm_user_password)){
					new AddUser().execute(user_name,spinnerValue,user_email,user_phone,user_screenName, user_password);
				}
				else{
					Toast.makeText(RegisterUserActivity.this, "Passwords don't match", Toast.LENGTH_LONG).show();
				}
			}	
		});
		
		
		
		
		/*DatabaseHandler db = new DatabaseHandler(this, null, null, 1);
		int count = db.getUserCount();
		Toast.makeText(this, "Hi", Toast.LENGTH_LONG).show();*/
		
	}
	private class AddUser extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			String name = params[0];
			String age = params[1];
			String email = params[2];
			String phone = params[3];
			String screen_name = params[4];
			String password = params[5]; 
			
			ServerHandler sv = new ServerHandler();
			String result = sv.addUser(name, age, email, phone, screen_name, password);
			return result;
		}
		
		protected void onPostExecute(String result){
			Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();	
			
			//parse the name and id...
			String delims = "[,]";
			String[] res = result.split(delims);
			
			DatabaseHandler db = new DatabaseHandler(getApplicationContext(), null, null, 1);
			db.addUser(new User(userName.getText().toString(),userEmail.getText().toString(),userPhone.getText().toString(),Integer.parseInt(spinnerValue)));
			
			Intent i  = new Intent(RegisterUserActivity.this, GymTabWidget.class);
			i.putExtra("user_name", userName.getText().toString());
			i.putExtra("id", res[0]);
			startActivity(i);
		}
	}
}
