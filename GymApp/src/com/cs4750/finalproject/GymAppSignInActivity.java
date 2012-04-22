package com.cs4750.finalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GymAppSignInActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signin_page);

		final EditText userName = (EditText)findViewById(R.id.loginText);
		final EditText userPassword = (EditText)findViewById(R.id.pwText);
		
		
		Button loginButton = (Button)findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String user_name = userName.getText().toString();
				String password = userPassword.getText().toString();
				Intent i = new Intent(GymAppSignInActivity.this, GymTabWidget.class);
				startActivity(i);
				//try to sign in...
				
			}
			
		});		
		
		
		Button signUpButton = (Button)findViewById(R.id.signUpButton);
		signUpButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(GymAppSignInActivity.this, RegisterUserActivity.class);
				startActivity(i);
				
			}
			
		});
	}

	@Override
    protected void onResume(){
    	super.onResume();
    	//new GetSomeData().execute("getRooms");		
    }
	private class SignIn extends AsyncTask <String, Integer, String>{

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