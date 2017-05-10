package com.example.admin.abc;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by SYS2 on 5/9/2017.
 */

public class ProductTypesDBList extends ArrayList<ProductTypesDB> implements Parcelable {

    public ProductTypesDBList(){

    }
    public ProductTypesDBList(Parcel in){
        readFromParcel(in);
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public ProductTypesDBList createFromParcel(Parcel in){
            return new ProductTypesDBList(in);
        }
        public Object[] newArray(int arg0){
            return null;
        }
    };
    private void readFromParcel(Parcel in){
        this.clear();

        // First read the list size
        int size = in.readInt();
        for(int i=0;i<size;i++){
            ProductTypesDB productTypesDB = new ProductTypesDB();
            productTypesDB.setProductTypeId(in.readInt());
            productTypesDB.setProductType(in.readString());
            productTypesDB.setImageUrl(in.readString());
            productTypesDB.setProductId(in.readInt());
            this.add(productTypesDB);
        }
    }
    public int describeContents(){
        return 0;
    }
    public void writeToParcel(Parcel dest, int flags) {
        int size = this.size();
        dest.writeInt(size);
        for(int i=0; i<size; i++){
            ProductTypesDB c = this.get(i);
            dest.writeInt(c.getProductTypeId());
            dest.writeString(c.getProductType());
            dest.writeString(c.getImageUrl());
            dest.writeInt(c.getProductId());
        }
    }
}
