package acsm.teacher;

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
import acsm.teacher.TeacherMenu;
import acsm.teacher.R;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TeacherLogin extends Activity {
	List<String> result;

	
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
		final EditText txtUser = (EditText) findViewById(R.id.usertlog);
		final EditText txtPass = (EditText) findViewById(R.id.passtlog);
		// btnLogin
		final Button btnLogin = (Button) findViewById(R.id.savequstion);
		
		
		
		

		
		
		// Perform action on click
		btnLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				String url = "http://acsm.ictte-project.com/loginpsupassport.php";
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("std_id", txtUser.getText().toString()));
				params.add(new BasicNameValuePair("std_pwd", txtPass.getText().toString()));

				String resultServer;

				try {

					resultServer = getHttpPost(url, params);
					
					Log.d("resultServer", resultServer.toString());

					Gson gson = new Gson();
					Type listType = new TypeToken<List<String>>() {
					}.getType();
					result = (List<String>) gson.fromJson(resultServer,listType);

					Log.d("resultServer", String.valueOf(result));

					if (resultServer != null) {
						Toast.makeText(TeacherLogin.this, "Login OK",
								Toast.LENGTH_SHORT).show();

						Intent intentMain = new Intent(TeacherLogin.this,TeacherMenu.class);

						String Username = txtUser.getText().toString();
						
						
						
						

						intentMain.putExtra("Username", Username);
						
					

						Log.e("send data to", Username.toString());

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

		/*
		 * TextView t3 = (TextView) findViewById(R.id.linkFG); t3.setText(Html
		 * .fromHtml(
		 * "<a href=\"https://passport.psu.ac.th/index.php?content=forgetpass\">Forget Password</a> "
		 * )); t3.setMovementMethod(LinkMovementMethod.getInstance());
		 */
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