package com.sdufe.thea.customlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MyListView extends ListView implements OnTouchListener,
		OnGestureListener {

	private GestureDetector gestureDetector;
	private OnDeleteListener listener;
	private View deleteButton;
	private ViewGroup itemLayout;
	private int selectItem;
	private boolean isDeteleShow;

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		gestureDetector = new GestureDetector(context, this);
		setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (isDeteleShow) {
			itemLayout.removeView(deleteButton);
			deleteButton = null;
			isDeteleShow = false;
			return false;
		} else {
			return gestureDetector.onTouchEvent(event);
		}
	}

	@Override
	public boolean onDown(MotionEvent e) {
		if (!isDeteleShow) {
			selectItem = pointToPosition((int) e.getX(), (int) e.getY());
		}
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (!isDeteleShow && Math.abs(velocityX) > Math.abs(velocityY)) {
			deleteButton = LayoutInflater.from(getContext()).inflate(
					R.layout.delete_button, null);
			deleteButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					itemLayout.removeView(deleteButton);
					deleteButton = null;
					isDeteleShow = false;
					listener.onDelete(selectItem);
				}
			});

			itemLayout = (ViewGroup) getChildAt(selectItem
					- getFirstVisiblePosition());
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			params.addRule(RelativeLayout.CENTER_VERTICAL);
			itemLayout.addView(deleteButton,params);
			isDeteleShow=true;
		}
		return false;
	}

	public interface OnDeleteListener {
		void onDelete(int index);
	}

	public void setOnDeleteListener(OnDeleteListener listener) {
		this.listener = listener;
	}

}
