package com.cs4750.finalproject;

import java.util.ArrayList;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

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
	/*private ExpandableListAdapter setUpAdapter(){
		groups = new ArrayList<String>();
		children = new HashMap<String, ArrayList<String>>();
        for(int i = 0; i < CATEGORIES.length;i++){
        	groups.add(CATEGORIES[i]);
        }
        
        for(int i = 0; i < ENTRIES.length; i++){
        	for(int j = 0; j < ENTRIES[i].length; j++){
        		addItem(ENTRIES[i][j]);
        	}
        }
        ExpandableListAdapter mAdapter = new ExpandableListAdapter(HomeTabActivity.this,groups,children);
		return mAdapter;
	}
	public void addItem(String child) {
		
		int tag_index = child.indexOf(",");
		int length = child.length();
		String child_tag = child.substring(0, tag_index);	
		String child_value = child.substring(tag_index+1, length);
		 
		//if the arraylist of groups does not contain a group name, add it to the arraylist...
		if (!groups.contains(child_tag)) {
			groups.add(child_tag);
		}
		
		//else the itemname is already a member of the group arraylist...
		//int index = groups.indexOf(child_tag); //returns the group in question...
		//if(children.size() < index + 1){
		if(!children.containsKey(child_tag)){
			children.put(child_tag,(new ArrayList<String>()));
		}
		children.get(child_tag).add(child_value);
	}*/
}
