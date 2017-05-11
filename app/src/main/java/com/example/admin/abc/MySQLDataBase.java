package com.example.admin.abc;

import java.io.Serializable;

/**
 * Created by Geetha on 5/9/2017.
 */

public class MySQLDataBase implements Serializable {
    public int ProductId, ProductTypeId,ProductSubTypeId,ProductSizeId,ProductSizeImageId;
    public String ProductName, ProductType, ProductSubTypeName;
    public String ProductImageUrl, ProductTypeImageUrl,ProductSubTypeImageUrl;
    public int Width,Height,Length;
    public String Name,ImagePath,Brand,Color;

    public MySQLDataBase(){

    }
    public int getProductId(){
        return ProductId;
    }
    public void setProductId(int proid){
        this.ProductId=proid;
    }
    public int getProductTypeId(){return ProductTypeId;}
    public void setProductTypeId(int protypeid){this.ProductTypeId=protypeid;}
    public int getProductSubTypeId(){return ProductSubTypeId;}
    public void setProductSubTypeId(int prosubid){this.ProductSubTypeId=prosubid;}
    public int getProductSizeId(){return ProductSizeId;}
    public void setProductSizeId(int prosizeid){this.ProductSizeId=prosizeid;}
    public int getProductSizeImageId(){
        return ProductSizeImageId;
    }
    public void setProductSizeImageId(int productSizeImageId){this.ProductSizeImageId = productSizeImageId;}
    public String getProductName() {
        return ProductName;
    }
    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }
    public String getProductType(){
        return ProductType;
    }
    public void setProductType(String productType){
        this.ProductType = productType;
    }
    public String getProductSubTypeName(){
        return ProductSubTypeName;
    }
    public void setProductSubTypeName(String productSubTypeName){this.ProductSubTypeName = productSubTypeName; }
    public String getProductImageUrl(){return ProductImageUrl;}
    public void setProductImageUrl(String productImageUrl){this.ProductImageUrl=productImageUrl;}
    public String getProductTypeImageUrl(){return ProductTypeImageUrl;}
    public void setProductTypeImageUrl(String productTypeImageUrl){this.ProductTypeImageUrl=productTypeImageUrl;}
    public String getProductSubTypeImageUrl(){return ProductSubTypeImageUrl;}
    public void setProductSubTypeImageUrl(String productSubTypeImageUrl){this.ProductSubTypeImageUrl=productSubTypeImageUrl;}
    public String getImagePath(){
        return ImagePath;
    }
    public void setImagePath(String imagePath){
        this.ImagePath = imagePath;
    }
    public String getName(){return Name;}
    public void setName(String name){this.Name=name;}
    public String getBrand(){
        return Brand;
    }
    public void setBrand(String brand){
        this.Brand = brand;
    }
    public String getColor(){
        return Color;
    }
    public void setColor(String color){
        this.Color = color;
    }
    public int getWidth(){
        return Width;
    }
    public void setWidth(int width){
        this.Width = width;
    }
    public int getHeight(){
        return Height;
    }
    public void setHeight(int height){
        this.Height = height;
    }
    public int getLength(){
        return Length;
    }
    public void setLength(int length){
        this.Length = length;
    }
}
