package com.cs4750.finalproject;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

//not using this class anymore...

public class ExpandableListAdapter extends BaseExpandableListAdapter{
	
	private Context context;
	private ArrayList<String> groups;
	private HashMap<String,ArrayList<String>> children;
	private HashMap<Integer,String> lookUp;
	
	public ExpandableListAdapter(Context context, ArrayList<String> groups, HashMap<String,ArrayList<String>> children){
		this.context = context;
		this.groups = groups;
		this.children = children;		
	}
	
	@Override
	public boolean areAllItemsEnabled(){
		return true;
	}
	
	public Object getChild(int groupPosition, int childPosition) {
		return children.get(groupPosition).get(childPosition);
		
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view,
			ViewGroup parent) {
		String item = (String) getChild(groupPosition, childPosition);
		
		if (view == null) {
			LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			view = infalInflater.inflate(R.layout.child_row, null);
		}
		
		TextView tv = (TextView) view.findViewById(R.id.tvChild);
		tv.setText("  "+item);
		
		return view;
	}

	public int getChildrenCount(int groupPosition) {
		return children.get(groupPosition).size();
	}

	public Object getGroup(int groupPosition) {
		return groups.get(groupPosition);
	}

	public int getGroupCount() {
		return groups.size();
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public View getGroupView(int groupPosition, boolean isLastChild, View view,
			ViewGroup parent) {
		String group = (String) getGroup(groupPosition);
		if (view == null) {
			LayoutInflater inf = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			view = inf.inflate(R.layout.group_row, null);
		}
		TextView tv = (TextView) view.findViewById(R.id.tvGroup);
		tv.setText(group);
		
		return view;
	}

	public boolean hasStableIds() {
		return true;
	}

	public boolean isChildSelectable(int arg0, int arg1) {
		return true;
	}

}
