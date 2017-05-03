package com.example.admin.abc;

/**
 * Created by Atwyn on 5/2/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter{
    private LayoutInflater layoutInflater;
    final ArrayList<Productcraft> listData ;
    private Context context;
    public SpinnerAdapter(Context context, ArrayList<Productcraft> listData) {
        this.context = context;
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listData = listData;
    }
    @Override
    public int getCount() {
        return listData.size();
    }
    @Override
    public Object getItem(int position) {
        return (Productcraft)listData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder spinnerHolder;
        if(convertView == null){
            spinnerHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.spinner_layout, parent, false);
            spinnerHolder.spinnerItemList = (TextView)convertView.findViewById(R.id.txt);
            convertView.setTag(spinnerHolder);
        }else{
            spinnerHolder = (ViewHolder)convertView.getTag();
        }
        spinnerHolder.spinnerItemList.setText(listData.get(position).getPRODUCTNAME());
        int spid=listData.get(position).getPRODUCTID();
        spinnerHolder.spinnerItemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        return convertView;
    }

    class ViewHolder{
        TextView spinnerItemList;
    }
}