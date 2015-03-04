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
import com.google.gson.JsonSyntaxException;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class StudentMenu extends Activity {

	List<String> result;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_menu);
		
		
		// Permission StrictMode
				if (android.os.Build.VERSION.SDK_INT > 12) {
					StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
							.permitAll().build();
					StrictMode.setThreadPolicy(policy);
				}
				
		Button checkm = (Button)findViewById(R.id.btnsubmitcheck);
		checkm.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {

		
		String showdatauser = getIntent().getStringExtra("Username");

		
		
		String url = "http://acsm.ictte-project.com/spinner.php";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		
		params.add(new BasicNameValuePair("student", showdatauser.toString()));
		
		

		String resultServer;
		
		
		
		try {
			
			resultServer = getHttpPost(url, params);
			
			
			Log.e("value in resultServer",resultServer.toString());

				
			
			if (resultServer != null) {
				Toast.makeText(StudentMenu.this, "Stand By",Toast.LENGTH_SHORT).show();
				
				Intent intentMain = new Intent(StudentMenu.this,StudentCheck.class);
				
				
				intentMain.putExtra("Subject", resultServer);
				
				intentMain.putExtra("Username", showdatauser);
				
				startActivity(intentMain);
				
				
			} 
		} catch (JsonSyntaxException e) {
			Toast.makeText(StudentMenu.this,
					"Incorrect Username and Password!",
					Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		
		
	
		}
});
		
		Button quest = (Button)findViewById(R.id.button2);
		quest.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),StudentQuestion.class);
		startActivity(i);
		}
});
		
		Button logout = (Button)findViewById(R.id.button3);
		logout.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(StudentMenu.this);
		alertDialog.setTitle("Confirm Logout...");
        alertDialog.setMessage("คุณต้องการลงชื่อออกการใช้งานใช่หรือไม่");
        alertDialog.setIcon(R.drawable.ic_launcher);
        alertDialog.setPositiveButton("ใช่",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        //คลิกใช่ ออกจากโปรแกรม
                        //finish();
                        Intent i = new Intent(getApplicationContext(),StudentLogin.class);
                		startActivity(i);
                        StudentMenu.super.finishAffinity();
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
				
		//Intent i = new Intent(getApplicationContext(),StudentLogin.class);
		//startActivity(i);
		}
});
		Button viewcount = (Button)findViewById(R.id.viewcount);
		viewcount.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),StudentViewattend.class);
		startActivity(i);
		}
});	
	}
	//Detect Back Button
    @Override
    public void onBackPressed() {
 
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(StudentMenu.this);
 
        alertDialog.setTitle("Confirm Exit...");
        alertDialog.setMessage("คุณต้องการออกจากโปรแกรมหรือไม่ ?");
        alertDialog.setIcon(R.drawable.ic_launcher);
 
        alertDialog.setPositiveButton("ใช่",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        //คลิกใช่ ออกจากโปรแกรม
                        finish();
                        StudentMenu.super.finishAffinity();
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
