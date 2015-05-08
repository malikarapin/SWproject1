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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class TeacherQuestion extends Activity {
	String item;
	 int selectedPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher_question);
		
		String subjectdata = getIntent().getStringExtra("Subject");
		
		 
		final String studentid = getIntent().getStringExtra("Username");
		

		final EditText question = (EditText) findViewById(R.id.etxtquestion);
		final EditText ans1 = (EditText) findViewById(R.id.ans1);
		final EditText ans2 = (EditText) findViewById(R.id.ans2);
		final EditText ans3 = (EditText) findViewById(R.id.ans3);
		final EditText ans4 = (EditText) findViewById(R.id.ans4);
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
        	final Spinner sp = (Spinner) findViewById(R.id.quessubject);
        	

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
		
		Button save = (Button)findViewById(R.id.savequstion);
		save.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
				
				  final String[] choice = {"1", "2", "3", "4"};
				  
				 

				  AlertDialog.Builder builder = new AlertDialog.Builder(TeacherQuestion.this);
				  builder.setTitle("Please select the correct answer.");
				  builder.setSingleChoiceItems(choice, 0, new DialogInterface.OnClickListener() {
				      public void onClick(DialogInterface dialog, int choice1) {
				    	  
				   Toast.makeText(getApplicationContext(),"You choose this choice " +choice[choice1], Toast.LENGTH_SHORT).show();
				   
				   
				   selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
				   
				      }
				  });
				  
				  builder.setPositiveButton("Yes",
				    new DialogInterface.OnClickListener() {
				     public void onClick(DialogInterface dialog, int choice1) {
				    	 
				    	 String url = "http://acsm.ictte-project.com/insertquestion.php";
				    		
				    		List<NameValuePair> params = new ArrayList<NameValuePair>();
				    		
				    		params.add(new BasicNameValuePair("teacid", studentid.toString()));
				    		params.add(new BasicNameValuePair("datetime", formattedDate.toString()));
				    		params.add(new BasicNameValuePair("subject", item.toString()));
				    		params.add(new BasicNameValuePair("question", question.getText().toString()));
				    		params.add(new BasicNameValuePair("ans1", ans1.getText().toString()));
				    		params.add(new BasicNameValuePair("ans2", ans2.getText().toString()));
				    		params.add(new BasicNameValuePair("ans3",ans3.getText().toString()));
				    		params.add(new BasicNameValuePair("ans4", ans4.getText().toString()));
				    		
				    		params.add(new BasicNameValuePair("answer", Integer.toString(selectedPosition)));
				    		

				    		Log.e("Param",String.valueOf(params));
				    		
				    		String resultServer;
				    		
				    		try {
				    			
				    			 resultServer = httpconnect.getHttpPost(url, params);

				    			Log.d("value in resultServer",String.valueOf(resultServer));

				    			if (resultServer.equals("Success")) {
				    				Toast.makeText(TeacherQuestion.this, "Create Question Success",Toast.LENGTH_SHORT).show();
				    				
				    				Intent intentMain = new Intent(TeacherQuestion.this,TeacherMenu.class);

				    				Log.i("check", String.valueOf(resultServer));

				    				startActivity(intentMain);

				    				
				    			}else {
				    				Toast.makeText(TeacherQuestion.this, "Create Question Unsuccess",Toast.LENGTH_SHORT).show();
								}
				    					
				    		} catch (JsonSyntaxException e) {
				    			Toast.makeText(TeacherQuestion.this,
				    					"Incorrect Username and Password!",
				    					Toast.LENGTH_LONG).show();
				    			e.printStackTrace();
				    		}
				    	 
				    	 
				      Toast.makeText(TeacherQuestion.this, "Success", Toast.LENGTH_SHORT).show();
				     }
				    });
				  builder.setNegativeButton("No",
				    new DialogInterface.OnClickListener() {
				     public void onClick(DialogInterface dialog, int id) {
				      Toast.makeText(TeacherQuestion.this, "Fail", Toast.LENGTH_SHORT).show();
				     }
				    });
				  AlertDialog alert = builder.create();
				  alert.show();
				
	    		}
});
	}
}
