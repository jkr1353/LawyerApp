package com.example.lawyerapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class FileFrag extends Fragment {

	ImageButton photoButton;
	
	@Override
	public void onCreate(Bundle saved) {
		super.onCreate(saved);
			
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saved) {
		View v = inflater.inflate(R.layout.file_frag, parent,false); 
		
		photoButton = (ImageButton) v.findViewById(R.id.photobutton);
		
		photoButton.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				Intent takePhoto = new Intent(getActivity(), PhotoIntentActivity.class);
				
				startActivity(takePhoto);
			}
			
		});
		
		return v; 
	}
}
