package com.cs4750.finalproject;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
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

public class MachineTabActivity extends ListActivity{
	private ArrayList<Machine> machineList;
	private MachineAdapter machineListAdapter;
	ListView lv;
	String user_name;
	String user_id;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.machine_tab);
		Bundle bundle = getIntent().getExtras();
		user_name = bundle.getString("user_name");
	    user_id = bundle.getString("id");
		
		TextView userTV = (TextView)findViewById(R.id.pageusername);
		userTV.setText(user_name);
        
        //Initialize the arraylist...
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
				Machine selectedMachine = machineList.get(position);
				final String selectedMachineId = String.valueOf(selectedMachine.getId());
				
				if(selectedMachine.isAvailable()){
					//is the selected is machine is available, change it's availibility and add its current user...
					new ChangeMachineAvail().execute("0",selectedMachineId);
					new ServerHandler().setMachineUser(user_id, selectedMachineId);
					
					//reset the list to reflect the most recent changes...
					machineList.clear();
					new LoadMachines().execute(machineList);
		
				}
				//else the machine is currently being used 
				else{
					AlertDialog.Builder machineAlertDialog = new AlertDialog.Builder(MachineTabActivity.this);
					machineAlertDialog.setTitle(user_name);
					machineAlertDialog.setMessage("Do you want to release the machine "+selectedMachine.getName());
					machineAlertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//change machine availibility...
							new ChangeMachineAvail().execute("1",selectedMachineId);
							new ServerHandler().setMachineUser("0", selectedMachineId);
							
							//reset list to reflect changes...
							machineList.clear();
							new LoadMachines().execute(machineList);
						}
					});
					machineAlertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
					machineAlertDialog.create().show();
				}
				
//end of set onitemclick	        	
	        };
        });
	}
	        
		
/*				AlertDialog.Builder builder = new AlertDialog.Builder(MachineTabActivity.this);
				builder.setMessage("Use this Machine: " + machineList.get(position).getName())
				       .setCancelable(true)
				       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Machine m = machineList.get(position);
							final String id = String.valueOf(m.getId());
							if(m.isAvailable()){
								new ChangeMachineAvail().execute("0",id);
								//set the user of the machine to current user
								new ServerHandler().setMachineUser(user_id, id);
								
								//reset list
								machineList.clear();
								new LoadMachines().execute(machineList);
							}
							else{
								//Toast.makeText(getApplicationContext(), "Machine is currently unavailable, are you user "+new ServerHandler().getMachineUser(id), Toast.LENGTH_LONG).show();
								AlertDialog.Builder machDialog = new AlertDialog.Builder(MachineTabActivity.this);
								machDialog.setTitle("Machine is currently unavailable, are you user "+new ServerHandler().getMachineUser(id)+"?");
								machDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {
										new ChangeMachineAvail().execute("1",id);

										//update view...
										machineList.clear();
										new LoadMachines().execute(machineList);
									}
								});
								machDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {	
										dialog.dismiss();
									}
								});
								machDialog.create().show();
								
								
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
				alert.show();*/
				

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
    		machineListAdapter.notifyDataSetChanged();
            setListAdapter(machineListAdapter);
    		
    	}
    }
    
    private class ChangeMachineAvail extends AsyncTask <String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			String availibility = params[0];
			String id = params[1];
			
			ServerHandler sv = new ServerHandler();
			String result = sv.changeAvailibility(availibility, id,"machine");
			return result;
		}
		
		protected void onPostExecute(String result){
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT);
		}
    	
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.machine_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.RefreshList:
	        	machineList.clear();
	        	new LoadMachines().execute(machineList);
	        	return true;
	        	
	        case R.id.viewMachines:
	        	//send retrieve all machines with thee current user id...
	        	ServerHandler sv = new ServerHandler();
	        	ArrayList<Machine> userMachineList = new ArrayList<Machine>();
	        	ArrayList<String> temp = new ArrayList<String>();
	        	temp = sv.getUserMachines(user_id);
	        	
	        	for(int i = 0; i < temp.size(); i++){
	        		String machine = temp.get(i);
	        		String delims = "[,]";
		        	String[] tokens = machine.split(delims);
		        	
		        	int id = Integer.parseInt(tokens[0]);
		        	String name = tokens[1];
		        	String body_focus = tokens[2];
		        	int available = Integer.parseInt(tokens[3]);
		        	String exercise_type = tokens[4];
		        	userMachineList.add(new Machine(id,name,body_focus,false,exercise_type));
	        	}
	        	machineListAdapter = new MachineAdapter(MachineTabActivity.this, R.layout.list_item_machines,userMachineList);
	        	machineListAdapter.notifyDataSetChanged();
	        	setListAdapter(machineListAdapter);
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
