package com.example.lawyerapp;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;


//import com.example.lawyerapp.ContactDao.Properties;
//import com.example.lawyerapp.Catalog;
import com.example.lawyerapp.MainActivity;
import com.example.lawyerapp.R;
//import com.example.lawyerapp.RecordListFrag;
import com.example.lawyerapp.DaoMaster.DevOpenHelper;

public class MainActivity extends ListActivity{

	private SQLiteDatabase db;

    private DaoInstance daoinstance;
    
    private CasesDao caseDao;
    
    private Cursor cursor;
    
    private Button addNewCase;
    
    private String noteText, caseType;

    @SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        daoinstance = DaoInstance.getInstance(this);
        
        setContentView(R.layout.all_cases);
        
        db = daoinstance.getDb();
        caseDao = daoinstance.getCaseDao();
        
        addNewCase = (Button) findViewById(R.id.buttonAdd);

        addNewCase.setOnClickListener(new View.OnClickListener() {
			
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
						        	
						        	EditText eText = (EditText) AlertView.findViewById(R.id.eTextNote);
						            EditText eType = (EditText) AlertView.findViewById(R.id.eTextType);
						            
						            noteText = eText.getText().toString();
						            caseType = eType.getText().toString();
						        	
						        	Cases newCase = new Cases(null, noteText, caseType);

						        	caseDao.insert(newCase);
						            Log.d("DaoExample", "Inserted new note, ID: " + newCase.getId());

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
        
        /*
        DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "cases-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        caseDao = daoSession.getCasesDao();
        logsDao = daoSession.getLogsDao();
*/
        String textColumn = CasesDao.Properties.Name.columnName;
        //String orderBy = textColumn + " COLLATE LOCALIZED ASC";
        
        cursor = db.query(caseDao.getTablename(), caseDao.getAllColumns(), null, null, null, null, null/*orderBy*/);
        String[] from = {textColumn, CasesDao.Properties.Casetype.columnName};
        int[] to = { R.id.textView1, R.id.textView2 };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_item2, cursor, from,
                to);
        setListAdapter(adapter);
        
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) 
    {
        
    	Intent newIntent = new Intent(this, CaseActivity.class);
    	
    	Cases newCase = caseDao.queryBuilder().where(CasesDao.Properties.Id.eq(id)).unique();
    	String newString = newCase.getName();
    	newIntent.putExtra("name", newString);
    	newIntent.putExtra("id", id);
    	
    	startActivity(newIntent);
    	
    	//caseDao.deleteByKey(id);
        //Log.d("DaoExample", "Deleted note, ID: " + id);
        //cursor.requery();
    }

}
