package com.example.admin.abc;

/**
 * Created by Geetha on 4/7/2017 for single data item.
 */

public class ProductImages {

    public int ProductId;

    public String ProductName;

    public String ImageUrl;

    public ProductImages(){

    }

    public int getId() {
        return ProductId;
    }

    public void setId(int ProductId) {
        this.ProductId = ProductId;
    }

    public String getName() {
        return ProductName;
    }

    public void setName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
    }

    @Override
    public String toString() {
        return this.ProductId + ". " + this.ProductName + "." + this.ImageUrl;
    }
}
