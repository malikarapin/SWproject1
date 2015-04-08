package acsm.teacher;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.JsonSyntaxException;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class TeacherCheck extends Activity {

	String item;

	protected Object latitude;
	protected Object longitude;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher__check);
		
		String subjectdata = getIntent().getStringExtra("Subject");
		
		 
		final String studentid = getIntent().getStringExtra("Username");
		

		//final Spinner spinner = (Spinner)findViewById(R.id.spnviewteac);                                 
		final EditText passcode = (EditText) findViewById(R.id.passcode);

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
        	final Spinner sp = (Spinner) findViewById(R.id.spnviewteac);
        	
        	List<String> list = new ArrayList<String>();
        	
        	for(int i=0;i<str2.length;i++)
        	{
        	
        		list.add(str2[i]);
        	}
        	
        	Collections.sort(list);
        	
        	
        	
        	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
			(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item, list);
        	dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        	sp.setAdapter(dataAdapter);
        	
        	
        	
        	
        	sp.setOnItemSelectedListener(new OnItemSelectedListener() 
        	{
        		public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long id) 
        		{
        			// TODO Auto-generated method stub

        			item=sp.getSelectedItem().toString();
        			

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
		
		 	Calendar c = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			final String formattedDate = df.format(c.getTime());
			//txtuser.setText(" Current : " + formattedDate);
			
			
			GPSTracker gps;
			
			gps = new GPSTracker(TeacherCheck.this);
			 
            // check if GPS enabled     
            if(gps.canGetLocation()){
                 
               latitude = gps.getLatitude();
               longitude = gps.getLongitude();
               
               Log.e("Lat&Lon",String.valueOf(latitude));
               Log.e("Lat&Lon",String.valueOf(longitude));
                 
                			
            }
            

            
            
            Button check = (Button)findViewById(R.id.addstudentid);
    		check.setOnClickListener(new OnClickListener() {
    			
    			@Override
    		public void onClick(View v) {
    		

    		String url = "http://acsm.ictte-project.com/insertcheckTeacher.php";
    		List<NameValuePair> params = new ArrayList<NameValuePair>(4);
    		
    		
    		params.add(new BasicNameValuePair("studentid", studentid.toString()));
    		params.add(new BasicNameValuePair("latitude", latitude.toString()));
    		params.add(new BasicNameValuePair("longitude", longitude.toString()));
    		params.add(new BasicNameValuePair("datetime", formattedDate.toString()));
    		params.add(new BasicNameValuePair("subject", item.toString()));
    		
    		params.add(new BasicNameValuePair("passcode", passcode.getText().toString()));
    		
    		
    		Log.e("Param",String.valueOf(params));
    		
    		
    		try {
    			
    			String resultServer = httpconnect.getHttpPost(url, params);

    			
    			Log.e("value in resultServer",String.valueOf(resultServer));

    				
    			
    			if (resultServer != null) {
    				Toast.makeText(TeacherCheck.this, "Stand By",Toast.LENGTH_SHORT).show();
    				
    				Intent intentMain = new Intent(TeacherCheck.this,TeacherViewReport.class);
    				
    				
    				intentMain.putExtra("Username", resultServer);
    				
    				
    				
    				startActivity(intentMain);
    				
    				
    			}
    					
    		} catch (JsonSyntaxException e) {
    			Toast.makeText(TeacherCheck.this,
    					"Incorrect Username and Password!",
    					Toast.LENGTH_LONG).show();
    			e.printStackTrace();
    		}

    		}
    });
            		
	}	
}
