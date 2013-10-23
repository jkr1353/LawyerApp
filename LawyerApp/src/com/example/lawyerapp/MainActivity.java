package com.example.lawyerapp;


import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
private ArrayList<Record> mCat; 
private Fragment frag;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mCat = Catalog.get(this).getRecords(); 
FragmentManager fm = getSupportFragmentManager(); 
		
		frag = fm.findFragmentById(R.id.fragmentContainer);
		
		if (frag == null) {
			frag = new RecordListFrag(); // RecordFragment();  
			//frag = new RecordFragment(); 
			FragmentTransaction fta = fm.beginTransaction(); 
			fta.add(R.id.fragmentContainer, frag);
			fta.commit(); 
		
			Button b=(Button)findViewById(R.id.button1);
			b.setOnClickListener(new View.OnClickListener() {

				  @Override
				  public void onClick(View view) {
					 //openDialog1(view);
					  AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
					  final LayoutInflater inflater = getLayoutInflater();

					  final View AlertView = inflater.inflate(R.layout.new_case_dialog, null);
					  builder.setView(AlertView);
					  AlertDialog ad = builder.create();
					  ad.setTitle("Create New Case");
					  ad.setButton(AlertDialog.BUTTON_POSITIVE, "Create Case",
							    new DialogInterface.OnClickListener() {
							        public void onClick(DialogInterface dialog, int which) {
							        	
							        	EditText et = (EditText)AlertView.findViewById(R.id.casename);
							        	Catalog.get(MainActivity.this).CreateRecord(et.getText().toString());
							        	
							        	((RecordListFrag)frag).reset();
							        }
							    });
					  ad.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
							    new DialogInterface.OnClickListener() {
							        public void onClick(DialogInterface dialog, int which) {
							        }
							    });
					  
							ad.show();
				  }

				});
			
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
