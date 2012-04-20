package com.cs4750.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GymAppSignInActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signin_page);

		Button loginButton = (Button)findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(GymAppSignInActivity.this,GymTabWidget.class);
				startActivity(i);
				
			}
			
		});
		
		//DatabaseHandler db = new DatabaseHandler(this, null, null, 1);
		//db.addUser(new User(1, "Brandon", "blw9u", "2158344621"));
		//TextView tv = (TextView) findViewById(R.id.title);
		//tv.setText(db.getUser(1).getName());
		

		
	}

	@Override
    protected void onResume(){
    	super.onResume();
    	//new GetSomeData().execute("getRooms");		
    }
	private class GetSomeData extends AsyncTask <String, Integer, String>{

		@Override
		protected String doInBackground(String... params) {
			ServerHandler sv = new ServerHandler();
			String sample_data = sv.createPostRequest(params[0]);
			return sample_data;
		}
		
		protected void onPostExecute(String data){
			TextView tv = (TextView)findViewById(R.id.sampleText);
			tv.setText(data);
		}
	}
}