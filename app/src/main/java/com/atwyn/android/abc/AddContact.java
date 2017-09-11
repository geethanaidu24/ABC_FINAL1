package com.atwyn.android.abc;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.util.TextUtils;

public class AddContact extends AppCompatActivity implements View.OnClickListener {
    private static final String UPLOAD_URL = Config.contactsCRUD;

    private EditText branchname,address,city,contactnumber,email,workinghours;
int click=0;
    private Button btnUpload;
    String MobilePattern = "[0-9]{10}";
    //String email1 = email.getText().toString().trim();
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
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
                        Intent in = new Intent(AddContact.this, Contact.class);
                        // in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                       /* in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(in);*/
                       finish();

                    }
                }
            });

        }

        branchname = (EditText) findViewById(R.id.editText2);
        branchname.setInputType(InputType.TYPE_CLASS_TEXT);
        address = (EditText)findViewById(R.id.editText6);
        address.setInputType(InputType.TYPE_CLASS_TEXT);
        city    = (EditText) findViewById(R.id.editText11);
        city.setInputType(InputType.TYPE_CLASS_TEXT);
        // sp1 = (Spinner)findViewById(R.id.spproductstypes);
        contactnumber = (EditText) findViewById(R.id.editText12);
        contactnumber.setInputType(InputType.TYPE_CLASS_TEXT);
        email=(EditText) findViewById(R.id.editText14);
        email.setInputType(InputType.TYPE_CLASS_TEXT);
        workinghours=(EditText) findViewById(R.id.editText15);
        workinghours.setInputType(InputType.TYPE_CLASS_TEXT);
        btnUpload = (Button)findViewById(R.id.button2);

        btnUpload.setOnClickListener(this);
    }
    public void onBackPressed() {
        click = click + 1;
        if (click == 1) {
            click = 0;
            //finishAffinity();
            Intent in = new Intent(AddContact.this, Contact.class);
            // in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            /*in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(in);*/
            finish();
            super.onBackPressed();
        }
    }
    @Override
    public void onClick(View view) {
        click = click + 1;
        if (click == 1) {
            click = 0;
            click = click + 1;
            if (click == 1) {
                click = 0;

                if (view == btnUpload) {
                    checkData();
                    //uploadMultipart();
                }
            }
        }
    }

    private void checkData() {
        String bname = branchname.getText().toString();
        String baddress = address.getText().toString();
        String bcontactno=contactnumber.getText().toString();
        String bcity = city.getText().toString();
        String bemail = email.getText().toString();
        String bworkinghrs = workinghours.getText().toString();

        if (TextUtils.isEmpty(bname)) {
            branchname.setError("Fill All");
            branchname.requestFocus();
            return;
        }

    /*    Boolean onError = false;
        if(!TextUtils.isEmpty(bemail)){
            if (!isValidEmail(bemail)) {
                onError = true;
                email.setError("Invalid Email");
                return;
            }
        }
*/
        //Boolean onErrorr=false;
       /* if(!TextUtils.isEmpty(bcontactno)) {
            if (!isValidPhone(bcontactno)) {
                onError = true;
                contactnumber.setError("Invalid contact");
                return;
            }
        }*/
        if(TextUtils.isEmpty(bemail)){

                email.setError(" Enter Email Id");
                return;

        }
      /*  if(TextUtils.isEmpty(bcontactno)) {


                contactnumber.setError("Invalid contact");
                return;

        }*/

        if (TextUtils.isEmpty(baddress)) {

            address.setError("Enter Your Branch Address");
            address.requestFocus();
            return;
        }

      /*  if (TextUtils.isEmpty(bcity)) {
            city.setError("Enter Your city");
            city.requestFocus();
            return;
        }*/
        if (TextUtils.isEmpty(bworkinghrs)) {
            workinghours.setError("Enter Your Timings");
            workinghours.requestFocus();
            return;
        }

        uploadMultipart();
        Toast toast = Toast.makeText(this,
                "Successfully Completed",
                Toast.LENGTH_SHORT);

        View toastView = toast.getView();
        toastView.setBackgroundResource(R.drawable.toast_drawable);

        toast.show();
        //Toast.makeText(this, "Successfully Completed", Toast.LENGTH_SHORT).show();
        branchname.setText("");
        address.setText("");
        city.setText("");
        email.setText("");
        contactnumber.setText("");
        workinghours.setText("");
        AlertDialog.Builder alert = new AlertDialog.Builder(AddContact.this);
        alert.setTitle(Html.fromHtml("<font color='#ff0000'>Information!</font>"));
        alert.setMessage("To Refresh Newly added content Go Back to Home Screen..");
        alert.setIcon(R.drawable.reload);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent in=new Intent(AddContact.this,AddContact.class);
                startActivity(in);
                finish();
            }
        });
        alert.show();
    }
    public void uploadMultipart() {
        String bname = branchname.getText().toString();
        String baddress = address.getText().toString();

        String bcity = city.getText().toString();

        String bemail = email.getText().toString();
        String bcontactno=contactnumber.getText().toString();
        String bworkinghours=workinghours.getText().toString();

        try {
            AndroidNetworking.post(UPLOAD_URL)
                    .addBodyParameter("action","save")
                    .addBodyParameter("branch", bname)
                    .addBodyParameter("address", baddress)
                    .addBodyParameter("city", bcity)
                    .addBodyParameter("email", bemail)
                    .addBodyParameter("contact", bcontactno)
                    .addBodyParameter("workinghrs", bworkinghours)
                    .setTag("TAG_ADD")
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {
                            if(response != null)
                                try {
                                    //SHOW RESPONSE FROM SERVER
                                    String responseString = response.get(0).toString();
                                   // Toast.makeText(AddContact.this, "" + responseString, Toast.LENGTH_SHORT).show();
                                    if (responseString.equalsIgnoreCase("Success")) {
                                      /*  //CLEAR EDITXTS
                                        txtwidth.setText("");
                                        txtheight.setText("");
                                        txtlength.setText("");*/
                                        /*adapter.notifyDataSetChanged();

                                       BackTask bt = new BackTask();
                                        bt.execute();*/

                                    }else
                                    {
                                        Toast.makeText(AddContact.this, " ", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(AddContact.this, " "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        }
                        //ERROR
                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(AddContact.this, "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


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
}





