package com.example.admin.abc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Geetha on 4/20/2017 for accessing url to displaying images in gridview.
 */

public class ProductSubTypeGridView extends AppCompatActivity {
    ImageView back;

    //Context c;
    final static String url = Config.productTypeSubTypeImgUrlAddress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types_subtype_images);

        final GridView gv = (GridView) findViewById(R.id.gv);
        gv.setNumColumns(2);
        // Get intent data
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity

        final int pid = intent.getExtras().getInt("PRODUCTID_KEY");
        final String pname = intent.getExtras().getString("PRODUCTNAME_KEY");
        final int ptid = intent.getExtras().getInt("PRODUCTTYPEID_KEY");
        final String ptname = intent.getExtras().getString("PRODUCTTYPENAME_KEY");
        final int pstid = intent.getExtras().getInt("PRODUCTSUBTYPEID_KEY");

        String urlAddress = url + pstid;

        new ProductTypeSubTypeImagesDownloader(ProductSubTypeGridView.this, urlAddress, gv, pid, pname, ptid, ptname, pstid).execute();

        Toolbar actionbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != actionbar) {
            actionbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            actionbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ProductSubTypeGridView.this, ProductSubTypes.class);
                  /*  intent.putExtra("PRODUCTID_KEY", pid);
                    intent.putExtra("PRODUCTNAME_KEY", pname);
                    intent.putExtra("PRODUCTTYPEID_KEY", ptid);
                    intent.putExtra("PRODUCTTYPENAME_KEY", ptname);
                    intent.putExtra("PRODUCTSUBTYPEID_KEY", pstid);
                    startActivity(intent);*/
                  finish();
                }
            });

            // Inflate a menu to be displayed in the toolbar
            actionbar.inflateMenu(R.menu.gridproducts);


            actionbar.setOnMenuItemClickListener(
                    new Toolbar.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            // Handle menu item click event

                            int id = item.getItemId();

                            if (id == R.id.productsadd) {
                                Intent in = new Intent(ProductSubTypeGridView.this, AddGridSubTypes.class);
                                startActivity(in);
                            }
                            if (id == R.id.productdelete) {
                                Intent in = new Intent(ProductSubTypeGridView.this, DeleteProducts.class);
                                startActivity(in);
                            }
                            return true;
                        }
                    });
        }
    }
}
