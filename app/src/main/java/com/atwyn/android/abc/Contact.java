package com.atwyn.android.abc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

import static com.atwyn.android.abc.R.id.productsadd;

//import static com.example.admin.abc.R.id.productsadd;

public class Contact extends AppCompatActivity {
int click=0;
    private boolean loggedIn = false;
    final static String contactsAddress = Config.contactsUrlAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        final ListView lv = (ListView) findViewById(R.id.contactlist);

        new ContactsDownloader(Contact.this, contactsAddress, lv).execute();
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
                        Intent in = new Intent(Contact.this, Main2Activity.class);

                       /* in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);

                         startActivity(in);*/
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
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);
       // getMenuInflater().inflate(R.menu.mainproducts, menu);
        if (loggedIn) {
            /*MenuItem item = menu.findItem(productsadd);
            item.setVisible(true);
            MenuItem items = menu.findItem(R.id.productdelete);
            items.setVisible(true);
            MenuItem itemss = menu.findItem(R.id.logout);
            items.setVisible(true);
            MenuItem items2 = menu.findItem(R.id.h1);
            items2.setVisible(true);

*/
            getMenuInflater().inflate(R.menu.mainproducts, menu);
        } else  {
           /* MenuItem item1 = menu.findItem(productsadd);
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
        if (id == productsadd) {
            click = click + 1;
            if (click == 1) {
                click = 0;
                Intent in = new Intent(Contact.this, AddContact.class);
               // in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                        Intent.FLAG_ACTIVITY_NEW_TASK*/);
                startActivity(in);
                return true;
            }
        } else if (id == R.id.productdelete) {
            click = click + 1;
            if (click == 1) {
                click = 0;
                Intent inn = new Intent(Contact.this, DeleteContact.class);
               // inn.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                inn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                        Intent.FLAG_ACTIVITY_NEW_TASK*/);
                startActivity(inn);

                return true;
            }
        } else if (id == R.id.logout) {
            logout();
            return true;
        }else if(id==R.id.h1)
        {
            Intent inn = new Intent(Contact.this, Main2Activity.class);
            //inn.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            inn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                    Intent.FLAG_ACTIVITY_NEW_TASK*/);
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
            Intent in = new Intent(Contact.this, Main2Activity.class);
/*
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(in);*/
            finish();
        }
    }
    private void logout() {
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Are you sure you want to logout?");
        alertDialogBuilder.setIcon(R.drawable.logoutt);

       // alertDialogBuilder.setMessage("Are you sure you want to logout?");

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
                        editor.apply();

                        //Starting login activity
                        Intent intent = new Intent(Contact.this, MainActivity.class);
                       // intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
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
    public class ContactsDownloader extends AsyncTask<Void, Void, String> {

        Context c;
        String urlAddress;
        ListView lv;


        public ContactsDownloader(Context c, String urlAddress, ListView lv) {
            this.c = c;
            this.urlAddress = urlAddress;
            this.lv = lv;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            return downloadData();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s == null) {
                Toast toast = Toast.makeText(c, "No Current Contacts! Thank you for your patience", Toast.LENGTH_SHORT);

                View toastView = toast.getView();
                toastView.setBackgroundResource(R.drawable.toast_drawable);
                toast.show();
               // Toast.makeText(c, "Unsuccessful,Null returned", Toast.LENGTH_SHORT).show();
            } else {

                //CALL DATA PARSER TO PARSE
                ContactsDataParser parser = new ContactsDataParser(c, lv, s);
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
                StringBuilder jsonData = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    jsonData.append(line).append("n");
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
    public class ContactsDataParser extends AsyncTask<Void,Void,Integer> {
        Context c;
        ListView lv;
        String jsonData;

        ArrayList<MySQLDataBase> mySQLDataBases=new ArrayList<>();
        public ContactsDataParser(Context c, ListView lv, String jsonData) {
            this.c = c;
            this.lv = lv;
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
            if(result==0)
            {
                click = click + 1;
                if (click == 1) {
                    click = 0;
                    Toast toast = Toast.makeText(c, "No Current Contacts! Thank you for your patience", Toast.LENGTH_SHORT);

                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.show();
                    //Toast.makeText(c, "No Collection Available", Toast.LENGTH_SHORT).show();
                }
            }else
            {

                final ContactsListAdapter adapter=new ContactsListAdapter(c,mySQLDataBases);
                lv.setAdapter(adapter);
            }
        }

        private int parseData()
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
                    int contactId=jo.getInt("ContactId");
                    String branch =jo.getString("Branch");
                    String address=jo.getString("Address");
                    String city = jo.getString("City");
                    String time = jo.getString("WorkingHrs");
                    String email=jo.getString("Email");
                    String number = jo.getString("ContactNumber");
                    mySQLDataBase=new MySQLDataBase();
                    mySQLDataBase.setContactId(contactId);
                    mySQLDataBase.setBranch(branch);
                    mySQLDataBase.setAddress(address);
                    mySQLDataBase.setCity(city);
                    mySQLDataBase.setWorkingHrs(time);
                    mySQLDataBase.setEmail(email);
                    mySQLDataBase.setContactNumber(number);
                    mySQLDataBases.add(mySQLDataBase);
                }
                return 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return 0;
        }

    }
    private class ContactsListAdapter extends BaseAdapter {

        Context c;
        ArrayList<MySQLDataBase> mySQLDataBases;
        LayoutInflater inflater;

        private ContactsListAdapter(Context c, ArrayList<MySQLDataBase> mySQLDataBases) {
            this.c = c;
            this.mySQLDataBases = mySQLDataBases;
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        private class ViewHolder {

            TextView emailTxt,branchTxt,addresstxt,workingHrTxt,contactTxt;
            ImageView locationImg,workingHrsImg,emailImg;
            ImageView phoneImg;
        }
        @Override
        public  int getViewTypeCount(){
            return getCount();
        }
        @Override
        public int getItemViewType(int position){
            return position;
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

            ViewHolder holder = null;
                // TODO Auto-generated method stub
                if (convertView == null) {


                    convertView = inflater.inflate(R.layout.contact_list, parent, false);
                    holder = new ViewHolder();
                    holder.branchTxt = (TextView) convertView.findViewById(R.id.textView2);
                    holder.branchTxt.setHorizontallyScrolling(true);
                    holder.branchTxt.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    holder.branchTxt.setSingleLine();
                    holder.branchTxt.setSelected(true);

                    holder. addresstxt = (TextView) convertView.findViewById(R.id.textView3);
                    holder.addresstxt.setHorizontallyScrolling(true);
                    holder.addresstxt.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    holder.addresstxt.setSingleLine();
                    holder.addresstxt.setSelected(true);

                    holder. workingHrTxt = (TextView) convertView.findViewById(R.id.textView4);
                    holder.workingHrTxt.setHorizontallyScrolling(true);
                    holder.workingHrTxt.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    holder.workingHrTxt.setSingleLine();
                    holder.workingHrTxt.setSelected(true);

                    holder.emailTxt = (TextView) convertView.findViewById(R.id.textView5);
                    holder.emailTxt.setHorizontallyScrolling(true);
                    holder.emailTxt.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    holder.emailTxt.setSingleLine();
                    holder.emailTxt.setSelected(true);

                    holder.contactTxt = (TextView) convertView.findViewById(R.id.textView6);
                    holder.contactTxt.setHorizontallyScrolling(true);
                    holder.contactTxt.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    holder.contactTxt.setSingleLine();
                    holder.contactTxt.setSelected(true);

                    holder.locationImg = (ImageView)convertView.findViewById(R.id.imageView);
                    holder. workingHrsImg = (ImageView) convertView.findViewById(R.id.imageView3);
                    holder.emailImg = (ImageView) convertView.findViewById(R.id.imageView4);
                    holder.phoneImg = (ImageView) convertView.findViewById(R.id.imageView5);


                    //BIND DATA
                    MySQLDataBase mySQLDataBase = (MySQLDataBase) this.getItem(position);
                    final String branchName = mySQLDataBase.getBranch();
                    final String branchAddress = mySQLDataBase.getAddress();
                    final String branchCity = mySQLDataBase.getCity();
                    final String branchTime = mySQLDataBase.getWorkingHrs();
                    final String email = mySQLDataBase.getEmail();
                    final String personContact = mySQLDataBase.getContactNumber();
                    final String location =  branchAddress + " " + branchCity;
                    holder.addresstxt.setText(location);
                   holder. branchTxt.setText(branchName);
                   holder.workingHrTxt.setText(branchTime);
                    if (!email.isEmpty() && !email.equals("null")) {
                        holder.emailTxt.setText(email);
                    } else {
                        holder.emailImg.setVisibility(View.INVISIBLE);
                        holder.emailTxt.setVisibility(View.INVISIBLE);
                    }
                    if (personContact != null && !personContact.isEmpty() && !personContact.equals("null")) {
                        holder.contactTxt.setText(personContact);
                    } else {
                        holder.phoneImg.setVisibility(View.GONE);
                        holder.contactTxt.setVisibility(View.GONE);
                    }
                    convertView.setTag(holder);
                    convertView.setTag(R.id.textView3, holder.branchTxt);
                    convertView.setTag(R.id.textView2, holder.addresstxt);
                    convertView.setTag(R.id.imageView, holder.locationImg);
                    convertView.setTag(R.id.textView4, holder.workingHrTxt);
                    convertView.setTag(R.id.imageView3, holder.workingHrsImg);
                    convertView.setTag(R.id.textView5, holder.emailTxt);
                    convertView.setTag(R.id.textView6, holder.contactTxt);
                    convertView.setTag(R.id.imageView4, holder.emailImg);
                    convertView.setTag(R.id.imageView5, holder.phoneImg);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }


                return convertView;
        }
    }

}

