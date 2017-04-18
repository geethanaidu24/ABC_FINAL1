package com.example.admin.abc;

/**
 * Created by Geetha on 4/14/2017 to defining database field names as class variables.
 */

public class ProductTypeSizeDBData {

    public int SizeId;
    public int Length;
    public int Width;
    public int Height;
    public String Measurement;
    public int ProductTypeId;

    public int getSizeId(){
        return SizeId;
    }
    public void setSizeId(int SizeId){
        this.SizeId = SizeId;
    }
    public int getLength(){
        return Length;
    }
    public void setLength(int Length){
        this.Length = Length;
    }
    public int getWidth(){
        return Width;
    }
    public void setWidth(int Width){
        this.Width = Width;
    }
    public int getHeight(){
        return Height;
    }
    public void setHeight(int Height){
        this.Height = Height;
    }
    public String getMeasurement(){
        return Measurement;
    }
    public void setMeasurement(String Meas){
        this.Measurement = Meas;
    }
    public int getProductTypeId(){
        return ProductTypeId;
    }
    public void setProductTypeId(int typeid){
        this.ProductTypeId = typeid;
    }
}
