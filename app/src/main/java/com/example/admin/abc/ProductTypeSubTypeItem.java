package com.example.admin.abc;

/**
 * Created by Geetha on 4/20/2017 for storing data from database into local variables using below functions.
 */

public class ProductTypeSubTypeItem {
    public int ProductSubTypeId;
    public String ProductSubTypeName;
    public String ImageUrl;
    public int ProductTypeId;

    public int getProductSubTypeId(){
        return ProductSubTypeId;
    }
    public void setProductSubTypeId(int productSubTypeId){
        this.ProductSubTypeId = productSubTypeId;
    }
    public String getProductSubTypeName(){
        return ProductSubTypeName;
    }
    public void setProductSubTypeName(String productSubTypeName){
        this.ProductSubTypeName = productSubTypeName;
    }
    public String getImageUrl(){
        return ImageUrl;
    }
    public void setImageUrl(String imageUrl){
        this.ImageUrl = imageUrl;
    }
    public int getProductTypeId(){
        return ProductTypeId;
    }
    public void setProductTypeId(int productTypeId){
        this.ProductTypeId = productTypeId;
    }
}
