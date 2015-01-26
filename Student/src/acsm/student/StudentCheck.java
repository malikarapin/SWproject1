package acsm.student;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class StudentCheck extends Activity {

	TextView text1;
	TextView text2;
	Intent i;
	GPSTracker gps;
	
	// url to create new product
    //public String url_create_product = "http://192.168.208.230/test.php";
    private static final String TAG_SUCCESS = "success";
	
	private ProgressDialog pDialog;
	private double latitude = 0;
	private double longitude = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_check);

		text1 = (TextView) findViewById(R.id.lat);
		text2 = (TextView) findViewById(R.id.lon);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy); 

		gps = new GPSTracker(StudentCheck.this);
		
		if (gps.canGetLocation()) {

			latitude = gps.getLatitude();
			longitude = gps.getLongitude();

			text1.setText("latitude :"+latitude);
			text2.setText("longitude :"+longitude);

		} else {
			text1.setText("อุปกรณ์์ของคุณ ปิด GPS");
		}
		Button check = (Button) findViewById(R.id.submit);
		check.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/*try{
					
		            String latitude1 = String.valueOf(latitude);
		            String longitude2 =  String.valueOf(longitude);
		            String link="http://172.19.13.207/db_create.php";
		            String data  = URLEncoder.encode("latitude", "UTF-8") 
		            + "=" + URLEncoder.encode(latitude1, "UTF-8");
		            data += "&" + URLEncoder.encode("longitude", "UTF-8") 
		            + "=" + URLEncoder.encode(longitude2, "UTF-8");
		            URL url = new URL(link);
		            URLConnection conn = url.openConnection(); 
		            conn.setDoOutput(true); 
		            OutputStreamWriter wr = new OutputStreamWriter
		            (conn.getOutputStream()); 
		            wr.write( data ); 
		            wr.flush(); 
		            BufferedReader reader = new BufferedReader
		            (new InputStreamReader(conn.getInputStream()));
		            StringBuilder sb = new StringBuilder();
		            String line = null;
		            // Read Server Response
		            while((line = reader.readLine()) != null)
		            {
		               sb.append(line);
		               break;
		            }
		            String aa = sb.toString();
		           
		            //
		            //Toast.makeText(getApplicationContext(),latitude1 , Toast.LENGTH_SHORT).show();
		            
		         }catch(Exception e){
		        	 e.getMessage();
		         }*/
				Intent tomenu = new Intent(getApplicationContext(), StudentPasscode.class);

				startActivity(tomenu);

			}
		});
		
		
		List < String > list = new ArrayList < String > ( );
		 
		list.add ( "ProjectI" );
		 
		list.add ( "SW VERIFICATION &VALIDATION" );
		
		 
		list.add ( "WORKSHOP IN IT III(WEB JAVA)" );
		
		list.add ( "HUMAN COMPUTER INTERACTION" );
		
		list.add ( "SOFTWARE PROJECT MANAGEMENT" );
		
		list.add ( "SOFTWARE ARCHITECTURE" );
		ArrayAdapter < String > dataAdapter = new ArrayAdapter < String > ( this, android.R.layout.simple_spinner_item, list );
		 
		 
		Spinner spinner = ( Spinner ) this.findViewById ( R.id.spinner1 );
		 
		spinner.setAdapter ( dataAdapter );

		

	}
	
}
