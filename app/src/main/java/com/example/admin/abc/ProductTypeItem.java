package com.example.admin.abc;

/**
 * Created by Geetha on 4/11/2017.
 */

public class ProductTypeItem {

    public int ProductTypeId;
    public String ProductType;
    public String ImageUrl;
    public int ProductId;

    public int getProductTypeId(){
        return ProductTypeId;
    }
    public void setProductTypeId(int productTypeId){
        this.ProductTypeId = productTypeId;
    }
    public String getProductType(){
        return ProductType;
    }
    public void setProductType(String productType){
        this.ProductType = productType;
    }
    public String getImageUrl(){
        return ImageUrl;
    }
    public void setImageUrl(String imageUrl){
        this.ImageUrl = imageUrl;
    }
    public int getProductId(){
        return ProductId;
    }
    public void setProductId(int productId){
        this.ProductId = productId;
    }
    @Override
    public String toString() {
        return this.ProductTypeId + ". " + this.ProductType + "." + this.ImageUrl + "." + this.ProductId;
    }
}
