package com.example.lawyerapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
FragmentManager fm = getSupportFragmentManager(); 
		
		Fragment frag = fm.findFragmentById(R.id.fragmentContainer);
		
		if (frag == null) {
			frag = new RecordListFrag(); // RecordFragment();  
			//frag = new RecordFragment(); 
			FragmentTransaction fta = fm.beginTransaction(); 
			fta.add(R.id.fragmentContainer, frag);
			fta.commit(); 
		}
		
	}
/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
*/
}
