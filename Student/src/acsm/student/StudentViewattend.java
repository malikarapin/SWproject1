package acsm.student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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

		final ListView lisView1 = (ListView) findViewById(R.id.lisView1);

		// Function Spinner
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

		// Button View
		Button btnview = (Button) findViewById(R.id.btnviewcount);
		btnview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String showdatauser = getIntent().getStringExtra("Username");

				String url = "http://acsm.ictte-project.com/viewcheckStudent.php";

				List<NameValuePair> params = new ArrayList<NameValuePair>();

				params.add(new BasicNameValuePair("student", showdatauser.toString()));
				params.add(new BasicNameValuePair("subject", item.toString()));

				try {

					JSONArray data = new JSONArray(httpconnect.getHttpPost(url,params));

					final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
					HashMap<String, String> map;

					for (int i = 0; i < data.length(); i++) {
						JSONObject c = data.getJSONObject(i);

						map = new HashMap<String, String>();
						map.put("Student_Id", c.getString("Student_Id"));
						map.put("Date_Time", c.getString("Date_Time"));
						MyArrList.add(map);
					}

					SimpleAdapter sAdap;
					sAdap = new SimpleAdapter(StudentViewattend.this,
							MyArrList, R.layout.sview_column, new String[] {
									"Student_Id", "Date_Time" }, new int[] {
									R.id.columnteacher, R.id.columnstudent });
					lisView1.setAdapter(sAdap);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
