package acsm.student;

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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;


public class StudentQuestion extends Activity {

	int pos1;



	String item;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_question);

		final String subjectdata = getIntent().getStringExtra("Subject");

		final String studentid = getIntent().getStringExtra("Username");
		
		
		final TextView question = (TextView) findViewById(R.id.question);
		final RadioButton answer1 = (RadioButton) findViewById(R.id.a0);
		final RadioButton answer2 = (RadioButton) findViewById(R.id.a1);
		final RadioButton answer3 = (RadioButton) findViewById(R.id.a2);
		final RadioButton answer4 = (RadioButton) findViewById(R.id.a3);
		
		final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioG1);
		

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

			final Spinner sp = (Spinner) findViewById(R.id.subject);
			List<String> list = new ArrayList<String>();

			for (int i = 0; i < str2.length; i++) {

				list.add(str2[i]);
				// str2[i]= "Please select Subject";
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

		
		
		 

		
		// Btuton Select Question
		Button selectSuject = (Button) findViewById(R.id.selectsubject);
		selectSuject.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				String url = "http://acsm.ictte-project.com/sentSelectQuestionFromStudent.php";
				List<NameValuePair> params = new ArrayList<NameValuePair>();

				params.add(new BasicNameValuePair("subject", item.toString()));

				Log.e("param", String.valueOf(params));

				// String resultServer;

				try {
					JSONArray data = new JSONArray(httpconnect.getHttpPost(url,
							params));

					Log.i("Show ResultServer", String.valueOf(data));

					

					for (int i = 0; i < data.length(); i++) {

						JSONObject c = data.getJSONObject(i);

						final String question_detail = c
								.getString("Proposition");
						final String c_1 = c.getString("Result1");
						final String c_2 = c.getString("Result2");
						final String c_3 = c.getString("Result3");
						final String c_4 = c.getString("Result4");

						question.setText(question_detail);

						answer1.setText(c_1);

						answer2.setText(c_2);

						answer3.setText(c_3);

						answer4.setText(c_4);
					}
					
					

					

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					question.setText("Question");

					answer1.setText("Choice1");

					answer2.setText("Choice2");

					answer3.setText("Choice3");

					answer4.setText("Choice4");
					
					Toast.makeText(StudentQuestion.this, "There are no questions in this course",Toast.LENGTH_SHORT).show();
					
				}
				
			}
		});
		
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					// TODO Auto-generated method stub
					
				    	//Method 2 For Getting Index of RadioButton
				   	 int pos1=radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));

				    	Toast.makeText(getBaseContext(), "You Select Choice "+String.valueOf(pos1+1),
						Toast.LENGTH_SHORT).show();
				}
			});
		
		
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final String formattedDate = df.format(c.getTime());
		
		 Button save = (Button)findViewById(R.id.btnsubmitcheck);
		  save.setOnClickListener(new OnClickListener() {
		  
		  @Override public void onClick(View v) {
			  
			  AlertDialog.Builder alertDialog = new AlertDialog.Builder(StudentQuestion.this);
				alertDialog.setTitle("Confirm Select Choice...");
				alertDialog.setMessage("You Want Confirm Answer");
				alertDialog.setIcon(R.drawable.ic_launcher);
				alertDialog.setPositiveButton("YES",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int which) {
								
								String url = "http://acsm.ictte-project.com/sentAnswerFromStudent.php";
								List<NameValuePair> params = new ArrayList<NameValuePair>();

								params.add(new BasicNameValuePair("subject", item.toString()));
								
								params.add(new BasicNameValuePair("studentID", studentid.toString()));
								
								params.add(new BasicNameValuePair("datatime", formattedDate.toString()));
								
								params.add(new BasicNameValuePair("answer", String.valueOf(pos1+1)));

								Log.e("param", String.valueOf(params));
								
								Intent intentMain = new Intent(StudentQuestion.this,StudentMenu.class);
								
								startActivity(intentMain);
							}
						});

				alertDialog.setNegativeButton("NO",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// คลิกไม่ cancel dialog
								dialog.cancel();
							}
						});

				alertDialog.show();
			  
			  
		  } });
		
		
	}
}
