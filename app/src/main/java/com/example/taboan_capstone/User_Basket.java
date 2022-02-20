package com.example.taboan_capstone;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class User_Basket extends Fragment {

    SharedPreferences sharedPreferences;
    public RequestQueue mQueue;
    public int order_id;
    HashMap<Integer,Integer> basketlist ;
    ArrayList<product> list;


    public User_Basket() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user__basket, container, false);


        sharedPreferences = getActivity().getSharedPreferences(login.SHARED_PREF_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Bundle extra =  getActivity().getIntent().getBundleExtra("cart");
        list = (ArrayList<product>) extra.getSerializable("objects");


        if(list.isEmpty()){

//            View v = inflater.inflate(R.layout.empty_basket, container, false);
            Button shop = v.findViewById(R.id.shopnow);
            shop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().finish();
                }
            });
            return v;
        }
        else
        {
//            View v = inflater.inflate(R.layout.fragment_user__basket, container, false);
            TextView totalPrice = v.findViewById(R.id.totalprice);
            ListView listcart = v.findViewById(R.id.cartlist);
            int Price = 0;
            mQueue = VolleySingleton.getInstance(getContext()).getmRequestqueue();
            BasketListAdapter adapter = new BasketListAdapter(getContext(),R.layout.basket_list,list);
            listcart.setAdapter(adapter);
            basketlist = new HashMap<>();
            for(product prod : list){
                Price+=prod.getProd_price()*prod.getQuant();
                basketlist.put(prod.getId(),prod.getQuant());
            }
            //Toast.makeText(this,Integer.toString(adapter.totalprice),Toast.LENGTH_LONG).show();
            totalPrice.setText("₱"+Price);
            String address = sharedPreferences.getString("address","");
            String Name = sharedPreferences.getString("name","");
            TextView cartName = v.findViewById(R.id.cartName);
            TextView add = v.findViewById(R.id.addresscart);
            cartName.setText(Name);
            add.setText(address);
            Button order = v.findViewById(R.id.order);
            order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Order();

                }
            });
            Button edit = v.findViewById(R.id.edit);
//            edit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent i = new Intent(getApplicationContext(),edit_profile.class);
//                    startActivity(i);
//                }
//            });
            return v;
        }


    }

//    private void Order() {
//
//        String url = "paste your link here";
//        StringRequest sr = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonobject = new JSONObject(response);
//
//                    String success = jsonobject.getString("success");
//
//                    if(success.equals("1")) {
//                        //Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
//                        list.clear();
//                        basketlist.clear();
//                        Home.CLEAR_CART=1;
//                        Toast.makeText(getContext(), "Successful Order PLaced", Toast.LENGTH_LONG).show();
//
//                        //Toast.makeText(Login.this,sharedPreferences.getString("un",""),Toast.LENGTH_LONG).show();
//                    }
//                    else{
//                        Toast.makeText(getContext(), "Order Failed ", Toast.LENGTH_LONG).show();
//
//                    }
////                    progressDialog.dismiss();
//
//                } catch (JSONException e) {
//                    Toast.makeText(getContext(), "Order Failed in Catch", Toast.LENGTH_LONG).show();
//                    e.printStackTrace();
////                    progressDialog.dismiss();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                Toast.makeText(getContext(), "Error Logging in check Internet Connection", Toast.LENGTH_LONG).show();
////                progressDialog.dismiss();
//            }
//        }){
//            @Override
//            protected Map<String,String> getParams(){
//                HashMap<String,String> param = new HashMap<String,String>();
//                param.put("user_id",sharedPreferences.getString("id",""));
//                Gson gson = new Gson();
//                String jsonhashmap = gson.toJson(basketlist);
//
//                param.put("data",jsonhashmap);
//
//                return param;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("Content-Type","application/x-www-form-urlencoded");
//                return params;
//            }
//        };
//
//        mQueue.add(sr);
//    }
}