package com.example.admin.abc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class DeleteContact extends AppCompatActivity {
    final ArrayList<MySQLDataBase> mySQLDataBases = new ArrayList<>();
    private Spinner sp,sp1;
    private Button btnAdd;
    private ArrayAdapter<MySQLDataBase> adapter ;
    private static final String DATA_DELETE_URL=Config.contactsCRUD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(DeleteContact.this, Main2Activity.class);
                    //finish();
                    startActivity(in);
                }
            });

        }
        this.initializeViews();
    }
    private void initializeViews()
    {
        btnAdd= (Button) findViewById(R.id.contactdelete);
        sp= (Spinner) findViewById(R.id.cityspinner);
        //sp.setPrompt("Select One");

    }
    /*
    HANDLE CLICK EVENTS
     */
    private void handleClickEvents(final int deleteContactId)
    {
        //EVENTS : ADD
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //SAVE
                MySQLDataBase s=new MySQLDataBase();
                s.setContactId(deleteContactId);
                if(s==null)
                {
                    Toast.makeText(DeleteContact.this, "No Data To Delete", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    AndroidNetworking.post(DATA_DELETE_URL)
                            .addBodyParameter("action","delete")
                            .addBodyParameter("contactid", String.valueOf(s.getContactId()))
                            .setTag("TAG_ADD")
                            .build()
                            .getAsJSONArray(new JSONArrayRequestListener() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    if(response != null)
                                        try {
                                            //SHOW RESPONSE FROM SERVER
                                            String responseString = response.get(0).toString();
                                            Toast.makeText(DeleteContact.this, "PHP SERVER RESPONSE : " + responseString, Toast.LENGTH_SHORT).show();
                                            if (responseString.equalsIgnoreCase("Successfully Deleted")) {
                                                Intent intent = new Intent(DeleteContact.this,DeleteContact.class);

                                                startActivity(intent);
   /* adapter.notifyDataSetChanged();
    BackTask bt = new BackTask();
    bt.execute();*/
                                            }else {
                                                Toast.makeText(DeleteContact.this, responseString, Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(DeleteContact.this, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIVED : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                }
                                //ERROR
                                @Override
                                public void onError(ANError anError) {
                                    Toast.makeText(DeleteContact.this, "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }

            }
        });

    }
    public void onStart() {
        super.onStart();
        BackTask bt = new BackTask();
        bt.execute();
    }

    private class BackTask extends AsyncTask<Void, Void, Void> {
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Void doInBackground(Void... params) {
            InputStream is = null;
            String result = "";
            try {
                org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(Config.contactsUrlAddress);
                org.apache.http.HttpResponse response = httpclient.execute(httppost);
                org.apache.http.HttpEntity entity = response.getEntity();
                // Get our response as a String.
                is = entity.getContent();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //convert response to string
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                is.close();
                //result=sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // parse json data
            try {
                JSONArray ja = new JSONArray(result);
                JSONObject jo=null;
                mySQLDataBases.clear();
                MySQLDataBase mySQLDataBase;
                for (int i = 0; i < ja.length(); i++) {
                    jo=ja.getJSONObject(i);
                    // add interviewee name to arraylist
                    int contactId=jo.getInt("ContactId");
                    String city = jo.getString("City");
                    String branch =jo.getString("Branch");
                    mySQLDataBase=new MySQLDataBase();
                    mySQLDataBase.setContactId(contactId);
                    mySQLDataBase.setCity(city);
                    mySQLDataBase.setBranch(branch);
                    mySQLDataBases.add(mySQLDataBase);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {

            final ArrayList<String> listItems = new ArrayList<>();

            listItems.add("Select One");
            for(int i=0;i<mySQLDataBases.size();i++){
                listItems.add(mySQLDataBases.get(i).getCity()+","+ mySQLDataBases.get(i).getBranch());

            }
            adapter=new ArrayAdapter(DeleteContact.this,R.layout.spinner_layout, R.id.txt,listItems);
            sp.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                public  void onItemSelected(AdapterView<?> arg0, View selectedItemView,
                                           int position, long id) {
                    if(sp.getSelectedItem().toString().equals("Select One")){
                        Toast.makeText(DeleteContact.this,
                                "Your Selected : Nothing",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        MySQLDataBase mySQLDataBase = (MySQLDataBase) mySQLDataBases.get(position-1);
                        final int contId = mySQLDataBase.getContactId();
                        final String selectedCity = mySQLDataBase.getCity();
                        Log.d(selectedCity,""+selectedCity);
                       /* BranchBackTask bt = new BranchBackTask(selectedCity);
                        bt.execute();*/
                        handleClickEvents(contId);
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                dialog.dismiss();
                            }
                        };
                    }
                }
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DeleteContact.this,
                            "Your Selected : Nothing",
                            Toast.LENGTH_SHORT).show();
                }

            });

        }
    }
    /*private class BranchBackTask extends AsyncTask<Void, Void, Void> {
        String FinalSelCity;
        public BranchBackTask(String selectedCity) {
            this.FinalSelCity=selectedCity;
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Void doInBackground(Void... params) {
            InputStream is = null;
            String result = "";
            try {
                org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(Config.contactsBranchSpin + FinalSelCity);
                org.apache.http.HttpResponse response = httpclient.execute(httppost);
                org.apache.http.HttpEntity entity = response.getEntity();
                // Get our response as a String.
                is = entity.getContent();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //convert response to string
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                is.close();
                //result=sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // parse json data
            try {
                JSONArray ja = new JSONArray(result);
                JSONObject jo=null;
                mySQLDataBases.clear();
                MySQLDataBase mySQLDataBase;
                for (int i = 0; i < ja.length(); i++) {
                    jo=ja.getJSONObject(i);
                    // add interviewee name to arraylist
                    int contactId2=jo.getInt("ContactId");
                    String branch2 =jo.getString("Branch");
                    mySQLDataBase=new MySQLDataBase();
                    mySQLDataBase.setContactId(contactId2);
                    mySQLDataBase.setBranch(branch2);
                    mySQLDataBases.add(mySQLDataBase);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            sp1 = (Spinner) findViewById(R.id.branchspinner);
            //sp1.setPrompt("Select One");
            sp1.setEnabled(true);
            final ArrayList<String> listItems1 = new ArrayList<>();
            listItems1.add("Select One");
            for(int i=0;i<mySQLDataBases.size();i++){
                listItems1.add(mySQLDataBases.get(i).getBranch());
            }

            adapter=new ArrayAdapter(DeleteContact.this,R.layout.spinner_layout, R.id.txt,listItems1);
            sp1.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView<?> arg0, View selectedItemView,
                                           int position, long id) {
                    if(sp1.getSelectedItem().toString().equals("Select One")){
                        Toast.makeText(DeleteContact.this,
                                "Your Selected : Nothing",
                                Toast.LENGTH_SHORT).show();
                    }else {
                    MySQLDataBase mySQLDataBase = (MySQLDataBase) mySQLDataBases.get(position-1);
                    final int contId2 =mySQLDataBase.getContactId() ;
                    final String selectedCity2 = mySQLDataBase.getCity();
                    final String selectedBranch2 = mySQLDataBase.getBranch();
                    Log.d("selected branch:",""+selectedBranch2 );
                    Log.d("selectd delete contact id:",""+contId2);
                    handleClickEvents(contId2);
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            // TODO Auto-generated method stub
                            dialog.dismiss();
                        }
                    };
                    }
                }
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DeleteContact.this,
                            "Your Selected : Nothing",
                            Toast.LENGTH_SHORT).show();
                }

            });

        } }*/


}
