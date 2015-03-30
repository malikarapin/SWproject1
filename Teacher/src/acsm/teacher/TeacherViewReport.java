package acsm.teacher;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;




import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;

public class TeacherViewReport extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher_view_report);
		
		
		Button acpet = (Button)findViewById(R.id.btncancelchpw);
		acpet.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),TeacherMenu.class);
		startActivity(i);
		}
});
		

		

		Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        
        
        String[] myData = new String[] {
        		"5430213054", formattedDate, "5430213025", formattedDate, "5430213019", formattedDate, "5430213040",
        		formattedDate,"5430213016",   formattedDate, "5430213044",formattedDate, "5430213033", formattedDate};
		
		
		
		
		 final GridView gView1 = (GridView)findViewById(R.id.gridView1); 
     	
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	                android.R.layout.simple_list_item_1, myData);
	        
	        gView1.setAdapter(adapter);
		
		
	        
	        
	        Button add = (Button)findViewById(R.id.addstudentid);
	        add.setOnClickListener(new OnClickListener() {
				
				@Override
			public void onClick(View v) {
			Intent i = new Intent(getApplicationContext(),TeacherViewReport.class);
			startActivity(i);
			}
	});
	       
	}
}
