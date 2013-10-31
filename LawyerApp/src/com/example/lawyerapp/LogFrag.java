package com.example.lawyerapp;

import java.text.DateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class LogFrag extends ListFragment {
	
	private DaoInstance daoinstance;
	private SQLiteDatabase db;
	private LogsDao logsDao;
	private Cursor cursor;
	private EditText eText, eHours;
	private Button addNewLog, deleteLog;
	private Long parentID;
	private String deleteLogStr, doneLogStr;
	private float tempFloat = 0.0f;
	
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
		
		
		
		logsDao = daoinstance.getLogsDao();
		
		parentID = getActivity().getIntent().getExtras().getLong("id");
		
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

				  final View AlertView = lInflater.inflate(R.layout.new_log_dialog, null);
				  
				  builder.setView(AlertView);
				  AlertDialog ad = builder.create();
				  ad.setTitle("Create New Log");
				  ad.setButton(AlertDialog.BUTTON_POSITIVE, "Create Log",
						    new DialogInterface.OnClickListener() {
						        public void onClick(DialogInterface dialog, int which) {
						        	
						        	eText = (EditText) AlertView.findViewById(R.id.editTextNote);
									eHours = (EditText) AlertView.findViewById(R.id.editTextHours);
						        	
						        	String noteText = eText.getText().toString();
							        eText.setText("");
							        
							        if (eHours.getText().toString().isEmpty())
							        {
							        	tempFloat = 0.0f;
							        }
							        else
							        {
								        tempFloat = Float.parseFloat(eHours.getText().toString());
								        eHours.setText("");
							        }

							        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
							        String comment = "" + df.format(new Date());
							        
							        Logs log = new Logs(null, parentID, comment, new Date(), noteText, null, null, tempFloat);
							        logsDao.insert(log);

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
        
		String textColumn = LogsDao.Properties.Notes.columnName;
		
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
		}
		else
		{
			logsDao.deleteByKey(id);
		}
		
		cursor.requery();
	}
	
}