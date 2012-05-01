package com.cs4750.finalproject;

import java.util.ArrayList;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ClassTabActivity extends ListActivity {
	private ArrayList<Class> classList;
	private ClassAdapter classListAdapter;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.class_tab);
		Bundle bundle = getIntent().getExtras();
		final String user_name = bundle.getString("user_name");
		final String user_id = bundle.getString("id");
		
		TextView userTV = (TextView)findViewById(R.id.pageusername);
		userTV.setText(user_name);

		// Initialize the arraylist...
		classList = new ArrayList<Class>();
		new LoadClasses().execute(classList);

		ListView lv = getListView();
		// ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						ClassTabActivity.this);
				builder.setMessage("View this Class: "+classList.get(position).getTitle())
						.setCancelable(true)
						.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,	int which) {
										//String userId = "1";
										
										String classId = String.valueOf(classList.get(position).getId());
										//new CheckIn().execute(userId,classId);
										Intent i = new Intent(ClassTabActivity.this, ClassActivity.class);
										i.putExtra("classId", classId);
										i.putExtra("userName",user_name);
										i.putExtra("id",user_id);
										startActivity(i);
									}
								})
						.setNegativeButton("No", new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,int which) {
										dialog.cancel();
									}
								});
				AlertDialog alert = builder.create();
				alert.show();
			}
		});
		//sign up for a class...
		lv.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
					AlertDialog.Builder builder = new AlertDialog.Builder(ClassTabActivity.this);
					builder.setMessage("Sign Up for "+classList.get(position).getTitle()+"?");
					builder.setCancelable(true);
					builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//DatabaseHandler db = new DatabaseHandler(getApplicationContext(), null, null,1);
							Bundle bundle = getIntent().getExtras();
							String userId = bundle.getString("id");
		
							userId = userId.replace("\n", "");
							//String userName = bundle.getString("user_name");
							//User user = db.getUser(userName);
							//Toast.makeText(getApplicationContext(), user.getName(), Toast.LENGTH_LONG).show();
							
							//use the user's information to sign up to the class
							
							new SignUp().execute(userId,String.valueOf(classList.get(position).getId()));
						}
					});
					builder.create();
					builder.show();
				return true;
			}
			
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.classes_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.RefreshList:
        	classList.clear();
        	new LoadClasses().execute(classList);
        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	//threads to handle the db interactions...
	private class LoadClasses extends AsyncTask<ArrayList<Class>, Void, ArrayList<Class>> {

		@Override
		protected ArrayList<Class> doInBackground(ArrayList<Class>... params) {
			ServerHandler sv = new ServerHandler();

			ArrayList<Class> classes = params[0];
			ArrayList<String> result = sv.createPostRequestArrayList("getClasses");
			for (int i = 0; i < result.size(); i++) {
				String cls = result.get(i);
				cls.trim();
				String delims = "[,]";
				String[] tokens = cls.split(delims);

				// parse the int boolean values..
				int id = Integer.parseInt(tokens[0]);
				String name = tokens[1];
				String location = tokens[2];
				int st = Integer.parseInt(tokens[3]);
				int et = Integer.parseInt(tokens[4]);
				int cap = Integer.parseInt(tokens[5]);
				int enrolled = Integer.parseInt(tokens[6]);
				
				//check to see if the class is full or not...
				if(enrolled < cap){
					Class c = new Class(id, name, location, st,et, cap, enrolled);
					c.setAvailible(true);
					classList.add(c);
				}
			}
			classes = classList;
			return classes;
		}

		protected void onPostExecute(ArrayList<Class> result) {
			classListAdapter = new ClassAdapter(ClassTabActivity.this,R.layout.list_item_classes, classList);
			classListAdapter.notifyDataSetChanged();
			setListAdapter(classListAdapter);

		}
	}

	private class SignUp extends AsyncTask<String, Void, String>{
		@Override
		protected String doInBackground(String... params) {
			String userId = params[0];
			String classId = params[1];
			ServerHandler sv = new ServerHandler();
			String result = sv.signUp(userId, classId);
			return result;
		}
		
		protected void onPostExecute(String result){
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
		}
		
	}
	
}
