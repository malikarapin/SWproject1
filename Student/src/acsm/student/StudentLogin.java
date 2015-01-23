package acsm.student;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;




public class StudentLogin extends Activity {

	

	String sUsername, sPassword;
	public static int agID;
	Toast toast;
	ProgressDialog dialog = null;
	 HttpResponse response;
	    HttpClient httpclient;
	    HttpPost httppost;
	    StringBuffer buffer;
	    List<NameValuePair> nameValuePairs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_login);
		
		//final AlertDialog.Builder ad = new AlertDialog.Builder(this);

		//final EditText etUsername = (EditText) findViewById(R.id.userlog);
		//final EditText etPassword = (EditText) findViewById(R.id.passlog);
		final Button submit = (Button) findViewById(R.id.submit);
		
		submit.setOnClickListener(new OnClickListener() {
			
			 @Override
	            public void onClick(View v) {
	               // dialog = ProgressDialog.show(StudentLogin.this, "", 
	                 //       "Validating user...", true);
				
	                 new Thread(new Runnable() {
	                        public void run() {
	                            login();                          
	                        }
	                      }).start();               
	            }
        });
        
	}
	
	void login(){
        try{            
        	final EditText etUsername = (EditText) findViewById(R.id.userlog);
    		final EditText etPassword = (EditText) findViewById(R.id.passlog);
            httpclient=new DefaultHttpClient();
            httppost= new HttpPost("http://192.168.208.253/PHPContodatabase/checkLogin.php"); // make sure the url is correct.
            //Log.d("Log d", "Test Log");
			// Log.i("Log d", "Test Log");
			// Log.e("Log d", "Test Log");
            //add your data
            nameValuePairs = new ArrayList<NameValuePair>(2);
            // Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar, 
            nameValuePairs.add(new BasicNameValuePair("std_id",etUsername.getText().toString().trim()));  // $Edittext_value = $_POST['Edittext_value'];
            nameValuePairs.add(new BasicNameValuePair("std_pwd",etPassword.getText().toString().trim())); 
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            //Execute HTTP Post Request
            response=httpclient.execute(httppost);
            // edited by James from coderzheaven.. from here....
            ResponseHandler<String> responseHandler = new BasicResponseHandler();

            final String response = httpclient.execute(httppost, responseHandler);
         System.out.println("Response : " + response); 
   
            runOnUiThread(new Runnable() {
            	 public void run() {
            		 
            		 if(response.equalsIgnoreCase("User Found")){
            			// Intent i = new Intent(getApplicationContext(),StudentMenu.class);
                 		//startActivity(i);
                 		Toast.makeText(StudentLogin.this,"NO Login Success", Toast.LENGTH_SHORT).show();
            			 
            		 }else{
                        	//Toast.makeText(StudentLogin.this,"NO Login Success", Toast.LENGTH_SHORT).show();
                        	Intent i = new Intent(getApplicationContext(),StudentMenu.class);
                     		startActivity(i);
                        }
            		 
            		 
            	 }
            });

             
        }catch(Exception e){
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
    }
	
	  
/*
	 @Override
	    public void onBackPressed() {
	 
	        AlertDialog.Builder alertDialog = new AlertDialog.Builder(StudentLogin.this);
	 
	        alertDialog.setTitle("Confirm Exit...");
	        alertDialog.setMessage("คุณต้องการออกจากโปรแกรมหรือไม่ ?");
	        alertDialog.setIcon(R.drawable.ic_launcher);
	 
	        alertDialog.setPositiveButton("ใช่",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog,int which) {
	                        //คลิกใช่ ออกจากโปรแกรม
	                        finish();
	                        StudentLogin.super.finishAffinity();
	                    }
	                });
	 
	        alertDialog.setNegativeButton("ไม่",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog,    int which) {
	                        //คลิกไม่ cancel dialog
	                        dialog.cancel();
	                    }
	                });
	 
	        alertDialog.show();
	}*/
	
}
