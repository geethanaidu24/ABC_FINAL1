package com.example.admin.abc;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class Paints extends AppCompatActivity {
ImageView im7;
    ImageView im8;
    ListView simpleListView;
    String[] bathroomtypes = {"Paints"};//fruit names array
    int[] typesimages = {R.mipmap.paint};//fruits images array

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paints);
        im7=(ImageView)findViewById(R.id.back);
        im7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Paints.this,Products.class);
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
                Intent in=new Intent(Paints.this,PaintsTypes.class);
                startActivity(in);
            }
        });



    }

}



