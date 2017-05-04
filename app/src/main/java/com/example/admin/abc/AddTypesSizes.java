package com.example.admin.abc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
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

public class AddTypesSizes extends AppCompatActivity {
    private EditText txtwidth, txtheight, txtlength;
    final ArrayList<SizesDB> productcrafts = new ArrayList<>();
    // ArrayAdapter<String> adapter;
    private Spinner sp1, sp2;
    private Button btnAdd;
    private ArrayAdapter<SizesDB> adapter;
    public int pid=0;
    public int ptid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_types_sizes);
        Toolbar actionbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != actionbar) {
            actionbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            actionbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(AddTypesSizes.this, ProductTypes.class);
                    startActivity(in);
                }
            });
            this.initializeViews();
            //HANDLE EVENTS
            // this.handleClickEvents();

        }
    }

    private void initializeViews() {
        txtwidth = (EditText) findViewById(R.id.wid);
        //txtPropellant= (TextInputEditText) findViewById(R.id.propellantTxt);
        txtheight = (EditText) findViewById(R.id.hei);
        txtlength = (EditText) findViewById(R.id.len);
        btnAdd = (Button) findViewById(R.id.addBtn);
        // btnRetrieve= (Button) findViewById(R.id.refreshBtn);
        sp1 = (Spinner) findViewById(R.id.spinner7);
        sp2 = (Spinner) findViewById(R.id.spinner8);
    }

    private void handleClickEvents(final int pid)
    {
        //EVENTS : ADD
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //GET VALUES
                String width=txtwidth.getText().toString();
                int wid=Integer.parseInt(width);
                // String propellant=txtPropellant.getText().toString();
                String height=txtheight.getText().toString();
                int heig=Integer.parseInt(height);

                String length=txtlength.getText().toString();
                int leng=Integer.parseInt(length);


                String spinSelVal = sp1.getSelectedItem().toString();
                String spinSelVal1=sp2.getSelectedItem().toString();


                final int sp1pid = pid;
                final int sp2ptid= ptid;

                //BASIC CLIENT SIDE VALIDATION
                if((width.length()<1 || height.length()<1 || length.length()<1 ))
                {
                    Toast.makeText(AddTypesSizes.this, "Please Enter all Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //SAVE
                    SizesDB s=new SizesDB();
                    s.setWidth(wid);
                    // s.setPname();
                    s.setHeight(heig);
                    s.getLength(leng);
                    s.setProductId(pid);

                    s.setProductTypeId(ptid);

                    new MySQLClient(AddTypesSizes.this).add(s,spinSelVal,spinSelVal1,txtwidth,txtheight,txtlength);
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
        ArrayList<String> list;

        protected void onPreExecute() {
            super.onPreExecute();
            list = new ArrayList<>();
        }

        protected Void doInBackground(Void... params) {
            InputStream is = null;
            String result = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://10.0.2.2/abc/getdb.php");
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
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
                productcrafts.clear();
                SizesDB productcraft;
                for (int i = 0; i < ja.length(); i++) {
                    jo=ja.getJSONObject(i);
                    // add interviewee name to arraylist
                    int pid = jo.getInt("ProductId");
                    String pname = jo.getString("ProductName");
                    int ptid=jo.getInt("ProductTypeId");
                    String pname1=jo.getString("ProductType");

                    productcraft= new SizesDB();
                    productcraft.setProductId(pid);
                    productcraft.setName(pname);
                    productcraft.setProductTypeId(ptid);
                    productcraft.setProductType(pname1);
                    productcrafts.add(productcraft);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {

            // productcrafts.addAll(productcrafts);
            final ArrayList<String> listItems = new ArrayList<>();
            for(int i=0;i<productcrafts.size();i++){
                listItems.add(productcrafts.get(i).getName());
            }
            //listItems.addAll(list);
            // SpinnerAdapter spinnerAdapter = new SpinnerAdapter(MainActivity.this,productcrafts);
            // sp.setAdapter(spinnerAdapter);
            adapter=new ArrayAdapter(AddTypesSizes.this,R.layout.spinner_layout5, R.id.txt,listItems);
            sp2.setAdapter(adapter);
            // adapter.notifyDataSetChanged();
            sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView<?> arg0, View selectedItemView,
                                           int position, long id) {
                    SizesDB productcraft = (SizesDB)productcrafts.get(position);
                    final String name = productcraft.getName();
                    //  final int pid
                    final int pid =productcraft.getProductId() ;
                    handleClickEvents(pid);
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            // TODO Auto-generated method stub
                            dialog.dismiss();
                        }
                    };
                }
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                    Toast.makeText(AddTypesSizes.this,
                            "Your Selected : Nothing",
                            Toast.LENGTH_SHORT).show();
                }

            });

            // productcrafts.addAll(productcrafts);
            final ArrayList<String> listItems1 = new ArrayList<>();
            for(int i=0;i<productcrafts.size();i++){
                listItems1.add(productcrafts.get(i).getProductType());
            }
            //listItems.addAll(list);
            // SpinnerAdapter spinnerAdapter = new SpinnerAdapter(MainActivity.this,productcrafts);
            // sp.setAdapter(spinnerAdapter);
            adapter=new ArrayAdapter(AddTypesSizes.this,R.layout.spinner_layout6, R.id.txt,listItems);
            sp1.setAdapter(adapter);
            // adapter.notifyDataSetChanged();
            sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView<?> arg0, View selectedItemView,
                                           int position, long id) {
                    SizesDB productcraft = (SizesDB)productcrafts.get(position);
                    final String name = productcraft.getProductType();
                    //  final int pid
                    final int pid =productcraft.getProductTypeId() ;
                    handleClickEvents(ptid);
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            // TODO Auto-generated method stub
                            dialog.dismiss();
                        }
                    };
                }
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                    Toast.makeText(AddTypesSizes.this,
                            "Your Selected : Nothing",
                            Toast.LENGTH_SHORT).show();
                }

            });

            /*sp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Productcraft productcraft=(Productcraft)adapter.getItem(position);
                    final String name=productcraft.getPRODUCTNAME();
                    final int pid=productcraft.getPRODUCTID();
                   // handleClickEvents(pid);
                   // Toast.makeText(MainActivity.this, sp.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

                }
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub

                }
            });*/

        }
    }

}
