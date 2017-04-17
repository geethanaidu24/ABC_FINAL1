package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class BasinsSize extends AppCompatActivity {
    Button b1;
    ImageView back;
    ListView simpleListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basins_size);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(BasinsSize.this, Bathroom.class);
                startActivity(in);
            }
        });
        b1 = (Button) findViewById(R.id.buttonws);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(BasinsSize.this, Basins.class);
                startActivity(in);
            }
        });
      /*  simpleListView = (ListView) findViewById(R.id.simpleListView);

        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        for (int i = 0; i < ProductId.length; i++) {
            HashMap<String, String> hashMap = new HashMap<>();//create a hashmap to store the data in key value pair
            hashMap.put("pro", ProductId[i]);
            hashMap.put("wi", Width[i]);
            hashMap.put("hei", height[i]);
            arrayList.add(hashMap);//add the hashmap into arrayList
        }
        String[] from = {"pro", "wi", "hei"};//string array
        int[] to = {R.id.productId, R.id.productWidth, R.id.productHeight};//int array of views id's
        CustomAdapter simpleAdapter = new CustomAdapter(this, arrayList, R.layout.button_layout, from, to);//Create object and set the parameters for simpleAdapter
        simpleListView.setAdapter(simpleAdapter);//sets the adapter for listView

        //perform listView item click event
        simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent in = new Intent(BasinsSize.this, Basins.class);
                startActivity(in);

            }


        });


    }
}

}*/
    }
}