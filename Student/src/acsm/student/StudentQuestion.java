package acsm.student;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;


public class StudentQuestion extends Activity {



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_question);
		
		
		
		Button save = (Button)findViewById(R.id.btnsubmitcheck);
		save.setOnClickListener(new OnClickListener() {
			
		@Override
		public void onClick(View v) {
			Intent i = new Intent(getApplicationContext(),StudentMenu.class);
			startActivity(i);
			}
	});
	}
}
