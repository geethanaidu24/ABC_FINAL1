package com.example.admin.abc;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.Html;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

import static android.view.View.OnClickListener;

public class AddProductsTypes extends AppCompatActivity implements OnClickListener {
    private static final String UPLOAD_URL = Config.productTypesCRUD;
    private static final int IMAGE_REQUEST_CODE = 3;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private ImageView imageView;
    private EditText etCaption;
    private TextView tvPath, productName;
    private Button btnUpload;
    private Bitmap bitmap;
    private Uri filePath;
    public int spid=0;
    int click=0;
    private int selectedProductId;
    private String selectedProductName;
    Context context;
    private String Select;
    ArrayList<MySQLDataBase> mySQLDataBases;
    /*final ArrayList<MySQLDataBase> mySQLDataBases =new ArrayList<>();
    private Spinner sp;
    private ArrayAdapter<String> adapter ;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
     //   getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products_types);
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity
         selectedProductId = intent.getExtras().getInt("PRODUCTID_KEY");
         selectedProductName = intent.getExtras().getString("PRODUCTNAME_KEY");
        mySQLDataBases = (ArrayList<MySQLDataBase>) intent.getSerializableExtra("ProductTypeList");
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
                        /*Intent in = new Intent(AddProductsTypes.this, Refresh.class);*/
                        Intent in = new Intent(AddProductsTypes.this, ProductTypes.class);
                        in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        in.putExtra("PRODUCTID_KEY", selectedProductId);
                        in.putExtra("PRODUCTNAME_KEY", selectedProductName);
                        in.putExtra("ProductTypeList", mySQLDataBases);
                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(in);
                        finish();
                    }
                    //finish();

                }
            });

        }

        imageView = (ImageView)findViewById(R.id.brandimage);
        etCaption = (EditText)findViewById(R.id.producttypes);
        etCaption.setInputType(InputType.TYPE_CLASS_TEXT);
        tvPath    = (TextView)findViewById(R.id.brandpath);
        productName = (TextView)findViewById(R.id.textView26);
       // sp = (Spinner)findViewById(R.id.addProSp);
        //sp.setPrompt("Select");

        btnUpload = (Button)findViewById(R.id.btnUpload);
        productName.setText(selectedProductName);

        requestStoragePermission();

        imageView.setOnClickListener(this);
        btnUpload.setOnClickListener(this);
    }
    public void onBackPressed() {
        //finishAffinity();
        click = click + 1;
        if (click == 1) {
            click = 0;
            Intent in = new Intent(AddProductsTypes.this, ProductTypes.class);
            in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            in.putExtra("PRODUCTID_KEY", selectedProductId);
            in.putExtra("PRODUCTNAME_KEY", selectedProductName);
            in.putExtra("ProductTypeList", mySQLDataBases);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(in);
            finish();
        }
    }
    @Override
    public void onClick(View view) {
        click = click + 1;
        if (click == 1) {
            click = 0;
            if (view == imageView) {
           /* Intent intent = new Intent();
            intent.setType("image*//*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Complete action using"), IMAGE_REQUEST_CODE);*/
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, IMAGE_REQUEST_CODE);
            } else if (view == btnUpload) {
                checkData();
                //for checking empty values
            }
        }
    }

    private void checkData() {
        if (etCaption.length() < 1 || tvPath.length() < 1 || bitmap==null) {
            Toast toast = Toast.makeText(AddProductsTypes.this, "Fill All", Toast.LENGTH_SHORT);

            View toastView = toast.getView();
            toastView.setBackgroundResource(R.drawable.toast_drawable);
            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            //Toast.makeText(AddProductsTypes.this, "Fill All", Toast.LENGTH_SHORT).show();
        } else {
            uploadMultipart();
            Toast toast = Toast.makeText(this, "Successfully Completed", Toast.LENGTH_SHORT);

            View toastView = toast.getView();
            toastView.setBackgroundResource(R.drawable.toast_drawable);
            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            //Toast.makeText(this, "Successfully Completed", Toast.LENGTH_SHORT).show();
            etCaption.setText("");
            tvPath.setText("");
            imageView.setImageResource(R.mipmap.browseimage);
            AlertDialog.Builder alert = new AlertDialog.Builder(AddProductsTypes.this);
            alert.setTitle(Html.fromHtml("<font color='#ff0000'>Caution!!!!!!</font>"));
            alert.setMessage("It will Take Couple of Minutes to make your Changes and Reload...");
            alert.setIcon(R.drawable.reload);
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent in=new Intent(AddProductsTypes.this,AddProductsTypes.class);
                    startActivity(in);
                    finish();
                }
            });
            alert.show();
           // adapter.notifyDataSetChanged();
           /* BackTask bt = new BackTask();
            bt.execute();*/

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                tvPath.setText("Path: ". concat(getPath(filePath)));
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadMultipart() {
        String caption = etCaption.getText().toString().trim();


        //getting the actual path of the image
        String path = getPath(filePath);

        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
            new MultipartUploadRequest(this, uploadId, UPLOAD_URL)
                    .addParameter("action","save")
                    .addFileToUpload(path, "image") //Adding file
                    .addParameter("caption", caption) //Adding text parameter to the request
                    .addParameter("productid", String.valueOf(selectedProductId))
                    .addParameter("productname", selectedProductName)
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload(); //Starting the upload

        } catch (Exception exc) {
                Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

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
            Toast.makeText(this, "You Need Permission to read from the storage", Toast.LENGTH_LONG).show();
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
    }

}