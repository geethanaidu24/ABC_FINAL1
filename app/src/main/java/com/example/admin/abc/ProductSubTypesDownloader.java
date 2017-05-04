package com.example.admin.abc;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by Geetha on 4/20/2017 for downloading data into json object from given url.
 */

public class ProductSubTypesDownloader extends AsyncTask<Void, Void, String> {

    Context c;
    String urlAddress;
    ListView lv;
    LinearLayout ll;
    int ptid;
    int pid;
    String pname,ptname;


    public ProductSubTypesDownloader(Context c, String urlAddress, ListView lv, LinearLayout ll,int pid, String pname, int ptid, String ptname) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.lv = lv;
        this.ll=ll;
        this.ptid = ptid;
        this.pid =pid;
        this.pname = pname;
        this.ptname = ptname;
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
            ProductSubTypesDataParser parser=new ProductSubTypesDataParser(c, lv, ll,s, pid, pname,ptid,ptname);
            parser.execute();
        }
    }
    private String downloadTypeData() {
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
