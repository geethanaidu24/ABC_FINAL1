package com.example.admin.abc;

/**
 * Created by Atwyn on 4/8/2017.
 */

public class BasinImages {
    public int ImageId;
    public String Name;
    public String ImagePath;
    public String Brands;
    public String Color;
    public int SizeId;

    public BasinImages(){

    }
    public int getId(){
        return ImageId;
    }
    public void setId(int ImageId){
        this.ImageId=ImageId;
    }
    public String getName(){
        return Name;
    }
    public void setName(String Name){
        this.Name=Name;
    }
    public String getUrl(){
        return ImagePath;
    }
    public void setUrl(String ImagePath){
        this.ImagePath=ImagePath;
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
    public void setColor(String Color){
        this.Color = Color;
    }
    public int getSizeid(){
        return SizeId;
    }
    public void setSizeid(int SizeId){
        this.SizeId = SizeId;
    }
    @Override
    public String toString() {
        return this.ImageId + ". " + this.Name + "." + this.ImagePath + "." + this.Brands + "." + this.Color + "." + this.SizeId;
    }

}
