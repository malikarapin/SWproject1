package acsm.student;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StudentMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_menu);
		
		Button checkm = (Button)findViewById(R.id.submit);
		checkm.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),StudentCheck.class);
		startActivity(i);
		}
});
		
		Button quest = (Button)findViewById(R.id.button2);
		quest.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),StudentQuestion.class);
		startActivity(i);
		}
});
		
		Button logout = (Button)findViewById(R.id.button3);
		logout.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),StudentLogin.class);
		startActivity(i);
		}
});
		
	
	}
	//Detect Back Button
    @Override
    public void onBackPressed() {
 
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(StudentMenu.this);
 
        alertDialog.setTitle("Confirm Exit...");
        alertDialog.setMessage("คุณต้องการออกจากโปรแกรมหรือไม่ ?");
        alertDialog.setIcon(R.drawable.ic_launcher);
 
        alertDialog.setPositiveButton("ใช่",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        //คลิกใช่ ออกจากโปรแกรม
                        finish();
                        StudentMenu.super.finishAffinity();
                    }
                });
 
        alertDialog.setNegativeButton("ไม่",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,    int which) {
                        //คลิกไม่ cancel dialog
                        dialog.cancel();
                    }
                });
 
        alertDialog.show();
}


}
