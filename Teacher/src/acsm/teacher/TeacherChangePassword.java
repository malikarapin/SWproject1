package acsm.teacher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TeacherChangePassword extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher_changepassword);
		
		
		Button accept = (Button)findViewById(R.id.logout);
		accept.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),TeacherMenu.class);
		startActivity(i);
		}
});
		
		Button cancel = (Button)findViewById(R.id.btncancelchpw);
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),TeacherChangePassword.class);
		startActivity(i);
		}
});
		
		
		
	}


}
