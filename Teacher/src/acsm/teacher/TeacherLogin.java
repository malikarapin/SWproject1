package acsm.teacher;

import acsm.teacher.R;
import acsm.teacher.TeacherCheck;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TeacherLogin extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher_login);
		
		Button login = (Button)findViewById(R.id.button1);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),TeacherMenu.class);
		startActivity(i);
		}
});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.teacher_login, menu);
		return true;
	}

}
