package com.example.taboan_capstone;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

import java.util.List;

public class basket_adapter{

    Context context;
    private List<basket_data> myData;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    private String name;
    private double price;
    private int quan;
    private Double total;

    public basket_adapter(Context context, List<basket_data> myData){
        this.context = context;
        this.myData = myData;
    }

}
