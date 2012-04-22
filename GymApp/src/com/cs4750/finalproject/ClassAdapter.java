package com.cs4750.finalproject;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ClassAdapter extends ArrayAdapter<Class>{

	private ArrayList<Class> classList;
	
	public ClassAdapter(Context context, int textViewResourceId, ArrayList<Class> items) {
		super(context, textViewResourceId, items);
		this.classList = items;
	}

	public ArrayList<Class> getClassList() {
		return classList;
	}

	public void setClassList(ArrayList<Class> classList) {
		this.classList = classList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_item_classes, null);
		}
		Class c = classList.get(position);
		if (c != null) {
			TextView tt = (TextView) v.findViewById(R.id.className);
			TextView bt = (TextView) v.findViewById(R.id.classAvailibity);
			if (tt != null) {
				tt.setText("Name: " + c.getTitle());
			}
			if (bt != null) {
				bt.setText("Location: " + c.getLocation());
			}
		}
		return v;
	}
}
