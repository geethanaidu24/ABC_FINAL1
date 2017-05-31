package com.example.admin.abc;

/**
 * Created by Geetha on 4/21/2017.
 */

public class Config {

    //Main hosting address

public final static String mainUrlAddress = "http://192.168.0.6/abc/";

    // For admin login

public final static String loginUrlAddress = mainUrlAddress+"login.php";

    // For Getting all main products data from database

public final static String productsUrlAddress = mainUrlAddress+"getProducts.php";

    //For performing crud on main products
public final static String productsCRUD = mainUrlAddress+"productsCRUD.php";

    // For getting all main products types from database

public final static String productTypesUrlAddress = mainUrlAddress+"getProductTypes.php";

    // For performing crud on product types
public final static String productTypesCRUD = mainUrlAddress+"productTypesCRUD.php";


    // For getting all main products sizes if they have directly size only from DB

public final static String productSizesUrlAddress = mainUrlAddress+"getProductSizes.php";

    // For performing crud on product sizes
public final static String productSizesCRUD = mainUrlAddress+"productSizesCRUD.php";

    // For getting all main products size images Grid view from DB

public final static String productSizeImgUrlAddress = mainUrlAddress+"getProductSizeImages.php?";

    //For adding grid product sizes
public final static String productSizesGridsCRUD = mainUrlAddress+"productSizesGridsCRUD.php";

    // For getting all main products types subtypes data from DB

public final static String productSubTypesUrlAddress = mainUrlAddress+"getProductSubTypes.php";

    //For performing crud on product subtypes
public final static String productSubTypesCRUD = mainUrlAddress+"productSubTypesCRUD.php";


    // For getting all main products typs subtypes Grid view images from DB
public final static String productSubTypeGridUrlAddress = mainUrlAddress+"getProductSubTypeGrid.php";

    //For performing crud on product sub types grid view
public final static String productSubTypeGridsCRUD = mainUrlAddress+"productSubTypeGridsCRUD.php";

    // For getting all main products types sizes from DB

public final static String productTypeSizesUrlAddress = mainUrlAddress+"getProductTypeSizes.php";

    //For performing crud on product type sizes
public final static String productTypeSizesCRUD = mainUrlAddress+"productTypeSizesCRUD.php";


    // For getting all main products types sizes images Grid view from DB
public final static String productTypeSizeImgUrlAddress = mainUrlAddress+"getProductTypeSizeImages.php?";

    //For adding grid product type sizes
public final static String producttypeSizesGridsCRUD = mainUrlAddress+"productTypeSizeGridsCRUD.php";

    // For getting all main products types Grid view images from DB

public final static String productTypeImgUrlAddress = mainUrlAddress+"getProductTypeImages.php?";

    //For aadding grid products types
public final static String productTypeGridsCRUD = mainUrlAddress+"productTypeGridsCRUD.php";


    // For getting all Brands from DB

public final static String brandsImgUrlAddress = mainUrlAddress+"getBrands.php";

    //For performing crud brands
public final static String brandsCRUD = mainUrlAddress+"brandsCRUD.php";

    // For getting all New Items Form DB
public final static String newsUrlAddress = mainUrlAddress+"getNews.php";

    //For performing crud news
public final static String newsCRUD = mainUrlAddress+"newsCRUD.php";

    // FOR Spinners
public final static String newsSpinner = mainUrlAddress+"newsSpinner.php";

    // For getting all Contacts From DB
public final static String contactsUrlAddress = mainUrlAddress+"getContacts.php";

    // For perform crud contacts
public final static String contactsCRUD = mainUrlAddress+"contactCRUD.php";

    //
public final static String contactsBranchSpin = mainUrlAddress+"contactBranchSpin.php?City=";

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

