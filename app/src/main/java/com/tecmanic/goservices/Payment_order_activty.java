package com.tecmanic.goservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.tecmanic.goservices.Extra.Config;
import com.tecmanic.goservices.Extra.CustomVolleyJsonRequest;
import com.tecmanic.goservices.Extra.DatabaseHandler;
import com.tecmanic.goservices.Extra.Session_management;
import at.grabner.circleprogress.CircleProgressView;

import static com.tecmanic.goservices.Extra.Config.ADD_ORDER_URL;
import static com.tecmanic.goservices.Extra.Config.KEY_ID;
import static com.tecmanic.goservices.Extra.Config.cat_id_json_array;
import static com.android.volley.VolleyLog.TAG;
public class Payment_order_activty extends AppCompatActivity {

    TextView title, number, number1, place_req;
    ImageView back_img, search;
    CardView cardview;
    LinearLayout linear, bottom_linear;
    String getuser_id, gettime, getdate, addressid;
    DatabaseHandler dbcart;

    SharedPreferences sharedPreferences;
    String addonamount;
    String getamount;
    RadioButton rb_cash, rb_debit, rb_credit, rb_netbanking;
    String currency;
    ProgressDialog progressDialog;
    TextView tv_price;
    Session_management session_management;
    int layout;
    CircleProgressView circleProgressView;
    JSONArray passArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cleaning_six);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading");
        place_req = findViewById(R.id.place_req);

        session_management = new Session_management(this);
        bottom_linear = findViewById(R.id.bottom_linear);
        rb_cash = findViewById(R.id.rb_cash);
        rb_credit = findViewById(R.id.rb_credit);
        rb_debit = findViewById(R.id.rb_debit);
        rb_netbanking = findViewById(R.id.rb_netbanking);

        sharedPreferences = getSharedPreferences("value", 0);

        addonamount = sharedPreferences.getString("addon", "");

        currency = getResources().getString(R.string.currency);
        dbcart = new DatabaseHandler(this);

        title = findViewById(R.id.title);

        tv_price = findViewById(R.id.price);

        getdate = getIntent().getStringExtra("date");
        gettime = getIntent().getStringExtra("time");

        addressid = getIntent().getStringExtra("address_id");

        Intent j = getIntent();
        layout = j.getIntExtra("layout", 0);
        title.setText("Select Payment Method");
        search = findViewById(R.id.search);
        search.setVisibility(View.GONE);
        if (layout == 1) {

            title.setText("Select Payment Method");


            circleProgressView = findViewById(R.id.circleView);
            circleProgressView.setVisibility(View.VISIBLE);
            circleProgressView.setOuterContourColor(getResources().getColor(R.color.blue));
            circleProgressView.setTextSize(20);
            circleProgressView.setBarColor(getResources().getColor(R.color.blue));
            circleProgressView.setSpinBarColor(getResources().getColor(R.color.blue));
            circleProgressView.setValue(Float.parseFloat("85"));
        }
        if (layout == 2) {

            title.setText("Select Payment Method");

            circleProgressView = findViewById(R.id.circleView);
            circleProgressView.setVisibility(View.VISIBLE);
            circleProgressView.setOuterContourColor(getResources().getColor(R.color.blue));
            circleProgressView.setTextSize(20);
            circleProgressView.setBarColor(getResources().getColor(R.color.blue));
            circleProgressView.setSpinBarColor(getResources().getColor(R.color.blue));
            circleProgressView.setValue(Float.parseFloat("85"));
        }
        if (layout == 3) {

            title.setText("Select Payment Method");

            circleProgressView = findViewById(R.id.circleView);
            circleProgressView.setVisibility(View.VISIBLE);
            circleProgressView.setOuterContourColor(getResources().getColor(R.color.blue));
            circleProgressView.setTextSize(20);
            circleProgressView.setBarColor(getResources().getColor(R.color.blue));
            circleProgressView.setSpinBarColor(getResources().getColor(R.color.blue));
            circleProgressView.setValue(Float.parseFloat("85"));
        }
        if (layout == 4) {


            circleProgressView = findViewById(R.id.circleView);
            circleProgressView.setVisibility(View.VISIBLE);
            circleProgressView.setOuterContourColor(getResources().getColor(R.color.blue));
            circleProgressView.setTextSize(20);
            circleProgressView.setBarColor(getResources().getColor(R.color.blue));
            circleProgressView.setSpinBarColor(getResources().getColor(R.color.blue));
            circleProgressView.setValue(Float.parseFloat("85"));
        }
        if (layout == 5) {

            title.setText("Select Payment Method");

            circleProgressView = findViewById(R.id.circleView);
            circleProgressView.setVisibility(View.VISIBLE);
            circleProgressView.setOuterContourColor(getResources().getColor(R.color.blue));
            circleProgressView.setTextSize(20);
            circleProgressView.setBarColor(getResources().getColor(R.color.blue));
            circleProgressView.setSpinBarColor(getResources().getColor(R.color.blue));
            circleProgressView.setValue(Float.parseFloat("85"));
        }

        getamount = Config.Finalam;

        tv_price.setText(getResources().getString(R.string.currency) + Config.Finalam);
