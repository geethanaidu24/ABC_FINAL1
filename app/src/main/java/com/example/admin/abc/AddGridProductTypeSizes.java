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
import android.util.Log;
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
    private static final String addSpinData = Config.sizeSpinner;
    private static final int IMAGE_REQUEST_CODE = 3;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private ImageView imageView;
    private TextView Path, txtSize,txtType,txtProduct;
    private EditText name,brand,color;
    private Button btnadd;
    private Bitmap bitmap;
    private Uri filePath;
    private static int recivedProductId,recivedProductTypeId,recivedProductTypeSizeId;
    private static String recivedProductName,recivedProducttype,recivedFinalSize,finalSelProtypeSize;
    private static int finalProLength,finalProWidth,finalProHeight;
   // public int pid=0;
   // public int ptid=0;
   // public int psid=0;

  //  String finalSize;
    Context context;
   // final ArrayList<MySQLDataBase> mySQLDataBases =new ArrayList<>();
   // private Spinner sp1,sp2,sp3;
   // private ArrayAdapter<MySQLDataBase> adapter1 ;
   // private ArrayAdapter<MySQLDataBase> adapter2 ;
   // private ArrayAdapter<MySQLDataBase> adapter3 ;
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
       /* Uri builtUri = Uri.parse(productSizeCheckUrl)
                .buildUpon()
                .appendQueryParameter(Config.PRODUCTID_PARAM, Integer.toString(recivedProductId))
                .appendQueryParameter(Config.PRODUCTSIZEID_PARAM, Integer.toString(recivedProductTypeId))
                .build();

        try {
            sizeSpinnerUrl = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/
        Toolbar actionbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != actionbar) {
            actionbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            actionbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(AddGridProductTypeSizes.this, ProductSubTypeGridView.class);
                    // startActivity(in);
                    finish();
                }
            });
            imageView = (ImageView) findViewById(R.id.image8);
            name = (EditText) findViewById(name8);
            Path = (TextView) findViewById(R.id.brandpath);
            brand = (EditText) findViewById(R.id.brand8);
            color = (EditText) findViewById(R.id.color8);
           /* sp1 = (Spinner) findViewById(R.id.sizespinner8);
            sp2 = (Spinner) findViewById(R.id.typespinner8);
            sp3 = (Spinner) findViewById(R.id.productspinner8);*/

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
    /*public void onStart() {
        super.onStart();
     BackTask bt = new BackTask();
        bt.execute();

    }
    private class BackTask extends AsyncTask<Void, Void, Void> {

        protected void onPreExecute() {
            super.onPreExecute();

        }
        protected Void doInBackground(Void... params) {
            InputStream is = null;
            String result = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(String.valueOf(sizeSpinnerUrl));
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
                JSONObject jo = null;
                mySQLDataBases.clear();
                MySQLDataBase mySQLDataBase;
                for (int i = 0; i < ja.length(); i++) {
                    jo = ja.getJSONObject(i);

                    psid = jo.getInt("ProductSizeId");
                    int width = jo.getInt("Width");
                    int height=jo.getInt("Height");
                    int length=jo.getInt("Length");
                    mySQLDataBase = new MySQLDataBase();
                    mySQLDataBase.setProductSizeId(psid);
                    mySQLDataBase.setWidth(width);
                    mySQLDataBase.setHeight(height);
                    mySQLDataBase.setLength(length);
                    mySQLDataBases.add(mySQLDataBase);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {

            final ArrayList<String> listItems = new ArrayList<>();
            for(int i=0;i<mySQLDataBases.size();i++){

                final int width = Integer.parseInt(String.valueOf(mySQLDataBases.get(i).getWidth()).toString());
                final int height = Integer.parseInt(String.valueOf(mySQLDataBases.get(i).getHeight()).toString());
                final int length = Integer.parseInt(String.valueOf(mySQLDataBases.get(i).getLength()).toString());
                //final String measure =productTypeSizeDBData.getMeasurement().toString();

                if(length !=0 && width !=0 && height !=0){
                    finalSize =  width + "X" + height + "X" + length;
                    listItems.add(String.valueOf(finalSize));

                }else if(length ==0 && width !=0 && height !=0){
                    finalSize =  width + "X" + height;
                    listItems.add(String.valueOf(finalSize));
                }else if(length !=0 && width ==0 && height !=0){
                    finalSize =  length + "X" + height;
                    listItems.add(String.valueOf(finalSize));
                }else if(length !=0 && width !=0 && height ==0 ){
                    finalSize =  length + "X" + width ;
                    listItems.add(String.valueOf(finalSize));
                }else if(length ==0 && width !=0 && height ==0 ){
                    finalSize = width + "" ;
                    listItems.add(String.valueOf(finalSize));
                }else if(length !=0 && width ==0 && height ==0 ){
                    finalSize = length + "" ;
                    listItems.add(String.valueOf(finalSize));
                }else if(length ==0 && width ==0 && height !=0 ){
                    finalSize = height + "" ;
                    listItems.add(String.valueOf(finalSize));
                }

            }

            adapter1=new ArrayAdapter(AddGridProductTypeSizes.this,R.layout.spinner_layout1, R.id.txt1,listItems);
            sp1.setAdapter(adapter1);
            adapter1.notifyDataSetChanged();
          ProductTypeTask producttypeTask = new ProductTypeTask();
            producttypeTask.execute();

        }
    }
    private class ProductTypeTask extends AsyncTask<Void, Void, Void> {

        protected void onPreExecute() {
            super.onPreExecute();

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
                JSONObject jo = null;
                mySQLDataBases.clear();
                MySQLDataBase mySQLDataBase;
                for (int i = 0; i < ja.length(); i++) {
                    jo = ja.getJSONObject(i);
                    ptid = jo.getInt("ProductTypeId");
                    String productType = jo.getString("ProductType");
                    mySQLDataBase = new MySQLDataBase();
                    mySQLDataBase.setProductTypeId(ptid);
                    mySQLDataBase.setProductType(productType);
                    mySQLDataBases.add(mySQLDataBase);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {

            final ArrayList<String> listItems2 = new ArrayList<>();
            for (int i = 0; i < mySQLDataBases.size(); i++) {
                listItems2.add(mySQLDataBases.get(i).getProductType());

            }

            adapter2 = new ArrayAdapter(AddGridProductTypeSizes.this, R.layout.spinner_layout2, R.id.txt2, listItems2);
            sp2.setAdapter(adapter2);
            adapter2.notifyDataSetChanged();
           ProductTask productTask = new ProductTask();
            productTask.execute();
        }
    }

    private class ProductTask extends AsyncTask<Void, Void, Void> {

        protected void onPreExecute() {
            super.onPreExecute();

        }
        protected Void doInBackground(Void... params) {
            InputStream is = null;
            String result = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(Config.productsUrlAddress);
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
                JSONObject jo = null;
                mySQLDataBases.clear();
                MySQLDataBase mySQLDataBase;
                for (int i = 0; i < ja.length(); i++) {
                    jo = ja.getJSONObject(i);
                    pid = jo.getInt("ProductId");
                    String productName = jo.getString("ProductName");
                    mySQLDataBase = new MySQLDataBase();

                    mySQLDataBase.setProductId(pid);

                    mySQLDataBase.setProductName(productName);

                    mySQLDataBases.add(mySQLDataBase);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {

            // productcrafts.addAll(productcrafts);

            final ArrayList<String> listItems3 = new ArrayList<>();
            for (int i = 0; i < mySQLDataBases.size(); i++) {
                listItems3.add(mySQLDataBases.get(i).getProductName());
            }
            adapter3 = new ArrayAdapter(AddGridProductTypeSizes.this, R.layout.spinner_layout3, R.id.txt3, listItems3);
            sp3.setAdapter(adapter3);
            adapter3.notifyDataSetChanged();
        }
    }*/
    @Override
    public void onClick(View v) {
        if (v == imageView) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Complete action using"), IMAGE_REQUEST_CODE);
        } else if (v == btnadd) {
            checkData();
            //uploadMultipart();
        }
    }
    private void checkData() {
        if (name.length() < 1 || Path.length() < 1|| brand.length() < 1 || color.length() < 1) {
            Toast.makeText(AddGridProductTypeSizes.this, "Fill All", Toast.LENGTH_SHORT).show();
        } else {
            uploadMultipart();
            Toast.makeText(this, "Successfully Completed", Toast.LENGTH_SHORT).show();
            name.setText("");
            Path.setText("");
            brand.setText("");
            color.setText("");
            imageView.setImageResource(R.mipmap.browseimage);
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

       /* sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View selectedItemView,
                                       int position, long id) {
                MySQLDataBase mySQLDataBase = (MySQLDataBase) mySQLDataBases.get(position);

                //  final int pid
                psid = mySQLDataBase.getProductSizeId();
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
                Toast.makeText(AddGridProductTypeSizes.this,
                        "Your Selected : Nothing",
                        Toast.LENGTH_SHORT).show();
            }

        });

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View selectedItemView,
                                       int position, long id) {
                MySQLDataBase mySQLDataBase = (MySQLDataBase) mySQLDataBases.get(position);


                //  final int pid
                ptid = mySQLDataBase.getProductTypeId();
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
                Toast.makeText(AddGridProductTypeSizes.this,
                        "Your Selected : Nothing",
                        Toast.LENGTH_SHORT).show();
            }

        });

        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View selectedItemView,
                                       int position, long id) {
                MySQLDataBase mySQLDataBase = (MySQLDataBase) mySQLDataBases.get(position);

                //  final int pid
                pid = mySQLDataBase.getProductId();
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
                Toast.makeText(AddGridProductTypeSizes.this,
                        "Your Selected : Nothing",
                        Toast.LENGTH_SHORT).show();
            }

        });*/
       /* if((caption.length()<1))
        {
            Toast.makeText(AddProductsTypes.this, "Please Enter Product Name",Toast.LENGTH_SHORT).show();
        }
        else {*/
       /* MySQLDataBase s = new MySQLDataBase();
        s.setName(namec);
        s.setBrand(brandc);
        s.setColor(colorc);
        s.setProductSizeId(psid);
        s.setProductTypeId(ptid);
        s.setProductId(pid);
        //s.setId(pid);*/
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

