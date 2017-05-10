package com.example.admin.abc;

/**
 * Created by Geetha on 4/10/2017 for opening Product types activity based on user clicked product .
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.io.Serializable;
import java.util.ArrayList;

public class ProductTypes extends AppCompatActivity implements Serializable {
    ImageView back;
    Context c;
   // ArrayList<ProductTypesDB> productTypesDBs1=new ArrayList<>();
   // ArrayList<ProductTypesDB> productTypesDBs=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types);
        LinearLayout ll = (LinearLayout) findViewById(R.id.products_type);
        final ListView lv = (ListView) findViewById(R.id.productTypesLv);
        TextView typeNameTxt = (TextView) findViewById(R.id.SelProductName);
        Intent intent = getIntent();
        final String selectedPname = intent.getExtras().getString("PRODUCTNAME_KEY");
        final int selectedPid = intent.getExtras().getInt("PRODUCTID_KEY");
        //productTypesDBs.clear();
       // ProductTypesDB productTypesDB;
        ArrayList<ProductTypesDB> productTypesDBs = (ArrayList<ProductTypesDB>) intent.getSerializableExtra("ProductTypeList");
       /* for(int i=0;i<productTypesDBs1.size();i++){
            int ProductTypeId1 = productTypesDBs1.get(i).getProductTypeId();
            String ProductType1 = productTypesDBs1.get(i).getProductType();
            String ImageUrl1 = productTypesDBs1.get(i).getImageUrl();
            int ProductId1 = productTypesDBs1.get(i).getProductId();
            Log.d("result response: ", "> " + ImageUrl1);
            productTypesDB=new ProductTypesDB();
            productTypesDB.setProductTypeId(ProductTypeId1);
            productTypesDB.setProductType(ProductType1);
            productTypesDB.setImageUrl(ImageUrl1);
            productTypesDB.setProductId(ProductId1);
            productTypesDBs.add(productTypesDB);

        }*/
        typeNameTxt.setText(selectedPname);
        Log.d("result response: ", "> " + productTypesDBs);

        final ProductTypesListAdapter adapter = new ProductTypesListAdapter(this, productTypesDBs, selectedPid, selectedPname);
        lv.setAdapter(adapter);

        Toolbar actionbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != actionbar) {
            actionbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            actionbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(ProductTypes.this, Products.class);
                    //startActivity(in);
                    finish();
                }
            });

            actionbar.inflateMenu(R.menu.productstypes);


            actionbar.setOnMenuItemClickListener(
                    new Toolbar.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            // Handle menu item click event

                            int id = item.getItemId();

                            if (id == R.id.producttypesadd) {
                                Intent in = new Intent(ProductTypes.this, AddProductsTypes.class);
                                in.putExtra("PRODUCTID_KEY", selectedPid);
                                in.putExtra("PRODUCTNAME_KEY", selectedPname);
                                startActivity(in);
                            }
                            if (id == R.id.producttypesdelete) {
                                Intent in = new Intent(ProductTypes.this, DeleteProductTypes.class);
                                in.putExtra("PRODUCTID_KEY", selectedPid);
                                in.putExtra("PRODUCTNAME_KEY", selectedPname);
                                startActivity(in);
                            }
                            return true;
                        }
                    }
            );

        }
    }

    private class ProductTypesListAdapter extends BaseAdapter {

        Context c;
        ArrayList<ProductTypesDB> productTypesDBs;
        LayoutInflater inflater;
        String FinalPname;
        int FinalPid;
        public ProductTypesListAdapter(Context c, ArrayList<ProductTypesDB> productTypesDBs,int selectdPid, String selectdPname) {
            this.c = c;
            this.productTypesDBs = productTypesDBs;
            this.FinalPname = selectdPname;
            this.FinalPid=selectdPid;
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return productTypesDBs.size();
        }
        @Override
        public Object getItem(int position) {
            return productTypesDBs.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null)
            {
                convertView=inflater.inflate(R.layout.producttypeimage_list_view,parent,false);
            }
            TextView typeNameTypeTxt= (TextView) convertView.findViewById(R.id.textViewURL1);
            ImageView img= (ImageView) convertView.findViewById(R.id.imageTypePro);

            //BIND DATA
            ProductTypesDB productTypesDB=(ProductTypesDB) this.getItem(position);
            final int ProductTypeId = productTypesDB.getProductTypeId();
            final String ProductTypeName = productTypesDB.getProductType();
            final int ProductId = productTypesDB.getProductId();
            final String imageUrl = productTypesDB.getImageUrl();
            final String finalUrl=Config.mainUrlAddress + imageUrl;

            typeNameTypeTxt.setText(productTypesDB.getProductType());

            //IMG
            PicassoClient.downloadImage(c,finalUrl,img);

            // open new activity
            convertView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    openProductTypeSubTypesActivity(FinalPid,FinalPname,ProductTypeId,ProductTypeName);
                }
            });

            return convertView;
        }

        public void openProductTypeSubTypesActivity(int pid,String name,int ptid,String ptname){
            Intent intent = new Intent(c,ProductSubTypes.class);
            intent.putExtra("PRODUCTID_KEY",pid);
            intent.putExtra("PRODUCTNAME_KEY",name);
            intent.putExtra("PRODUCTTYPEID_KEY", ptid);
            intent.putExtra("PRODUCTTYPENAME_KEY",ptname);
            c.startActivity(intent);
        }
    }

}

