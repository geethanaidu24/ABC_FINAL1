package com.atwyn.android.abc;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

//import static com.example.admin.abc.R.id.name8;

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
            finalSelProtypeSize =  finalProLength +" "+"X"+" " +finalProWidth  +" "+"X"+" " + finalProHeight;

        }else if(finalProLength ==0 && finalProWidth !=0 && finalProHeight !=0){
            finalSelProtypeSize =  finalProWidth + " "+"X"+" " + finalProHeight;

        }else if(finalProLength !=0 && finalProWidth ==0 && finalProHeight !=0){
            finalSelProtypeSize =  finalProLength + " "+"X"+" " + finalProHeight;

        }else if(finalProLength !=0 && finalProWidth !=0 && finalProHeight ==0 ){
            finalSelProtypeSize =  finalProLength + " "+"X"+" " + finalProWidth ;

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
                        /*in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK *//*|
                                Intent.FLAG_ACTIVITY_NEW_TASK*//*);
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
                        setResult(Activity.RESULT_OK,in);
                        startActivity(in);*/
                        finish();
                    }
                }
            });
            imageView = (ImageView) findViewById(R.id.image8);
            name = (EditText) findViewById(R.id.name8);
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
        /*in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK *//*|
                Intent.FLAG_ACTIVITY_NEW_TASK*//*);
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
        setResult(Activity.RESULT_OK,in);
        startActivity(in);*/
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
            alert.setTitle(Html.fromHtml("<font color='#ff0000'>Information!</font>"));
            alert.setMessage("To Refresh Newly added content Go Back to Home Screen..");
            alert.setIcon(R.drawable.reload);
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent in=new Intent(AddGridProductTypeSizes.this,AddGridProductTypeSizes.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                            Intent.FLAG_ACTIVITY_NEW_TASK*/);
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
                    setResult(Activity.RESULT_OK,in);
                    startActivity(in);
                    finish();
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
        String compressedPath = compressImage(path);


        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
            new MultipartUploadRequest(this, uploadId, addGridData)
                    .addParameter("action","save")
                    .addFileToUpload(compressedPath, "image") //Adding file
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
    private String compressImage(String path) {
        String filePath = getRealPathFromURI(path);
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

        float maxHeight = 816.0f;
        float maxWidth = 612.0f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;
            }
        }

//      setting inSampleSize value allows to load a scaled down version of the original image

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//          load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out = null;
        String filename = getFilename();
        try {
            out = new FileOutputStream(filename);

//          write the compressed bitmap at the destination specified by filename.
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return filename;

    }

    public String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "Images");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
        return uriSting;
    }

    private String getRealPathFromURI(String contentURI) {
        Uri contentUri = Uri.parse(contentURI);
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
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
