package com.atwyn.android.abc;

import java.io.Serializable;

/**
 * Created by Geetha on 5/9/2017.
 */

public class MySQLDataBase implements Serializable {
    public int ProductId, ProductTypeId,ProductSubTypeId,ProductSizeId,ProductSizeImageId,BrandId,NewsId,ContactId,BranchId,CityId;
    public String ProductName, ProductType, ProductSubTypeName,NewsTitle;
    public String ProductImageUrl, ProductTypeImageUrl,ProductSubTypeImageUrl,BrandImageUrl,NewsImageUrl;
    public int Width,Height,Length;
    public String Name,ImagePath,Brand,Color,NewsDescription;
    public String DateTime;
    public String Branch,Address,Email,City,WorkingHrs,ContactNumber;
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
    public int getBrandId() {
        return BrandId;
    }
    public void setBrandId(int Id) {
        this.BrandId = Id;
    }
    public int getNewsId(){ return NewsId; }
    public void setNewsId(int newsId){this.NewsId = newsId;}
    public int getContactId(){return ContactId;}
    public void setContactId(int contactId){this.ContactId=contactId;}
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
    public String getNewsTitle(){return NewsTitle;}
    public void setNewsTitle(String newsTitle){ this.NewsTitle=newsTitle;}
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
    public String getBrandImageUrl(){
        return BrandImageUrl;
    }
    public void setBrandImageUrl(String imgpath){
        this.BrandImageUrl = imgpath;
    }
    public String getNewsImageUrl(){return NewsImageUrl;}
    public void setNewsImageUrl(String newsImageUrl){this.NewsImageUrl = newsImageUrl;}
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
    public String getNewsDescription(){return NewsDescription;}
    public void setNewsDescription(String newsDescription){ this.NewsDescription=newsDescription;}
    public String getDateTime(){return DateTime;}
    public void setDateTime(String dateTime){this.DateTime=dateTime;}
    public String getContactNumber(){return ContactNumber;}
    public  void setContactNumber(String contactNumber){this.ContactNumber=contactNumber;}
    public String getBranch(){return Branch;}
    public void setBranch(String branch){this.Branch=branch;}
    public int getBranchId(){
        return BranchId;}
    public void setBranchId(int branchid){
        this.BranchId = branchid;
    }
    public String getAddress(){return Address;}
    public void setAddress(String address){this.Address=address;}
    public String getEmail(){return Email;}
    public void setEmail(String email){this.Email=email;}
    public String getCity(){return City;}
    public void setCity(String city){this.City=city;}
    public int getCityId(){
        return CityId;}
    public void setCityId(int cityId){
        this.CityId = cityId;
    }
    public String getWorkingHrs(){return WorkingHrs;}
    public void setWorkingHrs(String workingHrs){this.WorkingHrs=workingHrs;}
}
