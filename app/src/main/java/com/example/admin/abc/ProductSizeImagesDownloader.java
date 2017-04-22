package com.example.admin.abc;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Atwyn on 4/21/2017.
 */

class ProductSizeImagesDownloader extends AsyncTask<Void, Void, String> {

    Context c;
    URL urlAddress;
    GridView gv;
    int pid,psid;
    String psize;

    public ProductSizeImagesDownloader(Context c, URL urlAddress, GridView gv, int pid,int psid,String psize) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.gv = gv;
        this.pid = pid;
        this.psid =psid;
        this.psize = psize;

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
        {
            Toast.makeText(c,"Unsuccessful,Null returned",Toast.LENGTH_SHORT).show();
        }else {
            //CALL DATA PARSER TO PARSE
            ProductSizeImagesDataParser parser=new ProductSizeImagesDataParser(c, gv, s,pid,psid,psize);
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
