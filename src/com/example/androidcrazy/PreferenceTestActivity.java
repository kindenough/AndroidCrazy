package com.example.androidcrazy;

import android.os.Bundle;
import android.preference.PreferenceActivity;


public class PreferenceTestActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	
	}
}
