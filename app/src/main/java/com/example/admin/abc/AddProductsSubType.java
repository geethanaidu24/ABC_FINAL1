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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

public class AddProductsSubType extends AppCompatActivity implements View.OnClickListener {
    private static final String UPLOAD_URL = Config.productSubTypesCRUD;
    private static final int IMAGE_REQUEST_CODE = 3;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private ImageView imageView;
    private EditText etCaption;
    private TextView tvPath,productTypeText;
    private Button btnUpload;
    private Bitmap bitmap;
    private Uri filePath;
    public int pstid;
    private int selectedProducttypeid;
    private String selectedProducttype;

    Context context;
   // final ArrayList<ProductTypesDB> productTypesDBs =new ArrayList<>();

    //private Spinner sp1;
   // private ArrayAdapter<ProductTypesDB> adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      //  getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products_sub_type);
        // Get intent data
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity
       selectedProducttypeid = intent.getExtras().getInt("PRODUCTTYPEID_KEY");
        selectedProducttype = intent.getExtras().getString("PRODUCTTYPE_KEY");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(AddProductsSubType.this, ProductSubTypes.class);
                   /* in.putExtra("PRODUCTID_KEY", pid);
                    in.putExtra("PRODUCTNAME_KEY", pname);
                    in.putExtra("PRODUCTTYPEID_KEY", ptid);
                    in.putExtra("PRODUCTTYPENAME_KEY", ptname);
                    startActivity(in);*/
                   finish();
                }
            });

        }

        imageView = (ImageView)findViewById(R.id.brandimage);
        etCaption = (EditText)findViewById(R.id.productsubtypes);
        tvPath    = (TextView)findViewById(R.id.brandpath);
       // sp1 = (Spinner)findViewById(R.id.spproductstypes);
        productTypeText = (TextView)findViewById(R.id.productTypeDisplay);
        btnUpload = (Button)findViewById(R.id.btnUpload);

        requestStoragePermission();
        productTypeText.setText(selectedProducttype);

        imageView.setOnClickListener(this);
        btnUpload.setOnClickListener(this);
    }
    /*@Override
    public void onStart(){
        super.onStart();
       BackTask bt = new BackTask();
        bt.execute();
    }
    private class BackTask extends AsyncTask<Void,Void,Void>{
        ArrayList<String> list;
        protected void onPreExecute() {
            super.onPreExecute();
            list = new ArrayList<>();
        }


        protected Void doInBackground(Void... params) {
            InputStream is = null;
            String result = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(Config.productTypeSpinner);
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                // Get our response as a String.
                is = entity.getContent();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //convert response to string
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                is.close();
                //result=sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // parse json data
            try {
                JSONArray ja = new JSONArray(result);
                JSONObject jo=null;
                productTypesDBs.clear();
                ProductTypesDB productTypesDB;
                for (int i = 0; i < ja.length(); i++) {
                    jo=ja.getJSONObject(i);
                    // add interviewee name to arraylist
                    pstid = jo.getInt("ProductTypeId");
                    String pname = jo.getString("ProductType");
                    productTypesDB=new ProductTypesDB();
                    productTypesDB.setProductTypeId(pstid);
                    productTypesDB.setProductType(pname);
                    productTypesDBs.add(productTypesDB);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {

            // productcrafts.addAll(productcrafts);
            final ArrayList<String> listItems = new ArrayList<>();
            for(int i=0;i<productTypesDBs.size();i++){
                listItems.add(productTypesDBs.get(i).getProductType());
            }
            adapter=new ArrayAdapter(AddProductsSubType.this,R.layout.spinner_layout1, R.id.txt1,listItems);
            sp1.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
*/

    @Override
    public void onClick(View view) {
        if(view == imageView){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Complete action using"), IMAGE_REQUEST_CODE);
        }else if(view == btnUpload){
            checkData();
            //uploadMultipart();
        }
    }

    private void checkData() {
        if (etCaption.length() < 1 || tvPath.length() < 1) {
            Toast.makeText(AddProductsSubType.this, "Fill All", Toast.LENGTH_SHORT).show();
        } else {
            uploadMultipart();
            Toast.makeText(this, "Successfully Completed", Toast.LENGTH_SHORT).show();
            etCaption.setText("");
            tvPath.setText("");
            imageView.setImageResource(R.mipmap.browseimage);
           /* adapter.notifyDataSetChanged();
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
        /*sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View selectedItemView,
                                       int position, long id) {
                ProductTypesDB productTypesDB = (ProductTypesDB) productTypesDBs.get(position);
                final String name = productTypesDB.getProductType();
                //  final int pid
                pstid =productTypesDB.getProductTypeId() ;
                //uploadMultipart();
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                };
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                Toast.makeText(AddProductsSubType.this,
                        "Your Selected : Nothing",
                        Toast.LENGTH_SHORT).show();
            }

        });*/
/*
        ProductTypesDB s = new ProductTypesDB();
        s.setProductType(caption);

        s.setProductTypeId(pstid);*/
        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
            new MultipartUploadRequest(this, uploadId, UPLOAD_URL)
                    .addParameter("action","save")
                    .addFileToUpload(path, "image") //Adding file
                    .addParameter("caption", caption) //Adding text parameter to the request
                    .addParameter("producttypeid",String.valueOf(selectedProducttypeid))
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