package com.example.admin.abc;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;

public class Bathroom extends AppCompatActivity {
    ImageView back4;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;





        ListView simpleListView;
        String[] bathroomtypes = {"Basins", "Toilets", "Tubs", "Shower Rooms", "Urinal"};//fruit names array
        int[] typesimages = {R.mipmap.basinf, R.mipmap.toilets, R.mipmap.tubs, R.mipmap.shower, R.mipmap.urinal};//fruits images array

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            getSupportActionBar().hide();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_bathroom);

            back4 = (ImageView) findViewById(R.id.back);
            back4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(Bathroom.this, Products.class);
                    startActivity(in);
                }
            });
            simpleListView = (ListView) findViewById(R.id.simpleListView);

            ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
            for (int i = 0; i < bathroomtypes.length; i++) {
                HashMap<String, String> hashMap = new HashMap<>();//create a hashmap to store the data in key value pair
                hashMap.put("name", bathroomtypes[i]);
                hashMap.put("image", typesimages[i] + "");
                arrayList.add(hashMap);//add the hashmap into arrayList
            }
            String[] from = {"name", "image"};//string array
            int[] to = {R.id.textView, R.id.imageView};//int array of views id's
            CustomAdapter simpleAdapter = new CustomAdapter(this, arrayList, R.layout.list_single, from, to);//Create object and set the parameters for simpleAdapter
            simpleListView.setAdapter(simpleAdapter);//sets the adapter for listView

            //perform listView item click event
            simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String s=adapterView.getItemAtPosition(i).toString();
                    switch (i)
                    {
                        case 0:Intent in=new Intent(Bathroom.this,BasinsSize.class);
                            startActivity(in);
                            break;

                        case 1:Intent in1=new Intent(Bathroom.this,ToiletsSize.class);
                            startActivity(in1);
                            break;
                        case 2:Intent in2=new Intent(Bathroom.this,TubsSize.class);
                            startActivity(in2);
                            break;
                        case 3:Intent in3=new Intent(Bathroom.this,ShowerroomSize.class);
                            startActivity(in3);
                            break;
                        case 4:Intent in4=new Intent(Bathroom.this,UrinalSize.class);
                            startActivity(in4);
                            break;
                    }



                }
            });



        }

    }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
