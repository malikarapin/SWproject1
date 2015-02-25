package acsm.student;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Spinner;

public class StudentCheck extends Activity {

	Double test;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_check);

	}

	public void spiner() {

		final Spinner spin = (Spinner)findViewById(R.id.subject);
		
		

	}
}