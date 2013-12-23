package acsm.student;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class StudentCheck extends Activity {
	
	private TextView text1,text2;
    private String strlat,strlog;
    private LocationManager lm;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_check);
		
		text1 = (TextView)findViewById(R.id.textView3);
		text2 = (TextView)findViewById(R.id.textView4);
		
        
        lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
    }
    
    public void onResume() {
        super.onResume();
        setup();
    }
    
    public void onStart() {
        super.onStart();
        boolean gpsEnabled, networkEnabled;
        gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if(!gpsEnabled) {
            networkEnabled = 
                    lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if(!networkEnabled) {
                Intent intent = 
                        new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        }
    }
    
    public void onStop() {
        super.onStop();
        lm.removeUpdates(listener);
    }
    
    public void setup() {
        lm.removeUpdates(listener);
        String strlat = "Unknown";
        String strlog = "Unknown";
        
        Location networkLocation = requestUpdatesFromProvider(
                LocationManager.NETWORK_PROVIDER, "Network not supported");
        if(networkLocation != null) {
        	strlat = String.format("%.7f", networkLocation.getLatitude());
        	strlog = String.format("%.7f", networkLocation.getLongitude());
        }
        
        Location gpsLocation = requestUpdatesFromProvider(
                LocationManager.GPS_PROVIDER, "GPS not supported");
        
        if(gpsLocation != null) {
        	strlat = String.format("%.7f", gpsLocation.getLatitude());
        	strlog = String.format("%.7f", gpsLocation.getLongitude());
        }
        
        text1.setText(strlat);
        text2.setText(strlog);
    }
    
    public Location requestUpdatesFromProvider(final String provider
            , String error) {
        Location location = null;
        if (lm.isProviderEnabled(provider)) {
            lm.requestLocationUpdates(provider, 1000, 10, listener);
            location = lm.getLastKnownLocation(provider);
        } else {
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
        return location;
    }
    
    public final LocationListener listener = new LocationListener() {
        public void onLocationChanged(Location location) {
            text1.setText(String.format("%.7f"
                    , location.getLatitude()));
            text2.setText(String.format("%.7f"
                    ,location.getLongitude()));
        }

        public void onProviderDisabled(String provider) { }

        public void onProviderEnabled(String provider) { }

        public void onStatusChanged(String provider
                , int status, Bundle extras) { }
    };

}
