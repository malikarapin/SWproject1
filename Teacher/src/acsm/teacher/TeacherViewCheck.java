package acsm.teacher;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class TeacherViewCheck extends Activity {
	String item;
	String selected, spinner_item;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher_view_check);
		
		final Spinner spinview = (Spinner)findViewById(R.id.spnviewteac);
		
		String subjectdata = getIntent().getStringExtra("Subject");
		
		
		try {
			JSONArray JA=new JSONArray(subjectdata);
			
			JSONObject json= null;
        	final String[] str1 = new String[JA.length()];        
        	final String[] str2 = new String[JA.length()];
        	
        	for(int i=0;i<JA.length();i++)
        	{
        		json=JA.getJSONObject(i);
        		str1[i] = json.getString("Subject_Code");
        		str2[i]=json.getString("Subject_Name_Eng");
        	}
        	
        	List<String> list = new ArrayList<String>();
        	
        	for(int i=0;i<str2.length;i++)
        	{
        		
        		list.add(str2[i]);
        		//str2[i]= "Please select Subject";
        	}
        	
        	Collections.sort(list);
        	
        	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
			(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list);
        	dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        	
        	
        	
        	spinview.setAdapter(dataAdapter);
        	
        	
        	spinview.setOnItemSelectedListener(new OnItemSelectedListener() 
        	{
        		public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long id) 
        		{
        			// TODO Auto-generated method stub

        			
        			
        			item=spinview.getSelectedItem().toString();
        			

        			Toast.makeText(getApplicationContext(), item,Toast.LENGTH_LONG).show();

        			Log.e("Item",String.valueOf(item));
        			

        		}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub
					
				}
        	});

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}}
