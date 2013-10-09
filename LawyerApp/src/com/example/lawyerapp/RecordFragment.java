package com.example.lawyerapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RecordFragment extends Fragment {
	private Record mRecord; 
	private EditText mEditTitle;
	private ImageView mDocsImage;
	private ImageView mContactImage;
	private ImageView mTimeImage;
	private TextView mTitle;
	
	@Override
	public void onCreate(Bundle saved) {
		
		super.onCreate(saved);
		Bundle bundle=getArguments();
		
		
		mRecord = (Record)bundle.getSerializable("record");
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saved) {
		View v = inflater.inflate(R.layout.fragment_record, parent,false); 
		
		mTitle=(TextView) v.findViewById(R.id.Title);
		mTitle.setText(mRecord.getmTitle());
		mEditTitle=(EditText) v.findViewById(R.id.editTitle);
		mEditTitle.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence c, int start, int before, int count) {
				mRecord.setmTitle(c.toString());
				mTitle.setText(mRecord.getmTitle());
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			
		}); 
		
		final LinearLayout editLayout = (LinearLayout) v.findViewById(R.id.editLayout);
		final LinearLayout titleLayout = (LinearLayout) v.findViewById(R.id.titleLayout);
		
		if(!(v instanceof EditText)) {

	        v.setOnTouchListener(new OnTouchListener() {

	            public boolean onTouch(View v, MotionEvent event) {
	            	editLayout.setVisibility(View.GONE);
                    titleLayout.setVisibility(View.VISIBLE);
                    mTitle.setVisibility(View.VISIBLE);
	                return false;
	            }

	        });
	    }
		
		mTitle.setOnClickListener(new View.OnClickListener() {

			  @Override
			  public void onClick(View view) {
				  mTitle.setVisibility(View.GONE);
				  if(editLayout.getVisibility() == View.VISIBLE)
				  {
	                    editLayout.setVisibility(View.GONE);
	                    titleLayout.setVisibility(View.VISIBLE);
				  }
	                else
	                {
	                    editLayout.setVisibility(View.VISIBLE);
	                    titleLayout.setVisibility(View.GONE);
	                }
			  }

			});
		
		
		mDocsImage=(ImageView) v.findViewById(R.id.docsImage);
		mContactImage=(ImageView) v.findViewById(R.id.contactImage);
		mTimeImage=(ImageView) v.findViewById(R.id.timeImage);
		mDocsImage.setOnClickListener(new View.OnClickListener() {

			  @Override
			  public void onClick(View view) {
				  Toast.makeText(getActivity().getApplicationContext(),"Documents", Toast.LENGTH_SHORT).show();
			  }

			});
		mContactImage.setOnClickListener(new View.OnClickListener() {

			  @Override
			  public void onClick(View view) {
				  Toast.makeText(getActivity().getApplicationContext(),"Contacts", Toast.LENGTH_SHORT).show();
			  }

			});
		mTimeImage.setOnClickListener(new View.OnClickListener() {

			  @Override
			  public void onClick(View view) {
			    Toast.makeText(getActivity().getApplicationContext(),"Time Logs", Toast.LENGTH_SHORT).show();
			  }

			});
		
		
		
		
		
		
		return v; 
	}
}
