package com.example.admin.abc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Geetha on 4/14/2017 for displaying main product type images sizes based on size.
 */

public class ProductTypeSizesListAdapter extends BaseAdapter {

    Context c;
    ArrayList<ProductTypeSizeDBData> productTypeSizeDBDatas;
    LayoutInflater inflater;

    String finalSize;

    public ProductTypeSizesListAdapter(Context c, ArrayList<ProductTypeSizeDBData> productTypeSizeDBDatas) {
        this.c = c;
        this.productTypeSizeDBDatas = productTypeSizeDBDatas;
        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return productTypeSizeDBDatas.size();
    }
    @Override
    public Object getItem(int position) {
        return productTypeSizeDBDatas.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.producttypesize_list_view,parent,false);
        }
        TextView typeNameTxt= (TextView) convertView.findViewById(R.id.productSize);

        //BIND DATA
        ProductTypeSizeDBData productTypeSizeDBData = (ProductTypeSizeDBData) this.getItem(position);

        final int sizeid = productTypeSizeDBData.getSizeId();
        final int length = productTypeSizeDBData.getLength();
        final int width = productTypeSizeDBData.getWidth();
        final int height = productTypeSizeDBData.getHeight();
        final String measure = productTypeSizeDBData.getMeasurement();
       // typeNameTxt.setText(String.valueOf(productTypeSizeDBData.getWidth()));

        if(length!=0 && width!=0 && height!=0){

            //typeNameTxt.setText(productTypeSizeDBData.getWidth() + "X"+ productTypeSizeDBData.getHeight()+ "X" + productTypeSizeDBData.getLength());
            //typeNameTxt.setText(String.valueOf(productTypeSizeDBData.getWidth()));
          finalSize =  width + "X" + height + "X" + length;
            typeNameTxt.setText(String.valueOf(finalSize));

        }else if(height==0 && length!=0 && width!=0){
            finalSize = length + "X" + height;
            typeNameTxt.setText(String.valueOf(finalSize));
        }

        /*else if(width==0 && length ==0){
            typeNameTxt.setText(String.valueOf(productTypeSizeDBData.getHeight()));
           // typeNameTxt.setText(productTypeSizeDBData.getHeight()+ "X" + productTypeSizeDBData.getLength());
           // typeNameTxt.setText(String.valueOf(productTypeSizeDBData.getHeight()+ "x"+String.valueOf(productTypeSizeDBData.getLength())) ));
           finalSize = height;
        }else if (length ==0 && height ==0){
            typeNameTxt.setText(String.valueOf(productTypeSizeDBData.getWidth()));
            //typeNameTxt.setText(productTypeSizeDBData.getWidth() + "X"+ productTypeSizeDBData.getLength());
            finalSize = length + "X" + width;
        }else if(width==0 && height ==0) {
            typeNameTxt.setText(String.valueOf(productTypeSizeDBData.getLength()));
            //typeNameTxt.setText(productTypeSizeDBData.getWidth() + "X"+ productTypeSizeDBData.getHeight());
            //finalSize = width + "X" + height;
        }*/
      //  typeNameTxt.setText(productTypeSizeDBData.getSizeId());
       // typeNameTxt.setText(String.valueOf(finalSize));
        //IMG

        // open new activity
       /* convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openProductTypeSizesActivity(ptid);
            }
        });*/

        return convertView;
    }

   /* public void openProductTypeSizesActivity(int ptid){
        Intent intent = new Intent(c,ProductTypeSizes.class);
        intent.putExtra("PRODUCTTYPEID_KEY", ptid);
        c.startActivity(intent);
    }*/
}
