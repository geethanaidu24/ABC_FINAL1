package com.atwyn.android.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Refresh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
       /* AlertDialog.Builder alert = new AlertDialog.Builder(Refresh.this);
        alert.setTitle(Html.fromHtml("<font color='#ff0000'>Caution!!!!!!</font>"));
        alert.setMessage("It will Take Couple of Minutes to make your Changes and Reload...");
        alert.setIcon(R.drawable.reload);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent in=new Intent(Refresh.this,MainActivity.class);
                startActivity(in);
                finish();
            }
        });
        alert.show();
    }*/
        Button b = (Button) findViewById(R.id.b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    Intent in = new Intent(Refresh.this, MainActivity.class);

            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);
                finish();
            }
        });
    }
    public void onBackPressed() {
        //finishAffinity();
        Toast toast = Toast.makeText(getApplicationContext(),
                "Please Click OK to Continue...",
                Toast.LENGTH_SHORT);

        View toastView = toast.getView();
        toastView.setBackgroundResource(R.drawable.toast_drawable);

        toast.show();
       // Toast.makeText(this,"Please Click OK to Continue...",Toast.LENGTH_SHORT).show();
       /* Intent in = new Intent(Refresh.this, MainActivity.class);
        in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(in);
        finish();*/
        //finish();
    }
}
