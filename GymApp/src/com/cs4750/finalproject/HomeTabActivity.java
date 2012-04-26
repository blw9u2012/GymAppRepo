package com.cs4750.finalproject;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class HomeTabActivity extends ListActivity{
	 String user_name, user_id;
	 ArrayAdapter<String> adapter;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_tab);
        Bundle bundle = getIntent().getExtras();
        user_name = bundle.getString("user_name");
        user_id = bundle.getString("id");
        
        TextView userTV = (TextView)findViewById(R.id.pageusername);
        userTV.setText(user_name);
        
        ArrayList<String> machineActivity = new ArrayList<String>();
        ArrayList<String> classActivity = new ArrayList<String>();
        new LoadRecentActivity().execute(machineActivity,classActivity);
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
	        case R.id.viewClasses:
	        	Toast.makeText(getApplicationContext(), "Recent Activity", Toast.LENGTH_LONG);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
    private class LoadRecentActivity extends AsyncTask <ArrayList<String>, Void, ArrayList<String>>{

    	
		@Override
		protected ArrayList<String> doInBackground(ArrayList<String>... params) {
	        ServerHandler sv = new ServerHandler();
	        ArrayList<String> machines = params[0];
	        ArrayList<String> classes = params[1];
	   	 ArrayList<String> finalList = new ArrayList<String>();
	        
	      //first load user's recently used machines...
	        machines = sv.getUserMachines(user_id);
	        classes = sv.getUserClasses(user_id);
	        
	        for(int i = 0; i < machines.size(); i++){
	        	String machine = machines.get(i);
	        	machine.trim();
	        	String delims = "[,]";
	        	String[] tokens = machine.split(delims);
	        	
	        	String name = tokens[1];
	        	finalList.add(name);
	        	
	        }
	        
	        for(int i = 0; i < classes.size(); i++){
	        	String cls = classes.get(i);
	        	cls.trim();
	        	String delims = "[,]";
	        	String[] tokens = cls.split(delims);
	        	
	        	String name = tokens[1];
	        	finalList.add(name);
	        	
	        }
	        
			return finalList;
		}
    	protected void onPostExecute(ArrayList<String> result){
    		adapter = new ArrayAdapter<String>(HomeTabActivity.this, android.R.layout.simple_list_item_1,result);
    		adapter.notifyDataSetChanged();
            setListAdapter(adapter);
    		
    	}
    }
}
