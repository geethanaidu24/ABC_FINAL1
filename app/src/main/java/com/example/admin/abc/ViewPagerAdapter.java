package com.example.admin.abc;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by SYS3 on 6/27/2017.
 */

public class ViewPagerAdapter extends PagerAdapter {
    int size;
    Activity act;
    View layout;
 ImageView pagenumber1,pagenumber2,pagenumber3,pagenumber4,pagenumber5;
    ImageView pageImage;
    Button click;
    public ViewPagerAdapter(Main2Activity mainActivity, int noofsize) {
        // TODO Auto-generated constructor stub
        size = noofsize;
        act = mainActivity;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return size;
    }
    @Override
    public Object instantiateItem(View container, int position) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater) act
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = inflater.inflate(R.layout.pages, null);
        pagenumber1 = (ImageView)layout.findViewById(R.id.pagenumber1);
        pagenumber2 = (ImageView)layout.findViewById(R.id.pagenumber2);
        pagenumber3 = (ImageView)layout.findViewById(R.id.pagenumber3);
        pagenumber4 = (ImageView)layout.findViewById(R.id.pagenumber4);
        pagenumber5 = (ImageView)layout.findViewById(R.id.pagenumber5);
        pageImage = (ImageView)layout.findViewById(R.id.imageView1);
        int pagenumberTxt=position + 1;
        //pagenumber1.setText("Now your in Page No  " +pagenumberTxt );

        try {
            if(pagenumberTxt == 1){
                pageImage.setBackgroundResource(R.drawable.abackone);
                pagenumber1.setImageResource(R.drawable.dc);
                pagenumber2.setImageResource(R.drawable.dot);
                pagenumber3.setImageResource(R.drawable.dot);
                pagenumber4.setImageResource(R.drawable.dot);
                pagenumber5.setImageResource(R.drawable.dot);
            }
            else if(pagenumberTxt == 2){
                pageImage.setBackgroundResource(R.drawable.dbackfour);
                pagenumber1.setImageResource(R.drawable.dot);
                pagenumber2.setImageResource(R.drawable.dc);
                pagenumber3.setImageResource(R.drawable.dot);
                pagenumber4.setImageResource(R.drawable.dot);
                pagenumber5.setImageResource(R.drawable.dot);

            }else if(pagenumberTxt == 3){
                pageImage.setBackgroundResource(R.drawable.b);
                pagenumber1.setImageResource(R.drawable.dot);
                pagenumber2.setImageResource(R.drawable.dot);
                pagenumber3.setImageResource(R.drawable.dc);
                pagenumber4.setImageResource(R.drawable.dot);
                pagenumber5.setImageResource(R.drawable.dot);

            }
            else if(pagenumberTxt == 4){
                pageImage.setBackgroundResource(R.drawable.fbackseven);
                pagenumber1.setImageResource(R.drawable.dot);
                pagenumber2.setImageResource(R.drawable.dot);
                pagenumber3.setImageResource(R.drawable.dot);
                pagenumber4.setImageResource(R.drawable.dc);
                pagenumber5.setImageResource(R.drawable.dot);

            }
            else if(pagenumberTxt == 5){
                pageImage.setBackgroundResource(R.drawable.bck);
                pagenumber1.setImageResource(R.drawable.dot);
                pagenumber2.setImageResource(R.drawable.dot);
                pagenumber3.setImageResource(R.drawable.dot);
                pagenumber4.setImageResource(R.drawable.dot);
                pagenumber5.setImageResource(R.drawable.dc);

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ((ViewPager) container).addView(layout, 0);
        return layout;
    }
    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == ((View) arg1);
    }
    @Override
    public Parcelable saveState() {
        return null;
    }

    // }
}