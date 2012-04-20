package com.cs4750.finalproject;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ExpListAdapter extends BaseExpandableListAdapter {
	    /*-------------------------- Fields --------------------------*/
	 
	    private final HashMap<String, ArrayList<String>> myData = new HashMap<String, ArrayList<String>>();
	    private final HashMap<Integer, String> lookUp = new HashMap<Integer, String>();
	    private final Context context;
	 
	 
	 
	    /*-------------------------- Public --------------------------*/
	 
	    public ExpListAdapter(final Context con)
	    {
	        context = con;
	    }
	 
	    public boolean AddGroup(final String groupName, final ArrayList<String> list)
	    {
	        final ArrayList<String> prev = myData.put(groupName, list);
	  
	        if (prev != null)
	            return false;
	  
	        lookUp.put(myData.size() - 1, groupName);
	  
	        notifyDataSetChanged();
	        return true;
	    }
	 
	    @Override
	    public Object getChild(int groupPos, int childPos) 
	    {
	        if (lookUp.containsKey(groupPos))
	        {
	            final String str = lookUp.get(groupPos);
	            final ArrayList<String> data = myData.get(str);
	   
	            return data.get(childPos);
	        }
	  
	        return null;
	    }

	    @Override
	    public long getChildId(int groupPos, int childPos) 
	    {  
	        return 0;
	    }

	    @Override
	    public View getChildView(int groupPos, int childPos, boolean isLastChild,View convertView, ViewGroup parent){
	        LinearLayout linear = new LinearLayout(context);
	  
	        final LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
	  
	        TextView text = new TextView(context);
	  
	        // Indent
	        final String str = "\t\t\t" + (String)getChild(groupPos, childPos);
	  
	        linear = new LinearLayout(context);
	        linear.setOrientation(LinearLayout.VERTICAL);
	  
	        text.setLayoutParams(params);
	        text.setText(str);
	        linear.addView(text);
	  
	        return linear;
	    }

	    @Override
	    public int getChildrenCount(int groupPos) 
	    {
	        if (lookUp.containsKey(groupPos))
	            return myData.get(lookUp.get(groupPos)).size();

	        return 0;
	    }

	    @Override
	    public Object getGroup(int groupPos) 
	    {
	        if (lookUp.containsKey(groupPos))
	            return myData.get(lookUp.get(groupPos));

	        return null;
	    }

	    @Override
	    public int getGroupCount() 
	    {
	        return myData.size();
	    }

	    @Override
	    public long getGroupId(int groupPos) 
	    {
	        return 0;
	    }

	    @Override
	    public View getGroupView(int groupPos, boolean isExpanded, View convertView, ViewGroup parent){
	        LinearLayout linear = new LinearLayout(context);
	  
	        final LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
	  
	        TextView text = new TextView(context);
	  
	        // Push the group name slightly to the right for drop down icon
	        final String str = "\t\t" + lookUp.get(groupPos);
	  
	        linear = new LinearLayout(context);
	        linear.setOrientation(LinearLayout.VERTICAL);
	  
	        text.setLayoutParams(params);
	        text.setText(str);
	        text.setTextSize(18.0f);
	        linear.addView(text);
	  
	        return linear;
	    }

	    @Override
	    public boolean hasStableIds() 
	    {
	        return false;
	    }

	    @Override
	    public boolean isChildSelectable(int groupPos, int childPos) 
	    {
	        return false;
	    }

	
}
