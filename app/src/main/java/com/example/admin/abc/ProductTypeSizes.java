package com.example.admin.abc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Geetha on 4/14/2017 for opening Product Types Size activity based on user clicked product .
 */

public class ProductTypeSizes extends AppCompatActivity implements Serializable{
    int click=0;
    ImageView back;
    Context c;
    private boolean loggedIn = false;
    private static int selectedProdutId, selectedProdutTypeId;
    private static String selectedProductName, selectedProductType;
    ArrayList<MySQLDataBase> mySQLDataBases;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //  getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types_sizes);

        LinearLayout ll1 = (LinearLayout) findViewById(R.id.productTypes_size);

        final ListView lv = (ListView) findViewById(R.id.productTypeSizesLv);

        // Get intent data
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity

        selectedProdutId = intent.getExtras().getInt("PRODUCTID_KEY");
        selectedProductName = intent.getExtras().getString("PRODUCTNAME_KEY");
        selectedProdutTypeId = intent.getExtras().getInt("PRODUCTTYPEID_KEY");
        selectedProductType = intent.getExtras().getString("PRODUCTTYPE_KEY");
         mySQLDataBases = (ArrayList<MySQLDataBase>) intent.getSerializableExtra("ProductTypeSizeList");

        final ProductTypeSizesListAdapter adapter = new ProductTypeSizesListAdapter(this, mySQLDataBases, selectedProdutId, selectedProdutTypeId);
        lv.setAdapter(adapter);
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
                        Intent in = new Intent(ProductTypeSizes.this, ProductTypes.class);
                        finish();
                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                    }
                }
            });
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.dots);
            toolbar.setOverflowIcon(drawable);

        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);
        getMenuInflater().inflate(R.menu.mainproducts, menu);
        if (loggedIn == true) {
            MenuItem item = menu.findItem(R.id.productsadd);
            item.setVisible(true);
            MenuItem items = menu.findItem(R.id.productdelete);
            items.setVisible(true);
            MenuItem itemss = menu.findItem(R.id.logout);
            items.setVisible(true);

        } else if (loggedIn == false) {
            return false;
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.productsadd) {
            click = click + 1;
            if (click == 1) {
                click = 0;

                Intent intent = new Intent(ProductTypeSizes.this, AddProductTypeSizes.class);
               // intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("PRODUCTID_KEY", selectedProdutId);
                intent.putExtra("PRODUCTNAME_KEY", selectedProductName);
                intent.putExtra("PRODUCTTYPEID_KEY", selectedProdutTypeId);
                intent.putExtra("PRODUCTTYPE_KEY", selectedProductType);
                intent.putExtra("ProductTypeSizeList",mySQLDataBases);
                startActivity(intent);
                return true;
            }
        } else if (id == R.id.productdelete) {
            click = click + 1;
            if (click == 1) {
                click = 0;

                Intent inn = new Intent(ProductTypeSizes.this, DeleteProductTypeSizes.class);
                //inn.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                inn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                inn.putExtra("PRODUCTID_KEY", selectedProdutId);
                inn.putExtra("PRODUCTTYPEID_KEY", selectedProdutTypeId);
                startActivity(inn);

                return true;
            }
        } else if (id == R.id.logout) {
            logout();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        //finishAffinity();
        click = click + 1;
        if (click == 1) {
            click = 0;
            Intent in = new Intent(ProductTypeSizes.this, ProductTypes.class);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
        }



    }

    private class ProductTypeSizesListAdapter extends BaseAdapter {

        Context c;
        ArrayList<MySQLDataBase> mySQLDataBases;
        LayoutInflater inflater;

        String finalSize;
        int pid,ptid;

        private ProductTypeSizesListAdapter(Context c, ArrayList<MySQLDataBase> mySQLDataBases,int pid,int ptid) {
            this.c = c;
            this.mySQLDataBases = mySQLDataBases;
            this.pid = pid;
            this.ptid=ptid;
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return mySQLDataBases.size();
        }
        @Override
        public Object getItem(int position) {
            return mySQLDataBases.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null)
            {
                convertView=inflater.inflate(R.layout.producttypesize_list_view,parent,false);
            }
            TextView typeNameTxt= (TextView) convertView.findViewById(R.id.productTypeSize);

            //BIND DATA
            MySQLDataBase mySQLDataBase = (MySQLDataBase) this.getItem(position);

            final int sizeid = mySQLDataBase.getProductSizeId();
            final int proid = mySQLDataBase.getProductId();
            final int protid = mySQLDataBase.getProductTypeId();
            final int length =Integer.parseInt(String.valueOf(mySQLDataBase.getLength()).toString()) ;
            final int width = Integer.parseInt(String.valueOf(mySQLDataBase.getWidth()).toString());
            final int height = Integer.parseInt(String.valueOf(mySQLDataBase.getHeight()).toString());
            //final String measure =productTypeSizeDBData.getMeasurement().toString();

            if(length !=0 && width !=0 && height !=0){
                finalSize =  width + "X" + height + "X" + length;
                typeNameTxt.setText(String.valueOf(finalSize));
            }else if(length ==0 && width !=0 && height !=0){
                finalSize =  width + "X" + height;
                typeNameTxt.setText(String.valueOf(finalSize));
            }else if(length !=0 && width ==0 && height !=0){
                finalSize =  length + "X" + height;
                typeNameTxt.setText(String.valueOf(finalSize));
            }else if(length !=0 && width !=0 && height ==0 ){
                finalSize =  length + "X" + width ;
                typeNameTxt.setText(String.valueOf(finalSize));
            }else if(length ==0 && width !=0 && height ==0 ){
                finalSize = width + "" ;
                typeNameTxt.setText(finalSize);
            }else if(length !=0 && width ==0 && height ==0 ){
                finalSize = length + "" ;
                typeNameTxt.setText(finalSize);
            }else if(length ==0 && width ==0 && height !=0 ){
                finalSize = height + "" ;
                typeNameTxt.setText(finalSize);
            }
            // open new activity
            convertView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    openProductTypeSizeImagesActivity(sizeid,length,width,height,finalSize);
                }
            });

            return convertView;
        }

        public void openProductTypeSizeImagesActivity(int sizeid,int length,int width, int height,String finalSize){
            click = click + 1;
            if (click == 1) {
                click = 0;

                Intent intent = new Intent(c, ProductTypeSizeImagesGridView.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("PRODUCTID_KEY", selectedProdutId);
                intent.putExtra("PRODUCTNAME_KEY", selectedProductName);
                intent.putExtra("PRODUCTTYPEID_KEY", selectedProdutTypeId);
                intent.putExtra("PRODUCTTYPE_KEY", selectedProductType);
                intent.putExtra("PRODUCTTYPESIZEID_KEY", sizeid);
                intent.putExtra("WIDTH_KEY", width);
                intent.putExtra("LENGTH_KEY", length);
                intent.putExtra("HEIGHT_KEY", height);
                intent.putExtra("FINALSIZE_KEY", finalSize);
                c.startActivity(intent);
            }
        }
    }

    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
       // alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setTitle("Are you sure you want to logout?");
        alertDialogBuilder.setIcon(R.drawable.logoutt);
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Config.KEY_USER, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(ProductTypeSizes.this, MainActivity.class);
                        //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        intent.putExtra("finish",true);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

}

