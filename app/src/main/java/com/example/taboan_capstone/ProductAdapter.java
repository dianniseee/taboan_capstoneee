package com.example.taboan_capstone;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> implements Filterable {


    Context mCtx;
    List<product> productList;
    List<product> list;
    ArrayList<product> cart = new ArrayList();

//    private OnCardInfoListener onCardInfoListener;

    public ProductAdapter(Context mCtx, List<product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
        this.list = new ArrayList<>(productList);

//        try{
//            this.onCardInfoListener = ((OnCardInfoListener)mCtx);
//        }catch (ClassCastException e){
//            throw new ClassCastException(e.getMessage());
//        }

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vegfruit_list,null);
        ProductViewHolder holder = new ProductViewHolder(view);
        return holder;
//        LayoutInflater inflater = LayoutInflater.from(mCtx);
//        View view = inflater.inflate(R.layout.vegfruit_list, null);
//        ProductViewHolder holder = new ProductViewHolder(view);
//        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
        product getposition = productList.get(position);
        holder.textViewName.setText(getposition.getProd_name());
        holder.textViewDesc.setText(getposition.getProd_desc());
        holder.textViewCategory.setText(getposition.getProd_category());
        holder.textViewPrice.setText(String.valueOf(getposition.getProd_price()));
        holder.textViewQuantity.setText(String.valueOf(getposition.getProd_quantity()));

        byte[] bytes = Base64.decode(getposition.getImage(),Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0, bytes.length);
        holder.imageView.setImageBitmap(bitmap);

        holder.Qty.setText(Integer.toString(getposition.getQuant()));

//        Glide.with(mCtx)
//                .load(getposition.getImage())
//                .into(holder.imageView);

        holder.addtobasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.Qty.setText("1");
                holder.addtobasket.setVisibility(View.INVISIBLE);
                holder.plus.setVisibility(View.VISIBLE);
                holder.minus.setVisibility(View.VISIBLE);
                holder.Qty.setVisibility(View.VISIBLE);
                getposition.setQuant(1);
                cart.add(getposition);
                notifyDataSetChanged();
            }
        });

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quant = Integer.parseInt((String) holder.Qty.getText());
                quant++;
                holder.Qty.setText(Integer.toString(quant));
                getposition.setQuant(quant);

                notifyDataSetChanged();
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quant = Integer.parseInt((String) holder.Qty.getText());
                quant--;
                if(quant<1){
                    holder.addtobasket.setVisibility(View.VISIBLE);
                    holder.plus.setVisibility(View.INVISIBLE);
                    holder.minus.setVisibility(View.INVISIBLE);
                    holder.Qty.setVisibility(View.INVISIBLE);
                    getposition.setQuant(0);
                    cart.remove(getposition);
                    notifyDataSetChanged();
                }else{
                    holder.Qty.setText(Integer.toString(quant));
                    getposition.setQuant(quant);
                    notifyDataSetChanged();
                }

            }
        });



    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            //list=new ArrayList<>(models);
            ArrayList<product> filterlist = new ArrayList<>();
            if(charSequence.toString().isEmpty()){
                filterlist.addAll(list);

            }else{
                for(product item : list){
                    if(item.getProd_name().toLowerCase().startsWith(charSequence.toString().toLowerCase())){
                        filterlist.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterlist;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            productList.clear();
            productList=(ArrayList<product>) filterResults.values;
            notifyDataSetChanged();
        }


    };

    class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        Button addtobasket,plus,minus;
        TextView textViewName, textViewDesc, textViewCategory, textViewPrice, textViewQuantity, Qty;
        CardView layout;
        public ProductViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
            addtobasket = itemView.findViewById(R.id.addToBasket_btn);
            Qty =itemView.findViewById(R.id.quantity);
            int Quant = Integer.parseInt((String) Qty.getText());
            minus = itemView.findViewById(R.id.minus);
            plus = itemView.findViewById(R.id.plus);

            if(Quant>0){
                this.addtobasket.setVisibility(View.INVISIBLE);
                this.plus.setVisibility(View.VISIBLE);
                this.minus.setVisibility(View.VISIBLE);
                this.Qty.setVisibility(View.VISIBLE);
            }
        }
    }

    }


