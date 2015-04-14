package acsm.teacher;




import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.google.gson.JsonSyntaxException;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class TeacherMenu extends Activity {
	String showdatauser;
	
	String strUsername;
	
	private static final String MY_PREFS = "my_prefs";
	
	String resultServer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher_menu);
		
		//showdatauser = getIntent().getStringExtra("Username");
		
		SharedPreferences shared = getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE);
		
		strUsername = shared.getString("stringKey", "").toString();
		  
		Log.i("SharedPreferences", String.valueOf(strUsername));
		
		// Permission StrictMode
		if (android.os.Build.VERSION.SDK_INT > 12) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		
/*		Button quize = (Button)findViewById(R.id.btncancelchpw);
		quize.setOnClickListener(new OnClickListener() {

			@Override
		public void onClick(View v) {
				
				
				Log.e("value Intren",String.valueOf(showdatauser));

				
				
				String url = "http://acsm.ictte-project.com/spinnerTeacher.php";
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				
				
				params.add(new BasicNameValuePair("Teacher_Name_Thai", String.valueOf(strUsername)));
				
				Log.d("params", String.valueOf(params));

				
				
				
				
				try {
					
					resultServer = httpconnect.getHttpPost(url, params);
					
					
					
					
					Log.e("value in resultServer", String.valueOf(resultServer));

						
					
					if (resultServer != null) {
						Toast.makeText(TeacherMenu.this, "Stand By",Toast.LENGTH_SHORT).show();
						
						Intent toTeacherQuestion = new Intent(TeacherMenu.this,TeacherQuestion.class);
						
						
						toTeacherQuestion.putExtra("Subject", resultServer);
						
						//toTeacherQuestion.putExtra("Username", showdatauser);
						
						startActivity(toTeacherQuestion);
						
						
					}else {
						Log.d("test bug", "Not pass");
						Toast.makeText(TeacherMenu.this, "Error",Toast.LENGTH_SHORT).show();
						
					} 
					
				} catch (JsonSyntaxException e) {
					Toast.makeText(TeacherMenu.this,
							"Incorrect Username and Password!",
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
				}
});*/
		
		
			
		
		
		Button check = (Button)findViewById(R.id.savequstion);
		check.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		

			
			Log.e("value Intren",String.valueOf(showdatauser));

		
		
		String url = "http://acsm.ictte-project.com/spinnerTeacher.php";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		
		params.add(new BasicNameValuePair("Teacher_Name_Thai", String.valueOf(strUsername)));
		
		Log.d("params", String.valueOf(params));

		

		try {
			
			resultServer = httpconnect.getHttpPost(url, params);
			
			
			
			
			Log.e("value in resultServer", String.valueOf(resultServer));

				
			
			if (resultServer != null) {
				Toast.makeText(TeacherMenu.this, "Stand By",Toast.LENGTH_SHORT).show();
				
				Intent intentMain = new Intent(TeacherMenu.this,TeacherCheck.class);
				
				
				intentMain.putExtra("Subject", resultServer);
				
				//intentMain.putExtra("Username", showdatauser);
				
				startActivity(intentMain);
				
				
			}else {
				Log.d("test bug", "Not pass");
				Toast.makeText(TeacherMenu.this, "Error",Toast.LENGTH_SHORT).show();
				
			} 
			
		} catch (JsonSyntaxException e) {
			Toast.makeText(TeacherMenu.this,
					"Incorrect Username and Password!",
					Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		}
	
			
			
});
		/*
		//Button to ViewCheck Teacher
		Button viewcheck1 = (Button)findViewById(R.id.btnviewcheck);
		viewcheck1.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {

				
				showdatauser = getIntent().getStringExtra("Username");
				
				
				
				Log.e("value Intren",String.valueOf(showdatauser));

			
			
			String url = "http://acsm.ictte-project.com/spinnerTeacher.php";
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			
			params.add(new BasicNameValuePair("Teacher_Name_Thai", String.valueOf(strUsername)));
			
			Log.d("params", String.valueOf(params));

			
			
			
			
			try {
				
				resultServer = httpconnect.getHttpPost(url, params);
				
				
				
				Log.e("value in resultServer", String.valueOf(resultServer));

					
				
				if (resultServer != null) {
					Toast.makeText(TeacherMenu.this, "Stand By",Toast.LENGTH_SHORT).show();
					
					Intent ii = new Intent(TeacherMenu.this,TeacherViewCheck.class);
					
					
					ii.putExtra("Subject", resultServer);
					
					//ii.putExtra("Username", showdatauser);
					
					startActivity(ii);
					
					
				}else {
					Log.d("test bug", "Not pass");
					Toast.makeText(TeacherMenu.this, "Error",Toast.LENGTH_SHORT).show();
					
				} 
				
			} catch (JsonSyntaxException e) {
				Toast.makeText(TeacherMenu.this,
						"Incorrect Username and Password!",
						Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
			
		}
});
		*/
		
		
		Button logout = (Button)findViewById(R.id.logout);
		logout.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(TeacherMenu.this);
		alertDialog.setTitle("Confirm Logout...");
        alertDialog.setMessage("Do you want to Logout");
        alertDialog.setIcon(R.drawable.ic_launcher);
        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        //คลิกใช่ ออกจากโปรแกรม
                        //finish();
                        Intent i = new Intent(getApplicationContext(),TeacherLogin.class);
                		startActivity(i);
                        TeacherMenu.super.finishAffinity();
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
});
		
		
		
	}
	//Detect Back Button
    @Override
    public void onBackPressed() {
 
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(TeacherMenu.this);
 
        alertDialog.setTitle("Confirm Exit...");
        alertDialog.setMessage("Do you want to quit");
        alertDialog.setIcon(R.drawable.ic_launcher);
 
        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        //คลิกใช่ ออกจากโปรแกรม
                        finish();
                        TeacherMenu.super.finishAffinity();
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
