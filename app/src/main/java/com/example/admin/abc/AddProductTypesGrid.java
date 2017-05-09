package com.example.admin.abc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.admin.abc.R.id.name2;

public class AddProductTypesGrid extends AppCompatActivity implements View.OnClickListener {
    private static final String addGridData = Config.productSubTypeGridsCRUD;
    private static final int IMAGE_REQUEST_CODE = 3;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private ImageView imageView;
    private TextView Path;
    private EditText name, brand, color;
    private Button btnadd;
    private Bitmap bitmap;
    private Uri filePath;
    public int pid = 0;
    public int ptid = 0;
    public int pstid = 0;
    public int psid = 0;

    Context context;
    final ArrayList<GridDataDB> gridDataDBs = new ArrayList<>();

    private Spinner sp1, sp2, sp3, sp4;
    private ArrayAdapter<GridDataDB> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_types_grid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(AddProductTypesGrid.this, ProductTypeSizes.class);
            finish();
                    //startActivity(in);
                }
            });
            imageView = (ImageView) findViewById(R.id.image);
            name = (EditText) findViewById(name2);
            Path = (TextView) findViewById(R.id.path);
            brand = (EditText) findViewById(R.id.brand2);
            color = (EditText) findViewById(R.id.color2);
            sp1 = (Spinner) findViewById(R.id.sizespinner2);
            sp2 = (Spinner) findViewById(R.id.subtypesspinner2);
            sp3 = (Spinner) findViewById(R.id.typesspinner2);
            sp4 = (Spinner) findViewById(R.id.productspinner2);
            // sp1=(Spinner)findViewById(R.id.sp1producttypes) ;
            btnadd = (Button) findViewById(R.id.btnadd2);

            //requestStoragePermission();

            imageView.setOnClickListener(this);
            btnadd.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
