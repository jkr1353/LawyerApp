package com.example.lawyerapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LogFrag extends Fragment {
	
	@Override
	public void onCreate(Bundle saved) {
		super.onCreate(saved);
			// add stuff here to do when fragment starts
		
		
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saved) {
		View v = inflater.inflate(R.layout.log_frag, parent,false); 
		
			// add stuff to do here to the actual objects in the log_frag.xml file inflated
			// look at RecordFragment + fragment_record.xml to get an example of a fragment and its xml document interacting
			// RecordFragment is the page that displays after you click a case in the list
		
		return v; 
	}
}
