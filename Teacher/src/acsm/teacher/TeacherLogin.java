package acsm.teacher;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import acsm.teacher.TeacherMenu;
import acsm.teacher.R;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TeacherLogin extends Activity {
	List<String> result;
	
	
	//test SharedPreferences
	private static final String MY_PREFS = "my_prefs";

	
		
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher_login);

		// Permission StrictMode
		if (android.os.Build.VERSION.SDK_INT > 12) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		final AlertDialog.Builder ad = new AlertDialog.Builder(this);

		// txtUsername & txtPassword
		final EditText txtUser = (EditText) findViewById(R.id.etxtquestion);
		final EditText txtPass = (EditText) findViewById(R.id.ans1);
		// btnLogin
		final Button btnLogin = (Button) findViewById(R.id.savequstion);
		
		
		
		// Get SharedPreferences
	    final SharedPreferences shared = getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE);

		
    
		
        
		// Perform action on click
		btnLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				String url = "http://acsm.ictte-project.com/loginpsupassport.php";
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("std_id", txtUser.getText().toString()));
				params.add(new BasicNameValuePair("std_pwd", txtPass.getText().toString()));

				

				try {

					
					String resultServer = httpconnect.getHttpPost(url, params);
					
					
					
					Log.d("resultServer",String.valueOf(resultServer));

					Gson gson = new Gson();
					Type listType = new TypeToken<List<String>>() {
					}.getType();
					result = (List<String>) gson.fromJson(resultServer,listType);

					Log.d("resultServer", String.valueOf(result));

					if (resultServer != null) {Toast.makeText(TeacherLogin.this, "Login OK",
								Toast.LENGTH_SHORT).show();

						Intent intentMain = new Intent(TeacherLogin.this,TeacherMenu.class);

						String Username = txtUser.getText().toString();
						
						
						// Save SharedPreferences
				        Editor editor = shared.edit();
				        editor.putString("stringKey", txtUser.getText().toString());
				        editor.commit();

						//view SharedPreferences
				        String stringValue = shared.getString("stringKey","");
				        Log.i("LOG_TAG", "String value: " + stringValue);
				        
						

						intentMain.putExtra("Username", Username);
						
					

						Log.e("send data to",String.valueOf(Username));

						startActivity(intentMain);

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
					Toast.makeText(TeacherLogin.this,
							"Incorrect Username and Password!",
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}

			}
		}

		);


	}

	
	//Detect Back Button
    @Override
    public void onBackPressed() {
 
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(TeacherLogin.this);
 
        alertDialog.setTitle("Confirm Exit...");
        alertDialog.setMessage("Do you want to quit");
        alertDialog.setIcon(R.drawable.ic_launcher);
 
        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        //คลิกใช่ ออกจากโปรแกรม
                        finish();
                        TeacherLogin.super.finishAffinity();
                    }
                });
 
        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,    int which) {
                        //คลิกไม่ cancel dialog
                        dialog.cancel();
                    }
                });
 
        alertDialog.show();
}
}