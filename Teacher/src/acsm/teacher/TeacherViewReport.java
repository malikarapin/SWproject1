package acsm.teacher;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TeacherViewReport extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher_view_report);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.teacher_view_report, menu);
		return true;
	}

}