//

//
//        if (BaseURL.Totalamount==""){
//
//            getamount=dbcart.getTotalAmount();
//            tv_price.setText(session_management.getUserDetails().get(BaseURL.KEY_PINCODE)+dbcart.getTotalAmount());
//        }
//
//        else {
//            getamount=BaseURL.TOTAL_AMOUNT;
//            tv_price.setText(session_management.getUserDetails().get(BaseURL.KEY_PINCODE)+BaseURL.Totalamount);
//        }

        back_img = findViewById(R.id.back_img);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        linear = findViewById(R.id.linear);
        getuser_id = session_management.getUserDetails().get(KEY_ID);
        ArrayList<HashMap<String, String>> items = dbcart.getCartAll();
        if (items.size() > 0) {
            passArray = new JSONArray();
            for (int i = 0; i < items.size(); i++) {
                HashMap<String, String> map = items.get(i);
                JSONObject jObjP = new JSONObject();
                try {
                    jObjP.put("service_id", map.get("service_id"));
                    jObjP.put("service_qty", map.get("qty"));
                    passArray.put(jObjP);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //  Log.d("asdfgh", cat_id_json_array.toString());
        bottom_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rb_cash.isChecked()) {

                    if (cat_id_json_array != null && cat_id_json_array.length()>0) {
                        for (int j = 0; j < cat_id_json_array.length(); j++) {

                            try {
                                JSONObject obj = cat_id_json_array.getJSONObject(j);
                                String id = obj.getString("addon_id");
                                String name = obj.getString("addon_name");
                                String price = obj.getString("addon_price");

                                // Log.d("asdfgh", id);
                                if (!id.equalsIgnoreCase("") && id != null) {
                                    makeAddOrderRequest(getuser_id, passArray);   //addon
                                } else {
                                    if(id.equalsIgnoreCase("")&& id==null) {
                                        //makeAddOrderRequest1(getuser_id, passArray);// no addon
                                    }else {
                                        makeAddOrderRequest1(getuser_id, passArray);// no addon

                                    }
                                }
                            } catch (JSONException e) {
                            }
                        }
                    }else {
                        makeAddOrderRequest1(getuser_id, passArray);//no addon
                    }

                } else if (rb_credit.isChecked() || rb_debit.isChecked() || rb_netbanking.isChecked()) {
                    if (currency.contains("₹")) {
                        Intent intent = new Intent(Payment_order_activty.this, Razorpay.class);
                        intent.putExtra("time", gettime);
                        intent.putExtra("date", getdate);
                        intent.putExtra("addressid", addressid);
                        startActivity(intent);

                    } else {
                        Intent intent = new Intent(Payment_order_activty.this, PayPal.class);
                        intent.putExtra("time", gettime);
                        intent.putExtra("date", getdate);
                        intent.putExtra("addressid", addressid);

                        startActivity(intent);

                    }
                }
            }
        });
    }

    private void attemptOrder() {


        if (!isOnline()) {
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
        } else {
            if (cat_id_json_array != null) {
                for (int j = 0; j < cat_id_json_array.length(); j++) {

                    try {

                        JSONObject obj = cat_id_json_array.getJSONObject(j);

                        String id = obj.getString("addon_id");
                        String name = obj.getString("addon_name");
                        String price = obj.getString("addon_price");

                        Log.d("asdfgh", id);
                        if (!id.equalsIgnoreCase("") && id != null) {
                            makeAddOrderRequest(getuser_id, passArray);

                        } else {
                            // makeAddOrderRequest1(getuser_id, passArray);
                        }
                    } catch (JSONException e) {
                    }
                }
                makeAddOrderRequest1(getuser_id, passArray);
            }
        }
    }

    private void makeAddOrderRequest(String userid, JSONArray passArray) {
        String tag_json_obj = "json_add_order_req";
        Map<String, String> params = new HashMap<String, String>();
        params.put("id",userid);
        params.put("price",Config.Finalam);
        params.put("address_id", addressid);
        params.put("time_slot", gettime);
        params.put("confirmed_on", getdate);
        params.put("payment_mode", "cash");
        params.put("price",getamount);

        params.put("demo_array", passArray.toString());
        params.put("demo_array1",cat_id_json_array.toString());
        //  Log.d("demo_array1",cat_id_json_array.toString());
        progressDialog.show();
        CustomVolleyJsonRequest jsonObjReq = new CustomVolleyJsonRequest(Request.Method.POST,
                ADD_ORDER_URL, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {

                    String status=response.getString("status");
                    if(status.contains("1")){
                        dbcart.clearCart();
                        JSONObject jsonObject=response.getJSONObject("data");
                        String timeslot=jsonObject.getString("time_slot");
                        String bokdate=jsonObject.getString("confirmed_on");
                        String uniquecode=jsonObject.getString("unique_code");
                        String paymentmode=jsonObject.getString("payment_mode");
                        String bookingid=jsonObject.getString("booking_id");
                        //JSONArray jsonArray=response.getJSONArray("services");

                        place_req.setEnabled(false);
                        progressDialog.dismiss();

                        Intent intent=new Intent(Payment_order_activty.this,OrderSucessActivity.class);
                        intent.putExtra("timeslot",timeslot);
                        intent.putExtra("bokdate",bokdate);
                        intent.putExtra("uniquecode",uniquecode);
                        intent.putExtra("paymentmode",paymentmode);
                        intent.putExtra("bookingid",bookingid);
                        startActivity(intent);

                    }
                    else {
                        place_req.setEnabled(true);
                        progressDialog.dismiss();
                    }
                    String message=response.getString("message");
                    Toast.makeText(Payment_order_activty.this, message, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                progressDialog.dismiss();
                // makeAddOrderRequest1(getuser_id, passArray);
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    progressDialog.dismiss();
                    place_req.setEnabled(true);

                    Toast.makeText(Payment_order_activty.this, getResources().getString(R.string.connection_time_out), Toast.LENGTH_SHORT).show();
                }
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.getCache().clear();
        jsonObjReq.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 90000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 0;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        requestQueue.add(jsonObjReq);

    }
    private void makeAddOrderRequest1(String userid, JSONArray passArray) {
        String tag_json_obj = "json_add_order_req";
        Map<String, String> params = new HashMap<String, String>();
        params.put("id",userid);
        params.put("price",Config.Finalam);
        params.put("address_id", addressid);
        params.put("time_slot", gettime);
        params.put("confirmed_on", getdate);
        params.put("payment_mode", "cash");
        params.put("price",getamount);

        params.put("demo_array", passArray.toString());
        progressDialog.show();
        //   Log.d("demo_array",passArray.toString());
        CustomVolleyJsonRequest jsonObjReq = new CustomVolleyJsonRequest(Request.Method.POST,
                ADD_ORDER_URL, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {

                    String status=response.getString("status");
                    if(status.contains("1")){
                        //progressDialog.show();
                        dbcart.clearCart();
                        JSONObject jsonObject=response.getJSONObject("data");
                        String timeslot=jsonObject.getString("time_slot");
                        String bokdate=jsonObject.getString("confirmed_on");
                        String uniquecode=jsonObject.getString("unique_code");
                        String paymentmode=jsonObject.getString("payment_mode");
                        String bookingid=jsonObject.getString("booking_id");
                        //JSONArray jsonArray=response.getJSONArray("services");

                        place_req.setEnabled(false);
                        progressDialog.dismiss();

                        Intent intent=new Intent(Payment_order_activty.this,OrderSucessActivity.class);
                        intent.putExtra("timeslot",timeslot);
                        intent.putExtra("bokdate",bokdate);
                        intent.putExtra("uniquecode",uniquecode);
                        intent.putExtra("paymentmode",paymentmode);
                        intent.putExtra("bookingid",bookingid);
                        startActivity(intent);

                    }
                    else {
                        place_req.setEnabled(true);
                        progressDialog.dismiss();
                    }
                    String message=response.getString("message");
                    Toast.makeText(Payment_order_activty.this, message, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    progressDialog.dismiss();
                    place_req.setEnabled(true);
                    Toast.makeText(Payment_order_activty.this, getResources().getString(R.string.connection_time_out), Toast.LENGTH_SHORT).show();
                }
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.getCache().clear();
        jsonObjReq.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 90000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 0;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        requestQueue.add(jsonObjReq);

    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}

