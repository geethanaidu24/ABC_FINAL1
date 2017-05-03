package com.example.admin.abc;

/**
 * Created by Atwyn on 4/27/2017.
 */

public class Productcraft {

        /*
        INSTANCE FIELDS
         */
        public int id;
    public int PRODUCTID;
    public String PRODUCTNAME;

    /*
    GETTERS AND SETTERS
     */
    public int getPRODUCTID(){return PRODUCTID;}
    public void setPRODUCTID(int proid){this.PRODUCTID = proid;}
    public String getPRODUCTNAME(){return PRODUCTNAME;}
    public void setPRODUCTNAME(String productname){this.PRODUCTNAME=productname;}

    /*
    TOSTRING
     */
    @Override
    public String toString() {
        return null;
    }
    }

