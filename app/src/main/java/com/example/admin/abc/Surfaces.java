package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class Surfaces extends AppCompatActivity {
ImageView b,im1,im2,im3,im4,im5;


    ListView simpleListView;
    String[] bathroomtypes = {"Rustic Surface", "Polished Glaced Vitrified Tiles", "Mettalic Surface", "Glazed Vitried Tiles", "Wooden Floor"};//fruit names array
    int[] typesimages = {R.mipmap.surffour, R.mipmap.surftwo, R.mipmap.surffive, R.mipmap.surfone, R.mipmap.surfthree};//fruits images array
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surfaces);


        ImageView b=(ImageView)findViewById(R.id.back);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Surfaces.this,Products.class);
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
                    case 0:Intent in=new Intent(Surfaces.this,RusticSize.class);
                        startActivity(in);
                        break;

                    case 1:Intent in1=new Intent(Surfaces.this,PGVTSize.class);
                        startActivity(in1);
                        break;
                    case 2:Intent in2=new Intent(Surfaces.this,MetallicSize.class);
                        startActivity(in2);
                        break;
                    case 3:Intent in3=new Intent(Surfaces.this,GVTSize.class);
                        startActivity(in3);
                        break;
                    case 4:Intent in4=new Intent(Surfaces.this,WoodenSize.class);
                        startActivity(in4);
                        break;
                }



            }
        });



    }

}



