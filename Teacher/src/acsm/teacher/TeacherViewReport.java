package acsm.teacher;

import java.util.ArrayList;
import java.util.List;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class TeacherViewReport extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher_view_report);
		
		
		Button acpet = (Button)findViewById(R.id.button2);
		acpet.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),TeacherMenu.class);
		startActivity(i);
		}
});
		
		Button cancle = (Button)findViewById(R.id.button3);
		cancle.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),TeacherMenu.class);
		startActivity(i);
		}
});
		

		
	}



}
