package com.example.admin.abc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import java.util.ArrayList;

/**
 * Created by Geetha on 4/20/2017 for displaying main product sizes.
 */

public class ProductSizes extends AppCompatActivity {
    ImageView back;
    Context c;
    private boolean loggedIn = false;

    final static String url = Config.productSizesUrlAddress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_sizes);

        LinearLayout ll = (LinearLayout) findViewById(R.id.products_size);

        final ListView lv = (ListView) findViewById(R.id.productSizesLv);

        // Get intent data
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity

        final int pid = intent.getExtras().getInt("PRODUCTID_KEY");
        Log.d("result PID: ", "> " + pid);

        String urlAddress = url + pid;

        new ProductSizesDownloader(ProductSizes.this, urlAddress, lv, ll, pid).execute();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(ProductSizes.this, Products.class);
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
            Intent in = new Intent(ProductSizes.this, AddProductSizes.class);

            startActivity(in);
            return true;
        } else if (id == R.id.productdelete) {
            Intent inn = new Intent(ProductSizes.this, DeleteProductSizes.class);
            startActivity(inn);

            return true;
        } else if (id == R.id.logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ProductSizesDownloader extends AsyncTask<Void, Void, String> {
        Context c;
        String urlAddress;
        ListView lv;
        LinearLayout ll;
        int pid;

        private ProductSizesDownloader(Context c, String urlAddress, ListView lv, LinearLayout ll, int pid) {
            this.c = c;
            this.urlAddress = urlAddress;
            this.lv = lv;
            this.ll = ll;
            this.pid = pid;
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
            if (s == null) {
                Toast.makeText(c, "Unsuccessful,Null returned", Toast.LENGTH_SHORT).show();
            } else {
                //CALL DATA PARSER TO PARSE
                ProductSizesDataParser parser = new ProductSizesDataParser(c, lv, ll, s, pid);
                parser.execute();
            }
        }

        private String downloadTypeData() {
            HttpURLConnection con = Connector.connect(urlAddress);
            if (con == null) {
                return null;
            }
            try {
                InputStream sizesReader = new BufferedInputStream(con.getInputStream());
                BufferedReader sizeBufferReader = new BufferedReader(new InputStreamReader(sizesReader));
                String line;
                StringBuffer jsonData = new StringBuffer();
                while ((line = sizeBufferReader.readLine()) != null) {
                    jsonData.append(line + "n");
                }
                sizeBufferReader.close();
                sizesReader.close();
                return jsonData.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class ProductSizesDataParser extends AsyncTask<Void, Void, Integer> {
        Context c;
        ListView lv;
        LinearLayout ll;
        String jsonData;
        int pid;

        ArrayList<ProductTypeSizeDBData> productTypeSizeDBDatas = new ArrayList<>();

        private ProductSizesDataParser(Context c, ListView lv, LinearLayout ll, String jsonData, int pid) {
            this.c = c;
            this.lv = lv;
            this.ll = ll;
            this.jsonData = jsonData;
            this.pid = pid;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            return this.parseSizesData();
        }

        @Override

        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            if (result == 0) {
                Toast.makeText(c, "No Data For required Product", Toast.LENGTH_SHORT).show();
            } else {

                final ProductSizesListAdapter adapter = new ProductSizesListAdapter(c, productTypeSizeDBDatas, pid);
                lv.setAdapter(adapter);
            }
        }

        private int parseSizesData() {
            try {
                JSONArray sizeArray = new JSONArray(jsonData);
                JSONObject sizeObject = null;
                productTypeSizeDBDatas.clear();
                ProductTypeSizeDBData productTypeSizeDBData;
                for (int i = 0; i < sizeArray.length(); i++) {
                    sizeObject = sizeArray.getJSONObject(i);
                    Log.d("result response: ", "> " + sizeObject);
                    int ProductSizeId = sizeObject.getInt("ProductSizeId");
                    int Width = sizeObject.getInt("Width");
                    int Height = sizeObject.getInt("Height");
                    int Length = sizeObject.getInt("Length");

                    // String Measure =jo.getString("Measurement");
                    int ProductTypeId = sizeObject.optInt("ProductTypeId", 0);
                    // int ProductTypeId=jo.getInt("ProductTypeId");
                    int ProductId = sizeObject.getInt("ProductId");
                    productTypeSizeDBData = new ProductTypeSizeDBData();

                    productTypeSizeDBData.setProductSizeId(ProductSizeId);
                    productTypeSizeDBData.setWidth(Width);
                    productTypeSizeDBData.setHeight(Height);
                    productTypeSizeDBData.setLength(Length);

                    //productTypeSizeDBData.setMeasurement(Measure);
                    productTypeSizeDBData.setProductTypeId(ProductTypeId);
                    productTypeSizeDBData.setProductId(ProductId);
                    productTypeSizeDBDatas.add(productTypeSizeDBData);
                }
                return 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }

    private class ProductSizesListAdapter extends BaseAdapter {

        Context c;
        ArrayList<ProductTypeSizeDBData> productTypeSizeDBDatas;
        LayoutInflater inflater;
        int pid;
        String finalSize;

        private ProductSizesListAdapter(Context c, ArrayList<ProductTypeSizeDBData> productTypeSizeDBDatas, int pid) {
            this.c = c;
            this.productTypeSizeDBDatas = productTypeSizeDBDatas;
            this.pid = pid;
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return productTypeSizeDBDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return productTypeSizeDBDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.productsize_list_view, parent, false);
            }
            TextView typeNameTxt = (TextView) convertView.findViewById(R.id.productSize);

            //BIND DATA
            ProductTypeSizeDBData productTypeSizeDBData = (ProductTypeSizeDBData) this.getItem(position);

            final int sizeid = productTypeSizeDBData.getProductSizeId();
            final int length = Integer.parseInt(String.valueOf(productTypeSizeDBData.getLength()).toString());
            final int width = Integer.parseInt(String.valueOf(productTypeSizeDBData.getWidth()).toString());
            final int height = Integer.parseInt(String.valueOf(productTypeSizeDBData.getHeight()).toString());
            //final String measure =productTypeSizeDBData.getMeasurement().toString();

            if (length != 0 && width != 0 && height != 0) {
                finalSize = width + "X" + height + "X" + length;
                typeNameTxt.setText(String.valueOf(finalSize));
            } else if (length == 0 && width != 0 && height != 0) {
                finalSize = width + "X" + height;
                typeNameTxt.setText(String.valueOf(finalSize));
            } else if (length != 0 && width == 0 && height != 0) {
                finalSize = length + "X" + height;
                typeNameTxt.setText(String.valueOf(finalSize));
            } else if (length != 0 && width != 0 && height == 0) {
                finalSize = length + "X" + width;
                typeNameTxt.setText(String.valueOf(finalSize));
            } else if (length == 0 && width != 0 && height == 0) {
                finalSize = width + "";
                typeNameTxt.setText(finalSize);
            } else if (length != 0 && width == 0 && height == 0) {
                finalSize = length + "";
                typeNameTxt.setText(finalSize);
            } else if (length == 0 && width == 0 && height != 0) {
                finalSize = height + "";
                typeNameTxt.setText(finalSize);
            }
            // open new activity
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openProductSizeImagesActivity(pid, sizeid);
                }
            });

            return convertView;
        }

        public void openProductSizeImagesActivity(int pid, int sizeid) {
            Intent intent = new Intent(c, ProductSizeGridViewImages.class);
            intent.putExtra("PRODUCTID_KEY", pid);
            intent.putExtra("PRODUCTSIZEID_KEY", sizeid);
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
                        Intent intent = new Intent(ProductSizes.this, MainActivity.class);
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

