package com.example.admin.abc;

/**
 * Created by Geetha on 4/21/2017.
 */

public class Config {

    //Main hosting address

public final static String mainUrlAddress = "http://192.168.0.4/abc/";

    // For admin login

public final static String loginUrlAddress = mainUrlAddress+"login.php";

    // For Getting all main products data from database

public final static String productsUrlAddress = mainUrlAddress+"getProducts.php";

    // For getting all main products types from database

public final static String productTypesUrlAddress = mainUrlAddress+"getProductTypes.php";

    // For getting all main products sizes if they have directly size only from DB

public final static String productSizesUrlAddress = mainUrlAddress+"getProductSizes.php";

    // For getting all main products types subtypes data from DB

public final static String productSubTypesUrlAddress = mainUrlAddress+"getProductSubTypes.php";

    // For getting all main products types sizes from DB

public final static String productTypeSizesUrlAddress = mainUrlAddress+"getProductTypeSizes.php";

    // For getting all main products typs subtypes Grid view images from DB

public final static String productSubTypeGridUrlAddress = mainUrlAddress+"getProductSubTypeGrid.php";

    // For getting all main products types Grid view images from DB

public final static String productTypeImgUrlAddress = mainUrlAddress+"getProductTypeImages.php?";

    // For getting all main products types sizes images Grid view from DB

public final static String productTypeSizeImgUrlAddress = mainUrlAddress+"getProductTypeSizeImages.php?";

    // For getting all main products size images Grid view from DB

public final static String productSizeImgUrlAddress = mainUrlAddress+"getProductSizeImages.php?";

    // For getting all Brands from DB

public final static String brandsImgUrlAddress = mainUrlAddress+"getBrands.php";

    // For getting all New Items Form DB
public final static String newsUrlAddress = mainUrlAddress+"getNews.php";

    //For performing crud on main products
public final static String productsCRUD = mainUrlAddress+"productsCRUD.php";

   // For performing crud on product types
public final static String productTypesCRUD = mainUrlAddress+"productTypesCRUD.php";

    //For performing crud on product subtypes
public final static String productSubTypesCRUD = mainUrlAddress+"productSubTypesCRUD.php";

    //For performing crud on product sub types grid view
public final static String productSubTypeGridsCRUD = mainUrlAddress+"productSubTypeGridsCRUD.php";

    //For performing crud on product type sizes
public final static String productTypeSizesCRUD = mainUrlAddress+"productTypeSizesCRUD.php";

// For performin crud on product sizes
public final static String productSizesCRUD = mainUrlAddress+"productSizesCRUD.php";
//For adding brands
public final static String brandsCRUD = mainUrlAddress+"brandsCRUD.php";
    //For adding news
    public final static String newsCRUD = mainUrlAddress+"newsCRUD.php";
    //For aadding grid products types
    public final static String productTypeGridsCRUD = mainUrlAddress+"productTypeGridsCRUD.php";
    //For adding grid product sizes
    public final static String productSizesGridsCRUD = mainUrlAddress+"productSizesGridsCRUD.php";
    //For adding grid product type sizes
    public final static String producttypeSizesGridsCRUD = mainUrlAddress+"productTypeSizeGridsCRUD.php";
                                            // FOR Spinners
    public final static String newsSpinner = mainUrlAddress+"newsSpinner.php";

    // For product Types spinner
/*
public final static String productTypeSpinner = mainUrlAddress+"productTypeSpinner.php";
    // For product Subtypes spinner
public final static String productSubTypeSpinner = mainUrlAddress+"productSubTypeSpinner.php";
    //For size Spinner
    public final static String sizeSpinner = mainUrlAddress+"sizeSpinner.php";
    // For product Subtypes Grid spinner
public final static String productSubTypeGridSpinner = mainUrlAddress+"productSubTypeGridSpinner.php";
    // For product All product and types for Sizes Spinner for adding
public final static String productAllSizeSpinner = mainUrlAddress+"productAllSizeSpinner.php";
    // For product all sizes spinner for deleting
public final static String productTypeSizeDelSpinner = mainUrlAddress+"productTypeSizeDelSpinner.php";
    // For All product type subtype sizes grid view spinner delete
public final static String deleteGridSubTypeSpinner = mainUrlAddress+"productSubTypeGridDelSpinner.php?ProductSubTypeId=";
    //For product type grid view spinner delete
public final static String deleteGridTypeSpinner = mainUrlAddress+"productTypeGridDelSpinner.php?ProductTypeId=";
    //for adding sizes in spinner
    public final static String productSizesGridSpinner = mainUrlAddress+"productSizesGridSpinner.php";
*/

public final static String PRODUCTID_PARAM ="ProductId";
public final static String PRODUCTTYPEID_PARAM ="ProductTypeId";
public final static String PRODUCTSIZEID_PARAM ="ProductSizeId";
public final static String PRODUCTSUBTYPEID_PARAM = "ProductSubTypeId";
public final static String KEY_USER = "username";
public final static String KEY_PASS = "password";
public final static String LOGIN_SUCCESS = "success";
public final static String SHARED_PREF_NAME = "loginapp";
public final static String USER_SHARED_PREF = "username";
public final static String LOGGEDIN_SHARED_PREF = "loggedIn";
public final static String LOGIN_CHECK ="fail";

}

