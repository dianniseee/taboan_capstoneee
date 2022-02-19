package com.example.taboan_capstone;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class vegfruit extends Fragment{


    private static final String PRODUCT_URL = "https://capierap.online/api.php";
    private static final int CLEAR_CART = 0;
    RecyclerView recyclerView;
    ProductAdapter adapter;
    List<product> productList;
    ImageView backPress;
    public RequestQueue mQueue;

    Button productList_btn;


    public vegfruit() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_vegfruit, container, false);

        productList = new ArrayList<>();
        recyclerView = v.findViewById(R.id.recyclerView1);
        backPress = v.findViewById(R.id.backimg);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        //adding some items to our list

        getProducts();


        //creating recyclerview adapter
        adapter = new ProductAdapter(getContext(), productList);
        recyclerView.setAdapter(adapter);

        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User_Category fragment = new User_Category();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainLayout,fragment);
                fragmentTransaction.commit();
            }
        });



        return v;
    }

    private void getProducts() {
        String url = "https://capierap.online/getProductData.php";
        JsonObjectRequest ObjRequest = new JsonObjectRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray result = response.getJSONArray("result");
                            for (int i = 0; i < result.length(); i++) {
                                JSONObject jo = result.getJSONObject(i);
                                String prod_name = jo.getString("prod_name");
                                String prod_desc = jo.getString("prod_desc");
                                String prod_category = jo.getString("prod_category");
                                int id = Integer.parseInt(jo.getString("prod_id"));
                                double prod_price = Integer.parseInt(jo.getString("prod_price"));
                                double prod_quantity = Integer.parseInt(jo.getString("prod_quantity"));
                                String imgstr = jo.getString("image");

                                product p = new product();
                                p.setProd_name(prod_name.substring(0, 1).toUpperCase() + prod_name.substring(1));
                                p.setProd_desc(prod_desc);
                                p.setProd_category(prod_category);
                                p.setId(id);
                                p.setProd_price(prod_price);
                                p.setProd_quantity(prod_quantity);
                                p.setQuant(0);
                                p.setImage(imgstr);

                                productList.add(p);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
//                        progressDialog.dismiss();
                        adapter.list=new ArrayList<>(productList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError e) {
                        NetworkResponse response = e.networkResponse;
                        if(response != null && response.data != null){
                            Toast.makeText(getContext(),"errorMessage:"+response.statusCode, Toast.LENGTH_SHORT).show();
                        }else{
                            String errorMessage=e.getClass().getSimpleName();
                            if(!TextUtils.isEmpty(errorMessage)){
                                Toast.makeText(getContext(),"errorMessage:"+errorMessage, Toast.LENGTH_SHORT).show();
                            }
                        }
                        e.printStackTrace();
//                        progressDialog.dismiss();
                    }
                }) {

        };
        Toast.makeText(getContext(),"Done",Toast.LENGTH_LONG).show();
        mQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        mQueue.add(ObjRequest);

    }





//    private void loadProducts() {
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, PRODUCT_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONArray products = new JSONArray(response);
//
//
//                            for (int i =0; i<products.length(); i++){
//                                JSONObject productObject = products.getJSONObject(i);
//
//                                String name = productObject.getString("prod_name");
//                                String desc = productObject.getString("prod_desc");
//                                String category = productObject.getString("prod_category");
//                                Double price = productObject.getDouble("prod_price");
//                                Double quantity = productObject.getDouble("prod_quantity");
//                                String image = productObject.getString("image");
//                                product product = new product(name, desc, category, price, quantity, image);
//
//                                productList.add(product);
//                            }
//                            adapter = new ProductAdapter(getContext(), productList);
//                            recyclerView.setAdapter(adapter);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                        NetworkResponse response = error.networkResponse;
//                        if(response != null && response.data != null){
//                            Toast.makeText(getContext(),"errorMessage:"+response.statusCode, Toast.LENGTH_SHORT).show();
//                        }else{
//                            String errorMessage=error.getClass().getSimpleName();
//                            if(!TextUtils.isEmpty(errorMessage)){
//                                Toast.makeText(getContext(),"errorMessage:"+errorMessage, Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                    }
//                });
//
//
//
//
//        mQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
//        mQueue.add(stringRequest);
//    }

//    public void setTv_productList_btn(){
//        productList_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = new vegfruit();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.mainLayout,fragment);
//                fragmentTransaction.commit();
//
//            }
//        });
//    }

}