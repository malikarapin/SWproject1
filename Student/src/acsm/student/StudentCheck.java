package acsm.student;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StudentCheck extends Activity implements LocationListener{
	
	private TextView text;
    private String strlat,strlog;
    private LocationManager location;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_check);
		
		text = (TextView)findViewById(R.id.textView3);
		location =(LocationManager) getSystemService(LOCATION_SERVICE);

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		location.removeUpdates(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		location.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1500, 1, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.student_check, menu);
		return true;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		strlat = Double.toString(location.getLatitude());
		strlog = Double.toString(location.getLongitude());
		
		text.append("lat = " +strlat+"\n");
		text.append("log = " +strlog+"\n");
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
