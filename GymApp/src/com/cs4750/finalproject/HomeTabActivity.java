package com.cs4750.finalproject;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeTabActivity extends ListActivity{
	 String user_name, user_id;
	 ClassAdapter adapter;
	 ArrayList<Class> userRecentClasses;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_tab);
        Bundle bundle = getIntent().getExtras();
        user_name = bundle.getString("user_name");
        user_id = bundle.getString("id");
        
        TextView userTV = (TextView)findViewById(R.id.pageusername);
        userTV.setText(user_name);
        
        ArrayList<String> classActivity = new ArrayList<String>();
        new LoadRecentActivity().execute(classActivity);
        
        ListView lv = getListView();
        lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Class c = userRecentClasses.get(position);
				AlertDialog.Builder alertClassBox = new AlertDialog.Builder(HomeTabActivity.this);
				alertClassBox.setTitle(c.getTitle());
				alertClassBox.setMessage(c.getInstructor()+"\nEnrolled: "+c.getEnrolled()+" of "+c.getCapactity());
				alertClassBox.setCancelable(true);
				alertClassBox.create().show();		
			}
        	
        });
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.my_options_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.about:
	        	Toast.makeText(getApplicationContext(), "A Databases Project App", Toast.LENGTH_LONG);
	            return true;
	        case R.id.viewRecentActivity:
	        	Toast.makeText(getApplicationContext(), "Recent Activity", Toast.LENGTH_LONG);
	        	ArrayList<String> machineActivity = new ArrayList<String>();
	        	ArrayList<String> classActivity = new ArrayList<String>();
	        	
	        	new LoadRecentActivity().execute(machineActivity, classActivity);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
    private class LoadRecentActivity extends AsyncTask <ArrayList<String>, Void, ArrayList<Class>>{

    	
		@Override
		protected ArrayList<Class> doInBackground(ArrayList<String>... params) {
	        ServerHandler sv = new ServerHandler();
	        ArrayList<String> classes = params[0];
	   	 	ArrayList<Class> finalList = new ArrayList<Class>();
	        
	        classes = sv.getUserClasses(user_id);
	        
	        for(int i = 0; i < classes.size(); i++){
	        	String cls = classes.get(i);
	        	cls.trim();
	        	String delims = "[,]";
	        	String[] tokens = cls.split(delims);
	        	
	        	String name = tokens[1];
	        	Class c = new Class(name);
	        	c.setId(Integer.parseInt(tokens[0]));
	        	c.setInstructor(tokens[2]);
	        	c.setCapactity(Integer.parseInt(tokens[3]));
	        	c.setEnrolled(Integer.parseInt(tokens[4]));
	        	finalList.add(c);
	        	
	        }
	        
			return finalList;
		}
    	protected void onPostExecute(ArrayList<Class> result){
    		userRecentClasses = result;
    		adapter = new ClassAdapter(HomeTabActivity.this, R.layout.list_item_classes,result);
    		adapter.notifyDataSetChanged();
            setListAdapter(adapter);
    		
    	}
    }
}
