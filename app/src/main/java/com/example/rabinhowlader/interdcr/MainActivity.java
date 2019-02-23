package com.example.rabinhowlader.interdcr;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.rabinhowlader.interdcr.model.Adapter.ProductAdapter;
import com.example.rabinhowlader.interdcr.model.LiteratureList;
import com.example.rabinhowlader.interdcr.model.PhysicianSampleList;
import com.example.rabinhowlader.interdcr.model.ProductGroupList;
import com.example.rabinhowlader.interdcr.model.UserList;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.rabinhowlader.interdcr.model.Adapter.ProductAdapter.*;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_PRODUCT = "product_group_list";
    private static final String KEY_LITERATURE = "literature_list";
    private static final String KEY_PHYSICIAN = "physician_sample_list";
    private static final String KEY_GIFT = "gift_list";
    Spinner productSpinner;
    Spinner literatureSpinner;
    Spinner physicianSpinner;
    Spinner giftSpinner;
    private ProgressDialog pDialog;
    private String BASE_URL = "https://raw.githubusercontent.com/appinion-dev/intern-dcr-data/master/data.json";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productSpinner = (Spinner) findViewById(R.id.productSpinner);
        literatureSpinner = (Spinner) findViewById(R.id.literatureSpinner);
        physicianSpinner = (Spinner) findViewById(R.id.physicianSpinner);
        giftSpinner = (Spinner) findViewById(R.id.giftpinner);

        Button submitButton = findViewById(R.id.btnSubmit);

        loadProductLiteraturePhysicianGiftDetails();
        displayLoader();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserList product_group_list = (UserList) productSpinner.getSelectedItem();
                String literature_list = literatureSpinner.getSelectedItem().toString();
                String physician_list = physicianSpinner.getSelectedItem().toString();
                String gift_list = giftSpinner.getSelectedItem().toString();
                Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void displayLoader() {
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void loadProductLiteraturePhysicianGiftDetails() {
        final List<UserList> userLists = new ArrayList<>();
        final List<String> userlists = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, BASE_URL, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pDialog.dismiss();
                        try {

                            List<String> LiteratureList = null;
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String ProductGroupList = jsonObject.getString(KEY_PRODUCT);
                                JSONArray literatureList = jsonObject.getJSONArray(KEY_LITERATURE);
                                JSONArray physicianList = jsonObject.getJSONArray(KEY_PHYSICIAN);
                                JSONArray giftList = jsonObject.getJSONArray(KEY_GIFT);
                                LiteratureList = new ArrayList<>();
                                for (int j = 0; j < ProductGroupList.length(); j++) {
                                    LiteratureList.add(literatureList.getString(j));
                                }
                                userLists.add(new UserList(ProductGroupList, LiteratureList));
                            }
                            final ProductAdapter productAdapter = new ProductAdapter(MainActivity.this,
                                    R.layout.product_list, R.id.productSpinnerText, userlists);
                            productSpinner.setAdapter(productAdapter);

                            productSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                    //Populate City list to the second spinner when
                                    // a state is chosen from the first spinner
                                    UserList productDetail = productAdapter.getItem(position);
                                    List<ProductGroupList> ProductList = productDetail.getProductGroupList();
                                    ArrayAdapter literatureAdapter = new ArrayAdapter<>(MainActivity.this,
                                            R.layout.literature_list, R.id.literatureSpinner, ProductList);
                                    literatureSpinner.setAdapter(literatureAdapter);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();

                        //Display error message whenever an error occurs
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();


                    }

                });
        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);


    }
}