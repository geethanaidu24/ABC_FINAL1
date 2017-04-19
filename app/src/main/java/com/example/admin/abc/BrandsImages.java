package com.example.admin.abc;

/**
 * Created by Admin on 4/18/2017.
 */

public class BrandsImages {
    public int Id;



    public String ImagePath;

    public BrandsImages(){

    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    public String getImagePath(){
        return ImagePath;
    }
    public void setImagePath(String imgpath){
        this.ImagePath = imgpath;
    }


    @Override
    public String toString() {
        return this.Id + ". "  + this.ImagePath;
    }
}


