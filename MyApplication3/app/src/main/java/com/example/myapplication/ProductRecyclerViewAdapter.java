package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.db.ProductDAO;
import com.example.myapplication.db.UserDAO;

import java.util.ArrayList;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "ProductRecyclerViewAdap";


    private ArrayList<String> allProductInfo = new ArrayList<>();
    private Context context;
    ProductDAO productDAO;

    public ProductRecyclerViewAdapter(Context context, ArrayList<String> allProductInfo) {
        this.allProductInfo = allProductInfo;
        this.context = context;
    }


    @NonNull
    @Override
    public ProductRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //where we inflate the layout and give a look to our rows
        Log.d(TAG, "onCreateViewHolder: ");

        //LayoutInflater inflater = LayoutInflater.from(context);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_list_item, parent, false);
        ProductRecyclerViewAdapter.ViewHolder holder = new ProductRecyclerViewAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecyclerViewAdapter.ViewHolder holder, int position) {
        //assigning values to the views we created in the  layout file based on the position of the recycler view
        Log.d(TAG, "onBindViewHolder: ");

        holder.productInfo.setText(allProductInfo.get(position));

    }

    @Override
    public int getItemCount() {
        //return the number of items total in the product array list
        return allProductInfo.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        //grabbing the views from item layout file
        //create the variables

        TextView productInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //wire it up
            productInfo = itemView.findViewById(R.id.product_listitem_tv_userinfo);
        }
    }
}

