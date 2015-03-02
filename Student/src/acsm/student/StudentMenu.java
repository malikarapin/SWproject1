package acsm.student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
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

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
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
				final AlertDialog.Builder ad = new AlertDialog.Builder(this);
		Button checkm = (Button)findViewById(R.id.btnsubmitcheck);
		checkm.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),StudentCheck.class);
		
		
		
		String showdatauser = getIntent().getStringExtra("Username");
		i.putExtra("Username", showdatauser);
		
		
/*		String url = "http://acsm.ictte-project.com/spinner.php";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		
		params.add(new BasicNameValuePair("student", showdatauser.toString()));
		
		

		String resultServer;
		
		
		
		try {
			
			resultServer = getHttpPost(url, params);
			
			
			Log.e("value in resultServer",resultServer.toString());


			Gson gson = new Gson();
			Type listType = new TypeToken<List<String>>() {
			}.getType();
			result = (List<String>) gson.fromJson(resultServer,listType);
			
			
			
			if (resultServer != null) {
				Toast.makeText(StudentMenu.this, "Stand By",Toast.LENGTH_SHORT).show();
				
				Intent intentMain = new Intent(StudentMenu.this,StudentCheck.class);
				
				//String Username = txtUser.getText().toString();
				
				intentMain.putExtra("Username", resultServer);
				
				
				
				startActivity(intentMain);
				Log.e("send data to","Username");
				
				
			} else {
				// Dialog
				ad.setTitle("Incorrect Username and Password!");
				ad.setIcon(android.R.drawable.btn_star_big_on);
				ad.setPositiveButton("Close", null);
				ad.show();
				txtUser.setText("");
				txtPass.setText("");
			}
		} catch (JsonSyntaxException e) {
			Toast.makeText(StudentMenu.this,
					"Incorrect Username and Password!",
					Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		*/
		
		
		
		startActivity(i);
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
        alertDialog.setMessage("�س��ͧ���ŧ�����͡�����ҹ���������");
        alertDialog.setIcon(R.drawable.ic_launcher);
        alertDialog.setPositiveButton("��",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        //��ԡ�� �͡�ҡ�����
                        //finish();
                        Intent i = new Intent(getApplicationContext(),StudentLogin.class);
                		startActivity(i);
                        StudentMenu.super.finishAffinity();
                    }
                });
 
        alertDialog.setNegativeButton("���",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,    int which) {
                        //��ԡ��� cancel dialog
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
        alertDialog.setMessage("�س��ͧ����͡�ҡ������������ ?");
        alertDialog.setIcon(R.drawable.ic_launcher);
 
        alertDialog.setPositiveButton("��",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        //��ԡ�� �͡�ҡ�����
                        finish();
                        StudentMenu.super.finishAffinity();
                    }
                });
 
        alertDialog.setNegativeButton("���",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,    int which) {
                        //��ԡ��� cancel dialog
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
