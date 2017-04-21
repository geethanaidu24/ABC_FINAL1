package com.example.admin.abc;

/**
 * Created by Geetha on 4/20/2017 for storing and receiving productsize images from mysql database.
 */

class ProductTypeSubTypeImageItem {
    public int ProductSizeImageId;
    public String Name;
    public String ImagePath;
    public String Brand;
    public String Color;
    public int ProductSizeId;
    public int ProductSubTypeId;
    public int ProductTypeId;
    public int ProductId;

    public int getProductSizeImageId(){
        return ProductSizeImageId;
    }
    public void setProductSizeImageId(int productSizeImageId){
        this.ProductSizeImageId = productSizeImageId;
    }
    public String getName(){
        return Name;
    }
    public void setName(String name){
        this.Name = name;
    }
    public String getImagePath(){
        return ImagePath;
    }
    public void setImagePath(String imagePath){
        this.ImagePath = imagePath;
    }
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
    public int getProductSizeId(){
        return ProductSizeId;
    }
    public void setProductSizeId(int productSizeId){
        this.ProductSizeId = productSizeId;
    }
    public int getProductSubTypeId(){
        return ProductSubTypeId;
    }
    public void setProductSubTypeId(int productSubTypeId){
        this.ProductSubTypeId = productSubTypeId;
    }
    public int getProductTypeId(){
        return ProductTypeId;
    }
    public void setProductTypeId(int productTypeId){
        this.ProductTypeId = productTypeId;
    }
    public int getProductId(){
        return ProductId;
    }
    public void setProductId(int productId){
        this.ProductId = productId;
    }

}
