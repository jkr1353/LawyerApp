package com.example.lawyerapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FileFrag extends Fragment {

	@Override
	public void onCreate(Bundle saved) {
		super.onCreate(saved);
			
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saved) {
		View v = inflater.inflate(R.layout.file_frag, parent,false); 
		
			
		
		return v; 
	}
}
