package com.example.admin.abc;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;

public class Refresh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        AlertDialog.Builder alert = new AlertDialog.Builder(Refresh.this);
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
    }
    public void onBackPressed() {
        //finishAffinity();
        Intent in = new Intent(Refresh.this, MainActivity.class);
        // in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(in);
        finish();
        //finish();
    }
}
