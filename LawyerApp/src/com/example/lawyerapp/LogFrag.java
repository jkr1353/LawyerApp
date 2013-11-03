package com.example.lawyerapp;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class LogFrag extends ListFragment {
	
	private DaoInstance daoinstance;
	private SQLiteDatabase db;
	private LogsDao logsDao;
	private Cursor cursor;
	private EditText eText, eHours, eNotes, eMileage, eExpense;
	private Button addNewLog, deleteLog;
	private Long parentID;
	private String deleteLogStr, doneLogStr;
	private float tempFloat = 0.0f;
	private TextView totalHours;
	private float num_of_hours = 0.0f;
	
	@Override
	public void onCreate(Bundle saved) 
	{
		super.onCreate(saved);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saved) {
		View v = inflater.inflate(R.layout.main, parent,false); 
		
		deleteLogStr = "Delete Log";
        doneLogStr = "Done";
		
		daoinstance = DaoInstance.getInstance(getActivity());
		
		db = daoinstance.getDb();
		
		totalHours = (TextView) v.findViewById(R.id.totalHours);
		
		logsDao = daoinstance.getLogsDao();
		
		parentID = getActivity().getIntent().getExtras().getLong("id");
		
		totalHours.setText("Total Hours: " + calcTotalHours());
		
		addNewLog = (Button) getActivity().findViewById(R.id.buttonAdd);
		deleteLog = (Button) getActivity().findViewById(R.id.buttonDelete);
		
		deleteLog.setText(deleteLogStr);
		addNewLog.setText("New Log");
		
		final LayoutInflater lInflater = inflater;

        addNewLog.setOnClickListener(new View.OnClickListener() {
			
			@Override
			  public void onClick(View view) {
				 //openDialog1(view);
				  AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

				  TextView tVHours, tVExpense, tVMileage;
				  
				  final View AlertView = lInflater.inflate(R.layout.new_loghours_dialog, null);
				  
				  	eText = (EditText) AlertView.findViewById(R.id.editTextName);
					eHours = (EditText) AlertView.findViewById(R.id.editTextHours);
					eNotes = (EditText) AlertView.findViewById(R.id.editTextNote);
					//eMileage = (EditText) AlertView.findViewById(R.id.editTextMileage);
					//eExpense = (EditText) AlertView.findViewById(R.id.editTextExpense);
				  
					tVHours = (TextView) AlertView.findViewById(R.id.textViewHours);
					//tVExpense = (TextView) AlertView.findViewById(R.id.textViewExpense);
					//tVMileage = (TextView) AlertView.findViewById(R.id.textViewMileage);
					
					tVHours.setText("");
					//tVExpense.setText("");
					//tVMileage.setText("");
					
					eText.setHint("Name");
					eHours.setHint("Hours");
					eNotes.setHint("Notes");
					eExpense.setHint("Expense");
					eMileage.setHint("Mileage");
					
					eExpense.setVisibility(View.GONE);
					eMileage.setVisibility(View.GONE);
					
				  builder.setView(AlertView);
				  AlertDialog ad = builder.create();
				  ad.setTitle("Create New Log");
				  ad.setButton(AlertDialog.BUTTON_POSITIVE, "Create Log",
						    new DialogInterface.OnClickListener() {
						        public void onClick(DialogInterface dialog, int which) {
							         
							        float tempHours, tempExpense, tempMileage;
						        	
						        	String noteText = eText.getText().toString();
							        eText.setText("");
							        
							        checkForNull(eHours);
							        tempHours = tempFloat;
							        
							        /*
							        checkForNull(eMileage);
							        tempMileage = tempFloat;
							        
							        checkForNull(eExpense);
							        tempExpense = tempFloat;
							        */
							        
							        String tempNotes = eNotes.getText().toString();
							        
							        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
							        String comment = "" + df.format(new Date());
							        
							        Logs log = new Logs(null, noteText, parentID, comment, new Date(), tempNotes, null, tempHours, null, null);
							        logsDao.insert(log);

							        totalHours.setText("Total Hours: " + calcTotalHours());
							        
							        cursor.requery();
						        }
						    });
				  ad.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
						    new DialogInterface.OnClickListener() 
				  			{
						        public void onClick(DialogInterface dialog, int which) 
						        {
						        	
						        }
						    });
				  
						ad.show();
			  }
		});
		
        deleteLog.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				if (deleteLog.getText() == deleteLogStr)
				{
					deleteLog.setText(doneLogStr);
				}
				else
				{
					deleteLog.setText(deleteLogStr);
				}
			}
		});
        
		String textColumn = LogsDao.Properties.Name.columnName;
		
		String dateColumn = LogsDao.Properties.Date.columnName;
        String orderBy = dateColumn + " COLLATE LOCALIZED DESC";
		
        cursor = db.query(logsDao.getTablename(), null, "PARENT_ID IN " +
        		"(SELECT PARENT_ID FROM LOGS WHERE PARENT_ID = " + parentID.toString() + ")"
        		, null, null, null, orderBy);
        
        String[] from = {textColumn, LogsDao.Properties.Hours.columnName, LogsDao.Properties.LogDate.columnName};
        int[] to = { R.id.textView1, R.id.textView2, R.id.dateView };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(), R.layout.list_item2, cursor, from,
                to);
        setListAdapter(adapter);
		
		return v; 
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) 
	{
		if (deleteLog.getText() == deleteLogStr)
		{
			// PUT THE ACTIVITY/FRAG FOR EACH TIME LOG HERE
			
			final long tempID = id;
			
			final Logs newlog = logsDao.queryBuilder().where(LogsDao.Properties.Id.eq(id)).unique();
			
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

			  final View AlertView = View.inflate(getActivity(), R.layout.new_loghours_dialog, null);
					  
			  eText = (EditText) AlertView.findViewById(R.id.editTextName);
				eHours = (EditText) AlertView.findViewById(R.id.editTextHours);
				eNotes = (EditText) AlertView.findViewById(R.id.editTextNote);
				//eMileage = (EditText) AlertView.findViewById(R.id.editTextMileage);
				//eExpense = (EditText) AlertView.findViewById(R.id.editTextExpense);
				
				eText.setText(newlog.getName());
				eHours.setText(newlog.getHours()+"");
				eNotes.setText(newlog.getNotes());
				//eMileage.setText(newlog.getMileage()+"");
				//eExpense.setText(newlog.getExpenses()+"");
				
				if (eNotes.getText().toString().isEmpty())
				{
					eNotes.setHint("Enter Note");
				}
				
			  builder.setView(AlertView);
			  AlertDialog ad = builder.create();
			  ad.setTitle(newlog.getName());
			  ad.setButton(AlertDialog.BUTTON_POSITIVE, "Done",
					    new DialogInterface.OnClickListener() {
					        public void onClick(DialogInterface dialog, int which) {
					        	
					        	float tempHours, tempExpense, tempMileage;
					        	
					        	String noteText = eText.getText().toString();
						        eText.setText("");
						        
						        checkForNull(eHours);
						        
						        
						        tempHours = tempFloat;
						        
						        //checkForNull(eMileage);
						        //tempMileage = tempFloat;
						        
						        //checkForNull(eExpense);
						        //tempExpense = tempFloat;
						        
						        String tempNotes = eNotes.getText().toString();
						        
						        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
						        String comment = "" + df.format(new Date());
						        
						        Logs log = new Logs(tempID, noteText, parentID, comment, new Date(), tempNotes, null, tempHours, null, null);
						        logsDao.insertOrReplace(log);

						        totalHours.setText("Total Hours: " + calcTotalHours());
						        
						        cursor.requery();
					        }
					    });
			  ad.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
					    new DialogInterface.OnClickListener() 
			  			{
					        public void onClick(DialogInterface dialog, int which) 
					        {
					        	
					        }
					    });
			  
					ad.show();
		}
		else
		{
			//Logs newlog = logsDao.queryBuilder().where(LogsDao.Properties.Id.eq(id)).unique();
			
			totalHours.setText("Total Hours: " + calcTotalHours());
			
			logsDao.deleteByKey(id);
		}
		
		cursor.requery();
	}
	
	public void checkForNull(EditText tempText)
	{
		if (tempText.getText().toString().isEmpty())
        {
        	tempFloat = 0.0f;
        }
        else
        {
	        tempFloat = Float.parseFloat(tempText.getText().toString());
	        tempText.setText("");
        }
	}
	
	public float calcTotalHours()
	{
		ArrayList<Logs> newList = (ArrayList<Logs>) logsDao.queryBuilder().where(LogsDao.Properties.ParentID.eq(parentID)).list();
		
		num_of_hours = 0.0f;
		
		if (newList != null)
		{	
			for (Logs arrayLogs: newList)
			{
				num_of_hours += arrayLogs.getHours();
			}
		}
		
		return num_of_hours;
	}
	
}