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

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.util.TextUtils;

public class AddContact extends AppCompatActivity implements View.OnClickListener {
    private static final String UPLOAD_URL = Config.contactsCRUD;

    private static final int STORAGE_PERMISSION_CODE = 123;

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
        btnUpload = (Button)findViewById(R.id.btnUpload);


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

        if (TextUtils.isEmpty(bname)) {
            branchname.setError("Fill All");
            branchname.requestFocus();
            return;
        }

        Boolean onError = false;
        if (!isValidEmail(bemail)) {
            onError = true;
            email.setError("Invalid Email");
            return;
        }
Boolean onErrorr=false;
        if(!isValidPhone(bcontactno))
        if (TextUtils.isEmpty(baddress)) {
            contactnumber.setError("Enter Your Contact Number");
            contactnumber.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(bcity)) {
            city.setError("Enter Your Message");
            city.requestFocus();
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

           /* adapter.notifyDataSetChanged();
            BackTask bt = new BackTask();
            bt.execute();*/
        }





    public void uploadMultipart() {
        String bname = branchname.getText().toString();
        String baddress = address.getText().toString();

        String bcity = city.getText().toString();

        String bemail = email.getText().toString();
        String bcontactno=contactnumber.getText().toString();
        String bworkinghours=workinghours.getText().toString();

        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
            new MultipartUploadRequest(this, uploadId, UPLOAD_URL)
                    .addParameter("action","save")

                    .addParameter("branch", bname) //Adding text parameter to the request
                    .addParameter("address", baddress)
                    .addParameter("city", bcity)
                    .addParameter("email", bemail)
                    .addParameter("contact", bcontactno)
                    .addParameter("workinghrs", bworkinghours)

                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload(); //Starting the upload

        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

/*
    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }*/
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