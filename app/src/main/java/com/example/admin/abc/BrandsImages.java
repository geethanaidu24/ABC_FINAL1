package com.example.admin.abc;

/**
 * Created by Admin on 4/18/2017.
 */

public class BrandsImages {
    public int ProductId;



    public String ImageUrl;

    public BrandsImages(){

    }

    public int getId() {
        return ProductId;
    }

    public void setId(int ProductId) {
        this.ProductId = ProductId;
    }


    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
    }

    @Override
    public String toString() {
        return this.ProductId + ". " + "." + this.ImageUrl;
    }
}


