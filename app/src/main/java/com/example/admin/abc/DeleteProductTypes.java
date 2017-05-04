package com.example.admin.abc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class DeleteProductTypes extends AppCompatActivity {
    final ArrayList<Productcraft> productcrafts = new ArrayList<>();
    // ArrayAdapter<String> adapter;
    private Spinner sp;
    private Button btnAdd;
    private ArrayAdapter<Productcraft> adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product_types);


    }
}
