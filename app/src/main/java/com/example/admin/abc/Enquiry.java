package com.example.admin.abc;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.util.TextUtils;

public class Enquiry extends AppCompatActivity {
    ImageView im;
    int click=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //  getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click = click + 1;
                    if (click == 1) {
                        click = 0;
                        Intent in = new Intent(Enquiry.this, Main2Activity.class);

                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(in);
                        finish();
                    }
                    //  startActivity(in);
                }
            });
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ho);
            toolbar.setOverflowIcon(drawable);
            final EditText your_name = (EditText) findViewById(R.id.editText);
            final EditText your_email = (EditText) findViewById(R.id.editText3);
            final EditText your_subject = (EditText) findViewById(R.id.editText4);
            final EditText your_message = (EditText) findViewById(R.id.editText5);


            Button email = (Button) findViewById(R.id.button7);
            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String name = your_name.getText().toString();
                    String email = your_email.getText().toString();

                    String subject = your_subject.getText().toString();
                    String message = your_message.getText().toString();
                    if (TextUtils.isEmpty(name)) {
                        your_name.setError("Enter Your Name");
                        your_name.requestFocus();
                        return;
                    }

                    Boolean onError = false;
                    if (!isValidEmail(email)) {
                        onError = true;
                        your_email.setError("Invalid Email");
                        Toast.makeText(Enquiry.this, "Please Enter valid Email", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Boolean onErrorr = false;
                    if (!isValidPhone(subject)) {
                        onError = true;
                        your_subject.setError("Invalid Contact Number");
                        Toast.makeText(Enquiry.this, "Please Enter valid Contact Number", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(subject)) {
                        your_subject.setError("Enter Your Contact Number");
                        your_subject.requestFocus();
                        return;
                    }

                    if (TextUtils.isEmpty(message)) {
                        your_message.setError("Enter Your Message");
                        your_message.requestFocus();
                        return;
                    }

                    Intent sendEmail = new Intent(Intent.ACTION_SEND);

            /* Fill it with Data */
                    sendEmail.setData(Uri.parse("mailto:"));
                    sendEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{"reddy@abcuganda.com"});
                    sendEmail.putExtra(Intent.EXTRA_SUBJECT,"ABC Enquiry");
                    sendEmail.putExtra(Intent.EXTRA_PHONE_NUMBER,subject);
                    sendEmail.putExtra(Intent.EXTRA_TEXT,
                            "Name:" + name + '\n' + "Contact No:" + subject + '\n' +"Email ID:" + email + '\n' + "Message:" + '\n' + message);
                    sendEmail.setType("plain/text");
            /* Send it off to the Activity-Chooser */

                    try {
                        startActivity(Intent.createChooser(sendEmail, "Select email Client...."));
                        your_name.setText(null);
                        your_name.setHint("Name");
                        your_email.setText(null);
                        your_email.setHint("Email");
                        your_subject.setText(null);
                        your_subject.setHint("Contact Number");
                        your_message.setText(null);
                        your_message.setHint("Comment");

                    } catch (android.content.ActivityNotFoundException ex) {

                        Toast.makeText(Enquiry.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }
    }
 /*   public void onBackPressed() {
        //finishAffinity();
        Intent in = new Intent(Enquiry.this, Main2Activity.class);
        finish();
    }*/

    @Override
    public void onResume() {
        super.onResume();
        //Get a Tracker (should auto-report)


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }


    // validating email id

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isValidPhone(String phone)
    {
        String expression = "^([0-9\\+]|\\(\\d{1,3}\\))[0-9\\-\\. ]{3,15}$";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.



        //getMenuInflater().inflate(R.menu.mainproducts, menu);


        getMenuInflater().inflate(R.menu.home, menu);


        return true;


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if(id==R.id.h1)
        {
            Intent inn = new Intent(Enquiry.this, Main2Activity.class);
            //inn.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
               /* inn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);*/
            startActivity(inn);

            return true;
        }


        return super.onOptionsItemSelected(item);
    }


}