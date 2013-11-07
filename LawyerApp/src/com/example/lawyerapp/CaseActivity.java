package com.example.lawyerapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CaseActivity extends FragmentActivity {

    private TextView title;
    //private Record mRecord; 
	private EditText mEditTitle;
	private Button mDocsBut;
	private Button mContactBut;
	private Button mTimeBut;
	private Button newHoursButton, newExpenseButton, newMileageButton, deleteButton;
	private TextView mTitle;
	//private ArrayList<Record> mCat; 
	private Fragment contactfrag; //1
	private Fragment filefrag;    //2
	private Fragment logfrag;     //3
	private int FragSelect=0;
	private Long caseID;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_record);

        newHoursButton=(Button) findViewById(R.id.buttonNewHours);
        newExpenseButton=(Button) findViewById(R.id.buttonNewExpense);
        newMileageButton=(Button) findViewById(R.id.buttonNewMileage);
		deleteButton=(Button) findViewById(R.id.buttonDelete);
        
        title = (TextView) findViewById(R.id.Title);
        
        String newName = getIntent().getExtras().getString("name");
        caseID = getIntent().getExtras().getLong("id");
        
        title.setText(newName);
    		
		mEditTitle=(EditText) findViewById(R.id.editTitle);
		mEditTitle.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence c, int start, int before, int count) {
				
				//mTitle.setText(mRecord.getmTitle());
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
		
		/*
		final LinearLayout editLayout = (LinearLayout) findViewById(R.id.editLayout);
		final LinearLayout titleLayout = (LinearLayout) findViewById(R.id.titleLayout);
		
		if(!(v instanceof EditText)) {

	        setOnTouchListener(new OnTouchListener() {

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
		*/
		
		mDocsBut=(Button) findViewById(R.id.recentDocs);
		mContactBut=(Button) findViewById(R.id.recentContacts);
		mTimeBut=(Button) findViewById(R.id.recentTimeLogs);
		
		contactfrag = new ContactFrag();
		filefrag = new FileFrag();
		logfrag = new LogFrag();
		final android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
		
		final int buttonColor, buttonSelected;
		
		buttonColor = getResources().getColor(R.color.colorScheme2);
		buttonSelected = getResources().getColor(R.color.tabSelected);
		
		newHoursButton.setBackgroundColor(buttonColor);
		newExpenseButton.setBackgroundColor(buttonColor);
		newMileageButton.setBackgroundColor(buttonColor);
		deleteButton.setBackgroundColor(buttonColor);
		
		mDocsBut.setOnClickListener(new View.OnClickListener() {

			  @Override
			  public void onClick(View view) {
				  mDocsBut.setBackgroundColor(buttonSelected);
				  mContactBut.setBackgroundColor(buttonColor);
				  mTimeBut.setBackgroundColor(buttonColor);
				  
				  
					if (FragSelect==0)
					{
						FragmentTransaction fta = fm.beginTransaction();
						Bundle bundle = new Bundle();
						bundle.putLong("id", caseID);
						filefrag.setArguments(bundle);
						fta.add(R.id.tabfrag, filefrag);
						fta.commit(); 
						FragSelect=2;
					}
					
					
					if (FragSelect != 2) {
						if(FragSelect==1)
						{
							FragmentTransaction fta = fm.beginTransaction();
							fta.remove(contactfrag).commit();
						}
						else  //3
						{
							FragmentTransaction fta = fm.beginTransaction();
							fta.remove(logfrag).commit();
						}
						FragmentTransaction fta = fm.beginTransaction();
						Bundle bundle = new Bundle();
						bundle.putLong("id", caseID);
						filefrag.setArguments(bundle);
						fta.add(R.id.tabfrag, filefrag);
						fta.commit(); 
						FragSelect=2;
					}
			  }

			});
		mContactBut.setOnClickListener(new View.OnClickListener() {

			  @Override
			  public void onClick(View view) {
				  mDocsBut.setBackgroundColor(buttonColor);
				  mContactBut.setBackgroundColor(buttonSelected);
				  mTimeBut.setBackgroundColor(buttonColor);
				  
				  if (FragSelect==0)
					{
						FragmentTransaction fta = fm.beginTransaction();
						fta.add(R.id.tabfrag, contactfrag);
						fta.commit(); 
						FragSelect=1;
					}
					
					
					if (FragSelect != 1) {
						if(FragSelect==2)
						{
							FragmentTransaction fta = fm.beginTransaction();
							fta.remove(filefrag).commit();
						}
						else  //3
						{
							FragmentTransaction fta = fm.beginTransaction();
							fta.remove(logfrag).commit();
						}
						FragmentTransaction fta = fm.beginTransaction();
						fta.add(R.id.tabfrag, contactfrag);
						fta.commit(); 
						FragSelect=1;
					}
			  }

			});
		mTimeBut.setOnClickListener(new View.OnClickListener() {

			  @Override
			  public void onClick(View view) {
				  mDocsBut.setBackgroundColor(buttonColor);
				  mContactBut.setBackgroundColor(buttonColor);
				  mTimeBut.setBackgroundColor(buttonSelected);
				  
					
				  if (FragSelect==0)
					{
						FragmentTransaction fta = fm.beginTransaction();
						Bundle bundle = new Bundle();
						bundle.putLong("id", caseID);
						logfrag.setArguments(bundle);
						fta.add(R.id.tabfrag, logfrag);
						fta.commit(); 
						FragSelect=3;
					}
					
					
					if (FragSelect != 3) {
						if(FragSelect==1)
						{
							FragmentTransaction fta = fm.beginTransaction();
							fta.remove(contactfrag).commit();
						}
						else  //2
						{
							FragmentTransaction fta = fm.beginTransaction();
							fta.remove(filefrag).commit();
						}
						
						FragmentTransaction fta = fm.beginTransaction();
						Bundle bundle = new Bundle();
						bundle.putLong("id", caseID);
						logfrag.setArguments(bundle);
						fta.add(R.id.tabfrag, logfrag);
						fta.commit(); 
						FragSelect=3;
					}
			  }
		});
    }  
	
	@Override
	public void onResume()
	{
		super.onResume();
		
		newHoursButton.setText("New");
		newExpenseButton.setVisibility(View.INVISIBLE);
		newMileageButton.setVisibility(View.INVISIBLE);
		deleteButton.setText("Delete");
	}
}