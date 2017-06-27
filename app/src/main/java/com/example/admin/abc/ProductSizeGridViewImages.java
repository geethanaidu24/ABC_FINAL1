package com.example.admin.abc;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.admin.abc.R.id.productsadd;

/**
 * Created by Geetha on 4/21/2017.
 */

public class ProductSizeGridViewImages extends AppCompatActivity {
    ImageView back;
    Context c;
    private boolean loggedIn = false;
    final static String url =Config.productSizeImgUrlAddress;
    private static int productId,productSizeId;
    private static String selProductName,selFinalProSize;
    private static int selProWidth,selProLength,selProHeight;
    int click=0;
    ArrayList<MySQLDataBase> mySQLDataBases1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
       //  getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types_sizes_images);

        final GridView gv = (GridView) findViewById(R.id.gv);

        // Get intent data in.putExtra("PRODUCTID_KEY", recivedProductId);

        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity
        productId = intent.getExtras().getInt("PRODUCTID_KEY");
        selProductName = intent.getExtras().getString("PRODUCTNAME_KEY");
        productSizeId = intent.getExtras().getInt("PRODUCTSIZEID_KEY");
        selFinalProSize = intent.getExtras().getString("FINALPROSELSIZE_KEY");
        selProLength = intent.getExtras().getInt("PRODUCTSIZELENGTH_KEY");
        selProWidth = intent.getExtras().getInt("PRODUCTSIZEWIDTH_KEY");
        selProHeight = intent.getExtras().getInt("PRODUCTSIZEHEIGHT_KEY");
        mySQLDataBases1 = (ArrayList<MySQLDataBase>) intent.getSerializableExtra("ProductSizeList");
        Uri builtUri = Uri.parse(url)
                .buildUpon()
                .appendQueryParameter(Config.PRODUCTID_PARAM, Integer.toString(productId))
                .appendQueryParameter(Config.PRODUCTSIZEID_PARAM, Integer.toString(productSizeId))
                .build();
        URL urlAddress = null;
        try {
            urlAddress = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        new ProductSizeImagesDownloader(ProductSizeGridViewImages.this,urlAddress,gv,productId,productSizeId).execute();

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
                        Intent in = new Intent(ProductSizeGridViewImages.this, ProductSizes.class);
                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                                Intent.FLAG_ACTIVITY_NEW_TASK*/);
                        in.putExtra("PRODUCTID_KEY", productId);
                        in.putExtra("PRODUCTNAME_KEY", selProductName);
                        in.putExtra("ProductSizeList",mySQLDataBases1);
                       setResult(Activity.RESULT_OK,in);
                        startActivity(in);
                        finish();

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
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, true);
        //getMenuInflater().inflate(R.menu.mainproducts, menu);
        if (loggedIn == true) {
          /*  MenuItem item = menu.findItem(R.id.productsadd);
            item.setVisible(true);
            MenuItem items = menu.findItem(R.id.productdelete);
            items.setVisible(true);
            MenuItem itemss = menu.findItem(R.id.logout);
            items.setVisible(true);
            MenuItem items2 = menu.findItem(R.id.h1);
            items2.setVisible(true);*/
            getMenuInflater().inflate(R.menu.mainproducts, menu);


        } else if (loggedIn == false) {
          /*  MenuItem item1 = menu.findItem(productsadd);
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

                Intent in = new Intent(ProductSizeGridViewImages.this, AddGridProductSizes.class);
            //  in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                        Intent.FLAG_ACTIVITY_NEW_TASK*/);
                in.putExtra("PRODUCTID_KEY", productId);
                in.putExtra("PRODUCTNAME_KEY", selProductName);
                in.putExtra("PRODUCTSIZEID_KEY", productSizeId);
                in.putExtra("FINALPROSELSIZE_KEY", selFinalProSize);
                in.putExtra("PRODUCTSIZEWIDTH_KEY", selProWidth);
                in.putExtra("PRODUCTSIZELENGTH_KEY", selProLength);
                in.putExtra("PRODUCTSIZEHEIGHT_KEY", selProHeight);
                in.putExtra("ProductSizeList",mySQLDataBases1);
                startActivity(in);
                return true;
            }
        } else if (id == R.id.productdelete) {
            click = click + 1;
            if (click == 1) {
                click = 0;

                Intent inn = new Intent(ProductSizeGridViewImages.this, DeleteGridProductSizes.class);
            //  inn.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                inn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                        Intent.FLAG_ACTIVITY_NEW_TASK*/);
                inn.putExtra("PRODUCTID_KEY", productId);
                inn.putExtra("PRODUCTNAME_KEY", selProductName);
                inn.putExtra("PRODUCTSIZEID_KEY", productSizeId);
                inn.putExtra("FINALPROSELSIZE_KEY", selFinalProSize);
                inn.putExtra("PRODUCTSIZEWIDTH_KEY", selProWidth);
                inn.putExtra("PRODUCTSIZELENGTH_KEY", selProLength);
                inn.putExtra("PRODUCTSIZEHEIGHT_KEY", selProHeight);
                inn.putExtra("ProductSizeList",mySQLDataBases1);
                startActivity(inn);
                return true;
            }
        } else if (id == R.id.logout) {
            logout();
            return true;
        }
        else if(id==R.id.h1)
        {
            Intent inn = new Intent(ProductSizeGridViewImages.this, Main2Activity.class);
            //inn.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
               /* inn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);*/
            startActivity(inn);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        //finishAffinity();
        click = click + 1;
        if (click == 1) {
            click = 0;
            Intent in = new Intent(ProductSizeGridViewImages.this, ProductSizes.class);

            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                                Intent.FLAG_ACTIVITY_NEW_TASK*/);
            in.putExtra("PRODUCTID_KEY", productId);
            in.putExtra("PRODUCTNAME_KEY", selProductName);
            in.putExtra("ProductSizeList",mySQLDataBases1);
            setResult(Activity.RESULT_OK,in);
            startActivity(in);
            finish();
            super.onBackPressed();
        }


    }


    private class ProductSizeImagesDownloader extends AsyncTask<Void, Void, String> {

        Context c;
        URL urlAddress;
        GridView gv;
        int pid,psid;

        private ProductSizeImagesDownloader(Context c, URL urlAddress, GridView gv, int pid,int psid) {
            this.c = c;
            this.urlAddress = urlAddress;
            this.gv = gv;
            this.pid = pid;
            this.psid =psid;

            Log.d("newActivity url: ", "> " + urlAddress);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            String data = downloadTypeData();
            return data;

        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s==null)
            { Toast toast = Toast.makeText(c, "Coming Soon...", Toast.LENGTH_SHORT);

                View toastView = toast.getView();
                toastView.setBackgroundResource(R.drawable.toast_drawable);
                toast.show();
               // Toast.makeText(c,"Coming Soon...",Toast.LENGTH_SHORT).show();
            }else {
                //CALL DATA PARSER TO PARSE
                ProductSizeImagesDataParser parser=new ProductSizeImagesDataParser(c, gv, s,pid,psid);
                parser.execute();
            }
        }
        private String downloadTypeData() {
            HttpURLConnection con = Connector.connect(String.valueOf(urlAddress));
            if (con == null) {
                return null;
            }
            try {
                InputStream is = new BufferedInputStream(con.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer jsonData = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    jsonData.append(line + "n");
                }
                br.close();
                is.close();
                return jsonData.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

   private class ProductSizeImagesDataParser extends AsyncTask<Void,Void,Integer> {
        Context c;
        GridView gv;
        String jsonData;
        int pid,psid;

        ArrayList<MySQLDataBase> mySQLDataBases=new ArrayList<>();

        private ProductSizeImagesDataParser(Context c, GridView gv, String jsonData,int pid,int psid) {
            this.c = c;
            this.gv = gv;
            this.jsonData = jsonData;
            this.pid = pid;
            this.psid = psid;


        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Integer doInBackground(Void... params) {
            return this.parseSizeImagesData();
        }
        @Override

        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            if(result==0)
            { click = click + 1;
                if (click == 1) {
                    click = 0;
                    Toast toast = Toast.makeText(c, "No Collection Available", Toast.LENGTH_SHORT);

                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.show();
                  //  Toast.makeText(c, "No Collection Available", Toast.LENGTH_SHORT).show();
                }
            }else
            {

                final ProductSizeImagesGirdAdapter adapter=new ProductSizeImagesGirdAdapter(c,mySQLDataBases,pid,psid);
                gv.setAdapter(adapter);

            }
        }
        private int parseSizeImagesData()
        {
            try
            {
                JSONArray ja=new JSONArray(jsonData);
                JSONObject jo=null;
                mySQLDataBases.clear();
                MySQLDataBase mySQLDataBase;
                for(int i=0;i<ja.length();i++)
                {
                    jo=ja.getJSONObject(i);
                    Log.d("result response: ", "> " + jo);

                    int ImageId=jo.getInt("ProductSizeImageId");
                    String Name =jo.getString("Name");
                    String ImageUrl=jo.getString("ImagePath");
                    String Brands = jo.getString("Brand");
                    String Color = jo.getString("Color");
                    int SizeId = jo.getInt("ProductSizeId");
                    int ProductSTypeId = jo.optInt("ProductSubTypeId", 0);
                    int ProductTypeId = jo.optInt("ProductTypeId",0);
                    int ProductId = jo.getInt("ProductId");
                    int SizeWidth = jo.getInt("Width");
                    int SizeHeight = jo.getInt("Height");
                    int SizeLength = jo.getInt("Length");
                    mySQLDataBase=new MySQLDataBase();
                    mySQLDataBase.setProductSizeImageId(ImageId);
                    mySQLDataBase.setName(Name);
                    mySQLDataBase.setImagePath(ImageUrl);
                    mySQLDataBase.setBrand(Brands);
                    mySQLDataBase.setColor(Color);
                    mySQLDataBase.setProductSizeId(SizeId);
                    mySQLDataBase.setProductSubTypeId(ProductSTypeId);
                    mySQLDataBase.setProductTypeId(ProductTypeId);
                    mySQLDataBase.setProductId(ProductId);
                    mySQLDataBase.setWidth(SizeWidth);
                    mySQLDataBase.setHeight(SizeHeight);
                    mySQLDataBase.setLength(SizeLength);
                    mySQLDataBases.add(mySQLDataBase);
                }
                return 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return 0;
        }
   }


   private class ProductSizeImagesGirdAdapter  extends BaseAdapter {
        Context c;

        ArrayList<MySQLDataBase> mySQLDataBases;
        LayoutInflater inflater;
        String finalSize;
        int pid,psid;

        private ProductSizeImagesGirdAdapter(Context c, ArrayList<MySQLDataBase> mySQLDataBases,int pid,int psid) {
            this.c = c;
            this.mySQLDataBases = mySQLDataBases;
            this.pid = pid;
            this.psid = psid;
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
                convertView = inflater.inflate(R.layout.gridview_layout, parent, false);
            }
            TextView typeNameTxt = (TextView) convertView.findViewById(R.id.txtTypeSizePro);
            ImageView img = (ImageView) convertView.findViewById(R.id.imgTypeSizePro);
            //BIND DATA
            MySQLDataBase mySQLDataBase = (MySQLDataBase) this.getItem(position);
            typeNameTxt.setText(mySQLDataBase.getName());
            //IMG
            final String url = mySQLDataBase.getImagePath();
            final String finalUrl=Config.mainUrlAddress + url;
            PicassoClient.downloadImage(c, finalUrl, img);
            //BIND DATA
            final String name = mySQLDataBase.getName();

            final String brand = mySQLDataBase.getBrand();
            final String color = mySQLDataBase.getColor();
            final int sizeid = mySQLDataBase.getProductSizeId();
            final int length =Integer.parseInt(String.valueOf(mySQLDataBase.getLength()).toString()) ;
            final int width = Integer.parseInt(String.valueOf(mySQLDataBase.getWidth()).toString());
            final int height = Integer.parseInt(String.valueOf(mySQLDataBase.getHeight()).toString());
            //final String measure =productTypeSizeDBData.getMeasurement().toString();

            if(length !=0 && width !=0 && height !=0){
                finalSize =  width + "X" + height + "X" + length;

            }else if(length ==0 && width !=0 && height !=0){
                finalSize =  width + "X" + height;

            }else if(length !=0 && width ==0 && height !=0){
                finalSize =  length + "X" + height;

            }else if(length !=0 && width !=0 && height ==0 ){
                finalSize =  length + "X" + width ;

            }else if(length ==0 && width !=0 && height ==0 ){
                finalSize = width + "" ;

            }else if(length !=0 && width ==0 && height ==0 ){
                finalSize = length + "" ;

            }else if(length ==0 && width ==0 && height !=0 ){
                finalSize = height + "" ;

            }

            // open new activity
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //open detail activity
                    openDetailActivity(pid,psid,name,url, brand, color,finalSize);
                }
            });
            return convertView;
        }

        private void openDetailActivity(int pid,int psid,String... details) {
            click = click + 1;
            if (click == 1) {
                click = 0;

                Intent i = new Intent(c, ProductSizeImageSingleViewFullDetails.class);
                i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                        Intent.FLAG_ACTIVITY_NEW_TASK*/);
                i.putExtra("PRODUCTNAME_KEY", selProductName);
                i.putExtra("FINALPROSELSIZE_KEY", selFinalProSize);
                i.putExtra("PRODUCTSIZEWIDTH_KEY", selProWidth);
                i.putExtra("PRODUCTSIZELENGTH_KEY", selProLength);
                i.putExtra("PRODUCTSIZEHEIGHT_KEY", selProHeight);
                i.putExtra("ProductSizeList",mySQLDataBases);
                i.putExtra("PRODUCTID_KEY", pid);
                i.putExtra("PRODUCTSIZEID_KEY", psid);
                i.putExtra("NAME_KEY", details[0]);
                i.putExtra("IMAGE_KEY", details[1]);
                i.putExtra("BRAND_KEY", details[2]);
                i.putExtra("COLOR_KEY", details[3]);
                i.putExtra("SIZE_KEY", details[4]);
                c.startActivity(i);
            }
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
                        Intent intent = new Intent(ProductSizeGridViewImages.this, MainActivity.class);
                        //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
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
