package com.example.admin.abc;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.util.TextUtils;

public class AddContact extends AppCompatActivity implements View.OnClickListener {
    private static final String UPLOAD_URL = Config.contactsCRUD;

    private EditText branchname,address,city,contactnumber,email,workinghours;

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
                    Intent in = new Intent(AddContact.this, Contact.class);
                   /* in.putExtra("PRODUCTID_KEY", pid);
                    in.putExtra("PRODUCTNAME_KEY", pname);
                    in.putExtra("PRODUCTTYPEID_KEY", ptid);
                    in.putExtra("PRODUCTTYPENAME_KEY", ptname);
                    startActivity(in);*/
                    finish();
                }
            });

        }

        branchname = (EditText) findViewById(R.id.editText2);
        address = (EditText)findViewById(R.id.editText6);
        city    = (EditText) findViewById(R.id.editText11);
        // sp1 = (Spinner)findViewById(R.id.spproductstypes);
        contactnumber = (EditText) findViewById(R.id.editText12);
        email=(EditText) findViewById(R.id.editText14);
        workinghours=(EditText) findViewById(R.id.editText15);
        btnUpload = (Button)findViewById(R.id.button2);

        btnUpload.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        if(view == btnUpload){
            checkData();
            //uploadMultipart();
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

        Boolean onError = false;
        if(!TextUtils.isEmpty(bemail)){
            if (!isValidEmail(bemail)) {
                onError = true;
                email.setError("Invalid Email");
                return;
            }
        }

        //Boolean onErrorr=false;
        if(!TextUtils.isEmpty(bcontactno)) {
            if (!isValidPhone(bcontactno)) {
                onError = true;
                contactnumber.setError("Invalid contact");
                return;
            }
        }
        if (TextUtils.isEmpty(baddress)) {
            address.setError("Enter Your Branch Address");
            address.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(bcity)) {
            city.setError("Enter Your city");
            city.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(bworkinghrs)) {
            workinghours.setError("Enter Your Timings");
            workinghours.requestFocus();
            return;
        }

        uploadMultipart();
        Toast.makeText(this, "Successfully Completed", Toast.LENGTH_SHORT).show();
        branchname.setText("");
        address.setText("");
        city.setText("");
        email.setText("");
        contactnumber.setText("");
        workinghours.setText("");
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
                                    Toast.makeText(AddContact.this, "PHP SERVER RESPONSE : " + responseString, Toast.LENGTH_SHORT).show();
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
                                        Toast.makeText(AddContact.this, "PHP WASN'T SUCCESSFUL. ", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(AddContact.this, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIVED : "+e.getMessage(), Toast.LENGTH_SHORT).show();
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
        if (matcher.matches())
        {
            return true;
        }
        else{
            return false;
        }
    }
    public static boolean isValidPhone(String phone)
    {
        String expression = "^([0-9\\+]|\\(\\d{1,3}\\))[0-9\\-\\. ]{3,15}$";
        CharSequence inputString = phone;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches())
        {
            return true;
        }
        else{
            return false;
        }
    }
}





