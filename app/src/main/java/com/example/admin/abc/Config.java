package com.example.admin.abc;

/**
 * Created by Geetha on 4/21/2017.
 */

public class Config {

    //Main hosting address

public final static String mainUrlAddress = "http://192.168.0.8/abc/";

    // For admin login

public final static String loginUrlAddress = mainUrlAddress+"login.php";

    // For Getting all main products data from database

public final static String productsUrlAddress = mainUrlAddress+"getProducts.php";

    // For getting all main products types from database

public final static String productTypesUrlAddress = mainUrlAddress+"getProductTypes.php?ProductId=";

    // For getting all main products sizes if they have directly size only from DB

public final static String productSizesUrlAddress = mainUrlAddress+"getProductSizes.php?ProductId=";

    // For getting all main products types subtypes data from DB

public final static String productTypeSubTypesUrlAddress = mainUrlAddress+"getProductTypeSubTypes.php?ProductTypeId=";

    // For getting all main products types sizes from DB

public final static String productTypeSizesUrlAddress = mainUrlAddress+"getProductTypeSizes.php?";

    // For getting all main products typs subtypes Grid view images from DB

public final static String productTypeSubTypeImgUrlAddress = mainUrlAddress+"getProductTypeSubTypeImages.php?ProductSubTypeId=";

    // For getting all main products types Grid view images from DB

public final static String productTypeImgUrlAddress = mainUrlAddress+"getProductTypeImages.php?";

    // For getting all main products types sizes images Grid view from DB

public final static String productTypeSizeImgUrlAddress = mainUrlAddress+"getProductTypeSizeImages.php?";

    // For getting all main products size images Grid view from DB

public final static String productSizeImgUrlAddress = mainUrlAddress+"getProductSizeImages.php?";

    // For getting all Brands from DB

public final static String brandsImgUrlAddress = mainUrlAddress+"getAllBrandImages.php";

    //For adding new products into main products
public final static String addProducts = mainUrlAddress+"CRUD.php";

   // For geting product names for spinner

public final static String productSpinner =mainUrlAddress+"productSpinner.php";

   // For adding new products into products types

public final static String addProductTypes = mainUrlAddress+"addProductTypes.php";


public final static String PRODUCTID_PARAM ="ProductId";
public final static String PRODUCTTYPEID_PARAM ="ProductTypeId";
public final static String PRODUCTSIZEID_PARAM ="ProductSizeId";
public final static String KEY_USER = "username";
public final static String KEY_PASS = "password";
public final static String LOGIN_SUCCESS = "success";
public final static String SHARED_PREF_NAME = "loginapp";
public final static String USER_SHARED_PREF = "username";
public final static String LOGGEDIN_SHARED_PREF = "loggedIn";
public final static String LOGIN_CHECK ="fail";

}
