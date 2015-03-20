package acsm.student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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

		Button quest = (Button) findViewById(R.id.btnviewcount);
		quest.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String showdatauser = getIntent().getStringExtra("Username");

				String url = "http://acsm.ictte-project.com/viewcheckStudent.php";
				List<NameValuePair> params = new ArrayList<NameValuePair>();

				params.add(new BasicNameValuePair("student", showdatauser
						.toString()));
				params.add(new BasicNameValuePair("subject", item.toString()));

				String resultServer;

				txtview = (TextView) findViewById(R.id.txtview);

				try {

					resultServer = getHttpPost(url, params);

					Log.e("value in resultServer", String.valueOf(resultServer));

					if (resultServer != null) {
						Toast.makeText(StudentViewattend.this, "Stand By",
								Toast.LENGTH_SHORT).show();

						Intent intentMain = new Intent(StudentViewattend.this,
								StudentCheck.class);

						txtview.setText(resultServer);
					}

					Log.e("inrent to check", String.valueOf(resultServer));
					Log.e("submit", String.valueOf(showdatauser));

				} catch (JsonSyntaxException e) {
					Toast.makeText(StudentViewattend.this,
							"Incorrect Username and Password!",
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}

			}

		});
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
