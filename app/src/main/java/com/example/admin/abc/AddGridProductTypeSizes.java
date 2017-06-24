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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

import static com.example.admin.abc.R.id.name8;

public class AddGridProductTypeSizes extends AppCompatActivity implements View.OnClickListener {
    private static final String addGridData = Config.producttypeSizesGridsCRUD;
    final static String productSizeCheckUrl = Config.productTypeSizesUrlAddress;
   // private static final String addSpinData = Config.sizeSpinner;
    private static final int IMAGE_REQUEST_CODE = 3;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private ImageView imageView;
    private TextView Path, txtSize,txtType,txtProduct;
    private EditText name,brand,color;
    private Button btnadd;
    private Bitmap bitmap;
    private Uri filePath;
    int click=0;
    private static int recivedProductId,recivedProductTypeId,recivedProductTypeSizeId;
    private static String recivedProductName,recivedProducttype,recivedFinalSize,finalSelProtypeSize;
    private static int finalProLength,finalProWidth,finalProHeight;
   // public int pid=0;
   // public int ptid=0;
   // public int psid=0;

  //  String finalSize;
    Context context;
    ArrayList<MySQLDataBase> mySQLDataBases1;
    ArrayList<MySQLDataBase> mySQLDataBases2;
    URL sizeSpinnerUrl = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grid_product_type_sizes);
        Intent intent = getIntent();
        recivedProductId = intent.getExtras().getInt("PRODUCTID_KEY");
        recivedProductName =intent.getExtras().getString("PRODUCTNAME_KEY");
        recivedProductTypeId = intent.getExtras().getInt("PRODUCTTYPEID_KEY");
        recivedProducttype = intent.getExtras().getString("PRODUCTTYPE_KEY");
        recivedProductTypeSizeId = intent.getExtras().getInt("PRODUCTTYPESIZEID_KEY");
        recivedFinalSize = intent.getExtras().getString("FINALSIZE_KEY");
        finalProLength = intent.getExtras().getInt("LENGTH_KEY");
        finalProWidth = intent.getExtras().getInt("WIDTH_KEY");
        finalProHeight = intent.getExtras().getInt("HEIGHT_KEY");
        mySQLDataBases2 = (ArrayList<MySQLDataBase>) intent.getSerializableExtra("ProductTypeSizeList");

        mySQLDataBases1 = (ArrayList<MySQLDataBase>) intent.getSerializableExtra("ProductTypeList");
        if(finalProLength !=0 && finalProWidth !=0 && finalProHeight !=0){
            finalSelProtypeSize =  finalProWidth + "X" + finalProHeight + "X" + finalProLength;

        }else if(finalProLength ==0 && finalProWidth !=0 && finalProHeight !=0){
            finalSelProtypeSize =  finalProWidth + "X" + finalProHeight;

        }else if(finalProLength !=0 && finalProWidth ==0 && finalProHeight !=0){
            finalSelProtypeSize =  finalProLength + "X" + finalProHeight;

        }else if(finalProLength !=0 && finalProWidth !=0 && finalProHeight ==0 ){
            finalSelProtypeSize =  finalProLength + "X" + finalProHeight ;

        }else if(finalProLength ==0 && finalProWidth !=0 && finalProHeight ==0 ){
            finalSelProtypeSize = finalProWidth + "" ;

        }else if(finalProLength !=0 && finalProWidth ==0 && finalProHeight ==0 ){
            finalSelProtypeSize = finalProLength + "" ;

        }else if(finalProLength ==0 && finalProWidth ==0 && finalProHeight !=0 ){
            finalSelProtypeSize = finalProHeight + "" ;

        }

        Toolbar actionbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != actionbar) {
            actionbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            actionbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click = click + 1;
                    if (click == 1) {
                        click = 0;
                        Intent in = new Intent(AddGridProductTypeSizes.this, ProductTypeSizeImagesGridView.class);
                        //  in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        in.putExtra("PRODUCTID_KEY", recivedProductId);
                        in.putExtra("PRODUCTNAME_KEY", recivedProductName);
                        in.putExtra("PRODUCTTYPESIZEID_KEY", recivedProductTypeSizeId);
                        in.putExtra("PRODUCTTYPEID_KEY", recivedProductTypeId);
                        in.putExtra("PRODUCTTYPE_KEY", recivedProducttype);
                        in.putExtra("FINALSIZE_KEY", recivedFinalSize);
                        in.putExtra("WIDTH_KEY", finalProWidth);
                        in.putExtra("LENGTH_KEY", finalProLength);
                        in.putExtra("HEIGHT_KEY", finalProHeight);
                        in.putExtra("ProductTypeList",mySQLDataBases1);
                        in.putExtra("ProductTypeSizeList",mySQLDataBases2);
                        startActivity(in);
                        finish();
                    }
                }
            });
            imageView = (ImageView) findViewById(R.id.image8);
            name = (EditText) findViewById(name8);
            name.setInputType(InputType.TYPE_CLASS_TEXT);
            Path = (TextView) findViewById(R.id.brandpath);
            brand = (EditText) findViewById(R.id.brand8);
            brand.setInputType(InputType.TYPE_CLASS_TEXT);
            color = (EditText) findViewById(R.id.color8);
            color.setInputType(InputType.TYPE_CLASS_TEXT);
            btnadd = (Button) findViewById(R.id.btnadd8);
            txtSize = (TextView) findViewById(R.id.sizetext);
            txtType = (TextView) findViewById(R.id.typetext);
            txtProduct = (TextView)findViewById(R.id.protext);
            txtSize.setText(finalSelProtypeSize);
            txtType.setText(recivedProducttype);
            txtProduct.setText(recivedProductName);

            requestStoragePermission();

            imageView.setOnClickListener(this);
            btnadd.setOnClickListener(this);
        }
    }
    public void onBackPressed() {
        //finishAffinity();
        Intent in = new Intent(AddGridProductTypeSizes.this, ProductTypeSizeImagesGridView.class);
       // in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        in.putExtra("PRODUCTID_KEY", recivedProductId);
        in.putExtra("PRODUCTNAME_KEY", recivedProductName);
        in.putExtra("PRODUCTTYPESIZEID_KEY", recivedProductTypeSizeId);
        in.putExtra("PRODUCTTYPEID_KEY", recivedProductTypeId);
        in.putExtra("PRODUCTTYPE_KEY", recivedProducttype);
        in.putExtra("FINALSIZE_KEY", recivedFinalSize);
        in.putExtra("WIDTH_KEY", finalProWidth);
        in.putExtra("LENGTH_KEY", finalProLength);
        in.putExtra("HEIGHT_KEY", finalProHeight);
        in.putExtra("ProductTypeList",mySQLDataBases1);
        in.putExtra("ProductTypeSizeList",mySQLDataBases2);
        startActivity(in);
        finish();
    }


    @Override
    public void onClick(View v) {
        click = click + 1;
        if (click == 1) {
            click = 0;
            if (v == imageView) {
            /*Intent intent = new Intent();
            intent.setType("image*//*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Complete action using"), IMAGE_REQUEST_CODE);*/
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, IMAGE_REQUEST_CODE);
            } else if (v == btnadd) {
                checkData();
                //uploadMultipart();
            }
        }
    }
    private void checkData() {
        if (name.length() < 1 || Path.length() < 1|| brand.length() < 1 || color.length() < 1 || bitmap==null) {
            Toast toast = Toast.makeText(AddGridProductTypeSizes.this, "Fill All", Toast.LENGTH_SHORT);

            View toastView = toast.getView();
            toastView.setBackgroundResource(R.drawable.toast_drawable);
            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            //Toast.makeText(AddGridProductTypeSizes.this, "Fill All", Toast.LENGTH_SHORT).show();
        } else {
            uploadMultipart();
            Toast toast = Toast.makeText(this, "Successfully Completed", Toast.LENGTH_SHORT);

            View toastView = toast.getView();
            toastView.setBackgroundResource(R.drawable.toast_drawable);
           // toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            //Toast.makeText(this, "Successfully Completed", Toast.LENGTH_SHORT).show();
            name.setText("");
            Path.setText("");
            brand.setText("");
            color.setText("");
            imageView.setImageResource(R.mipmap.browseimage);
            AlertDialog.Builder alert = new AlertDialog.Builder(AddGridProductTypeSizes.this);
            alert.setTitle(Html.fromHtml("<font color='#ff0000'>Caution!!!!!!</font>"));
            alert.setMessage("It will Take Couple of Minutes to make your Changes and Reload...");
            alert.setIcon(R.drawable.reload);
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent in=new Intent(AddGridProductTypeSizes.this,AddGridProductTypeSizes.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    in.putExtra("PRODUCTID_KEY", recivedProductId);
                    in.putExtra("PRODUCTNAME_KEY", recivedProductName);
                    in.putExtra("PRODUCTTYPESIZEID_KEY", recivedProductTypeSizeId);
                    in.putExtra("PRODUCTTYPEID_KEY", recivedProductTypeId);
                    in.putExtra("PRODUCTTYPE_KEY", recivedProducttype);
                    in.putExtra("FINALSIZE_KEY", recivedFinalSize);
                    in.putExtra("WIDTH_KEY", finalProWidth);
                    in.putExtra("LENGTH_KEY", finalProLength);
                    in.putExtra("HEIGHT_KEY", finalProHeight);
                    in.putExtra("ProductTypeList",mySQLDataBases1);
                    in.putExtra("ProductTypeSizeList",mySQLDataBases2);
                    startActivity(in);
                }
            });
            alert.show();
            //txtlength.setText("");
           /* adapter1.notifyDataSetChanged();
            adapter2.notifyDataSetChanged();
            adapter3.notifyDataSetChanged();
      BackTask bt = new BackTask();
            bt.execute();*/
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                Path.setText("Path: ".concat(getPath(filePath)));
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadMultipart() {
        String namec = name.getText().toString().trim();
        String brandc = brand.getText().toString().trim();
        String colorc = color.getText().toString().trim();
        /*String spinSelVal1 = sp1.getSelectedItem().toString();
        String spinSelVal2=sp2.getSelectedItem().toString();
        String spinSelVal3 = sp3.getSelectedItem().toString();*/

        //getting the actual path of the image
        String path = getPath(filePath);


        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
            new MultipartUploadRequest(this, uploadId, addGridData)
                    .addParameter("action","save")
                    .addFileToUpload(path, "image") //Adding file
                    .addParameter("caption", namec) //Adding text parameter to the request
                    .addParameter("brand", brandc)
                    .addParameter("color", colorc)
                    .addParameter("productsizeid", String.valueOf(recivedProductTypeSizeId))
                    .addParameter("producttypeid", String.valueOf(recivedProductTypeId))
                    .addParameter("productid", String.valueOf(recivedProductId))
                    .addParameter("productname",recivedProductName)
                    .addParameter("producttype",recivedProducttype)
                    .addParameter("productsize",finalSelProtypeSize)
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

