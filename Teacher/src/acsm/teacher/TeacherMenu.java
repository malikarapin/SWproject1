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
		
		
		Button quize = (Button)findViewById(R.id.button2);
		quize.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),TeaxherQuestion.class);
		startActivity(i);
		}
});
		
		Button check = (Button)findViewById(R.id.login);
		check.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),TeacherCheck.class);
		startActivity(i);
		}
});
		Button logout = (Button)findViewById(R.id.button1);
		logout.setOnClickListener(new OnClickListener() {
			
			@Override
		public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),TeacherLogin.class);
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
