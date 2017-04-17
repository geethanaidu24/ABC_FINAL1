package com.example.admin.abc;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Geetha on 4/8/2017 for Picasso ImageLoader to download our image into an imageview
 */
public class PicassoClient {
    public static void downloadImage(Context c,String imageUrl,ImageView img)
    {
        if(imageUrl.length()>0 && imageUrl!=null)
        {
            Picasso.with(c).load(imageUrl).placeholder(R.drawable.pageloader).into(img);
        }else {
            Picasso.with(c).load(R.mipmap.ic_launcher).into(img);
        }
    }
}