package acsm.teacher;

import acsm.teacher.TeacherLogin;
import acsm.teacher.Teacherspl;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

public class Teacherspl extends Activity {
	Handler handler;
	Runnable runnable;
	Long delay_time;
	Long time = 3500L;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher_spl);

		handler = new Handler();

		runnable = new Runnable() {
			public void run() {
				Intent intent = new Intent(Teacherspl.this, TeacherLogin.class);
				startActivity(intent);
				finish();
			}
		};
	}

	public void onResume() {
		super.onResume();
		delay_time = time;
		handler.postDelayed(runnable, delay_time);
		time = System.currentTimeMillis();
	}

	public void onPause() {
		super.onPause();
		handler.removeCallbacks(runnable);
		time = delay_time - (System.currentTimeMillis() - time);
	}
}
