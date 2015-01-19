package acsm.student;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;

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
import org.json.JSONException;
import org.json.JSONObject;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.util.Log;
import android.view.Menu;




public class StudentLogin extends Activity {

	

	String sUsername, sPassword;
	public static int agID;
	Toast toast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_login);
		
		final AlertDialog.Builder ad = new AlertDialog.Builder(this);

		final EditText etUsername = (EditText) findViewById(R.id.userlog);
		final EditText etPassword = (EditText) findViewById(R.id.passlog);

		final Button btnlogin = (Button) findViewById(R.id.submit);
		btnlogin.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
				String url = "192.168.208.237/PHPContodatabase/checkLogin.php";
        		List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("strUser", etUsername.getText().toString()));
                params.add(new BasicNameValuePair("strPass", etPassword.getText().toString()));
                
                /** Get result from Server (Return the JSON Code)
                 * StatusID = ? [0=Failed,1=Complete]
                 * MemberID = ? [Eg : 1]
                 * Error	= ?	[On case error return custom error message]
                 * 
                 * Eg Login Failed = {"StatusID":"0","MemberID":"0","Error":"Incorrect Username and Password"}
                 * Eg Login Complete = {"StatusID":"1","MemberID":"2","Error":""}
                 */
                
            	String resultServer  = getHttpPost(url,params);
                
                /*** Default Value ***/
            	String strStatusID = "0";
            	String strMemberID = "0";
            	String strError = "Unknow Status!";
            	
            	JSONObject c;
				try {
					c = new JSONObject(resultServer);
	            	strStatusID = c.getString("StatusID");
	            	strMemberID = c.getString("MemberID");
	            	strError = c.getString("Error");
	            	
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				// Prepare Login
				if(strStatusID.equals("0"))
				{
					// Dialog
					ad.setTitle("Error! ");
					ad.setIcon(android.R.drawable.btn_star_big_on); 
					ad.setPositiveButton("Close", null);
					ad.setMessage(strError);
					ad.show();
					etUsername.setText("");
					etPassword.setText("");
				}
				else
				{
					Toast.makeText(StudentLogin.this, "Login OK", Toast.LENGTH_SHORT).show();
					Intent newActivity = new Intent(StudentLogin.this,StudentMenu.class);
					newActivity.putExtra("MemberID", strMemberID);
					startActivity(newActivity);
				}
           	            
            }
        });
        
	}
	public String getHttpPost(String url,List<NameValuePair> params) {
		StringBuilder str = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = client.execute(httpPost);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) { // Status OK
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
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

	public void beginLogin() {
		// TODO Auto-generated method stub
		Intent tomenu = new Intent(getApplicationContext(), StudentMenu.class);

		startActivity(tomenu);
	}
}
