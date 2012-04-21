package com.cs4750.finalproject;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MachineTabActivity extends ListActivity{
	private ArrayList<Machine> machineList;
	private MachineAdapter machineListAdapter;
	ListView lv;
   
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.machine_tab);
        
        //Initialize the arraylist...
        //ArrayList<String> passing = new ArrayList<String>();
        machineList = new ArrayList<Machine>();
        LoadMachines object = new LoadMachines();
        object.execute(machineList);
        
        lv = getListView();

        
    }
	@Override
	protected void onResume(){
		super.onResume();
        lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
				AlertDialog.Builder builder = new AlertDialog.Builder(MachineTabActivity.this);
				builder.setMessage("Use this Machine: " + machineList.get(position).getName())
				       .setCancelable(true)
				       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Machine m = machineList.get(position);
							String id = String.valueOf(m.getId());
							if(m.isAvailable()){
								new ChangeMachineAvail().execute("0",id);
								
								//reset list
								machineList.clear();
								new LoadMachines().execute(machineList);
							}
							else{
								Toast.makeText(getApplicationContext(), "Machine is currently unavailable", Toast.LENGTH_LONG).show();
								new ChangeMachineAvail().execute("1",id);
								
								//update view...
								machineList.clear();
								new LoadMachines().execute(machineList);
							}
							
						}
					})
				       .setNegativeButton("No", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
							
						}
					});
				AlertDialog alert = builder.create();
				alert.show();
				
			}
        	
        });
		
        
	}
    private class LoadMachines extends AsyncTask <ArrayList<Machine>, Void, ArrayList<Machine>>{

		@Override
		protected ArrayList<Machine> doInBackground(ArrayList<Machine>... passing) {
	        ServerHandler sv = new ServerHandler();
	        
	        ArrayList<Machine> machines = passing[0];
	        ArrayList<String> result = sv.createPostRequestArrayList("getMachines");
	        for(int i = 0; i < result.size(); i++){
	        	String machine = result.get(i);
	        	machine.trim();
	        	String delims = "[,]";
	        	String[] tokens = machine.split(delims);
	        	
	        	int id = Integer.parseInt(tokens[0]);
	        	String name = tokens[1];
	        	String body_focus = tokens[2];
	        	int available = Integer.parseInt(tokens[3]);
	        	String exercise_type = tokens[4];
	        	
	        	
	        	if(available == 1){
	        		boolean availibility = true;
	        		Machine m = new Machine(id,name,body_focus,availibility,exercise_type);
	        		machineList.add(m);
	        	}
	        	else{
	        		machineList.add(new Machine(id,name,body_focus,false,exercise_type));	
	        	}
	        	
	        	
	        }
	        machines = machineList;
			return machines;
		}
    	protected void onPostExecute(ArrayList<Machine> result){
    		machineListAdapter = new MachineAdapter(MachineTabActivity.this, R.layout.list_item_machines,machineList);
            setListAdapter(machineListAdapter);
    		
    	}
    }
    
    private class ChangeMachineAvail extends AsyncTask <String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			String availibility = params[0];
			String id = params[1];
			
			ServerHandler sv = new ServerHandler();
			String result = sv.changeAvailibility(availibility, id);
			return result;
		}
		
		protected void onPostExecute(String result){
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT);
		}
    	
    }
    
   
    
    
	
}
