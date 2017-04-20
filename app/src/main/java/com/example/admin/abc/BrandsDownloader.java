package com.example.admin.abc;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by Admin on 4/18/2017.
 */

class BrandsDownloader extends AsyncTask<Void, Void, String> {

    Context c;
    String urlAddress;
    GridView gridView1;


    public BrandsDownloader(Context c, String urlAddress, GridView gridView1 ) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.gridView1 = gridView1;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {
        String data = downloadData();
        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s == null) {
            Toast.makeText(c, "Unsuccessful,Null returned", Toast.LENGTH_SHORT).show();
        } else {

            //CALL DATA PARSER TO PARSE
            BrandsDataParser parser = new BrandsDataParser(c, gridView1, s);
            parser.execute();
        }
    }

    private String downloadData() {
        HttpURLConnection con = Connector.connect(urlAddress);
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
