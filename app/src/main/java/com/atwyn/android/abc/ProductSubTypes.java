package com.atwyn.android.abc;

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

//import static com.example.admin.abc.R.id.productsadd;

/**
 * Created by Geetha on 4/20/2017 for displaying sub menu for product types of main products.
 */

public class ProductSubTypes extends AppCompatActivity implements Serializable{
    ImageView back;
    Context c;
    int click=0;
    private boolean loggedIn = false;
   // private int productSubTypeId;
   // private String productSubTypeName;
    private int selectedProducttypeid;
    private String selectedProducttype;
    private static int selectedPid;
    private static String selectedPname;
    ArrayList<MySQLDataBase> mySQLDataBases1;
    ArrayList<MySQLDataBase> mySQLProTypes;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types_subtypes);
        LinearLayout ll = (LinearLayout) findViewById(R.id.products_subtype);
        final ListView lv = (ListView) findViewById(R.id.productTypeSubTypesLv);
        TextView typeNameTxt = (TextView) findViewById(R.id.SelProductTypeName);

        // Get intent data
        Intent intent = getIntent();
      /*  productSubTypeName = intent.getExtras().getString("PRODUCTSUBTYPENAME_KEY");
        productSubTypeId = intent.getExtras().getInt("PRODUCTSUBTYPEID_KEY");*/
        selectedPname = intent.getExtras().getString("PRODUCTNAME_KEY");
        selectedPid = intent.getExtras().getInt("PRODUCTID_KEY");
        selectedProducttype = intent.getExtras().getString("PRODUCTTYPE_KEY");
        selectedProducttypeid = intent.getExtras().getInt("PRODUCTTYPEID_KEY");
        mySQLDataBases1 = (ArrayList<MySQLDataBase>) intent.getSerializableExtra("ProductSubTypeList");

        mySQLProTypes = (ArrayList<MySQLDataBase>) intent.getSerializableExtra("ProductTypeList") ;

        typeNameTxt.setText(selectedProducttype);
        Log.d("result response: ", "> " + mySQLDataBases1);

        final ProductSubTypesListAdapter adapter = new ProductSubTypesListAdapter(this, mySQLDataBases1, selectedProducttypeid, selectedProducttype);
        lv.setAdapter(adapter);
        //typeNameTxt.setText(selectedProducttype);

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
                        Intent in = new Intent(ProductSubTypes.this, ProductTypes.class);
                        /*in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK *//*|
                                Intent.FLAG_ACTIVITY_NEW_TASK*//*);
                        in.putExtra("PRODUCTNAME_KEY", selectedPname);
                        in.putExtra("PRODUCTID_KEY", selectedPid);
                        in.putExtra("ProductTypeList", mySQLProTypes);
                        //startActivity(in);
                        setResult(Activity.RESULT_OK,in);
                        startActivity(in);*/
                        finish();
                        //ProductSubTypes.super.onBackPressed();
                    }
                }
            });
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ho);
            toolbar.setOverflowIcon(drawable);

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);
        //getMenuInflater().inflate(R.menu.mainproducts, menu);
        if (loggedIn) {
            /*MenuItem item = menu.findItem(R.id.productsadd);
            item.setVisible(true);
            MenuItem items = menu.findItem(R.id.productdelete);
            items.setVisible(true);
            MenuItem itemss = menu.findItem(R.id.logout);
            items.setVisible(true);
            MenuItem items2 = menu.findItem(R.id.h1);
            items2.setVisible(true);*/
            getMenuInflater().inflate(R.menu.mainproducts, menu);

        } else  {
            /*MenuItem item1 = menu.findItem(productsadd);
            item1.setVisible(false);
            MenuItem items = menu.findItem(R.id.productdelete);
            items.setVisible(false);
            MenuItem itemss = menu.findItem(R.id.logout);
            itemss.setVisible(false);
            MenuItem items2 = menu.findItem(R.id.h1);
            items2.setVisible(true);*/
            getMenuInflater().inflate(R.menu.home, menu);
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

                Intent in = new Intent(ProductSubTypes.this, AddProductsSubType.class);
              //  in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                        Intent.FLAG_ACTIVITY_NEW_TASK*/);
                in.putExtra("PRODUCTID_KEY", selectedPid);
                in.putExtra("PRODUCTNAME_KEY", selectedPname);
                in.putExtra("PRODUCTTYPEID_KEY", selectedProducttypeid);
                in.putExtra("PRODUCTTYPE_KEY", selectedProducttype);
                in.putExtra("ProductSubTypeList",mySQLDataBases1);
                in.putExtra("ProductTypeList",mySQLProTypes);
                startActivity(in);
                return true;
            }
        } else if (id == R.id.productdelete) {
            click = click + 1;
            if (click == 1) {
                click = 0;

                Intent in = new Intent(ProductSubTypes.this, DeleteProductSubTypes.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                        Intent.FLAG_ACTIVITY_NEW_TASK*/);
                in.putExtra("PRODUCTID_KEY", selectedPid);
                in.putExtra("PRODUCTNAME_KEY", selectedPname);
                in.putExtra("PRODUCTTYPEID_KEY", selectedProducttypeid);
                in.putExtra("PRODUCTTYPE_KEY", selectedProducttype);
                in.putExtra("ProductSubTypeList",mySQLDataBases1);
                in.putExtra("ProductTypeList",mySQLProTypes);
                startActivity(in);
                return true;
            }
        } else if (id == R.id.logout) {
            logout();
            return true;
        }
        else if(id==R.id.h1)
        {
            Intent inn = new Intent(ProductSubTypes.this, Main2Activity.class);
            //inn.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
               /* inn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);*/
            startActivity(inn);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ProductSubTypesListAdapter extends BaseAdapter {

        Context c;
        ArrayList<MySQLDataBase> mySQLDataBases;
        int finalProductTypeId;
        String finalProductType;
        LayoutInflater inflater;

        ProductSubTypesListAdapter(Context c, ArrayList<MySQLDataBase> mySQLDataBases, int selectdPtid, String selectdPtname) {
            this.c = c;
            this.mySQLDataBases = mySQLDataBases;
            this.finalProductTypeId = selectdPtid;
            this.finalProductType = selectdPtname;
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.producttypesubtypeimage_list_view, parent, false);
            }
            TextView typeNameTxt = (TextView) convertView.findViewById(R.id.txtSubTypePro);
            ImageView img = (ImageView) convertView.findViewById(R.id.imageSubTypePro);
            //BIND DATA
            MySQLDataBase mySQLDataBase = (MySQLDataBase) this.getItem(position);
            final int ProductSubTypeId = mySQLDataBase.getProductSubTypeId();
            final int ProductTypeId = mySQLDataBase.getProductTypeId();
            final String ProductSubTypeName = mySQLDataBase.getProductSubTypeName();
            final String ImageUrl = mySQLDataBase.getProductSubTypeImageUrl();
            final String finalUrl = Config.mainUrlAddress + ImageUrl;

            typeNameTxt.setText(mySQLDataBase.getProductSubTypeName());
            //IMG
            PicassoClient.downloadImage(c, finalUrl, img);

            // open new activity
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openProductSubTypesGridView(ProductSubTypeId,ProductSubTypeName);
                }
            });

            return convertView;
        }

        private void openProductSubTypesGridView(int recvProSubId, String recvProSubName) {
            click = click + 1;
            if (click == 1) {
                click = 0;

                Intent intent = new Intent(c, ProductSubTypeGridView.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("PRODUCTSUBTYPEID_KEY", recvProSubId);
                intent.putExtra("PRODUCTSUBTYPENAME_KEY", recvProSubName);
                intent.putExtra("PRODUCTID_KEY", selectedPid);
                intent.putExtra("PRODUCTNAME_KEY", selectedPname);
                intent.putExtra("PRODUCTTYPEID_KEY", selectedProducttypeid);
                intent.putExtra("PRODUCTTYPE_KEY", selectedProducttype);
                intent.putExtra("ProductSubTypeList",mySQLDataBases1);
                intent.putExtra("ProductTypeList",mySQLProTypes);
                c.startActivity(intent);
            }
        }
    }
    public void onBackPressed() {
        //finishAffinity();
        click = click + 1;
        if (click == 1) {
            click = 0;
            Intent in = new Intent(ProductSubTypes.this, ProductTypes.class);
            /*in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK *//*|
                    Intent.FLAG_ACTIVITY_NEW_TASK*//*);
            in.putExtra("PRODUCTNAME_KEY", selectedPname);
            in.putExtra("PRODUCTID_KEY", selectedPid);
            in.putExtra("ProductTypeList", mySQLProTypes);
            //startActivity(in);
            setResult(Activity.RESULT_OK,in);
            startActivity(in);*/
            finish();
            super.onBackPressed();
        }

    }
    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        //alertDialogBuilder.setMessage("Are you sure you want to logout?");
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
                        editor.apply();

                        //Starting login activity
                        Intent intent = new Intent(ProductSubTypes.this, MainActivity.class);
                       // intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
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








