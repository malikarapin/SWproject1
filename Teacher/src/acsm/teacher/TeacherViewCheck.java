package acsm.teacher;


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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class TeacherViewCheck extends Activity {
	String item;
	String selected, spinner_item;
	
	String username;
	
	ListView listviewteac;
	
	
	SimpleAdapter sAdap;
	
	AlertDialog.Builder viewDetail;
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher_view_check);
		
		
		
		final Spinner spinview = (Spinner)findViewById(R.id.spnviewteac);
		
		String subjectdata = getIntent().getStringExtra("Subject");
		
		 username = getIntent().getStringExtra("Username");
		
		  listviewteac = (ListView)findViewById(R.id.listviewteac);
		  

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
        			
        			/*Toast.makeText(getApplicationContext(), item,Toast.LENGTH_LONG).show();
        			Log.e("Item",String.valueOf(item));*/
        			
        		
        			
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
		
        //Button Add Student Id for Check
        Button btnviewcheck = (Button)findViewById(R.id.btnviewcheck);
        btnviewcheck.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
				
				 String url = "http://acsm.ictte-project.com/view-check-teacher.php";
				    List<NameValuePair> params = new ArrayList<NameValuePair>();
				    
					params.add(new BasicNameValuePair("subject", item.toString()));
					params.add(new BasicNameValuePair("teacherid", username.toString()));
					

				    	try {
						
				    		
				    		JSONArray data = new JSONArray(httpconnect.getHttpPost(url, params));
						
						final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
						HashMap<String, String> map;
						
						for(int i = 0; i < data.length(); i++){
				            JSONObject c = data.getJSONObject(i);
				            
							map = new HashMap<String, String>();
							map.put("Date_Time", c.getString("Date_Time"));
							map.put("Pass_Code", c.getString("Pass_Code"));
							
							map.put("Teacher_Id", c.getString("Teacher_Id"));
							map.put("Subject_Name_Eng", c.getString("Subject_Name_Eng"));
							MyArrList.add(map);	
							
						}
						

						SimpleAdapter sAdap;
				        sAdap = new SimpleAdapter(TeacherViewCheck.this, MyArrList, R.layout.view_check_colunm,
				                new String[] {"Date_Time", "Pass_Code"}, new int[] {R.id.colDate, R.id.colPasscode});      
				        listviewteac.setAdapter(sAdap);
				        
				        
				        //delete row in database
				        
				        //viewDetail = new AlertDialog.Builder(this);
						// OnClick Item
				        listviewteac.setOnItemClickListener(new OnItemClickListener() {
							public void onItemClick(AdapterView<?> myAdapter, View myView,
									int position, long mylng) {
				
								final String sTime = MyArrList.get(position).get("Date_Time").toString();
								final String sPassCode = MyArrList.get(position).get("Pass_Code").toString();
								final String sTeacherId = MyArrList.get(position).get("Teacher_Id").toString();
								final String sSubject = MyArrList.get(position).get("Subject_Name_Eng").toString();
							
								
				                //String sMemberID = ((TextView) myView.findViewById(R.id.ColMemberID)).getText().toString();
				                // String sName = ((TextView) myView.findViewById(R.id.ColName)).getText().toString();
				                // String sTel = ((TextView) myView.findViewById(R.id.ColTel)).getText().toString();
				                
								//viewDetail.setIcon(android.R.drawable.btn_star_big_on);
								
								AlertDialog.Builder viewDetail = new AlertDialog.Builder(TeacherViewCheck.this);
								
								viewDetail.setTitle("CheckDetail");
								viewDetail.setMessage("Date Time : " + sTime + "\n"
										+ "Passcode : " + sPassCode + "\n"+ "Teacher_Id : " + sTeacherId + "\n"+ "Subject : " + sSubject );
								viewDetail.setPositiveButton("OK",
										new DialogInterface.OnClickListener() {
											public void onClick(DialogInterface dialog,
													int which) {
												// TODO Auto-generated method stub
												dialog.dismiss();
											}
										});
								viewDetail.setNegativeButton("Delete",
						                new DialogInterface.OnClickListener() {
						                    public void onClick(DialogInterface dialog,    int which) {
						                      	
						                    	 String url = "http://acsm.ictte-project.com/select-delete-row.php";
						     			        List<NameValuePair> params = new ArrayList<NameValuePair>();
						     			        
						     					params.add(new BasicNameValuePair("TeacherId", sTeacherId.toString()));
						     					params.add(new BasicNameValuePair("Subject", sSubject.toString()));
						     					params.add(new BasicNameValuePair("PassCode", sPassCode.toString()));
						     					params.add(new BasicNameValuePair("DateTime", sTime.toString()));
						     			        
						     			        
						     			        	try {
						     						
						     			        		String resultServer = httpconnect.getHttpPost(url, params);
						     			        		
						     			        		if (resultServer != "0") {
						     								
						     			        			Toast.makeText(TeacherViewCheck.this, "Delete Successful",Toast.LENGTH_SHORT).show();
						     			        			 dialog.cancel();
						     							}  else {
						     								Toast.makeText(TeacherViewCheck.this, "Delete Unsuccessful",Toast.LENGTH_SHORT).show();
						     			        			 dialog.cancel();
						     							}
						     			    			
						     					
						     		
						     		
						     		} catch (Exception e) {
						     			// TODO Auto-generated catch block
						     			e.printStackTrace();
						     		}
						                    	
						                    	
						                    	
						                    	
						                    	/*  //คลิกไม่ cancel dialog
						                        dialog.cancel();*/
						                    }
						                });
								viewDetail.show();

							}
						});
				        
				        
				        
				        
				  
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	    		}
	    });

}
}
