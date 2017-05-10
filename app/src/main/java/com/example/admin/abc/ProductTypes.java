package com.example.admin.abc;

/**
 * Created by Geetha on 4/10/2017 for opening Product types activity based on user clicked product .
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import java.util.ArrayList;

public class ProductTypes extends AppCompatActivity implements Serializable {
    ImageView back;
    Context c;
    private boolean loggedIn = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
       // getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types);
        LinearLayout ll = (LinearLayout) findViewById(R.id.products_type);
        final ListView lv = (ListView) findViewById(R.id.productTypesLv);
        TextView typeNameTxt = (TextView) findViewById(R.id.SelProductName);
        Intent intent = getIntent();
        final String selectedPname = intent.getExtras().getString("PRODUCTNAME_KEY");
        final int selectedPid = intent.getExtras().getInt("PRODUCTID_KEY");

        ArrayList<ProductTypesDB> productTypesDBs = (ArrayList<ProductTypesDB>) intent.getSerializableExtra("ProductTypeList");

        typeNameTxt.setText(selectedPname);
        Log.d("result response: ", "> " + productTypesDBs);

        final ProductTypesListAdapter adapter = new ProductTypesListAdapter(this, productTypesDBs, selectedPid, selectedPname);
        lv.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(ProductTypes.this, Products.class);
                    //startActivity(in);
                    finish();
                }
            });


            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.dots);
            toolbar.setOverflowIcon(drawable);

        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, true);
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
            Intent in = new Intent(ProductTypes.this, AddProductsTypes.class);

            startActivity(in);
            return true;
        } else if (id == R.id.productdelete) {
            Intent inn = new Intent(ProductTypes.this, DeleteProductTypes.class);
            startActivity(inn);

            return true;
        } else if (id == R.id.logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ProductTypesListAdapter extends BaseAdapter {

        Context c;
        ArrayList<ProductTypesDB> productTypesDBs;
        LayoutInflater inflater;
        String FinalPname;
        int FinalPid;
        private ProductTypesListAdapter(Context c, ArrayList<ProductTypesDB> productTypesDBs,int selectdPid, String selectdPname) {
            this.c = c;
            this.productTypesDBs = productTypesDBs;
            this.FinalPname = selectdPname;
            this.FinalPid=selectdPid;
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return productTypesDBs.size();
        }
        @Override
        public Object getItem(int position) {
            return productTypesDBs.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null)
            {
                convertView=inflater.inflate(R.layout.producttypeimage_list_view,parent,false);
            }
            TextView typeNameTypeTxt= (TextView) convertView.findViewById(R.id.textViewURL1);
            ImageView img= (ImageView) convertView.findViewById(R.id.imageTypePro);

            //BIND DATA
            ProductTypesDB productTypesDB=(ProductTypesDB) this.getItem(position);
            final int ProductTypeId = productTypesDB.getProductTypeId();
            final String ProductTypeName = productTypesDB.getProductType();
            final int ProductId = productTypesDB.getProductId();
            final String imageUrl = productTypesDB.getImageUrl();
            final String finalUrl=Config.mainUrlAddress + imageUrl;

            typeNameTypeTxt.setText(productTypesDB.getProductType());

            //IMG
            PicassoClient.downloadImage(c,finalUrl,img);

            // open new activity
            convertView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    openProductTypeSubTypesActivity(FinalPid,FinalPname,ProductTypeId,ProductTypeName);
                }
            });

            return convertView;
        }

        public void openProductTypeSubTypesActivity(int pid,String name,int ptid,String ptname){
            Intent intent = new Intent(c,ProductSubTypes.class);
            intent.putExtra("PRODUCTID_KEY",pid);
            intent.putExtra("PRODUCTNAME_KEY",name);
            intent.putExtra("PRODUCTTYPEID_KEY", ptid);
            intent.putExtra("PRODUCTTYPENAME_KEY",ptname);
            c.startActivity(intent);
        }
    }
    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
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
                        Intent intent = new Intent(ProductTypes.this, MainActivity.class);
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


