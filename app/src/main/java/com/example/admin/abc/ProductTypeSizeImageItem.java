package com.example.admin.abc;

/**
 * Created by Geetha on 4/18/2017.
 */

public class ProductTypeSizeImageItem {
    public int ProductSizeImageId;
    public String Name;
    public String ImagePath;
    public String Brand;
    public String Color;
    public int ProductSizeId;
    public int ProductSubTypeId;
    public int ProductTypeId;
    public int ProductId;
    public int Width;
    public int Height;
    public int Length;

    public int getProductSizeImageId(){
        return ProductSizeImageId;
    }
    public void setProductSizeImageId(int ImageId){
        this.ProductSizeImageId = ImageId;
    }
    public String getName(){
        return Name;
    }
    public void setName(String Name){
        this.Name = Name;
    }
    public String getImagePath(){
        return ImagePath;
    }
    public void setImagePath(String ImgPath){
        this.ImagePath = ImgPath;
    }
    public String getBrand(){
        return Brand;
    }
    public void setBrand(String Brand){
        this.Brand = Brand;
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
    public void setProductSizeId(int sizeid){
        this.ProductSizeId = sizeid;
    }
    public int getProductSubTypeId(){return ProductSubTypeId;}
    public void setProductSubTypeId(int prosid){this.ProductSubTypeId = prosid;}
    public int getProductTypeId(){
        return ProductTypeId;
    }
    public void setProductTypeId(int protid){
        this.ProductTypeId = protid;
    }
    public int getProductId(){
        return ProductId;
    }
    public void setProductId(int proid){
        this.ProductId = proid;
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
    public int getLength(){ return Length;}
    public void setLength(int len){ this.Length = len;}
}
