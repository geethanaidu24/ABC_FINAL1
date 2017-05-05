package com.example.admin.abc;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Admin on 5/4/2017.
 */

class MySQLClient {
    //SAVE/RETRIEVE URLS
    private static final String DATA_INSERT_URL="http://10.0.2.2/abc/CRUD.php";
    //private static final String DATA_RETRIEVE_URL="http://10.0.2.2/abc/Index.php";
    //INSTANCE FIELDS
    private final Context c;
    // private ArrayAdapter<Productcraft> adapter ;
    public MySQLClient(Context c) {
        this.c = c;
    }
    /*
    SAVE/INSERT
     */
    void add(SizesDB s, String spinSelVal, String spinSelVal1, EditText txtwidth, EditText txtheight, EditText txtlength) {
    {
        if(s==null)
        {
            Toast.makeText(c, "No Data To Save", Toast.LENGTH_SHORT).show();
        }
        else
        {  // .addBodyParameter("pname", String.valueOf(s.getPRODUCTID()))
            // .addBodyParameter("pname", String.valueOf(pid))
            AndroidNetworking.post(DATA_INSERT_URL)
                    .addBodyParameter("action","save")
                    .addBodyParameter("width", String.valueOf(s.getWidth()))
                   .addBodyParameter("height",String.valueOf(s.getHeight()))
                    .addBodyParameter("length",String.valueOf(s.setLength()))
                    .addBodyParameter("pname",s.getName())
                    .addBodyParameter("pid", String.valueOf(s.getProductId()))
                    .addBodyParameter("pname1",s.getProductType())
                  //  .addBodyParameter("ptid",String.valueOf(s.getProductTypeId())
                            .setTag("TAG_ADD")
                            .build()
                            .getAsJSONArray(new JSONArrayRequestListener() {
                                                @Override
                                                public void onResponse(JSONArray response) {
                                                    if(response != null)
                                                        try {
                                                            //SHOW RESPONSE FROM SERVER
                                                            String responseString = response.get(0).toString();
                                                            Toast.makeText(c, "PHP SERVER RESPONSE : " + responseString, Toast.LENGTH_SHORT).show();
                                                            if (responseString.equalsIgnoreCase("Success")) {
                                                                //CLEAR EDITXTS
                                                                EditText[] editTexts = new EditText[0];
                                                                EditText txtwidth = editTexts[0];
                                                                // EditText propTxt = editTexts[1];
                                                                EditText txtheight = editTexts[1];
                                                                EditText txtlength =editTexts[2];
                                                                txtwidth.setText("");
                                                                // propTxt.setText("");
                                                                txtheight.setText("");
                                                                txtlength.setText("");

                                                            }else
                                                            {
                                                                Toast.makeText(c, "PHP WASN'T SUCCESSFUL. ", Toast.LENGTH_SHORT).show();
                                                            }
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                            Toast.makeText(c, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIVED : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                                        }
                                                }
                                //ERROR
                                @Override
                                public void onError(ANError anError) {
                                    Toast.makeText(c, "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
        }
    }

    }
}