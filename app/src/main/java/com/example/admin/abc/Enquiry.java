package com.example.admin.abc;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
                        finish();
                    }
                    //  startActivity(in);
                }
            });
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
                    sendEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{"geethamca.naidu@gmail.com"});
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


}