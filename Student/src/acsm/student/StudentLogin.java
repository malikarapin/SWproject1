package acsm.student;


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
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StudentLogin extends Activity {
	
	
	
	
    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_login);

        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 12) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        
        final AlertDialog.Builder ad = new AlertDialog.Builder(this);
        
        // txtUsername & txtPassword
        final EditText txtUser = (EditText)findViewById(R.id.userlog); 
        final EditText txtPass = (EditText)findViewById(R.id.passlog); 
        
        // btnLogin
        final Button btnLogin = (Button) findViewById(R.id.submit);
        // Perform action on click
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	
            	String url = "http://acsm.ictte-project.com/checkLoginStudent.php";
        		List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("std_id", txtUser.getText().toString()));
                params.add(new BasicNameValuePair("std_pwd", txtPass.getText().toString()));
                
                Log.e("Log error", "error params");
                
                /** Get result from Server (Return the JSON Code)
                 * StatusID = ? [0=Failed,1=Complete]
                 * MemberID = ? [Eg : 1]
                 * Error	= ?	[On case error return custom error message]
                 * 
                 * Eg Login Failed = {"StatusID":"0","MemberID":"0","Error":"Incorrect Username and Password"}
                 * Eg Login Complete = {"StatusID":"1","MemberID":"2","Error":""}
                 */
                
            	String resultServer  = getHttpPost(url,params);
                /***
                /*** Default Value ***/
            	String strStatusID = "0";
            	String strMemberID = "0";
            	String strError = "Unknow Status!";
            	
            	
            	
            	
            	JSONObject c;
				try {
					c = new JSONObject(resultServer);
					 strStatusID = c.getString("std_id");
					 strMemberID = c.getString("std_pwd");
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
					txtUser.setText("");
					txtPass.setText("");
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
        
        TextView t3 = (TextView) findViewById(R.id.linkFG);
        t3.setText(
            Html.fromHtml(
                "<a href=\"http://web52.phuket.psu.ac.th/registra\">Forget Password</a> "));
        t3.setMovementMethod(LinkMovementMethod.getInstance());
        
    }
    

	public String getHttpPost(String url,List<NameValuePair> params) {
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
	
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.student_login, menu);
        return true;
    }
    
}