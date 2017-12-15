package com.example.androidcrazy.listener;

import com.example.androidcrazy.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MyLoadGestureListener implements OnGesturePerformedListener {

	private Activity context;
	
	public MyLoadGestureListener(Activity context){
		this.context = context;
	}
	
	@Override
	public void onGesturePerformed(GestureOverlayView gestureOverlayView, final Gesture gesture) {
		View saveDialog = context.getLayoutInflater().inflate(R.layout.gesture_save_dialog_view, null);
		ImageView gestureImg = (ImageView)saveDialog.findViewById(R.id.gestureImageView);
		final EditText gestureName = (EditText)saveDialog.findViewById(R.id.gestureName);
		Bitmap bitmap = gesture.toBitmap(128, 128, 10, 0xFFFF0000);
		gestureImg.setImageBitmap(bitmap);
		
		new AlertDialog.Builder(context)
		.setView(saveDialog)
		.setPositiveButton("保存", new android.content.DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				GestureLibrary gestureLib = GestureLibraries.fromFile(Environment.getExternalStorageDirectory() + "/mygesture");
				gestureLib.addGesture(gestureName.getText().toString(), gesture);
				gestureLib.save();
			}
		})
		.setNegativeButton("取消", null)
		.show();
	}

}
