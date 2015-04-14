package acsm.teacher;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import com.google.gson.JsonSyntaxException;

import android.util.Log;
import android.widget.SimpleAdapter;


public class TeacherViewReport extends Activity {

	String formattedDate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher_view_report);
		
		// Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 12) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        
        
        final String subjectdata = getIntent().getStringExtra("subject");
        
        final String passcode = getIntent().getStringExtra("passcode");
        
        final String time = getIntent().getStringExtra("time");
        
        final TextView showsubject = (TextView) findViewById(R.id.showsubject);
        
        final TextView showcount = (TextView) findViewById(R.id.showcount);
        
        final EditText addstudent = (EditText) findViewById(R.id.addStudentCheck);

        
        showsubject.setText(subjectdata);
        
        //Time
        Calendar cc = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final String formattedDate = df.format(cc.getTime());
        
        
        
     // listView1
        final ListView lisView1 = (ListView)findViewById(R.id.ListView1); 	
        
        
        String url = "http://acsm.ictte-project.com/Teacher-view-report.php";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        
		params.add(new BasicNameValuePair("subject", subjectdata.toString()));
		params.add(new BasicNameValuePair("pass_Code", passcode.toString()));
		params.add(new BasicNameValuePair("time_check", time.toString()));
        
		
		//JSONArray data = null;
        
        	try {
			
        		//String resultServer = httpconnect.getHttpPost(url, params);
        		
        		JSONArray data = new JSONArray(httpconnect.getHttpPost(url, params));
			
			final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map;
			
			for(int i = 0; i < data.length(); i++){
                JSONObject c = data.getJSONObject(i);
                
    			map = new HashMap<String, String>();
    			map.put("Student_Id", c.getString("Student_Id"));
    			map.put("Date_Time", c.getString("Date_Time"));
    			MyArrList.add(map);	
    			
			}
			
			int count = data.length();
			Log.i("data length", String.valueOf(data.length()));
			
			showcount.setText(String.valueOf(count));
			
			SimpleAdapter sAdap;
	        sAdap = new SimpleAdapter(TeacherViewReport.this, MyArrList, R.layout.view_report_column,
	                new String[] {"Student_Id", "Date_Time"}, new int[] {R.id.colMemberID, R.id.colName});      
	        lisView1.setAdapter(sAdap);
	        
      
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        	

		//Button Refresh for Refresh in Listview
		Button refresh = (Button)findViewById(R.id.refresh);
		refresh.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		//Intent refresh = new Intent(getApplicationContext(),TeacherMenu.class);
		
		
				 String url = "http://acsm.ictte-project.com/Teacher-view-report.php";
			        List<NameValuePair> params = new ArrayList<NameValuePair>();
			        
					params.add(new BasicNameValuePair("subject", subjectdata.toString()));
					params.add(new BasicNameValuePair("pass_Code", passcode.toString()));
					params.add(new BasicNameValuePair("time_check", time.toString()));
			        
			        
			        	try {
						
			        		//String resultServer = httpconnect.getHttpPost(url, params);
			        		
						JSONArray data = new JSONArray(httpconnect.getHttpPost(url, params));
						
						final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
						HashMap<String, String> map;
						
						for(int i = 0; i < data.length(); i++){
			                JSONObject c = data.getJSONObject(i);
			                
			    			map = new HashMap<String, String>();
			    			map.put("Student_Id", c.getString("Student_Id"));
			    			map.put("Date_Time", c.getString("Date_Time"));
			    			MyArrList.add(map);
			    			
						}
						
						int count = data.length();
						Log.i("data length", String.valueOf(data.length()));
						
						showcount.setText(String.valueOf(count));
						
						
						SimpleAdapter sAdap;
				        sAdap = new SimpleAdapter(TeacherViewReport.this, MyArrList, R.layout.view_report_column,
				                new String[] {"Student_Id", "Date_Time"}, new int[] {R.id.colMemberID, R.id.colName});      
				        lisView1.setAdapter(sAdap);
		
		
		
		//startActivity(refresh);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}        	
			        	
});
		
		

	        //Button Add Student Id for Check
	        Button add = (Button)findViewById(R.id.btnaddstu);
	        add.setOnClickListener(new OnClickListener() {
				
				@Override
			public void onClick(View v) {
					
					String url = "http://acsm.ictte-project.com/add-student-check.php";
			        List<NameValuePair> params = new ArrayList<NameValuePair>();
			        
			        params.add(new BasicNameValuePair("studentid", addstudent.getText().toString()));
					params.add(new BasicNameValuePair("subject", subjectdata.toString()));
					params.add(new BasicNameValuePair("passcode", passcode.toString()));
					params.add(new BasicNameValuePair("datetime", formattedDate.toString()));
					
					
					Log.e("Param",String.valueOf(params));
		    		
		    		
		    		try {
		    			
		    			String resultServer = httpconnect.getHttpPost(url, params);

		    			
		    			Log.e("value in resultServer",String.valueOf(resultServer));

		    				
		    			
		    			if (resultServer != null) {
		    				Toast.makeText(TeacherViewReport.this, "Stand By",Toast.LENGTH_SHORT).show();
		
		    			}
		    					
		    		} catch (JsonSyntaxException e) {
		    			Toast.makeText(TeacherViewReport.this,
		    					"Incorrect Username and Password!",
		    					Toast.LENGTH_LONG).show();
		    			e.printStackTrace();
		    		}

		    		}
		    });
					
	        // Button OK Back to menu
	        Button okbackMenu = (Button)findViewById(R.id.btnokstu);
			 okbackMenu.setOnClickListener(new OnClickListener() {
					
						@Override
					public void onClick(View v) {
							Intent okbackmenu = new Intent(TeacherViewReport.this,TeacherMenu.class);
							
							startActivity(okbackmenu);
						}

				 });
	        
	        
	}
}
