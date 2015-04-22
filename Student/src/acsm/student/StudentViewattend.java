package acsm.student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonSyntaxException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class StudentViewattend extends Activity {

	String item;
	String selected, spinner_item;
	TextView txtview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_viewatten);

		String subjectdata = getIntent().getStringExtra("Subject");

		
		//Function Spinner
		try {
			JSONArray JA = new JSONArray(subjectdata);

			JSONObject json = null;
			final String[] str1 = new String[JA.length()];
			final String[] str2 = new String[JA.length()];

			for (int i = 0; i < JA.length(); i++) {
				json = JA.getJSONObject(i);
				str1[i] = json.getString("Subject_Code");
				str2[i] = json.getString("Subject_Name_Eng");
			}
			final Spinner sp = (Spinner) findViewById(R.id.subjectviewcount);
			List<String> list = new ArrayList<String>();

			for (int i = 0; i < str2.length; i++) {

				list.add(str2[i]);

			}

			Collections.sort(list);

			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
					getApplicationContext(),
					android.R.layout.simple_spinner_dropdown_item, list);
			dataAdapter
					.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);

			sp.setAdapter(dataAdapter);

			sp.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int position, long id) {
					// TODO Auto-generated method stub

					item = sp.getSelectedItem().toString();

					Toast.makeText(getApplicationContext(), item,
							Toast.LENGTH_LONG).show();

					Log.e("Item", String.valueOf(item));

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

		//Button View 
		Button btnview = (Button) findViewById(R.id.btnviewcount);
		btnview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String showdatauser = getIntent().getStringExtra("Username");

				String url = "http://acsm.ictte-project.com/viewcheckStudent.php";
				List<NameValuePair> params = new ArrayList<NameValuePair>();

				params.add(new BasicNameValuePair("student", showdatauser.toString()));
				params.add(new BasicNameValuePair("subject", item.toString()));

				

				txtview = (TextView) findViewById(R.id.txtview);

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
			}

		});
	}
}
