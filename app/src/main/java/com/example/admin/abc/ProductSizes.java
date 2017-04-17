package com.example.admin.abc;




public class ProductSizes {
    public int sizeid;
    public int typeid;
    public int length;
    public int width;
    public int height;
    public String mess;

    public ProductSizes(){

    }
    public int getSizeid(){
        return sizeid;
    }
    public void setSizeid(int sizeid){
        this.sizeid = sizeid;
    }
    public int getTypeid(){
        return typeid;
    }
    public void setTypeid(int typeid){
        this.typeid = typeid;
    }
    public int getLength(){
        return length;
    }
    public void setLength(int length){
        this.length = length;
    }
    public int getWidth(){
        return width;
    }
    public void setWidth(int width){
        this.width = width;
    }
    public int getHeight(){
        return height;
    }
    public void setHeight(int height){
        this.height=height;
    }
    public String getMess(){
        return mess;
    }
    public void setMess(String mess){
        this.mess=mess;
    }
}
