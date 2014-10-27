package com.sdufe.thea.customlistview;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyListViewAdapter extends ArrayAdapter<String> {

	public MyListViewAdapter(Context context, int resource, List<String> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(
					R.layout.listview_item, null);
		}else {
			view=convertView;
		}
		TextView tv=(TextView) view.findViewById(R.id.item_tv);
		tv.setText(getItem(position));
		return view;
	}

}
