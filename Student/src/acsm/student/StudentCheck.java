package acsm.student;







import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class StudentCheck extends Activity {

	TextView txtpass;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_check);
		
	
		
		final TextView txtUser = (TextView) findViewById(R.id.lat);
		final Button test = (Button) findViewById(R.id.btnsubmitcheck);
		final Spinner spin = (Spinner) findViewById(R.id.subject);
		final AlertDialog.Builder ad = new AlertDialog.Builder(this);
		
		String showdatauser = getIntent().getStringExtra("Username");
		
		
		
		
		String url = "http://acsm.ictte-project.com/spinner.php";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		
		params.add(new BasicNameValuePair("student", showdatauser.toString()));

		String resultServer;
		
		resultServer = getHttpPost(url, params);
		
			
		try {
			//ค่าที่ได้จาก PHP อยู่ที่ ตัวแปร resultServer
			resultServer = getHttpPost(url, params);

			Log.e("value in resultServer",resultServer.toString());
			txtUser.setText("Username is:" + resultServer.toString());
			
			
				
				

		} catch (JsonSyntaxException e) {
			Toast.makeText(StudentCheck.this,"Transmission outages",
					Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		
		
		 
		

		test.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {


			}
		}

		);

	}

	public String getHttpPost(String url, List<NameValuePair> params) {
		StringBuilder str = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpPost httpGet = new HttpPost(url);

		try {
			httpGet.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) { // Status OK
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					str.append(line);
				}
			} else {
				Log.e("Log", "Failed to download result..");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str.toString();
		
	}
 

	
}


