package com.cs4750.finalproject;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ClassTabActivity extends ListActivity{
	private ArrayList<Class> classList;
	private ClassAdapter classAdapter;
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_tab);
        
        //Initialize the arraylist...
        //ArrayList<String> passing = new ArrayList<String>();
        classList = new ArrayList<Class>();
        classList.add(new Class("Zumba"));
        classList.add(new Class("Salsa"));
        classList.add(new Class("Insanity"));
        
        classAdapter = new ClassAdapter(ClassTabActivity.this,R.layout.list_item_classes, classList);
        setListAdapter(classAdapter);
        //LoadMachines object = new LoadMachines();
        //object.execute(classList);
        
        ListView lv = getListView();
        //ListView lv = getListView();
        lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				AlertDialog.Builder builder = new AlertDialog.Builder(ClassTabActivity.this);
				builder.setMessage("Check in to this Machine?")
				       .setCancelable(true)
				       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//new ChangeMachineAvail().execute("0","4mo1b");
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
}
