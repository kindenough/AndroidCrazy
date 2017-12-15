package com.example.androidcrazy.listener;

import java.util.ArrayList;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Environment;
import android.widget.Toast;

public class MyUseGestureListener implements OnGesturePerformedListener {

	private Activity context;
	
	public MyUseGestureListener(Activity context){
		this.context = context;
	}
	
	@Override
	public void onGesturePerformed(GestureOverlayView gestureOverlayView, final Gesture gesture) {
		GestureLibrary gestureLibrary = GestureLibraries.fromFile(Environment.getExternalStorageDirectory() + "/mygesture");
		if(gestureLibrary.load()){
			Toast.makeText(context, "手势文件装载成功", 3000).show();
		} else {
			Toast.makeText(context, "手势文件装载失败", 3000).show();
			return;
		}
		ArrayList<Prediction> preditions = gestureLibrary.recognize(gesture);
		ArrayList<String> result = new ArrayList<String>();
		for(Prediction p : preditions){
			if(p.score > 2){
				result.add("与手势【" + p.name + "】相似度为" + p.score);
				break;
			}
		}
		if(result.size() == 0){
			Toast.makeText(context, "没有找到匹配的手势", 3000).show();
		} else {
			Toast.makeText(context, result.get(0), 3000).show();
		}
	}

}
