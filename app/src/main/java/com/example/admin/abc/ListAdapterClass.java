package com.example.admin.abc;

/**
 * Created by Geetha on 4/6/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapterClass extends BaseAdapter {

    Context context;
    List<Product> valueList;

    public ListAdapterClass(List<Product> listValue, Context context)
    {
        this.context = context;
        this.valueList = listValue;
    }

    @Override
    public int getCount()
    {
        return this.valueList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.valueList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewItem viewItem = null;

        if(convertView == null)
        {
            viewItem = new ViewItem();

            LayoutInflater layoutInfiater = (LayoutInflater)this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInfiater.inflate(R.layout.listview_item, null);

            viewItem.TextViewProductWidth = (TextView)convertView.findViewById(R.id.textView1);
            viewItem.TextViewProductHeight = (TextView)convertView.findViewById(R.id.textView2);

            convertView.setTag(viewItem);
        }
        else
        {
            viewItem = (ViewItem) convertView.getTag();
        }

        viewItem.TextViewProductWidth.setText(valueList.get(position).ProductWidth);
        viewItem.TextViewProductHeight.setText(valueList.get(position).ProductHeight);

        return convertView;
    }


}

class ViewItem
{
    TextView TextViewProductWidth, TextViewProductHeight, TextViewProductId;


}