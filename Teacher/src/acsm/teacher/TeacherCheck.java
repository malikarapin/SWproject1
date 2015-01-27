package acsm.teacher;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class TeacherCheck extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher__check);

		Button login = (Button) findViewById(R.id.add);
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						TeacherViewReport.class);
				startActivity(i);
			}
		});

		List<String> list = new ArrayList<String>();

		list.add("ProjectI");

		list.add("SOFTWARE ARCHITECTURE");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);

		Spinner spinner = (Spinner) this.findViewById(R.id.spinner1);

		spinner.setAdapter(dataAdapter);

		final EditText et1 = (EditText) findViewById(R.id.EditText01);
		final EditText et2 = (EditText) findViewById(R.id.EditText02);
		final EditText et3 = (EditText) findViewById(R.id.EditText03);
		final EditText et4 = (EditText) findViewById(R.id.EditText04);

		et1.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (et1.getText().toString().length() == 1) // size as per your
															// requirement
				{
					et2.requestFocus();

				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
		
		et2.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (et2.getText().toString().length() == 1) // size as per your
															// requirement
				{
					et3.requestFocus();

				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
		et3.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (et3.getText().toString().length() == 1) // size as per your
															// requirement
				{
					et4.requestFocus();

				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});

	}

}
