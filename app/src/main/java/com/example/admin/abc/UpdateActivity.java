package com.example.admin.abc;

/**
 * Created by Geetha on 4/6/2017.
 */

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class UpdateActivity extends AppCompatActivity {

    String HttpURL = "https://10.0.2.3/abc/products/UpdateStudent.php";
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    EditText ProductName, ProductWidth, ProductHeight;
    Button UpdateProduct;
    String IdHolder, ProductNameHolder, ProductWidthHolder, ProductHeightHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        ProductName = (EditText)findViewById(R.id.editName);
        ProductWidth = (EditText)findViewById(R.id.editWidth);
        ProductHeight = (EditText)findViewById(R.id.editHeight);

        UpdateProduct = (Button)findViewById(R.id.UpdateProduct);

        // Receive Student ID, Name , Phone Number , Class Send by previous ShowSingleRecordActivity.
        IdHolder = getIntent().getStringExtra("Id");
        ProductNameHolder = getIntent().getStringExtra("Name");
        ProductWidthHolder = getIntent().getStringExtra("Width");
        ProductHeightHolder = getIntent().getStringExtra("Height");

        // Setting Received Student Name, Phone Number, Class into EditText.
        ProductName.setText(ProductNameHolder);
        ProductWidth.setText(ProductWidthHolder);
        ProductHeight.setText(ProductHeightHolder);

        // Adding click listener to update button .
        UpdateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Getting data from EditText after button click.
                GetDataFromEditText();

                // Sending Student Name, Phone Number, Class to method to update on server.
                ProductRecordUpdate(IdHolder,ProductNameHolder,ProductWidthHolder, ProductHeightHolder);

            }
        });


    }

    // Method to get existing data from EditText.
    public void GetDataFromEditText(){

        ProductNameHolder = ProductName.getText().toString();
        ProductWidthHolder = ProductWidth.getText().toString();
        ProductHeightHolder = ProductHeight.getText().toString();

    }

    // Method to Update Student Record.
    public void ProductRecordUpdate(final String ID, final String P_Name, final String P_Width, final String P_Height){

        class productRecordUpdateClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(UpdateActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(UpdateActivity.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("ProductID",params[0]);

                hashMap.put("ProductName",params[1]);

                hashMap.put("ProductWidth",params[2]);

                hashMap.put("ProductHeight",params[3]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        productRecordUpdateClass productRecordUpdateClass = new productRecordUpdateClass();

        productRecordUpdateClass.execute(ID,P_Name,P_Width,P_Height);
    }
}
