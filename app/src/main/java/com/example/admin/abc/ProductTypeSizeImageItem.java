package com.example.admin.abc;

/**
 * Created by Geetha on 4/18/2017.
 */

public class ProductTypeSizeImageItem {
    public int ImageId;
    public String Name;
    public String ImagePath;
    public String Brands;
    public String Color;
    public int SizeId;

    public int getImageId(){
        return ImageId;
    }
    public void setImageId(int ImageId){
        this.ImageId = ImageId;
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
    public String getBrands(){
        return Brands;
    }
    public void setBrands(String Brands){
        this.Brands = Brands;
    }
    public String getColor(){
        return Color;
    }
    public void setColor(String color){
        this.Color = color;
    }
    public int getSizeId(){
        return SizeId;
    }
    public void setSizeId(int sizeid){
        this.SizeId = sizeid;
    }

}
