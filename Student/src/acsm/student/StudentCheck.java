package acsm.student;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class StudentCheck extends Activity implements LocationListener {

	TextView text1;
	TextView text2;
	LocationManager locationManager;
	String provider;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_check);

		text1 = (TextView) findViewById(R.id.lat);
		text2 = (TextView) findViewById(R.id.lon);
		Button check = (Button) findViewById(R.id.submit);

		// Getting LocationManager object
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// Creating an empty criteria object
		Criteria criteria = new Criteria();

		// Getting the name of the provider that meets the criteria
		provider = locationManager.getBestProvider(criteria, false);

		if (provider != null && !provider.equals("")) {

			// Get the location from the given provider
			Location location = locationManager.getLastKnownLocation(provider);

			locationManager.requestLocationUpdates(provider, 1000, 1, this);

			if (location != null)
				onLocationChanged(location);
			else
				Toast.makeText(getBaseContext(), "Location can't be retrieved",
						Toast.LENGTH_SHORT).show();

		} else {
			Toast.makeText(getBaseContext(), "No Provider Found",
					Toast.LENGTH_SHORT).show();
		}

		check.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		List<String> list = new ArrayList<String>();

		list.add("ProjectI");

		list.add("SW VERIFICATION &VALIDATION");

		list.add("WORKSHOP IN IT III(WEB JAVA)");

		list.add("HUMAN COMPUTER INTERACTION");

		list.add("SOFTWARE PROJECT MANAGEMENT");

		list.add("SOFTWARE ARCHITECTURE");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);

		Spinner spinner = (Spinner) this.findViewById(R.id.spinner1);

		spinner.setAdapter(dataAdapter);

	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

		text2.setText("Longitude: "
				+ String.format("%.9f", location.getLongitude()));

		text1.setText("Latitude: "
				+ String.format("%.9f", location.getLatitude()));

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

}
