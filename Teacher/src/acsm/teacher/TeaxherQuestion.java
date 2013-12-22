package acsm.teacher;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TeaxherQuestion extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teaxher_question);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.teaxher_question, menu);
		return true;
	}

}
