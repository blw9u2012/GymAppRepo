package com.cs4750.finalproject;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MachineAdapter extends ArrayAdapter<Machine> {

	private ArrayList<Machine> machineList;

	public MachineAdapter(Context context, int textViewResourceId,
			ArrayList<Machine> items) {
		super(context, textViewResourceId, items);
		this.machineList = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_item_machines, null);
		}
		Machine m = machineList.get(position);
		if (m != null) {
			TextView tt = (TextView) v.findViewById(R.id.machineName);
			TextView bt = (TextView) v.findViewById(R.id.machineAvailibity);
			if (tt != null) {
				tt.setText(m.getName());
				
			}
			if (bt != null) {
				bt.setText("Availibility: " + m.isAvailable());
			}
		}
		return v;
	}
}
