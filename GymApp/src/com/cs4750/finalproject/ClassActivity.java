package com.cs4750.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ClassActivity extends Activity {
	TextView classTitle, classLocation, classInstructor , classDay, classTime, classEnrollment, classDescription;
	Button checkIn, signUp;
	String class_id, user_name, user_id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.class_page_dialog);
		
		Bundle bundle = getIntent().getExtras();
		class_id = bundle.getString("classId");
		user_name = bundle.getString("userName");
		user_id = bundle.getString("id");
		
		Button checkIn = (Button)findViewById(R.id.classCheckInButton);
		Button signUp = (Button)findViewById(R.id.classSignUpButton);
		
		signUp.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				new SignUp().execute(user_name,class_id);
			}
			
		});
		
		checkIn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//pass username and class id...
				new CheckIn().execute(user_name,class_id);
			}
			
		});
		classTitle = (TextView)findViewById(R.id.classTitle);
		classInstructor = (TextView)findViewById(R.id.classInstructor);
		classLocation = (TextView)findViewById(R.id.classPageLocation);
		classDay = (TextView)findViewById(R.id.classDay);
		classEnrollment = (TextView)findViewById(R.id.classPageEnrollmentInfo);
		classDescription = (TextView)findViewById(R.id.classPageDescription);
		
		new GetClass().execute(class_id);
	}
	private class GetClass extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
		ServerHandler sv = new ServerHandler();
		String result = sv.getClass(params[0]);
			return result;
		}
		
		protected void onPostExecute(String result){
			String delim = "[,]";
			String[] tokens = result.split(delim);
			
			String class_title = tokens[0];
			String class_instructor = tokens[1];
			String class_description = tokens[2];
			String class_location = tokens[3];
			String class_start_time = tokens[4];
			String class_end_time = tokens[5];
			String class_day = tokens[6];
			String class_capacity = tokens[7];
			String class_enrolled = tokens[8];
			class_enrolled.replace("\n", "");
			
			//set class view objects
			classTitle.setText("Name: "+class_title);
			classInstructor.setText("Instructor: "+class_instructor);
			classDay.setText(class_day+": "+class_start_time+ " - "+class_end_time);
			classDescription.setText(class_description);
			classLocation.setText("Location: "+class_location);
			classEnrollment.setText("Enrolled: "+class_enrolled+" of "+class_capacity);
			
		}
		
	}
	
	private class CheckIn extends AsyncTask<String, Void, String>{
		@Override
		protected String doInBackground(String... params) {
			String userName = params[0];
			String classId = params[1];
			String userId;
			ServerHandler sv = new ServerHandler();
			userId = sv.getUserId(userName);
			String result = sv.checkIn(userId, classId);
			return result;
		}
		
		protected void onPostExecute(String result){
			Toast.makeText(ClassActivity.this, result, Toast.LENGTH_LONG).show();
			Intent i = new Intent(ClassActivity.this, HomeTabActivity.class);
			i.putExtra("user_name", user_name);
			i.putExtra("user_id", user_id);
			//startActivity(i);
		}
		
	}
	
	private class SignUp extends AsyncTask<String, Void, String>{
		@Override
		protected String doInBackground(String... params) {
			String userName = params[0];
			String classId = params[1];
			String userId;
			ServerHandler sv = new ServerHandler();
			userId = sv.getUserId(userName);
			String result = sv.signUp(userId, classId);
			return result;
		}
		
		protected void onPostExecute(String result){
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
			Intent i = new Intent(ClassActivity.this, HomeTabActivity.class);
			i.putExtra("user_name", user_name);
			i.putExtra("user_id", user_id);
			//startActivity(i);
		}
		
	}
}