package acsm.student;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StudentMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_menu);
		
		Button checkm = (Button)findViewById(R.id.button1);
		checkm.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),StudentCheck.class);
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
		Intent i = new Intent(getApplicationContext(),StudentLogin.class);
		startActivity(i);
		}
});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.student_menu, menu);
		return true;
	}

}
