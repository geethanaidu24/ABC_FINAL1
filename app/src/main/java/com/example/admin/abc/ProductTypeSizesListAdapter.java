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
        //final int length = productTypeSizeDBData.getLength();
        final int length =Integer.parseInt(String.valueOf(productTypeSizeDBData.getLength()).toString()) ;
        final int width = Integer.parseInt(String.valueOf(productTypeSizeDBData.getWidth()).toString());
        final int height = Integer.parseInt(String.valueOf(productTypeSizeDBData.getHeight()).toString());
        final String measure =productTypeSizeDBData.getMeasurement().toString();

/*if(Integer.parseInt(String.valueOf(productTypeSizeDBData.getLength()).toString())!=0 &&
        Integer.parseInt(String.valueOf(productTypeSizeDBData.getWidth()).toString())!=0 &&
        Integer.parseInt(String.valueOf(productTypeSizeDBData.getHeight()).toString()) !=0 )
    {
          finalSize =  width + "X" + height + "X" + length;
            typeNameTxt.setText(String.valueOf(finalSize));

        }else if(Integer.parseInt(String.valueOf(productTypeSizeDBData.getHeight()).toString())==0 && Integer.parseInt(String.valueOf(productTypeSizeDBData.getLength()).toString())!=0 && Integer.parseInt(String.valueOf(productTypeSizeDBData.getWidth()).toString())!=0){
            finalSize = length + "X" + width;
            typeNameTxt.setText(String.valueOf(finalSize));
        }else if(Integer.parseInt(String.valueOf(productTypeSizeDBData.getHeight()).toString())!=0 && Integer.parseInt(String.valueOf(productTypeSizeDBData.getLength()).toString())==0 && Integer.parseInt(String.valueOf(productTypeSizeDBData.getWidth()).toString())!=0){
    finalSize = width + "X" + height;
    typeNameTxt.setText(String.valueOf(finalSize));
}else if(Integer.parseInt(String.valueOf(productTypeSizeDBData.getHeight()).toString())!=0 && Integer.parseInt(String.valueOf(productTypeSizeDBData.getLength()).toString())!=0 && Integer.parseInt(String.valueOf(productTypeSizeDBData.getWidth()).toString())==0){
    finalSize = height + "X" + length;
    typeNameTxt.setText(String.valueOf(finalSize));
}else{
    finalSize = height + "X" + length;
    typeNameTxt.setText(String.valueOf(finalSize));
}*/

if(length !=0 && width !=0 && height !=0 && measure !=null){
    finalSize =  width + "X" + height + "X" + length + "" + measure;
    typeNameTxt.setText(String.valueOf(finalSize));
}else if(length ==0 && width !=0 && height !=0 && measure !=null){
    finalSize =  width + "X" + height + "" +measure;
    typeNameTxt.setText(String.valueOf(finalSize));
}else if(length !=0 && width ==0 && height !=0 && measure !=null){
    finalSize =  length + "X" + height + "" +measure;
    typeNameTxt.setText(String.valueOf(finalSize));
}else if(length !=0 && width !=0 && height ==0 && measure !=null){
    finalSize =  length + "X" + width + "" + measure;
    typeNameTxt.setText(String.valueOf(finalSize));
}else if(length ==0 && width !=0 && height ==0 && measure !=null){
    finalSize = width + "" +measure;
    typeNameTxt.setText(finalSize);
}else if(length !=0 && width ==0 && height ==0 && measure !=null){
    finalSize = length + "" +measure;
    typeNameTxt.setText(finalSize);
}else if(length ==0 && width ==0 && height !=0 && measure !=null){
    finalSize = height + "" + measure;
    typeNameTxt.setText(finalSize);
} else if(length !=0 && width !=0 && height !=0 && measure ==null){
    finalSize =  width + "X" + height + "X" + length;
    typeNameTxt.setText(String.valueOf(finalSize));
}else if(length ==0 && width !=0 && height !=0 && measure ==null){
    finalSize =  width + "X" + height;
    typeNameTxt.setText(String.valueOf(finalSize));
}else if(length !=0 && width ==0 && height !=0 && measure ==null){
    finalSize =  length + "X" + height;
    typeNameTxt.setText(String.valueOf(finalSize));
}else if(length !=0 && width !=0 && height ==0 && measure ==null){
    finalSize =  length + "X" + width;
    typeNameTxt.setText(String.valueOf(finalSize));
}else if(length ==0 && width !=0 && height ==0 && measure ==null){
    finalSize = width + "";
    typeNameTxt.setText(finalSize);
}else if(length !=0 && width ==0 && height ==0 && measure ==null){
    finalSize = length + "" ;
    typeNameTxt.setText(finalSize);
}else if(length ==0 && width ==0 && height !=0 && measure ==null){
    finalSize = height + "";
    typeNameTxt.setText(finalSize);
}

        return convertView;
    }

   /* public void openProductTypeSizesActivity(int ptid){
        Intent intent = new Intent(c,ProductTypeSizes.class);
        intent.putExtra("PRODUCTTYPEID_KEY", ptid);
        c.startActivity(intent);
    }*/
}
