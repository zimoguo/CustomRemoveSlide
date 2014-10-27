package com.sdufe.thea.customlistview;

import java.util.ArrayList;
import java.util.List;

import com.sdufe.thea.customlistview.MyListView.OnDeleteListener;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;

public class MainActivity extends Activity {
	
	private MyListView listView;
	private MyListViewAdapter adapter;
	private List<String> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		list=new ArrayList<String>();
		list.add("11");
		list.add("11");
		list.add("11");
		list.add("11");
		list.add("11");
		list.add("11");
		list.add("11");
		list.add("11");
		list.add("11");
		listView=(MyListView) findViewById(R.id.my_listview);
		listView.setOnDeleteListener(new OnDeleteListener() {
			
			@Override
			public void onDelete(int index) {
				list.remove(index);
				adapter.notifyDataSetChanged();
			}
		});
		adapter=new MyListViewAdapter(this, 0, list);
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
