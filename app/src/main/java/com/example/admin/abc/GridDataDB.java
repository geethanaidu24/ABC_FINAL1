package com.example.admin.abc;

/**
 * Created by Admin on 5/4/2017.
 */

class GridDataDB {

    public String Nname;
    public String ImagePath;
    public String Brand;
    public String Color;
    public int Width;
    public int Height;
    public int Length;

    public int ProductSizeImageId;

    public int ProductSizeId;

    public int ProductSubTypeId;
    public String ProductSubTypeName;

    public int ProductTypeId;
    public String ProductType;

    public int ProductId;
    public String ProductName;



    public String getNname(){
        return Nname;
    }
    public void setNname(String name){
        this.Nname = name;
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

    public int getProductSizeId(){
        return ProductSizeId;
    }
    public void setProductSizeId(int productSizeId){
        this.ProductSizeId = productSizeId;
    }

    public int getProductSizeImageId(){
        return ProductSizeImageId;
    }
    public void setProductSizeImageId(int productSizeImageId){
        this.ProductSizeImageId = productSizeImageId;
    }

    public int getProductSubTypeId(){
        return ProductSubTypeId;
    }
    public void setProductSubTypeId(int productSubTypeId){this.ProductSubTypeId = productSubTypeId;}
    public String getProductSubTypeName(){
        return ProductSubTypeName;
    }
    public void setProductSubTypeName(String productSubTypeName){
        this.ProductSubTypeName = productSubTypeName;
    }

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

    public int getProductId(){
        return ProductId;
    }
    public void setProductId(int productId){
        this.ProductId = productId;
    }
    public String getName() {
        return ProductName;
    }
    public void setName(String ProductName) {
        this.ProductName = ProductName;
    }
}

