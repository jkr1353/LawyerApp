package com.example.lawyerapp;

import java.util.ArrayList;
import java.util.Collections;

import android.R.drawable;
import android.app.Fragment;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContactFrag extends Fragment {

	private ArrayList<String> phoneContactList;
	private ArrayList<String> phoneNumbers;
	private ListView lv;
	
	@Override
	public void onCreate(Bundle saved) {
		super.onCreate(saved);
		
		phoneContactList = new ArrayList<String>(); 
		phoneNumbers = new ArrayList<String>();
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saved) {
		View v = inflater.inflate(R.layout.contact_frag, parent,false); 
		
		lv=(ListView)v.findViewById(R.id.contact_listview);
		
		ContentResolver cr = getActivity().getContentResolver();
		Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

		if (cur.getCount() > 0) {
		while (cur.moveToNext()) {
		    String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
		    String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
		    if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
		      // This inner cursor is for contacts that have multiple numbers.
		    	//String [] phonenumber=new String[] { id };
		      Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[] { id }, null);
		      int PhoneIdx = pCur.getColumnIndex(Phone.DATA);
		      while (pCur.moveToNext()) {
		        phoneContactList.add(name);
		        //phoneNumbers.add(pCur.getString(PhoneIdx));
		      }
		      pCur.close();
		    }
		  }
		
		  //Collections.sort(phoneContactList); 
		  //int cnt = phoneContactList.size();

		  
		  ArrayAdapter<String> adapter= new ArrayAdapter<String>(getActivity(), R.layout.contact_item,R.id.contact_display, phoneContactList);
		  lv.setAdapter(adapter);
		  lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		}
		cur.close();
			
			
		
		return v; 
	}
}
