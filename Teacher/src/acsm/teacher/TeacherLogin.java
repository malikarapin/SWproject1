package acsm.teacher;


import acsm.teacher.R;
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

public class TeacherLogin extends Activity {

	EditText etUsername, etPassword;
	String sUsername, sPassword;
	public static int agID;
	Toast toast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher_login);

		etUsername = (EditText) findViewById(R.id.usertlog);
		etPassword = (EditText) findViewById(R.id.passtlog);

		Button login = (Button) findViewById(R.id.add);
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkUserPassword();

			}
		});

	}

	// Detect Back Button
	@Override
	public void onBackPressed() {

		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				TeacherLogin.this);

		alertDialog.setTitle("Confirm Exit...");
		alertDialog.setMessage("คุณต้องการออกจากโปรแกรมหรือไม่ ?");
		alertDialog.setIcon(R.drawable.ic_launcher);

		alertDialog.setPositiveButton("ใช่",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// คลิกใช่ ออกจากโปรแกรม
						finish();
						TeacherLogin.super.finishAffinity();
					}
				});

		alertDialog.setNegativeButton("ไม่",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// คลิกไม่ cancel dialog
						dialog.cancel();
					}
				});

		alertDialog.show();
	}

	public void checkUserPassword() {

		// TODO Auto-generated method stub
		sUsername = etUsername.getText().toString();
		sPassword = etPassword.getText().toString();
		toast = Toast.makeText(getApplicationContext(),
				"ชื่อผู้ใช้งานหรือรหัสผ่านไม่ถูกต้อง", Toast.LENGTH_SHORT);

		if (sUsername.equals("5430213054") && sPassword.equals("5430213054")) {
			this.agID = 10;
			beginLogin();
		} else if (sUsername.equals("5430213019")
				&& sPassword.equals("5430213019")) {
			this.agID = 20;
			beginLogin();
		} else if (sUsername.equals("5430213025")
				&& sPassword.equals("5430213025")) {
			this.agID = 30;
			beginLogin();
		}else if (sUsername.equals("")
				&& sPassword.equals("")) {
			this.agID = 30;
			beginLogin();
		}
		else
			toast.show();

	}

	public void beginLogin() {
		// TODO Auto-generated method stub
		Intent tomenu = new Intent(getApplicationContext(), TeacherMenu.class);

		startActivity(tomenu);
		finish();
	}
}