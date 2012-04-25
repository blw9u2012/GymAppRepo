package com.cs4750.finalproject;

import java.util.ArrayList;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeTabActivity extends ExpandableListActivity{

	private ExpListAdapter adapter;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_tab);
        Bundle bundle = getIntent().getExtras();
        String user_name = bundle.getString("user_name");
        
        TextView userTV = (TextView)findViewById(R.id.pageusername);
        userTV.setText(user_name);
        

        
        adapter = new ExpListAdapter(HomeTabActivity.this);
        setUpAdapter();
        setListAdapter(adapter);
        ExpandableListView elv = getExpandableListView();
        elv.setTextFilterEnabled(true);
        //listview.setOnItemClickListener(OnClickingListItem());
        
        
        //ExpandableListView listView = (ExpandableListView)findViewById(R.id.listView);
        //adapter = setUpAdapter();
        //listView.setAdapter(adapter);
        //ListView lv = getListView();
        //lv.setTextFilterEnabled(true);
        //setListAdapter(new ArrayAdapter<String>(HomeTabActivity.this, R.layout.list_item, COUNTRIES));
        
        //lv.setOnItemClickListener(new OnItemClickListener() {
          //public void onItemClick(AdapterView<?> parent, View view,
            //  int position, long id) {
            // When clicked, show a toast with the TextView text
            //Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
                //Toast.LENGTH_SHORT).show();
          //}
        //});
        
/*        TextView textview = new TextView(this);
        textview.setText("This is the Home tab");
        setContentView(textview);*/
    }
	
	private void setUpAdapter(){
		ArrayList<String> models = new ArrayList<String>();
        models.add("Barbell Row");
        models.add("Inclined Bench Press");
        models.add("Leg Press");
        adapter.AddGroup("Exercises", models);
        
        models = new ArrayList<String>();
        models.add("Salsa");
        models.add("Zumba");
        models.add("Cycling");
        adapter.AddGroup("Classes", models);
        
        //reset models...
        models = new ArrayList<String>();
        models.add("Cheeseburger");
        models.add("Ham");
        models.add("Salmon");
        adapter.AddGroup("Meals", models);
		
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
	        case R.id.viewActivity:
	        	Toast.makeText(getApplicationContext(), "Recent Activity", Toast.LENGTH_LONG);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
