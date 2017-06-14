package com.example.admin.abc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.LruCache;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;


public class News extends AppCompatActivity {
    private LruCache<String, Bitmap> mMemoryCache;
    private boolean loggedIn = false;
    final static String newsUrlAddress = Config.newsUrlAddress;
    int click=0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        final ListView listView1 = (ListView) findViewById(R.id.newslist);

        new NewsDownloader(News.this, newsUrlAddress, listView1).execute();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(News.this, Main2Activity.class);
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

                Intent in = new Intent(News.this, AddNews.class);
                in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(in);
                return true;
            }
                } else if (id == R.id.productdelete) {
            click = click + 1;
            if (click == 1) {
                click = 0;

                Intent inn = new Intent(News.this, DeleteNews.class);
                inn.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
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
        Intent in = new Intent(News.this, Main2Activity.class);
        //startActivity(in);
        finish();
    }
    private void logout() {
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
                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Config.KEY_USER, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(News.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
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

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);

        // ATTENTION: This was auto-generated to implement the App Indexing API.


        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("News Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private class NewsDownloader extends AsyncTask<Void, Void, String> {

        Context c;
        String newsUrlAddress;
        ListView listView1;


        private NewsDownloader(Context c, String urlAddress, ListView listView1) {
            this.c = c;
            this.newsUrlAddress = urlAddress;
            this.listView1 = listView1;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            return downloadNewsData();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s == null) {
                Toast.makeText(c, "Unsuccessful,Null returned", Toast.LENGTH_SHORT).show();
            } else {
                //CALL DATA PARSER TO PARSE
                NewsDataParser parser = new NewsDataParser(c, listView1, s);
                parser.execute();
            }
        }

        private String downloadNewsData() {
            HttpURLConnection con = Connector.connect(newsUrlAddress);
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
    private class NewsDataParser extends AsyncTask<Void, Void, Integer> {
        Context c;
        ListView listView1;
        String jsonData;

        ArrayList<MySQLDataBase> mySQLDataBases = new ArrayList<>();

        private NewsDataParser(Context c, ListView listView1, String jsonData) {
            this.c = c;
            this.listView1 = listView1;
            this.jsonData = jsonData;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            return this.parseData();
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            if (result == 0) {
                Toast.makeText(c, "No Data, Add New", Toast.LENGTH_SHORT).show();
            } else {

                final NewsListAdapter adapter = new NewsListAdapter(c, mySQLDataBases);
                listView1.setAdapter(adapter);
            }
        }

        private int parseData() {
            try {
                JSONArray newsArray = new JSONArray(jsonData);
                JSONObject newsObject = null;
                mySQLDataBases.clear();
                MySQLDataBase mySQLDataBase;
                for (int i = 0; i < newsArray.length(); i++) {
                    newsObject = newsArray.getJSONObject(i);
                    Log.d("result response: ", "> " + newsObject);
                    int newsId = newsObject.getInt("NewsId");
                    String newsTitle = newsObject.getString("NewsTitle");
                    String newsDescription = newsObject.getString("Description");
                    String newsImageUrl = newsObject.getString("ImageUrl");
                    String createdDate = newsObject.getString("CreatedDate");
                    String createdAt = newsObject.getString("CreatedAt");
                    DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = null;
                    try {
                        date = iso8601Format.parse(createdDate);
                    } catch (ParseException e) {
                        Log.d("Parsing ISO8601 datetime failed", "" + e);
                    }

                    long when = date.getTime();
                    int flags = 0;
                  //  flags |= android.text.format.DateUtils.FORMAT_SHOW_TIME;
                    flags |= android.text.format.DateUtils.FORMAT_SHOW_DATE;
                    flags |= android.text.format.DateUtils.FORMAT_ABBREV_MONTH;
                  //  flags |= android.text.format.DateUtils.FORMAT_SHOW_YEAR;

                    String finalDateTime = android.text.format.DateUtils.formatDateTime(c,
                            when + TimeZone.getDefault().getOffset(when), flags);
                    mySQLDataBase = new MySQLDataBase();
                    mySQLDataBase.setNewsId(newsId);
                    mySQLDataBase.setNewsTitle(newsTitle);
                    mySQLDataBase.setNewsDescription(newsDescription);
                    mySQLDataBase.setNewsImageUrl(newsImageUrl);
                    mySQLDataBase.setDateTime(finalDateTime);
                    Log.d("datetime display", "" + finalDateTime);
                    mySQLDataBases.add(mySQLDataBase);

                }
                return 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
    private class NewsListAdapter extends BaseAdapter {

        Context c;
        ArrayList<MySQLDataBase> mySQLDataBases;
        LayoutInflater inflater;

        private NewsListAdapter(Context c, ArrayList<MySQLDataBase> mySQLDataBases) {
            this.c = c;
            this.mySQLDataBases = mySQLDataBases;
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
                convertView = inflater.inflate(R.layout.news_list_gridview, parent, false);
            }

            TextView newsDatetxt =(TextView) convertView.findViewById(R.id.newsDate);
            ImageView img = (ImageView) convertView.findViewById(R.id.newsgridimg);
            TextView newsTiletxt = (TextView) convertView.findViewById(R.id.newsTitle);
            Button readmore = (Button) convertView.findViewById(R.id.newsbutton);

            //BIND DATA
            MySQLDataBase mySQLDataBase = (MySQLDataBase) this.getItem(position);
            final String url = mySQLDataBase.getNewsImageUrl();
            final String finalUrl = Config.mainUrlAddress + url;
            final String newsDate = mySQLDataBase.getDateTime();
            final String newsTitle = mySQLDataBase.getNewsTitle();
            final String newsDescription = mySQLDataBase.getNewsDescription();
            //IMG
            PicassoClient.downloadImage(c, finalUrl, img);
            newsDatetxt.setText(newsDate);
            newsTiletxt.setText(newsTitle);
            readmore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openDetailNewsActivity(finalUrl,newsTitle,newsDescription);
                }
            });
            convertView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                   openDetailNewsActivity(finalUrl,newsTitle,newsDescription);
                }
            });

            return convertView;
        }
        private void openDetailNewsActivity(String...details){
            click = click + 1;
            if (click == 1) {
                click = 0;

                Intent intent = new Intent(c, NewsDescription.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("NEWSIMAGE_KEY", details[0]);
                intent.putExtra("NEWSTITLE", details[1]);
                intent.putExtra("NEWSDES_KEY", details[2]);
                c.startActivity(intent);
            }
        }
    }
}



