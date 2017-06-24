package com.example.admin.abc;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.InputType;
import android.view.Gravity;
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

public class AddNews extends AppCompatActivity implements View.OnClickListener {
    private static final String DATA_INSERT_URL = Config.newsCRUD;
    private static final int IMAGE_REQUEST_CODE = 3;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private ImageView imageView;
    private EditText etCaption,etDescription;
    private TextView tvPath;
    private Button btnUpload;
    private Bitmap bitmap;
    private Uri filePath;
    int click=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
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
                        Intent in = new Intent(AddNews.this, News.class);
                        // in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(in);
                        finish();
                    }
                }
            });

        }

        imageView = (ImageView)findViewById(R.id.newsimage);
        etCaption = (EditText)findViewById(R.id.news);
        etCaption.setInputType(InputType.TYPE_CLASS_TEXT);
        etDescription = (EditText)findViewById(R.id.descptext);
        etDescription.setInputType(InputType.TYPE_CLASS_TEXT);
        tvPath    = (TextView)findViewById(R.id.newspath);
        btnUpload = (Button)findViewById(R.id.newsbtn);

        requestStoragePermission();

        imageView.setOnClickListener(this);
        btnUpload.setOnClickListener(this);
    }
    public void onBackPressed() {
        //finishAffinity();
        click = click + 1;
        if (click == 1) {
            click = 0;
            Intent in = new Intent(AddNews.this, News.class);
            //  in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            //finish();

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
            } else {
                if (view == btnUpload) {
                    if (etCaption.length() < 1 || etDescription.length() < 1 || tvPath.length() < 1 || bitmap == null) {
                        Toast toast = Toast.makeText(this, "Please Complete it", Toast.LENGTH_SHORT);

                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.toast_drawable);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                        //Toast.makeText(this, "Please Complete it", Toast.LENGTH_SHORT).show();
                    } else {
                        uploadMultipart();
                        Toast toast = Toast.makeText(this, "Successfully Completed", Toast.LENGTH_SHORT);

                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.toast_drawable);
                        //toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                        //Toast.makeText(this, "Successfully Completed", Toast.LENGTH_SHORT).show();
                        etCaption.setText("");
                        tvPath.setText("");
                        etDescription.setText("");
                        imageView.setImageResource(R.mipmap.browseimage);
                        AlertDialog.Builder alert = new AlertDialog.Builder(AddNews.this);
                        alert.setTitle(Html.fromHtml("<font color='#ff0000'>Caution!!!!!!</font>"));
                        alert.setMessage("It will Take Couple of Minutes to make your Changes and Reload...");
                        alert.setIcon(R.drawable.reload);
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent in=new Intent(AddNews.this,AddNews.class);
                                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                        Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(in);
                            }
                        });
                        alert.show();

                    }
                }
            }
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
        String description = etDescription.getText().toString().trim();

        //getting the actual path of the image
        String path = getPath(filePath);

        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
            new MultipartUploadRequest(this, uploadId, DATA_INSERT_URL)
                    .addParameter("action","save")
                    .addFileToUpload(path, "image") //Adding file
                    .addParameter("caption", caption) //Adding text parameter to the request
                    .addParameter("description", description)
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload();
            //Starting the upload
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