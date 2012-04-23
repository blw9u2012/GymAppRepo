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
import android.widget.Toast;

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
				new SignIn().execute(user_name,password);
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
/*		TextView tv = (TextView)findViewById(R.id.sampleText);
		DatabaseHandler db = new DatabaseHandler(this, null, null, 1);
		User user = new User(null, null, null);
		user.setPhone_number("1234567890");
		user.setName("Brianna");
		user.setEmail("bw@gmail.com");
		user.setAge(21);
		db.addUser(user);
		tv.setText(db.getUser(2).getName());*/
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
			String result = sv.signIn(params[0], params[1]);
			return result;
		}
		
		protected void onPostExecute(String result){
				//parse the result string...
				String tmp = result;
				String delims = "[,]";
				String[] tokens = tmp.split(delims);
				
				String success = tokens[0]; 
				
				if(success.equals("success")){
					
					String user_name = tokens[1];
					String user_id = tokens[2];
					
					Toast.makeText(getApplicationContext(), "Welcome "+user_name, Toast.LENGTH_LONG).show();
					Intent i = new Intent(GymAppSignInActivity.this, GymTabWidget.class);
					Intent data = new Intent(getApplicationContext(), ClassTabActivity.class);
					i.putExtra("id", user_id);
					i.putExtra("user_name",user_name);
					
					startActivity(i);
				}
				else{
					Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
				}
		}
	}
}