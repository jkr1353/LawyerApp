package com.example.lawyerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;

public class RecordAct extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recordact);
		
		
		FragmentManager fm = getSupportFragmentManager(); 
		
		Fragment frag = fm.findFragmentById(R.id.fragmentContainer); 
		
		if (frag == null) {
			
			
			
			Bundle args = new Bundle();
			Intent i=getIntent();
            args.putSerializable("record",i.getSerializableExtra("record"));
   
            
			frag = new RecordFragment(); // RecordFragment();  
			frag.setArguments(args);
			FragmentTransaction fta = fm.beginTransaction(); 
			fta.add(R.id.fragmentContainer, frag);
			fta.commit(); 
		}
	}


}