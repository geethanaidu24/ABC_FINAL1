package com.example.admin.abc;

import java.io.Serializable;

/**
 * Created by Geetha on 4/14/2017 to defining database field names as class variables.
 */

public class ProductTypeSizeDBData implements Serializable {

    public int ProductSizeId;
    public int Length;
    public int Width;
    public int Height;
    public int ProductTypeId;
    public int ProductId;

   public int getProductSizeId(){
       return ProductSizeId;
   }
   public void setProductSizeId(int SizeId){
       this.ProductSizeId = SizeId;
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
    public int getProductTypeId(){
        return ProductTypeId;
    }
    public void setProductTypeId(int typeid){
        this.ProductTypeId = typeid;
    }
    public int getProductId(){
        return ProductId;
    }
    public void setProductId(int ProductId){
        this.ProductId = ProductId;
    }
}
