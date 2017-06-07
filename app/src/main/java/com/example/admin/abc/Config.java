package com.example.admin.abc;

/**
 * Created by Geetha on 4/21/2017.
 */

public class Config {

    //Main hosting address

public final static String mainUrlAddress = "http://52.15.38.220/";

    // Main hosting address for PHP
public final static String mainPhpUrl = "http://52.15.38.220/php/";

    // For admin login

public final static String loginUrlAddress = mainPhpUrl+"login.php";

    // For Getting all main products data from database

public final static String productsUrlAddress = mainPhpUrl+"getProducts.php";

    //For performing crud on main products
public final static String productsCRUD = mainPhpUrl+"productsCRUD.php";

    // For getting all main products types from database

public final static String productTypesUrlAddress = mainPhpUrl+"getProductTypes.php";

    // For performing crud on product types
public final static String productTypesCRUD = mainPhpUrl+"productTypesCRUD.php";


    // For getting all main products sizes if they have directly size only from DB

public final static String productSizesUrlAddress = mainPhpUrl+"getProductSizes.php";

    // For performing crud on product sizes
public final static String productSizesCRUD = mainPhpUrl+"productSizesCRUD.php";

    // For getting all main products size images Grid view from DB

public final static String productSizeImgUrlAddress = mainPhpUrl+"getProductSizeImages.php?";

    //For adding grid product sizes
public final static String productSizesGridsCRUD = mainPhpUrl+"productSizesGridsCRUD.php";

    // For getting all main products types subtypes data from DB

public final static String productSubTypesUrlAddress = mainPhpUrl+"getProductSubTypes.php";

    //For performing crud on product subtypes
public final static String productSubTypesCRUD = mainPhpUrl+"productSubTypesCRUD.php";


    // For getting all main products typs subtypes Grid view images from DB
public final static String productSubTypeGridUrlAddress = mainPhpUrl+"getProductSubTypeGrid.php";

    //For performing crud on product sub types grid view
public final static String productSubTypeGridsCRUD = mainPhpUrl+"productSubTypeGridsCRUD.php";

    // For getting all main products types sizes from DB

public final static String productTypeSizesUrlAddress = mainPhpUrl+"getProductTypeSizes.php";

    //For performing crud on product type sizes
public final static String productTypeSizesCRUD = mainPhpUrl+"productTypeSizesCRUD.php";


    // For getting all main products types sizes images Grid view from DB
public final static String productTypeSizeImgUrlAddress = mainPhpUrl+"getProductTypeSizeImages.php?";

    //For adding grid product type sizes
public final static String producttypeSizesGridsCRUD = mainPhpUrl+"productTypeSizeGridsCRUD.php";

    // For getting all main products types Grid view images from DB

public final static String productTypeImgUrlAddress = mainPhpUrl+"getProductTypeImages.php?";

    //For aadding grid products types
public final static String productTypeGridsCRUD = mainPhpUrl+"productTypeGridsCRUD.php";


    // For getting all Brands from DB

public final static String brandsImgUrlAddress = mainPhpUrl+"getBrands.php";

    //For performing crud brands
public final static String brandsCRUD = mainPhpUrl+"brandsCRUD.php";

    // For getting all New Items Form DB
public final static String newsUrlAddress = mainPhpUrl+"getNews.php";

    //For performing crud news
public final static String newsCRUD = mainPhpUrl+"newsCRUD.php";

    // FOR Spinners
public final static String newsSpinner = mainPhpUrl+"newsSpinner.php";

    // For getting all Contacts From DB
public final static String contactsUrlAddress = mainPhpUrl+"getContacts.php";

    // For perform crud contacts
public final static String contactsCRUD = mainPhpUrl+"contactCRUD.php";

    //
public final static String contactsBranchSpin = mainPhpUrl+"contactBranchSpin.php?City=";

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

