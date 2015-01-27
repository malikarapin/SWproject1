package acsm.student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

public class StudentViewattend extends Activity {
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_viewatten);
		
		
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
		 
		Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());
        
        
        String[] myData = new String[] {
    	        formattedDate, "Check In", formattedDate, "Check In", formattedDate, "Check In",
    	        formattedDate, "Check In", formattedDate, "Check In", formattedDate, "Check In"};
		
		
		
		
		 final GridView gView1 = (GridView)findViewById(R.id.gridView1); 
     	
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	                android.R.layout.simple_list_item_1, myData);
	        
	        gView1.setAdapter(adapter);
		
	}


}
