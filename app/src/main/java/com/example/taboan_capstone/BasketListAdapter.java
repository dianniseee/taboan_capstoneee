package com.example.taboan_capstone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BasketListAdapter extends ArrayAdapter<product> {
    Context c;
    int resource;

    public BasketListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<product> objects) {
        super(context, resource, objects);
        this.c=context;
        this.resource=resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String Name = getItem(position).getProd_name();
        String Qty = Integer.toString(getItem(position).getQuant());
        double price = getItem(position).getProd_price();
        String cost = Integer.toString((int) getItem(position).getProd_price());
//        int costint = Integer.parseInt(cost.substring(0,cost.indexOf("R")).trim());
        String Total = Integer.toString((int) (price*getItem(position).getQuant()));


        LayoutInflater inflater = LayoutInflater.from(c);
        convertView = inflater.inflate(resource,parent,false);

        TextView PName = (TextView) convertView.findViewById(R.id.Pname);
        TextView Pqty = (TextView) convertView.findViewById(R.id.Pqty);
        TextView Pprice = (TextView) convertView.findViewById(R.id.Pprice);
        TextView Ptotal = (TextView) convertView.findViewById(R.id.Ptotal);



        PName.setText(Name);
        Pqty.setText(Qty);
        Pprice.setText(cost);
        Ptotal.setText(Total);


        return convertView;
    }
}
