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

		final String showdatauser = getIntent().getStringExtra("Username");
		
		// Button Function Check
		Button checkm = (Button) findViewById(R.id.btnsubmitcheck);
		checkm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				

				String url = "http://acsm.ictte-project.com/spinner.php";
				List<NameValuePair> params = new ArrayList<NameValuePair>();

				params.add(new BasicNameValuePair("student", showdatauser
						.toString()));

				// String resultServer;

				try {

					String resultServer = httpconnect.getHttpPost(url, params);

					// resultServer = getHttpPost(url, params);

					Log.e("value in resultServer", String.valueOf(params));

					if (resultServer != null) {
						Toast.makeText(StudentMenu.this, "Stand By",
								Toast.LENGTH_SHORT).show();

						Intent intentMain = new Intent(StudentMenu.this,
								StudentCheck.class);

						intentMain.putExtra("Subject", resultServer);

						intentMain.putExtra("Username", showdatauser);

						startActivity(intentMain);

						Log.e("inrent to check", String.valueOf(resultServer));
						Log.e("submit", String.valueOf(showdatauser));

					}
				} catch (JsonSyntaxException e) {
					Toast.makeText(StudentMenu.this,
							"Incorrect Username and Password!",
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}

			}
		});
		
		// Function Question
		Button quest = (Button) findViewById(R.id.button2);
		quest.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String url = "http://acsm.ictte-project.com/spinner.php";
				List<NameValuePair> params = new ArrayList<NameValuePair>();

				params.add(new BasicNameValuePair("student", showdatauser
						.toString()));

				// String resultServer;

				try {

					String resultServer = httpconnect.getHttpPost(url, params);

					// resultServer = getHttpPost(url, params);

					Log.e("value in resultServer", String.valueOf(params));

					if (resultServer != null) {
						Toast.makeText(StudentMenu.this, "Stand By",
								Toast.LENGTH_SHORT).show();

						Intent intentMain = new Intent(StudentMenu.this,
								StudentQuestion.class);

						intentMain.putExtra("Subject", resultServer);

						intentMain.putExtra("Username", showdatauser);

						startActivity(intentMain);

						Log.e("inrent to check", String.valueOf(resultServer));
						Log.e("submit", String.valueOf(showdatauser));

					}
				} catch (JsonSyntaxException e) {
					Toast.makeText(StudentMenu.this,
							"Incorrect Username and Password!",
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		});

		// Button Logout
		Button logout = (Button) findViewById(R.id.button3);
		logout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						StudentMenu.this);
				alertDialog.setTitle("Confirm Logout...");
				alertDialog.setMessage("Do you want to Logout");
				alertDialog.setIcon(R.drawable.ic_launcher);
				alertDialog.setPositiveButton("YES",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// ��ԡ�� �͡�ҡ�����
								// finish();
								Intent i = new Intent(getApplicationContext(),
										StudentLogin.class);
								startActivity(i);
								StudentMenu.super.finishAffinity();
							}
						});

				alertDialog.setNegativeButton("NO",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// ��ԡ��� cancel dialog
								dialog.cancel();
							}
						});

				alertDialog.show();
			}
		});

		// Button Function View
		Button viewcount = (Button) findViewById(R.id.viewcount);
		viewcount.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String showdatauser = getIntent().getStringExtra("Username");

				String url = "http://acsm.ictte-project.com/spinner.php";
				List<NameValuePair> params = new ArrayList<NameValuePair>();

				params.add(new BasicNameValuePair("student", showdatauser
						.toString()));

				try {

					String resultServer = httpconnect.getHttpPost(url, params);

					Log.e("value in resultServer", String.valueOf(params));

					if (resultServer != null) {
						Toast.makeText(StudentMenu.this, "Stand By",
								Toast.LENGTH_SHORT).show();

						Intent intentMain2 = new Intent(StudentMenu.this,
								StudentViewattend.class);

						intentMain2.putExtra("Subject", resultServer);

						intentMain2.putExtra("Username", showdatauser);

						startActivity(intentMain2);

						Log.e("inrent to check", String.valueOf(resultServer));
						Log.e("submit", String.valueOf(showdatauser));

					}
				} catch (JsonSyntaxException e) {
					Toast.makeText(StudentMenu.this,
							"Incorrect Username and Password!",
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}

			}

		});
	}

	// Detect Back Button
	@Override
	public void onBackPressed() {

		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				StudentMenu.this);

		alertDialog.setTitle("Confirm Exit...");
		alertDialog.setMessage("Do you want to quit");
		alertDialog.setIcon(R.drawable.ic_launcher);

		alertDialog.setPositiveButton("YES",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// ��ԡ�� �͡�ҡ�����
						finish();
						StudentMenu.super.finishAffinity();
					}
				});

		alertDialog.setNegativeButton("NO",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// ��ԡ��� cancel dialog
						dialog.cancel();
					}
				});

		alertDialog.show();
	}

}
