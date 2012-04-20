package com.cs4750.finalproject;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class GymTabWidget extends TabActivity {
	
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.tab_host);

	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, HomeTabActivity.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("Home").setIndicator("Home",res.getDrawable(R.drawable.ic_tab_icons))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, MachineTabActivity.class);
	    spec = tabHost.newTabSpec("Machines").setIndicator("Machines",
	                      res.getDrawable(R.drawable.ic_tab_icons))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, RoomTabActivity.class);
	    spec = tabHost.newTabSpec("Rooms").setIndicator("Rooms",
	                      res.getDrawable(R.drawable.ic_tab_icons))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, ClassTabActivity.class);
	    spec = tabHost.newTabSpec("Class").setIndicator("Class",
	                      res.getDrawable(R.drawable.ic_tab_icons))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    tabHost.setCurrentTab(0);
	}	

}
