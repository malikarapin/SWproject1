package acsm.student;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class StudentCheck extends Activity {

	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_check);
		

		String showdatauser = getIntent().getStringExtra("Username");
		final TextView txtuser = (TextView)findViewById(R.id.lat);
		final Spinner spinner = (Spinner)findViewById(R.id.subject);

        
		try {
			JSONArray JA=new JSONArray(showdatauser);
			
			JSONObject json= null;
        	final String[] str1 = new String[JA.length()];        
        	final String[] str2 = new String[JA.length()];
        	
        	for(int i=0;i<JA.length();i++)
        	{
        		json=JA.getJSONObject(i);
        		str1[i] = json.getString("Subject_Code");
        		str2[i]=json.getString("Subject_Name_Eng");
        	}
        	final Spinner sp = (Spinner) findViewById(R.id.subject);
        	List<String> list = new ArrayList<String>();
        	
        	for(int i=0;i<str2.length;i++)
        	{
        		list.add(str2[i]);
        	}
        	
        	Collections.sort(list);
        	
        	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
			(getApplicationContext(), android.R.layout.simple_spinner_item, list);
        	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        	sp.setAdapter(dataAdapter);
        	
        	
        	sp.setOnItemSelectedListener(new OnItemSelectedListener() 
        	{
        		public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long id) 
        		{
        			// TODO Auto-generated method stub

        			String item=sp.getSelectedItem().toString();

        			Toast.makeText(getApplicationContext(), item,
					Toast.LENGTH_LONG).show();

        			Log.e("Item",item);
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
		
		 Calendar c = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String formattedDate = df.format(c.getTime());
			txtuser.setText(" Current : " + formattedDate);
	}
	
	
}







