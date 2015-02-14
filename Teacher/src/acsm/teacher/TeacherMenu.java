package acsm.teacher;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TeacherMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher_menu);
		
		
		Button quize = (Button)findViewById(R.id.btncancelchpw);
		quize.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),TeaxherQuestion.class);
		startActivity(i);
		}
});
		
		Button check = (Button)findViewById(R.id.submit);
		check.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),TeacherCheck.class);
		startActivity(i);
		}
});
		Button logout = (Button)findViewById(R.id.logout);
		logout.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(TeacherMenu.this);
		alertDialog.setTitle("Confirm Logout...");
        alertDialog.setMessage("คุณต้องการลงชื่อออกการใช้งานใช่หรือไม่");
        alertDialog.setIcon(R.drawable.ic_launcher);
        alertDialog.setPositiveButton("ใช่",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        //คลิกใช่ ออกจากโปรแกรม
                        //finish();
                        Intent i = new Intent(getApplicationContext(),TeacherLogin.class);
                		startActivity(i);
                        TeacherMenu.super.finishAffinity();
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
				
		//Intent i = new Intent(getApplicationContext(),StudentLogin.class);
		//startActivity(i);
		}
});
		
		Button chpw = (Button)findViewById(R.id.chpassword);
		chpw.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),TeacherChangePassword.class);
		startActivity(i);
		}
});
		
		
		
	}
	//Detect Back Button
    @Override
    public void onBackPressed() {
 
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(TeacherMenu.this);
 
        alertDialog.setTitle("Confirm Exit...");
        alertDialog.setMessage("คุณต้องการออกจากโปรแกรมหรือไม่ ?");
        alertDialog.setIcon(R.drawable.ic_launcher);
 
        alertDialog.setPositiveButton("ใช่",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        //คลิกใช่ ออกจากโปรแกรม
                        finish();
                        TeacherMenu.super.finishAffinity();
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
