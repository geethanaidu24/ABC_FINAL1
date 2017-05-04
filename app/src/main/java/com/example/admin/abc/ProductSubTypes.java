package com.example.admin.abc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Geetha on 4/20/2017 for displaying sub menu for product types of main products.
 */

public class ProductSubTypes extends AppCompatActivity {
    ImageView back;

    //Context c;
    final static String url = Config.productSubTypesUrlAddress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types_subtypes);

        LinearLayout ll = (LinearLayout) findViewById(R.id.products_subtype);
        final ListView lv = (ListView) findViewById(R.id.productTypeSubTypesLv);
        TextView typeNameTxt = (TextView) findViewById(R.id.SelProductTypeName);

        // Get intent data
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity
        final int pid = intent.getExtras().getInt("PRODUCTID_KEY");
        final String pname = intent.getExtras().getString("PRODUCTNAME_KEY");
        final int ptid = intent.getExtras().getInt("PRODUCTTYPEID_KEY");
        final String ptname = intent.getExtras().getString("PRODUCTTYPENAME_KEY");
        typeNameTxt.setText(ptname);
        Log.d("result PID: ", "> " + pid);
        Log.d("result PTID: ", "> " + ptid);

        String urlAddress = url + ptid;

        new ProductSubTypesDownloader(ProductSubTypes.this, urlAddress, lv, ll, pid, pname, ptid, ptname).execute();

        Toolbar actionbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != actionbar) {
            actionbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            actionbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(ProductSubTypes.this, ProductTypes.class);
                    in.putExtra("PRODUCTID_KEY", pid);
                    in.putExtra("PRODUCTNAME_KEY", ptid);
                    in.putExtra("PRODUCTTYPEID_KEY", ptid);
                    in.putExtra("PRODUCTTYPENAME_KEY", ptname);

                    startActivity(in);
                }
            });

            // Inflate a menu to be displayed in the toolbar
            actionbar.inflateMenu(R.menu.productsubtypes);

            actionbar.setOnMenuItemClickListener(
                    new Toolbar.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            // Handle menu item click event

                            int id = item.getItemId();

                            if (id == R.id.productsubtypesadd) {
                                Intent in = new Intent(ProductSubTypes.this, AddProductsSubType.class);
                                in.putExtra("PRODUCTID_KEY", pid);
                                in.putExtra("PRODUCTNAME_KEY", ptid);
                                in.putExtra("PRODUCTTYPEID_KEY", ptid);
                                in.putExtra("PRODUCTTYPENAME_KEY", ptname);
                                startActivity(in);
                            }
                            if (id == R.id.productsubtypesdelete) {
                                Intent in = new Intent(ProductSubTypes.this, DeleteProductSubTypes.class);
                                in.putExtra("PRODUCTID_KEY", pid);
                                in.putExtra("PRODUCTNAME_KEY", ptid);
                                in.putExtra("PRODUCTTYPEID_KEY", ptid);
                                in.putExtra("PRODUCTTYPENAME_KEY", ptname);
                                startActivity(in);
                            }
                            return true;
                        }
                    });

        }
    }
}





